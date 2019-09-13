package Com.CB.Production.service;

import Com.CB.Production.domain.UnqualifyApply;
import Com.CB.Production.domain.customize.CustomResult;
import Com.CB.Production.domain.customize.EUDataGridResult;

public interface UnqualifyService {
    EUDataGridResult getList(Integer page, Integer rows, UnqualifyApply unqualifyApply);

    UnqualifyApply get(String unqualifyApplyId);

    CustomResult insert(UnqualifyApply unqualifyApply);

    CustomResult updateAll(UnqualifyApply unqualifyApply);

    CustomResult updateNote(UnqualifyApply unqualifyApply);

    CustomResult delete(String id);

    CustomResult deleteBatch(String[] ids);

    EUDataGridResult searchUnqualifyByUnqualifyId(Integer page, Integer rows, String searchValue);

    EUDataGridResult searchUnqualifyByProductName(Integer page, Integer rows, String searchValue);
}
