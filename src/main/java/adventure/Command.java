package adventure;

import java.util.ArrayList;
import java.util.Arrays;



public class Command {
    private String action;
    private String noun;
    
    
    public static final ArrayList<String> ACTIONS = 
    new ArrayList<String>( Arrays.asList("GO", "LOOK", "INVENTORY", "QUIT", "SAVE", "TAKE")  ); 
    
    public static final ArrayList<String> ACTIONSLIST = 
    new ArrayList<String>( Arrays.asList("go", "look", "inventory", 
    "quit", "save", "take", "eat","toss", "wear", "read")  ); 
  /**
     * Create a command object with default values.  
     * both instance variables are set to null
     * 
     */

     /*
    public static ArrayList<String> returnStringOfActions(){

        return ACTIONS;
    }
    
    */

    public Command() throws InvalidCommandException {
        this(null, null);

        


    }



  /**
     * Create a command object given only an action.  this.noun is set to null
     *
     * @param command The first word of the command. 
     * 
     */
    public Command(String command) throws InvalidCommandException{

        //TODO validate the action word here and throw an exception if it isn't
        // a single-word action
        
        
        
        this(command, null);

        setAction( command);
        
    }

    /**
     * Create a command object given both an action and a noun
     *
     * @param command The first word of the command. 
     * @param what      The second word of the command.
     */
    public Command(String command, String what) throws InvalidCommandException{
        
        if(command==null || ( command==null && what==null)){
            throw new InvalidCommandException();
        } else if(command!=null && what==null){

            setAction( command);


            int flagForAction = 0;
            
            for(String string: ACTIONS){

                if(getActionWord().equals(string)){
                    
                    
                    flagForAction = 1;
                }
            }


            for(String string: ACTIONSLIST){

                if(getActionWord().equals(string)){
                    
                    
                    flagForAction = 1;
                }
            }

            if(flagForAction == 0){
                throw new InvalidCommandException();
            }




        }   else if(command!=null && what!=null ){
            setAction( command);
            setNoun(what);


            int flagForAction = 0;
            
            for(String string: ACTIONS){

                if(getActionWord().equals(string)){
                    
                    
                    flagForAction = 1;
                }
            }

            for(String string: ACTIONSLIST){

                if(getActionWord().equals(string)){
                    
                    
                    flagForAction = 1;
                }
            }

            if(flagForAction == 0){
                throw new InvalidCommandException();
            }


        }




        
        
        
                
         



        



    }

    /**
     * Takes in the command word
     * and sets the command string
     * of the command object to it
     * @param command command string user wishes to
     * execute
     */
    public void setAction(String command){
        this.action = command;

    }

    /**
     * Return the command word (the first word) of this command. If the
     * command was not understood, the result is null.
     *
     * @return The command word.
     */
    public String getActionWord() {
        return this.action;
    }
    /**
     * Takes in noun corresponding
     * to action and sets
     * noun string instance var
     * of command class
     * @param what takes in the
     * noun to set
     */
    public void setNoun(String what) {
        this.noun = what;
    }

    /**
     * @return The second word of this command. Returns null if there was no
     * second word.
     */
    public String getNoun() {
        return this.noun;
    }

    

    /**
     * @return true if the command has a second word.
     */
    public boolean hasSecondWord() {
        return (noun != null);
    }

    /**
     * To string method
     * returns command object
     * string and action
     */
    @Override
   public String toString() {
      return "\nAction: "+ this.getActionWord() +" Noun: "+this.getNoun()+"\n";
   }
}
