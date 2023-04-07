package dmdev;

import dmdev.weapon.Bow;
import dmdev.weapon.Sword;

public class WeaponRunner {
    public static void main(String[] args) {
        Warrior<Sword> warrior = new Warrior("Legolas", new Sword());
        Archer<Bow> archer = new Archer("Strela", new Bow());

        System.out.println(warrior);
        System.out.println(archer);
    }
}
