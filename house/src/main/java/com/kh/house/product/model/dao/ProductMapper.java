package com.kh.house.product.model.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.kh.house.product.model.vo.Attachment;
import com.kh.house.product.model.vo.Product;

@Mapper
public interface ProductMapper {

	void saveHouse(Product house);

	void saveFile(Attachment at);

	int setProduct(Product product);

	int setFile(Attachment at);
	
	List<Product> findAll(String type);
	
	@Select("SELECT CHANGE_NAME FROM TB_FILE WHERE REF_HOUSE_NO = #{refHouseNo}")
	String getChaneName(@Param("refHouseNo") int refHouseNo);
	
	@Delete("DELETE FROM TB_FILE WHERE REF_HOUSE_NO = #{houseNo")
	void deleteFile(@Param("houseNo") int houseNo);
	
	@Delete("DELETE FROM TB_PRODUCT WHERE HOUSE_NO = #{houseNo")
	void deleteProduct(@Param("houseNo") int houseNo);

}
