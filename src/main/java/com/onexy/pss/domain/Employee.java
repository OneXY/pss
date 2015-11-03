package com.onexy.pss.domain;

import java.util.HashSet;
import java.util.Set;

/**
 * CREATE TABLE `employee` (
 *   `id` bigint(20) NOT NULL AUTO_INCREMENT,
 *   `name` varchar(255) DEFAULT NULL,
 *   `password` varchar(255) DEFAULT NULL,
 *   `email` varchar(255) DEFAULT NULL,
 *   `age` int(11) DEFAULT NULL,
 *   `department_id` bigint(20) DEFAULT NULL,
 *   PRIMARY KEY (`id`),
 *   KEY `FK4AFD4ACE851EFECF` (`department_id`),
 *   CONSTRAINT `FK4AFD4ACE851EFECF` FOREIGN KEY (`department_id`) REFERENCES `department` (`id`)
 * )
 * 
 * @author Lenovo
 *
 */
public class Employee {
	private Long id;
	private String name;
	private String password;
	private String email;
	private Integer age;
	private Department department;
	private Set<Role> roles = new HashSet<Role>();
	public Employee() {
	}
	public Employee(Long id) {
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
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Integer getAge() {
		return age;
	}
	public void setAge(Integer age) {
		this.age = age;
	}
	public Department getDepartment() {
		return department;
	}
	public void setDepartment(Department department) {
		this.department = department;
	}
	public Set<Role> getRoles() {
		return roles;
	}
	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}
	@Override
	public String toString() {
		return "Employee [id=" + id + ", name=" + name + ", password="
				+ password + ", email=" + email + ", age=" + age + "]";
	}
}
