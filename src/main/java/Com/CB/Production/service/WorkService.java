package Com.CB.Production.service;

import Com.CB.Production.domain.Work;
import Com.CB.Production.domain.customize.CustomResult;
import Com.CB.Production.domain.customize.EUDataGridResult;
import Com.CB.Production.domain.vo.WorkVo;

import java.util.List;

public interface WorkService {
    WorkVo get(String empId);

    List<WorkVo> find();

    CustomResult update(Work task);

    CustomResult insert(Work task);

    CustomResult updateAll(Work task);

    CustomResult delete(String id);

    CustomResult deleteBatch(String[] ids);

    EUDataGridResult getList(Integer page, Integer rows);

    EUDataGridResult searchWorkByWorkProduct(Integer page, Integer rows, String searchValue);

    EUDataGridResult searchWorkByWorkDevice(Integer page, Integer rows, String searchValue);

    EUDataGridResult searchWorkByWorkProcess(Integer page, Integer rows, String searchValue);

    EUDataGridResult searchWorkByWorkId(Integer page, Integer rows, String searchValue);
}
