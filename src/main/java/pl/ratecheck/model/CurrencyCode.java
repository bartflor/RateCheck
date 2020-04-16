package pl.ratecheck.model;

public enum CurrencyCode {
	USD("US dollar", "USD"),
	EUR("Euro", "EUR"),
	GBP("Pound Sterling", "GBP"),
	JPY("Yen", "JPY");
	
	private final String name;
	private final String code;
	
	
	private CurrencyCode(String name, String code) {
		this.name = name;
		this.code = code;
	}


	public String getName() {
		return name;
	}


	public String getCode() {
		return code;
	}

}
