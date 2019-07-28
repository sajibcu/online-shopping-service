package com.red.code.onlineshopping.service.dto;



import com.red.code.onlineshopping.database.entity.model.Product;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class ProductDTO {
	
	private Long id;
 	
    private String name;
 	
 	private long productGroupId;

 	private long productCategoryId;

	 private double orginalPrice;

	 private int discountType;

	 private double discount;

	 private double buyPrice;
 	 
	 private double selPrice;
 	 
	 private int quantity;
 	 
	 private String caption;
 	 
	 private String imageOriginalName;
 	 
	 private String imageName;
 	 
	 private String searchKey;

	 private boolean activated;
	 
	 public ProductDTO(Product product)
	 {
		 this(	 product.getId(),
				 product.getName(),
				 product.getProductGroup().getId(),
				 product.getProductCategory().getId(),
				 product.getOrginalPrice(),
				 product.getDiscountType(),
				 product.getDiscount(),
				 product.getBuyPrice(),
				 product.getSelPrice(),
				 product.getQuantity(),
				 product.getCaption(),
				 product.getImageOriginalName(),
				 product.getImageName(),
				 product.getSearchKey(),
				 product.isActivated()
			);
	 }

}
