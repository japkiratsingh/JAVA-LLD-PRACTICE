interface ProtoType<T> {
    T copy();
}

class NPC implements ProtoType<NPC> {

    private String name;
    private int attack;
    private int defence;
    private int health;

    public NPC(String name, int attack, int defence, int health) {
        this.attack = attack;
        this.name = name;
        this.defence = defence;
        this.health = health;
    }

    NPC(NPC otherNpc) {
        this.attack = otherNpc.attack;
        this.name = otherNpc.name;
        this.defence = otherNpc.defence;
        this.health = otherNpc.health;
    }

    @Override
    public NPC copy() {
        return new NPC(this);
    }

    public void display() {
        System.out.println("________________________________________________");
        System.out.println("Name: " + name);
        System.out.println("Attack: " + attack);
        System.out.println("Defence: " + defence);
        System.out.println("Health: " + health);
        System.out.println("________________________________________________");

    }

}

public class PrototypeDesignPattern {
    public static void main(String[] args) {
        NPC goblin = new NPC("Goblin", 10, 5, 100);
        NPC goblin2 = goblin.copy();
        NPC goblin3 = goblin.copy();
        NPC goblin4 = goblin.copy();
        NPC goblin5 = goblin.copy();

        goblin2.display();
        goblin3.display();
        goblin4.display();
        goblin5.display();
    }
}