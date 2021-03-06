/*
 * Created on 25.10.2005 by Karsten $Id$
 */
package com.pluralis.plucker.gui.action;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JOptionPane;

import com.pluralis.plucker.gui.Application;

public class HelpAbout extends AbstractAction {
  public static final String COPYRIGHT = "\u00a9 2006 PLURALIS AG. All Rights Reserved.";
  private Application application;  

  public HelpAbout(Application application) {
    this.application = application;
    putValue(Action.NAME, "About");
  }

  public void actionPerformed(ActionEvent event) {
    JOptionPane.showMessageDialog(application.rootWindow(),
        "<html><b>PREPare Requirements Engineering Tool</b><br><br>" +
        "Version: <b>" + application.getVersion() + "</b><br>"
        + COPYRIGHT + "<br>Written by Karsten Kroesch");
  }
}