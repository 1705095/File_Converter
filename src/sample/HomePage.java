package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;

public class HomePage {

    @FXML
    private Label nameLabel;

    @FXML
    private Button pdfdoc;

    @FXML
    private Button docPDF;

    @FXML
    private Button mergepdf;

    @FXML
    private Button mergedoc;

    @FXML
    private Button image;

    @FXML
    private Button textpdf;

    @FXML
    private Button imgtopdfFile;

    @FXML
    private Button pdfToImagebutton;

    @FXML
    private Button pdfToHTMLButton;

    @FXML
    private Button HtmlToPDFButton;

    @FXML
    void DOCtoPDF(ActionEvent event) throws IOException {
        Parent Tpage= FXMLLoader.load(getClass().getResource("DocxToPDF.fxml"));
        Scene Tscne=new Scene(Tpage);
        Stage window=(Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(Tscne);
        window.show();

    }

    @FXML
    void MergeDocx(ActionEvent event) throws IOException {
        Parent Tpage= FXMLLoader.load(getClass().getResource("MergeDocx.fxml"));
        Scene Tscne=new Scene(Tpage);
        Stage window=(Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(Tscne);
        window.show();

    }

    @FXML
    void MergePdf(ActionEvent event) throws IOException {
        Parent Tpage= FXMLLoader.load(getClass().getResource("MergePDF.fxml"));
        Scene Tscne=new Scene(Tpage);
        Stage window=(Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(Tscne);
        window.show();

    }

    @FXML
    void PDFtoDOC(ActionEvent event) throws IOException {
        Parent Tpage= FXMLLoader.load(getClass().getResource("PDFtoDOCx.fxml"));
        Scene Tscne=new Scene(Tpage);
        Stage window=(Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(Tscne);
        window.show();

    }

    @FXML
    void ImageResizeButton(ActionEvent event) throws IOException {
        Parent Tpage= FXMLLoader.load(getClass().getResource("Image_Resize.fxml"));
        Scene Tscne=new Scene(Tpage);
        Stage window=(Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(Tscne);
        window.show();

    }

    @FXML
    void TExtToPdf(ActionEvent event) throws IOException {
        Parent Tpage= FXMLLoader.load(getClass().getResource("TexttoPdf.fxml"));
        Scene Tscne=new Scene(Tpage);
        Stage window=(Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(Tscne);
        window.show();

    }

    @FXML
    void PDFtoImage(ActionEvent event) throws IOException {
        Parent Tpage= FXMLLoader.load(getClass().getResource("PdftoImage.fxml"));
        Scene Tscne=new Scene(Tpage);
        Stage window=(Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(Tscne);
        window.show();

    }

    @FXML
    void ImageToPDF(ActionEvent event) throws IOException {
        Parent Tpage= FXMLLoader.load(getClass().getResource("ImagetoPdf.fxml"));
        Scene Tscne=new Scene(Tpage);
        Stage window=(Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(Tscne);
        window.show();

    }

    @FXML
    void PDFtoHTML(ActionEvent event) throws IOException {
        Parent Tpage= FXMLLoader.load(getClass().getResource("PDFtoHTML.fxml"));
        Scene Tscne=new Scene(Tpage);
        Stage window=(Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(Tscne);
        window.show();

    }

    @FXML
    void HTMLtoPDF(ActionEvent event) throws IOException {
        Parent Tpage= FXMLLoader.load(getClass().getResource("HTMLtoPDF.fxml"));
        Scene Tscne=new Scene(Tpage);
        Stage window=(Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(Tscne);
        window.show();

    }



}
