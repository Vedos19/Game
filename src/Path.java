public class Path {
    public static int path(Player player, Equipment equipment, Pocket pocket, Mob mob1, Mob mob2, Mob mob3){
        int pass;

        if(Fight.fight(player, mob1, equipment, pocket)==1)
            if(Fight.fight(player, mob2, equipment, pocket)==1)
                if(Fight.fight(player, mob3, equipment, pocket)==1)
                    pass=1;
                else
                    pass = 0;
            else
                pass = 0;
        else
            pass = 0;

        if(pass==1)
            System.out.println("You survived your journey to the city!");
        else{
            System.out.println("You died on a road!\n\nYou've been teleported back to the city.");
            mob1.reset(mob1);
            mob2.reset(mob2);
            mob3.reset(mob3);
        }
        return pass;
    }
}
