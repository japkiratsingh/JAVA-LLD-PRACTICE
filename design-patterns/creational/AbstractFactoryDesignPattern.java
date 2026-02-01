interface Engine {
    void start();
}

interface Tyre {
    void rotate();
}

class NormalEngine implements Engine {
    @Override
    public void start() {
        System.out.println("Normal Engine started");
    }
}

class RacingEngine implements Engine {
    @Override
    public void start() {
        System.out.println("Racing Engine started");
    }
}

class NormalTyre implements Tyre {
    @Override
    public void rotate() {
        System.out.println("Normal Tyre rotated");
    }
}

class RacingTyre implements Tyre {
    @Override
    public void rotate() {
        System.out.println("Racing Tyre rotated");
    }
}

interface VehicleFactory {
    Engine createEngine();

    Tyre createTyre();
}

class NormalVehicleFactory implements VehicleFactory {
    @Override
    public Engine createEngine() {
        return new NormalEngine();
    }

    @Override
    public Tyre createTyre() {
        return new NormalTyre();
    }
}

class RacingVehicleFactory implements VehicleFactory {
    @Override
    public Engine createEngine() {
        return new RacingEngine();
    }

    @Override
    public Tyre createTyre() {
        return new RacingTyre();
    }
}

public class AbstractFactoryDesignPattern {
    public static void main(String[] args) {
        VehicleFactory vehicleFactory = new NormalVehicleFactory();
        Engine normaEngine = vehicleFactory.createEngine();
        Tyre normalTyre = vehicleFactory.createTyre();
        normaEngine.start();
        normalTyre.rotate();
        vehicleFactory = new RacingVehicleFactory();
        Engine racingEngine = vehicleFactory.createEngine();
        Tyre racingTyre = vehicleFactory.createTyre();
        racingEngine.start();
        racingTyre.rotate();
    }
}