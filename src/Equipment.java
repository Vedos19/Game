import java.util.Random;

public class Equipment{
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    /*
    Equipment(Weapon weapon, Armor armor) {
        w_value = weapon.value;
        w_dmg = weapon.weapon_dmg;
        w_type = weapon.type;
        w_name = weapon.name;
        a_value = armor.value;
        a_def = armor.armor_def;
        a_name = armor.name;
        a_type = armor.type;
    }
    */
    Equipment(){}

    String w_name, a_name;
    double w_dmg, a_def, w_value, a_value;
    String w_type, a_type;
    Random random = new Random();

    void equipArmor(Armor armor){
        a_value = armor.value;
        a_def = armor.armor_def;
        a_name = armor.name;
        a_type = armor.type;
    }
    void equipWeapon(Weapon weapon){
        w_value = weapon.value;
        w_dmg = weapon.weapon_dmg;
        w_type = weapon.type;
        w_name = weapon.name;
    }
    void showArmor(){
        System.out.print("--------------------------|---------|-------|--------\n" +
                ANSI_YELLOW + "Equipped armor            | Defense | Worth | Type\n" + ANSI_RESET +
                "--------------------------|---------|-------|--------\n");
        String format = "%-25s%s%-7s%s%-5s%s%s%n";
        String n1 = a_name;
        Double n2 = a_def;
        Double n3 = a_value;
        String n4 = a_type;
        System.out.printf(format, n1, " | ", n2, " | ", n3, " | ", n4);
    }
    void showWeapon(){
        System.out.print("--------------------------|---------|-------|--------\n" +
                ANSI_YELLOW + "Equipped weapon           | Damage  | Worth | Type\n" + ANSI_RESET +
                "--------------------------|---------|-------|--------\n");
        String format = "%-25s%s%-7s%s%-5s%s%s%n";
        String n1 = w_name;
        Double n2 = w_dmg;
        Double n3 = w_value;
        String n4 = w_type;
        System.out.printf(format, n1, " | ", n2, " | ", n3, " | ", n4);
    }

    //Dagger abilities
    double Stab(Player attacker, Mob receiver, double w_dmg){
        System.out.println("Stab!");
        double damage = attacker.strenght*1.5 + w_dmg - receiver.endurance;
        if(damage<0) damage = 0;
        receiver.hp -= damage;
        System.out.println(attacker.nick + " dealt " + damage + " damage!");
        return damage;
    }
    double Tearing_Cut(Player attacker, Mob receiver, double w_dmg){
        System.out.println("Tearing cut!");
        double damage = attacker.strenght + w_dmg - receiver.endurance;
        if(damage<0) damage = 0;
        receiver.bleed_cooldown = attacker.giveBleed(attacker, receiver);
        receiver.hp -= damage;
        System.out.println(attacker.nick + " dealt " + damage + " damage!");
        return damage;
    }
    double Flurry(Player attacker, Mob receiver, double w_dmg){
        System.out.println("Flurry!");
        double damage, total_damage = 0;
        for(int i=0;i<5;i++){
            damage = attacker.strenght + w_dmg - receiver.endurance + random.nextInt(3);
            if(damage<0) damage = 0;
            receiver.hp -= damage;
            System.out.println(attacker.nick + " dealt " + damage + " damage!");
            total_damage += damage;
        }
        return total_damage;
    }
    //Sword abilities
    double Slash(Player attacker, Mob receiver, double w_dmg) {
        System.out.println("Slash!");
        double damage = attacker.strenght*1.5 + w_dmg - receiver.endurance;
        if(damage<0) damage = 0;
        receiver.hp -= damage;
        System.out.println(attacker.nick + " dealt " + damage + " damage!");
        return damage;
    }
    double Fury_Slashes(Player attacker, Mob receiver, double w_dmg) {
        System.out.println("Fury Slashes!");
        double damage, total_damage = 0;
        for(int i=0;i<3;i++){
            damage = attacker.strenght - receiver.endurance;
            if(damage<0) damage = 0;
            receiver.hp -= damage;
            System.out.println(attacker.nick + " dealt " + damage + " damage!");
            total_damage += damage;
        }
        return total_damage;
    }
    double Mighty_Swing(Player attacker, Mob receiver, double w_dmg){
        System.out.println("Mighty Swing!");
        double damage = (attacker.strenght + w_dmg) * 3 - receiver.endurance;
        if(damage<0) damage = 0;
        receiver.hp -= damage;
        System.out.println(attacker.nick + " dealt " + damage + " damage!");
        return damage;
    }
    //Mace abilities
    double Heavy_Slam(Player attacker, Mob receiver, double w_dmg) {
        System.out.println("Heavy Slam!");
        double damage = attacker.strenght*1.5 + w_dmg - receiver.endurance;
        if(damage<0) damage = 0;
        receiver.hp -= damage;
        System.out.println(attacker.nick + " dealt " + damage + " damage!");
        return damage;
    }
    double Crushing_Blow(Player attacker, Mob receiver, double w_dmg) {
        System.out.println("Crushing Blow!");
        double damage = attacker.strenght*1.5 + w_dmg - receiver.endurance * 0.25;
        if(damage<0) damage = 0;
        receiver.hp -= damage;
        System.out.println(attacker.nick + " dealt " + damage + " damage!");
        return damage;
    }
    double Powerful_Blow(Player attacker, Mob receiver, double w_dmg){
        System.out.println("Powerful Blow!");
        double damage = (attacker.strenght + w_dmg) * 3 - receiver.endurance/2;
        if(damage<0) damage = 0;
        receiver.hp -= damage;
        attacker.stun_cooldown++;
        System.out.println(attacker.nick + " dealt " + damage + " damage!");
        return damage;
    }
    //Skill 1-3 depends on equipped weapon
    void Skill1(Player attacker, Mob receiver, double w_dmg, String w_type){
        if(w_type.equals("Sword")) Slash(attacker, receiver, w_dmg);
        if(w_type.equals("Dagger")) Stab(attacker, receiver, w_dmg);
        if(w_type.equals("Mace")) Heavy_Slam(attacker, receiver, w_dmg);
    }
    void Skill2(Player attacker, Mob receiver, double w_dmg, String w_type){
        if(w_type.equals("Sword")) Fury_Slashes(attacker, receiver, w_dmg);
        if(w_type.equals("Dagger")) Tearing_Cut(attacker, receiver, w_dmg);
        if(w_type.equals("Mace")) Crushing_Blow(attacker, receiver, w_dmg);
    }
    void Skill3(Player attacker, Mob receiver, double w_dmg, String w_type){
        if(w_type.equals("Sword")) Mighty_Swing(attacker, receiver, w_dmg);
        if(w_type.equals("Dagger")) Flurry(attacker, receiver, w_dmg);
        if(w_type.equals("Mace")) Powerful_Blow(attacker, receiver, w_dmg);
    }
}