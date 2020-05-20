package pl.ratecheck.config;

import org.springframework.core.convert.converter.Converter;

import pl.ratecheck.model.PriceType;

public class StringToPriceTypeEnumConverter implements Converter<String, PriceType> {

	@Override
	public PriceType convert(String price) {
		switch (price.toLowerCase()) {
			case "ask":
				return PriceType.ASK;
			case "bid": default:
				return PriceType.BID;
		}
	}

}
