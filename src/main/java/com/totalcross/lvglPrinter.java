package com.totalcross;

import com.totalcross.ui.HomeView;
import com.totalcross.util.Colors;
import com.totalcross.util.Images;
import totalcross.ui.MainWindow;
import totalcross.sys.Settings;

public class lvglPrinter extends MainWindow {

    public lvglPrinter() {
        // Set the background color
        setBackColor(Colors.COLOR_DEEP_BLUE);
    }

    @Override
    public void initUI() {
        //Loads to the memory the images used at the home screen
        Images.loadImages();

        //Swap to HomeView class
        swap(new HomeView());
    }
}
