package Com.CB.Production.service;

import Com.CB.Production.domain.TechnologyPlan;
import Com.CB.Production.domain.customize.CustomResult;
import Com.CB.Production.domain.customize.EUDataGridResult;
import Com.CB.Production.domain.vo.TechnologyPlanVo;

import java.util.List;

public interface TechnologyPlanService {
    TechnologyPlan get(String technologyPlanId);

    List<TechnologyPlan> find();

    CustomResult insert(TechnologyPlan technologyPlan);

    CustomResult updateAll(TechnologyPlan technologyPlan);

    CustomResult deleteBatch(String[] ids);

    EUDataGridResult getList(Integer page, Integer rows, TechnologyPlanVo technologyPlanPO);

    EUDataGridResult searchTechnologyPlanByTechnologyPlanId(Integer page, Integer rows, String searchValue);

    EUDataGridResult searchTechnologyPlanByTechnologyName(Integer page, Integer rows, String searchValue);
}
