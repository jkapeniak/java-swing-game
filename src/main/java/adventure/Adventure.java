package adventure;

import java.util.ArrayList;
import java.util.HashMap;

import org.json.simple.JSONObject;
import org.json.simple.JSONArray;


public class Adventure implements java.io.Serializable{
    /* you will need to add some private member variables */

    /**
     *
     */
    private static final long serialVersionUID = 7728306554336895313L;
    /* ======== Required public methods ========== */
        /* note,  you don't have to USE all of these
        methods but you do have to provide them.
        We will be using them to test your code */
    private ArrayList<Room> allRooms;    
    private Room currentRoom;
    private Player currentPlayer;
    private ArrayList<Item> allItems;




    public Adventure(){
        
    }

    /**
     * This Adventure constructor method takes the JSOBObject representing a json adventure file and generates 
     * all the components of an Adventure, a player,rooms with its corresponding entrances, and items.
     * @param obj is the JSONObject that represents the adventure json file that is used to load a new adventure game
     */

    
    public Adventure(JSONObject obj){
        JSONObject adventure = (JSONObject) obj.get("adventure");

        setAllItems((JSONArray) adventure.get("item"));

        setAllRooms( (JSONArray) adventure.get("room"));

        setItemsInEachRoom(allItems,allRooms);


        for(Room room: this.allRooms){

            room.setRoomsforNavigation(this.allRooms);
        }

         setCurrentPlayer();
         setStartRoom(this.allRooms);

    }


    


    /**
     * Takes in the full list of rooms in the adventure 
     * and sets the current room 
     * in the adventure by finding the start tag
     * @param listOfRooms total list of rooms 
     * in the the adventure
     */
    public void setStartRoom(ArrayList<Room> listOfRooms ){
        
       
       for(Room room: listOfRooms){

      
        String trueorFalse = room.getStartRoom();
        
        if(trueorFalse.equals("true")){
            

            this.setCurrentRoom(room);

        }
        

       }
    }
    /**
     * This method calls the new player method
     * which creates a new player object
     * this current player instance object
     * in adventure is set
     */
    public void setCurrentPlayer(){
        Player newPlayer = new Player();
        this.currentPlayer = newPlayer;

    }


    /**
     * This method takes a list
     * of all the items in the adventure
     * and sets the instance list
     * of item ins adventure to 
     * the passed list
     * @param itemList list of all 
     * the items in the adventure
     */
    public void setAllItems(JSONArray itemList){

        allItems = new ArrayList<Item>();

        for (int p = 0; p <itemList.size(); p++) {

            JSONObject itemOBJECT = (JSONObject) itemList.get(p);
           
            /**
             * TODO try and modularize setting of interfaces
             */

             

            long itemID  = (long) itemOBJECT.get("id");
            
            
            
            
            

                String itemName  = (String) itemOBJECT.get("name");
                String itemDesc  = (String) itemOBJECT.get("desc");

                Boolean isEdible = false;
                Boolean isTossible = false;
                Boolean isWearable = false;
                Boolean isReadable = false;
            /**
              * CHECKING IF EDIBLE
              */
             try {
                 boolean getEdibleBooleanFromJson = (boolean) itemOBJECT.get("edible");
                 isEdible = getEdibleBooleanFromJson;
                 
             } catch (Exception e) {
               
             }
             /**
              * CHECKING IF TOSSABLE
              */
             try {
                boolean getTossableBooleanFromJson = (boolean) itemOBJECT.get("tossable");
                isTossible = getTossableBooleanFromJson;
                
            } catch (Exception e) {
                
             
            }


            /**
              * CHECKING IF Wearable
              */
              try {
                boolean getWearableBooleanFromJson = (boolean) itemOBJECT.get("wearable");
                isWearable = getWearableBooleanFromJson;
                
            } catch (Exception e) {
                
             
            }


            /**
              * CHECKING IF Readable
              */
              try {
                boolean getReadableBooleanFromJson = (boolean) itemOBJECT.get("readable");
                isReadable = getReadableBooleanFromJson;
                
            } catch (Exception e) {
                
             
            }
             
                

            try {


                if(isTossible && !isEdible){

                    allItems.add( new Weapon(itemID, itemName, itemDesc) );

                } else if(isEdible && isTossible){
                 
                    allItems.add( new SmallFood(itemID, itemName, itemDesc) );

                } else if(isEdible){
 
                    allItems.add( new Food(itemID, itemName, itemDesc) ); 

                } else if(isWearable && isReadable){

                    allItems.add( new BrandedClothing(itemID, itemName, itemDesc) );     

                } else if(isWearable){

                    allItems.add( new Clothing(itemID, itemName, itemDesc) );     

                }else if(isReadable){

                    allItems.add( new Spell(itemID, itemName, itemDesc) );     

                }else {
                
                    allItems.add( new Item(itemID, itemName, itemDesc) ); 
                }
                
            } catch (Exception e) {
                System.out.println("Error in instantiating item subclass");
            }


               
       
                
                 
         }

         System.out.println("Testing item list in adventure START");
         for(Item item: allItems){
             System.out.println(item.getName());

            
         }

         System.out.println("Testing item list in adventure END");

    }
    /**
     * this method returns
     * all the items in the
     * adventure
     * @return list of all items in the
     * adventure
     */
    public ArrayList<Item> listAllItems(){

        return this.allItems;
    }

    /**
     * 
     * @return Returns the object that represents the current player of the adventure.
     */

    public Player getCurrentPlayer(){
        return this.currentPlayer;
    }

    /**
     * This method is called by the Adventure constructor and intializes the rooms and items in the adventure.
     * @param roomList Takes the JSONArray that represents the rooms in the game.
     */
    public void setAllRooms(JSONArray roomList){
        ArrayList<Room> listOfRooms = new ArrayList<Room>();
        
        for (int i = 0; i <roomList.size(); i++) {
            JSONObject roomOBJECT = (JSONObject) roomList.get(i);
            String name  = (String) roomOBJECT.get("name");
            String shortDescription  = (String) roomOBJECT.get("short_description");
            String longDescription  = (String) roomOBJECT.get("long_description");                
            long id  = (long) roomOBJECT.get("id");

            Room tempRoom = new Room(id,shortDescription,longDescription,name);
            String startCondition = (String) roomOBJECT.get("start");
                if(startCondition != null){
                    tempRoom.setStartRoom();
                } 
                ArrayList<Integer> listOfLootIdsInRoom;
                try{
                    listOfLootIdsInRoom = new ArrayList<Integer>();

                    JSONArray lootArray = (JSONArray) roomOBJECT.get("loot");            
                    for (int k = 0; k <lootArray.size(); k++) {                        
                        JSONObject lootObject = (JSONObject) lootArray.get(k);
                        long lootId  = (long) lootObject.get("id"); 
                        Integer roomLootId = (int)lootId;
                        listOfLootIdsInRoom.add(roomLootId);                      
                    }
                    tempRoom.setListOfLootIdsInRoom(listOfLootIdsInRoom);
                } catch(NullPointerException e) {
                }  

                HashMap<String,Long> hashMapofEntrancesInRoom = new HashMap<String,Long>();
                
                JSONArray entranceArray = (JSONArray) roomOBJECT.get("entrance");
                
                    for (int j = 0; j <entranceArray.size(); j++) {
                        
                        JSONObject entranceObject = (JSONObject) entranceArray.get(j);
                        long entranceId  = (long) entranceObject.get("id");

                        String entranceDirection = (String) entranceObject.get("dir");

                        hashMapofEntrancesInRoom.put(entranceDirection,entranceId);

                       
                        

                        tempRoom.setHashMapofEntrancesInRoom(hashMapofEntrancesInRoom);
                        
                    }




                
            listOfRooms.add(tempRoom);
        }// end of roomList iterator
        this.allRooms = listOfRooms;
    }

    /**
     * This method takes the master list of
     * items in the adventure and
     * the master list of rooms in
     * adventure and sets all the items into
     * those rooms
     * @param itemsInAdventure arrayList of items in the
     * adventure
     * @param listOfRooms arraylist of rooms
     * that needed items added to them
     */
    public void setItemsInEachRoom(ArrayList<Item> itemsInAdventure,ArrayList<Room> listOfRooms){
       
        ArrayList<Item> listOfItemsInRoom;

            for(Room room: listOfRooms){
                
                listOfItemsInRoom = new ArrayList<Item>();
                


                if(room.getListOfLootIdsInRoom()==null){
                    String statement;

                } else {

                    for( Integer lootIdInRoom: room.getListOfLootIdsInRoom() ){
                        
                        for(Item itemFromAllItemsInAdventure: itemsInAdventure){
    
                            Integer itemId = (int)itemFromAllItemsInAdventure.getId();
    
                            if(itemId==lootIdInRoom){
                                listOfItemsInRoom.add(itemFromAllItemsInAdventure);
                            }
    
    
                        }
    
                    }
                    
                    room.setItemsInRoom(listOfItemsInRoom);
                    
                }
                


            }
            

        
    }





    /**
     * This method returns an Array List representing all the rooms in the adventure.
     * @return The List of rooms in the adventure.
     */

    public ArrayList<Room> listAllRooms(){

        return this.allRooms;
    }

    
    

    
    /**
     * 
     * @return This method returns the short description of the room in which the player is currently in.
     */
    public String getCurrentRoomDescription(){

        return this.getCurrentRoom().getShortDescription();
    }
    /**
     * 
     * @return This method returns a Room object which represents the current room the player is in.
     */
    public Room getCurrentRoom(){
        return this.currentRoom;
    }

    /**
     * Sets the current room in the adventure.
     * @param room This method takes in a Room object 
     * that represents the current room the player is in and sets the current room
     * in the adventure class and in the player class.
     */
    public void setCurrentRoom(Room room){
        this.currentRoom = room;
        this.currentPlayer.setCurrentRoom(room);
    }

/**
 * This method prints some useful information about the adventure class instance variables
 */
@Override
 public String toString() {
    return "\nThe current room in the adventure is called"+this.getCurrentRoom().getName()+   ".\n";
 }
}
