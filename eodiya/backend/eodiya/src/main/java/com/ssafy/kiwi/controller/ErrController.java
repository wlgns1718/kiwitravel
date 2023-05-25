package com.ssafy.kiwi.controller;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ErrController implements ErrorController {

	private final Logger logger = LoggerFactory.getLogger(ErrController.class);

	@GetMapping("/error")
	public String handleError(Exception ex, HttpServletRequest request, Model model) {
		logger.error("Exception 발생 : {}", ex.getMessage());

		// error로 들어온 에러의 status를 불러온다 (ex:404,500,403...)
		Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);

		if (status != null) {
			int statusCode = Integer.valueOf(status.toString());

			if (statusCode == HttpStatus.NOT_FOUND.value()) {
				model.addAttribute("errormsg", "페이지를 찾을 수 없어요.");
				return "error/error";
			} else {
				model.addAttribute("errormsg", "처리 중 알 수 없는 에러가 발생하였어요.");
				return "error/error";
			}
		}
		model.addAttribute("msg", "처리 중 알 수 없는 에러가 발생하였어요.");
		return "error/error";
	}
}