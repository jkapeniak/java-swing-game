package adventure;

import java.util.ArrayList;

public class Player implements java.io.Serializable{


 
    private static final long serialVersionUID = -5202137524177113605L;
    private ArrayList<Item> inventory;
    private String name ="";
    private Room currentRoom;
    private String saveGameName="";



    /**
     * Constructor initializing the player object. Creates a new ArrayList of player items.
     */
    public Player(){
        setInventory();

    }
    /**
     * Takes a name as a parameter and sets the name of the player with it.
     * @param playerName Name of the player.
     */
    public void setName(String playerName){
        this.name = playerName;

    }

    /**
     * Returns the name of the player.
     * @return String name.
     */
    public String getName(){
        return this.name;
    }
    /**
     * Takes a currentRoom Room object as a parameter 
     * and sets the current room the player is in instance variable in to it.
     * @param newCurrentRoom Takes a current Room object.
     */
    public void setCurrentRoom(Room newCurrentRoom){

        this.currentRoom = newCurrentRoom;

    }
    /**
     * Returns the current room the player is in.
     * @return Room object.
     */
    public Room getCurrentRoom(){
        return this.currentRoom;
    }

    /**
     * Sets name of game save.
     * @param gameSaveName Name of game save.
     */
    public void setSaveGameName(String gameSaveName){
        this.saveGameName = gameSaveName;

    }

    /**
     * Returns the name of the game save.
     * @return Game save name.
     */
    public String getSaveGameName(){
        return this.saveGameName;

    }


    

    /**
     * Returns the player inventory.
     * @return ArrayList player inventory.
     */
    public ArrayList<Item> getInventory(){
        return this.inventory;
    }

    /**
     * takes item to remove
     * and removes from
     * player inventory
     * @param itemToRemove item
     * object to remove from
     * the inventory
     */
    public void removeItemFromInventory(Item itemToRemove){
       this.getInventory().remove(itemToRemove);
       System.out.println("removed item from inventory");
    }

    /**
     * Initializes empty
     * player inventory
     */

    public void setInventory(){
        inventory = new ArrayList<Item>();
    }

    /**
     * Prints the items in the players inventory.
     */
    public void printPlayerInventory(){

        

        if(this.getInventory().isEmpty()){
            System.out.println("\nThere are no items in your inventory.\n");
            return;

        }


        int i = 1;
        for(Item playerInventoryItem: this.getInventory()){

            if (playerInventoryItem instanceof Clothing   && ((Clothing) playerInventoryItem).getClothingIsOn() ) {
                try {
                    
                    System.out.println("Player is wearing " +playerInventoryItem.getName());
                    i++;  
                } catch (Exception e) {
                    System.out.println("Is clothing but player is not wearing it");
                }
            } else {
                System.out.println("Inventory Item "+ i +  ": "+playerInventoryItem.getName());
                i++;
                
                
            }
            
            
        }

    }

    /**
     * returns string
     * representation of 
     * player inventory
     * @return player inventory
     */
    public String returnStringRepresentationOfPlayerInventory(){

        if(this.getInventory().isEmpty()){
           
            return"\nThere are no items in your inventory.\n";

        }


        String inventory =  "";
        for(Item playerInventoryItem: this.getInventory()){

            if (playerInventoryItem instanceof Clothing   && ((Clothing) playerInventoryItem).getClothingIsOn() ) {
                try {
                    
                  //  System.out.println(  "Player is wearing " +playerInventoryItem.getName()  );
                    inventory= inventory + "\nPlayer is wearing " +playerInventoryItem.getName()+"\n";
                } catch (Exception e) {
                  //  System.out.println("Is clothing but player is not wearing it");
                }
            } else {
                System.out.println("Inventory Item: "+playerInventoryItem.getName());
                
                inventory = inventory + "\nInventory Item: "+playerInventoryItem.getName()+"\n";
                
            }
            
            
        }

        return inventory;





    }







    /**
     * Takes an item object and adds it to the players inventory.
     * @param itemToAddToInventory Item object to add to player inventory.
     */
    public void addItemToPlayerInventory(Item itemToAddToInventory){
        inventory.add(itemToAddToInventory); 

        System.out.println("added item to inventory");

        System.out.println("TESING ADD");
        this.printPlayerInventory();
        System.out.println("ENDING TEST OF ADD");
    }

    /**
     * This toString method
     * provies the user with the players name
     *  
     */

    @Override
   public String toString() {
      return "\nplayer name: "+ this.getName()+"\n";
   }


}
