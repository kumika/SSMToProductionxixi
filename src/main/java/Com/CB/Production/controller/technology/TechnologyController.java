package Com.CB.Production.controller.technology;

import Com.CB.Production.domain.Technology;
import Com.CB.Production.domain.customize.CustomResult;
import Com.CB.Production.domain.customize.EUDataGridResult;
import Com.CB.Production.service.TechnologyService;
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
@RequestMapping("/technology")
public class TechnologyController {


    @Autowired
    TechnologyService technologyService;

    @RequestMapping("/get/{technologyId}")
    @ResponseBody
    public Technology getItemById(@PathVariable String technologyId) throws Exception{
        Technology technology = technologyService.get(technologyId);
        return technology;
    }

    @RequestMapping("/find")
    public String find() throws Exception{
        return "technology_list";
    }

    @RequestMapping("/add")
    public String add() throws Exception{
        return "technology_add";
    }

    @RequestMapping("/edit")
    public String edit() throws Exception{
        return "technology_edit";
    }

    @RequestMapping("/get_data")
    @ResponseBody
    public List<Technology> getData() throws Exception{
        List<Technology> list = technologyService.find();
        return list;
    }


    @RequestMapping("/list")
    @ResponseBody
    public EUDataGridResult getItemList(Integer page, Integer rows, Technology technology) throws Exception{
        EUDataGridResult result = technologyService.getList(page, rows, technology);
        return result;
    }

    @RequestMapping(value="/insert", method= RequestMethod.POST)
    @ResponseBody
    private CustomResult insert(@Valid Technology technology, BindingResult bindingResult) throws Exception {
        CustomResult result;
        if(bindingResult.hasErrors()){
            FieldError fieldError = bindingResult.getFieldError();
            return CustomResult.build(100, fieldError.getDefaultMessage());
        }
        if(technologyService.get(technology.getTechnologyId()) != null){
            result = new CustomResult(0, "该工艺编号已经存在，请更换工艺编号！", null);
        }else{
            result = technologyService.insert(technology);
        }
        return result;
    }

    @RequestMapping(value="/update_all")
    @ResponseBody
    private CustomResult updateAll(@Valid Technology technology, BindingResult bindingResult) throws Exception {
        if(bindingResult.hasErrors()){
            FieldError fieldError = bindingResult.getFieldError();
            return CustomResult.build(100, fieldError.getDefaultMessage());
        }
        return technologyService.updateAll(technology);
    }

    @RequestMapping(value="/delete_batch")
    @ResponseBody
    private CustomResult deleteBatch(String[] ids) throws Exception {
        CustomResult result = technologyService.deleteBatch(ids);
        return result;
    }

    //根据工艺id查找
    @RequestMapping("/search_technology_by_technologyId")
    @ResponseBody
    public EUDataGridResult searchTechnologyByTechnologyId(Integer page, Integer rows, String searchValue)
            throws Exception{
        EUDataGridResult result = technologyService.searchTechnologyByTechnologyId(page, rows, searchValue);
        return result;
    }

    //根据工艺名称查找
    @RequestMapping("/search_technology_by_technologyName")
    @ResponseBody
    public EUDataGridResult searchTechnologyByTechnologyName(Integer page, Integer rows, String searchValue)
            throws Exception{
        EUDataGridResult result = technologyService.searchTechnologyByTechnologyName(page, rows, searchValue);
        return result;
    }


}
