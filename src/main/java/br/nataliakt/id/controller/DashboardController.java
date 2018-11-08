package br.nataliakt.id.controller;

import br.nataliakt.id.model.ImageFilter;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.stage.FileChooser;

import java.io.File;
import java.io.IOException;

public class DashboardController {

    @FXML
    private FlowPane imageFlowPane;

    private ImageFilter[] imageFilters;

    @FXML
    private void initialize() {

    }

    @FXML
    private void handleOpenButton() throws IOException {
        FileChooser chooser = new FileChooser();
        File file = chooser.showOpenDialog(null);

        if (file != null) {
            Image image = new Image(file.toURI().toString());
            open(image);
        }
    }

    public void open(Image image) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("br/nataliakt/id/view/image.fxml"));
        AnchorPane root = loader.load();

        ImageController imageController = loader.getController();
        imageController.setImage(image);
        imageController.setDashboardController(this);

        imageFlowPane.getChildren().add(root);
    }

    public void remove(AnchorPane anchorPane) {
        imageFlowPane.getChildren().remove(anchorPane);
    }

    public void setFilters(ImageFilter[] filters) {
        this.imageFilters = filters;
    }

    public ImageFilter[] getImageFilters() {
        return imageFilters;
    }
}
