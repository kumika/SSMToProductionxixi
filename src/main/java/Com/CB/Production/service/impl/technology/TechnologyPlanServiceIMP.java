package Com.CB.Production.service.impl.technology;

import Com.CB.Production.Mapper.TechnologyPlanMapper;
import Com.CB.Production.domain.TechnologyPlan;
import Com.CB.Production.domain.TechnologyPlanExample;
import Com.CB.Production.domain.customize.CustomResult;
import Com.CB.Production.domain.customize.EUDataGridResult;
import Com.CB.Production.domain.vo.TechnologyPlanVo;
import Com.CB.Production.service.TechnologyPlanService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TechnologyPlanServiceIMP implements TechnologyPlanService {

    @Autowired
    TechnologyPlanMapper technologyPlanMapper;


    @Override
    public TechnologyPlan get(String technologyPlanId) {
        return technologyPlanMapper.selectByPrimaryKey(technologyPlanId);
    }

    @Override
    public List<TechnologyPlan> find() {
        TechnologyPlanExample example = new TechnologyPlanExample();
        return technologyPlanMapper.selectByExample(example);
    }

    @Override
    public CustomResult insert(TechnologyPlan technologyPlan) {
        int i = technologyPlanMapper.insert(technologyPlan);
        if(i>0){
            return CustomResult.ok();
        }else{
            return CustomResult.build(101, "新增工艺计划信息失败");
        }
    }

    @Override
    public CustomResult updateAll(TechnologyPlan technologyPlan) {
        int i = technologyPlanMapper.updateByPrimaryKey(technologyPlan);
        if(i>0){
            return CustomResult.ok();
        }else{
            return CustomResult.build(101, "修改工艺计划信息失败");
        }
    }

    @Override
    public CustomResult deleteBatch(String[] ids) {
        int i = technologyPlanMapper.deleteBatch(ids);
        if(i>0){
            return CustomResult.ok();
        }else{
            return null;
        }
    }

    @Override
    public EUDataGridResult getList(Integer page, Integer rows, TechnologyPlanVo technologyPlan) {
        //分页处理
        PageHelper.startPage(page, rows);
        List<TechnologyPlan> list = technologyPlanMapper.find(technologyPlan);
        //创建一个返回值对象
        EUDataGridResult result = new EUDataGridResult();
        result.setRows(list);
        //取记录总条数
        PageInfo<TechnologyPlan> pageInfo = new PageInfo<>(list);
        result.setTotal(pageInfo.getTotal());
        return result;
    }

    @Override
    public EUDataGridResult searchTechnologyPlanByTechnologyPlanId(Integer page, Integer rows, String technologyPlanId) {
        //分页处理
        PageHelper.startPage(page, rows);
        List<TechnologyPlan> list = technologyPlanMapper.searchTechnologyPlanByTechnologyPlanId(technologyPlanId);
        //创建一个返回值对象
        EUDataGridResult result = new EUDataGridResult();
        result.setRows(list);
        //取记录总条数
        PageInfo<TechnologyPlan> pageInfo = new PageInfo<>(list);
        result.setTotal(pageInfo.getTotal());
        return result;
    }

    @Override
    public EUDataGridResult searchTechnologyPlanByTechnologyName(Integer page, Integer rows, String technologyName) {
        //分页处理
        PageHelper.startPage(page, rows);
        List<TechnologyPlan> list = technologyPlanMapper.searchTechnologyPlanByTechnologyName(technologyName);
        //创建一个返回值对象
        EUDataGridResult result = new EUDataGridResult();
        result.setRows(list);
        //取记录总条数
        PageInfo<TechnologyPlan> pageInfo = new PageInfo<>(list);
        result.setTotal(pageInfo.getTotal());
        return result;
    }
}
