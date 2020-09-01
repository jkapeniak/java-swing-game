package adventure;



import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import java.util.ArrayList;
import java.io.InputStream;
import java.io.InputStreamReader;
 

public class Game{

    private boolean newGame = true;
   


    public static void main(String[] args){

                                boolean initialCommand = false;
                                Adventure newAdventure;
                                boolean loadJSON = false;
                                boolean loadAdventure = false;

                                Game theGame = new Game();  

                                GUI gameGUI = new GUI();
                            

                                while(initialCommand != true){
                                  

                                      
                                   loadJSON =  gameGUI.getLoadJSON();
                                   loadAdventure = gameGUI.getLoadAdventure();

                                   System.out.println();

                                      
                                    
                                  if(loadJSON==true || loadAdventure==true ){
                                      

                                      initialCommand = true;
                                  }


                                    
                                }

                                if(loadJSON){
                                    JSONObject obj = theGame.defaultGameLoader(gameGUI.getJSONFile() );
                                    newAdventure = theGame.generateAdventure(obj);
                                    theGame.playtheGame(newAdventure, gameGUI);
                                } else if(loadAdventure){
                                    Adventure gotItBack = null;
            

                                        try (ObjectInputStream in = 
                                        new ObjectInputStream(new FileInputStream(gameGUI.
                                        getAdventureFile())); ){ 

                                            theGame.setNewGame();
                                    
                                            gotItBack = (Adventure)in.readObject(); 
                                            
                                            
                                            newAdventure = gotItBack;

                                                    theGame.playtheGame(newAdventure,gameGUI);
                                                        
                                                    } catch(IOException ex) { 
                                                        
                                                    }  catch(ClassNotFoundException ex) { 
                                                        
                                                    } 

                                              }
                                }

                               
                            
                                

    

    public Game(){

    }
    /**
     * takes a JSONObject representing the adventure file
     * and using that obj
     * creates a new adventure and returns
     * @param obj json object representing
     * the adventure
     * @return adventure object representing
     * all the components of the adventure
     */
    public Adventure generateAdventure(JSONObject obj) {
        Adventure adventure = new Adventure(obj);
        //System.out.println("GOT EM");
        return adventure;
    }


    /**
     * sets the value of newGame to
     * false used in determing whether to
     * start in the start room
     * or a room from the previous adventure
     */
    public void setNewGame(){
        this.newGame=false;
    }

    /**
     * gets the value of whether the game is new
     * or not
     * @return newGame boolean
     */
    public boolean getNewGame(){
        return this.newGame;
    }

    


    /**
     * Depending on command line arguments this method 
     * determines whether a previously started game 
     * is loaded or if a new adventure is started with 
     * a json file name of choice
     * @param args The command line arguments.
     * @param theGame The Game object.
     */

    public void loadOrStartNewAdventure(String[] args, Game theGame){
        
        Adventure newAdventure;
        if(args[0].equals("-l")){


            
            
            Adventure gotItBack = null;
            

            try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(args[1])); ){ 

                this.setNewGame();
        
                gotItBack = (Adventure)in.readObject(); 
                
                
                newAdventure = gotItBack;

               // theGame.playtheGame(newAdventure);
                
            } catch(IOException ex) { 
                
            }  catch(ClassNotFoundException ex) { 
                
            } 

            } else if(args[0].equals("-a") ){
                
                JSONObject obj = theGame.gameLoader(args[1]);
        
                newAdventure = new Adventure(obj);
        
              //  theGame.playtheGame(newAdventure);
        
            } 


    }


    /**
     * This method is the default game loader.
     * @param filename file to load
     * @return it returns the json object representing the adventure game.
     */

    public JSONObject defaultGameLoader(File filename){





       // String filename = "julius_adventure.json";
      //  File initialFile = new File(filename);
     // File intialFile = filename;
        JSONObject obj;
        
  
        try {
          InputStream inputStream = new FileInputStream(filename);
  
            obj = loadAdventureJson(inputStream);
  
            return obj;
  
  
        } catch(Exception e){
              System.out.println(e.getMessage());
        }
       


        return null;
    } 


    /**
     * This method is used for the user to input their own adventure file of choosing.
     * 
     * @param fileName Filename of the json adventure of choice.
     * @return It returns the parsed json file and returns a json object representing the adventure.
     * 
     */
    public JSONObject gameLoader(String fileName){

        
       JSONObject obj;
       
        obj = this.loadAdventureJson(fileName);
       


       return obj;
    } //end of gameloader

    /**
     * The method is used to load the default adventure game.
     * @param inputStream The inputStream parameter is used to load the json file.
     * @return The method returns the json object which represents the adventure game.
     */

    public static JSONObject loadAdventureJson(InputStream inputStream){ 

        //we need to instantiate a json parser so we can use it parse our json file
        JSONParser jsonParser = new JSONParser();

        JSONObject jsonObject; 



        try{

             jsonObject = (JSONObject)jsonParser.parse(new InputStreamReader(inputStream, "UTF-8"));

            

        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }  

        
         return jsonObject;   
        
    }

    /**
     * 
     * @param filename
     * @return j
     */

    public JSONObject loadAdventureJson(String filename){ 

        JSONParser jsonParser = new JSONParser();

        try (FileReader reader = new FileReader(filename)){
            
           
            JSONObject jsonObject = (JSONObject) jsonParser.parse(reader);

            
            
            return jsonObject;
            

           


            
 
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }  

        
        
        
    }


    /**
     * gets current room name
     * from the adventure object
     * @param exampleAdventureFile adventure object
     * @return returns current room name
     */
    public String currentRoomName(Adventure exampleAdventureFile){
        return exampleAdventureFile.getCurrentRoom().getName();
    }

    /**
     * returns current room description
     * @param exampleAdventureFile adventure object
     * @return returns current room description
     */
    public String currentRoomDescription(Adventure exampleAdventureFile){
        return exampleAdventureFile.getCurrentRoomDescription();
    }


    /**
     * This method is a return template for the current room the user is in the adventure game
     * it is called everytime the tried to or successfully enters a room.
     * @param exampleAdventureFile The method takes the adventure object and use its to access information about
     * the current progress of the game.
     * @return string representation of
     * items in the room
     */

    public String currentRoomDetails(Adventure exampleAdventureFile){



       // System.out.println("\n|"+exampleAdventureFile.getCurrentRoom().getName()+"|\n");

      //  System.out.println("\n"+exampleAdventureFile.getCurrentRoomDescription()+"\n");
            

            

        ArrayList<Item> itemsInTheRoom = exampleAdventureFile.getCurrentRoom().getItemsInRoom();

        
        if(itemsInTheRoom==null || itemsInTheRoom.isEmpty()){
            

         //   System.out.println("\nThe room does not have items in it.\n");
            return "\nThe room does not have items in it.\n";
        } else{
            String items = "The room contains a ";
             for(Item i: itemsInTheRoom){
                

 
                 items = items + i.getName() +",";

                
                

                
 
                     
             }

             return items;
 
        }

    }

    /**
     * Sets the start room in the adventure. 
     * @param exampleAdventureFile Takes the adventure object as a parameter.
     */
    public void setStartRoom(Adventure exampleAdventureFile){
        ArrayList<Room> listOfRooms = exampleAdventureFile.listAllRooms();
       
       for(Room i: listOfRooms){

      
        String trueorFalse = i.getStartRoom();
        
        if(trueorFalse.equals("true")){
            

            exampleAdventureFile.setCurrentRoom(i);

        }
        

       }
    }
    
   
    /**
     * This method taken the adventure object and plays the adventure game.
     * @param exampleAdventureFile The adventure object is used to gather 
     * @param gameGUI gui of game
     * information about the current state of the game.
     */
    public void playtheGame(Adventure exampleAdventureFile, GUI gameGUI){

        

        

        try {
            if(this.getNewGame()){
                setStartRoom( exampleAdventureFile);
            }
            
        } catch (Exception e) {
            System.out.println("ERROR in trying to set startRoom");
        }
      

       String checkForExitCommand = "";
       Command coomand;
       do {
           try {
            this.currentRoomDetails(exampleAdventureFile);


            gameGUI.setCurrentRoomName(currentRoomName(exampleAdventureFile));
            gameGUI.setPlayerInventory(exampleAdventureFile.getCurrentPlayer().
            returnStringRepresentationOfPlayerInventory());
            gameGUI.setCurrentRoomDescription( currentRoomDescription(exampleAdventureFile) );

            gameGUI.setCurrentRoomItems(currentRoomDetails( exampleAdventureFile));

           } catch (Exception e) {
               System.out.println("error in trying to print current room details");
           }

       

            Parser userInputParser = new Parser();
            
            try{

                boolean commandEntered = false;

                while(commandEntered == false){
                    //System.out.println("no command entered");

                    if(gameGUI.getSaveEntered()==true){
               
                        this.saveGame(exampleAdventureFile,gameGUI.getSaveGameName());
                        System.out.println("Game State Saved.");
      
                     gameGUI.setSaveToFalse();
                 }

                    commandEntered = gameGUI.getCommandEntered();
                }



              coomand =   userInputParser.getUserInput(gameGUI);
              gameGUI.setCommandEnteredtoFalse();


              
              checkForExitCommand = coomand.getActionWord();
           
                if(coomand.getNoun()==null){
                    singleCommandExecutor(coomand.getActionWord(), exampleAdventureFile);
                   
            
                } else {
                    
                    multiWordCommandExecutor(coomand.getActionWord(),coomand.getNoun(), exampleAdventureFile); 
                }

            } catch(InvalidCommandException e){
                System.out.println(e);
            }
        
            


       } while (!checkForExitCommand.equals("QUIT"));

     
       
       

       
    }

    /**
     * the method
     * takes the adventure object
     * representing the game and 
     * saves the current progress
     * of the game via serializaiton
     * @param exampleAdventureFile takes the current adventure
     * @param saveGameName name of game to save
     * progress as a parameter
     */
    public void saveGame(Adventure exampleAdventureFile, String saveGameName){

     //   System.out.println("Enter the name of the game save.");
    //    Scanner getFileName = new Scanner(System.in);


     //  String filename = getFileName.nextLine();
        
        exampleAdventureFile.getCurrentPlayer().setSaveGameName(saveGameName);

        try{
            
            FileOutputStream outPutStream = new FileOutputStream(saveGameName); 
            ObjectOutputStream outPutDest = new ObjectOutputStream(outPutStream); 
            outPutDest.writeObject(exampleAdventureFile); 

            outPutDest.close(); 
            outPutStream.close(); 
        
            


        } catch(IOException ex){
         

        }

    }
    
    /**
     * This method is used when the user is trying to execute single word commands.
     * @param action The action paramter describes the single action 
     * the user is trying to execute.
     * @param exampleAdventureFile The adventure object is used to 
     * provide and update the current state of the adventure.
     */

    public void singleCommandExecutor(String action, Adventure exampleAdventureFile){

        if(action.equals("LOOK")){
            
            System.out.println("\n"+exampleAdventureFile.getCurrentRoom().getLongDescription()+"\n");
            

        }

        if(action.equals("INVENTORY")){
            
            
             exampleAdventureFile.getCurrentPlayer().printPlayerInventory();
           
            
              
            

        }

        



    }

    /**
     * This method executes the go command
     * of the adventure takes a noun representing 
     * the direction the user is attempting to go
     * @param exampleAdventureFile adventure object
     * and its current progress
     * @param noun direction noun
     */
    public void goCommand(Adventure exampleAdventureFile, String noun){
            Room temporaryRoom = exampleAdventureFile.getCurrentRoom();
            temporaryRoom.setRoomsforNavigation(exampleAdventureFile.listAllRooms());
            char dir = noun.charAt(0);
            System.out.println("");
            String directionRequest = Character.toString(dir);
            Room test =  temporaryRoom.getConnectedRoom(directionRequest);
            exampleAdventureFile.setCurrentRoom(test);
    }
    /**
     * This command executor executes the eat command, the noun passed
     * needs to have the edible interface otherwise we can eat it,
     * so if it is edible we search through the players intventory
     * and check if the item exits
     * of it exists and has the interface
     * we return some string, then remove the item from the inventory
     * within the edible interface class, then we must,
     * search through
     * @param exampleAdventureFile adventure object
     * @param noun item to eat noun
     */
    public void eatCommandExecutor(String noun, Adventure exampleAdventureFile){
        

          
        for(Item item: exampleAdventureFile.getCurrentPlayer().getInventory()){
            if(item.getName().equals(noun)  && (item instanceof Edible) ){
                System.out.println("FOUND Matching Item and it is edible");

               System.out.println(((Edible) item).eat()); 
                
               exampleAdventureFile.getCurrentPlayer().removeItemFromInventory(item);
               return;

            // TODO break this out into the two below options   
            } 
        }

        System.out.println("This item either not in the inventory or is not edible");
        return;



    }
    /**
     * executes toss command
     * @param exampleAdventureFile adventure object
     * @param noun item to toss
     * 
     */
    public void tossCommandExecutor(String noun, Adventure exampleAdventureFile){
        for(Item item: exampleAdventureFile.getCurrentPlayer().getInventory()){
            if(item.getName().equals(noun) && (item instanceof Edible) && (item instanceof Tossable) ){
                System.out.println("FOUND Matching Item and it is edible and tossable");

                exampleAdventureFile.getCurrentRoom().addItemToRoom(item);

                exampleAdventureFile.getCurrentPlayer().removeItemFromInventory(item);
                
               //exampleAdventureFile.getCurrentPlayer().removeItemFromInventory(item);
               return;

            // TODO break this out into the two below options   
            //CALL INTERFACE
            } 
        }
    }

    /**
     * executes wear command
     * @param exampleAdventureFile adventure object
     * @param noun item to wear
     * 
     */
    public void wearCommandExecutor(String noun, Adventure exampleAdventureFile){

        for(Item item: exampleAdventureFile.getCurrentPlayer().getInventory()){
            if(item.getName().equals(noun) && (item instanceof Wearable) ){

                
              System.out.println(((Wearable) item).wear());  

              
               return;

            // TODO break this out into the two below options   , when an item is taken off need to set it as NOT ON
            } 
        }



    }


    /**
     * executes read command
     * @param exampleAdventureFile adventure object
     * @param noun item to read
     * 
     */
    public void readCommandExecutor(String noun, Adventure exampleAdventureFile){

        for(Item item: exampleAdventureFile.getCurrentPlayer().getInventory()){
            if(item.getName().equals(noun) && (item instanceof Readable) ){

                
              System.out.println(((Readable) item).read());  

              
               return;

            // TODO break this out into the two below options   , when an item is taken off need to set it as NOT ON
            } 
        }



    }




    /**
     * This method is used to execute multi word commands.
     * @param action The action the user wishes the execute.
     * @param noun  The noun represents the noun associated with the action the user wishes to execute.
     * @param exampleAdventureFile The adventure object is used to inform and is updated on the state of the adventure.
     */
    public void multiWordCommandExecutor(String action, String noun, Adventure exampleAdventureFile){

        if(action.equals("toss")){
            tossCommandExecutor(noun,exampleAdventureFile);
        }

        if(action.equals("eat")){
            eatCommandExecutor(noun,exampleAdventureFile);
        }

        if(action.equals("wear")){
            wearCommandExecutor(noun,exampleAdventureFile);

        }
        if(action.equals("read")){
            readCommandExecutor(noun,exampleAdventureFile);

        }



        if(action.equals("take")){

            try{
                for(Item item: exampleAdventureFile.getCurrentRoom().getItemsInRoom()){
                    if( item.getName().equals(noun) ){

                        System.out.println("Trig 1");
exampleAdventureFile.getCurrentPlayer().addItemToPlayerInventory(exampleAdventureFile.getCurrentRoom()
.returnItemFromRoom(noun));
                       
                        exampleAdventureFile.getCurrentRoom().removeItemfromList(noun);
                        
                        return;
                    }
    
                }

            } catch(Exception e){
                System.out.println("\nThere no item named "+noun+" in this room\n");
                return;
            }
            


            System.out.println("\nThere no item named "+noun+" in this room\n");

            return;
   
        } 
        
    
        if(action.equals("go")){

            
            goCommand( exampleAdventureFile,noun);

   
        }



        final int check = 6;

        if(action.equals("LOOK")&& action.length() >=check ){
           

            ArrayList<Item> itemsInRoom =  exampleAdventureFile.getCurrentRoom().getItemsInRoom();

            if(itemsInRoom==null){
                

                System.out.println("\nThis room has no "+ noun +  " in it.\n");
                

            } else{

                int flag = 0;
                for(Item item: itemsInRoom){
                    if(item.getName().equals(noun)){
                        System.out.println(item.getLongDescription());


                        
                        flag = 1;
                    }
                }

                if(flag == 0){
                    

                    System.out.println("\nThis room has no "+ noun +  " in it.\n");
                    

                }
                

            }

            



        }

    }





    /**
     * This toString method for the game class
     * provides some slighlty more informative
     * information than the default
     * toString method
     */
    @Override
   public String toString() {
      return "\nThis is a game object\n";
   }



}

