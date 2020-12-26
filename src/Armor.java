public class Armor extends Item{
    Armor(String name, double value, double armor_def, int type){
        super(name, value);
        this.armor_def = armor_def;
        this.type = type;
    }
    double armor_def;
    int type;
}