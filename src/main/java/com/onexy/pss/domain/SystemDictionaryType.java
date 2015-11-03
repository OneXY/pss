package com.onexy.pss.domain;

/**
 * CREATE TABLE `systemdictionarytype` (
 *   `id` bigint(20) NOT NULL AUTO_INCREMENT,
 *   `sn` varchar(255) DEFAULT NULL,
 *   `name` varchar(255) DEFAULT NULL,
 *   PRIMARY KEY (`id`),
 *   UNIQUE KEY `sn` (`sn`)
 * )
 * 
 * @author Lenovo
 *
 */
public class SystemDictionaryType {
	public static final String PRODUCT_BRAND = "productBrand";
	public static final String PRODUCT_UNIT = "productUnit";
	private Long id;
	private String sn; 
	private String name;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getSn() {
		return sn;
	}
	public void setSn(String sn) {
		this.sn = sn;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Override
	public String toString() {
		return "SystemDictionaryType [id=" + id + ", sn=" + sn + ", name="
				+ name + "]";
	}
}
