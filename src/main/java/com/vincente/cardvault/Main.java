package com.vincente.cardvault;

import com.vincente.cardvault.model.CardCollection;
import com.vincente.cardvault.ui.CollectionWindow;
import javafx.application.Application;
import javafx.stage.Stage;

/** Starts CardVault and opens the user's collection window. */
public final class Main extends Application {
    /** Opens an empty in-memory collection when the JavaFX application starts. */
    @Override
    public void start(Stage stage) {
        new CollectionWindow(new CardCollection()).show(stage);
    }

    /** Launches the JavaFX application. */
    public static void main(String[] args) {
        launch(args);
    }
}
