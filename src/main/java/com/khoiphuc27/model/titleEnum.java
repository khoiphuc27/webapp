package com.khoiphuc27.model;

public enum titleEnum {
	MR("Mr"),
	MRS("Mrs");
	
	private String title;
	
	titleEnum(String title) {
		this.title = title;
	}
	
	public String title() {
		return title;
	}
}
