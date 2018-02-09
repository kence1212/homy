package com.quantum.engine.homy.excel;

import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import com.quantum.engine.homy.model.ext.BizGoodsSkuStockExt;

public class SkuStockWriter {
	
	public HSSFWorkbook getWp(List<BizGoodsSkuStockExt> list){
		HSSFWorkbook wb = new HSSFWorkbook();  
        // 第二步，在webbook中添加一个sheet,对应Excel文件中的sheet  
        HSSFSheet sheet = wb.createSheet("库存记录");  
        // 第三步，在sheet中添加表头第0行,注意老版本poi对Excel的行数列数有限制short  
        HSSFRow row = sheet.createRow(0);  
        // 第四步，创建单元格，并设置值表头 设置表头居中  
        HSSFCellStyle style = wb.createCellStyle();  
        style.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 创建一个居中格式  
 
        int i = 0 ; 
        HSSFCell cell = row.createCell(i++);  
        cell.setCellValue("商品ID");  
        cell.setCellStyle(style);  
        cell = row.createCell(i++);  
        cell.setCellValue("全国");  
        cell.setCellStyle(style);  
        cell = row.createCell(i++);  
        cell.setCellValue("省份");  
        cell.setCellStyle(style); 
        cell = row.createCell(i++);  
        cell.setCellValue("城市");  
        cell.setCellStyle(style);
        cell = row.createCell(i++);  
        cell.setCellValue("颜色");  
        cell.setCellStyle(style);
        cell = row.createCell(i++);  
        cell.setCellValue("规格");  
        cell.setCellStyle(style);
        cell = row.createCell(i++);  
        cell.setCellValue("材质");  
        cell.setCellStyle(style);
        cell = row.createCell(i++);  
        cell.setCellValue("库存");  
        cell.setCellStyle(style);
        cell = row.createCell(i++);  
        cell.setCellValue("价格");  
        cell.setCellStyle(style);
        if(list != null && list.size()>0){
        	for(int j = 0 ; j < list.size() ; j ++){
        		BizGoodsSkuStockExt model = list.get(j);
        		row = sheet.createRow((int) j+1);
        		int n = 0;
        		row.createCell(n++).setCellValue(model.getGoodsId());
        		row.createCell(n++).setCellValue("");
        		row.createCell(n++).setCellValue("");
        		row.createCell(n++).setCellValue(model.getCityName());
        		row.createCell(n++).setCellValue(model.getColorName());
        		row.createCell(n++).setCellValue(model.getSizeName());
        		row.createCell(n++).setCellValue(model.getTextureName());
        		row.createCell(n++).setCellValue(model.getStock());
        		row.createCell(n++).setCellValue(model.getPrice());
        	}
        }
        return wb;
	}

}
