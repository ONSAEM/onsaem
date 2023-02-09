package com.onsaem.web.common.web;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@CrossOrigin(origins = "*")
public class CustomErrorController implements ErrorController {

	@RequestMapping(value = "/error", method = RequestMethod.GET)
	public String errorPage(HttpServletRequest request, Model model) {
		Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);

		model.addAttribute("code", status.toString());
		model.addAttribute("msg", HttpStatus.valueOf(Integer.valueOf(status.toString())).getReasonPhrase());
		return "content/error/errorPage";
	}
	
	@RequestMapping(value = "/errorPage", method = RequestMethod.GET)
	public String errorPage(String status, String error,Model model) {

		model.addAttribute("code", status);
		model.addAttribute("msg", error);
		return "content/error/errorPage";
	}


}
