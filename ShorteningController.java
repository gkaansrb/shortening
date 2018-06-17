package com.url.shortening.web;

import com.url.shortening.exception.ShorteningConvertException;
import com.url.shortening.model.ConvertType;
import com.url.shortening.model.UrlMappingDto;
import com.url.shortening.service.ShorteningConverterStrategyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class ShorteningController {

	@Autowired
	private ShorteningConverterStrategyService shorteningConverterStrategyService;

	@RequestMapping(value = "/main", method = RequestMethod.GET)
	public String main() {
		return "main";
	}

	@RequestMapping(value = "/decrypt", method = RequestMethod.GET)
	public String decrypt(@RequestBody String url) throws ShorteningConvertException {
		UrlMappingDto convert = shorteningConverterStrategyService.convert(ConvertType.DECRYPT, url);
		return "redirect:" + convert.getOriginUrl();
	}

	@RequestMapping(value = "/encrypt", method = RequestMethod.POST)
	@ResponseBody
	public UrlMappingDto encrypt(@RequestBody String url) throws ShorteningConvertException {
		return shorteningConverterStrategyService.convert(ConvertType.ENCRYPT, url);
	}
}
