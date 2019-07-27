package com.red.code.onlineshopping.service.dto;

import javax.validation.constraints.Size;

import com.red.code.onlineshopping.database.entity.model.ProductGroup;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ProductGroupDTO {
	private Long id;

    @Size(min = 5, max = 64)
    private String name;
    private boolean activated;
    
    public ProductGroupDTO(ProductGroup productGroup) {
    	this(productGroup.getId(),productGroup.getName(),productGroup.isActivated());
    	
    }

}
