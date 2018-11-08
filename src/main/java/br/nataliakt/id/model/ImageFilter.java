package br.nataliakt.id.model;

import javafx.scene.image.Image;
import org.opencv.core.Mat;

public abstract class ImageFilter {

    public Image filterToImage(Image image, String... param) {
        Mat mat = filter(OpenCVUtils.image2Mat(image), param);
        return OpenCVUtils.mat2Image(mat);
    }

    public abstract Mat filter(Mat mat, String... param);

    public boolean hasParam() {
        return false;
    }

    @Override
    public abstract String toString();

}
