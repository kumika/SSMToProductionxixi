package Com.CB.Production.service.impl.scheduling;

import Com.CB.Production.Mapper.ProductMapper;
import Com.CB.Production.domain.Product;
import Com.CB.Production.domain.ProductExample;
import Com.CB.Production.domain.customize.CustomResult;
import Com.CB.Production.domain.customize.EUDataGridResult;
import Com.CB.Production.service.ProductService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceIMPL implements ProductService {

    @Autowired
    ProductMapper productMapper;

    @Override
    public Product get(String productId) {
        return productMapper.selectByPrimaryKey(productId);
    }

    @Override
    public List<Product> find() {
        ProductExample example = new ProductExample();
        return productMapper.selectByExample(example);
    }



    @Override
    public CustomResult insert(Product product) {
        int i = productMapper.insert(product);
        if( i > 0 ){
            return CustomResult.ok();
        }else{
            return CustomResult.build(101, "新增产品信息失败");
        }
    }

    @Override
    public CustomResult update(Product product) {
        int i = productMapper.updateByPrimaryKeySelective(product);
        if( i > 0 ){
            return CustomResult.ok();
        }else{
            return CustomResult.build(101, "更新产品信息失败");
        }
    }

    @Override
    public CustomResult updateAll(Product product) {
        int i = productMapper.updateByPrimaryKey(product);
        if( i > 0 ){
            return CustomResult.ok();
        }else{
            return CustomResult.build(101, "更新产品信息失败");
        }
    }

    @Override
    public CustomResult updateNote(Product product) {
        int i = productMapper.updateNote(product);
        if( i > 0 ){
            return CustomResult.ok();
        }else{
            return CustomResult.build(101, "修改产品备注失败");
        }
    }

    @Override
    public CustomResult delete(String id) {
        int i = productMapper.deleteByPrimaryKey(id);
        if( i > 0 ){
            return CustomResult.ok();
        }else{
            return CustomResult.build(101, "删除产品失败");
        }
    }

    @Override
    public CustomResult deleteBatch(String[] ids) {
        int i = productMapper.deleteBatch(ids);
        if( i > 0 ){
            return CustomResult.ok();
        }else{
            return CustomResult.build(101, "删除产品失败");
        }
    }

    @Override
    public EUDataGridResult searchProductByProductId(Integer page, Integer rows, String productId) {
        //分页处理
        PageHelper.startPage(page, rows);
        List<Product> productList = productMapper.searchProductByProductId(productId);
        //创建一个返回值对象
        EUDataGridResult euDataGridResult = new EUDataGridResult();
        euDataGridResult.setRows(productList);
        //取记录总条数
        PageInfo<Product> productPageInfo = new PageInfo<>(productList);
        euDataGridResult.setTotal(productPageInfo.getTotal());
        return euDataGridResult;
    }

    @Override
    public EUDataGridResult searchProductByProductName(Integer page, Integer rows, String productName) {
        //分页处理
        PageHelper.startPage(page, rows);
        List<Product> productList = productMapper.searchProductByProductName(productName);
        //创建一个返回值对象
        EUDataGridResult euDataGridResult = new EUDataGridResult();
        euDataGridResult.setRows(productList);
        //取记录总条数
        PageInfo<Product> productPageInfo = new PageInfo<>(productList);
        euDataGridResult.setTotal(productPageInfo.getTotal());
        return euDataGridResult;
    }

    @Override
    public EUDataGridResult searchProductByProductType(Integer page, Integer rows, String productType) {
        //分页处理
        PageHelper.startPage(page, rows);
        List<Product> productList = productMapper.searchProductByProductType(productType);
        //创建一个返回值对象
        EUDataGridResult euDataGridResult = new EUDataGridResult();
        euDataGridResult.setRows(productList);
        //取记录总条数
        PageInfo<Product> productPageInfo = new PageInfo<>(productList);
        euDataGridResult.setTotal(productPageInfo.getTotal());
        return euDataGridResult;
    }

    @Override
    public EUDataGridResult getList(Integer page, Integer rows) {
        //查询产品列表
        ProductExample example = new ProductExample();

        //分页处理
        PageHelper.startPage(page, rows);
        List<Product> productList = productMapper.selectByExample(example);
        //创建一个返回值对象
        EUDataGridResult euDataGridResult = new EUDataGridResult();
        euDataGridResult.setRows(productList);
        //取记录总条数
        PageInfo<Product> productPageInfo = new PageInfo<>(productList);
        euDataGridResult.setTotal(productPageInfo.getTotal());
        return euDataGridResult;
    }

}
