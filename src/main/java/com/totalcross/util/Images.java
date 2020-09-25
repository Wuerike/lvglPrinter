package com.totalcross.util;

import totalcross.ui.dialog.MessageBox;
import totalcross.ui.image.Image;

/**
 * Class that loads and stores the used images
 */
public class Images {
    // Declaring the variables.
    public static Image iCopyIcon, iLeafIcon, iPcPhoneIcon, iPhoneIcon;
    public static Image iPrinterIcon, iScannerIcon, iSetupIcon, iWifiIcon;

    // Method used to load the images from system
    public static void loadImages() {
        // Try to initialize the variables and catch the exception in case of some error
        try {
            iCopyIcon = new Image("images/copy.png");
            iLeafIcon = new Image("images/leaf.png");
            iPcPhoneIcon = new Image("images/pc_smartphone.png");
            iPhoneIcon = new Image("images/phone.png");
            iPrinterIcon = new Image("images/printer.png");
            iScannerIcon = new Image("images/scanner.png");
            iSetupIcon = new Image("images/settings.png");
            iWifiIcon = new Image("images/wifi.png");
        } catch (Exception e) {
            MessageBox.showException(e, true);
        }
    }
}


