package adventure;


public class Clothing extends Item implements Wearable{


    private boolean clothingIsOn = false;

    /**
     *
     */
    private static final long serialVersionUID = 8773947943928745492L;

    public Clothing(long i, String string, String string2) {
        super( i,  string,  string2);
    }
    /**
     * whether clothing
     * is on or not
     * @return boolean
     * of clothing on 
     * or not
     */
    public boolean getClothingIsOn(){
        return this.clothingIsOn;
    }

    /**
     * implementing wear interface
     * of clothing class
     */
    @Override
    public String wear(){
        clothingIsOn = true;
        return "Damn I look fine as heck in this "+this.getName()+".";
    }

}
