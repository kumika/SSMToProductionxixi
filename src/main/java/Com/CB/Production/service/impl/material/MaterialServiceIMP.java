package Com.CB.Production.service.impl.material;

import Com.CB.Production.Mapper.MaterialMapper;
import Com.CB.Production.domain.Material;
import Com.CB.Production.domain.MaterialExample;
import Com.CB.Production.domain.customize.CustomResult;
import Com.CB.Production.domain.customize.EUDataGridResult;
import Com.CB.Production.service.MaterialService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class MaterialServiceIMP implements MaterialService {

    @Autowired
    MaterialMapper materialMapper;

    @Override
    public Material get(String materialId) {
        return materialMapper.selectByPrimaryKey(materialId);

    }

    @Override
    public List<Material> find() {
        MaterialExample example = new MaterialExample();
        return materialMapper.selectByExample(example);
    }

    @Override
    public CustomResult insert(Material material) {
        int i = materialMapper.insert(material);
        if(i>0){
            return CustomResult.ok();
        }else{
            return CustomResult.build(101, "新增物料信息失败");
        }
    }

    @Override
    public CustomResult update(Material material) {
        int i = materialMapper.updateByPrimaryKeySelective(material);
        if(i>0){
            return CustomResult.ok();
        }else{
            return CustomResult.build(101, "修改物料信息失败");
        }
    }

    @Override
    public CustomResult updateAll(Material material) {
        int i = materialMapper.updateByPrimaryKey(material);
        if(i>0){
            return CustomResult.ok();
        }else{
            return CustomResult.build(101, "修改物料信息失败");
        }
    }

    @Override
    public CustomResult updateNote(Material material) {
        int i = materialMapper.updateNote(material);
        if(i>0){
            return CustomResult.ok();
        }else{
            return CustomResult.build(101, "修改物料备注失败");
        }
    }

    @Override
    public CustomResult delete(String id) {
        int i = materialMapper.deleteByPrimaryKey(id);
        if(i>0){
            return CustomResult.ok();
        }else{
            return null;
        }
    }

    @Override
    public CustomResult deleteBatch(String[] ids) {
        int i = materialMapper.deleteBatch(ids);
        if(i>0){
            return CustomResult.ok();
        }else{
            return null;
        }
    }

    @Override
    public EUDataGridResult getList(Integer page, Integer rows, Material material) {
        //查询列表
        MaterialExample example = new MaterialExample();
        //分页处理
        PageHelper.startPage(page, rows);
        List<Material> list = materialMapper.selectByExample(example);
        //创建一个返回值对象
        EUDataGridResult result = new EUDataGridResult();
        result.setRows(list);
        //取记录总条数
        PageInfo<Material> pageInfo = new PageInfo<>(list);
        result.setTotal(pageInfo.getTotal());
        return result;
    }

    @Override
    public EUDataGridResult searchMaterialByMaterialId(Integer page, Integer rows, String materialId) {
        //分页处理
        PageHelper.startPage(page, rows);
        List<Material> list = materialMapper.searchMaterialByMaterialId(materialId);
        //创建一个返回值对象
        EUDataGridResult result = new EUDataGridResult();
        result.setRows(list);
        //取记录总条数
        PageInfo<Material> pageInfo = new PageInfo<>(list);
        result.setTotal(pageInfo.getTotal());
        return result;
    }

    @Override
    public EUDataGridResult searchMaterialByMaterialType(Integer page, Integer rows, String materialType) {
        //分页处理
        PageHelper.startPage(page, rows);
        List<Material> list = materialMapper.searchMaterialByMaterialType(materialType);
        //创建一个返回值对象
        EUDataGridResult result = new EUDataGridResult();
        result.setRows(list);
        //取记录总条数
        PageInfo<Material> pageInfo = new PageInfo<>(list);
        result.setTotal(pageInfo.getTotal());
        return result;
    }
}
