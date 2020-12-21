public class Main {
public static void main(String[] args) {
        int path_lvl = 0;
        System.out.println();
        Player player = new Player(3, 3, 3, 1, 0, "Nameless", 30);
        player.create(player);

        //lvl1
        Mob wolf1 = new Mob(5, 2, 3, 30, 1, "Young Wolf");
        Mob wolf2 = new Mob(6, 2, 4, 50, 1, "Wolf");
        Mob wolf3 = new Mob(6, 3, 5, 100, 2, "Alpha Wolf");

        //fight, city
        do path_lvl += Path.path(player, wolf1, wolf2, wolf3);
        while(path_lvl!=1);
        player.upgrade(player, 3);
        City.enter_city(player, 1);
    }
}