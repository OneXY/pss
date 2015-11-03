package com.onexy.pss.domain;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * CREATE TABLE `purchasebill` (
 *   `id` bigint(20) NOT NULL AUTO_INCREMENT,
 *   `vdate` datetime DEFAULT NULL,
 *   `totalAmount` decimal(19,2) DEFAULT NULL,
 *   `totalNum` decimal(19,2) DEFAULT NULL,
 *   `inputTime` datetime DEFAULT NULL,
 *   `auditorTime` datetime DEFAULT NULL,
 *   `status` int(11) DEFAULT NULL,
 *   `supplier_id` bigint(20) NOT NULL,
 *   `auditor_id` bigint(20) DEFAULT NULL,
 *   `inputUser_id` bigint(20) NOT NULL,
 *   `buyer_id` bigint(20) NOT NULL,
 *   PRIMARY KEY (`id`),
 *   KEY `FK9BD788C89FE0CD6A` (`buyer_id`),
 *   KEY `FK9BD788C83FF7A83F` (`auditor_id`),
 *   KEY `FK9BD788C8A902BD48` (`inputUser_id`),
 *   KEY `FK9BD788C812C245CF` (`supplier_id`),
 *   CONSTRAINT `FK9BD788C812C245CF` FOREIGN KEY (`supplier_id`) REFERENCES `supplier` (`id`),
 *   CONSTRAINT `FK9BD788C83FF7A83F` FOREIGN KEY (`auditor_id`) REFERENCES `employee` (`id`),
 *   CONSTRAINT `FK9BD788C89FE0CD6A` FOREIGN KEY (`buyer_id`) REFERENCES `employee` (`id`),
 *   CONSTRAINT `FK9BD788C8A902BD48` FOREIGN KEY (`inputUser_id`) REFERENCES `employee` (`id`)
 * ) 
 * @author Lenovo
 *
 */
public class PurchaseBill {
	private Long id;
	private Date vdate;
	private BigDecimal totalAmount;
	private BigDecimal totalNum;
	private Date inputTime = new Date();
	private Date auditorTime;
	private Integer status = 0;
	private Supplier supplier;
	private Employee auditor;
	private Employee buyer;
	private Employee inputUser;
	private List<PurchaseBillItem> items = new ArrayList<PurchaseBillItem>();
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Date getVdate() {
		return vdate;
	}
	public void setVdate(Date vdate) {
		this.vdate = vdate;
	}
	public BigDecimal getTotalAmount() {
		return totalAmount;
	}
	public void setTotalAmount(BigDecimal totalAmount) {
		this.totalAmount = totalAmount;
	}
	public BigDecimal getTotalNum() {
		return totalNum;
	}
	public void setTotalNum(BigDecimal totalNum) {
		this.totalNum = totalNum;
	}
	public Date getInputTime() {
		return inputTime;
	}
	public void setInputTime(Date inputTime) {
		this.inputTime = inputTime;
	}
	public Date getAuditorTime() {
		return auditorTime;
	}
	public void setAuditorTime(Date auditorTime) {
		this.auditorTime = auditorTime;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public Supplier getSupplier() {
		return supplier;
	}
	public void setSupplier(Supplier supplier) {
		this.supplier = supplier;
	}
	public Employee getAuditor() {
		return auditor;
	}
	public void setAuditor(Employee auditor) {
		this.auditor = auditor;
	}
	public Employee getBuyer() {
		return buyer;
	}
	public void setBuyer(Employee buyer) {
		this.buyer = buyer;
	}
	public Employee getInputUser() {
		return inputUser;
	}
	public void setInputUser(Employee inputUser) {
		this.inputUser = inputUser;
	}
	public List<PurchaseBillItem> getItems() {
		return items;
	}
	public void setItems(List<PurchaseBillItem> items) {
		this.items = items;
	}
	@Override
	public String toString() {
		return "PurchaseBill [id=" + id + ", vdate=" + vdate + ", totalAmount="
				+ totalAmount + ", totalNum=" + totalNum + ", inputTime="
				+ inputTime + ", auditorTime=" + auditorTime + ", status="
				+ status + "]";
	}
}
