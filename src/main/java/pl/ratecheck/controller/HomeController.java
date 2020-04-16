package pl.ratecheck.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import pl.ratecheck.service.TodayRatesService;

@Controller
public class HomeController {
	@Autowired
	private TodayRatesService todayRatesService;	
	@RequestMapping("/")
	public String showHome(Model model) {
		model.addAttribute("rates", todayRatesService.getTodayRates());
		return "home";
	}
	
	@RequestMapping("/about")
	public String showAbout() {
		return "about";
	}
}
