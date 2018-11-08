package br.nataliakt.id.controller;

import br.nataliakt.id.model.ImageFilter;
import javafx.application.Platform;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;

public class ImageController {

    @FXML
    private AnchorPane imageAnchorPane;

    @FXML
    private ImageView imageView;

    @FXML
    private TextField paramTextField;

    @FXML
    private ComboBox<ImageFilter> filterComboBox;

    @FXML
    private TextArea logTextArea;

    private ObjectProperty<Image> imageProperty = new SimpleObjectProperty<>();
    private DashboardController dashboardController;

    @FXML
    private void initialize() {
        Platform.runLater(() -> {
            imageView.imageProperty().bind(imageProperty);
            filterComboBox.setItems(FXCollections.observableArrayList(
                    Arrays.asList(dashboardController.getImageFilters())
            ));
            logTextArea.appendText("Imagem carregada.\n");
        });
    }

    @FXML
    private void handleFilterComboBox() {
        ImageFilter imageFilter = filterComboBox.getValue();
        paramTextField.setDisable(!imageFilter.hasParam());
    }

    @FXML
    private void handleApplyButton() {
        Platform.runLater(() -> {
            ImageFilter imageFilter = filterComboBox.getValue();
            Image image;
            if (imageFilter.hasParam()) {
                image = imageFilter.filterToImage(imageProperty.get(), paramTextField.getText().split(","));
            } else {
                image = imageFilter.filterToImage(imageProperty.get());
            }
            imageProperty.set(image);
            logTextArea.appendText("Filtro " + imageFilter.toString() + " aplicado.\n");
        });
    }

    @FXML
    private void handleSaveButton() {
        FileChooser fileChooser = new FileChooser();

        //Set extension filter
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("Image files (*.png)", "*.png");
        fileChooser.getExtensionFilters().add(extFilter);

        //Show save file dialog
        File file = fileChooser.showSaveDialog(null);

        if(file != null){
            BufferedImage bImage = SwingFXUtils.fromFXImage(imageProperty.get(), null);
            try {
                ImageIO.write(bImage, "png", file);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @FXML
    private void handleDuplicateButton() throws IOException {
        dashboardController.open(imageProperty.get());
    }

    @FXML
    private void handleRemoveButton() {
        dashboardController.remove(imageAnchorPane);
    }

    public void setImage(Image image) {
        this.imageProperty.set(image);
    }

    public void setDashboardController(DashboardController dashboardController) {
        this.dashboardController = dashboardController;
    }
}
