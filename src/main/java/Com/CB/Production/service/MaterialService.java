package Com.CB.Production.service;

import Com.CB.Production.domain.Material;
import Com.CB.Production.domain.customize.CustomResult;
import Com.CB.Production.domain.customize.EUDataGridResult;

import java.util.List;

public interface MaterialService {
    Material get(String materialId);

    List<Material> find();

    CustomResult insert(Material material);

    CustomResult update(Material material);

    CustomResult updateAll(Material material);

    CustomResult updateNote(Material material);

    CustomResult delete(String id);

    CustomResult deleteBatch(String[] ids);

    EUDataGridResult getList(Integer page, Integer rows, Material material);

    EUDataGridResult searchMaterialByMaterialId(Integer page, Integer rows, String searchValue);

    EUDataGridResult searchMaterialByMaterialType(Integer page, Integer rows, String searchValue);
}
