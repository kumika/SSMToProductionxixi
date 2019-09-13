package Com.CB.Production.service;

import Com.CB.Production.domain.Technology;
import Com.CB.Production.domain.TechnologyRequirement;
import Com.CB.Production.domain.customize.CustomResult;
import Com.CB.Production.domain.customize.EUDataGridResult;
import Com.CB.Production.domain.vo.TechnologyRequirementVo;

import java.util.List;

public interface TechnologyRequirementService {
    TechnologyRequirement get(String technologyRequirementId);

    List<Technology> find();

    EUDataGridResult getList(Integer page, Integer rows, TechnologyRequirementVo technologyRequirementPO);

    CustomResult insert(TechnologyRequirement technologyRequirement);

    CustomResult updateAll(TechnologyRequirement technologyRequirement);

    CustomResult updateRequirement(TechnologyRequirement technologyRequirement);

    CustomResult deleteBatch(String[] ids);

    EUDataGridResult searchTechnologyRequirementByTechnologyRequirementId(Integer page, Integer rows, String searchValue);

    EUDataGridResult searchTechnologyRequirementByTechnologyName(Integer page, Integer rows, String searchValue);
}
