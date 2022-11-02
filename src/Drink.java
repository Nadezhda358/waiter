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
        return "Drink{" +
                "drinkType=" + drinkType +
                ", volumeInMl=" + volumeInMl +
                ", name='" + name + '\'' +
                ", price=" + price +
                '}';
    }
}
