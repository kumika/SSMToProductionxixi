package Com.CB.Production.Mapper;

import Com.CB.Production.domain.Product;
import Com.CB.Production.domain.ProductExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ProductMapper {
    long countByExample(ProductExample example);

    int deleteByExample(ProductExample example);

    int deleteByPrimaryKey(String productId);

    int insert(Product record);

    int insertSelective(Product record);

    List<Product> selectByExample(ProductExample example);

    Product selectByPrimaryKey(String productId);

    int updateByExampleSelective(@Param("record") Product record, @Param("example") ProductExample example);

    int updateByExample(@Param("record") Product record, @Param("example") ProductExample example);

    int updateByPrimaryKeySelective(Product record);

    int updateByPrimaryKey(Product record);

    /*==================================================================*/

    int updateNote(Product product);

    int deleteBatch(String[] ids);

    List<Product> searchProductByProductType(String productType);

    List<Product> searchProductByProductName(String productName);

    List<Product> searchProductByProductId(String productId);
}