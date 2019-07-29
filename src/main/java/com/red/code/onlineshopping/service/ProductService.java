package com.red.code.onlineshopping.service;

import java.util.List;

import com.red.code.onlineshopping.database.entity.model.Product;
import com.red.code.onlineshopping.service.dto.ProductDTO;

public interface ProductService {
	
	List<Product> getAllProduct();
	
	List<Product> getProductByActivatedStatus(boolean activated);
	
	Product getProductById(long id);
	
	Product addProduct(ProductDTO productDTO);
	
	Product editProduct(ProductDTO productDTO);
	
	void deleteProduct(long id);

}
