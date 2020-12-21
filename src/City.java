import java.util.Scanner;

public class City {
    public static int enter_city(Player player, int city_lvl){
        int choice=0, leave=0;
        Scanner scanner = new Scanner(System.in);


        System.out.println("\nYou entered the city!");
        do{
            System.out.print("\nWhere you want to go?\n1. City square\n2. Tavern\n3. Quest Board\n4. City gate\nYour choice: ");
            choice = scanner.nextInt();
            switch(choice){
                case 1:
                    System.out.println("Nothing here!");
                    break;
                case 2:
                    System.out.println("Nothing here!");
                    break;
                case 3:
                    System.out.println("No quests at this moment!");
                    break;
                case 4:
                    System.out.println("You really want to leave city?\n1. Yes\n2. No\nYour choice: ");
                    choice = scanner.nextInt();
                    if(choice==1){
                        leave=1;
                    }
                    else if(choice==2)
                        System.out.println("Smart choice!");
                    else
                        System.out.println("Wrong choice!");
            }
        }while(leave!=1);

        return leave;
    }
}
