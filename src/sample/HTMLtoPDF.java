package sample;

import com.itextpdf.html2pdf.ConverterProperties;
import com.itextpdf.html2pdf.HtmlConverter;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.tool.xml.XMLWorkerHelper;
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

import java.io.*;

public class HTMLtoPDF {

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
    void SelectFile(ActionEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        fileChooser = new FileChooser();
        fileChooser.setTitle("Select TXT Files");
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("HTML Files", "*.html"));

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
        fileDestinationPath = filePath.getParent()+"\\"+fileFullPath.substring(0,index)+"HTMLtoPDF.pdf";
        //System.out.println(fileDestinationPath);
        locationText.setText(fileDestinationPath);
        showMessage.setText("Click Convert");

    }

    @FXML
    void ConvertButton(ActionEvent event) throws IOException, DocumentException {
       /* Document document = new Document();
        PdfWriter writer = PdfWriter.getInstance(document,new FileOutputStream(filePath.getAbsoluteFile()));
        document.open();
        XMLWorkerHelper.getInstance().parseXHtml(writer, document, new FileInputStream(fileDestinationPath));
        document.close();*/

       /* File html = new File(filePath.getAbsolutePath());
        File pdf = new File(fileDestinationPath);
        ConverterProperties converterProperties = new ConverterProperties();
        HtmlConverter.convertToPdf(new FileInputStream(html), new FileOutputStream(pdf), converterProperties);
        fileName.clear();
        locationText.clear();
        showMessage.setText("File Converted");*/

       HtmlConverter.convertToPdf(new File(filePath.getAbsolutePath()),new File(fileDestinationPath));
       locationText.clear();
       fileName.clear();
       showMessage.setText("Converted");

    }


    @FXML
    void BackHome(ActionEvent event) throws IOException {
        Parent Tpage= FXMLLoader.load(getClass().getResource("HomePage.fxml"));
        Scene Tscne=new Scene(Tpage);
        Stage window=(Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(Tscne);
        window.show();

    }



}
