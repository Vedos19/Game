import java.util.Random;

public class Mob {
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";

    Mob(int strenght, int endurance, int vitality, int stamina, int exp, int lvl, String nick){
        this.strenght = strenght;
        this.endurance = endurance;
        this.vitality = vitality;
        this.stamina = stamina;
        hp = vitality*4;
        energy = stamina;
        this.exp = exp;
        this.lvl = lvl;
        this.nick = nick;
    }

    protected int strenght, endurance, vitality, stamina, hp, energy, exp, lvl, stun_cooldown, bleed_cooldown, beer_counter;
    protected boolean stun;
    protected String nick;
    Random random = new Random();

    double Attack(Mob attacker, Mob receiver, double a_def){
        double damage;
        damage = attacker.strenght - receiver.endurance - a_def;
        if(damage<0) damage = 0;
        return damage;
    }
    double FistAttack(Mob attacker, Mob receiver){
        double damage;
        damage = attacker.strenght - receiver.endurance;
        if(damage<0) damage = 0;
        return damage;
    }
    double Strong_fist_attack(Player attacker, Mob receiver){
        double damage;
        damage = attacker.strenght * 2 - receiver.endurance;
        if(damage<0) damage = 0;
        return damage;
    }
    double Skill(Mob attacker, Mob receiver, double a_def){
        double damage = attacker.Attack(attacker, receiver, a_def);
        return damage;
    }
    void stunCooldown(Mob receiver){
        if(receiver.stun_cooldown>0){
            receiver.stun_cooldown--;
            receiver.stun=true;
        }
        else
            receiver.stun=false;
    }
    void bleedCooldown(Mob attacker, Mob receiver){
        if(receiver.bleed_cooldown>0){
            receiver.bleed_cooldown--;
            receiver.hp-=2;
            System.out.println(attacker.nick + "'s" + ANSI_RED + " bleed" + ANSI_RESET + " dealt 2 damage");
        }
    }
    int giveBleed(Mob attacker, Mob receiver){
        int rng, bleed;
        rng = random.nextInt(10);
        if(rng==0){
            receiver.bleed_cooldown=3;
            System.out.println(receiver.nick + " is" + ANSI_RED + " bleeding " + ANSI_RESET + "for 3 turns!");
            bleed = 3;
        }
        else if(rng<4) {
            receiver.bleed_cooldown=2;
            System.out.println(receiver.nick + " is" + ANSI_RED + " bleeding " + ANSI_RESET + "for 2 turns!");
            bleed = 2;
        }
        else if(rng<8){
            receiver.bleed_cooldown=1;
            System.out.println(receiver.nick + " is" + ANSI_RED + " bleeding " + ANSI_RESET + "for 1 turn!");
            bleed = 1;
        }
        else{
            System.out.println(attacker.nick + " tried to " + ANSI_RED + "bleed " + ANSI_RESET + receiver.nick + " out but didn't success!");
            bleed = 0;
        }
        return bleed;
    }
    void GetExp(Player player, Mob deadMob){
        player.exp += deadMob.exp;
    }
    public void reset(Mob mob) {
        stun = false;
        stun_cooldown = 0;
        bleed_cooldown = 0;
        beer_counter = 0;
        hp = vitality*4;
        energy = stamina/3;
    }

    @Override
    public String toString(){
        return(nick + " (Lvl " + lvl + ".)");
    }
}
