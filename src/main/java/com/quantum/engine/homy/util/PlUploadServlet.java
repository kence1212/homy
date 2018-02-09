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
import org.apache.log4j.Logger;

import com.google.gson.Gson;
import com.quantum.engine.homy.model.request.RequestUtil;

/**
 * plUpload上传工具
 * 
 * @author 孙宇
 * 
 */
public class PlUploadServlet extends HttpServlet {
	
	private static final Logger logger = Logger.getLogger(PlUploadServlet.class);

	private static final long serialVersionUID = 1L;

	public PlUploadServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    Gson gson = new Gson();
	    Map<String, Object> m = new HashMap<String, Object>();
	    boolean status = false;
		String fileFolder = request.getParameter("fileFolder");// 前台传递过来的文件夹参数，如果有就在这个目录里面保存文件
		if (StringHelper.isNull(fileFolder)) {
			fileFolder = "/temp";// 避免前台没有传递这个参数的时候会报错
		}
		String datefolder = createDateFilePath();
		String webParentPath = getParentFilePath(request);
		String realPath = webParentPath + ConfigUtil.get("uploadPath") + fileFolder + datefolder;// 文件上传到服务器的真实路径
		String path = ConfigUtil.get("uploadPath") + fileFolder + datefolder;// 文件在服务器的相对路径
		boolean isLogin = RequestUtil.getCurrentUserId(request) != null;
		if(isLogin){
			//初始化文件夹
			initFolder(realPath);
			
			response.setCharacterEncoding("UTF-8");
			Integer chunk = null;// 分割块数
			Integer chunks = null;// 总分割数
			String tempFileName = null;// 上传到服务器的临时文件名
			String newFileName = null;// 最后合并后的新文件名
			BufferedOutputStream outputStream = null;
			FileOutputStream fileOutputStream = null;
			if (ServletFileUpload.isMultipartContent(request)) {
				try {
					DiskFileItemFactory factory = new DiskFileItemFactory();
					factory.setSizeThreshold(1024);
					ServletFileUpload upload = new ServletFileUpload(factory);
					upload.setHeaderEncoding("UTF-8");
					List<FileItem> items = upload.parseRequest(request);
					for (FileItem item : items) {
						if (item.isFormField()) {// 是文本域
							if (item.getFieldName().equals("name")) {
							    //防止乱码
								tempFileName =new String(item.get(),"UTF-8");;
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
								File savedFile = new File(realPath,chunkName);
								item.write(savedFile);
							}
						}
					}
					newFileName = UUID.randomUUID().toString().replace("-", "").concat(".").concat(FilenameUtils.getExtension(tempFileName));
					if (chunks == null) {// 如果不分块上传，那么只有一个名称，就是临时文件的名称
					    newFileName = tempFileName;
					}

					if (chunk != null && chunk + 1 == chunks) {
					    File theLastestFile = new File(realPath, newFileName);
					    fileOutputStream = new  FileOutputStream(theLastestFile);
						outputStream = new BufferedOutputStream(fileOutputStream);
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
					String tFileName = realPath + "/" + newFileName;
					logger.info("tFileName:" + tFileName);
					status = true;
					m.put("fileUrl", path + "/" + newFileName);
				} catch (FileUploadException e) {
					e.printStackTrace();
				} catch (Exception e) {
					e.printStackTrace();
				} finally {
					StreamUtil.close(fileOutputStream);
					StreamUtil.close(outputStream);
				}
			}
		}
		m.put("status", status);
		response.getWriter().write(gson.toJson(m));
	}
	
	//初始化文件夹
	private void initFolder(String realPath){
		File up = new File(realPath);
		if (!up.exists()) {
			up.mkdirs();
		}
	}
	
	//获取上级目录
	private String getParentFilePath(HttpServletRequest request){
		return new File(request.getSession().getServletContext().getRealPath("/")).getAbsolutePath();// 当前WEB环境的上层目录
	}
	
	private String createDateFilePath(){
		String year = DateUtil.dateToString(new Date(), "yyyy") ;
		String month = DateUtil.dateToString(new Date(), "MM") ;
		String day = DateUtil.dateToString(new Date(), "dd");
		StringBuffer sb = new StringBuffer();
		sb.append("/").append(year).append("/").append(month).append("/").append(day);
		return sb.toString() ;
	}
}
