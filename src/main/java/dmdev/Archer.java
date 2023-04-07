package dmdev;

import dmdev.weapon.DamageWeapon;

public class Archer<T extends DamageWeapon> {
    public String name;
    public T weapon;

    public Archer(String name, T weapon) {
        this.name = name;
        this.weapon = weapon;
    }

    @Override
    public String toString() {
        return "Archer{" +
                "name='" + name + '\'' +
                ", weapon=" + weapon +
                '}';
    }
}
