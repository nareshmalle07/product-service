package com.amazon.payment_service.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.amazon.payment_service.Exception.ProductNotFoundException;
import com.amazon.payment_service.dto.ProductRequest;
import com.amazon.payment_service.dto.ProductResponse;
import com.amazon.payment_service.entity.Product;
import com.amazon.payment_service.repository.ProductRepository;

@Service
public class ProductService {

	private final ProductRepository productRepository;

	public ProductService(ProductRepository productRepository) {
		this.productRepository = productRepository;
	}

	public ProductResponse insertProduct(ProductRequest request) {
//		return productRepository.save(request);

		Product product = new Product();

		product.setPrice(request.getPrice());
		product.setName(request.getName());
		product.setActive(request.getActive());

		Product saveProduct = productRepository.save(product);

		ProductResponse response = new ProductResponse();
		response.setId(saveProduct.getId());
		response.setName(saveProduct.getName());
		response.setPrice(saveProduct.getPrice());

		return response;
	}

	public Product findById(Long productId) {
		return productRepository.findById(productId)
				.orElseThrow(() -> new ProductNotFoundException("product not found"));
	}

	public List<Product> getActiveProducts() {
		return productRepository.findByActiveTrue();
	}

	public String deactivateProduct(Long ProductID) {
		Product product = productRepository.findById(ProductID).orElseThrow(() -> new ProductNotFoundException("product not found with id : " + ProductID));
		product.setActive(false);

		productRepository.save(product);

		return "Product Deactivated";
	}

	public List<Product> getProduct() {

		return productRepository.findAll();
	}

}
