package com.quantum.engine.homy.util;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;

import com.google.gson.Gson;
import com.mysql.jdbc.StringUtils;

/**
 * plUpload上传工具
 * 
 * @author 孙宇
 * 
 */
public class PlUploadScaleServlet extends HttpServlet {

	public PlUploadScaleServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Gson gson = new Gson();
		String fileFolder = request.getParameter("fileFolder");// 前台传递过来的文件夹参数，如果有就在这个目录里面保存文件
		if (StringHelper.isNull(fileFolder)) {
			fileFolder = "/temp";// 避免前台没有传递这个参数的时候会报错
		}
		String datefolder = "/" + DateUtil.dateToString(new Date(), "yyyy") + "/"
				+ DateUtil.dateToString(new Date(), "MM") + "/" + DateUtil.dateToString(new Date(), "dd");// 日期命名的文件夹
		String webParentPath = new File(request.getSession().getServletContext().getRealPath("/")).getAbsolutePath();// 当前WEB环境的上层目录
		String realPath = webParentPath + ConfigUtil.get("uploadPath") + fileFolder + datefolder;// 文件上传到服务器的真实路径
		// System.out.println(realPath);
		String path = ConfigUtil.get("uploadPath") + fileFolder + datefolder;// 文件在服务器的相对路径
		// if (request.getSession() == null ||
		// RequestUtil.getCurrentUser(request) == null) {// 如果用户没有登录，则不允许上传
		if (1 == 2) { // TODO
			Map<String, Object> m = new HashMap<String, Object>();
			m.put("status", false);
			response.getWriter().write(gson.toJson(m));
		} else {
			File up = new File(realPath);
			if (!up.exists()) {
				up.mkdirs();
			}

			response.setCharacterEncoding("UTF-8");
			Integer chunk = null;// 分割块数
			Integer chunks = null;// 总分割数
			String tempFileName = null;// 上传到服务器的临时文件名
			String newFileName = null;// 最后合并后的新文件名
			String realUploadFileName = null;// 上传文件实际名称
			BufferedOutputStream outputStream = null;
			if (ServletFileUpload.isMultipartContent(request)) {
				try {
					DiskFileItemFactory factory = new DiskFileItemFactory();
					factory.setSizeThreshold(1024);
					// factory.setRepository(new File(repositoryPath));// 设置临时目录
					ServletFileUpload upload = new ServletFileUpload(factory);
					upload.setHeaderEncoding("UTF-8");
					// upload.setSizeMax(5 * 1024 * 1024);//
					// 设置附件最大大小，超过这个大小上传会不成功
					List<FileItem> items = upload.parseRequest(request);
					for (FileItem item : items) {
						if (item.isFormField()) {// 是文本域
							if (item.getFieldName().equals("name")) {
								// 防止乱码
								tempFileName = new String(item.get(), "UTF-8");
								;
							} else if (item.getFieldName().equals("chunk")) {
								chunk = Integer.parseInt(item.getString());
							} else if (item.getFieldName().equals("chunks")) {
								chunks = Integer.parseInt(item.getString());
							}
						} else {// 如果是文件类型
							if (tempFileName != null) {
								String chunkName = tempFileName;
								if (chunk != null) {
									chunkName = chunk + "_" + tempFileName;
								}
								File savedFile = new File(realPath, chunkName);
								realUploadFileName = item.getName();
								item.write(savedFile);
							}
						}
					}
					// app应用必须要原文件名
					if ("/appFile".equals(fileFolder) && realUploadFileName != null) {
						newFileName = realUploadFileName;
					} else {
					}
					newFileName = UUID.randomUUID().toString().replace("-", "").concat(".")
							.concat(FilenameUtils.getExtension(tempFileName));
					if (chunks == null) {// 如果不分块上传，那么只有一个名称，就是临时文件的名称
						newFileName = tempFileName;
					}

					if (chunk != null && chunk + 1 == chunks) {
						File theLastestFile = new File(realPath, newFileName);
						outputStream = new BufferedOutputStream(new FileOutputStream(theLastestFile));
						// 遍历文件合并
						for (int i = 0; i < chunks; i++) {
							File tempFile = new File(realPath, i + "_" + tempFileName);
							byte[] bytes = FileUtils.readFileToByteArray(tempFile);
							outputStream.write(bytes);
							outputStream.flush();
							tempFile.delete();
						}
						outputStream.flush();
					}
					String tFileName = webParentPath + ConfigUtil.get("uploadPath") + fileFolder + datefolder + "/"
							+ newFileName;
					System.out.println("tFileName:" + tFileName);
					// File tFile = new File(tFileName);
					// String md5str = MD5FileUtil.getFileMD5String(tFile);
					Map<String, Object> m = new HashMap<String, Object>();
					m.put("status", true);
					m.put("fileUrl", path + "/" + newFileName);

					// 压缩图片 
					int imgHeight = ImageUtil.getImgHeight(new File(request.getRealPath(path) + "/" + newFileName));
					int imgWidth = ImageUtil.getImgWidth(new File(request.getRealPath(path) + "/" + newFileName));
					if (imgHeight > 1024 || imgWidth > 1024) {
						
						double percent = 0;
						if (imgHeight > imgWidth) {
							imgWidth = imgWidth * 1024 / imgHeight;
							imgHeight = 1024;
						} else {
							imgHeight = imgHeight * 1024 / imgWidth;
							imgWidth = 1024;
						}
						String scale = ImageUtil.scale(request.getRealPath(path) + "/" + newFileName, imgWidth, imgHeight);
						String dateToString = DateUtil.dateToString(new Date(), "yyyy/MM/dd/");
						String dateToString2 = DateUtil.dateToString(new Date(), "yyyy\\MM\\dd/");
						int indexOf = scale.indexOf(dateToString);
						int indexOf2 = scale.indexOf(dateToString2);
						if(indexOf2 != -1){
							indexOf = indexOf2;
						}
						String newFile = scale.substring(indexOf+11);
						System.out.println("压缩后地址"+scale);
						m.put("fileUrl",  path + "/" + newFile);
						
					}

					response.getWriter().write(gson.toJson(m));
				} catch (FileUploadException e) {
					e.printStackTrace();
					Map<String, Object> m = new HashMap<String, Object>();
					m.put("status", false);
					response.getWriter().write(gson.toJson(m));
				} catch (Exception e) {
					e.printStackTrace();
					Map<String, Object> m = new HashMap<String, Object>();
					m.put("status", false);
					response.getWriter().write(gson.toJson(m));
				} finally {
					try {
						if (outputStream != null) {
							outputStream.close();
						}
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		}
	}
}
