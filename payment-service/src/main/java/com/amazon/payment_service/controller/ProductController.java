package com.amazon.payment_service.controller;

import java.util.List;

import com.amazon.payment_service.entity.Product;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.amazon.payment_service.dto.ProductRequest;
import com.amazon.payment_service.dto.ProductResponse;
import com.amazon.payment_service.service.ProductService;

@RestController
@RequestMapping("/product")
public class ProductController {
	
	private final ProductService productService;

	public ProductController(ProductService productService) {
		this.productService=productService;
    }
	
	@GetMapping
	public List<Product> getProduct()
	{
		return productService.getProduct();	
	}  
	
	@PostMapping
	public ProductResponse insertProduct(@RequestBody ProductRequest request) 
	{
		return productService.insertProduct(request);
	}
	
	@PostMapping("/{productId}")
	public Product findById(@PathVariable Long productId, HttpServletRequest servletRequest)
	{
//		System.out.println("inside product controller - findById -start");

//		System.out.println("inside product controller "+servletRequest.getHeader("X-Correlation-ID"));

		System.out.println("inside product controller - findById -start");

		System.out.println("inside product controller - Correlation ID = " + servletRequest.getHeader("X-Correlation-ID"));

		return productService.findById(productId);
	}
	
	@GetMapping("/active")
	public List<Product> getActiveProducts()
	{
		return productService.getActiveProducts();
	}
	
	@PostMapping("/deactivate/{ProductID}")
	public String deactivateProduct(@PathVariable Long ProductID)
	{
		return productService.deactivateProduct(ProductID);
	}
}
