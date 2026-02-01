interface Image {
    void display();
}

class RealImage implements Image {
    private String fileName;

    public RealImage(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public void display() {
        System.out.println("Displaying " + fileName);
    }
}

class VirtualProxyImage implements Image {
    private RealImage realImage;
    private String fileName;

    public VirtualProxyImage(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public void display() {
        if (realImage == null) {
            realImage = new RealImage(fileName);
        }
        realImage.display();
    }
}

public class ProxyDesignPattern {
    public static void main(String[] args) {
        Image image = new VirtualProxyImage("image.jpg");
        image.display();
    }
}
