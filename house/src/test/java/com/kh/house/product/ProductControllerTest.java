package com.kh.house.product;

import static org.springframework.test.web.client.match.MockRestRequestMatchers.content;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.kh.house.product.model.service.ProductService;

import lombok.extern.slf4j.Slf4j;

@ExtendWith(SpringExtension.class)
@Slf4j
@WebMvcTest //컨트롤러로만 테스트를 할 때 사용하는 애노테이션
public class ProductControllerTest {
	/*
	 * TDD Test Develop D != Unit Test(단위 테스트)
	 * TDD == 테스트 주도 개발
	 * 항상 안 돌아가는 테스트 코드를 먼저 작성함
	 * => 이걸 가지고 돌아가는 코드를 작성함
	 * => 리팩토링
	 * TDD의 특징은 시간이 오래 걸린다. / 뭐든 코드를 다 적용시켜 보기 때문에 문제 발생율이 적다 / 기한이 중요한 프로젝트에 적용할 확률이 적다
	 * 따라서 TDD는 기한에 쫓기지 않고 고객 서비스를 중요시 여기는 회사에서 많이 사용
	 * 
	 * --------------------------------------------------------------------------------------------------------
	 * 
	 * unit test == 기능단위의 테스트 코드를 작성
	 * 쉽고 용이 / 복잡한 절차 없이 간단한 테스트로 문제점을 발견 / 내가 만들어 놓은 메소드의 설명서 같은 용도로 사용(내가 만든 기능의 설명서)
	 * 자바에서는 unit 테스트를 할 때 Junit을 사용
	 */
	
	@Autowired
	private MockMvc mvc;
	
	@MockBean
	ProductService productService;
	
	@Test
	public void test() throws Exception {
		//perform 요청을 전달하는 메소드
		//get("/url")
		//post("/url")
		//put("/url")
		//delete("/url")
		
		//andExpect() => 요청의 결과로 여상되는 응답값을 지정해서 테스트를 진행
		//content().타입 => 응답 데이터 검증
		mvc.perform(get("/test"))
			//.andExpect()
			.andDo(print());
		//isOK => 200
		//isNotFound => 404
		
		mvc.perform(MockMvcRequestBuilders.get("/product/hanok"))
											.andExpect(status().isOk())
											.andDo(print());
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
