public class Drink extends MenuItem{
    private DrinkType drinkType;
    private int volumeInMl;

    public Drink(String name, double price, DrinkType drinkType, int volumeInMl) {
        super(name, price);
        this.drinkType = drinkType;
        this.volumeInMl = volumeInMl;
    }

    @Override
    public String toString() {
        return this.name+ " ("+this.volumeInMl+" ml) - "+this.price+" lv.";
    }
}
