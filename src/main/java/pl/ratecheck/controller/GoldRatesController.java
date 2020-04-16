package pl.ratecheck.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import pl.ratecheck.model.GoldMassUnits;
import pl.ratecheck.model.Rate;
import pl.ratecheck.service.GoldRateService;

@Controller
public class GoldRatesController {
	@Autowired
	private GoldRateService goldRateService;
	@Autowired
	private InputValidator inputDateValidator;

	@RequestMapping("/goldrate")
	public String showGoldRate(
			@RequestParam(value = "start") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate start,
			@RequestParam(value = "stop") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate stop,
			@RequestParam(value = "unit") GoldMassUnits unit,
			Model model) {
		List<? extends Rate> rates;
		
		if (inputDateValidator.isValid(start, stop, model)) {
			rates = goldRateService.getGoldRateFromPeriod(start, stop, unit);
		} else {
			return "findgoldrates";
		}
		if(rates == null) {
			model.addAttribute("error", "No rates in selected dates range.");
			return "findgoldrates";
		} 
		model.addAttribute("RatesList", rates);
		return "goldrate";
	}

	@RequestMapping("/findgoldrates")
	public String showFindGoldRate() {
		return "findgoldrates";
	}

}
