package sei.dao;

import sei.pojo.ProductType;

public interface ProductTypeMapper {
    int deleteByPrimaryKey(Long id);

    int insert(ProductType record);

    int insertSelective(ProductType record);

    ProductType selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(ProductType record);

    int updateByPrimaryKeyWithBLOBs(ProductType record);

    int updateByPrimaryKey(ProductType record);
}