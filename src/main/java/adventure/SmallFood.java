package adventure;

public class SmallFood extends Food implements Tossable,Edible{

    public SmallFood(long i, String string, String string2) {
        super(i, string, string2);
        // TODO Auto-generated constructor stub
    }

    /**
     *
     */
    private static final long serialVersionUID = 1761649863769056069L;
    
    /**
     * implements toss interface
     * for small food
     * class
     */
    @Override
    public String toss(){
        return "I dont need this "+this.getName()+".";
    }


}
