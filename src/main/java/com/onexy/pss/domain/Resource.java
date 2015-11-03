package com.onexy.pss.domain;

/**
 * CREATE TABLE `resource` (
 *   `id` bigint(20) NOT NULL AUTO_INCREMENT,
 *   `name` varchar(255) DEFAULT NULL,
 *   `method` varchar(255) DEFAULT NULL,
 *   `descs` varchar(255) DEFAULT NULL,
 *   PRIMARY KEY (`id`)
 * )
 * 
 * @author Lenovo
 *
 */
public class Resource {
	private Long id;
	private String name;
	private String method;
	private String descs;
	
	public Resource() {
	}
	
	public Resource(Long id) {
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
	public String getMethod() {
		return method;
	}
	public void setMethod(String method) {
		this.method = method;
	}
	public String getDescs() {
		return descs;
	}
	public void setDescs(String descs) {
		this.descs = descs;
	}
	@Override
	public String toString() {
		return "Resource [id=" + id + ", name=" + name + ", method=" + method
				+ ", descs=" + descs + "]";
	}
}
