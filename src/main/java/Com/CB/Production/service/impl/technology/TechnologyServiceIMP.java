package Com.CB.Production.service.impl.technology;

import Com.CB.Production.Mapper.TechnologyMapper;
import Com.CB.Production.domain.Technology;
import Com.CB.Production.domain.TechnologyExample;
import Com.CB.Production.domain.customize.CustomResult;
import Com.CB.Production.domain.customize.EUDataGridResult;
import Com.CB.Production.service.TechnologyService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TechnologyServiceIMP implements TechnologyService {

    @Autowired
    TechnologyMapper technologyMapper;

    @Override
    public Technology get(String technologyId) {
        return technologyMapper.selectByPrimaryKey(technologyId);
    }

    @Override
    public List<Technology> find() {
        TechnologyExample example = new TechnologyExample();
        return technologyMapper.selectByExample(example);
    }

    @Override
    public CustomResult insert(Technology technology) {
        int i = technologyMapper.insert(technology);
        if(i>0){
            return CustomResult.ok();
        }else{
            return CustomResult.build(101, "新增工艺信息失败");
        }
    }

    @Override
    public CustomResult updateAll(Technology technology) {
        int i = technologyMapper.updateByPrimaryKey(technology);
        if(i>0){
            return CustomResult.ok();
        }else{
            return CustomResult.build(101, "修改工艺信息失败");
        }
    }

    @Override
    public CustomResult deleteBatch(String[] ids) {
        int i = technologyMapper.deleteBatch(ids);
        if(i>0){
            return CustomResult.ok();
        }else{
            return null;
        }
    }

    @Override
    public EUDataGridResult getList(Integer page, Integer rows, Technology technology) {
        //查询工艺列表
        TechnologyExample example = new TechnologyExample();
        //分页处理
        PageHelper.startPage(page, rows);
        List<Technology> list = technologyMapper.selectByExample(example);
        //创建一个返回值对象
        EUDataGridResult result = new EUDataGridResult();
        result.setRows(list);
        //取记录总条数
        PageInfo<Technology> pageInfo = new PageInfo<>(list);
        result.setTotal(pageInfo.getTotal());
        return result;
    }

    @Override
    public EUDataGridResult searchTechnologyByTechnologyId(Integer page, Integer rows, String technologyId) {
        //分页处理
        PageHelper.startPage(page, rows);
        List<Technology> list = technologyMapper.searchTechnologyByTechnologyId(technologyId);
        //创建一个返回值对象
        EUDataGridResult result = new EUDataGridResult();
        result.setRows(list);
        //取记录总条数
        PageInfo<Technology> pageInfo = new PageInfo<>(list);
        result.setTotal(pageInfo.getTotal());
        return result;
    }

    @Override
    public EUDataGridResult searchTechnologyByTechnologyName(Integer page, Integer rows, String technologyName) {
        //分页处理
        PageHelper.startPage(page, rows);
        List<Technology> list = technologyMapper.searchTechnologyByTechnologyName(technologyName);
        //创建一个返回值对象
        EUDataGridResult result = new EUDataGridResult();
        result.setRows(list);
        //取记录总条数
        PageInfo<Technology> pageInfo = new PageInfo<>(list);
        result.setTotal(pageInfo.getTotal());
        return result;
    }
}
