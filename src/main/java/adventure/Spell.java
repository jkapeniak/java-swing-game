package adventure;

public class Spell extends Item implements Readable{

    /**
     *
     */
    private static final long serialVersionUID = -5163880340803854247L;

    public Spell(long i, String string, String string2) {
        super( i,  string,  string2);
    }
    /**
     * implements spell interface
     * for spell class
     */
    @Override
    public String read() {
        // TODO Auto-generated method stub
        return "expeliomis";
    }
    
}
