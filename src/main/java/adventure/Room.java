
package adventure;

import java.util.ArrayList;
import java.util.HashMap;

public class Room implements java.io.Serializable{

    /**
     *
     */
    private static final long serialVersionUID = -6035600630522289674L;
    /* you will need to add some private member variables */
    private String startRoom = "false";
    private long id;
    private String name;
    private String shortDescription;
    private String longDescription;
    private ArrayList<Item> itemsInRoom; 
    private HashMap<String, Long> hashMapofEntrancesInRoom;
    private ArrayList<Room> roomsforNavigation;
    private ArrayList<Integer> listOfLootIdsInRoom; 
    
    
    
    /* required public methods */
    public Room(){

    }

    
   
    /**
     * Parametrized constructor for room object.
     * @param roomId Room id.
     * @param roomShortDescription Short description of room.
     * @param roomLongDescription Long description of room.
     * @param roomName Room name.
     */
    public Room(long roomId, String roomShortDescription, String roomLongDescription, String roomName){
        
        setId(roomId);
        
       
        setName(roomName);
        setShortDescription(roomShortDescription);
        setLongDescription(roomLongDescription);
        
    }
    /**
     * takes arraylist of loot ids
     * and sets the instance
     * variable in room
     * @param lootIdsForRoom arraylist of loot ids
     * that correspond to a room
     */
    public void setListOfLootIdsInRoom(ArrayList<Integer> lootIdsForRoom){
        listOfLootIdsInRoom = lootIdsForRoom;
    }

    /**
     * Returns the arrayList
     * of loot ids in a room
     * @return arraylist of integer loot
     * ids
     */
    public ArrayList<Integer> getListOfLootIdsInRoom(){
        return listOfLootIdsInRoom;
    }

    /**
     * Sets the id of the room
     * @param roomId long id of 
     * room
     */
    public void setId(long roomId){
        this.id = roomId;
    }

    /**
     * sets the name of the room
     * @param roomName string room 
     * name
     */
    public void setName(String roomName){
        this.name = roomName;
    }

    /** 
     * sets the short description
     *  of the room 
     * @param roomShortDescription string representation
     * of short description of the room
     */
    public void setShortDescription(String roomShortDescription){
        this.shortDescription = roomShortDescription;
    }

    /**
     * sets long description of
     * the room
     * @param roomLongDescription string representation
     * of long description of room
     */
    public void setLongDescription(String roomLongDescription){
        this.longDescription = roomLongDescription;
    }

    /**
     * Returns the id of the room.
     * @return Room id.
     */
   public long getId(){
    return this.id;
    }

    /**
     * Returns the name of the room object.
     * @return Name of room.
     */
    public String getName(){
        return this.name;
    }

     /**
     * Returns the long description of the room object.
     * @return Long description of room.
     */

    public String getLongDescription(){
        return this.longDescription;
    }

    /**
     * Returns the short description of the room object.
     * @return Short description of room.
     */
    public String getShortDescription(){
        return this.shortDescription;
    }
    
    

    /**
     * Returns the String value of the startRoom in the Adventure.
     * @return a string saying true or false.
     */
    public String getStartRoom(){
        return this.startRoom;
    }

    /**
     * Takes a list of rooms as a parameter and sets the room list instance variable in the room object.
     * @param fillRoomsList Takes an arraylist  of rooms as a parameter.
     */
    public void setRoomsforNavigation(ArrayList<Room> fillRoomsList){
        this.roomsforNavigation = fillRoomsList;
    }

    
    /**
     * This method returns the array list of rooms associated with the room object.
     * @return Returns the array list of rooms.
     */
    public ArrayList<Room> getRoomsforNavigation(){
        return this.roomsforNavigation;
    }

    
   

    /**
     * Sets the hashmap that contains all the entrances that correspond to a particular room object.
     * @param hashMapofEntrancesWithinRoom Takes in the hashmap representing the entrances in a room.
     */

    public void setHashMapofEntrancesInRoom(HashMap<String,Long> hashMapofEntrancesWithinRoom){
        this.hashMapofEntrancesInRoom = hashMapofEntrancesWithinRoom;
    }

   

    /**
     * Returns the hashmap of entrances in a particular room object.
     * @return HashMap of entrances.
     */

    public HashMap<String,Long> getHashMapofEntrancesInRoom(){
        return this.hashMapofEntrancesInRoom;
    }

    /**
     * Sets the value of start room in the room object to true.
     */
    public void setStartRoom(){
        this.startRoom = "true";
    }

    
    /**
     * Removes an item from the list of items in the room.
     * @param itemName is the string name of the item to be removed from the room.
     */
    public void removeItemfromList(String itemName){
        for(Item item: itemsInRoom){

            if(item.getName().equals(itemName)){
                itemsInRoom.remove(item);
                return;
            }


        }
    }

    /**
     * Sets the items located in a room.
     * @param listOfItemsInRoom is the list of items located in a particular room object.
     */
    public void setItemsInRoom(ArrayList<Item> listOfItemsInRoom){

        this.itemsInRoom = listOfItemsInRoom;


    }

    /**
     * adds item to the current room
     * @param itemToAddToRoom item to add
     */
    public void addItemToRoom(Item itemToAddToRoom){
        this.itemsInRoom.add(itemToAddToRoom);
    }

    
    /**
     * Returns the list of items in the room object.
     * @return ArrayList of items in the room.
     */
    public ArrayList<Item> getItemsInRoom(){
        
        return this.itemsInRoom;
    }

    
    


    /**
     * Takes an item name and returns that item object from the room if it exists in the room.
     * @param itemToGetFromRoomName String name of item to get from room.
     * @return Item object.
     */

    public Item returnItemFromRoom(String itemToGetFromRoomName){
        
        for(Item item: this.getItemsInRoom()){

            if(item.getName().equals(itemToGetFromRoomName)){
                
                return item;
            }

        }
        
        return null;
    }

   
    




    /**
     * Takes in a String direction and the instance method returns 
     * a room associated with the direction if the room has a entrance leading to 
     * a room in that direction.
     * @param direction Direction of room user is attempting to go to.
     * @return Connected room if it exits else it returns the same room.
     */
    public Room getConnectedRoom(String direction){
        Room tempRoom = this; 
        
        
        
        
        HashMap<String,Long> mapOfE = this.getHashMapofEntrancesInRoom();
       
        if(mapOfE.containsKey(direction)){
            
            for(Room room: tempRoom.getRoomsforNavigation()){

                
                if(room.getId()==mapOfE.get(direction)){
                    
                    return room;
                }
            }
        }

        return tempRoom;
    }


    /**
     * Returns a string representation
     * of some useful information about
     * a room object
     */
   @Override
   public String toString() {
      return "\nroom name: "+ this.getName() + " Short Description of the room: " + this.getShortDescription()+"\n";
   }

}
