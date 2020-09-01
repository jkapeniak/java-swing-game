package adventure;

import java.io.File;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;




public class GUI extends JFrame {
    private GridBagConstraints layoutConst = null;
    private JTextField playerInventory;
    private JTextField roomNameField;
    private JTextField roomDescriptionField;
    private JTextField roomItemsField;
    private JTextField playerNameField;


    private JTextField commandField;
    private JTextField saveGameName;

    private File f = null;
    private File adventureFile = null;
    private Boolean loadJSON = false;
    private Boolean loadAdventure = false;
    private Boolean saveAdventure = false;
    private Boolean commandEntered = false;
    private Boolean saveGameEntered = false;
    /**
     *
     */
    private static final long serialVersionUID = 1068175853247326063L;

    public GUI() {
        setTitle("Gryphon Adventure Game");

        final int dimension = 2000;

        setSize(dimension, dimension);

        setLayout(new GridBagLayout());

        // creating new panel for player inventory

        JPanel inventoryPanel = new JPanel();
        inventoryPanel.setLayout(new FlowLayout());
        JLabel inventoryLabel = new JLabel("|Player Inventory|");

        final int length = 50;
        playerInventory = new JTextField(length);
        playerInventory.setEditable(true);
        // playerInventory.setText("PlayerInventory is empty");

        inventoryPanel.add(inventoryLabel);
        inventoryPanel.add(playerInventory);

        layoutConst = new GridBagConstraints();

        layoutConst.gridx = 0;
        layoutConst.gridy = 2;

        final int inset = 10;

        layoutConst.insets = new Insets(inset, inset, inset, inset);

        add(inventoryPanel, layoutConst);

        // roomName panel

        JPanel roomNamePanel = new JPanel();
        roomNamePanel.setLayout(new FlowLayout());
        JLabel roomNameLabel = new JLabel("Room Name:");
        final int fieldLength = 15;

        roomNameField = new JTextField(fieldLength);
        roomNameField.setEditable(true);

        roomNamePanel.add(roomNameLabel);
        roomNamePanel.add(roomNameField);

        layoutConst = new GridBagConstraints();

        final int y = 3;
        layoutConst.gridx = 0;
        layoutConst.gridy = y;

        layoutConst.insets = new Insets(inset, inset, inset, inset);

        add(roomNamePanel, layoutConst);

        // roomDesc

        JPanel roomDescriptionPanel = new JPanel();
        roomDescriptionPanel.setLayout(new FlowLayout());
        JLabel roomDescriptionLabel = new JLabel("Room Description:");
        final int fieldLengthTwo = 30;
        roomDescriptionField = new JTextField(fieldLengthTwo);
        roomDescriptionField.setEditable(true);
        roomDescriptionPanel.add(roomDescriptionLabel);
        roomDescriptionPanel.add(roomDescriptionField);

        layoutConst = new GridBagConstraints();

        final int yTwo = 4;
        layoutConst.gridx = 0;
        layoutConst.gridy = yTwo;

        layoutConst.insets = new Insets(inset, inset, inset, inset);

        add(roomDescriptionPanel, layoutConst);

        // roomItems

        JPanel roomItemsPanel = new JPanel();
        roomItemsPanel.setLayout(new FlowLayout());
        JLabel roomItemsLabel = new JLabel("Room Items:");
        roomItemsField = new JTextField(fieldLengthTwo);
        roomItemsPanel.add(roomItemsLabel);
        roomItemsPanel.add(roomItemsField);

        layoutConst = new GridBagConstraints();

        final int yThree = 5;
        layoutConst.gridx = 0;
        layoutConst.gridy = yThree;

        layoutConst.insets = new Insets(inset, inset, inset, inset);

        add(roomItemsPanel, layoutConst);

        //player name

        JPanel playerNamePanel = new JPanel();
        playerNamePanel.setLayout(new FlowLayout());
        JLabel playerNameLabel = new JLabel("Player Name:");
        playerNameField = new JTextField(fieldLengthTwo);
        playerNameField.setEditable(false);

        playerNamePanel.add(playerNameLabel);
        playerNamePanel.add(playerNameField);

        layoutConst = new GridBagConstraints();

        layoutConst.gridx = 0;
        layoutConst.gridy = 1;

        layoutConst.insets = new Insets(inset, inset, inset, inset);

        add(playerNamePanel, layoutConst);


        // creating new panel menu for game

        JPanel loadJSONorLoadSavedGameorSaveGamePanel = new JPanel();
        loadJSONorLoadSavedGameorSaveGamePanel.setLayout(new FlowLayout());

        JButton btn = new JButton("Load JSON File");
        JLabel saveGame = new JLabel("Enter Save Name then Press Save To Save Game Progress");
        final int fieldLengthThree = 10;
        saveGameName = new JTextField(fieldLengthThree);
        JButton saveButton = new JButton("Save");
        JButton loadButton = new JButton("Load Adventure File");
        JButton playerName = new JButton("Change Player Name");



        loadJSONorLoadSavedGameorSaveGamePanel.add(btn);
        loadJSONorLoadSavedGameorSaveGamePanel.add(saveGame);
        loadJSONorLoadSavedGameorSaveGamePanel.add(saveGameName);

        loadJSONorLoadSavedGameorSaveGamePanel.add(saveButton);
        loadJSONorLoadSavedGameorSaveGamePanel.add(loadButton);
        loadJSONorLoadSavedGameorSaveGamePanel.add(playerName);

        layoutConst = new GridBagConstraints();

        layoutConst.gridx = 0;
        layoutConst.gridy = 0;

        layoutConst.insets = new Insets(inset, inset, inset, inset);

        add(loadJSONorLoadSavedGameorSaveGamePanel, layoutConst);
        // COMMAND PANEL
        JPanel commandPanel = new JPanel();
        commandPanel.setLayout(new FlowLayout());
        JLabel commandPanelLabel = new JLabel("|Enter Commands Here|");

        commandPanel.add(commandPanelLabel);

        commandField = new JTextField(fieldLength);

        commandField.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {

                System.out.println("Enter pressed");
                commandField.setText(commandField.getText());
                commandEntered = true;

            }
        });

        commandPanel.add(commandField);

        layoutConst = new GridBagConstraints();

        final int yMe = 6;
        layoutConst.gridx = 0;
        layoutConst.gridy = yMe;

        layoutConst.insets = new Insets(inset, inset, inset, inset);

        add(commandPanel, layoutConst);

        btn.addActionListener(e -> {
            selectFile();
            loadJSON = true;
        });

        loadButton.addActionListener(e -> {
            selectAdventureFile();
            loadAdventure = true;
        });

        saveButton.addActionListener(e -> {
            saveGameName.setText(saveGameName.getText());

            saveGameEntered = true;
        });

        playerName.addActionListener(e -> {
            playerNameField.setText(playerNameField.getText());

            saveGameEntered = true;
        });

        setVisible(true);
    }

    /**
     * Sets player inventory string in GUI
     * @param currentPlayerInventory string representation of player inventory
     */
    public void setPlayerInventory(String currentPlayerInventory) {
        playerInventory.setText(currentPlayerInventory);
    }

    /**
     * sets current room name in gui
     * @param currentRoomName takes string of current room name
     */
    public void setCurrentRoomName(String currentRoomName) {
        roomNameField.setText(currentRoomName);
    }

    /**
     * sets current room description in GUI
     * @param currentRoomDescription string representation of room
     */
    public void setCurrentRoomDescription(String currentRoomDescription) {
        roomDescriptionField.setText(currentRoomDescription);
    }

    /**
     * sets room items in GUI
     * @param currentRoomItems string representation of room items
     */
    public void setCurrentRoomItems(String currentRoomItems) {
        roomItemsField.setText(currentRoomItems);
    }

    /**
     * sets player name in GUI
     * @param currentPlayerName sets name of current player
     */
    public void setCurrentPlayerName(String currentPlayerName) {
        playerNameField.setText(currentPlayerName);
    }


    /**
     * action listener that opens file dialog
     * to select json file
     */
    public void selectFile() {
        JFileChooser chooser = new JFileChooser();
        // optionally set chooser options ...
        if (chooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {

            f = chooser.getSelectedFile();

            // read and/or display the file somehow. ....
        } 
        return;
    }

    /**
     * action listener that opens
     * file dialog to selct
     * adventure object file
     */
    public void selectAdventureFile() {
        JFileChooser chooser = new JFileChooser();
        // optionally set chooser options ...
        if (chooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {

            adventureFile = chooser.getSelectedFile();

            // read and/or display the file somehow. ....
        } 
        return;
    }
    
    /**
     * returns name of game save
     * @return save game name
     */
    public String getSaveGameName() {
        return saveGameName.getText();
    }


    /**
     *returns whether
     *user has selected load a JSON file 
     *@return boolean of load or not to load json
     */
    public Boolean getLoadJSON() {
        return this.loadJSON;
    }

    /**
     * returns true or false
     * of whether user
     * has decided to load
     * a started adventure or not
     * @return load adventure boolean
     */
    public Boolean getLoadAdventure() {
        return this.loadAdventure;
    }


    /**
     * returns set JSON file
     * @return json file
     */
    public File getJSONFile() {
        return this.f;
    }

    /**
     * returns adventure file
     * @return adventure file
     */
    public File getAdventureFile() {
        return this.adventureFile;
    }

    
    public void action() {

    }

    /**
     * gets command entered into GUI
     * 
     * @return text of command
     * entered into GUI
     */
    public String returnCommandfromGUI() {
        return commandField.getText();

    }

    /**
     * Boolean of whether
     * user entered command or
     * not
     * @return command entered
     * or not
     */
    public Boolean getCommandEntered() {
        return this.commandEntered;
    }

    /**
     * sets command entered to false
     * after command is executed
     */
    public void setCommandEnteredtoFalse() {
        this.commandEntered = false;
    }

    /**
     * did user try
     * to save game yes or no
     * @return boolean of save
     * game or no
     */
    public Boolean getSaveEntered() {
        return this.saveGameEntered;
    }


    /**
     * sets save game to false
     * after user done saving game
     */
    public void setSaveToFalse() {
        this.saveGameEntered = false;
    }

}

