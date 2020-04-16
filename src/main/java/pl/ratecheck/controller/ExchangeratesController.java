package pl.ratecheck.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import pl.ratecheck.model.CurrencyCode;
import pl.ratecheck.model.Rate;
import pl.ratecheck.service.ExchangerateService;

@Controller
public class ExchangeratesController {
	@Autowired
	private ExchangerateService exchangerateService;
	@Autowired
	private InputValidator inputDateValidator;

	@RequestMapping("/exchangerate")
	public String showExchangerate(Model model,
			@RequestParam(value = "start") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate start,
			@RequestParam(value = "stop") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate stop,
			@RequestParam(value = "currency_code") CurrencyCode currencyCode,
			@RequestParam(value = "price") String price) {
		List<? extends Rate> rates;
		if (inputDateValidator.isValid(start, stop, model)) {
			rates = exchangerateService.getExchangerateFromPeriod(start, stop, currencyCode, price);
		} else {
			return "findexchangerates";
		}
		if (rates == null) {
			model.addAttribute("error", "No rates in selected dates range.");
			return "findexchangerates";
		}
		model.addAttribute("RatesList", rates);
		return "exchangerate";
	}

	@RequestMapping("/findexchangerates")
	public String showFindExchangerate() {
		return "findexchangerates";
	}
}
