public class Main {
public static void main(String[] args) {
        int path_lvl = 0;
        System.out.println();
        Player player = new Player(3, 3, 3, 3,1, 0, "Nameless", 30);
        player.create(player);

        //lvl1
        Mob wolf1 = new Mob(5, 2, 3, 3, 30, 1, "Young Wolf");
        Mob_Tearing_Cut wolf2 = new Mob_Tearing_Cut(6, 2, 4, 3, 50, 1, "Wolf");
        Mob_Tearing_Cut wolf3 = new Mob_Tearing_Cut(6, 3, 5, 3, 100, 2, "Alpha Wolf");

        //lvl2
        Mob_Deafening_Blow bandit1 = new Mob_Deafening_Blow(5, 4,7,100,3, 2, "Thug");
        Mob_Tearing_Cut bandit2 = new Mob_Tearing_Cut(7, 3,6,150,3, 2, "Cutthroat");
        Mob_Strong_Attack bandit3 = new Mob_Strong_Attack(6, 6,8,250,3, 3, "Bandit Leader");

        //fight_1, city_1
        do path_lvl += Path.path(player, wolf1, wolf2, wolf3);
        while(path_lvl!=1);
        player.upgrade(player, 3);
        City.enter_city(player, 1);

        //fight_2, city_2
        do path_lvl += Path.path(player, bandit1, bandit2, bandit3);
        while(path_lvl!=2);
        player.upgrade(player, 3);
        City.enter_city(player, 2);
    }
}