class SubSystem1 {
    public void operation1() {
        System.out.println("Operation 1");
    }
}

class SubSystem2 {
    public void operation2() {
        System.out.println("Operation 2");
    }
}

class SubSystem3 {
    public void operation3() {
        System.out.println("Operation 3");
    }
}

class Facade {
    private SubSystem1 subSystem1;
    private SubSystem2 subSystem2;
    private SubSystem3 subSystem3;

    public Facade() {
        subSystem1 = new SubSystem1();
        subSystem2 = new SubSystem2();
        subSystem3 = new SubSystem3();
    }

    public void operation() {
        subSystem1.operation1();
        subSystem2.operation2();
        subSystem3.operation3();
    }
}

public class FascadeDesignPattern {
    public static void main(String[] args) {
        Facade facade = new Facade();
        facade.operation();
    }
}
