package adventure;

public class Food extends Item implements Edible{

    /**
     *TODO ensure that it is appropriate to serialize this
     */
    private static final long serialVersionUID = -3234960400166778987L;


   
    
    
    public Food(long i, String string, String string2) {
        super( i,  string,  string2);
    }
    


    /**
     * implements eat interface
     * for the food class
     */
	@Override
    public String eat(){
        return "That was a yummy "+this.getName()+".";
    }

    
} 

