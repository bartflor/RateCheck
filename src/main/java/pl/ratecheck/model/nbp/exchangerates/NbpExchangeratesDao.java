package pl.ratecheck.model.nbp.exchangerates;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import pl.ratecheck.model.CurrencyCode;
import pl.ratecheck.model.ExchangeratesDao;
import pl.ratecheck.model.PriceType;
import pl.ratecheck.model.Rate;

@Repository
public class NbpExchangeratesDao implements ExchangeratesDao {
	@Autowired
	private NbpExchangeratesProvider ratesProvider;

	public NbpExchangeratesDao() {
	}

	@Override
	public List<? extends Rate> getRatesInDatesRange(LocalDate startDate, LocalDate stopDate, CurrencyCode currencyCode, PriceType price) {
		List<NbpExchangerate> ratesList = ratesProvider.provideRatesList(startDate, stopDate, currencyCode);

		return ratesList.stream()
				 .map(exchangerate -> mapToOnePriceTypeRate(price, exchangerate))
				 .collect(Collectors.toList());
	}

	private Rate mapToOnePriceTypeRate(PriceType price, NbpExchangerate exchangerate) {
		
		return new Rate(){
			
			private LocalDate date = exchangerate.getDate();
			
			@Override
			public LocalDate getDate() {
				return this.date;
			}

			@Override
			public void setDate(String date) {}

			@Override
			public Double getValue() {
				return price.equals(PriceType.ASK) ? exchangerate.getAsk() : exchangerate.getBid();
			}

			@Override
			public void setValue(Double value) {}
		};
	}
}
