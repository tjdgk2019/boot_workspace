package com.kh.house.product.controller;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kh.house.product.model.service.ProductService;
import com.kh.house.product.model.vo.Attachment;
import com.kh.house.product.model.vo.Product;
import com.kh.house.product.model.vo.ResponseData;

import jakarta.annotation.Resource;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@RequestMapping("/product")
@RequiredArgsConstructor
@CrossOrigin("*")
public class ProductController {
	private final ProductService productService;
	
	@PostMapping
	public ResponseEntity<ResponseData> save(@RequestParam("img")MultipartFile img,
											 @RequestParam("product") String product) throws JsonMappingException, JsonProcessingException{
		log.info("product Table에 Insert할 내용{}", product);
		log.info("File Table에 Inset할 내용 : {}",img);
		Product house = new ObjectMapper().readValue(product, Product.class);
		house.setCreateDate(LocalDateTime.now());
		
		if(!img.getOriginalFilename().equals("")) {
			String changeName = saveFile(img);
			String originName = img.getOriginalFilename();
			
			Attachment at = Attachment.builder()
									  .originName(originName)
									  .changeName(changeName)
									  .build();
			
			int result = productService.save(house,at);
			ResponseData rd = ResponseData.builder()
										  .message("성공")
										  .responseDate(result)
										  .build();
			return ResponseEntity.status(HttpStatus.OK).body(rd);
		}
		
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
	}
	
public String saveFile(MultipartFile upfile) {
		
		String originName = upfile.getOriginalFilename();
		String ext = originName.substring(originName.lastIndexOf("."));
		String currentTime = new SimpleDateFormat("yyyyMMddmmss").format(new Date());
		int ranNum = (int) Math.random() * 90000 + 10000;
		String changeName = "KH_" + currentTime + "_" + ranNum + ext;
		
		String absolutePath = new File("").getAbsolutePath() + "\\";
		String saveDir = "images/";
		File file = new File(saveDir);
		if(!file.exists()) {
			file.mkdirs();
		}
		
		try {
			upfile.transferTo(new File(absolutePath + saveDir + "/" + changeName));
		} catch (IllegalStateException | IOException e) {
			e.printStackTrace();
		} 
		
		return saveDir + changeName;
		
	}
	
	@GetMapping("/{type}")
	public ResponseEntity<ResponseData> findAll(@PathVariable("type") String type){
		List<Product> homes = productService.findAll(type);
		ResponseData rd = ResponseData.builder()
									  .message(type+"조회 성공")
									  .responseDate(homes)
									  .build();
		
		return ResponseEntity.ok(rd);
	}
	
	@GetMapping("/images/{filename}")
	public Resource showImage(@PathVariable("filename") String filename) throws MalformedURLException {

		return (Resource) new UrlResource("file:images/" + filename);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<ResponseData> setProduct(@PathVariable("id") int hoseNo,
			@RequestBody Product product){
		//log.info("{}, 상품 정보{}",hoseNo,product);
		product.setHouseNo(hoseNo);
		int result = productService.setProduct(product);
		
		ResponseData rd = ResponseData.builder()
									  .message("성공")
									  .responseDate(result)
									  .build();
		
		return ResponseEntity.ok(rd);
	}
	
	@PutMapping("/file")
	public ResponseEntity<ResponseData> setFile(@RequestParam("houseNo") int houseNo,
												@RequestParam("file")MultipartFile file){
		String changeName = saveFile(file);
		Attachment at =Attachment.builder()
								 .refHouseNo(houseNo)
								 .changeName(changeName)
								 .originName(file.getOriginalFilename())
								 .build();
		
		String result = productService.setFile(at);
		ResponseData rd = ResponseData.builder()
									  .message("파일 수정 성공")
									  .responseDate(result)
									  .build();
		return ResponseEntity.ok(rd);
	}
	
	
	@DeleteMapping("/{houseNo}")
	public ResponseEntity<ResponseData> deleteById(@PathVariable("houseNo") int houseNo){
		int result = productService.deleteById(houseNo);
		ResponseData rd= null;
		
		if(result > 0) {
			rd= ResponseData.builder()
							.message("삭제성공")
							.responseDate("01")
							.build();
		}else {
			rd=ResponseData.builder()
							.message("삭제실패")
							.responseDate("11")
							.build();
		}
		
		return ResponseEntity.ok(rd);
	}
	
	
	
	
	
	/*
	@PostMapping
	public ResponseEntity<ResponseData> save(@RequestBody String data) throws JsonMappingException, JsonProcessingException{
		log.info("전달값 : {}",data);
		Product product = new ObjectMapper().readValue(data,Product.class);
		product.setCreateDate(LocalDateTime.now());
		product.setHouseNo(1);
		
		log.info("vo가공: {}",product);
		
		Product savedObj = productService.save(product);
		log.info("반환받은 product: {}", savedObj);
		
		return null;
	}
	
	@GetMapping
	public ResponseEntity<ResponseData> findAll(){
		
		List<Product> products = productService.findAll();
		
		ResponseData rd = ResponseData.builder()
									  .responseDate(products)
									  .build();
		
		return ResponseEntity.status(HttpStatus.OK).body(rd);
	}
	
	@GetMapping("/id")
	public ResponseEntity<ResponseData> findById(@PathVariable("id") int id){
		Optional<Product> product = productService.findById(id);
		Product searched = product.get();
		
		ResponseData rd= ResponseData.builder().responseDate(product).build();
		
		return ResponseEntity.ok(rd);
	}
	*/
	
	
}
