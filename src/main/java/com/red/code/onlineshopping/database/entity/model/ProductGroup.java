package com.red.code.onlineshopping.database.entity.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "product_group")
@NoArgsConstructor
@Data
public class ProductGroup extends AbstractAuditingEntity{
	
	 	@Id
	    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "product_group_seq")
	    @SequenceGenerator(name = "product_group_seq", sequenceName = "product_group_seq", allocationSize = 1)
	    private Long id;
	 	
	 	@Size(max = 64)
	    @Column(name = "name", length = 64)
	    private String name;
	 
		 @Column(name = "activated")
		 private boolean activated;

}
