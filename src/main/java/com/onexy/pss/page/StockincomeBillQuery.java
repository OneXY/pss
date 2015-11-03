package com.onexy.pss.page;

import java.util.Date;

import org.apache.commons.lang3.time.DateUtils;

import com.onexy.pss.domain.StockincomeBill;

public class StockincomeBillQuery extends BaseQuery {
	private Date beginDate;
	
	private Date endDate;
	
	private Integer status;
	
	public StockincomeBillQuery() {
		super(StockincomeBill.class);
	}

	@Override
	protected void addWhere() {
		if (beginDate!=null) {
			addWhere("o.vdate>=?", beginDate);
		}
		if (endDate!=null) {
			Date date = DateUtils.addDays(endDate, 1);
			addWhere("o.vdate<?", date);
		}
		if (status!=null&&status!=-2) {
			addWhere("o.status=?", status);
		}
	}

	public Date getBeginDate() {
		return beginDate;
	}

	public void setBeginDate(Date beginDate) {
		this.beginDate = beginDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}
		
}
