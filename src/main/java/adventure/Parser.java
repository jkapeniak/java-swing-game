
package adventure;




public class Parser {

   
    private String action ="";
    private String noun = "";

    public Parser(){
        
    }
    /**
     * Takes a string and
     * sets the action
     * instance variable to that string
     * @param parserAction string action to set
     */
    public void setAction(String parserAction){
        this.action = parserAction;

    }
    /**
     * Returns the action string
     * @return string representing
     * the action
     */
    public String getAction(){
        return this.action;

    }

    /**
     * sets the noun string of
     * the parser object
     * @param parserNoun string
     * representation of noun
     * to set in parser object
     */
    public void setNoun(String parserNoun){
        this.noun = parserNoun;

    }
    /**
     * Returns the noun of the 
     * parser object
     * @return string representation
     * of the noun
     */
    public String getNoun(){
        return this.noun;
    }
    


    /**
     * Returns a string representation of all the valid commands in the command class.
     * @return String of valid commands.
     */
    public String allCommands(){
    
        return "\ngo " + "look " + "inventory "  +"quit " + "take\n";
    }



/**
 * Takes user input and calls the parseUserCommand method with the 
 * input which returns the corresponding command object if the command 
 * is valid otherwise it throws an exception.
 * @return Command object.
 * @throws InvalidCommandException
 * @param gameGUI game gui object
 */
 public Command getUserInput(GUI gameGUI) throws InvalidCommandException{

   // Scanner getUserInput = new Scanner(System.in);
  // String userEnteredCommand;

    

       // System.out.println("What would you like to do?");
       // userEnteredCommand = getUserInput.nextLine();

       String userEnteredCommand = gameGUI.returnCommandfromGUI();
        
         Command returnedCommand =   parseUserCommand(userEnteredCommand);

         return returnedCommand;
            
 }
/**
 * Takes the string input from getUserInput() and calls the command class to see if the inputted command exists.
 * @param userCommand String of user input.
 * @return Command object if the command is valid.
 * @throws InvalidCommandException
 */
 public Command parseUserCommand(String userCommand) throws InvalidCommandException{

            boolean hasAction = false;
            boolean hasNoun = false;
            
            String[] commandsToPass = userCommand.split(" ");

            

            
            if(commandsToPass.length >= 1){
                
                hasAction = true;
                this.action = commandsToPass[0];
            }

        

            if(commandsToPass.length > 1){
                
                
                hasNoun = true;
                
                for(int i=1;i<commandsToPass.length;i++){
                    
                    this.noun = this.noun + " " + commandsToPass[i];
                }

            }

            if(hasAction && hasNoun ){

                    

                    
                    this.noun = this.noun.trim();
                     return new Command(action,noun);

                     
                    
            } else if(!hasNoun  && hasAction ){
            

                    
                    

                     return new Command(action,null); 
                     
            }

            return null;



 }
/**
 * returns some useful information
 * about the parser object
 */
 @Override
 public String toString() {
    return "\nThis is a parser object\n";
 }



}
