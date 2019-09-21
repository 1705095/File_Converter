package sample;

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
import org.apache.poi.xwpf.converter.pdf.PdfConverter;
import org.apache.poi.xwpf.converter.pdf.PdfOptions;
import org.apache.poi.xwpf.usermodel.XWPFDocument;

import java.io.*;

public class DocxToPDF {

    @FXML
    private Button backButton;

    @FXML
    private Button selectFileButton;

    @FXML
    private TextField fileName;

    @FXML
    private TextField locationText;

    @FXML
    private Button convertFileButton;

    @FXML
    private Label ShowMessage;

    private FileChooser fileChooser;
    private File filePath;
    String fileDestinationPath;

    @FXML
    void BackToHome(ActionEvent event) throws IOException {
        Parent Tpage= FXMLLoader.load(getClass().getResource("HomePage.fxml"));
        Scene Tscne=new Scene(Tpage);
        Stage window=(Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(Tscne);
        window.show();

    }


    @FXML
    void SelectFile(ActionEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        fileChooser = new FileChooser();
        fileChooser.setTitle("Select TXT Files");
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Docx Files", "*.docx"));

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
        fileDestinationPath = filePath.getParent()+"\\"+fileFullPath.substring(0,index)+"DocxToPDf.pdf";
        //System.out.println(fileDestinationPath);
        locationText.setText(fileDestinationPath);
        ShowMessage.setText("Click Convert");

    }


    @FXML
    void ConvertButton(ActionEvent event) throws IOException {
        InputStream docx = new FileInputStream(new File(filePath.getAbsolutePath()));
        XWPFDocument document = new XWPFDocument(docx);
        PdfOptions options = PdfOptions.create();
        OutputStream out = new FileOutputStream(new File(fileDestinationPath));
        PdfConverter.getInstance().convert(document,out,options);
        ShowMessage.setText("PDF created");
        fileName.clear();
        locationText.clear();
    }

}
