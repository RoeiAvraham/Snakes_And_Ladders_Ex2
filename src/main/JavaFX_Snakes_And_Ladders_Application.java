/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;

import javafx.application.Application;
import javafx.stage.Stage;
import engine.ScreenManager;

/**
 *
 * @author Roei
 */
public class JavaFX_Snakes_And_Ladders_Application extends Application
{
    Stage primaryStage;

    @Override
    public void start(Stage primaryStage)
    {
        this.primaryStage = primaryStage;
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)
    {
        launch(args);
    }

}
