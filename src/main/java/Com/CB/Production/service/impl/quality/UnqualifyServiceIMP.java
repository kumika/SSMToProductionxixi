package Com.CB.Production.service.impl.quality;

import Com.CB.Production.Mapper.UnqualifyApplyMapper;
import Com.CB.Production.domain.UnqualifyApply;
import Com.CB.Production.domain.customize.CustomResult;
import Com.CB.Production.domain.customize.EUDataGridResult;
import Com.CB.Production.domain.vo.UnqualifyApplyVO;
import Com.CB.Production.service.UnqualifyService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UnqualifyServiceIMP implements UnqualifyService {

    @Autowired
    UnqualifyApplyMapper unqualifyApplyMapper;


    @Override
    public EUDataGridResult getList(Integer page, Integer rows, UnqualifyApply unqualifyApply) {
        PageHelper.startPage(page, rows);
        List<UnqualifyApplyVO> list = unqualifyApplyMapper.find(unqualifyApply);
        //创建一个返回值对象
        EUDataGridResult result = new EUDataGridResult();
        result.setRows(list);
        //取记录总条数
        PageInfo<UnqualifyApplyVO> pageInfo = new PageInfo<>(list);
        result.setTotal(pageInfo.getTotal());
        return result;
    }

    @Override
    public UnqualifyApply get(String unqualifyApplyId) {
        return unqualifyApplyMapper.selectByPrimaryKey(unqualifyApplyId);
    }

    @Override
    public CustomResult insert(UnqualifyApply unqualify) {
        int i = unqualifyApplyMapper.insert(unqualify);
        if(i>0){
            return CustomResult.ok();
        }else{
            return CustomResult.build(101, "新增不合格品申请信息失败");
        }
    }

    @Override
    public CustomResult updateAll(UnqualifyApply unqualifyApply) {
        int i = unqualifyApplyMapper.updateByPrimaryKey(unqualifyApply);
        if(i>0){
            return CustomResult.ok();
        }else{
            return CustomResult.build(101, "修改不合格品申请信息失败");
        }
    }

    @Override
    public CustomResult updateNote(UnqualifyApply unqualify) {
        int i = unqualifyApplyMapper.updateNote(unqualify);
        if(i>0){
            return CustomResult.ok();
        }else{
            return CustomResult.build(101, "修改不合格品申请备注失败");
        }
    }

    @Override
    public CustomResult delete(String id) {
        int i = unqualifyApplyMapper.deleteByPrimaryKey(id);
        if(i>0){
            return CustomResult.ok();
        }else{
            return null;
        }
    }

    @Override
    public CustomResult deleteBatch(String[] ids) {
        int i = unqualifyApplyMapper.deleteBatch(ids);
        if(i>0){
            return CustomResult.ok();
        }else{
            return null;
        }
    }

    @Override
    public EUDataGridResult searchUnqualifyByUnqualifyId(Integer page, Integer rows, String unqualifyId) {
        //分页处理
        PageHelper.startPage(page, rows);
        List<UnqualifyApplyVO> list = unqualifyApplyMapper.searchUnqualifyByUnqualifyId(unqualifyId);
        //创建一个返回值对象
        EUDataGridResult result = new EUDataGridResult();
        result.setRows(list);
        //取记录总条数
        PageInfo<UnqualifyApplyVO> pageInfo = new PageInfo<>(list);
        result.setTotal(pageInfo.getTotal());
        return result;
    }

    @Override
    public EUDataGridResult searchUnqualifyByProductName(Integer page, Integer rows, String productName) {
        //分页处理
        PageHelper.startPage(page, rows);
        List<UnqualifyApplyVO> list = unqualifyApplyMapper.searchUnqualifyByProductName(productName);
        //创建一个返回值对象
        EUDataGridResult result = new EUDataGridResult();
        result.setRows(list);
        //取记录总条数
        PageInfo<UnqualifyApplyVO> pageInfo = new PageInfo<>(list);
        result.setTotal(pageInfo.getTotal());
        return result;
    }
}
