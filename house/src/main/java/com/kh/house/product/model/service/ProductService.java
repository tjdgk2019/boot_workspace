package com.kh.house.product.model.service;

import java.util.List;
import java.util.Optional;

import com.kh.house.product.model.vo.Attachment;
import com.kh.house.product.model.vo.Product;

public interface ProductService {

	int save(Product house, Attachment at);
	List<Product> findAll(String type);
	int setProduct(Product product);
	String setFile(Attachment at);
	int deleteById(int houseNo);
	
   /*
   Product save(Product product);
   List<Product> findAll();
   Optional<Product> findById(int id);
   */

	
}