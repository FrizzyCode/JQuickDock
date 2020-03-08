package com.frizz.dock.Display;

import com.alee.laf.WebLookAndFeel;
import org.jnativehook.NativeHookException;


public class App {

    /**
     *
     */
    //private final Dock dock;

    /**
     *
     */
    public App() throws NativeHookException {
        WebLookAndFeel.initializeManagers();
        WebLookAndFeel.install();

        DockHandler handler = new DockHandler();
    }

    /**
     *
     * @param args
     */
    public static void main (String args[]) {
        try {
            new App();
        } catch (NativeHookException e) {
            e.printStackTrace();
        }
    }
}