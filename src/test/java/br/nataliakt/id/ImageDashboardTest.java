package br.nataliakt.id;

import br.nataliakt.id.model.ImageFilter;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.opencv.core.Mat;
import org.opencv.core.Size;
import org.opencv.imgproc.Imgproc;

import static org.junit.jupiter.api.Assertions.*;

class ImageDashboardTest {

    private static ImageFilter[] imageFilters;

    @BeforeAll
    static void setUp() {
        // Dilatation Filter to exemplify
        imageFilters = new ImageFilter[] { new ImageFilter() {
            @Override
            public Mat filter(Mat mat, String... filter) {
                Mat destination = new Mat(mat.rows(), mat.cols(), mat.type());
                Mat element = Imgproc.getStructuringElement(Imgproc.MORPH_RECT, new Size(3, 3));
                Imgproc.dilate(mat, destination, element);
                return destination;
            }

            @Override
            public String toString() {
                return "Example";
            }
        },new ImageFilter() {
            @Override
            public Mat filter(Mat mat, String... filter) {
                Mat destination = new Mat(mat.rows(), mat.cols(), mat.type());
                Mat element = Imgproc.getStructuringElement(Imgproc.MORPH_RECT, new Size(3, 3));
                Imgproc.dilate(mat, destination, element);
                return destination;
            }

            @Override
            public String toString() {
                return "Example Param";
            }

            @Override
            public boolean hasParam() {
                return true;
            }
        }};
    }

    @Test
    void launch() {
        ImageDashboard.showFilters(imageFilters);
    }

}