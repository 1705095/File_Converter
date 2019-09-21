package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.rendering.ImageType;
import org.apache.pdfbox.rendering.PDFRenderer;
import org.apache.pdfbox.tools.imageio.ImageIOUtil;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class PdftoImage {

    @FXML
    private Button backhome;

    @FXML
    private Label convertName;

    @FXML
    private Button fileselectButton;

    @FXML
    private TextField fileName;

    @FXML
    private Label savedName;

    @FXML
    private TextField locationText;

    @FXML
    private Button convertfileButton;

    @FXML
    private Label showMessage;

    @FXML
    private MenuButton menuButton;

    @FXML
    private MenuItem jpg;

    @FXML
    private MenuItem png;

    private FileChooser fileChooser;
    private File filePath;
    String fileDestinationPath, fileFullPath;

    @FXML
    void BackHome(ActionEvent event) throws IOException {
        Parent Tpage= FXMLLoader.load(getClass().getResource("HomePage.fxml"));
        Scene Tscne=new Scene(Tpage);
        Stage window=(Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(Tscne);
        window.show();

    }

    @FXML
    void ConvertButton(ActionEvent event) throws IOException {
        PDDocument document = PDDocument.load(filePath.getAbsoluteFile());
        PDFRenderer pdfRenderer = new PDFRenderer(document);

        for (int page = 0;page<document.getNumberOfPages();page++){
            BufferedImage bufferedImage = pdfRenderer.renderImageWithDPI(page,300, ImageType.RGB);
            ImageIOUtil.writeImage(bufferedImage,fileDestinationPath,300);
        }
        document.close();

    }

    @FXML
    void SelectFile(ActionEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        fileChooser = new FileChooser();
        fileChooser.setTitle("Select TXT Files");
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("PDF Files", "*.pdf"));

        String userDirectoryString = System.getProperty("user.home");
        File userDirectory = new File(userDirectoryString);

        if (!userDirectory.canRead())
            userDirectory = new File("c:/");

        fileChooser.setInitialDirectory(userDirectory);

        this.filePath = fileChooser.showOpenDialog(stage);

        System.out.println(filePath.getAbsolutePath());

        fileName.setText(filePath.getAbsolutePath());
    }

    @FXML
    void getJpeg(ActionEvent event) {
        menuButton.setText("JPEG");
        fileFullPath = filePath.getName();
        int index = fileFullPath.indexOf(".");
        fileDestinationPath = filePath.getParent() + "\\" + fileFullPath.substring(0, index) + "PDftoImage.jpg";
        locationText.setText(fileDestinationPath);
        showMessage.setText("Click Convert");


    }

    @FXML
    void getPng(ActionEvent event) {
        menuButton.setText("PNG");
        fileFullPath = filePath.getName();
        int index = fileFullPath.indexOf(".");
        fileDestinationPath = filePath.getParent() + "\\" + fileFullPath.substring(0, index) + "PDftoImage.png";
        locationText.setText(fileDestinationPath);
        showMessage.setText("Click Convert");

    }

}
