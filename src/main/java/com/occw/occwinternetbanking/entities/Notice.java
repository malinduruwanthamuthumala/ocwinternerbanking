package com.occw.occwinternetbanking.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Notice {

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
	String  noticeSummary;
	String noticeDetails;
	
	public Notice(String noticeSummary, String noticeDetails) {
		super();
		this.noticeSummary = noticeSummary;
		this.noticeDetails = noticeDetails;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNoticeSummary() {
		return noticeSummary;
	}

	public void setNoticeSummary(String noticeSummary) {
		this.noticeSummary = noticeSummary;
	}

	public String getNoticeDetails() {
		return noticeDetails;
	}

	public void setNoticeDetails(String noticeDetails) {
		this.noticeDetails = noticeDetails;
	}
	
	
}
