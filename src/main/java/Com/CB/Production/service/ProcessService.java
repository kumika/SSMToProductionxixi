package Com.CB.Production.service;

import Com.CB.Production.domain.customize.CustomResult;
import Com.CB.Production.domain.customize.EUDataGridResult;
import Com.CB.Production.domain.Process;
import java.util.List;

public interface ProcessService {
    Process get(String processId);

    List<Process> find();

    EUDataGridResult getList(Integer page, Integer rows, Process process);

    CustomResult insert(Process process);

    CustomResult updateAll(Process process);

    CustomResult deleteBatch(String[] ids);

    EUDataGridResult searchProcessByProcessId(Integer page, Integer rows, String searchValue);

    EUDataGridResult searchProcessByTechnologyPlanId(Integer page, Integer rows, String searchValue);
}
