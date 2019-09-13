package Com.CB.Production.service;

import Com.CB.Production.domain.Manufacture;
import Com.CB.Production.domain.customize.CustomResult;
import Com.CB.Production.domain.customize.EUDataGridResult;
import Com.CB.Production.domain.vo.ManufactureVo;

import java.util.List;

public interface ManufactureService {
    ManufactureVo get(String manufactureId);

    List<ManufactureVo> find();

    EUDataGridResult getList(Integer page, Integer rows);

    CustomResult insert(Manufacture manufacture);

    CustomResult update(Manufacture manufacture);

    CustomResult updateAll(Manufacture manufacture);

    CustomResult delete(String id);

    CustomResult deleteBatch(String[] ids);

    EUDataGridResult searchManufactureByManufactureSN(Integer page, Integer rows, String searchValue);

    EUDataGridResult searchManufactureByManufactureOrderId(Integer page, Integer rows, String searchValue);

    EUDataGridResult searchManufactureByManufactureTechnologyName(Integer page, Integer rows, String searchValue);
}
