public class Trader{

    private final String name;
    private final String city;

    public Trader(String name, String city){
        this.name = name;
        this.city = city;
    }

    public String getName(){
        return this.name;
    }

    public String getCity(){
        return this.city;
    }

    public String toString(){
        return "Trader:" + this.name + " in " + this.city;
    }

    @Override
    public boolean equals(Object o){
        Trader trader = (Trader)o;
        return this.getCity().equals(trader.getCity()) &&
               this.getName().equals(trader.getName());
    }

}