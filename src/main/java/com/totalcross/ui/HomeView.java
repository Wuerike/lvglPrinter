package com.totalcross.ui;

import com.totalcross.ui.components.CardButton;
import totalcross.ui.*;
import com.totalcross.util.*;
import totalcross.ui.dialog.MessageBox;
import totalcross.ui.event.ControlEvent;
import totalcross.ui.event.PressListener;
import totalcross.ui.font.Font;

public class HomeView extends Container {
    private Label lDateTime, lMessage;
    private ImageControl icWifiIcon, icPhoneIcon, icLeafIcon, icPcPhoneIcon;
    private CardButton cbCopy, cbScan, cbPrint, cbSetup;
    private Container cEmptyArea, cTopIcons, cDateTime, cMain, cMessage, cColorLevel;
    private Container cBlueLevel, cPurpleLevel, cYellowLevel, cBlackLevel;


    /**
     * Method to return date and time as a string
     */
    private String getDateTime(){
        // Do something to get date and time
        return "22 April 2020 15:36";
    }

    /**
     *  Method that runs this GUI's home screen
     */
    public void initUI() {

        /**
         * Create the containers
         * Add then using relative positioning and percentage dimensions
         * Set each container background color and if it has a rounded border
         */
        add(cEmptyArea = new Container(),
                MaterialConstants.MC1W,
                MaterialConstants.MC35H,
                FILL-MaterialConstants.MC1W,
                FILL-MaterialConstants.MC1W);
        cEmptyArea.setBackColor(Colors.COLOR_LIGHT_GRAY);

        add(cTopIcons = new Container(),
                MaterialConstants.MC5W,
                MaterialConstants.MC8H,
                FILL-MaterialConstants.MC50W,
                MaterialConstants.MC8H);
        cTopIcons.setBackColor(Colors.COLOR_DEEP_BLUE);

        add(cDateTime = new Container(),
                AFTER + MaterialConstants.MC5W,
                MaterialConstants.MC8H,
                FILL-MaterialConstants.MC5W,
                MaterialConstants.MC8H);
        cDateTime.setBackColor(Colors.COLOR_DEEP_BLUE);

        add(cMain = new Container(),
                MaterialConstants.MC5W,
                AFTER + MaterialConstants.MC7H,
                FILL-MaterialConstants.MC5W,
                MaterialConstants.MC50H);
        cMain.setBorderStyle(BORDER_ROUNDED);
        cMain.setBackForeColors(Colors.COLOR_WHITE, Colors.COLOR_WHITE);

        add(cMessage = new Container(),
                MaterialConstants.MC5W,
                AFTER+MaterialConstants.MC4H,
                FILL-MaterialConstants.MC30W,
                MaterialConstants.MC15H);
        cMessage.setBorderStyle(BORDER_ROUNDED);
        cMessage.setBackForeColors(Colors.COLOR_WHITE, Colors.COLOR_WHITE);

        add(cColorLevel = new Container(),
                AFTER + MaterialConstants.MC5W,
                cMessage.getY(),
                FILL-MaterialConstants.MC5W,
                MaterialConstants.MC15H);
        cColorLevel.setBorderStyle(BORDER_ROUNDED);
        cColorLevel.setBackForeColors(Colors.COLOR_WHITE, Colors.COLOR_WHITE);

        //-----------------------------

        /**
         * Create ImageController for the icons
         * Scale then by fixing its height as the same height as the container they'll be added
         * Catch any exception that may happen when scaling
         * Add them to the cTopIcons container using relative positioning and percentage dimensions
         */
        try {
            icWifiIcon = new ImageControl(Images.iWifiIcon.smoothScaledFixedAspectRatio(cTopIcons.getHeight(), true));
            icPhoneIcon = new ImageControl(Images.iPhoneIcon.smoothScaledFixedAspectRatio(cTopIcons.getHeight(), true));
            icLeafIcon = new ImageControl(Images.iLeafIcon.smoothScaledFixedAspectRatio(cTopIcons.getHeight(), true));
            icPcPhoneIcon = new ImageControl(Images.iPcPhoneIcon.smoothScaledFixedAspectRatio(cTopIcons.getHeight(), true));
        } catch (Exception e) {
            MessageBox.showException(e, true);
        }

        cTopIcons.add(icWifiIcon, 0, 0);
        cTopIcons.add(icPhoneIcon, AFTER + MaterialConstants.MC5W, 0);
        cTopIcons.add(icLeafIcon, AFTER + MaterialConstants.MC5W, 0);
        cTopIcons.add(icPcPhoneIcon, AFTER + MaterialConstants.MC5W, 0);

        //-----------------------------

        /**
         * Create a label using a method that returns the desired string
         * It uses a for loop to find the bigger font size while keeping its witdh at 80% of the container it'll be
         * Add it at the center of cDateTime container
         */
        lDateTime = new Label(getDateTime());

        for (int i=1; lDateTime.getPreferredWidth() < cDateTime.getWidth()*0.8; i++){
            lDateTime.setFont(Font.getFont(i++));
        }

        lDateTime.setForeColor(Colors.COLOR_WHITE);
        cDateTime.add(lDateTime, CENTER, CENTER);

        //-----------------------------

        /**
         * Variables used to set the controllers inside de cMain container using percentage
         * The space between controllers will be 4% percent of the container's width
         * The controllers width will be 20% percent of the container's width
         * The height will be 80% of the container's height
         */
        int verticalEmptySpace = (int) (cMain.getWidth()*0.04);
        int buttonWidth = (int) (cMain.getWidth()*0.2);
        int buttonHeight = (int) (cMain.getHeight()*0.8);

        /**
         * Create the CardButton controllers with de calculated buttonWidth and buttonHeight
         * Add it to the cMain container using the calculated dimensions and relative positions
         * Set it's image icon and it's label text
         */
        cbCopy = new CardButton(buttonWidth, buttonHeight, Colors.COLOR_PURPLE, Colors.COLOR_WHITE);
        cMain.add(cbCopy, verticalEmptySpace, CENTER, buttonWidth, buttonHeight);
        cbCopy.setIconImage(Images.iCopyIcon);
        cbCopy.setCardLabel("COPY");

        // Listener that get the press events at the CardButton controller
        cbCopy.addPressListener(new PressListener() {
            @Override
            // When a event happen, it prints something in the console
            public void controlPressed(ControlEvent e) {
                System.out.println("COPY EVENT");
            }
        });

        cbScan  = new CardButton(buttonWidth, buttonHeight, Colors.COLOR_BLUE, Colors.COLOR_WHITE);
        cMain.add(cbScan, AFTER+verticalEmptySpace, CENTER, buttonWidth, buttonHeight);
        cbScan.setIconImage(Images.iScannerIcon);
        cbScan.setCardLabel("SCAN");

        cbScan.addPressListener(new PressListener() {
            @Override
            public void controlPressed(ControlEvent e) {
                System.out.println("SCAN EVENT");
            }
        });

        cbPrint  = new CardButton(buttonWidth, buttonHeight, Colors.COLOR_GREEN, Colors.COLOR_WHITE);
        cMain.add(cbPrint, AFTER+verticalEmptySpace, CENTER, buttonWidth, buttonHeight);
        cbPrint.setIconImage(Images.iPrinterIcon);
        cbPrint.setCardLabel("PRINT");

        cbPrint.addPressListener(new PressListener() {
            @Override
            public void controlPressed(ControlEvent e) {
                System.out.println("PRINT EVENT");
            }
        });

        cbSetup  = new CardButton(buttonWidth, buttonHeight, Colors.COLOR_RED, Colors.COLOR_WHITE);
        cMain.add(cbSetup, AFTER+verticalEmptySpace, CENTER, buttonWidth, buttonHeight);
        cbSetup.setIconImage(Images.iSetupIcon);
        cbSetup.setCardLabel("SETUP");

        cbSetup.addPressListener(new PressListener() {
            @Override
            public void controlPressed(ControlEvent e) {
                System.out.println("SETUP EVENT");
            }
        });

        //-----------------------------

        // Create the Message label
        lMessage = new Label("What do you want to do today?");

        // Looks for the bigger font size it can have while being up to 60% of its container
        for (int i=1; lMessage.getPreferredWidth() < cMessage.getWidth()*0.6; i++){
            lMessage.setFont(Font.getFont(i++));
        }

        // Set it's color and add it at the center of its container
        lMessage.setForeColor(Colors.COLOR_BLACK);
        cMessage.add(lMessage, CENTER, CENTER);

        //-----------------------------

        /**
         * Variables used to set the controllers inside de cColorLevel container using percentage
         * The space between controllers will be 8% percent of the container's width
         * The controllers width will be 15% percent of the container's width
         */
        int horizontalEmptySpace2 = (int) (cColorLevel.getWidth()*0.08);
        int fieldWidth = (int) (cColorLevel.getWidth()*0.15);

        /**
         * Variables to define the level of each color by using the height as indicator
         * Multiply the height for fixed values
         * Could improve to get the multipliers through a function that in fact checks the paint level
         */
        int blueLevel = (int) (cColorLevel.getHeight() * 1);
        int purbleLevel = (int) (cColorLevel.getHeight() * 0.7);
        int yellowLevel = (int) (cColorLevel.getHeight() * 0.2);
        int blackLevel = (int) (cColorLevel.getHeight() * 0.8);

        /**
         * Create the indicators as containers
         * Set each container background color and a rounded border
         * Add then using relative positioning and percentage dimensions
         */
        cBlueLevel = new Container();
        cBlueLevel.setBackColor(Colors.COLOR_LIGHT_BLUE);
        cBlueLevel.setBorderStyle(BORDER_ROUNDED);
        cColorLevel.add(cBlueLevel, horizontalEmptySpace2, BOTTOM, fieldWidth, blueLevel);

        cPurpleLevel = new Container();
        cPurpleLevel.setBackColor(Colors.COLOR_PURPLE);
        cPurpleLevel.setBorderStyle(BORDER_ROUNDED);
        cColorLevel.add(cPurpleLevel, AFTER+horizontalEmptySpace2, BOTTOM, fieldWidth, purbleLevel);

        cYellowLevel = new Container();
        cYellowLevel.setBackColor(Colors.COLOR_YELLOW);
        cYellowLevel.setBorderStyle(BORDER_ROUNDED);
        cColorLevel.add(cYellowLevel, AFTER+horizontalEmptySpace2, BOTTOM, fieldWidth, yellowLevel);

        cBlackLevel = new Container();
        cBlackLevel.setBackColor(Colors.COLOR_BLACK);
        cBlackLevel.setBorderStyle(BORDER_ROUNDED);
        cColorLevel.add(cBlackLevel, AFTER+horizontalEmptySpace2, BOTTOM, fieldWidth,  blackLevel);

    }
}