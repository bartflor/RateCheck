package pl.ratecheck.config;

import org.springframework.core.convert.converter.Converter;

import pl.ratecheck.model.CurrencyCode;

public class StringToCurrencyEnumConverter implements Converter<String, CurrencyCode> {

	@Override
	public CurrencyCode convert(String currencyCode) {
		switch (currencyCode.toUpperCase()) {
			case "EUR":
				return CurrencyCode.EUR;
			case "GBP": 
				return CurrencyCode.GBP;
			case "JPY": 
				return CurrencyCode.JPY;
			case "USD": 
				return CurrencyCode.USD;
			default:
				return CurrencyCode.USD;
		}
	}

}
