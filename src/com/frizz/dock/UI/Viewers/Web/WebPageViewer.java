package com.frizz.dock.UI.Viewers.Web;

import java.net.URL;
import javafx.embed.swing.JFXPanel;
import javafx.scene.web.WebView;

/**
 * @author Steven Frizell
 * @version 1.0.0a
 */
public class WebPageViewer extends JFXPanel {

    /**
     *
     */
    private final URL url;

    /**
     *
     */
    private WebView view;

    /**
     *
     * @param url
     */
    public WebPageViewer(URL url) {
        this.url = url;
    }
}
