/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ui;

import controllers.WelcomeScreenController;
import java.io.IOException;
import java.net.URL;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

/**
 *
 * @author Anat
 */

public class ScreenManager
{
     private WelcomeScreenController welcomeScreenCr;
         
     private Scene welcomeScreenSc;
         
     private FXMLLoader fxmlLoader;
     private URL url;
     
     private static ScreenManager Instance;
     
     private void LoadFxml(String FileName)
     {
         fxmlLoader = new FXMLLoader();
         url = getClass().getResource(FileName);  
         fxmlLoader.setLocation(url);
     }
    
     private void LoadWelcomeScreen() throws IOException
     {
         LoadFxml("../resources/fxml/WelcomeScreen.fxml");
         welcomeScreenSc = new Scene((Parent)fxmlLoader.load(url.openStream()));     
         welcomeScreenCr = (WelcomeScreenController) fxmlLoader.getController();
     }
    
     private ScreenManager() throws IOException
     {
//        LoadCreatePlayers();        
//        LoadGameScreen();             
          LoadWelcomeScreen();        
//        LoadBidScreen();
     }

     public static ScreenManager GetInstance()
     {       
         if(Instance == null)
         {
             try 
             {
                 Instance = new ScreenManager();
             }
             catch (IOException ex) 
             {
                 return null;
             }
         }
         return Instance;
     }

    public WelcomeScreenController getWelcomeScreenCr() {
        return welcomeScreenCr;
    }
    
    public Scene welcomeScreenSc() {
        return welcomeScreenSc;
    }
}
