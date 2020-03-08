package com.frizz.dock.UI.Listeners;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.util.EventListener;

/**
 * @author Steven Frizell
 * @version 1.0.0a
 */
public interface IconClickListener extends ActionListener {

    void iconClicked(IconClickEvent ev);
}
