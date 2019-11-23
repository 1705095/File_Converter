package sample;


import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ResourceBundle;

public  class ImageResize{

    @FXML
    private Button selectButton;

    @FXML
    private Button backButton;

    @FXML
    private TextField fileName;

    @FXML
    private TextField heightText;

    @FXML
    private TextField widthText;

    @FXML
    private Button saveButon;

    @FXML
    private TextField locationText;

    @FXML
    private MenuButton selectformat;

    @FXML
    private MenuItem jpg;

    @FXML
    private MenuItem png;

    @FXML
    private ImageView previewImage;



    private String image_name, image_original_name, outputFilePath;

    private FileChooser fileChooser;
    private File filePath;
    private String fileDestinationPath;

    @FXML
    void SaveImage(ActionEvent event) throws IOException {

        if (heightText.getText()!=null && widthText.getText()!=null && selectformat.getText()!=null) {

            int height = Integer.parseInt(heightText.getText());
            int width = Integer.parseInt(widthText.getText());
            File inputFile = new File(image_name);
            BufferedImage inputImage = ImageIO.read(inputFile);

            BufferedImage outPut = new BufferedImage(width, height, inputImage.getType());

            Graphics2D g2d = outPut.createGraphics();
            g2d.drawImage(inputImage,0,0,width,height, null);
            g2d.dispose();

            //String formatName = out
            String format = null;

            //System.out.println(inputImage.getType());
            if (selectformat.getText().equals("JPEG")){
                 format = "jpg";
                 outputFilePath = outputFilePath+".jpg";
            }
            else if (selectformat.getText().equals("PNG")){
                 format = "png";
                 outputFilePath = outputFilePath+".png";
            }
            else{
                format = "png";
                outputFilePath = outputFilePath+".png";
            }
            ImageIO.write(outPut,format, new File(outputFilePath));
            fileName.clear();
            heightText.clear();
            widthText.clear();
            locationText.clear();
            previewImage.setImage(null);

        }
        else{
            System.out.println("error");
        }



    }

    @FXML
    void SelectImage(ActionEvent event) {

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        fileChooser = new FileChooser();
        fileChooser.setTitle("Select Image");
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpeg", "*.gif",".JPG"));

        String userDirectoryString = System.getProperty("user.home");
        File userDirectory = new File(userDirectoryString);

        if (!userDirectory.canRead())
            userDirectory = new File("c:/");

        fileChooser.setInitialDirectory(userDirectory);

        this.filePath = fileChooser.showOpenDialog(stage);

        try{
            BufferedImage bufferedImage = ImageIO.read(filePath);
            WritableImage image = SwingFXUtils.toFXImage(bufferedImage, null);
            this.previewImage.setImage(image);
            fileName.setText(String.valueOf(filePath));
            //locationText.setText(filePath.getParent());
            image_name = filePath.getAbsolutePath();
            image_original_name = filePath.getName();
            //String dir = filePath.getParent();
            //System.out.println(image_original_name);
            //System.out.println(dir);
            int index = image_original_name.indexOf(".");
            // System.out.println(index);
            outputFilePath = filePath.getParent()+"\\"+image_original_name.substring(0, index)+"Formatted";
            //System.out.println(outputFilePath);
            locationText.setText(outputFilePath);

        }
        catch (IOException e)
        {
            //System.out.println("Error Occurred");
            fileName.setText("No file Selected");
        }


    }

    @FXML
    void BackHome(ActionEvent event) throws IOException {
        Parent Tpage= FXMLLoader.load(getClass().getResource("HomePage.fxml"));
        Scene Tscne=new Scene(Tpage);
        Stage window=(Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(Tscne);
        window.show();

    }


    @FXML
    void getJPEG(ActionEvent event) {
        selectformat.setText("JPEG");

    }

    @FXML
    void getPNG(ActionEvent event) {
        selectformat.setText("PNG");

    }

    void PrintName(String name){
        System.out.println(name);
    }


}
