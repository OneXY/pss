package com.onexy.pss.domain;

/**
 * CREATE TABLE `supplier` (
 *   `id` bigint(20) NOT NULL AUTO_INCREMENT,
 *   `name` varchar(255) DEFAULT NULL,
 *   PRIMARY KEY (`id`)
 * )
 * 
 * @author Lenovo
 *
 */
public class Supplier {
	private Long id;
	private String name;
	public Supplier() {
	}
	public Supplier(Long id) {
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
	@Override
	public String toString() {
		return "Supplier [id=" + id + ", name=" + name + "]";
	}
}
