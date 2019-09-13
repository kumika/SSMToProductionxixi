package Com.CB.Production.service.impl;

import Com.CB.Production.service.FileService;
import Com.CB.Production.util.FileUtil;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

@Service
public class FileServiceIMP implements FileService {

    /**
     *      1       创建HashMap类型对象resultMap
     *      2       if判断,有3个条件，都为真时候可以进入
     *                  上传文件uploadFile不为null，
     *                  uploadFile的原文件名不为null
     *                  uploadFile的原文件名长度大于0
     *      3       创建String变量 fileName, 赋值上传文件uploadFile的原始文件名
     *      4       创建存放的文件路径 filePath  ， 随便哪个盘哪个文件，比如D:\\iB\\fileYY\\
     *      5       根据自定义工具类FileUtil，创建文件工具FileUtil对象 fu
     *      6       根据filePath，fileName作为2个参数，使用fu的方法，创建String类型的变量newName，作为新文件名
     *      7       根据newName, fileName作为参数，创建File类的对象file
     *      8       根据 file 作为参数，使用MultipartFile的方法，将内存中的文件写入磁盘
     *      9       上传成功后把文件的路径写回去，就是对resultMap进行 put操作， resultMap.put("url", "/file/" + newName);
     *      10      返回resultMap
     *      11      else 对resultMap进行put操作， resultMap.put("message", "文件异常");
     *      12      else 对resultMap进行put操作，resultMap.put("message", "文件上传发生异常");
     * @param uploadFile
     * @return
     */
    @Override
    public Map<String, Object> uploadFile(MultipartFile uploadFile) {
        Map<String,Object> resultMap = new HashMap<String,Object>();
        try {
            if(uploadFile!=null && uploadFile.getOriginalFilename()!=null && uploadFile.getOriginalFilename().length()>0){
                //生成一个新的文件名
                //取原始文件名
                String fileName = uploadFile.getOriginalFilename();

                //String date = new DateTime().toString("yyyy-MM-dd");
                //生成新文件名,防止重名
                //UUID.randomUUID();
                //String newName = oldName.substring(0,oldName.lastIndexOf("."))+"("+date+")"+oldName.substring(oldName.lastIndexOf("."));

                String filePath = "D:\\upload\\temp\\file\\";
                FileUtil fu = new FileUtil();
                String newName = fu.newFile(filePath, fileName);

                //新文件
                File file = new java.io.File(filePath+newName);
                //将内存中的文件写入磁盘
                uploadFile.transferTo(file);
                //图片上传成功后，将图片的地址写回
                resultMap.put("error", 0);
                resultMap.put("url", "/file/" + newName);
                return resultMap;

            }else{
                //返回结果
                resultMap.put("error", 1);
                resultMap.put("message", "文件异常");
                return resultMap;
            }
        } catch (Exception e) {
            resultMap.put("error", 1);
            resultMap.put("message", "文件上传发生异常");
            return resultMap;
        }
    }

    @Override
    public boolean deleteFile(String fileName) {
        fileName = fileName.substring(fileName.lastIndexOf("/")+1);
        fileName = "D:\\upload\\temp\\file\\"+fileName;
        FileUtil.deleteFile(fileName);
        return true;
    }
}
