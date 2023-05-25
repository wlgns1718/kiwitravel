package com.ssafy.kiwi.tour.service;

import java.util.List;

import com.ssafy.kiwi.tour.model.TourAttractionDto;
import com.ssafy.kiwi.tour.model.TourGugunDto;
import com.ssafy.kiwi.tour.model.TourSidoDto;

public interface TourService {

	List<TourSidoDto> getSidoList() throws Exception;

	List<TourGugunDto> getGugunList(String code) throws Exception;

	List<TourAttractionDto> getTourList(String areaCode, String gugunCode, String contentTypeId) throws Exception;

}
