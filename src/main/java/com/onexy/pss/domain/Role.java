package com.onexy.pss.domain;

import java.util.HashSet;
import java.util.Set;

/**
 * CREATE TABLE `role` (
 *   `id` bigint(20) NOT NULL AUTO_INCREMENT,
 *   `name` varchar(255) DEFAULT NULL,
 *   PRIMARY KEY (`id`)
 * )
 * 
 * @author Lenovo
 *
 */
public class Role {
	private Long id;
	private String name;
	private Set<Menu> menus = new HashSet<Menu>();
	private Set<Resource> resources = new HashSet<Resource>();
	private Set<Employee> employees = new HashSet<Employee>();
	public Role() {
	}
	public Role(Long id) {
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
	public Set<Menu> getMenus() {
		return menus;
	}
	public void setMenus(Set<Menu> menus) {
		this.menus = menus;
	}
	public Set<Resource> getResources() {
		return resources;
	}
	public void setResources(Set<Resource> resources) {
		this.resources = resources;
	}
	public Set<Employee> getEmployees() {
		return employees;
	}
	public void setEmployees(Set<Employee> employees) {
		this.employees = employees;
	}
	@Override
	public String toString() {
		return "Role [id=" + id + ", name=" + name + "]";
	}
}
