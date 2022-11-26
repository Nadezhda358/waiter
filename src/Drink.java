public class Drink extends MenuItem{
    private DrinkType drinkType;
    private int volumeInMl;

    public Drink(String name, double price, DrinkType drinkType, int volumeInMl) {
        super(name, price);
        this.drinkType = drinkType;
        this.volumeInMl = Math.abs(volumeInMl);
    }

    public DrinkType getDrinkType() {
        return drinkType;
    }

    public int getVolumeInMl() {
        return volumeInMl;
    }

    @Override
    public String toString() {
        return String.format("%s (%d ml) - %.2f lv.",this.name,this.volumeInMl,this.price);
    }
}
