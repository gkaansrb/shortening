package com.url.shortening.web;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ShorteningController {

	@RequestMapping(value = "/main", method = RequestMethod.GET)
	public String main() {
		return "main";
	}
}
