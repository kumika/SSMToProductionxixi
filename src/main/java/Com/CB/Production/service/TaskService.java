package Com.CB.Production.service;

import Com.CB.Production.domain.Task;
import Com.CB.Production.domain.customize.CustomResult;
import Com.CB.Production.domain.customize.EUDataGridResult;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface TaskService {
    Task get(String empId);

    List<Task> find();

    CustomResult insert(Task task);

    CustomResult update(Task task);

    CustomResult updateAll(Task task);

    CustomResult updateNote(Task task);

    CustomResult delete(String id);

    CustomResult deleteBatch(String[] ids);

    EUDataGridResult getList(Integer page, Integer rows);

    EUDataGridResult searchTaskByTaskId(Integer page, Integer rows, String searchValue);

    EUDataGridResult searchTaskByTaskWorkId(Integer page, Integer rows, String searchValue);

    EUDataGridResult searchTaskByTaskManufactureSn(Integer page, Integer rows, String searchValue);
}
