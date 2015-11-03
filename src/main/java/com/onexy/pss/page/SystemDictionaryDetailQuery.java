package com.onexy.pss.page;

import org.apache.commons.lang3.StringUtils;

import com.onexy.pss.domain.SystemDictionaryDetail;

public class SystemDictionaryDetailQuery extends BaseQuery {
	private String name;

	private Long typesId;
	
	public SystemDictionaryDetailQuery() {
		super(SystemDictionaryDetail.class);
	}

	@Override
	protected void addWhere() {
		if (StringUtils.isNotBlank(name)) {
			addWhere("o.name LIKE ?", "%" + name + "%");
		}
//		if (StringUtils.isNotBlank(email)) {
//			addWhere("o.email LIKE ?", "%" + email + "%");
//		}
		if (typesId != null && typesId != -1L) {
			addWhere("o.types.id = ?", typesId);
		}
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getTypesId() {
		return typesId;
	}

	public void setTypesId(Long typesId) {
		this.typesId = typesId;
	}

	
}
