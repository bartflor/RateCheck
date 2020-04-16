package pl.ratecheck.config;

import org.springframework.core.convert.converter.Converter;

import pl.ratecheck.model.GoldMassUnits;

public class StringToGoldMassUnitEnumConverter implements Converter<String, GoldMassUnits> {

	@Override
	public GoldMassUnits convert(String unit) {
		switch (unit.toLowerCase()) {
			case "ounce":
				return GoldMassUnits.OUNCE;
			case "gram": default:
				return GoldMassUnits.GRAM;
		}
	}

}
