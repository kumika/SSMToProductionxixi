package Com.CB.Production.service;

import Com.CB.Production.domain.Technology;
import Com.CB.Production.domain.customize.CustomResult;
import Com.CB.Production.domain.customize.EUDataGridResult;

import java.util.List;

public interface TechnologyService {
    Technology get(String technologyId);

    List<Technology> find();

    CustomResult insert(Technology technology);

    CustomResult updateAll(Technology technology);

    CustomResult deleteBatch(String[] ids);

    EUDataGridResult getList(Integer page, Integer rows, Technology technology);

    EUDataGridResult searchTechnologyByTechnologyId(Integer page, Integer rows, String searchValue);

    EUDataGridResult searchTechnologyByTechnologyName(Integer page, Integer rows, String searchValue);
}
