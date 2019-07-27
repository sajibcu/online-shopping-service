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
@Table(name = "product_category")
@NoArgsConstructor
@Data
public class ProductCategory extends AbstractAuditingEntity{
	@Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "product_category_id_seq")
    @SequenceGenerator(name = "product_category_id_seq", sequenceName = "product_category_id_seq", allocationSize = 1)
    private Long id;
 	
 	@Size(max = 64)
    @Column(name = "name", length = 64)
    private String name;
 	
 	@ManyToOne
    @JoinColumn(name = "group_id")
 	private ProductGroup productGroup;
 
	 @Column(name = "activated")
	 private boolean activated;

}
