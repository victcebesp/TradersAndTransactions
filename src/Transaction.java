public class Transaction {
    private final Trader trader;
    private final int year;
    private final int value;

    public Transaction(Trader trader, int year, int value){
        this.trader = trader;
        this.year = year;
        this.value = value;
    }

    public Trader getTrader(){
        return this.trader;
    }

    public int getYear(){
        return this.year;
    }

    public int getValue(){
        return this.value;
    }

    public String toString(){
        return "{" + this.trader + ", " +
                "year: "+this.year+", " + "value:" + this.value +"}";
    }

    @Override
    public boolean equals(Object o){
        Transaction transaction = (Transaction) o;
        return transaction.getValue() == this.getValue() &&
               transaction.getYear() == this.getYear() &&
               transaction.getTrader().equals(this.getTrader());
    }

    public String getTraderCity(){
        return trader.getCity();
    }
}
