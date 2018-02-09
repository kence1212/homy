package com.quantum.engine.homy.img;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Date;
import java.util.UUID;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;

import com.quantum.engine.homy.util.ConfigUtil;
import com.quantum.engine.homy.util.DateUtil;
import com.quantum.engine.homy.util.StringHelper;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

/**
 * 
 * @author nicya
 * @date 2017年11月23日 下午5:36:40
 */
public class Base64Image {

	public static String GetImageStr(String imgFile) {// 将图片文件转化为字节数组字符串，并对其进行Base64编码处理
		InputStream in = null;
		byte[] data = null;
		// 读取图片字节数组
		try {
			in = new FileInputStream(imgFile);
			data = new byte[in.available()];
			in.read(data);
			in.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		// 对字节数组Base64编码
		BASE64Encoder encoder = new BASE64Encoder();
		return encoder.encode(data);// 返回Base64编码过的字节数组字符串
	}

	public static boolean GenerateImage(String base64Str, String imgPath) { // 对字节数组字符串进行Base64解码并生成图片

		if (base64Str == null) // 图像数据为空
			return false;
		BASE64Decoder decoder = new BASE64Decoder();
		try {
			// Base64解码
			byte[] b = decoder.decodeBuffer(base64Str);
			for (int i = 0; i < b.length; ++i) {
				if (b[i] < 0) {// 调整异常数据
					b[i] += 256;
				}
			}
			// 生成jpeg图片
			OutputStream out = new FileOutputStream(imgPath);
			out.write(b);
			out.flush();
			out.close();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public static String getPhotoPath(String fileFolder, HttpServletRequest request, String base64Imge) {

		int indexOf = base64Imge.indexOf("/");
		int lastIndexOf = base64Imge.indexOf(";");
		String suffix = base64Imge.substring(indexOf + 1, lastIndexOf);

		if (StringHelper.isNull(fileFolder)) {
			fileFolder = "/temp";// 避免传空字符报错
		}
		String datefolder = "/" + DateUtil.dateToString(new Date(), "yyyy") + "/"
				+ DateUtil.dateToString(new Date(), "MM") + "/" + DateUtil.dateToString(new Date(), "dd");// 日期命名的文件夹
		String webParentPath = new File(request.getSession().getServletContext().getRealPath("/")).getAbsolutePath();// 当前WEB环境的上层目录
		String realPath = webParentPath + ConfigUtil.get("uploadPath") + fileFolder + datefolder;// 文件上传到服务器的真实路径
		System.out.println(realPath);
		String path = ConfigUtil.get("uploadPath") + fileFolder + datefolder;// 文件在服务器的相对路径
		String fileName = UUID.randomUUID().toString() + "." + suffix;
		File up = new File(realPath);
		if (!up.exists()) {
			up.mkdirs();
		}

		return realPath + "/" + fileName;
	}

	public static String scale(String srcImageFile, double standardWidth, double standardHeight){
		   try {
		    BufferedImage src = ImageIO.read(new File(srcImageFile)); // 读入文件

		    Image image = src.getScaledInstance((int) standardWidth,
		      (int) standardHeight, Image.SCALE_DEFAULT);
		    BufferedImage tag = new BufferedImage((int) standardWidth,
		      (int) standardHeight, BufferedImage.TYPE_INT_RGB);
		    Graphics g = tag.getGraphics();
		    g.drawImage(image, 0, 0, null);
		    g.dispose();
		    String path =appendExt(srcImageFile,Integer.parseInt(new java.text.DecimalFormat("0").format(standardWidth)));
		    ImageIO.write(tag, "jpg", new File(path));// 输出到文件流
		    return path;
		   } catch (IOException e) {
		    e.printStackTrace();
		    return null;
		   }
		}
	
	private static String appendExt(String fileSrc,int width){
		StringBuffer result = new StringBuffer();
		if(fileSrc.lastIndexOf(".")>=0){
			String type = fileSrc.substring(fileSrc.lastIndexOf(".")+1);
			result.append(fileSrc.substring(0, fileSrc.lastIndexOf(".")));
			result.append("_"+width);
			result.append(".").append(type);
		}
		return result.toString();
	}
	
	public static void main(String[] args) {
		
		File file = new File("C:/Users/Administrator/Pictures/8c1001e93901213feec966355ee736d12e2e9564.jpg");
		file.delete();
		//System.out.println(scale("C:/Users/Administrator/Pictures/8c1001e93901213feec966355ee736d12e2e9564.jpg",362,268));;
	}

}
