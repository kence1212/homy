package com.quantum.engine.homy.excel;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class Main {
	
	public static void main(String[] args) throws IOException {
		File file = new File("D:\\stock.xls");
		InputStream is = new FileInputStream(file);
		SkuStockReader reader = new SkuStockReader(is);
		List<GoodsSkuStockModel> list = reader.readExcelContent();
		if(list != null & list.size() > 0 ){
			System.out.println(list.size());
			for(GoodsSkuStockModel model : list){
				System.out.println(model);
			}
		}
		is.close();
	}

}
