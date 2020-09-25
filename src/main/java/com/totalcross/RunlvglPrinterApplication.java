package com.totalcross;

import totalcross.TotalCrossApplication;

// Starts the simulator at the defined resolution
public class RunlvglPrinterApplication {
    public static void main(String [] args) {
        TotalCrossApplication.run(lvglPrinter.class, "/scr", "896x504"); //1280x720
    }
}
