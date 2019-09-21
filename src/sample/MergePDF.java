package sample;
import com.itextpdf.text.pdf.codec.Base64;
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
import com.itextpdf.text.Document;
import org.apache.pdfbox.multipdf.PDFMergerUtility;
import org.apache.pdfbox.pdmodel.PDDocument;


import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;

public class MergePDF {

    @FXML
    private TextField fileOne;

    @FXML
    private TextField fileTwo;

    @FXML
    private Button buttonOne;

    @FXML
    private Button buttonTwo;

    @FXML
    private Label name;

    @FXML
    private Label showMessage;

    @FXML
    private TextField locationText;

    @FXML
    private Label loc;

    @FXML
    private Button mergeButton;

    @FXML
    private Button backButton;

    private FileChooser fileChooser;
    private File filePath1, filePath2;
    String fileDestinationPath;
    boolean select1 = false, select2 = false;

    @FXML
    void Back(ActionEvent event) throws IOException {
        Parent Tpage= FXMLLoader.load(getClass().getResource("HomePage.fxml"));
        Scene Tscne=new Scene(Tpage);
        Stage window=(Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(Tscne);
        window.show();

    }

    @FXML
    void MergeButton(ActionEvent event) throws IOException {

        if (!select1 || !select2){
            showMessage.setText("Select both files");
        }
        else{
            File file1 = new File(filePath1.getAbsolutePath());
            PDDocument doc1 = PDDocument.load(file1);
            File file2 = new File(filePath2.getAbsolutePath());
            PDDocument doc2 = PDDocument.load(file2);

            PDFMergerUtility PDFmerger = new PDFMergerUtility();
            PDFmerger.setDestinationFileName(fileDestinationPath);
            PDFmerger.addSource(file1);
            PDFmerger.addSource(file2);

            PDFmerger.mergeDocuments();
            showMessage.setText("DONE");

            doc1.close();
            doc2.close();
            fileOne.clear();
            fileTwo.clear();
            locationText.clear();


        }


    }

    @FXML
    void SelectFileOne(ActionEvent event) {
        showMessage.setText("");
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        fileChooser = new FileChooser();
        fileChooser.setTitle("Select DOCX Files");
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("PDF Files", "*.pdf"));

        String userDirectoryString = System.getProperty("user.home");
        File userDirectory = new File(userDirectoryString);

        if (!userDirectory.canRead())
            userDirectory = new File("c:/");

        fileChooser.setInitialDirectory(userDirectory);

        this.filePath1 = fileChooser.showOpenDialog(stage);

        System.out.println(filePath1.getAbsolutePath());

        fileOne.setText(filePath1.getAbsolutePath());
        select1 = true;

        if(select2 == true) {

            String FileOne = filePath1.getName();
            String FileTwo = filePath2.getName();
            int indexOne = FileOne.indexOf(".");
            int indexTwo = FileTwo.indexOf(".");


            fileDestinationPath = filePath1.getParent() + "\\" + FileOne.substring(0, indexOne) + FileTwo.substring(0, indexTwo) + ".pdf";
            // System.out.println(fileDestinationPath);
            locationText.setText(fileDestinationPath);
        }



    }

    @FXML
    void SelectFileTwo(ActionEvent event) {
        showMessage.setText("");
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        fileChooser = new FileChooser();
        fileChooser.setTitle("Select DOCX Files");
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("PDF Files", "*.pdf"));

        String userDirectoryString = System.getProperty("user.home");
        File userDirectory = new File(userDirectoryString);

        if (!userDirectory.canRead())
            userDirectory = new File("c:/");

        fileChooser.setInitialDirectory(userDirectory);

        this.filePath2 = fileChooser.showOpenDialog(stage);

        System.out.println(filePath2.getAbsolutePath());

        fileTwo.setText(filePath2.getAbsolutePath());

        select2 = true;

        if(select1 == true) {

            String FileOne = filePath1.getName();
            String FileTwo = filePath2.getName();
            int indexOne = FileOne.indexOf(".");
            int indexTwo = FileTwo.indexOf(".");


            fileDestinationPath = filePath1.getParent() + "\\" + FileOne.substring(0, indexOne) + FileTwo.substring(0, indexTwo) + ".pdf";
            // System.out.println(fileDestinationPath);
            locationText.setText(fileDestinationPath);
        }



    }

}
