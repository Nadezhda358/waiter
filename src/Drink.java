public class Drink extends MenuItem{
    private DrinkType drinkType;
    private int volumeInMl;

    public Drink(String name, double price, DrinkType drinkType, int volumeInMl) {
        super(name, price);
        this.drinkType = drinkType;
        this.volumeInMl = volumeInMl;
    }

    public DrinkType getDrinkType() {
        return drinkType;
    }

    public void setDrinkType(DrinkType drinkType) {
        this.drinkType = drinkType;
    }

    public int getVolumeInMl() {
        return volumeInMl;
    }

    public void setVolumeInMl(int volumeInMl) {
        this.volumeInMl = volumeInMl;
    }

    @Override
    public String toString() {
        return String.format("%s (%d ml) - %.2f lv.",this.name,this.volumeInMl,this.price);
    }
}
