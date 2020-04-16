package pl.ratecheck.model.nbp.exchangerates;

import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import pl.ratecheck.model.CurrencyCode;
import pl.ratecheck.model.ExchangeratesDao;
import pl.ratecheck.model.Rate;
import pl.ratecheck.model.nbp.NoDataExeption;

@Repository
public class NbpExchangeratesDao implements ExchangeratesDao {
	@Autowired
	private NbpExchangeratesProvider urlProvider;

	public NbpExchangeratesDao() {
	}

	@Override
	public List<? extends Rate> getRatesInDatesRange(LocalDate startDate, LocalDate stopDate,
			CurrencyCode currencyCode, String price) {
		List<NbpExchangerate> ratesList;
		try {
			ratesList = urlProvider.provideRatesList(startDate, stopDate, currencyCode);
		} catch (NoDataExeption e) {
			e.printStackTrace();
			return null;
		}
		List<Rate> resultRates = new LinkedList<Rate>();
		ratesList.stream().map(exchangerate -> priceMapper(price, exchangerate)).forEach(resultRates::add);

		return resultRates;
	}

	private Rate priceMapper(String price, NbpExchangerate exchangerate) {
		
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
				if(price.equalsIgnoreCase("ask")) {
					return exchangerate.getAsk();
				}else {
					return exchangerate.getBid();
				}
			}

			@Override
			public void setValue(Double value) {}
		};
	}
}
