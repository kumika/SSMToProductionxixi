package Com.CB.Production.service;

import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

public interface PictureService {
    Map<String,Object> uploadPicture(MultipartFile uploadFile);

    boolean deleteFile(String picName);
}
