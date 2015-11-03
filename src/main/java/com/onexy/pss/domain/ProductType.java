package com.onexy.pss.domain;

import org.apache.struts2.json.annotations.JSON;

/**
 * CREATE TABLE `producttype` (
 *   `id` bigint(20) NOT NULL AUTO_INCREMENT,
 *   `name` varchar(255) DEFAULT NULL,
 *   `descs` varchar(255) DEFAULT NULL,
 *   `parent_id` bigint(20) DEFAULT NULL,
 *   PRIMARY KEY (`id`),
 *   KEY `FKA8168A931A015E4` (`parent_id`),
 *   CONSTRAINT `FKA8168A931A015E4` FOREIGN KEY (`parent_id`) REFERENCES `producttype` (`id`)
 * )
 * 
 * @author Lenovo
 *
 */
public class ProductType {
	private Long id;
	private String name;
	private String descs;
	private ProductType parent;
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
	@JSON(serialize=false)
	public String getDescs() {
		return descs;
	}
	public void setDescs(String descs) {
		this.descs = descs;
	}
	@JSON(serialize=false)
	public ProductType getParent() {
		return parent;
	}
	public void setParent(ProductType parent) {
		this.parent = parent;
	}
	@Override
	public String toString() {
		return "ProductType [id=" + id + ", name=" + name + ", descs=" + descs
				+ "]";
	}
}
