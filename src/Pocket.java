import java.util.ArrayList;
import java.util.Scanner;

public class Pocket {
    Pocket(){
        tool_pocket[0]=3;
        tool_pocket[1]=1;
    }
    //0 - pot hp || 1 - pot mana || 3 - tool1 || 4 - tool2
    int[] tool_pocket = new int[4];
    Scanner scanner = new Scanner(System.in);
    ArrayList<Item> equipment_pocket = new ArrayList<>();

    void pickupItem(int potka_hp, int potka_mana, int tool1, int tool2){
        tool_pocket[0]+=potka_hp;
        tool_pocket[1]+=potka_mana;
        tool_pocket[2]+=tool1;
        tool_pocket[3]+=tool2;
    }
    void pickupEquipment(Item item){
        if(equipment_pocket.size()<=5){
            System.out.println(item.name + " został dodany do ekwipunku.");
            equipment_pocket.add(item);
        }
        else
            System.out.println("Brak miejsca w ekwipunku!");
    }
    void showItemPocket(){
        System.out.println("1. Small healing potion (+10hp): " + tool_pocket[0]);
        System.out.println("2. Small energy potion (+3e): " + tool_pocket[1]);
        System.out.println("3. Tool1: " + tool_pocket[2]);
        System.out.println("4. Tool2: " + tool_pocket[3]);
    }
    void showEquipmentPocket(){
        int licznik = 1;
        for(Item i : equipment_pocket) {
            System.out.println(licznik + ". " + i.name);
            licznik++;
        }
    }
    void dropFromEquipmentPocket(){
        System.out.println("Which item you want to drop? ");
        showEquipmentPocket();
        System.out.print("Your choice: ");
        int a = scanner.nextInt();
        equipment_pocket.remove(a-1);
    }
}
