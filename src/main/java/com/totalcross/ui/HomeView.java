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


    private String getDateTime(){
        // Do something to get date and time
        return "22 April 2020 15:36";
    }


    private String getMessage(){
        // Do something to get the message
        return "What do you want to do today?";
    }


    public void initUI() {

        /**
         * Criar os containers
         */
        add(cEmptyArea = new Container(), MaterialConstants.MC1W , MaterialConstants.MC35H , FILL-MaterialConstants.MC1W , FILL-MaterialConstants.MC1W);
        cEmptyArea.setBackColor(Colors.COLOR_LIGHT_GRAY);

        add(cTopIcons = new Container(), MaterialConstants.MC5W , MaterialConstants.MC8H , FILL-MaterialConstants.MC50W , MaterialConstants.MC8H);
        cTopIcons.setBackColor(Colors.COLOR_DEEP_BLUE);

        add(cDateTime = new Container(), AFTER + MaterialConstants.MC5W , MaterialConstants.MC8H , FILL-MaterialConstants.MC5W , MaterialConstants.MC8H);
        cDateTime.setBackColor(Colors.COLOR_DEEP_BLUE);

        add(cMain = new Container(), MaterialConstants.MC5W , AFTER + MaterialConstants.MC7H , FILL-MaterialConstants.MC5W , MaterialConstants.MC50H);
        cMain.setBorderStyle(BORDER_ROUNDED);
        cMain.setBackForeColors(Colors.COLOR_WHITE, Colors.COLOR_WHITE);

        add(cMessage = new Container(), MaterialConstants.MC5W , AFTER+MaterialConstants.MC4H , FILL-MaterialConstants.MC30W , MaterialConstants.MC15H);
        cMessage.setBorderStyle(BORDER_ROUNDED);
        cMessage.setBackForeColors(Colors.COLOR_WHITE, Colors.COLOR_WHITE);

        add(cColorLevel = new Container(), AFTER + MaterialConstants.MC5W , cMessage.getY() , FILL-MaterialConstants.MC5W , MaterialConstants.MC15H);
        cColorLevel.setBorderStyle(BORDER_ROUNDED);
        cColorLevel.setBackForeColors(Colors.COLOR_WHITE, Colors.COLOR_WHITE);

        /**
         * Cria e adiciona os ImageControls e redimensiona os mesmos to fit no TopIcons container
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

        /**
         * Cria e adiciona a label de data e hora
         */
        lDateTime = new Label(getDateTime());
        // Faz com que a fonte ajuste seu tamanho para ocupar 80% do comprimento do container
        for (int i=1; lDateTime.getPreferredWidth() < cDateTime.getWidth()*0.8; i++){
            lDateTime.setFont(Font.getFont(i++));
        }
        lDateTime.setForeColor(Colors.COLOR_WHITE);
        cDateTime.add(lDateTime, CENTER, CENTER);

        /**
         *  Add os botões principais
         */

        int verticalEmptySpace = (int) (cMain.getWidth()*0.04);
        int buttonWidth = (int) (cMain.getWidth()*0.2);

        int horizontalEmptySpace = (int) (cMain.getHeight()*0.1);
        int buttonHeight = (int) (cMain.getHeight()*0.8);

        cbCopy = new CardButton(buttonWidth, buttonHeight, Colors.COLOR_PURPLE, Colors.COLOR_WHITE);
        cMain.add(cbCopy, verticalEmptySpace, horizontalEmptySpace, buttonWidth, buttonHeight);
        cbCopy.setIconImage(Images.iCopyIcon);
        cbCopy.setCardLabel("COPY");

        cbCopy.addPressListener(new PressListener() {
            @Override
            public void controlPressed(ControlEvent e) {
                System.out.println("COPY EVENT");
            }
        });

        cbScan  = new CardButton(buttonWidth, buttonHeight, Colors.COLOR_BLUE, Colors.COLOR_WHITE);
        cMain.add(cbScan, AFTER+verticalEmptySpace, horizontalEmptySpace, buttonWidth, buttonHeight);
        cbScan.setIconImage(Images.iScannerIcon);
        cbScan.setCardLabel("SCAN");

        cbScan.addPressListener(new PressListener() {
            @Override
            public void controlPressed(ControlEvent e) {
                System.out.println("SCAN EVENT");
            }
        });

        cbPrint  = new CardButton(buttonWidth, buttonHeight, Colors.COLOR_GREEN, Colors.COLOR_WHITE);
        cMain.add(cbPrint, AFTER+verticalEmptySpace, horizontalEmptySpace, buttonWidth, buttonHeight);
        cbPrint.setIconImage(Images.iPrinterIcon);
        cbPrint.setCardLabel("PRINT");

        cbPrint.addPressListener(new PressListener() {
            @Override
            public void controlPressed(ControlEvent e) {
                System.out.println("PRINT EVENT");
            }
        });

        cbSetup  = new CardButton(buttonWidth, buttonHeight, Colors.COLOR_RED, Colors.COLOR_WHITE);
        cMain.add(cbSetup, AFTER+verticalEmptySpace, horizontalEmptySpace, buttonWidth, buttonHeight);
        cbSetup.setIconImage(Images.iSetupIcon);
        cbSetup.setCardLabel("SETUP");

        cbSetup.addPressListener(new PressListener() {
            @Override
            public void controlPressed(ControlEvent e) {
                System.out.println("SETUP EVENT");
            }
        });


        /**
         * Cria e adiciona a label de mensagem
         */
        lMessage = new Label(getMessage());
        // Faz com que a fonte ajuste seu tamanho para ocupar 60% do comprimento do container
        for (int i=1; lMessage.getPreferredWidth() < cMessage.getWidth()*0.6; i++){
            lMessage.setFont(Font.getFont(i++));
        }
        lMessage.setForeColor(Colors.COLOR_BLACK);
        cMessage.add(lMessage, CENTER, CENTER);

        /**
         * Checa o nivel da tinta
         */

        int verticalEmptySpace2 = (int) (cColorLevel.getWidth()*0.08);
        int fieldWidth = (int) (cColorLevel.getWidth()*0.15);

        int horizontalEmptySpace2 = (int) (cColorLevel.getHeight()*0.1);
        int fieldHeight = (int) (cColorLevel.getHeight()*0.8);

        int blueLevel = fieldHeight - (int) (fieldHeight*0.3);
        int purbleLevel = fieldHeight - (int) (fieldHeight*0.7);
        int yellowLevel = fieldHeight - (int) (fieldHeight*0.2);
        int blackLevel = fieldHeight - (int) (fieldHeight*0.8);

        cBlueLevel = new Container();
        cBlueLevel.setBackColor(Colors.COLOR_LIGHT_BLUE);
        cBlueLevel.setBorderStyle(BORDER_ROUNDED);
        cColorLevel.add(cBlueLevel, verticalEmptySpace2, horizontalEmptySpace2 + (int) (fieldHeight*0.3), fieldWidth, blueLevel);

        cPurpleLevel = new Container();
        cPurpleLevel.setBackColor(Colors.COLOR_PURPLE);
        cPurpleLevel.setBorderStyle(BORDER_ROUNDED);
        cColorLevel.add(cPurpleLevel, AFTER+verticalEmptySpace2, horizontalEmptySpace2 + (int) (fieldHeight*0.7), fieldWidth, purbleLevel);

        cYellowLevel = new Container();
        cYellowLevel.setBackColor(Colors.COLOR_YELLOW);
        cYellowLevel.setBorderStyle(BORDER_ROUNDED);
        cColorLevel.add(cYellowLevel, AFTER+verticalEmptySpace2, horizontalEmptySpace2 + (int) (fieldHeight*0.2), fieldWidth, yellowLevel);

        cBlackLevel = new Container();
        cBlackLevel.setBackColor(Colors.COLOR_BLACK);
        cBlackLevel.setBorderStyle(BORDER_ROUNDED);
        cColorLevel.add(cBlackLevel, AFTER+verticalEmptySpace2, horizontalEmptySpace2 + (int) (fieldHeight*0.8), fieldWidth,  blackLevel);

    }

}