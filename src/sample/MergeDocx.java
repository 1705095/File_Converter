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
import org.apache.poi.xwpf.usermodel.*;
import org.apache.xmlbeans.XmlException;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTStyles;

import java.io.*;

public class MergeDocx {

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
    XWPFDocument document;
    int i=0;
    int j=0;

    @FXML
    void Back(ActionEvent event) throws IOException {
        Parent Tpage= FXMLLoader.load(getClass().getResource("HomePage.fxml"));
        Scene Tscne=new Scene(Tpage);
        Stage window=(Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(Tscne);
        window.show();

    }

    @FXML
    void MergeButton(ActionEvent event) throws IOException, XmlException {
        XWPFDocument doc1 = new XWPFDocument(new FileInputStream(new File(filePath1.getAbsolutePath())));
        XWPFDocument doc2 = new XWPFDocument(new FileInputStream(new File(filePath2.getAbsolutePath())));
        document = new XWPFDocument();

        passElement(doc1);
        passElement(doc2);
        PassStil(doc1, doc2);
        OutputStream out = new FileOutputStream(new File(fileDestinationPath));
        document.write(out);
        out.close();

        fileOne.clear();
        fileTwo.clear();

    }



    @FXML
    void SelectFileOne(ActionEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        fileChooser = new FileChooser();
        fileChooser.setTitle("Select DOCX Files");
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Docx Files", "*.docx"));

        String userDirectoryString = System.getProperty("user.home");
        File userDirectory = new File(userDirectoryString);

        if (!userDirectory.canRead())
            userDirectory = new File("c:/");

        fileChooser.setInitialDirectory(userDirectory);

        this.filePath1 = fileChooser.showOpenDialog(stage);

        System.out.println(filePath1.getAbsolutePath());

        fileOne.setText(filePath1.getAbsolutePath());

    }

    @FXML
    void SelectFileTwo(ActionEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        fileChooser = new FileChooser();
        fileChooser.setTitle("Select DOCX Files");
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Docx Files", "*.docx"));

        String userDirectoryString = System.getProperty("user.home");
        File userDirectory = new File(userDirectoryString);

        if (!userDirectory.canRead())
            userDirectory = new File("c:/");

        fileChooser.setInitialDirectory(userDirectory);

        this.filePath2 = fileChooser.showOpenDialog(stage);

        System.out.println(filePath2.getAbsolutePath());

        fileTwo.setText(filePath2.getAbsolutePath());

        String FileOne = filePath1.getName();
        String FileTwo = filePath2.getName();
        int indexOne = FileOne.indexOf(".");
        int indexTwo = FileTwo.indexOf(".");

        fileDestinationPath = filePath1.getParent()+"\\"+FileOne.substring(0, indexOne)+FileTwo.substring(0, indexTwo)+".docx";
       // System.out.println(fileDestinationPath);
        locationText.setText(fileDestinationPath);

    }


    void passElement(XWPFDocument doc1)
    {
        for(IBodyElement e: doc1.getBodyElements()){
            if( e instanceof XWPFParagraph){
                XWPFParagraph p =(XWPFParagraph) e;
                if(p.getCTP().getPPr()!=null && p.getCTP().getPPr().getSectPr()!=null){
                    continue;
                }
                else{
                    document.createParagraph();
                    document.setParagraph(p, i);
                    i++;
                }
            }
            else if(e instanceof XWPFTable){
                XWPFTable t = (XWPFTable) e;
                document.createTable();
                document.setTable(j, t);
                j++;
            }
            else if(e instanceof XWPFSDT){

            }
        }
    }

    void PassStil(XWPFDocument doc1, XWPFDocument doc2) throws IOException, XmlException {
        CTStyles c1 =  doc1.getStyle();
        CTStyles c2 =  doc2.getStyle();

        int size1 = c1.getStyleList().size();
        int size2 = c2.getStyleList().size();

        for (int k=0;k<size2;k++){
            c1.addNewStyle();
            c1.setStyleArray(size1+k, c2.getStyleList().get(k));
        }
        document.createStyles().setStyles(c1);
    }

}
