package com.onexy.pss.domain;

import java.math.BigDecimal;

/**
 * CREATE TABLE `depot` (
 *   `id` bigint(20) NOT NULL AUTO_INCREMENT,
 *   `name` varchar(255) DEFAULT NULL,
 *   `maxCapacity` decimal(19,2) DEFAULT NULL,
 *   `capacity` decimal(19,2) DEFAULT NULL,
 *   `totlaAmount` decimal(19,2) DEFAULT NULL,
 *   PRIMARY KEY (`id`)
 * )
 * @author Lenovo
 *
 */
public class Depot {
	private Long id;
	private String name;
	private BigDecimal maxCapacity;
	private BigDecimal capacity;
	private BigDecimal totlaAmount;
	public Depot() {
	}
	public Depot(Long id) {
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
	public BigDecimal getMaxCapacity() {
		return maxCapacity;
	}
	public void setMaxCapacity(BigDecimal maxCapacity) {
		this.maxCapacity = maxCapacity;
	}
	public BigDecimal getCapacity() {
		return capacity;
	}
	public void setCapacity(BigDecimal capacity) {
		this.capacity = capacity;
	}
	public BigDecimal getTotlaAmount() {
		return totlaAmount;
	}
	public void setTotlaAmount(BigDecimal totlaAmount) {
		this.totlaAmount = totlaAmount;
	}
	@Override
	public String toString() {
		return "Depot [id=" + id + ", name=" + name + ", maxCapacity="
				+ maxCapacity + ", capacity=" + capacity + ", totlaAmount="
				+ totlaAmount + "]";
	}
}
