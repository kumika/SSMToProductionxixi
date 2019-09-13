package Com.CB.Production.controller.scheduling;

import Com.CB.Production.domain.Manufacture;
import Com.CB.Production.domain.customize.CustomResult;
import Com.CB.Production.domain.customize.EUDataGridResult;
import Com.CB.Production.domain.vo.ManufactureVo;
import Com.CB.Production.service.ManufactureService;
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
@RequestMapping("/manufacture")
public class ManufactureController {

    @Autowired
    ManufactureService manufactureService;

    @RequestMapping("/get/{manufactureId}")
    @ResponseBody
    public ManufactureVo getItemById(@PathVariable String manufactureId) {
        ManufactureVo manufactureVo = manufactureService.get(manufactureId);
        return manufactureVo;
    }


    @RequestMapping("/find")
    public String find() {
        return "manufacture_list";
    }

    @RequestMapping("/get_data")
    @ResponseBody
    public List<ManufactureVo> getData() {
        return manufactureService.find();
    }

    @RequestMapping("/add")
    public String add() throws Exception {
        return "manufacture_add";
    }

    @RequestMapping("/edit")
    public String edit() throws Exception {
        return "manufacture_edit";
    }

    @RequestMapping("/list")
    @ResponseBody
    public EUDataGridResult getList(Integer page, Integer rows) {
        EUDataGridResult result = manufactureService.getList(page, rows);
        return result;
    }


    @RequestMapping(value = "/insert", method = RequestMethod.POST)
    @ResponseBody
    private CustomResult insert(@Valid Manufacture manufacture, BindingResult bindingResult) throws Exception {
        CustomResult result;
        if (bindingResult.hasErrors()) {
            FieldError fieldError = bindingResult.getFieldError();
            return CustomResult.build(100, fieldError.getDefaultMessage());
        }
        if (manufactureService.get(manufacture.getManufactureSn()) != null) {
            result = new CustomResult(0, "该生产批号已经存在，请更换生产批号！", null);
        } else {
            result = manufactureService.insert(manufacture);
        }
        return result;
    }

    @RequestMapping(value = "/update")
    @ResponseBody
    private CustomResult update(@Valid Manufacture manufacture, BindingResult bindingResult) throws Exception {
        if (bindingResult.hasErrors()) {
            FieldError fieldError = bindingResult.getFieldError();
            return CustomResult.build(100, fieldError.getDefaultMessage());
        }
        return manufactureService.update(manufacture);
    }

    @RequestMapping(value = "/update_all")
    @ResponseBody
    private CustomResult updateAll(@Valid Manufacture manufacture, BindingResult bindingResult) throws Exception {
        if (bindingResult.hasErrors()) {
            FieldError fieldError = bindingResult.getFieldError();
            return CustomResult.build(100, fieldError.getDefaultMessage());
        }
        return manufactureService.updateAll(manufacture);
    }

    @RequestMapping(value = "/delete")
    @ResponseBody
    private CustomResult delete(String id) throws Exception {
        CustomResult result = manufactureService.delete(id);
        return result;
    }

    @RequestMapping(value = "/delete_batch")
    @ResponseBody
    private CustomResult deleteBatch(String[] ids) throws Exception {
        System.out.println(ids);
        CustomResult result = manufactureService.deleteBatch(ids);
        return result;
    }


    //根据生产流水号查找
    @RequestMapping("/search_manufacture_by_manufactureSn")
    @ResponseBody
    public EUDataGridResult searchManufactureByManufactureSN(Integer page, Integer rows, String searchValue) {
        EUDataGridResult result = manufactureService.searchManufactureByManufactureSN(page, rows, searchValue);

        return result;
    }


    //根据订单id查找
    @RequestMapping("/search_manufacture_by_manufactureOrderId")
    @ResponseBody
    public EUDataGridResult searchManufactureByManufactureOrderId(Integer page, Integer rows, String searchValue) {
        EUDataGridResult euDataGridResult = manufactureService.searchManufactureByManufactureOrderId(page, rows, searchValue);
        return euDataGridResult;
    }

    //根据工艺名称查找
    @RequestMapping("/search_manufacture_by_manufactureTechnologyName")
    @ResponseBody
    public EUDataGridResult searchManufactureByManufactureTechnologyName(Integer page, Integer rows, String searchValue) {
        EUDataGridResult euDataGridResult = manufactureService.searchManufactureByManufactureTechnologyName(page, rows, searchValue);
        return euDataGridResult;
    }

}
