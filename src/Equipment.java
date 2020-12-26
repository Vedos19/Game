import java.util.Random;

public class Equipment {
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_YELLOW = "\u001B[33m";

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
    String w_name, a_name;
    double w_dmg, a_def, w_value, a_value;
    int w_type, a_type;
    Random random = new Random();

    void equipArmor(Equipment equipment, Armor armor){
        a_value = armor.value;
        a_def = armor.armor_def;
        a_name = armor.name;
        a_type = armor.type;
    }
    void equipWeapon(Equipment equipment, Weapon weapon){
        w_value = weapon.value;
        w_dmg = weapon.weapon_dmg;
        w_type = weapon.type;
        w_name = weapon.name;
    }
    void showArmor(){
        System.out.println("Equipped armor:\nName: " + a_name + "\nProtection: " + a_def + "\nValue: " + a_value + "\nType: ");
        if(a_type==1) System.out.print("Light robe\n");
        else if(a_type==2) System.out.print("Leather jacket\n");
        else if(a_type==3) System.out.print("Plate armor\n");
    }
    void showWeapon(){
        System.out.print("Equipped weapon:\nName: " + w_name + "\nDamage: " + w_dmg + "\nValue: " + w_value + "\nTyp: ");
        if(w_type==1) System.out.print("Dagger\n");
        else if(w_type==2) System.out.print("Sword\n");
        else if(w_type==3) System.out.print("Mace\n");
    }

    //Dagger abilities
    double Stab(Player attacker, Mob receiver, double w_dmg){
        double damage = attacker.strenght*1.5 + w_dmg - receiver.endurance;
        if(damage<0) damage = 0;
        receiver.hp -= damage;
        System.out.println(attacker.nick + " dealt " + damage + " damage!");
        return damage;
    }
    double Tearing_Cut(Player attacker, Mob receiver, double w_dmg){
        double damage = attacker.strenght + w_dmg - receiver.endurance;
        if(damage<0) damage = 0;
        receiver.bleed_cooldown = attacker.giveBleed(attacker, receiver);
        receiver.hp -= damage;
        System.out.println(attacker.nick + " dealt " + damage + " damage!");
        return damage;
    }
    double Flurry(Player attacker, Mob receiver, double w_dmg){
        double damage, total_damage = 0;
        for(int i=0;i<5;i++){
            damage = attacker.strenght*0.5 + w_dmg - receiver.endurance + random.nextInt(3);
            if(damage<0) damage = 0;
            receiver.hp -= damage;
            System.out.println(attacker.nick + " dealt " + damage + " damage!");
            total_damage += damage;
        }
        return total_damage;
    }
    //Sword abilities
    double Slash(Player attacker, Mob receiver, double w_dmg) {
        double damage = attacker.strenght*1.5 + w_dmg - receiver.endurance;
        if(damage<0) damage = 0;
        receiver.hp -= damage;
        System.out.println(attacker.nick + " dealt " + damage + " damage!");
        return damage;
    }
    double Fury_Slashes(Player attacker, Mob receiver, double w_dmg) {
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
        double damage = (attacker.strenght + w_dmg) * 3 - receiver.endurance;
        if(damage<0) damage = 0;
        receiver.hp -= damage;
        System.out.println(attacker.nick + " dealt " + damage + " damage!");
        return damage;
    }
    //Mace abilities
    double Heavy_Slam(Player attacker, Mob receiver, double w_dmg) {
        double damage = attacker.strenght*1.5 + w_dmg - receiver.endurance;
        if(damage<0) damage = 0;
        receiver.hp -= damage;
        System.out.println(attacker.nick + " dealt " + damage + " damage!");
        return damage;
    }
    double Crushing_Blow(Player attacker, Mob receiver, double w_dmg) {
        double damage = attacker.strenght*1.5 + w_dmg - receiver.endurance * 0.25;
        if(damage<0) damage = 0;
        receiver.hp -= damage;
        System.out.println(attacker.nick + " dealt " + damage + " damage!");
        return damage;
    }
    double Powerful_Blow(Player attacker, Mob receiver, double w_dmg){
        double damage = (attacker.strenght + w_dmg) * 3 - receiver.endurance/2;
        if(damage<0) damage = 0;
        receiver.hp -= damage;
        attacker.stun_cooldown++;
        System.out.println(attacker.nick + " dealt " + damage + " damage!");
        return damage;
    }

    //Skill 1-3 depends on equipped weapon
    void Skill1(Player attacker, Mob receiver, double w_dmg, int w_type){
        if(w_type==1) Stab(attacker, receiver, w_dmg);
        if(w_type==2) Slash(attacker, receiver, w_dmg);
        if(w_type==3) Heavy_Slam(attacker, receiver, w_dmg);
    }
    void Skill2(Player attacker, Mob receiver, double w_dmg, int w_type){
        if(w_type==1) Tearing_Cut(attacker, receiver, w_dmg);
        if(w_type==2) Fury_Slashes(attacker, receiver, w_dmg);
        if(w_type==3) Crushing_Blow(attacker, receiver, w_dmg);
    }
    void Skill3(Player attacker, Mob receiver, double w_dmg, int w_type){
        if(w_type==1) Flurry(attacker, receiver, w_dmg);
        if(w_type==2) Mighty_Swing(attacker, receiver, w_dmg);
        if(w_type==3) Powerful_Blow(attacker, receiver, w_dmg);
    }
}