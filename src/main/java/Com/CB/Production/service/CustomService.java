package Com.CB.Production.service;

import Com.CB.Production.domain.Custom;
import Com.CB.Production.domain.customize.CustomResult;
import Com.CB.Production.domain.customize.EUDataGridResult;

import java.util.List;

public interface CustomService {
    Custom get(String customId);

    List<Custom> find();

    EUDataGridResult getList(Integer page, Integer rows, Custom custom);

    CustomResult insert(Custom custom);

    CustomResult update(Custom custom);

    CustomResult updateAll(Custom custom);

    CustomResult updateNote(Custom custom);

    CustomResult delete(String id);

    CustomResult deleteBatch(String[] ids);

    CustomResult changeStatus(String[] ids);

    EUDataGridResult searchCustomByCustomId(Integer page, Integer rows, String searchValue);

    EUDataGridResult searchCustomBYCustomName(Integer page, Integer rows, String searchValue);
}
