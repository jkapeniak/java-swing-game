package adventure;

public class InvalidCommandException extends Exception{

    /**
     * Serializable Id for this class.
     */
    private static final long serialVersionUID = 1L;

    /**
     *
     */
    
    /**
     * Prints out the invalid command message.
     */
    public InvalidCommandException() {
        super("\nThe command you entered was invalid.\n");
    }
    
    
}
