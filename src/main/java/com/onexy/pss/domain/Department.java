package com.onexy.pss.domain;

/**
 * CREATE TABLE `department` (
 *   `id` bigint(20) NOT NULL AUTO_INCREMENT,
 *   `name` varchar(255) DEFAULT NULL,
 *   PRIMARY KEY (`id`)
 * )
 * 
 * @author Lenovo
 *
 */
public class Department {
	 private Long id;
	 private String name;
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
		return "Department [id=" + id + ", name=" + name + "]";
	}
}
