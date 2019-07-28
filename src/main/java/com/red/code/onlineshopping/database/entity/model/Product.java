package com.red.code.onlineshopping.database.entity.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "product")
@NoArgsConstructor
@Data
public class Product extends AbstractAuditingEntity{
	@Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "product_id_seq")
    @SequenceGenerator(name = "product_id_seq", sequenceName = "product_id_seq", allocationSize = 1)
    private Long id;
 	
 	@Size(max = 100)
    @Column(name = "name", length = 100)
    private String name;
 	
 	@ManyToOne
    @JoinColumn(name = "group_id")
 	private ProductGroup productGroup;
 	
 	@ManyToOne
    @JoinColumn(name = "category_id")
 	private ProductCategory productCategory;
 	
 	 @Column(name = "orginal_price")
	 private Double orginalPrice;
 	 
 	 @Column(name = "discount_type")
	 private int discountType;
 	 
 	 @Column(name = "discount")
	 private Double discount;
 	 
 	 @Column(name = "buy_price")
	 private Double buyPrice;
 	 
 	 @Column(name = "sel_price")
	 private Double selPrice;
 	 
 	 @Column(name = "quantity")
	 private int quantity;
 	 
 	 @Column(name = "caption")
	 private String caption;
 	 
 	@Column(name = "image_original_name")
	 private String imageOriginalName;
 	 
 	 @Column(name = "image_name")
	 private String imageName;
 	 
 	 @Column(name = "search_key")
	 private String searchKey;
 	 
	 @Column(name = "activated")
	 private boolean activated;

}
