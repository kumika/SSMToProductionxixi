package Com.CB.Production.service;

import Com.CB.Production.domain.MaterialConsume;
import Com.CB.Production.domain.customize.CustomResult;
import Com.CB.Production.domain.customize.EUDataGridResult;
import Com.CB.Production.domain.vo.MaterialConsumeVo;

import java.util.List;

public interface MaterialConsumeService {
    MaterialConsume get(String orderId);

    List<MaterialConsume> find();

    EUDataGridResult getList(Integer page, Integer rows, MaterialConsumeVo materialConsume);

    CustomResult insert(MaterialConsume materialConsume);

    CustomResult update(MaterialConsume materialConsume);

    CustomResult updateAll(MaterialConsume materialConsume);

    CustomResult updateNote(MaterialConsume materialConsume);

    CustomResult delete(String id);

    CustomResult deleteBatch(String[] ids);

    EUDataGridResult searchMaterialConsumeByConsumeId(Integer page, Integer rows, String searchValue);

    EUDataGridResult searchMaterialConsumeByMaterialId(Integer page, Integer rows, String searchValue);

    EUDataGridResult searchMaterialConsumeByWorkId(Integer page, Integer rows, String searchValue);
}
