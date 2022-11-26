public class Dish extends MenuItem {
    private DishType dishType;
    private int weightInGrams;

    public Dish(String name, double price, DishType dishType, int weightInGrams) {
        super(name, price);
        this.dishType = dishType;
        this.weightInGrams = Math.abs(weightInGrams);
    }

    public DishType getDishType() {
        return dishType;
    }

    public int getWeightInGrams() {
        return weightInGrams;
    }

    @Override
    public String toString() {
        return String.format("%s (%d g) - %.2f lv.", this.name, this.weightInGrams, this.price);
    }
}
