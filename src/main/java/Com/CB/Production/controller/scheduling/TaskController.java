package Com.CB.Production.controller.scheduling;

import Com.CB.Production.domain.Product;
import Com.CB.Production.domain.Task;
import Com.CB.Production.domain.customize.CustomResult;
import Com.CB.Production.domain.customize.EUDataGridResult;
import Com.CB.Production.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/task")
public class TaskController {

    @Autowired
    TaskService taskService;

    @RequestMapping("/get/{empId}")
    @ResponseBody
    public Task getItemById(@PathVariable String empId) {
        Task ctask = taskService.get(empId);
        return ctask;
    }


    @RequestMapping("/get_data")
    @ResponseBody
    public List<Task> getData() {
        return taskService.find();
    }


    @RequestMapping("/find")
    public String find() {
        return "task_list";
    }


    @RequestMapping("/add")
    public String add() throws Exception{
        return "task_add";
    }

    @RequestMapping("/edit")
    public String edit() throws Exception{
        return "task_edit";
    }


    @RequestMapping(value="/insert", method= RequestMethod.POST)
    @ResponseBody
    private CustomResult insert(@Valid Task task, BindingResult bindingResult) throws Exception {
        CustomResult result;
        if(bindingResult.hasErrors()){
            FieldError fieldError = bindingResult.getFieldError();
            return CustomResult.build(100, fieldError.getDefaultMessage());
        }
        if(taskService.get(task.getTaskId()) != null){
            result = new CustomResult(0, "该产品编号已经存在，请更换产品编号！", null);
        }else{
            result = taskService.insert(task);
        }
        return result;
    }

    @RequestMapping(value="/update")
    @ResponseBody
    private CustomResult update(@Valid Task task, BindingResult bindingResult) throws Exception {
        if(bindingResult.hasErrors()){
            FieldError fieldError = bindingResult.getFieldError();
            return CustomResult.build(100, fieldError.getDefaultMessage());
        }
        return taskService.update(task);
    }

    @RequestMapping(value="/update_all")
    @ResponseBody
    private CustomResult updateAll(@Valid Task task, BindingResult bindingResult) throws Exception {
        if(bindingResult.hasErrors()){
            FieldError fieldError = bindingResult.getFieldError();
            return CustomResult.build(100, fieldError.getDefaultMessage());
        }
        return taskService.updateAll(task);
    }

    @RequestMapping(value="/update_note")
    @ResponseBody
    private CustomResult updateNote(@Valid Task task, BindingResult bindingResult) throws Exception {
        if(bindingResult.hasErrors()){
            FieldError fieldError = bindingResult.getFieldError();
            return CustomResult.build(100, fieldError.getDefaultMessage());
        }
        return taskService.updateNote(task);
    }

    @RequestMapping(value="/delete")
    @ResponseBody
    private CustomResult delete(String id) throws Exception {
        CustomResult result = taskService.delete(id);
        return result;
    }

    @RequestMapping(value="/delete_batch")
    @ResponseBody
    private CustomResult deleteBatch(String[] ids) throws Exception {
        CustomResult result = taskService.deleteBatch(ids);
        return result;
    }


    @RequestMapping("/list")
    @ResponseBody
    public EUDataGridResult getItemList(Integer page, Integer rows) {
        EUDataGridResult euDataGridResult = taskService.getList(page, rows);
        return euDataGridResult;
    }

    //根据生产派工id查找
    @RequestMapping("/search_task_by_taskId")
    @ResponseBody
    public EUDataGridResult searchTaskByTaskId(Integer page, Integer rows, String searchValue) {
        EUDataGridResult euDataGridResult = taskService.searchTaskByTaskId(page, rows,searchValue);
        return euDataGridResult;
    }

    //根据作业id查找
    @RequestMapping("/search_task_by_taskWorkId")
    @ResponseBody
    public EUDataGridResult searchTaskByTaskWorkId(Integer page, Integer rows,String searchValue) {
        EUDataGridResult euDataGridResult = taskService.searchTaskByTaskWorkId(page, rows,searchValue);
        return euDataGridResult;
    }

    //根据生产计划id查找
    @RequestMapping("/search_task_by_taskManufactureSn")
    @ResponseBody
    public EUDataGridResult searchTaskByTaskManufactureSn(Integer page, Integer rows,String searchValue) {
        EUDataGridResult euDataGridResult = taskService.searchTaskByTaskManufactureSn(page, rows,searchValue);
        return euDataGridResult;
    }



}
