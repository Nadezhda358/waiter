public class Dish extends MenuItem{
    private DishType dishType;
    private int weightInGrams;

    public Dish(String name, double price, DishType dishType, int weightInGrams) {
        super(name, price);
        this.dishType = dishType;
        this.weightInGrams = weightInGrams;
    }

    public DishType getDishType() {
        return dishType;
    }

    public void setDishType(DishType dishType) {
        this.dishType = dishType;
    }

    public int getWeightInGrams() {
        return weightInGrams;
    }

    public void setWeightInGrams(int weightInGrams) {
        this.weightInGrams = weightInGrams;
    }

    @Override
    public String toString() {
        return String.format("%s (%d g) - %.2f lv.",this.name,this.weightInGrams,this.price);
    }
}
