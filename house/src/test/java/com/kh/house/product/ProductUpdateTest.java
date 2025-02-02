package com.kh.house.product;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kh.house.product.model.vo.Product;

import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
@Slf4j
@Transactional
public class ProductUpdateTest {
	
	@Autowired
	MockMvc mvc;
	
	@Autowired
	ObjectMapper objectMapper;
	
	@Test
	public void updateTest() throws Exception {
		//given / when / then
		// 준비  /  실행 /  검증
		
		Product product = Product.builder()
								 .houseNo(4)
								 .rateCount("테스트 후기")
								 .title("테스트 한옥")
								 .build();
		String jsonProduct = objectMapper.writeValueAsString(product);
		
		((ResultActions) ((MockHttpServletRequestBuilder) mvc.perform(put("/product/4"))).content(jsonProduct)
									  .contentType(MediaType.APPLICATION_JSON)
									  .accept(MediaType.APPLICATION_JSON))
									  .andExpect(status().isOk())
									  .andDo(print());
		
		log.info("===============");
		mvc.perform(get("/product/hanok")).andDo(print());
		
		
	}
	
	
	
	
}
