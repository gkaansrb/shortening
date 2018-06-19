package com.url.shortening.web;

import com.url.shortening.exception.ShorteningConvertException;
import com.url.shortening.model.UrlMappingDto;
import com.url.shortening.service.ShorteningConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class ShorteningController {

	@Autowired
	private ShorteningConverter shorteningConverter;

	@RequestMapping(value = "/main", method = RequestMethod.GET)
	public String main() {
		return "main";
	}

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	@ResponseBody
	public List<UrlMappingDto> list() {
		return shorteningConverter.getUrlList();
	}

	@RequestMapping(value = "/{shortUrl}", method = RequestMethod.GET)
	public String redirect(@PathVariable String shortUrl) {
		try {
			return "redirect://" + shorteningConverter.findByShortUrl(shortUrl).getOriginUrl();
		} catch (ShorteningConvertException e) {
			return "error";
		}
	}

	@RequestMapping(value = "/encrypt", method = RequestMethod.POST)
	@ResponseBody
	public UrlMappingDto encrypt(@RequestParam(required = true) String originUrl) throws ShorteningConvertException {
		return shorteningConverter.convert(originUrl);
	}
}
