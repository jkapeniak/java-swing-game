package adventure;

public class BrandedClothing extends Clothing implements Readable{

    /**
     *
     */
    private static final long serialVersionUID = -8574273143107903697L;

    public BrandedClothing(long i, String string, String string2) {
        super( i,  string,  string2);
    }
    /**
     * implements read interface
     * for branded clothing class
     */
    @Override
    public String read(){
        
        return "This clothing has something written on it: \"" + this.getLongDescription() +"\".";
    }

}
