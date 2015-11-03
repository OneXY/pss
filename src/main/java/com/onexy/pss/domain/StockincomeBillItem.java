package com.onexy.pss.domain;

import java.math.BigDecimal;

/**
 * CREATE TABLE `stockincomebillitem` (
 *   `id` bigint(20) NOT NULL AUTO_INCREMENT,
 *   `price` decimal(19,2) DEFAULT NULL,
 *   `num` decimal(19,2) DEFAULT NULL,
 *   `amount` decimal(19,2) DEFAULT NULL,
 *   `descs` varchar(255) DEFAULT NULL,
 *   `productt_id` bigint(20) NOT NULL,
 *   `bill_id` bigint(20) NOT NULL,
 *   PRIMARY KEY (`id`),
 *   KEY `FKACA67119D6A81925` (`productt_id`),
 *   KEY `FKACA671192B5E3024` (`bill_id`),
 *   CONSTRAINT `FKACA671192B5E3024` FOREIGN KEY (`bill_id`) REFERENCES `stockincomebill` (`id`),
 *   CONSTRAINT `FKACA67119D6A81925` FOREIGN KEY (`productt_id`) REFERENCES `productt` (`id`)
 * )
 * 
 * @author Lenovo
 *
 */
public class StockincomeBillItem {
	private Long id;
	private BigDecimal price;
	private BigDecimal num;
	private BigDecimal amount;
	private String descs;
	private StockincomeBill bill;
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
	public StockincomeBill getBill() {
		return bill;
	}
	public void setBill(StockincomeBill bill) {
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
		return "StockincomeBillItem [id=" + id + ", price=" + price + ", num="
				+ num + ", amount=" + amount + ", descs=" + descs + "]";
	}
}
