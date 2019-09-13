package Com.CB.Production.controller.scheduling;

import Com.CB.Production.Mapper.WorkMapper;
import Com.CB.Production.domain.Work;
import Com.CB.Production.domain.customize.CustomResult;
import Com.CB.Production.domain.customize.EUDataGridResult;
import Com.CB.Production.domain.vo.WorkVo;
import Com.CB.Production.service.WorkService;
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
@RequestMapping("/work")
public class WorkController {

    @Autowired
    WorkService workService;


    @RequestMapping("/get/{empId}")
    @ResponseBody
    public WorkVo getItemById(@PathVariable String empId) {
        WorkVo cWork = workService.get(empId);
        return cWork;
    }


    @RequestMapping("/get_data")
    @ResponseBody
    public List<WorkVo> getData() {
        return workService.find();
    }


    @RequestMapping("/find")
    public String find() {
        return "work_list";
    }

    @RequestMapping("/add")
    public String add() throws Exception{
        return "work_add";
    }

    @RequestMapping("/edit")
    public String edit() throws Exception{
        return "work_edit";
    }


    @RequestMapping(value="/insert", method= RequestMethod.POST)
    @ResponseBody
    private CustomResult insert(@Valid Work task, BindingResult bindingResult) throws Exception {
        CustomResult result;
        if(bindingResult.hasErrors()){
            FieldError fieldError = bindingResult.getFieldError();
            return CustomResult.build(100, fieldError.getDefaultMessage());
        }
        if(workService.get(task.getWorkId()) != null){
            result = new CustomResult(0, "该产品编号已经存在，请更换产品编号！", null);
        }else{
            result = workService.insert(task);
        }
        return result;
    }

    @RequestMapping(value="/update")
    @ResponseBody
    private CustomResult update(@Valid Work task, BindingResult bindingResult) throws Exception {
        if(bindingResult.hasErrors()){
            FieldError fieldError = bindingResult.getFieldError();
            return CustomResult.build(100, fieldError.getDefaultMessage());
        }
        return workService.update(task);
    }

    @RequestMapping(value="/update_all")
    @ResponseBody
    private CustomResult updateAll(@Valid Work task, BindingResult bindingResult) throws Exception {
        if(bindingResult.hasErrors()){
            FieldError fieldError = bindingResult.getFieldError();
            return CustomResult.build(100, fieldError.getDefaultMessage());
        }
        return workService.updateAll(task);
    }


    @RequestMapping(value="/delete")
    @ResponseBody
    private CustomResult delete(String id) throws Exception {
        CustomResult result = workService.delete(id);
        return result;
    }

    @RequestMapping(value="/delete_batch")
    @ResponseBody
    private CustomResult deleteBatch(String[] ids) throws Exception {
        CustomResult result = workService.deleteBatch(ids);
        return result;
    }


    @RequestMapping("/list")
    @ResponseBody
    public EUDataGridResult getItemList(Integer page, Integer rows) {
        EUDataGridResult euDataGridResult = workService.getList(page, rows);
        return euDataGridResult;
    }

    //根据产品名称查找
    @RequestMapping("/search_work_by_workProduct")
    @ResponseBody
    public EUDataGridResult searchWorkByWorkProduct(Integer page, Integer rows, String searchValue) {
        EUDataGridResult euDataGridResult = workService.searchWorkByWorkProduct(page, rows,searchValue);
        return euDataGridResult;
    }

    //根据设备id查找
    @RequestMapping("/search_work_by_workDevice")
    @ResponseBody
    public EUDataGridResult searchWorkByWorkDevice(Integer page, Integer rows,String searchValue) {
        EUDataGridResult euDataGridResult = workService.searchWorkByWorkDevice(page, rows,searchValue);
        return euDataGridResult;
    }

    //根据工序id查找
    @RequestMapping("/search_work_by_workProcess")
    @ResponseBody
    public EUDataGridResult searchWorkByWorkProcess(Integer page, Integer rows,String searchValue) {
        EUDataGridResult euDataGridResult = workService.searchWorkByWorkProcess(page, rows,searchValue);
        return euDataGridResult;
    }

    //根据作业id查找
    @RequestMapping("/search_work_by_workId")
    @ResponseBody
    public EUDataGridResult searchWorkByWorkId(Integer page, Integer rows, String searchValue) throws Exception{
        EUDataGridResult result = workService.searchWorkByWorkId(page, rows, searchValue);
        return result;
    }


}
