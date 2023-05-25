package com.ssafy.kiwi.tour.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.kiwi.tour.model.TourAttractionDto;
import com.ssafy.kiwi.tour.model.TourGugunDto;
import com.ssafy.kiwi.tour.model.TourSidoDto;
import com.ssafy.kiwi.tour.service.TourService;

@RestController
@RequestMapping("/tour")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class TourController {

	private final Logger logger = LoggerFactory.getLogger(TourController.class);

	private TourService tourService;

	public TourController(TourService tourService) {
		super();
		this.tourService = tourService;
	}

	@GetMapping("/sidoList")
	public ResponseEntity<?> sidoList() {
		logger.debug("sidoList call");
		try {
			List<TourSidoDto> list = tourService.getSidoList();
			if (list != null && !list.isEmpty()) {
				return new ResponseEntity<List<TourSidoDto>>(list, HttpStatus.OK);
			} else {
				return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
			}
		} catch (Exception e) {
			return exceptionHandling(e);
		}
	}
	
	@GetMapping("/sigunList/{areacode}")
	public ResponseEntity<?> sigunList(@PathVariable("areacode") String areaCode) {
		logger.debug("sigunList call");
		try {
			List<TourGugunDto> list = tourService.getGugunList(areaCode);
			if (list != null && !list.isEmpty()) {
				return new ResponseEntity<List<TourGugunDto>>(list, HttpStatus.OK);
			} else {
				return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
			}
		} catch (Exception e) {
			return exceptionHandling(e);
		}
	}
	
	@GetMapping("/tourList/{areacode}/{siguguncode}/{contenttypeid}")
	public ResponseEntity<?> tourList(@PathVariable("areacode") String areaCode, @PathVariable("siguguncode") String gugunCode, @PathVariable("contenttypeid") String contentTypeId) {
		logger.debug("tourList call");
		try {
			List<TourAttractionDto> list = tourService.getTourList(areaCode, gugunCode, contentTypeId);
			if (list != null && !list.isEmpty()) {
				return new ResponseEntity<List<TourAttractionDto>>(list, HttpStatus.OK);
			} else {
				return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
			}
		} catch (Exception e) {
			return exceptionHandling(e);
		}
	}

	private ResponseEntity<String> exceptionHandling(Exception e) {
		e.printStackTrace();
		return new ResponseEntity<String>("Error : " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
