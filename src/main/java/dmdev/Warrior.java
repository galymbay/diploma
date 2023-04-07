package dmdev;

import dmdev.weapon.MeleeWeapon;

public class Warrior<T extends MeleeWeapon> {
    private String name;
    private T weapon;

    public Warrior(String name, T weapon) {
        this.name = name;
        this.weapon = weapon;
    }

    @Override
    public String toString() {
        return "Warrior{" +
                "name='" + name + '\'' +
                ", weapon=" + weapon +
                '}';
    }
}
