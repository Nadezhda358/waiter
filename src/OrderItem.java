public class OrderItem {
    private MenuItem item;
    private int count;

    public OrderItem(MenuItem item, int count) {
        this.item = item;
        this.count = count;
    }

    public MenuItem getItem() {
        return item;
    }

    public void setItem(MenuItem item) {
        this.item = item;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    @Override
    public String toString() {
        return this.count + " x " + this.item;
    }

    //public String toString(int i) {
     //   return this.count + " x " + this.item;
   // }
}
