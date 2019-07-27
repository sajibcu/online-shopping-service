package com.red.code.onlineshopping.service.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.red.code.onlineshopping.database.entity.model.ProductCategory;
import com.red.code.onlineshopping.database.entity.model.ProductGroup;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ProductCategoryDTO {
	private Long id;

    @Size(min = 5, max = 64)
    private String name;
    
    @NotNull
    private Long productGroup;
    
    private boolean activated;
    
    public ProductCategoryDTO(ProductCategory productCategory) {
    	this(productCategory.getId(),productCategory.getName(),productCategory.getProductGroup().getId(),productCategory.isActivated());
    	
    }

}
