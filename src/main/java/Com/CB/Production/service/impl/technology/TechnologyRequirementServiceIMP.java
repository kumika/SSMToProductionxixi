package Com.CB.Production.service.impl.technology;

import Com.CB.Production.Mapper.TechnologyMapper;
import Com.CB.Production.Mapper.TechnologyRequirementMapper;
import Com.CB.Production.domain.Technology;
import Com.CB.Production.domain.TechnologyExample;
import Com.CB.Production.domain.TechnologyRequirement;
import Com.CB.Production.domain.customize.CustomResult;
import Com.CB.Production.domain.customize.EUDataGridResult;
import Com.CB.Production.domain.vo.TechnologyRequirementVo;
import Com.CB.Production.service.TechnologyRequirementService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class TechnologyRequirementServiceIMP implements TechnologyRequirementService {

    @Autowired
    TechnologyRequirementMapper technologyRequirementMapper;


    @Autowired
    TechnologyMapper technologyMapper;//为什么find（） 需要用这个mapper啊


    @Override
    public TechnologyRequirement get(String technologyRequirementId) {
        return technologyRequirementMapper.selectByPrimaryKey(technologyRequirementId);
    }

    @Override
    public List<Technology> find() {
        TechnologyExample example = new TechnologyExample();
        return technologyMapper.selectByExample(example);
    }

    @Override
    public EUDataGridResult getList(Integer page, Integer rows, TechnologyRequirementVo technologyRequirement) {
        //分页处理
        PageHelper.startPage(page, rows);
        List<TechnologyRequirementVo> list = technologyRequirementMapper.find(technologyRequirement);
        //创建一个返回值对象
        EUDataGridResult result = new EUDataGridResult();
        result.setRows(list);
        //取记录总条数
        PageInfo<TechnologyRequirementVo> pageInfo = new PageInfo<>(list);
        result.setTotal(pageInfo.getTotal());
        return result;
    }

    @Override
    public CustomResult insert(TechnologyRequirement technologyRequirement) {
        int i = technologyRequirementMapper.insert(technologyRequirement);
        if(i>0){
            return CustomResult.ok();
        }else{
            return CustomResult.build(101, "新增工艺要求信息失败");
        }
    }

    @Override
    public CustomResult updateAll(TechnologyRequirement technologyRequirement) {
        int i = technologyRequirementMapper.updateByPrimaryKey(technologyRequirement);
        if(i>0){
            return CustomResult.ok();
        }else{
            return CustomResult.build(101, "修改工艺要求信息失败");
        }
    }

    @Override
    public CustomResult updateRequirement(TechnologyRequirement technologyRequirement) {
        System.out.println("updateRequirement_service:");
        System.out.println(technologyRequirement.getRequirement());
        int i = technologyRequirementMapper.updateRequirement(technologyRequirement);
        if(i>0){
            return CustomResult.ok();
        }else{
            return CustomResult.build(101, "修改工艺要求失败");
        }
    }

    @Override
    public CustomResult deleteBatch(String[] ids) {
        int i = technologyRequirementMapper.deleteBatch(ids);
        if(i>0){
            return CustomResult.ok();
        }else{
            return null;
        }
    }

    @Override
    public EUDataGridResult searchTechnologyRequirementByTechnologyRequirementId(Integer page, Integer rows, String technologyRequirementId) {
        //分页处理
        PageHelper.startPage(page, rows);
        List<TechnologyRequirementVo> list = technologyRequirementMapper
                .searchTechnologyRequirementByTechnologyRequirementId(technologyRequirementId);
        //创建一个返回值对象
        EUDataGridResult result = new EUDataGridResult();
        result.setRows(list);
        //取记录总条数
        PageInfo<TechnologyRequirementVo> pageInfo = new PageInfo<>(list);
        result.setTotal(pageInfo.getTotal());
        return result;
    }

    @Override
    public EUDataGridResult searchTechnologyRequirementByTechnologyName(Integer page, Integer rows, String technologyName) {
        //分页处理
        PageHelper.startPage(page, rows);
        List<TechnologyRequirementVo> list = technologyRequirementMapper
                .searchTechnologyRequirementByTechnologyName(technologyName);
        //创建一个返回值对象
        EUDataGridResult result = new EUDataGridResult();
        result.setRows(list);
        //取记录总条数
        PageInfo<TechnologyRequirementVo> pageInfo = new PageInfo<>(list);
        result.setTotal(pageInfo.getTotal());
        return result;
    }
}
