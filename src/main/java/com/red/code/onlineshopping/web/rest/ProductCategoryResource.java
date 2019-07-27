package com.red.code.onlineshopping.web.rest;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.red.code.onlineshopping.database.entity.model.ProductCategory;
import com.red.code.onlineshopping.service.ProductCategoryService;
import com.red.code.onlineshopping.service.dto.ProductCategoryDTO;

@RestController
@RequestMapping("/api")
public class ProductCategoryResource {
	
	private ProductCategoryService productCategoryService;

    @Autowired
    public ProductCategoryResource(ProductCategoryService productCategoryService) {
        this.productCategoryService = productCategoryService;
    }
    
    @GetMapping(value = "/productcategory", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<ProductCategoryDTO> getAllProductGroup(){
    	return productCategoryService.getProductCategory()
    			.stream()
    			.map(ProductCategoryDTO::new)
    			.collect(Collectors.toList());
    }
    
    @GetMapping(value = "/productcategory/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ProductCategoryDTO getProductGroupbyId(@PathVariable("id") Long id) {
        return new ProductCategoryDTO(productCategoryService.getProductCategorybyId(id));
    }
    @PostMapping(value = "/productcategory", produces = MediaType.APPLICATION_JSON_VALUE)
    public ProductCategoryDTO addProductCategory(@RequestBody @Validated ProductCategoryDTO productCategoryDTO) {
        return new ProductCategoryDTO(productCategoryService.createNewProductCategory(productCategoryDTO));
    }

    @PutMapping(value = "/productcategory", produces = MediaType.APPLICATION_JSON_VALUE)
    public ProductCategoryDTO editProductCategory(@RequestBody @Validated ProductCategoryDTO productCategoryDTO) {
        return new ProductCategoryDTO(productCategoryService.editProductCategory(productCategoryDTO));
    }

    @DeleteMapping(value = "/productcategory/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public void deleteProductGroup(@PathVariable("id") Long id) {
    	productCategoryService.deleteProductCategory(id);
    }

}
