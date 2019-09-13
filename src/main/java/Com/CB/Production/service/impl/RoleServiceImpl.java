package Com.CB.Production.service.impl;

import Com.CB.Production.Mapper.authority.SysRolePermissionMapper;
import Com.CB.Production.domain.authority.*;
import Com.CB.Production.domain.customize.CustomResult;
import Com.CB.Production.domain.customize.EUDataGridResult;
import Com.CB.Production.domain.vo.RoleVo;
import Com.CB.Production.Mapper.authority.SysRoleMapper;
import Com.CB.Production.Mapper.authority.SysUserRoleMapper;
import Com.CB.Production.service.RoleService;
import Com.CB.Production.util.IDUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private SysRoleMapper sysRoleMapper;


    @Autowired
    SysRolePermissionMapper sysRolePermissionMapper;
    @Autowired
    private SysUserRoleMapper sysUserRoleMapper;

    @Override
    public RoleVo findRoleByUserId(String userid) {

        //这里我没有认识到数据库上表格的内容和查询的内容--权限对象
        //数据库上的表格：权限----系统角色权限认证----用户角色----角色----系统用户
        //我错误认知把  系统用户 的属性里有  用户权限  外键，但是是没有的，系统用户只有id，用户名，密码，锁定这4个属性，
        //而用户角色---- 角色 之间是有角色id作为连接的

        SysUserRoleExample example = new SysUserRoleExample();
        SysUserRoleExample.Criteria criteria = example.createCriteria();
        criteria.andSysUserIdEqualTo(userid);

        SysUserRole sysUserRole = sysUserRoleMapper.selectByExample(example).get(0);
        RoleVo sysRole = sysRoleMapper.selectByPrimaryKey(sysUserRole.getSysRoleId());
        return sysRole;
    }

    @Override
    public RoleVo get(String roleId) {
        return sysRoleMapper.selectByPrimaryKey(roleId);
    }

    @Override
    public List<RoleVo> find() {
        SysRoleExample example = new SysRoleExample();
        return sysRoleMapper.selectByExample(example);
    }

    @Override
    public EUDataGridResult getList(Integer page, Integer rows, RoleVo role) {
        //查询列表
        SysRoleExample example = new SysRoleExample();
        //分页处理
        PageHelper.startPage(page, rows);
        List<RoleVo> list = sysRoleMapper.selectByExample(example);
        //创建一个返回值对象
        EUDataGridResult result = new EUDataGridResult();
        result.setRows(list);
        //取记录总条数
        PageInfo<RoleVo> pageInfo = new PageInfo<>(list);
        result.setTotal(pageInfo.getTotal());
        return result;
    }

    @Override
    public List<RoleVo> findByRoleNameAndId(String roleName, String roleId) {
        SysRoleExample example = new SysRoleExample();
        SysRoleExample.Criteria criteria = example.createCriteria();
        criteria.andRoleNameEqualTo(roleName);
        if(roleId != null){
            criteria.andRoleIdNotEqualTo(roleId);
        }
        List<RoleVo> sysRoleList = sysRoleMapper.selectByExample(example);
        return sysRoleList;
    }

    @Override
    public CustomResult insert(SysRole role) {
        //在业务层整合
        SysRolePermission sysRolePermission = new SysRolePermission();
        sysRolePermission.setId(IDUtils.genStringId());
        sysRolePermission.setSysRoleId(role.getRoleId());
        sysRolePermission.setSysPermissionId(role.getPermission());
        //存角色权限表
        int k = sysRolePermissionMapper.insertSelective(sysRolePermission);

        int i = sysRoleMapper.insert(role);
        if(i>0 && k>0){
            return CustomResult.ok();
        }else{
            return CustomResult.build(101, "新增角色信息失败");
        }
    }

    @Override
    public CustomResult update(SysRole role) {
        int i = sysRoleMapper.updateByPrimaryKeySelective(role);
        if(i>0){
            return CustomResult.ok();
        }else{
            return CustomResult.build(101, "修改角色信息失败");
        }
    }

    @Override
    public CustomResult updateAll(SysRole role) {
        //在业务层整合处理
        SysRolePermission sysRolePermission = new SysRolePermission();
        sysRolePermission.setSysRoleId(role.getRoleId());
        sysRolePermission.setSysPermissionId(role.getPermission());
        //修改角色权限表
        int k = sysRolePermissionMapper.updateRolePermission(sysRolePermission);

        int i = sysRoleMapper.updateByPrimaryKey(role);
        if(i>0 && k>0){
            return CustomResult.ok();
        }else{
            return CustomResult.build(101, "修改角色信息失败");
        }
    }

    @Override
    public CustomResult delete(String id) {
        int i = sysRoleMapper.deleteByPrimaryKey(id);
        if(i>0){
            return CustomResult.ok();
        }else{
            return null;
        }
    }

    @Override
    public CustomResult deleteBatch(String[] ids) {
        int i = sysRoleMapper.deleteBatch(ids);
        if(i>=0){
            return CustomResult.ok();
        }else{
            return null;
        }
    }

    @Override
    public EUDataGridResult searchRoleByRoleId(Integer page, Integer rows, String roleId) {
        //分页处理
        PageHelper.startPage(page, rows);
        List<RoleVo> list = sysRoleMapper.searchRoleByRoleId(roleId);
        //创建一个返回值对象
        EUDataGridResult result = new EUDataGridResult();
        result.setRows(list);
        //取记录总条数
        PageInfo<RoleVo> pageInfo = new PageInfo<>(list);
        result.setTotal(pageInfo.getTotal());
        return result;
    }

    @Override
    public EUDataGridResult searchRoleByRoleName(Integer page, Integer rows, String roleName) {
        //分页处理
        PageHelper.startPage(page, rows);
        List<RoleVo> list = sysRoleMapper.searchRoleByRoleName(roleName);
        //创建一个返回值对象
        EUDataGridResult result = new EUDataGridResult();
        result.setRows(list);
        //取记录总条数
        PageInfo<RoleVo> pageInfo = new PageInfo<>(list);
        result.setTotal(pageInfo.getTotal());
        return result;
    }
}
