package pl.ratecheck.model.todayrates;

import java.util.List;

import pl.ratecheck.model.nbp.goldrates.NbpGoldRate;

public class NbpTodayRates {

	private NbpGoldRate todayGoldRate;
	private List<NbpTodayExchangerate> todayExchangeRates;

	public NbpTodayRates() {
	}

	public NbpTodayRates(NbpGoldRate todayGoldRate, List<NbpTodayExchangerate> todayExchangeRates) {
		super();
		this.todayGoldRate = todayGoldRate;
		this.todayExchangeRates = todayExchangeRates;
	}

	public NbpGoldRate getTodayGoldRate() {
		return todayGoldRate;
	}

	public void setTodayGoldRate(NbpGoldRate todayGoldRate) {
		this.todayGoldRate = todayGoldRate;
	}

	public List<NbpTodayExchangerate> getTodayExchangeRates() {
		return todayExchangeRates;
	}

	public void setTodayExchangeRates(List<NbpTodayExchangerate> todayExchangeRates) {
		this.todayExchangeRates = todayExchangeRates;
	}

}
