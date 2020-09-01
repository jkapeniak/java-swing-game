package adventure;

import java.util.ArrayList;

public class Item implements java.io.Serializable {

    private static final long serialVersionUID = 4317496562785529276L;
    private long id;
    private String name;
    private String longDescription;
    private ArrayList<Room> roomsforMatchingItemtoRoom;
    private boolean isEdible = false;


    
   
    public Item(){
        
    }

    /**
     * Parameterized Constructor for the item object.
     * @param itemId Sets item id.
     * @param itemName  Sets item name.
     * @param itemDescription Sets the item itemDescriptionription;
     */

    public Item(long itemId, String itemName, String itemDescription){
        setId(itemId);
        
        setName(itemName );
       
        setLongDescription(itemDescription);

    }
    /**
     * Sets whether the item is edible or 
     * not
     */
    public void setIsEdible(){
        this.isEdible = true;

    }
    /**
     * returns wether the item is edible or not
     * @return boolean edible true or false
     */
    public boolean getIsEdible(){
        return this.isEdible;
    }

    /**
     * sets item id
     * @param itemId item id to
     * set
     */
    public void setId(long itemId){
        this.id = itemId;
    }


     /**
     * Returns the id of the item.
     * @return Id of the item.
     */
    public long getId(){
        return this.id;
    }

    /**
     * sets long description of the item
     * @param itemDescription long description
     * of the item
     */
    public void setLongDescription(String itemDescription){
        
        this.longDescription = itemDescription;
    }

    /**
   * Returns the long itemDescriptionription of the jtem.
   * @return Long itemDescriptionription of the item.
   */

  
  public String getLongDescription(){
    return this.longDescription;
    }

    /**
     * sets the name of the item
     * @param itemName string name of
     * the item
     */
    public void setName(String itemName ){
        this.name = itemName;
    }





    /**
     * Returns the name of the item.
     * @return Name of the item.
     */
    public String getName(){
        return this.name;
  }
 
  


  






    /**
     * Is passed the list of rooms in the Adventure and sets the list of rooms instance variable in the item class.
     * @param fillRoomsList List of rooms in the adventure.
     */
    public void setRoomsforMatchingItemtoRoom(ArrayList<Room> fillRoomsList){
        this.roomsforMatchingItemtoRoom = fillRoomsList;
    }
    
    /**
     * returns a master list of items
     * in the adventure to list
     * @return arrayList of type room
     * of all the rooms in the
     * adventure
     */
    public ArrayList<Room> getRoomsforMatchingItemtoRoom(){
        return this.roomsforMatchingItemtoRoom;
    }



    
    

    /**
     * Returns a room object which represents the room which the item is contained in.
     * @return Room in which item is contained in.
     */
    public Room getContainingRoom(){
        //returns a reference to the room that contains the item

        //would call item get containingRooom
        for(Room room: roomsforMatchingItemtoRoom){



            

            final int getdigit3 = 100;
            if(this.getId()== (room.getId() % getdigit3) ){

                return room;
                
                
            }
        }


        return null;
    }
    

    /**
     * returns a string
     * representation of some useful information
     * about an item object
     */
    @Override
   public String toString() {
      return "item name: "+ this.getName() + " itemDescriptionription of the item: " + this.getLongDescription();
   }
}

