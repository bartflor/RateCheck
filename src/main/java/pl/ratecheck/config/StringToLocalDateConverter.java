package pl.ratecheck.config;

import java.time.LocalDate;

import org.springframework.core.convert.converter.Converter;

public class StringToLocalDateConverter implements Converter<String, LocalDate> {

	@Override
	public LocalDate convert(String source) {
		System.out.println("conv");
		return LocalDate.parse(source);
	}

}
