package application;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Result {
	GridPane gp = new GridPane();
	Main m = new Main();
	HBox hbox1 = new HBox(15);
	HBox hbox2 = new HBox(15);
	HBox hbox3 = new HBox(15);
	Label max, LED;
	TextField txt1, txt2;
	TextArea txtnum, txtchar;
	Button back ;
	
	public void ResaltPane(Stage primaryStage) {
		max = new Label("Max LED Lighting");
		LED = new Label("The LEDs that gives the expected result");
		back = new Button("Back");
		
		txt1 = new TextField();
		txt2 = new TextField();
		txtnum = new TextArea();
		txtchar = new TextArea();
		txtnum.setPrefSize(500, 300);
		txtchar.setPrefSize(500, 300);
		
		hbox1.getChildren().addAll(max, txt1);
		hbox2.getChildren().addAll(LED, txt2);
		hbox3.getChildren().addAll(txtnum, txtchar);
		hbox1.setAlignment(Pos.TOP_CENTER);
		hbox2.setAlignment(Pos.TOP_CENTER);
		hbox3.setAlignment(Pos.CENTER);
		
		
		gp.setPadding(new Insets(25,25,25,25));
		gp.setVgap(20);
		gp.setHgap(20);
		
		
		GridPane.setConstraints(hbox1, 0, 0);
		GridPane.setConstraints(hbox2, 0, 1);
		GridPane.setConstraints(hbox3, 0, 3);
		GridPane.setConstraints(back, 0, 4);
		
		gp.getChildren().addAll(hbox1, hbox2, hbox3, back);
		gp.setBackground(new Background(new BackgroundFill(Color.DARKGREY, null, null)));
		Scene scene = new Scene(gp,800,650);
		primaryStage.setScene(scene);
		primaryStage.show();
		
		back.setOnAction(e->{
			m.start(primaryStage);
		});
		
	}
	
	// put values and results of program in texts
	public void values(int count, Integer [] values, int [] numbers, int num, int [][] c, char [][] b) {
		
		txt1.setText(""+count);
		for (int i = 0; i < count; i++) {
			txt2.appendText(values[i]+"  ");
		}
		
		
		txtnum.appendText("      ");
		for (int i = 0; i < numbers.length; i++) {
			txtnum.appendText("          "+numbers[i]);
		}
		
		txtnum.appendText("\n");
		txtnum.appendText("_____________________________________________________\n");
		
		// write DP numbers table in text area 
		for (int i = 1; i <= num; i++) {
			txtnum.appendText(i+"  | ");
			for (int j = 1; j <= num; j++) {
				txtnum.appendText("          "+c[i][j]);
			}
			txtnum.appendText("\n");
			txtnum.appendText("\n");
		}
		
		
		// write DP characters table in text area 
		txtchar.appendText("      ");
		for (int i = 0; i < numbers.length; i++) {
			txtchar.appendText("          "+numbers[i]);
		}
		
		txtchar.appendText("\n");
		txtchar.appendText("_____________________________________________________\n");
		
		
		for (int i = 1; i <= num; i++) {
			txtchar.appendText(i+"  | ");
			for (int j = 1; j <= num; j++) {
				txtchar.appendText("          "+b[i][j]);
			}
			txtchar.appendText("\n");
			txtchar.appendText("\n");
		}
	}

}
