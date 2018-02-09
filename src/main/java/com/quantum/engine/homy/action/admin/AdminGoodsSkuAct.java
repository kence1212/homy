package com.quantum.engine.homy.action.admin;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.quantum.engine.homy.action.BaseAct;
import com.quantum.engine.homy.excel.GoodsSkuStockModel;
import com.quantum.engine.homy.excel.SkuStockReader;
import com.quantum.engine.homy.excel.SkuStockWriter;
import com.quantum.engine.homy.model.BasRegion;
import com.quantum.engine.homy.model.BizGoodsBase;
import com.quantum.engine.homy.model.BizGoodsModel;
import com.quantum.engine.homy.model.dto.BizGoodsSkuDto;
import com.quantum.engine.homy.model.dto.PageListQueryDto;
import com.quantum.engine.homy.model.dto.SkuPageListQueryDto;
import com.quantum.engine.homy.model.ext.BizGoodsModelExt;
import com.quantum.engine.homy.model.ext.BizGoodsSkuStockExt;
import com.quantum.engine.homy.model.page.Page;
import com.quantum.engine.homy.model.request.RequestUtil;
import com.quantum.engine.homy.model.result.BaseResponse;
import com.quantum.engine.homy.model.result.Json;
import com.quantum.engine.homy.service.BasRegionService;
import com.quantum.engine.homy.service.BizGoodsService;
import com.quantum.engine.homy.service.BizGoodsSkuService;
import com.quantum.engine.homy.service.GoodsModelService;
import com.quantum.engine.homy.util.DateUtil;

@Controller
@RequestMapping("/amsku")
public class AdminGoodsSkuAct extends BaseAct {
	
	@Autowired
	private BizGoodsSkuService bizGoodsSkuService;
	
	@Autowired
	private BizGoodsService goodsService;
	
	@Autowired
	private BasRegionService basRegionService;
	
	@RequestMapping("index.html")
	public String index(Model model,Integer id){
		
		model.addAttribute("goodsId", id);
		
		return jspUrl("list");
		
	}
	
	@RequestMapping("/getList.html")
	@ResponseBody
	public Page<BizGoodsSkuStockExt> getList(SkuPageListQueryDto dto){
		
		boolean validate = dto.validate();
		if(!validate){
			 Page<BizGoodsSkuStockExt> page = new Page<>(0, 0, 0);
			 page.setList(new ArrayList<BizGoodsSkuStockExt>());
			 return page;
		}
		
		return bizGoodsSkuService.getList(dto);
		
	}
	
	@RequestMapping("toAdd.html")
	public String toAdd(Model model,Integer id){
		
		if(id == null || id <= 0){
			redirectIndex();
		}
		
		BizGoodsBase goodsBase = goodsService.getById(id);
		
		List<Map<String,Object>> colors = goodsService.getColorById(id);
		
		List<Map<String,Object>> sizes = goodsService.getSizeById(id);
		
		List<Map<String,Object>> textures = goodsService.getTextureById(id);
		
		List<BasRegion> regions = basRegionService.getRegion(0);
		
		model.addAttribute("goodsBase", goodsBase);
		
		model.addAttribute("colors", colors);
		
		model.addAttribute("sizes", sizes);
		
		model.addAttribute("textures", textures);
		
		model.addAttribute("regions", regions);
		
		return jspUrl("add");
	}
	
	@RequestMapping("add.html")
	@ResponseBody
	public BaseResponse add(BizGoodsSkuDto dto,HttpServletRequest request){
		
		BaseResponse validate = dto.validate();
		if(validate.getErrorMsg() != null){
			return validate;
		}
		
		dto.setCreateId(RequestUtil.getCurrentUserId(request));
		BaseResponse br = bizGoodsSkuService.add(dto);
		return br;
		
	}
	
	@RequestMapping("toEdit.html")
	public String toEdit(Integer id,Model model){
		if(id == null){
			return redirectIndex();
		}
		BizGoodsSkuStockExt sku = bizGoodsSkuService.getById(id);
		if(sku == null){
			redirectIndex();
		}
		
		Integer goodsId = sku.getGoodsId();
		
		BizGoodsBase goodsBase = goodsService.getById(goodsId);
		
		List<Map<String,Object>> colors = goodsService.getColorById(goodsId);
		
		List<Map<String,Object>> sizes = goodsService.getSizeById(goodsId);
		
		List<Map<String,Object>> textures = goodsService.getTextureById(goodsId);
		
		List<BasRegion> regions = basRegionService.getRegion(0);
		
		model.addAttribute("goodsBase", goodsBase);
		
		model.addAttribute("colors", colors);
		
		model.addAttribute("sizes", sizes);
		
		model.addAttribute("textures", textures);
		
		model.addAttribute("regions", regions);
		
		model.addAttribute("sku", sku);
		
		return jspUrl("edit");
	}
	
	
	@RequestMapping("edit.html")
	@ResponseBody
	public BaseResponse edit(BizGoodsSkuDto dto,HttpServletRequest request){
		
		BaseResponse validate = dto.validate();
		if(validate.getErrorMsg() != null){
			return validate;
		}
		
		dto.setModifyId(RequestUtil.getCurrentUserId(request));
		BaseResponse br = bizGoodsSkuService.update(dto);
		return br;
	
	}
	
	@RequestMapping("delete.html")
	public void delete(Integer id,HttpServletResponse response){
		Json json = null;
		if(id == null){
			json = new Json();
			json.setSuccess(false);
			json.setMsg("没有ID");
		}else{
			int result = bizGoodsSkuService.delete(id);
			if(result == 0){
				json = new Json();
				json.setSuccess(false);
				json.setMsg("没有ID");
			}else{
				json = Json.genSuccess("删除成功");
			}
		}
		writeJson(json, response);
	}
	
	@RequestMapping("importExcel.do")
	public void importExcel(String fileUrl,Integer goodsId,HttpServletRequest request,HttpServletResponse response){
		Json json = new Json();
		json.setSuccess(false);
		String path = request.getRealPath(fileUrl);
		File file = new File(path);
		if(file.exists() && file.isFile() ){
			InputStream is = null;
			try {
				is = new FileInputStream(file);
				SkuStockReader reader  = new SkuStockReader(is);
				List<GoodsSkuStockModel> list = reader.readExcelContent();
				Integer result = bizGoodsSkuService.importSku(list, goodsId);
				reader.close();
				json.setSuccess(true);
				json.setObj(result);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
				json.setMsg("文件未找到");
			}finally{
				if(is != null){
					try {
						is.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		}else{
			json.setMsg("文件未找到");
		}
		writeJson(json, response);
	}
	
	@RequestMapping("exportExcel.do")
	public void exportExcel(Integer goodsId,HttpServletResponse response){
		OutputStream out = null;
		try {
			BizGoodsBase goods = goodsService.getById(goodsId);
			if(goods != null){
				List<BizGoodsSkuStockExt> list = bizGoodsSkuService.getByGoodsId(goodsId);
				String filename = goods.getGoodsName() + "-库存导出-" + DateUtil.dateToString(new Date(),"yyyyMMddHHmmss");
				filename = new String(filename.getBytes("UTF-8"),"ISO8859-1");
				response.setContentType("application/octet-stream"); 
				response.setHeader("Content-Disposition", "attachment;filename="+filename+".xls");
				out = response.getOutputStream();
				SkuStockWriter writer = new SkuStockWriter();
				HSSFWorkbook wb = writer.getWp(list);
				wb.write(out);
				out.flush();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			if(out != null){
				try {
					out.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			
		}
	}
	
	protected String jspUrl(String htmlUrl){
		return "jsp/admin/goods_sku/" + htmlUrl;
	}
	
}
