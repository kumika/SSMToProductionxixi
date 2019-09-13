package Com.CB.Production.controller.scheduling;

import Com.CB.Production.domain.COrder;
import Com.CB.Production.domain.customize.CustomResult;
import Com.CB.Production.domain.customize.EUDataGridResult;
import Com.CB.Production.domain.vo.COrderVo;
import Com.CB.Production.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/order")
public class OrderController {

    @Autowired
    OrderService orderService;

    @RequestMapping("/get/{orderId}")
    @ResponseBody
    public COrderVo getItemById(@PathVariable String orderId) {
        COrderVo cOrderVo = orderService.get(orderId);
        return cOrderVo;
    }

    @RequestMapping("/get_data")
    @ResponseBody
    public List<COrderVo> getData() {
        List<COrderVo> list = orderService.find();
        return list;
    }


    @RequestMapping("/find")
    public String find() {
        //查询，跳转到显示列表页面，进行全部查询
        return "order_list";
    }

    @RequestMapping("/add")
    public String add() {
        return "order_add";
    }

    @RequestMapping("edit")
    public String edit() {
        return "order_edit";
    }

    /**
     * 查询全部的表单内容
     * @param page
     * @param rows
     * @param cOrderVo
     * @return
     */
    @RequestMapping(value = "/list",method = RequestMethod.GET)
    @ResponseBody
    public EUDataGridResult getList(Integer page, Integer rows, COrderVo cOrderVo) {
        EUDataGridResult result = orderService.getList(page, rows, cOrderVo);
        return result;
    }


    @RequestMapping("/insert")
    @ResponseBody
    public CustomResult insert(@Valid COrder cOrder, BindingResult bindingResult) {
        CustomResult result;
        if (bindingResult.hasErrors()) {
            FieldError filedError = bindingResult.getFieldError();
            System.out.println(filedError.getDefaultMessage());
            return CustomResult.build(100, filedError.getDefaultMessage());
        }
        if (orderService.get(cOrder.getOrderId()) != null) {
            result = new CustomResult(0, "该订单编号已经存在，请更换订单编号！", null);
        } else {
            result = orderService.insert(cOrder);
        }
        return result;
    }

    @RequestMapping("/update")
    @ResponseBody
    public CustomResult update(@Valid COrder cOrder, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            FieldError filedError = bindingResult.getFieldError();
            System.out.println(filedError.getDefaultMessage());
            return CustomResult.build(100, filedError.getDefaultMessage());
        }
        return orderService.update(cOrder);
    }

    @RequestMapping("/update_all")
    @ResponseBody
    public CustomResult updateAll(@Valid COrder cOrder, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            FieldError filedError = bindingResult.getFieldError();
            System.out.println(filedError.getDefaultMessage());
            return CustomResult.build(100, filedError.getDefaultMessage());
        }
        return orderService.updateAll(cOrder);
    }

    /**
     * 修改订单要求
     *
     * @param cOrder
     * @param bindingResult
     * @return
     */
    @RequestMapping("/update_note")
    @ResponseBody
    public CustomResult updateNote(@Valid COrder cOrder, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            FieldError filedError = bindingResult.getFieldError();
            System.out.println(filedError.getDefaultMessage());
            return CustomResult.build(100, filedError.getDefaultMessage());
        }
        return orderService.updateNote(cOrder);
    }

    @RequestMapping("/delete")
    @ResponseBody
    public CustomResult delete(String id) {
        CustomResult result = orderService.delete(id);
        return result;
    }


    @RequestMapping("/delete_batch")
    @ResponseBody
    public CustomResult deleteBatch(String[] ids) {
        CustomResult result = orderService.deleteBatch(ids);
        return result;
    }

    //<!-- 修改状态，批量启用停用-->
    @RequestMapping("/change_status")
    @ResponseBody
    public CustomResult changStatus(String[] ids) {
        CustomResult result = orderService.changeStatus(ids);
        return result;
    }

    //根据订单id查找
    @RequestMapping("/search_order_by_orderId")
    @ResponseBody
    public EUDataGridResult searchOrderByOrderId(Integer page, Integer rows, String searchValue) {
        EUDataGridResult result = orderService.searchOrderByOrderId(page, rows, searchValue);
        return result;
    }

    //根据客户名称查找
    @RequestMapping("/search_order_by_orderCustom")
    @ResponseBody
    public EUDataGridResult searchOrderByCustom(Integer page, Integer rows, String searchValue) {
        EUDataGridResult result = orderService.searchOrderByCustom(page, rows, searchValue);
        return result;
    }

    //根据产品名称查找
    @RequestMapping("/search_order_by_orderProduct")
    @ResponseBody
    public EUDataGridResult searchOrderByProductName(Integer page, Integer rows, String searchValue) {
        EUDataGridResult result = orderService.searchOrderByProductName(page, rows, searchValue);
        return result;
    }
}
