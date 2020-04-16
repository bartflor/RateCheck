package pl.ratecheck.controller;

import java.time.LocalDate;

import org.springframework.stereotype.Component;
import org.springframework.ui.Model;
@Component
public class InputValidator {

	public boolean isValid(LocalDate start, LocalDate stop, Model model) {
		if(start == null || stop == null ) {
			model.addAttribute("error", "Start and stop dates fields are required.");
			return false;
		}
		if(start.isAfter(stop)) {
			model.addAttribute("error", "Start date must be before the stop date.");
			return false;
		}
		if(start.isAfter(LocalDate.now()) || stop.isAfter(LocalDate.now())) {
			model.addAttribute("error", "Start and stop dates must be in the past.");
			return false;
		}
		return true;
	}

}
