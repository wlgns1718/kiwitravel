package com.ssafy.kiwi.tour.model;

import org.springframework.stereotype.Component;

@Component
public class TourSidoDto {

	String code;
	String name;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "TourSidoDto [code=" + code + ", name=" + name + "]";
	}

}
