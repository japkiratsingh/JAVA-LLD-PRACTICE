public class SingletonDesignPattern {
    private static volatile SingletonDesignPattern instance;

    private SingletonDesignPattern() {
    }

    public static SingletonDesignPattern getInstance() {
        if (instance == null) {
            synchronized (SingletonDesignPattern.class) {
                if (instance == null) {
                    instance = new SingletonDesignPattern();
                }
            }
        }
        return instance;
    }

    public static void main(String[] args) {
        SingletonDesignPattern instance1 = SingletonDesignPattern.getInstance();
        SingletonDesignPattern instance2 = SingletonDesignPattern.getInstance();

        System.out.println("Instance 1 hashCode: " + instance1.hashCode());
        System.out.println("Instance 2 hashCode: " + instance2.hashCode());
        System.out.println("Are both instances the same? " + (instance1 == instance2));
    }
}