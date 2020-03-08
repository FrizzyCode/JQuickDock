package com.frizz.dock.UI.Viewers;

import com.alee.extended.window.WebPopOver;
import com.alee.laf.toolbar.WebToolBar;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import javafx.embed.swing.JFXPanel;
import javax.swing.*;

/**
 * @author Steven Frizell
 * @version 1.0.0a
 */
public class SourceViewer extends WebPopOver {

    /**
     *
     */
    private WebToolBar viewerToolBar;

    /**
     *
     */
    private JComponent viewerContainer;

    /**
     *
     * @param source
     */
    public SourceViewer(final String source) {
        if (isURL( source )) {

        } else if (isFile( source )) {

        }
    }

    /**
     *
     * @param source
     * @return
     */
    private boolean isURL(String source) {
        try {
            new URL( source );
        } catch ( MalformedURLException e ) {
            return false;
        }
        return true;
    }

    /**
     *
     * @param source
     * @return
     */
    private boolean isFile(String source) {
        return new File( source ).exists();
    }
}
