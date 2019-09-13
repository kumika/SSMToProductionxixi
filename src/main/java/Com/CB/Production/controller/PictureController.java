package Com.CB.Production.controller;

import Com.CB.Production.service.PictureService;

import Com.CB.Production.util.CollectionsFactory;
import Com.CB.Production.util.JsonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

/**
 * 
 * created on 2016年9月27日 
 *
 * 上传图片处理
 *
 * @author  megagao
 * @version  0.0.1
 */
@RestController
public class PictureController {

	@Autowired
	private PictureService pictureService;
	
	@RequestMapping("/pic/upload")
	//@ResponseBody
	public String pictureUpload(MultipartFile uploadFile) throws Exception{
		Map<String,Object> result = pictureService.uploadPicture(uploadFile);
		//为了保证功能的兼容性，需要把Result转换成json格式的字符串。
		String json = JsonUtils.objectToJson(result);
		return json;
	}
	
	@RequestMapping("/pic/delete")
	public String pictureDelete(@RequestParam String picName) throws Exception{
		pictureService.deleteFile(picName);
		Map<String,Object> result = CollectionsFactory.newHashMap();
		result.put("data", "success");
		String json = JsonUtils.objectToJson(result);
		return json;
	}
}
