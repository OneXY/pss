package com.onexy.pss.domain;

/**
 * CREATE TABLE `systemdictionarydetail` (
 *   `id` bigint(20) NOT NULL AUTO_INCREMENT,
 *   `name` varchar(255) DEFAULT NULL,
 *   `types_id` bigint(20) NOT NULL,
 *   PRIMARY KEY (`id`),
 *   KEY `FK81BC50F6718C93B5` (`types_id`),
 *   CONSTRAINT `FK81BC50F6718C93B5` FOREIGN KEY (`types_id`) REFERENCES `systemdictionarytype` (`id`)
 * )
 * 
 * @author Lenovo
 *
 */
public class SystemDictionaryDetail {
	private Long id;
	private String name;
	private SystemDictionaryType types;
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
	public SystemDictionaryType getTypes() {
		return types;
	}
	public void setTypes(SystemDictionaryType types) {
		this.types = types;
	}
	@Override
	public String toString() {
		return "SystemDictionaryDetail [id=" + id + ", name=" + name + "]";
	}
}
