public class Armor extends Item{
    Armor(String name, int value, double armor_def, String type){
        super(name, value);
        this.armor_def = armor_def;
        this.type = type;
    }
    double armor_def;
    //1 - Light Robe, 2 - Leather Jacket, 3 - Heavy Armor
    String type;
}