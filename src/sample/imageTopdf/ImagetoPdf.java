package sample.imageTopdf;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.pdf.PdfWriter;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class ImagetoPdf {

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

    private FileChooser fileChooser;
    private File filePath;
    String fileDestinationPath;

    @FXML
    void BackHome(ActionEvent event) throws IOException {
        Parent Tpage= FXMLLoader.load(getClass().getResource("../home/HomePage.fxml"));
        Scene Tscne=new Scene(Tpage);
        Stage window=(Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(Tscne);
        window.show();

    }

    @FXML
    void ConvertButton(ActionEvent event) throws IOException, DocumentException {
        Document document = new Document();
        FileOutputStream fileOutputStream = new FileOutputStream(fileDestinationPath);

        PdfWriter writer = PdfWriter.getInstance(document, fileOutputStream);
        writer.open();
        document.open();
        Image image = Image.getInstance(filePath.getAbsolutePath());
        image.setAbsolutePosition(0,0);
        image.setBorderWidth(0);
        image.scaleAbsoluteHeight(PageSize.A4.getHeight());
        image.scaleAbsoluteWidth(PageSize.A4.getWidth());
        document.add(image);
        document.close();
        writer.close();
        showMessage.setText("Converted");
        fileName.clear();
        locationText.clear();



    }

    @FXML
    void SelectFile(ActionEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        fileChooser = new FileChooser();
        fileChooser.setTitle("Select TXT Files");
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("sample.Image Files", "*.png", "*.jpg"));

        String userDirectoryString = System.getProperty("user.home");
        File userDirectory = new File(userDirectoryString);

        if (!userDirectory.canRead())
            userDirectory = new File("c:/");

        fileChooser.setInitialDirectory(userDirectory);

        this.filePath = fileChooser.showOpenDialog(stage);

        System.out.println(filePath.getAbsolutePath());

        fileName.setText(filePath.getAbsolutePath());

        //creating output file path
        String fileFullPath = filePath.getName();
        int index = fileFullPath.indexOf(".");
        fileDestinationPath = filePath.getParent()+"\\"+fileFullPath.substring(0,index)+"imagetopdf.pdf";
        //System.out.println(fileDestinationPath);
        locationText.setText(fileDestinationPath);
        showMessage.setText("Click Convert");

    }

}
