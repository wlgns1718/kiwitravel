package com.ssafy.kiwi.tour.model;

import org.springframework.stereotype.Component;

@Component
public class TourAttractionDto {

	String title;
	String addr1;
	String addr2;
	String firstImage;
	String firstImage2;
	String latitude;
	String longitude;
	String overview;
	int contentTypeId;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAddr1() {
		return addr1;
	}

	public void setAddr1(String addr1) {
		this.addr1 = addr1;
	}

	public String getAddr2() {
		return addr2;
	}

	public void setAddr2(String addr2) {
		this.addr2 = addr2;
	}

	public String getFirstImage() {
		return firstImage;
	}

	public void setFirstImage(String firstImage) {
		this.firstImage = firstImage;
	}

	public String getFirstImage2() {
		return firstImage2;
	}

	public void setFirstImage2(String firstImage2) {
		this.firstImage2 = firstImage2;
	}

	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	public String getLongitude() {
		return longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	public String getOverview() {
		return overview;
	}

	public void setOverview(String overview) {
		this.overview = overview;
	}

	public int getContentTypeId() {
		return contentTypeId;
	}

	public void setContentTypeId(int contentTypeId) {
		this.contentTypeId = contentTypeId;
	}

	@Override
	public String toString() {
		return "TourAttractionDto [title=" + title + ", addr1=" + addr1 + ", addr2=" + addr2 + ", firstImage="
				+ firstImage + ", firstImage2=" + firstImage2 + ", latitude=" + latitude + ", longitude=" + longitude
				+ ", overview=" + overview + "]";
	}

}
