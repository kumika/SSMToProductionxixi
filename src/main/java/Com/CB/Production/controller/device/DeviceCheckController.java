package Com.CB.Production.controller.device;


import Com.CB.Production.domain.DeviceCheck;
import Com.CB.Production.domain.customize.CustomResult;
import Com.CB.Production.domain.customize.EUDataGridResult;
import Com.CB.Production.service.DeviceCheckService;
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
@RequestMapping("/deviceCheck")
public class DeviceCheckController {

    @Autowired
    DeviceCheckService deviceCheckService;






    @RequestMapping("/add")
    public String add() throws Exception{
        return "deviceCheck_add";
    }

    @RequestMapping("/edit")
    public String edit() throws Exception{
        return "deviceCheck_edit";
    }


    @RequestMapping(value="/insert", method= RequestMethod.POST)
    @ResponseBody
    private CustomResult insert(@Valid DeviceCheck task, BindingResult bindingResult) throws Exception {
        CustomResult result;
        if(bindingResult.hasErrors()){
            FieldError fieldError = bindingResult.getFieldError();
            return CustomResult.build(100, fieldError.getDefaultMessage());
        }
        if(deviceCheckService.get(task.getDeviceCheckId()) != null){
            result = new CustomResult(0, "该产品编号已经存在，请更换产品编号！", null);
        }else{
            result = deviceCheckService.insert(task);
        }
        return result;
    }

    @RequestMapping(value="/update")
    @ResponseBody
    private CustomResult update(@Valid DeviceCheck task, BindingResult bindingResult) throws Exception {
        if(bindingResult.hasErrors()){
            FieldError fieldError = bindingResult.getFieldError();
            return CustomResult.build(100, fieldError.getDefaultMessage());
        }
        return deviceCheckService.update(task);
    }

    @RequestMapping(value="/update_note")
    @ResponseBody
    private CustomResult updateAll(@Valid DeviceCheck task, BindingResult bindingResult) throws Exception {
        if(bindingResult.hasErrors()){
            FieldError fieldError = bindingResult.getFieldError();
            return CustomResult.build(100, fieldError.getDefaultMessage());
        }
        return deviceCheckService.updateNote(task);
    }




    @RequestMapping(value="/delete_batch")
    @ResponseBody
    private CustomResult deleteBatch(String[] ids) throws Exception {
        CustomResult result = deviceCheckService.deleteBatch(ids);
        return result;
    }


    @RequestMapping("/list")
    @ResponseBody
    public EUDataGridResult getItemList(Integer page, Integer rows,DeviceCheck deviceCheck) {
        EUDataGridResult euDataGridResult = deviceCheckService.getList(page, rows,deviceCheck);
        return euDataGridResult;
    }

    //搜索
    @RequestMapping("/search_deviceCheck_by_deviceCheckId")
    @ResponseBody
    public EUDataGridResult searchDeviceCheckByDeviceCheckId(Integer page, Integer rows, String searchValue) {
        EUDataGridResult euDataGridResult = deviceCheckService.searchDeviceCheckByDeviceCheckId(page, rows,searchValue);
        return euDataGridResult;
    }

    //搜索
    @RequestMapping("/search_deviceCheck_by_deviceName")
    @ResponseBody
    public EUDataGridResult searchDeviceCheckByDeviceName(Integer page, Integer rows,String searchValue) {
        EUDataGridResult euDataGridResult = deviceCheckService.searchDeviceCheckByDeviceName(page, rows,searchValue);
        return euDataGridResult;
    }


}
