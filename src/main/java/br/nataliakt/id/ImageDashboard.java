package br.nataliakt.id;

import br.nataliakt.id.controller.DashboardController;
import br.nataliakt.id.model.ImageFilter;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class ImageDashboard extends Application {

    private static ImageFilter[] imageFilters;

    public synchronized static void showFilters(ImageFilter... imageFilters) {
        ImageDashboard.imageFilters = imageFilters;
        launch();
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        nu.pattern.OpenCV.loadShared();

        FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("br/nataliakt/id/view/dashboard.fxml"));
        AnchorPane root = loader.load();

        DashboardController controller = loader.getController();
        controller.setFilters(imageFilters);

        primaryStage.setScene(new Scene(root));
        primaryStage.setMaximized(true);
        primaryStage.show();
    }
}
