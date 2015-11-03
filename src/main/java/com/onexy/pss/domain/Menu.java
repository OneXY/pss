package com.onexy.pss.domain;

import org.apache.struts2.json.annotations.JSON;

/**
 * CREATE TABLE `menu` (
 *   `id` bigint(20) NOT NULL AUTO_INCREMENT,
 *   `name` varchar(255) DEFAULT NULL,
 *   `url` varchar(255) DEFAULT NULL,
 *   `icon` varchar(255) DEFAULT NULL,
 *   `parent_id` bigint(20) DEFAULT NULL,
 *   PRIMARY KEY (`id`),
 *   KEY `FK24897F76799044` (`parent_id`),
 *   CONSTRAINT `FK24897F76799044` FOREIGN KEY (`parent_id`) REFERENCES `menu` (`id`)
 * )
 * 
 * @author Lenovo
 *
 */
public class Menu {
	private Long id;
	private String name;
	private String url;
	private String icon;
	private Menu parent;
	
	public boolean getIsParent() {
		return parent == null;
	}
	public String getTarget() {
		return getIsParent() ? null : "main";
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
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getIcon() {
		return icon;
	}
	public void setIcon(String icon) {
		this.icon = icon;
	}
	@JSON(serialize=false)
	public Menu getParent() {
		return parent;
	}
	public void setParent(Menu parent) {
		this.parent = parent;
	}
	@Override
	public String toString() {
		return "Menu [id=" + id + ", name=" + name + ", url=" + url + ", icon="
				+ icon + "]";
	}
}
