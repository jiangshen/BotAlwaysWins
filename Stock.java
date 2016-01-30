public class Stock {
	private double m_dWorth;
	private long m_lShares;
	private double m_dRatio;
	private double m_dValue;
	private double m_dVolatility;
	private String m_szSymbol;
	private double m_dAsk;
	private double m_dBuy;
	private double m_dEPS;
	
    
    public void updateValues(double dWorth, long lShares, double dRatio, double dValue, double dVolatility, String szSymbol) {
    	setWorth(dWorth);
    	setShares(lShares);
    	setRatio(dRatio);
    	setValue(dValue);
    	setVol(dVolatility);
    	setSymbol(szSymbol);
    }
    
    public void updateValues(String szSymbol, double dWorth, double dRatio, double dVolatility) {
    	setSymbol(szSymbol);
    	setWorth(dWorth);
    	setRatio(dRatio);
    	setVol(dVolatility);
    }
    
    public void update() {
    	m_dEPS = m_dWorth * m_dRatio / m_dAsk;
    }

	public double getWorth() {
		return m_dWorth;
	}

	public void setWorth(double m_Worth) {
		this.m_dWorth = m_Worth;
	}

	public long getShares() {
		return m_lShares;
	}

	public void setShares(long m_lShares) {
		this.m_lShares = m_lShares;
	}

	public double getRatio() {
		return m_dRatio;
	}

	public void setRatio(double m_dRatio) {
		this.m_dRatio = m_dRatio;
	}

	public double getValue() {
		return m_dValue;
	}

	public void setValue(double m_dValue) {
		this.m_dValue = m_dValue;
	}

	public double getVol() {
		return m_dVolatility;
	}

	public void setVol(double m_dVolatility) {
		this.m_dVolatility = m_dVolatility;
	}

	public String getSymbol() {
		return m_szSymbol;
	}

	public void setSymbol(String m_szSymbol) {
		this.m_szSymbol = m_szSymbol;
	}

	public double getAsk() {
		return m_dAsk;
	}

	public void setAsk(double m_dAsk) {
		this.m_dAsk = m_dAsk;
	}

	public double getBuy() {
		return m_dBuy;
	}

	public void setBuy(double m_dBuy) {
		this.m_dBuy = m_dBuy;
	}

	public double getEPS() {
		return m_dEPS;
	}

	public void setEPS(double m_dEPS) {
		this.m_dEPS = m_dEPS;
	}
}