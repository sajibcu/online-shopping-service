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

import com.red.code.onlineshopping.service.ProductGroupService;
import com.red.code.onlineshopping.service.dto.ProductGroupDTO;

@RestController
@RequestMapping("/api")
public class ProductGroupResource {
	
	private ProductGroupService productGroupService;

    @Autowired
    public ProductGroupResource(ProductGroupService productGroupService) {
        this.productGroupService = productGroupService;
    }
    
    @GetMapping(value = "/productgroup", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<ProductGroupDTO> getAllProductGroup(){
    	return productGroupService.getProductGroup()
    			.stream()
    			.map(ProductGroupDTO::new)
    			.collect(Collectors.toList());
    }
    
    @GetMapping(value = "/productgroup/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ProductGroupDTO getProductGroupbyId(@PathVariable("id") Long id) {
        return new ProductGroupDTO(productGroupService.getProductGroupbyId(id));
    }
    @PostMapping(value = "/productgroup", produces = MediaType.APPLICATION_JSON_VALUE)
    public ProductGroupDTO addProductGroup(@RequestBody @Validated ProductGroupDTO productGroupDTO) {
        return new ProductGroupDTO(productGroupService.createNewProductGroup(productGroupDTO));
    }

    @PutMapping(value = "/productgroup", produces = MediaType.APPLICATION_JSON_VALUE)
    public ProductGroupDTO editProductGroup(@RequestBody @Validated ProductGroupDTO productGroupDTO) {
        return new ProductGroupDTO(productGroupService.editProductGroup(productGroupDTO));
    }

    @DeleteMapping(value = "/productgroup/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public void deleteProductGroup(@PathVariable("id") Long id) {
    	productGroupService.deleteProductGroup(id);
    }

}
