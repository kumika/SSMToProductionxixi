package Com.CB.Production.service.impl.scheduling;

import Com.CB.Production.Mapper.CustomMapper;
import Com.CB.Production.domain.Custom;
import Com.CB.Production.domain.CustomExample;
import Com.CB.Production.domain.customize.CustomResult;
import Com.CB.Production.domain.customize.EUDataGridResult;
import Com.CB.Production.service.CustomService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomServiceIMPL implements CustomService{

    @Autowired
    CustomMapper customMapper;

    @Override
    public Custom get(String customId) {
        return customMapper.selectByPrimaryKey(customId);
    }

    @Override
    public List<Custom> find() {
        CustomExample example = new CustomExample();
        return customMapper.selectByExample(example);
    }

    @Override
    public EUDataGridResult getList(Integer page, Integer rows, Custom custom) {
        //查询客户列表
        CustomExample example = new CustomExample();
        //分页处理
        PageHelper.startPage(page, rows);
        List<Custom> list = customMapper.selectByExample(example);
        //创建一个返回值对象
        EUDataGridResult result = new EUDataGridResult();
        result.setRows(list);
        //取记录总条数
        PageInfo<Custom> pageInfo = new PageInfo<>(list);
        result.setTotal(pageInfo.getTotal());

        return result;
    }

    @Override
    public CustomResult insert(Custom custom) {
        int i = customMapper.insert(custom);
        if (i > 0) {
            return CustomResult.ok();
        } else {
            return CustomResult.build(101, "新增客户失败");
        }
    }

    @Override
    public CustomResult update(Custom custom) {
        int i = customMapper.updateByPrimaryKeySelective(custom);
        if (i > 0) {
            return CustomResult.ok();
        } else {
            return CustomResult.build(101, "修改客户失败");
        }
    }

    @Override
    public CustomResult updateAll(Custom custom) {
        int i = customMapper.updateByPrimaryKey(custom);
        if (i > 0) {
            return CustomResult.ok();
        } else {
            return CustomResult.build(101, "修改客户失败");
        }
    }

    @Override
    public CustomResult updateNote(Custom custom) {
        int i = customMapper.updateNote(custom);
        if (i > 0) {
            return CustomResult.ok();
        } else {
            return CustomResult.build(101, "更新客户介绍失败");
        }
    }

    @Override
    public CustomResult delete(String id) {
        int i = customMapper.deleteByPrimaryKey(id);
        if (i > 0) {
            return CustomResult.ok();
        } else {
            return CustomResult.build(101, "删除客户失败");
        }
    }

    @Override
    public CustomResult deleteBatch(String[] ids) {
        int i = customMapper.deleteBatch(ids);
        if (i > 0) {
            return CustomResult.ok();
        } else {
            return CustomResult.build(101, "删除客户失败");
        }
    }

    @Override
    public CustomResult changeStatus(String[] ids) {
        int i = customMapper.changeStatus(ids);
        if (i > 0) {
            return CustomResult.ok();
        } else {
            return CustomResult.build(101, "修改客户状态失败");
        }
    }

    @Override
    public EUDataGridResult searchCustomByCustomId(Integer page, Integer rows, String customId) {
        PageHelper.startPage(page, rows);
        List<Custom> list = customMapper.searchCustomByCustomId(customId);

        EUDataGridResult result = new EUDataGridResult();
        result.setRows(list);

        PageInfo<Custom> pageInfo = new PageInfo<>(list);
        result.setTotal(pageInfo.getTotal());

        return result;
    }

    @Override
    public EUDataGridResult searchCustomBYCustomName(Integer page, Integer rows, String customName) {
        PageHelper.startPage(page, rows);
        List<Custom> list = customMapper.searchCustomByCustomName(customName);

        EUDataGridResult result = new EUDataGridResult();
        result.setRows(list);

        PageInfo<Custom> pageInfo = new PageInfo<>(list);
        result.setTotal(pageInfo.getTotal());
        return result;
    }
}
