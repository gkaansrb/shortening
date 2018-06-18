package com.url.shortening.web;

import com.url.shortening.exception.ShorteningConvertException;
import com.url.shortening.model.UrlMappingDto;
import com.url.shortening.service.ShorteningConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class ShorteningController {

	@Autowired
	private ShorteningConverter shorteningConverter;

	@RequestMapping(value = "/main", method = RequestMethod.GET)
	public String main() {
		return "main";
	}

	@RequestMapping(value = "/decrypt", method = RequestMethod.GET)
	public String decrypt(@RequestParam(value = "shortUrl") String shortUrl) throws ShorteningConvertException {
		UrlMappingDto convert = shorteningConverter.findByShortUrl(shortUrl);
		return convert.getOriginUrl();
	}

	@RequestMapping(value = "/encrypt", method = RequestMethod.POST)
	@ResponseBody
	public UrlMappingDto encrypt(@RequestParam(required = true) String originUrl) throws ShorteningConvertException {
		return shorteningConverter.convert(originUrl);
	}
}
