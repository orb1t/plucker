package com.pluralis.plucker.gui;

import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.UIManager;

import org.apache.log4j.Logger;

import com.jgoodies.looks.plastic.PlasticXPLookAndFeel;
import com.pluralis.plucker.model.ClientOptions;
import com.pluralis.plucker.model.Project;
import com.pluralis.plucker.plugin.PluginRegistry;

import de.kroesch.util.GuiUtils;


public class TestApplication implements Application {

  private static final Logger log = Logger.getLogger(TestApplication.class);
  
  private JFrame rootWindow;
  
  private ClientOptions options = new ClientOptions();
  
  /**
   * Creates an invisible JFrame.
   */
  public TestApplication() {
    rootWindow = new JFrame("Test Application");
    rootWindow.setSize(1024,768);
    rootWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    GuiUtils.centerOnScreen(rootWindow);
    
    try {
      UIManager.setLookAndFeel(new PlasticXPLookAndFeel());
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  public PluginRegistry pluginRegistry() {
    // TODO Auto-generated method stub
    return null;
  }

  public JFrame rootWindow() {
    return rootWindow;
  }

  public void centerDialog(JDialog dialog) {
    GuiUtils.center(dialog, rootWindow);
  }

  public MenuBar menu() {
    // TODO Auto-generated method stub
    return null;
  }

  public JComponent navigationPanel() {
    // TODO Auto-generated method stub
    return null;
  }

  public void setTopic(ComponentCreator component, String title) {
    rootWindow.setVisible(true);
    rootWindow.getContentPane().add(component.create());
    rootWindow.pack();
  }

  public void status(String status) {
    // TODO Auto-generated method stub
  }

  public ClientOptions options() {
    return options;
  }

  public void userInfo(String message) {
    log.info(message);
  }

  public void userWarn(String message) {
    log.warn(message);
  }

  public void userError(String message) {
    log.error(message);
  }

  public Project getProject() {
    throw new UnsupportedOperationException("Not implemented.");
  }

  public void setProject(Project project) {
    log.info("Switching project to " + project.getName());
  }

  public void handle(Throwable t) {
    log.error("Handled Throwable ", t);
  }

  public String getApplicationName() {
    return "PREPare Test APPLICATION";
  }
  
  public String getVersion() {
    return "Test Application";
  }
}
