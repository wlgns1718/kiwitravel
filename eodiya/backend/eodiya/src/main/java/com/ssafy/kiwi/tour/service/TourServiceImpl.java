package com.ssafy.kiwi.tour.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ssafy.kiwi.tour.mapper.TourMapper;
import com.ssafy.kiwi.tour.model.TourAttractionDto;
import com.ssafy.kiwi.tour.model.TourGugunDto;
import com.ssafy.kiwi.tour.model.TourSidoDto;

@Service
public class TourServiceImpl implements TourService {

	private TourMapper tourMapper;
	
	public TourServiceImpl(TourMapper tourMapper) {
		super();
		this.tourMapper = tourMapper;
	}

	@Override
	public List<TourSidoDto> getSidoList() throws Exception {
		return tourMapper.getSidoList();
	}

	@Override
	public List<TourGugunDto> getGugunList(String areaCode) throws Exception {
		return tourMapper.getGugunList(areaCode);
	}

	@Override
	public List<TourAttractionDto> getTourList(String areaCode, String gugunCode, String contentTypeId)
			throws Exception {
		return tourMapper.getTourList(areaCode, gugunCode, contentTypeId);
	}

}
