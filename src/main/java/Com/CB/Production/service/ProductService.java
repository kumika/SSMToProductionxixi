package Com.CB.Production.service;

import Com.CB.Production.domain.Product;
import Com.CB.Production.domain.customize.CustomResult;
import Com.CB.Production.domain.customize.EUDataGridResult;

import java.util.List;

public interface ProductService {
    Product get(String productId);

    List<Product> find();

    EUDataGridResult getList(Integer page, Integer rows);

    CustomResult insert(Product product);

    CustomResult update(Product product);

    CustomResult updateAll(Product product);

    CustomResult updateNote(Product product);

    CustomResult delete(String id);

    CustomResult deleteBatch(String[] ids);

    EUDataGridResult searchProductByProductId(Integer page, Integer rows, String searchValue);

    EUDataGridResult searchProductByProductName(Integer page, Integer rows, String searchValue);

    EUDataGridResult searchProductByProductType(Integer page, Integer rows, String searchValue);
}
