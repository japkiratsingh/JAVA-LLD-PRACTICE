// Product Interface
interface Coffee {
    int getCost();

    String getDescription();
}

// Concrete Product
class SimpleCoffee implements Coffee {
    @Override
    public int getCost() {
        return 5;
    }

    @Override
    public String getDescription() {
        return "Simple Coffee";
    }
}

class CaramelCoffee implements Coffee {
    @Override
    public int getCost() {
        return 10;
    }

    @Override
    public String getDescription() {
        return "Caramel Coffee";
    }
}

// Abstract Decorator class
abstract class CoffeeDecorator implements Coffee {
    Coffee decoratedCoffee;

    public CoffeeDecorator(Coffee decoratedCoffee) {
        this.decoratedCoffee = decoratedCoffee;
    }

    @Override
    public int getCost() {
        return decoratedCoffee.getCost();
    }

    @Override
    public String getDescription() {
        return decoratedCoffee.getDescription();
    }
}

// Concrete Decorator
class MilkDecorator extends CoffeeDecorator {
    MilkDecorator(Coffee decoratedCoffe) {
        super(decoratedCoffe);
    }

    @Override
    public int getCost() {
        return super.getCost() + 2;
    }

    @Override
    public String getDescription() {
        return super.getDescription() + ", with Milk";
    }

}

class SugarDecorator extends CoffeeDecorator {
    SugarDecorator(Coffee decoratedCoffe) {
        super(decoratedCoffe);
    }

    @Override
    public int getCost() {
        return super.getCost() + 2;
    }

    @Override
    public String getDescription() {
        return super.getDescription() + ", with Sugar";
    }
}

public class DecoratorDesignPattern {
    public static void main(String[] args) {
        Coffee coffee = new SimpleCoffee();
        System.out.println("Cost: " + coffee.getCost());
        System.out.println("Description: " + coffee.getDescription());
        coffee = new MilkDecorator(coffee);
        System.out.println("Cost: " + coffee.getCost());
        System.out.println("Description: " + coffee.getDescription());
        coffee = new SugarDecorator(coffee);
        System.out.println("Cost: " + coffee.getCost());
        System.out.println("Description: " + coffee.getDescription());

        System.out.println("---------------------------------------------------");

        Coffee caramelCoffee = new CaramelCoffee();
        System.out.println("Cost: " + caramelCoffee.getCost());
        System.out.println("Description: " + caramelCoffee.getDescription());
        caramelCoffee = new MilkDecorator(caramelCoffee);
        System.out.println("Cost: " + caramelCoffee.getCost());
        System.out.println("Description: " + caramelCoffee.getDescription());
        caramelCoffee = new SugarDecorator(caramelCoffee);
        System.out.println("Cost: " + caramelCoffee.getCost());
        System.out.println("Description: " + caramelCoffee.getDescription());
    }
}
