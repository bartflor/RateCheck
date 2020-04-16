package pl.ratecheck.model.todayrates;

public class NbpTodayExchangerate {
	String currency;
	String code;
	double mid;

	public NbpTodayExchangerate() {

	}

	public NbpTodayExchangerate(String currency, String code, double mid) {
		super();
		this.currency = currency;
		this.code = code;
		this.mid = mid;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public double getMid() {
		return mid;
	}

	public void setMid(double mid) {
		this.mid = mid;
	}

}
