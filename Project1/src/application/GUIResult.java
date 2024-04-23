package application;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class GUIResult {
	GridPane gp = new GridPane();
	
	
	public void GUI (Stage primaryStage, int [] n, Integer [] l) {
		// loop to put numbers of battery in text field
		for (int i = 0; i < n.length; i++) {
            TextField txt = new TextField();
            txt.setText(n[i]+ "");
            txt.setMaxSize(30, 30);
            txt.setEditable(false);
            txt.setBackground(new Background(new BackgroundFill(Color.DARKGREY, null, null)));
            if(n.length > 15)
            	txt.setFont(Font.font("Arial Narrow",FontWeight.BOLD ,FontPosture.ITALIC, 8));
            else
            	txt.setFont(Font.font("Arial Narrow",FontWeight.BOLD ,FontPosture.ITALIC, 15));
            
            GridPane.setConstraints(txt, 6, i);
            gp.getChildren().add(txt);
        }
		
		// loop to put numbers of LED in text field
        for (int i = 0; i < n.length; i++) {
            TextField txt = new TextField();
            txt.setText(i+1+ "");
            txt.setEditable(false);
            txt.setMaxSize(30, 30);
            txt.setBackground(new Background(new BackgroundFill(Color.DARKGREY, null, null)));
            if(n.length > 15)
            	txt.setFont(Font.font("Arial Narrow",FontWeight.BOLD ,FontPosture.ITALIC, 8));
            else
            	txt.setFont(Font.font("Arial Narrow",FontWeight.BOLD ,FontPosture.ITALIC, 15));
            GridPane.setConstraints(txt, 12, i);
            gp.getChildren().add(txt);
        }
        
     // loop to put on and off LEDs using LCS
        for (int i = 0; i < n.length; i++) {
			for (int j = 0; j < l.length; j++) {
				if(n[i] == l[j]) {
					Image image = new Image("ON.png");
					ImageView imagev = new ImageView(image);
					if(n.length > 15){
						imagev.setFitHeight(10);
						imagev.setFitWidth(10);
					}
		            else {
		            	imagev.setFitHeight(30);
						imagev.setFitWidth(30);
		            }
		            	
					GridPane.setConstraints(imagev, 4, i);
		            gp.getChildren().add(imagev);
		            break;
				}
				else {
					Image image = new Image("OFF.png");
					ImageView imagev = new ImageView(image);
					
					if(n.length > 15){
						imagev.setFitHeight(10);
						imagev.setFitWidth(10);
					}
		            else {
		            	imagev.setFitHeight(30);
						imagev.setFitWidth(30);
		            }
					GridPane.setConstraints(imagev, 4, i);
		            gp.getChildren().add(imagev);
				}
			}
		}
        
        gp.setPadding(new Insets(25,25,25,25));
		gp.setVgap(20);
		gp.setHgap(20);
		
		gp.setBackground(new Background(new BackgroundFill(Color.DARKGREY, null, null)));
			Scene scene = new Scene(gp,450,500);
			primaryStage.setScene(scene);
			primaryStage.show();
		
	}
}
