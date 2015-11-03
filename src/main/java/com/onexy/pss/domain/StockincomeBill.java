package com.onexy.pss.domain;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * CREATE TABLE `stockincomebill` (
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
 *   `keeper_id` bigint(20) NOT NULL,
 *   `depot_id` bigint(20) NOT NULL,
 *   PRIMARY KEY (`id`),
 *   KEY `FK54AC64E69F064DC5` (`depot_id`),
 *   KEY `FK54AC64E63FF7A83F` (`auditor_id`),
 *   KEY `FK54AC64E6A902BD48` (`inputUser_id`),
 *   KEY `FK54AC64E6725F67CB` (`keeper_id`),
 *   KEY `FK54AC64E612C245CF` (`supplier_id`),
 *   CONSTRAINT `FK54AC64E612C245CF` FOREIGN KEY (`supplier_id`) REFERENCES `supplier` (`id`),
 *   CONSTRAINT `FK54AC64E63FF7A83F` FOREIGN KEY (`auditor_id`) REFERENCES `employee` (`id`),
 *   CONSTRAINT `FK54AC64E6725F67CB` FOREIGN KEY (`keeper_id`) REFERENCES `employee` (`id`),
 *   CONSTRAINT `FK54AC64E69F064DC5` FOREIGN KEY (`depot_id`) REFERENCES `depot` (`id`),
 *   CONSTRAINT `FK54AC64E6A902BD48` FOREIGN KEY (`inputUser_id`) REFERENCES `employee` (`id`)
 * )
 * 
 * @author Lenovo
 *
 */
public class StockincomeBill {
	private Long id;
	private Date vdate;
	private BigDecimal totalAmount;
	private BigDecimal totalNum;
	private Date inputTime = new Date();
	private Date auditorTime;
	private Integer status = 0;
	private Supplier supplier;
	private Employee auditor;
	private Employee keeper;
	private Depot depot;
	private Employee inputUser;
	private List<StockincomeBillItem> items = new ArrayList<StockincomeBillItem>();
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
	public Employee getKeeper() {
		return keeper;
	}
	public void setKeeper(Employee keeper) {
		this.keeper = keeper;
	}
	public Depot getDepot() {
		return depot;
	}
	public void setDepot(Depot depot) {
		this.depot = depot;
	}
	public Employee getInputUser() {
		return inputUser;
	}
	public void setInputUser(Employee inputUser) {
		this.inputUser = inputUser;
	}
	public List<StockincomeBillItem> getItems() {
		return items;
	}
	public void setItems(List<StockincomeBillItem> items) {
		this.items = items;
	}
	@Override
	public String toString() {
		return "StockincomeBill [id=" + id + ", vdate=" + vdate
				+ ", totalAmount=" + totalAmount + ", totalNum=" + totalNum
				+ ", inputTime=" + inputTime + ", auditorTime=" + auditorTime
				+ ", status=" + status + "]";
	}
}
