public class Dish extends MenuItem{
    private DishType dishType;
    private int weightInGrams;

    public Dish(String name, double price, DishType dishType, int weightInGrams) {
        super(name, price);
        this.dishType = dishType;
        this.weightInGrams = weightInGrams;
    }

    @Override
    public String toString() {
        return "Dish{" +
                "dishType=" + dishType +
                ", weightInGrams=" + weightInGrams +
                ", name='" + name + '\'' +
                ", price=" + price +
                '}';
    }
}
