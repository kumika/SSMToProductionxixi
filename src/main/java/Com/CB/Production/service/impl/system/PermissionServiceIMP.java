package Com.CB.Production.service.impl.system;

import Com.CB.Production.Mapper.authority.SysPermissionMapper;
import Com.CB.Production.Mapper.authority.SysRolePermissionMapper;
import Com.CB.Production.domain.authority.SysRole;
import Com.CB.Production.domain.authority.SysRolePermission;
import Com.CB.Production.domain.authority.SysRolePermissionExample;
import Com.CB.Production.domain.customize.CustomResult;
import Com.CB.Production.domain.customize.EUDataGridResult;
import Com.CB.Production.service.PermissionService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PermissionServiceIMP implements PermissionService {



    @Autowired
    SysRolePermissionMapper sysRolePermissionMapper;


    @Override
    public SysRolePermission get(String permissionId) {
        return sysRolePermissionMapper.selectByPrimaryKey(permissionId);
    }

    @Override
    public List<SysRolePermission> find() {
        SysRolePermissionExample example = new SysRolePermissionExample();
        return sysRolePermissionMapper.selectByExample(example);
    }

    @Override
    public SysRolePermission getByRoleId(String roleId) {
        SysRolePermissionExample example = new SysRolePermissionExample();
        SysRolePermissionExample.Criteria criteria = example.createCriteria();
        criteria.andSysRoleIdEqualTo(roleId);
        return sysRolePermissionMapper.selectByExample(example).get(0);
    }

    @Override
    public EUDataGridResult getList(Integer page, Integer rows, SysRolePermission sysRolePermission) {
        //查询列表
        SysRolePermissionExample example = new SysRolePermissionExample();
        //分页处理
        PageHelper.startPage(page, rows);
        List<SysRolePermission> list = sysRolePermissionMapper.selectByExample(example);
        //创建一个返回值对象
        EUDataGridResult result = new EUDataGridResult();
        result.setRows(list);
        //取记录总条数
        PageInfo<SysRolePermission> pageInfo = new PageInfo<>(list);
        result.setTotal(pageInfo.getTotal());
        return result;
    }

    @Override
    public CustomResult insert(SysRolePermission sysRolePermission) {
        int i = sysRolePermissionMapper.insert(sysRolePermission);
        if(i>0){
            return CustomResult.ok();
        }else{
            return CustomResult.build(101, "新增权限失败");
        }
    }

    @Override
    public CustomResult update(SysRolePermission sysRolePermission) {
        int i = sysRolePermissionMapper.updateByPrimaryKeySelective(sysRolePermission);
        if(i>0){
            return CustomResult.ok();
        }else{
            return CustomResult.build(101, "修改权限失败");
        }
    }

    @Override
    public CustomResult updateByRoleId(String roleId, String permission) {
        SysRolePermission sysRolePermission = new SysRolePermission();
        sysRolePermission.setSysPermissionId(permission);
        sysRolePermission.setSysRoleId(roleId);
        //修改角色权限表
        int i = sysRolePermissionMapper.updateRolePermission(sysRolePermission);
        if(i>0){
            return CustomResult.ok();
        }else{
            return null;
        }
    }

    @Override
    public CustomResult updateAll(SysRolePermission sysRolePermission) {
        int i = sysRolePermissionMapper.updateByPrimaryKey(sysRolePermission);
        if(i>0){
            return CustomResult.ok();
        }else{
            return CustomResult.build(101, "修改权限失败");
        }
    }

    @Override
    public CustomResult delete(String id) {
        int i = sysRolePermissionMapper.deleteByPrimaryKey(id);
        if(i>0){
            return CustomResult.ok();
        }else{
            return null;
        }
    }
}
