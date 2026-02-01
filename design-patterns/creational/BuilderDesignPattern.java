import java.util.ArrayList;
import java.util.List;

class Burger {

    private final String bun;
    private final String patty;
    private final List<String> toppings;
    private final boolean cheese;
    private final boolean ketchup;

    // Private constructor → only Builder can create
    private Burger(BurgerBuilder builder) {
        this.bun = builder.bun;
        this.patty = builder.patty;
        this.toppings = builder.toppings;
        this.cheese = builder.cheese;
        this.ketchup = builder.ketchup;
    }

    public void display() {
        System.out.println("Bun: " + bun);
        System.out.println("Patty: " + patty);
        System.out.println("Toppings: " + toppings);
        System.out.println("Cheese: " + cheese);
        System.out.println("Ketchup: " + ketchup);
    }

    // -------- BUILDER --------
    public static class BurgerBuilder {

        private final String bun;
        private final String patty;
        private List<String> toppings = new ArrayList<>();
        private boolean cheese;
        private boolean ketchup;

        // Mandatory fields
        public BurgerBuilder(String bun, String patty) {
            this.bun = bun;
            this.patty = patty;
        }

        // Optional fields
        public BurgerBuilder addTopping(String topping) {
            this.toppings.add(topping);
            return this;
        }

        public BurgerBuilder addCheese() {
            this.cheese = true;
            return this;
        }

        public BurgerBuilder addKetchup() {
            this.ketchup = true;
            return this;
        }

        public Burger build() {
            return new Burger(this);
        }
    }
}

public class BuilderDesignPattern {
    public static void main(String[] args) {

        Burger burger = new Burger.BurgerBuilder("Wheat", "Veg")
                .addTopping("Onion")
                .addTopping("Lettuce")
                .addCheese()
                .addKetchup()
                .build();

        burger.display();
    }
}
