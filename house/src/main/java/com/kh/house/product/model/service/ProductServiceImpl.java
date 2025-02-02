package com.kh.house.product.model.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.kh.house.product.model.dao.ProductMapper;
import com.kh.house.product.model.dao.ProductRepository;
import com.kh.house.product.model.vo.Attachment;
import com.kh.house.product.model.vo.Product;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

   private final ProductMapper productMapper;
   //private final ProductRepository productRepository;
   
   @Transactional
   @Override
   public int save(Product house, Attachment at) {
	   try {
		   saveHouse(house);
		   at.setRefHouseNo(house.getHouseNo());
		   saveFile(at);
		   return 1;
		   
	   }catch(Exception e) {
		   
		   return 0;
	   }   
   }
   
   public void saveHouse(Product house) {
	   productMapper.saveHouse(house);
   }
   
   public void saveFile(Attachment at) {
	   productMapper.saveFile(at);
   }
   
	@Override
	public List<Product> findAll(String type) {
		return productMapper.findAll(type);
	}
   
	@Override
	public int setProduct(Product product) {
		return productMapper.setProduct(product);
	}

	@Override
	public String setFile(Attachment at) {
		int result = productMapper.setFile(at);
		if(result > 0) {
			return productMapper.getChaneName(at.getRefHouseNo());
		}
		return "";
	}
	
	@Transactional
	@Override
	public int deleteById(int houseNo) {
		try {
			productMapper.deleteFile(houseNo);
			productMapper.deleteProduct(houseNo);
			
			return 1;
			
		}catch(Exception e) {
			
			return 0;
		}
		
	}


   
   /*
   @Override
   public Product save(Product product) {
      return productRepository.save(product);
   }

	@Override
	public List<Product> findAll() {
		return productRepository.findAll();
	}

	@Override
	public Optional<Product> findById(int id) {
		return productRepository.findById(id);
	}
	*/
	
}
