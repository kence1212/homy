package com.quantum.engine.homy.util;


import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.Iterator;

import javax.imageio.ImageIO;
import javax.imageio.ImageReadParam;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;

/**
 * 图片操作工具
 * @author yinyuanping
 *
 */
public class ImageUtil {
	
	public static void main(String[] args) throws IOException {
	    
	   /* String a = new String("环太平洋".getBytes("utf-8"),"iso8859-1");
	    System.out.println(a);*/
		
	           
    }
	
	/**
	 * 得到�?��知道尺寸的压缩图�?
	 * @param srcImageFile
	 * @param standardWidth
	 * @param standardHeight
	 * @throws IOException 
	 */
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
	
	/**
	 * 是否是图�?
	 * @param imageName 图片全名（含后缀�?
	 * @return
	 */
	public static boolean isImage(String imageName){
		 if(imageName.lastIndexOf(".")>0){
			 String type = imageName.substring(imageName.lastIndexOf(".")+1);
			 if("jpg".equalsIgnoreCase(type) || "gif".equalsIgnoreCase(type) || "png".equalsIgnoreCase(type) || "bmp".equalsIgnoreCase(type)){
				 return true;
			 }
		 }
		return false;
	}
	
	/**
	 * 得到图片宽度
	 * @param file 图片文件
	 * @return 宽度
	 */
	public static int getImgWidth(File file) {
        InputStream is = null;
        BufferedImage src = null;
        int ret = -1;
        try {
            is = new FileInputStream(file);
            src = javax.imageio.ImageIO.read(is);
            ret = src.getWidth(null); // 得到源图�?
            is.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ret;
    }
	/**
	 * 得到图片高度
	 * @param 图片文件
	 * @return 高度
	 */
	public static int getImgHeight(File file) {
        InputStream is = null;
        BufferedImage src = null;
        int ret = -1;
        try {
            is = new FileInputStream(file);
            src = javax.imageio.ImageIO.read(is);
            ret = src.getHeight(null); // 得到源图�?
            is.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ret;
    }
	/**
	 * 裁剪图片
	 * @param srcpath  
	 * @param subpath  
	 * @param x      
	 * @param y
	 * @param width
	 * @param height
	 * @throws IOException
	 */
	public static void cut(String srcpath,String subpath,int x,int y,int width,int height) throws IOException {
        FileInputStream is = null;
        ImageInputStream iis = null;
        try {
            // 读取图片文件
            is = new FileInputStream(srcpath);
            /*
             * 
             * 返回包含�?��当前已注�?ImageReader �?Iterator，这�?ImageReader
             * 
             * 声称能够解码指定格式�?参数：formatName - 包含非正式格式名�?.
             * 
             * (例如 "jpeg" �?"tiff")�?�?
             */
            Iterator<ImageReader> it = ImageIO.getImageReadersByFormatName("jpg");
            ImageReader reader = it.next();
            // 获取图片�?
            iis = ImageIO.createImageInputStream(is);
            /*
             * 
             * <p>iis:读取源�?true:只向前搜�?</p>.将它标记�?‘只向前搜索’�?
             * 
             * 此设置意味着包含在输入源中的图像将只按顺序读取，可能允许 reader
             * 
             * 避免缓存包含与以前已经读取的图像关联的数据的那些输入部分�?
             */
            reader.setInput(iis, true);
            /*
             * 
             * <p>描述如何对流进行解码的类<p>.用于指定如何在输入时�?Java Image I/O
             * 
             * 框架的上下文中的流转换一幅图像或�?��图像。用于特定图像格式的插件
             * 
             * 将从�?ImageReader 实现�?getDefaultReadParam 方法中返�?
             * 
             * ImageReadParam 的实例�?
             */
            ImageReadParam param = reader.getDefaultReadParam();
            /*
             * 
             * 图片裁剪区域。Rectangle 指定了坐标空间中的一个区域，通过 Rectangle 对象
             * 
             * 的左上顶点的坐标(x，y)、宽度和高度可以定义这个区域�?
             */
            Rectangle rect = new Rectangle(x, y, width, height);
            // 提供�?�� BufferedImage，将其用作解码像素数据的目标�?
            param.setSourceRegion(rect);
            /*
             * 
             * 使用�?��供的 ImageReadParam 读取通过索引 imageIndex 指定的对象，并将
             * 
             * 它作为一个完整的 BufferedImage 返回�?
             */
            BufferedImage bi = reader.read(0, param);
            // 保存新图�?
            ImageIO.write(bi, "jpg", new File(subpath));
        } finally {
            if (is != null){
                try{
                    is.close();
                }catch(IOException e){
                    throw e;
                }
            }
            if (iis != null){
                try{
                    iis.close();
                }catch(IOException e){
                    throw e;
                }
            }
        }

    }
	/**
	 * 裁剪成一个正方形图片
	 * @param fileStr
	 * @param subStr
	 * @throws IOException
	 */
	public static void cutSquare(String sourceStr,String subStr) throws IOException{
	    File file = new File(sourceStr);
	    int width = getImgWidth(file);
        int height = getImgHeight(file);
        int small = width > height? height:width;
        cut(sourceStr,subStr, 0, 0, small, small);
	}
	
	


}
