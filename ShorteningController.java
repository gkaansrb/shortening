package com.url.shortening.web;

import com.url.shortening.model.ConvertType;
import com.url.shortening.service.ShorteningConverterStrategyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ShorteningController {

	@Autowired
	private ShorteningConverterStrategyService shorteningConverterStrategyService;

	@RequestMapping(value = "/main", method = RequestMethod.GET)
	public String main() {
		return "main";
	}

	@RequestMapping(value = "/decrypt", method = RequestMethod.POST)
	public String decrypt(@RequestBody String url) {
		shorteningConverterStrategyService.convert(ConvertType.DECRYPT, url);
		return "decrypt";
	}

	@RequestMapping(value = "/encrypt", method = RequestMethod.POST)
	public String encrypt(@RequestBody String url) {
		shorteningConverterStrategyService.convert(ConvertType.ENCRYPT, url);
		return "encrypt";
	}
}
