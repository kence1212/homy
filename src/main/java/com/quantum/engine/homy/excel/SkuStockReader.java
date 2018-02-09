package com.quantum.engine.homy.excel;

import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;

import com.quantum.engine.homy.util.StringHelper;

public class SkuStockReader {
	
	public SkuStockReader(InputStream is){
		try {
			fs = new POIFSFileSystem(is);
			wb = new HSSFWorkbook(fs);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private POIFSFileSystem fs;
	private HSSFWorkbook wb;
	private HSSFSheet sheet;
	private HSSFRow row;
	
	/**
	 * 读取Excel表格表头的内容
	 * 
	 * @param InputStream
	 * @return String 表头内容的数组
	 */
	public String[] readExcelTitle() {
		sheet = wb.getSheetAt(0);
		row = sheet.getRow(0);
		// 标题总列数
		int colNum = row.getPhysicalNumberOfCells();
		System.out.println("colNum:" + colNum);
		String[] title = new String[colNum];
		for (int i = 0; i < colNum; i++) {
			title[i] = getCellFormatValue(row.getCell((short) i));
		}
		return title;
	}
	
	private String getCellFormatValue(HSSFCell cell) {
		String cellvalue = "";
		if (cell != null) {
			// 判断当前Cell的Type
			switch (cell.getCellType()) {
			// 如果当前Cell的Type为NUMERIC
			case HSSFCell.CELL_TYPE_NUMERIC:
			case HSSFCell.CELL_TYPE_FORMULA: {
				// 判断当前的cell是否为Date
				if (HSSFDateUtil.isCellDateFormatted(cell)) {
					Date date = cell.getDateCellValue();
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
					cellvalue = sdf.format(date);
				}
				else {
					cellvalue = String.valueOf(cell.getNumericCellValue());
				}
				break;
			}
			case HSSFCell.CELL_TYPE_STRING:
				cellvalue = cell.getRichStringCellValue().getString();
				break;
			default:
				cellvalue = " ";
			}
		} else {
			cellvalue = "";
		}
		return cellvalue;
	}
	
	private Double getCellDouble(HSSFCell cell) {
		Double cellvalue = null;
		if (cell != null && cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC ){
			cellvalue = cell.getNumericCellValue();
		}
		return cellvalue;
	} 
	
	private Integer getCellInt(HSSFCell cell) {
		Double cellvalue = null;
		if (cell != null && cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC ){
			cellvalue = cell.getNumericCellValue();
		}
		return cellvalue == null?null:cellvalue.intValue();
	}
	
	
	public List<GoodsSkuStockModel> readExcelContent() {
		List<GoodsSkuStockModel> list = new ArrayList<GoodsSkuStockModel>();
		sheet = wb.getSheetAt(0);
		// 得到总行数
		int rowNum = sheet.getLastRowNum();
		row = sheet.getRow(0);
		// 正文内容应该从第二行开始,第一行为表头的标题
		for (int i = 1; i <= rowNum; i++) {
			row = sheet.getRow(i);
			int j = 0;
			Integer goodsId = getCellInt(row.getCell((short)j++));
			String country =  getCellFormatValue(row.getCell((short)j++)).trim();
			String province =  getCellFormatValue(row.getCell((short)j++)).trim();
			String city =  getCellFormatValue(row.getCell((short)j++)).trim();
			String colorName =  getCellFormatValue(row.getCell((short)j++)).trim();
			String sizeName =  getCellFormatValue(row.getCell((short)j++)).trim();
			String textureName =  getCellFormatValue(row.getCell((short)j++)).trim();
			Integer stock = getCellInt(row.getCell((short)j++));
			Double price = getCellDouble(row.getCell((short)j++));
			if(StringHelper.isNull(colorName)
					|| StringHelper.isNull(sizeName)
					|| StringHelper.isNull(textureName)
					|| stock == null
					|| price == null){
				continue;
			}
			GoodsSkuStockModel model = new GoodsSkuStockModel();
			model.setCityName(city);
			model.setColorName(colorName);
			model.setCountry(country);
			model.setGoodsId(goodsId);
			model.setPrice(price);
			model.setProvince(province);
			model.setSizeName(sizeName);
			model.setStock(stock);
			model.setTextureName(textureName);
			list.add(model);
		}
		return list;
	}
	
	public void close(){
		if(fs != null){
			try {
				fs.close();
			} catch (IOException e) {}
		}
	}
	

}
