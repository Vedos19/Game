public class Weapon extends Item{
    Weapon(String name, double value, double weapon_dmg, int type){
        super(name, value);
        this.weapon_dmg = weapon_dmg;
        this.type = type;
    }
    double weapon_dmg;
    //type: 1 - sword, 2 - dagger, 3 - mace
    int type;

    @Override
    public String toString() {
        return "Name: " + name + "\nDamage: " + weapon_dmg + "\nValue: " + value;
    }
}
