import java.util.Random;

public class Mob {
    Mob(double strenght, double endurance, double vitality, double exp, int lvl, String nick){
        this.strenght = strenght;
        this.endurance = endurance;
        this.vitality = vitality;
        hp = vitality*4;
        this.lvl = lvl;
        this.exp = exp;
        this.nick = nick;
    }

    protected double strenght, endurance, vitality, hp, exp;
    protected int lvl;
    protected String nick;
    Random random = new Random();

    double Attack(Mob attacker, Mob receiver){
        double damage;
        damage = attacker.strenght - receiver.endurance;
        if(damage<0) damage = 0;
        return damage;
    }
    void GetExp(Player player, Mob deadMob){
        player.exp += deadMob.exp;
    }
    public void reset(Mob mob) {
        mob.hp = mob.vitality*4;
    }

    @Override
    public String toString(){
        return(nick + " (Lvl " + lvl + ".)");
    }
}
