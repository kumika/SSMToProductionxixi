package Com.CB.Production.controller.scheduling;

import Com.CB.Production.domain.Custom;
import Com.CB.Production.domain.customize.CustomResult;
import Com.CB.Production.domain.customize.EUDataGridResult;
import Com.CB.Production.service.CustomService;
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
@RequestMapping("/custom")
public class CustomController {

    @Autowired
    CustomService customService;

    @RequestMapping("/get/{customId}")
    @ResponseBody
    public Custom getItemById(@PathVariable String customId) {
        Custom custom = customService.get(customId);
        return custom;
    }


    @RequestMapping("/find")
    public String find() {
        return "custom_list";
    }

    @RequestMapping("/add")
    public String add() {
        return "custom_add";
    }

    @RequestMapping("/edit")
    public String edit() {
        return "custom_edit";
    }

    @RequestMapping("/get_data")
    @ResponseBody
    public List<Custom> getData() {
        List<Custom> list = customService.find();
        return list;
    }


    @RequestMapping("/list")
    @ResponseBody
    public EUDataGridResult getItemList(Integer page, Integer rows, Custom custom) {
        EUDataGridResult result = customService.getList(page, rows, custom);
        return result;
    }


    @RequestMapping(value = "/insert", method = RequestMethod.POST)
    @ResponseBody
    public CustomResult insert(@Valid Custom custom, BindingResult bindingResult) {
        CustomResult result;
        if (bindingResult.hasErrors()) {
            FieldError fieldError = bindingResult.getFieldError();
            return CustomResult.build(100, fieldError.getDefaultMessage());
        }
        if (customService.get(custom.getCustomId()) != null) {
            result = new CustomResult(0, "该客户编号已经存在，请更换客户编号！", null);
        } else {
            result = customService.insert(custom);
        }
        return result;
    }


    @RequestMapping("/update")
    @ResponseBody
    private CustomResult update(@Valid Custom custom, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            FieldError fieldError = bindingResult.getFieldError();
            return CustomResult.build(100, fieldError.getDefaultMessage());
        }
        return customService.update(custom);
    }


    @RequestMapping("/update_all")
    @ResponseBody
    public CustomResult updateAll(@Valid Custom custom, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            FieldError fieldError = bindingResult.getFieldError();
            return CustomResult.build(100, fieldError.getDefaultMessage());
        }
        return customService.updateAll(custom);
    }

    @RequestMapping("/update_note")
    @ResponseBody
    public CustomResult update_note(@Valid Custom custom, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            FieldError fieldError = bindingResult.getFieldError();
            return CustomResult.build(100, fieldError.getDefaultMessage());
        }
        return customService.updateNote(custom);
    }


    @RequestMapping("delete")
    @ResponseBody
    public CustomResult delete(String id) {
        CustomResult result = customService.delete(id);
        return result;
    }


    @RequestMapping("/deleteBatch")
    @ResponseBody
    public CustomResult deleteBatch(String[] ids) {
        CustomResult result = customService.deleteBatch(ids);
        return result;
    }

    @RequestMapping("/change_status")
    @ResponseBody
    public CustomResult changeStatus(String[] ids) {
        CustomResult result = customService.changeStatus(ids);
        return result;
    }


    //根据客户id查找
    @RequestMapping("/search_custom_by_customId")
    @ResponseBody
    public EUDataGridResult searchCustomByCustomId(Integer page, Integer rows, String searchValue) {
        EUDataGridResult result = customService.searchCustomByCustomId(page, rows, searchValue);
        return result;
    }

    //根据客户名查找
    @RequestMapping("/search_custom_by_customName")
    @ResponseBody
    public EUDataGridResult searchCustomByCustomName(Integer page, Integer rows, String searchValue) {
        EUDataGridResult result = customService.searchCustomBYCustomName(page, rows, searchValue);
        return result;
    }

}
