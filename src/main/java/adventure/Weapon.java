package adventure;

public class Weapon extends Item implements Tossable{

    /**
     *
     */
    private static final long serialVersionUID = -81261580785844089L;

    public Weapon(long i, String string, String string2) {
        super( i,  string,  string2);
    }

    /**
     * implements the toss
     * interface for weapon
     */
    @Override
    public String toss(){
        
        return "Weapons are great but I dont have room for this: "+this.getName();
    }
}
