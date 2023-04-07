package dmdev.weapon;

public class Bow implements DamageWeapon {
    @Override
    public int getDamage() {
        return 10;
    }

    @Override
    public String toString() {
        return "damage: " + getDamage();
    }
}
