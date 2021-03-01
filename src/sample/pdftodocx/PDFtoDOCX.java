package sample.pdftodocx;

import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.parser.PdfReaderContentParser;
import com.itextpdf.text.pdf.parser.SimpleTextExtractionStrategy;
import com.itextpdf.text.pdf.parser.TextExtractionStrategy;
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
import org.apache.poi.xwpf.usermodel.BreakType;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;

import javax.xml.parsers.ParserConfigurationException;
import java.io.*;

public class PDFtoDOCX {

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
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("PDF Files", "*.pdf"));

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
        fileDestinationPath = filePath.getParent()+"\\"+fileFullPath.substring(0,index)+"PDFtoDocx.docx";
        //System.out.println(fileDestinationPath);
        locationText.setText(fileDestinationPath);
        showMessage.setText("Click Convert");

    }

    @FXML
    void ConvertButton(ActionEvent event) throws IOException, ParserConfigurationException {
        XWPFDocument document = new XWPFDocument();
        String pdf = filePath.getAbsolutePath();
        PdfReader reader = new PdfReader(pdf);
        PdfReaderContentParser parser = new PdfReaderContentParser(reader);

        for (int i=1;i<=reader.getNumberOfPages();i++){
            TextExtractionStrategy strategy = parser.processContent(i, new SimpleTextExtractionStrategy());
            String text = strategy.getResultantText();
            XWPFParagraph p =document.createParagraph();
            XWPFRun run = p.createRun();
            run.setText(text);
            run.addBreak(BreakType.PAGE);
        }
        FileOutputStream out = new FileOutputStream(fileDestinationPath);
        document.write(out);
        reader.close();
        showMessage.setText("Docx created");
        fileName.clear();
        locationText.clear();
    }

    @FXML
    void BackHome(ActionEvent event) throws IOException {
        Parent Tpage= FXMLLoader.load(getClass().getResource("../home/HomePage.fxml"));
        Scene Tscne=new Scene(Tpage);
        Stage window=(Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(Tscne);
        window.show();

    }



}
