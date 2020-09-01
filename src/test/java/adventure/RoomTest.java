package adventure;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.HashMap;

import static org.junit.Assert.assertTrue;
import org.junit.Test;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.junit.Before;


public class RoomTest{
    private Room testRoom;
    private ArrayList<Room> rooms;
    private Room currentRoom = null;

    



@Before
public void setup(){

    

}


/**
 * This test checks if the getConnectedRoom method 
 * correctly returns the connected room when passed a direction
 */
@Test
public void testConnectedRoomWithValidDirection(){

    Game theGame = new Game();

    RoomTest newTest = new RoomTest();

    JSONObject obj = theGame.loadAdventureJson("julius_adventure.json");
    JSONObject adventure = (JSONObject) obj.get("adventure");

    newTest.setRooms( (JSONArray) adventure.get("room"),  (JSONArray) adventure.get("item"));


    newTest.setStartRoom(newTest.rooms);

   

    Room testRoom = newTest.getStartRoom();

    
    testRoom.setRooms(newTest.rooms);

    newTest.testRoom = testRoom;


   

    
   Room connectedRoom = newTest.testRoom.getConnectedRoom("N");

  

   assertTrue(connectedRoom.getName().equals("MacKinnon"));





    
    
}


/**
 * This test checks if the getConnectedRoom method correctly returns 
 * the same intial room when passed a string corresponds to a entrance that does not exist
 */

@Test
public void testConnectedRoomWithInvalidDirection(){

    Game theGame = new Game();

    RoomTest newTest = new RoomTest();

    JSONObject obj = theGame.loadAdventureJson("julius_adventure.json");
    JSONObject adventure = (JSONObject) obj.get("adventure");

    newTest.setRooms( (JSONArray) adventure.get("room"),  (JSONArray) adventure.get("item"));


    newTest.setStartRoom(newTest.rooms);

   

    Room testRoom = newTest.getStartRoom();

    
    testRoom.setRooms(newTest.rooms);

    newTest.testRoom = testRoom;


   
    
   Room connectedRoom = newTest.testRoom.getConnectedRoom("E");

  

   assertTrue(connectedRoom.getName().equals("Rozanski"));





    
    
}

















public void setRooms(JSONArray roomList, JSONArray itemList){

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
                tempRoom.setTrue();
            } 


            try{
                JSONArray lootArray = (JSONArray) roomOBJECT.get("loot");

                ArrayList<Item> itemsInRoom = new ArrayList<Item>();

                for (int k = 0; k <lootArray.size(); k++) {
                    
                    JSONObject lootObject = (JSONObject) lootArray.get(k);
                    long lootId  = (long) lootObject.get("id");
                    int compareLootItem = (int) lootId;


                   

                    for (int p = 0; p <itemList.size(); p++) {
                        JSONObject itemOBJECT = (JSONObject) itemList.get(p);
                       
                        

                        long itemID  = (long) itemOBJECT.get("id");
                        
                        int convertedItemId = (int) itemID;
                        
                        
                        if( compareLootItem == convertedItemId){

                            String itemName  = (String) itemOBJECT.get("name");
                            String itemDesc  = (String) itemOBJECT.get("desc");
                            
                            itemsInRoom.add( new Item(itemID, itemName, itemDesc) ); 

                            
                            tempRoom.setRoomItems(itemsInRoom);
                            
                        }

                       
                                     
                     }

                }
                
            } catch(NullPointerException e) {
             
            } 

            HashMap<String,Long> hashMapofEntrancesInRoom = new HashMap<String,Long>();
            ArrayList<Entrance> entrances = new ArrayList<Entrance>();
            JSONArray entranceArray = (JSONArray) roomOBJECT.get("entrance");
            
                for (int j = 0; j <entranceArray.size(); j++) {
                    
                    JSONObject entranceObject = (JSONObject) entranceArray.get(j);
                    long entranceId  = (long) entranceObject.get("id");

                    String entranceDirection = (String) entranceObject.get("dir");

                    hashMapofEntrancesInRoom.put(entranceDirection,entranceId);

                   
                    entrances.add(new Entrance(entranceId, entranceDirection));

                    tempRoom.setHashEntrances(hashMapofEntrancesInRoom);
                    
                }

                

                listOfRooms.add(tempRoom);

    }// end of roomList iterator
    this.rooms = listOfRooms;

}

    public void setStartRoom(ArrayList<Room> rooms){
        
        for(Room room: rooms){

        
            String trueorFalse = room.getStartRoom();
            
            if(trueorFalse.equals("true")){
                
                currentRoom = room;
                

            }
            

        }
        

    }



    public  Room getStartRoom(){
        return this.currentRoom;
    }

    public Room returnRoom(){
        return this.testRoom;
    }

}