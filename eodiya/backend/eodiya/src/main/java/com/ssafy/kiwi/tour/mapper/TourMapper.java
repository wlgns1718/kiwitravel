package com.ssafy.kiwi.tour.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.kiwi.tour.model.TourAttractionDto;
import com.ssafy.kiwi.tour.model.TourGugunDto;
import com.ssafy.kiwi.tour.model.TourSidoDto;

@Mapper
public interface TourMapper {

	List<TourSidoDto> getSidoList() throws Exception;

	List<TourGugunDto> getGugunList(String areaCode) throws Exception;

	List<TourAttractionDto> getTourList(String areaCode, String gugunCode, String contentTypeId) throws Exception;

}
