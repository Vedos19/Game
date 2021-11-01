import java.util.ArrayList;
import java.util.Scanner;

public class Pocket {
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    Pocket(int gold){
        tool_pocket[0]=3;
        tool_pocket[1]=1;
        this.gold = gold;
    }
    //0 - pot hp (cap: 3, 25g)|| 1 - pot energy (cap: 3, 25g)|| 3 - tool1 (cap: 5, 10g)|| 4 - tool2 (cap: 5, 20g)
    int[] tool_pocket = new int[4];
    int gold;
    ArrayList<Weapon> weapon_pocket = new ArrayList<>();
    ArrayList<Armor> armor_pocket = new ArrayList<>();

    int fillToolBag(){
        int total_cost=0, missing_amount;
        missing_amount=3-tool_pocket[0];
        total_cost+=missing_amount*25;
        missing_amount=3-tool_pocket[1];
        total_cost+=missing_amount*25;
        missing_amount=5-tool_pocket[2];
        total_cost+=missing_amount*25;
        missing_amount=5-tool_pocket[3];
        total_cost+=missing_amount*25;
        if(gold>=total_cost){
            tool_pocket[0]=3;
            tool_pocket[1]=3;
            tool_pocket[2]=5;
            tool_pocket[3]=5;
            gold-=total_cost;
        }
        else
            System.out.println("You don't have " + total_cost + " gold!");
        return total_cost;
    }
    void pickupTool(int health_potion, int energy_potion, int tool1, int tool2){
        if(tool_pocket[0]<3) tool_pocket[0]+=health_potion;
        else System.out.println("Your bag is full!");
        if(tool_pocket[1]<3) tool_pocket[1]+=energy_potion;
        else System.out.println("Your bag is full!");
        if(tool_pocket[2]<5) tool_pocket[2]+=tool1;
        else System.out.println("Your bag is full!");
        if(tool_pocket[3]<5) tool_pocket[3]+=tool2;
        else System.out.println("Your bag is full!");
    }
    void pickupWeapon(Weapon weapon){
        if(weapon_pocket.size()<=5){
            System.out.println(weapon.name + " was added to inventory.");
            weapon_pocket.add(weapon);
        }
        else
            System.out.println("Your inventory is full!");
    }
    void pickupArmor(Armor  armor){
        if(armor_pocket.size()<=5){
            System.out.println(armor.name + " was added to inventory.");
            armor_pocket.add(armor);
        }
        else
            System.out.println("Your inventory is full!");
    }
    void showToolPocket(){
        System.out.println("1. Small healing potion (+10hp): " + tool_pocket[0]);
        System.out.println("2. Small energy potion (+3e): " + tool_pocket[1]);
        System.out.println("3. Tool1: " + tool_pocket[2]);
        System.out.println("4. Tool2: " + tool_pocket[3]);
    }
    void showWeaponPocket() {
        int counter = 1;
        if (!weapon_pocket.isEmpty()){
            System.out.print(ANSI_YELLOW + "   | Name                 | Damage  | Worth | Type" + ANSI_RESET);
            String format = "%s%-20s%s%-7s%s%-5s%s%s";
            for (Weapon w : weapon_pocket) {
                System.out.print("\n---|----------------------|---------|-------|--------\n");
                String n1 = w.name;
                Double n2 = w.weapon_dmg;
                int n3 = w.value;
                String n4 = w.type;
                System.out.print(counter);
                counter++;
                System.out.printf(format, ". | ", n1, " | ", n2, " | ", n3, " | ", n4);
            }
        }
        else System.out.println("Weapon pocket is empty!");
    }
    void showArmorPocket() {
        int counter = 1;
        if (!armor_pocket.isEmpty()){
            System.out.print(ANSI_YELLOW + "   | Name                 | Defense | Worth | Type" + ANSI_RESET);
            String format = "%s%-20s%s%-7s%s%-5s%s%s";
            for (Armor a : armor_pocket) {
                System.out.print("\n---|----------------------|---------|-------|--------\n");
                String n1 = a.name;
                Double n2 = a.armor_def;
                int n3 = a.value;
                String n4 = a.type;
                System.out.print(counter);
                counter++;
                System.out.printf(format, ". | ", n1, " | ", n2, " | ", n3, " | ", n4);
            }
        }
        else System.out.println("Armor pocket is empty!");
    }
    void dropFromWeaponPocket(int index){
        weapon_pocket.remove(index);
    }
    void dropFromArmorPocket(int index){
        armor_pocket.remove(index);
    }
    void showGold(){
        System.out.println("Your gold: " + ANSI_YELLOW + gold + ANSI_RESET);
    }
}
