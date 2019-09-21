package sample;

import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
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

import java.io.*;

public class TexttoPdf {

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
    private Label showMessage;

    @FXML
    private Button convertfileButton;

    private FileChooser fileChooser;
    private File filePath;
    String fileDestinationPath;


    @FXML
    void SelectFile(ActionEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        fileChooser = new FileChooser();
        fileChooser.setTitle("Select TXT Files");
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Text Files", "*.txt"));

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
        fileDestinationPath = filePath.getParent()+"\\"+fileFullPath.substring(0,index)+"TxtToPDf.pdf";
        //System.out.println(fileDestinationPath);
        locationText.setText(fileDestinationPath);
        showMessage.setText("Click Convert");


    }



    @FXML
    boolean ConvertButton(ActionEvent event) {

        FileInputStream iStream=null;
        DataInputStream in=null;
        InputStreamReader is=null;
        BufferedReader br=null;
        try {
            com.itextpdf.text.Document pdfDoc = new Document();

            String text_file_name = fileDestinationPath;
            PdfWriter writer=PdfWriter.getInstance(pdfDoc,new FileOutputStream(text_file_name));
            pdfDoc.open();
            pdfDoc.setMarginMirroring(true);
            pdfDoc.setMargins(36, 72, 108,180);
            pdfDoc.topMargin();
            Font normal_font = new Font();
            Font bold_font = new Font();
            bold_font.setStyle(Font.BOLD);
            bold_font.setSize(10);
            normal_font.setStyle(Font.NORMAL);
            normal_font.setSize(10);
            pdfDoc.add(new Paragraph("\n"));
            if(filePath.exists())
            {
                iStream = new FileInputStream(filePath);
                in = new DataInputStream(iStream);
                is=new InputStreamReader(in);
                br = new BufferedReader(is);
                String strLine;
                while ((strLine = br.readLine()) != null)   {
                    Paragraph para =new Paragraph(strLine+"\n",normal_font);
                    para.setAlignment(Element.ALIGN_JUSTIFIED);
                    pdfDoc.add(para);
                }
            }
            else
            {
                System.out.println("file does not exist");
                return false;
            }
            fileName.clear();
            locationText.clear();
            showMessage.setText("PDF created");
            pdfDoc.close();
        }

        catch(Exception e)
        {
            System.out.println("FileUtility.covertEmailToPDF(): exception = " + e.getMessage());
        }
        finally
        {

            try {
                if(br!=null)
                {
                    br.close();
                }
                if(is!=null)
                {
                    is.close();
                }
                if(in!=null)
                {
                    in.close();
                }
                if(iStream!=null)
                {
                    iStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

        }


        return false;
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
