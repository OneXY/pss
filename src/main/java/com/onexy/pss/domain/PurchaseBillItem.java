package com.onexy.pss.domain;

import java.math.BigDecimal;

/**
 * CREATE TABLE `purchasebillitem` (
 *   `id` bigint(20) NOT NULL AUTO_INCREMENT,
 *   `price` decimal(19,2) DEFAULT NULL,
 *   `num` decimal(19,2) DEFAULT NULL,
 *   `amount` decimal(19,2) DEFAULT NULL,
 *   `descs` varchar(255) DEFAULT NULL,
 *   `product_id` bigint(20) NOT NULL,
 *   `bill_id` bigint(20) NOT NULL,
 *   PRIMARY KEY (`id`),
 *   KEY `FK5FF8D3FBD6A81925` (`product_id`),
 *   KEY `FK5FF8D3FB60931610` (`bill_id`),
 *   CONSTRAINT `FK5FF8D3FB60931610` FOREIGN KEY (`bill_id`) REFERENCES `purchasebill` (`id`),
 *   CONSTRAINT `FK5FF8D3FBD6A81925` FOREIGN KEY (`product_id`) REFERENCES `product` (`id`)
 * )
 * 
 * @author Lenovo
 *
 */
public class PurchaseBillItem {
	private Long id;
	private BigDecimal price;
	private BigDecimal num;
	private BigDecimal amount;
	private String descs;
	private PurchaseBill bill;
	private Product product;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public BigDecimal getPrice() {
		return price;
	}
	public void setPrice(BigDecimal price) {
		this.price = price;
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
	public String getDescs() {
		return descs;
	}
	public void setDescs(String descs) {
		this.descs = descs;
	}
	public PurchaseBill getBill() {
		return bill;
	}
	public void setBill(PurchaseBill bill) {
		this.bill = bill;
	}
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	@Override
	public String toString() {
		return "PurchaseBillItem [id=" + id + ", price=" + price + ", num="
				+ num + ", amount=" + amount + ", descs=" + descs + "]";
	}
}
