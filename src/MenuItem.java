import java.io.File;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;

public abstract class MenuItem {
    protected String name;
    protected double price;

    public MenuItem(String name, double price) {
        this.name = name;
        this.price = price;
    }
    public MenuItem() {
        this.name = "";
        this.price = 0;
    }


}
