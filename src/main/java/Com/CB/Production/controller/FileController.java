package Com.CB.Production.controller;


import Com.CB.Production.service.FileService;
import Com.CB.Production.util.DownloadUtil;
import Com.CB.Production.util.JsonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;


/**
 * created on 2016年9月27日 
 *
 * 上传图片处理
 *
 * @author  megagao
 * @version  0.0.1
 */
@Controller
public class FileController {

	@Autowired
	private FileService fileService;

	/**
	 *		因为是多文件上传，所以参数request 使用MultipartHttpServletRequest类
	 * 		循环体iterator，方便点，不用向for循环体再写循环代码
	 * 		1		使用iterator 获取上传来的文件名
	 * 		2		因为页面使用Ajax， 所以创建String 变量json
	 * 		3		while 循环 ，条件是iterator.hasNext()
	 * 		4		创建String变量fileName, 从iterator获取文件名，赋值到fileName上，String fileName = iterator.next();
	 * 		5		创建MultipartFile类对象multipartFile， 根据文件名fileName从request获取文件，赋值到multipartFile
	 * 		6		使用FileService的上传方法，参数multipartFile，返回值是Map类型的变量XXX
	 * 		7		将返回值XXX，根据自定义转换类JsonUtils转换成json，并且赋值到变量json上
	 * 		8		返回json
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/file/upload", method=RequestMethod.POST)
	@ResponseBody
	public String handleFileUpload(MultipartHttpServletRequest request) throws Exception{
		Iterator<String> iterator = request.getFileNames();
		String json = null;
		while (iterator.hasNext()) {
			String fileName = iterator.next();
			MultipartFile multipartFile = request.getFile(fileName);
			Map<String,Object> result = fileService.uploadFile(multipartFile);
			json = JsonUtils.objectToJson(result);
		}
		return json;
	}
	
	@RequestMapping(value="/file/delete")
	@ResponseBody
	public String handleFileDelete(@RequestParam String fileName) throws Exception{
		fileService.deleteFile(fileName);
		Map<String,Object> result = new HashMap<String,Object>();	
		result.put("data", "success");
		String json = JsonUtils.objectToJson(result);
		return json;
	}
	
	@RequestMapping(value="/file/download")
	public void handleFileDownload(@RequestParam String fileName, HttpServletResponse response) throws Exception{
		fileName = fileName.substring(fileName.lastIndexOf("/")+1);
		String filePath = "D:\\upload\\temp\\file\\"+fileName;
		DownloadUtil du = new DownloadUtil();
		du.download(filePath, fileName, response, false);
	}
}
