package com.onexy.pss.domain;

import java.math.BigDecimal;
import java.util.Date;

/**
 * CREATE TABLE `productstock` (
 *   `id` bigint(20) NOT NULL AUTO_INCREMENT,
 *   `num` decimal(19,2) DEFAULT NULL,
 *   `amount` decimal(19,2) DEFAULT NULL,
 *   `price` decimal(19,2) DEFAULT NULL,
 *   `incomeDate` datetime DEFAULT NULL,
 *   `warning` bit(1) DEFAULT NULL,
 *   `topNum` decimal(19,2) DEFAULT NULL,
 *   `bottomNum` decimal(19,2) DEFAULT NULL,
 *   `product_id` bigint(20) NOT NULL,
 *   `depot_id` bigint(20) NOT NULL,
 *   PRIMARY KEY (`id`),
 *   KEY `FK459B4B879F064DC5` (`depot_id`),
 *   KEY `FK459B4B87D6A81925` (`product_id`),
 *   CONSTRAINT `FK459B4B879F064DC5` FOREIGN KEY (`depot_id`) REFERENCES `depot` (`id`),
 *   CONSTRAINT `FK459B4B87D6A81925` FOREIGN KEY (`product_id`) REFERENCES `product` (`id`)
 * )
 * @author Lenovo
 *
 */
public class ProductStock {
	private Long id;
	private BigDecimal num;
	private BigDecimal amount;
	private BigDecimal price;
	private Date incomeDate;
	private Boolean warning = false;
	private BigDecimal topNum;
	private BigDecimal bottomNum;
	private Product product;
	private Depot depot;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public BigDecimal getNum() {
		return num;
	}
	public void setNum(BigDecimal num) {
		this.num = num;
	}
	public BigDecimal getAmount() {
		return amount;
	}
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}
	public BigDecimal getPrice() {
		return price;
	}
	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	public Date getIncomeDate() {
		return incomeDate;
	}
	public void setIncomeDate(Date incomeDate) {
		this.incomeDate = incomeDate;
	}
	public Boolean getWarning() {
		return warning;
	}
	public void setWarning(Boolean warning) {
		this.warning = warning;
	}
	public BigDecimal getTopNum() {
		return topNum;
	}
	public void setTopNum(BigDecimal topNum) {
		this.topNum = topNum;
	}
	public BigDecimal getBottomNum() {
		return bottomNum;
	}
	public void setBottomNum(BigDecimal bottomNum) {
		this.bottomNum = bottomNum;
	}
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	public Depot getDepot() {
		return depot;
	}
	public void setDepot(Depot depot) {
		this.depot = depot;
	}
	@Override
	public String toString() {
		return "ProductStock [id=" + id + ", num=" + num + ", amount=" + amount
				+ ", price=" + price + ", incomeDat=" + incomeDate
				+ ", warning=" + warning + ", topNum=" + topNum
				+ ", bottomNum=" + bottomNum + "]";
	}
}
