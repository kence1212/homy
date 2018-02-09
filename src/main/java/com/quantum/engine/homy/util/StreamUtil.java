package com.quantum.engine.homy.util;

import java.io.IOException;
import java.io.OutputStream;

public class StreamUtil {
	
	/**
	 * 关闭流操作
	 * @param out
	 */
	public static void close(OutputStream out){
		if(out != null){
			try {
				out.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

}
