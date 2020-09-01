package adventure;


public class Entrance implements java.io.Serializable {
   
    private static final long serialVersionUID = -5054122879715131504L;

    private long id;
    
    private String dir;

    public Entrance(){
        
    }

    public Entrance(long entranceId, String entranceDir){
        this.id = entranceId;
        this.dir = entranceDir;
    }

    /**
     * @return Returns the id of the room which the entrance connects to.
     */

    public long getId(){
        return this.id;
    }

    /**
     * Takes a long
     * and sets the entrance id
     * to that long
     * @param entranceId long id to set
     */
    public void setId(long entranceId){
        this.id = entranceId;
    }

    /**
     * 
     * @return Returns the direction of the room which the entrance connects to/
     */

    public String getDir(){
        return this.dir;
    }
    /**
     * Takes in a string
     * representing the
     * direction of the entrance
     * and sets it
     * @param entranceDir string
     * representing entrance direction
     */
    public void setDir(String entranceDir){
        this.dir = entranceDir;
    }


}
