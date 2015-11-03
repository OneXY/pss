package com.onexy.pss.domain;

import java.math.BigDecimal;

/**
 * CREATE TABLE `product` (
 *   `id` bigint(20) NOT NULL AUTO_INCREMENT,
 *   `name` varchar(255) DEFAULT NULL,
 *   `color` varchar(255) DEFAULT NULL,
 *   `pic` varchar(255) DEFAULT NULL,
 *   `smallPic` varchar(255) DEFAULT NULL,
 *   `costPrice` decimal(19,2) DEFAULT NULL,
 *   `salePrice` decimal(19,2) DEFAULT NULL,
 *   `types_id` bigint(20) NOT NULL,
 *   `unit_id` bigint(20) NOT NULL,
 *   `brand_id` bigint(20) NOT NULL,
 *   PRIMARY KEY (`id`),
 *   KEY `FK50C664CF8DF77FB5` (`types_id`),
 *   KEY `FK50C664CF422B987E` (`brand_id`),
 *   KEY `FK50C664CF329AED61` (`unit_id`),
 *   CONSTRAINT `FK50C664CF329AED61` FOREIGN KEY (`unit_id`) REFERENCES `systemdictionarydetail` (`id`),
 *   CONSTRAINT `FK50C664CF422B987E` FOREIGN KEY (`brand_id`) REFERENCES `systemdictionarydetail` (`id`),
 *   CONSTRAINT `FK50C664CF8DF77FB5` FOREIGN KEY (`types_id`) REFERENCES `producttype` (`id`)
 * )
 * 
 * @author Lenovo
 */
public class Product {
	private Long id;
	private String name;
	private String color;
	private String pic;
	private String smallPic;
	private BigDecimal costPrice;
	private BigDecimal salePrice;
	private SystemDictionaryDetail unit;
	private SystemDictionaryDetail brand;
	private ProductType types;
	public Product() {
	}
	
	public Product(Long id) {
		this.id = id;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public String getPic() {
		return pic;
	}

	public void setPic(String pic) {
		this.pic = pic;
	}

	public String getSmallPic() {
		return smallPic;
	}

	public void setSmallPic(String smallPic) {
		this.smallPic = smallPic;
	}

	public BigDecimal getCostPrice() {
		return costPrice;
	}

	public void setCostPrice(BigDecimal costPrice) {
		this.costPrice = costPrice;
	}

	public BigDecimal getSalePrice() {
		return salePrice;
	}

	public void setSalePrice(BigDecimal salePrice) {
		this.salePrice = salePrice;
	}

	public SystemDictionaryDetail getUnit() {
		return unit;
	}

	public void setUnit(SystemDictionaryDetail unit) {
		this.unit = unit;
	}

	public SystemDictionaryDetail getBrand() {
		return brand;
	}

	public void setBrand(SystemDictionaryDetail brand) {
		this.brand = brand;
	}

	public ProductType getTypes() {
		return types;
	}

	public void setTypes(ProductType types) {
		this.types = types;
	}

	@Override
	public String toString() {
		return "Product [id=" + id + ", name=" + name + ", color=" + color
				+ ", pic=" + pic + ", smallPic=" + smallPic + ", costPrice="
				+ costPrice + ", salePrice=" + salePrice + "]";
	}
}
