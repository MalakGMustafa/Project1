package application;
	
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;


public class Main extends Application {
	BorderPane root = new BorderPane();
	Label title, numofLED, massage;
	Button rand, loadfile, results, photo;
	TextField txtnum;
	private Scanner input;
	HBox hbox2 = new HBox(15);
	int num = 0, count = 0;
	int [] numbers ,battery;
	Integer [] values;
	ArrayList<Integer> list = new ArrayList<>();
	int [][] c ;
	char [][] b;
	
	@Override
	public void start(Stage primaryStage) {
		//create GUI and edit items
		try {
			VBox vbox = new VBox(15);
			HBox hbox1 = new HBox(15);
			Scene scene = new Scene(root,400,400);
			
			
			root.setBackground(new Background(new BackgroundFill(Color.DARKGREY, null, null)));
			title = new Label("Max LED Lighting");
			title.setTextFill(Color.BLACK);
			title.setFont(Font.font("Arial Narrow",FontWeight.BOLD ,FontPosture.ITALIC, 20));
			
			rand = new Button("Random");
			loadfile = new Button("Load File");
			results = new Button("Results");
			photo = new Button("Photo");
			numofLED = new Label("Number Of LEDs");
			txtnum = new TextField();
			
			hbox2.getChildren().addAll(numofLED,txtnum);	
			hbox1.getChildren().addAll(rand,loadfile, results, photo);
			vbox.getChildren().addAll(hbox1,hbox2);
			
			root.setTop(title);
			root.setCenter(vbox);
			BorderPane.setAlignment(title, Pos.TOP_CENTER);
			BorderPane.setAlignment(photo, Pos.BOTTOM_CENTER);
			hbox1.setAlignment(Pos.CENTER);
			hbox2.setAlignment(Pos.CENTER);
			vbox.setAlignment(Pos.CENTER);
			
			ReadFromFile(primaryStage);
			Random();
			primaryStage.setScene(scene);
			primaryStage.show();
		} 
		catch(Exception e) {
			e.printStackTrace();
		}
		
		results.setOnAction(e->{
			LCS();
			print_LCS(b, battery, num, num);
			convert(count, list);
			Result r = new Result();
			r.ResaltPane(primaryStage);
			r.values(count, values, numbers, num, c, b);
		});
		
		photo.setOnAction(e->{
			LCS();
			print_LCS(b, battery, num, num);
			convert(count, list);
			GUIResult r = new GUIResult();
			r.GUI(primaryStage, numbers, values);
 		});
	
	}
	
	public static void main(String[] args) {
		launch(args);
	}
	
	
	// create method to read texts from file chooser 
    public void ReadFromFile(Stage primaryStage) throws FileNotFoundException {
    	FileChooser file = new FileChooser();
    	
    	loadfile.setOnAction(e-> {
		  File selectedFile = file.showOpenDialog(primaryStage);

		    try {
				input = new Scanner(selectedFile);
				
				//read lines from file and create arrays to save values
		        while (input.hasNext()) { 
		           num = Integer.parseInt(input.nextLine());
		           String[] str = input.nextLine().split(" ");
		           numbers = new int [num];
		           battery = new int [num];
		           
		           if(num > 100) {
		        	   massage = new Label("The numbers of LED is more than 100 please choose random choice");
	        		   root.setBottom(massage);
	        		   BorderPane.setAlignment(massage, Pos.BOTTOM_CENTER);
		        	   break;
		           }
		           
		           //check if the numbers digit less than values, if it is fill missing numbers
		           if(str.length < num) {
		        	   for (int i = 0; i < str.length; i++) {
		        		   numbers[i] = Integer.parseInt(str[i]);
					}
		        	  missing(numbers, str.length, num);
		        	  for (int i = 0; i < num; i++) {
						System.out.println(numbers[i]);
					}
	        	   }
		           
		           
		           else if(numbers.length > num) {
		        	   checkDup(numbers, str, num);
	        	   }
		           
		           else {
		        	   checkDup(numbers, str, num);
		        }  
		      }
		    }
		    
		    catch (FileNotFoundException ex) {
		    	System.out.println(ex);
			}
    	});
    }
    
    // create method to random choice    
    public void Random () {
		rand.setOnAction(e->{
			
			//take number of LEDs and create the array numbers
			num = Integer.parseInt(txtnum.getText());
			numbers = new int [num];
			battery = new int [num];
			for (int i = 0; i < numbers.length; i++) {
				battery[i] = i+1;
				numbers[i] = i+1;
			}
		
			
			//shuffle the array numbers
			for (int i = numbers.length -1; i >= 0; i--) {
				int index = (int)(Math.random() * (i));
				
				int temp = numbers[index];
				numbers[index] = numbers[i];
				numbers[i] = temp;
			}
		});
    }
    
    public void LCS() {
    	c = new int[num + 1][num + 1];
    	b = new char [num + 1][num + 1];
    	
    	for (int i = 1; i <= num; i++) 
    		c [ i ] [ 0 ] = 0;
    	
    	for (int j = 1; j <= num; j++) 
			c [ 0 ] [ j ] = 0;
    	
    	for (int i = 1; i <= num; i++) {
			for (int j = 1; j <= num; j++) {
				if(battery[i-1] == numbers[j-1]) {
					c[i][j] = c[i-1][j-1] + 1;
					b[i][j] = 'D';
				}
				
				else if(c[i][j-1] > c[i-1][j]) {
					c[i][j] = c[i][j-1];
					b[i][j] = 'L';
				}
				
				else {
					c[i][j] = c[i-1][j];
					b[i][j] = 'U';
				}
			}
		}
    }
    
    
    public void print_LCS(char[][] b, int[] x, int i, int j) {
    	
    	if((i == 0) || (j == 0))
    		return;
    	
    	else if(b[i][j] == 'D') {
    		print_LCS(b, x, i-1, j-1);
    		//count the maximum numbers of LEDs lighting and save the number of LED in array list
    		count ++;
    		list.add(x[i-1]);
    	}
    	
    	else if(b[i][j] == 'U')
    		print_LCS(b, x, i-1, j);
    	
    	else
    		print_LCS(b, x, i, j-1);
    }
    
    
    // method to convert array list to array
    private void convert(int size, ArrayList<Integer> list) {
    	for (int i = 0; i < size; i++) {
			values = list.toArray(new Integer[0]);
		}
    }
    
    //method to fill missing values
    private void missing(int [] numbers, int length, int num) {
       int k = 0;
  	   for (int i = 0; i < num; i++) {
			for (int j = 0; j < length; j++) {
				if((i+1) == numbers[j])
					break;
				else if(j+1 == length) {
					numbers[length + k] = i+1;
					k++;
				}
			}
		}
    }
    
   
    
    
  //method to check if there duplicate or out of bound numbers and delete it
    private void checkDup(int[] numbers, String [] str, int num) {
    	int s = 0;
    	// check if there a duplicate number before save numbers in integer array
    	for (int i = 0; i < num; i++) {
    		int n = Integer.parseInt(str[i]);
    		for (int j = i+1; j < str.length; j++) {
     			   if(str[i].equals(str[j]) || n > num)
     				   str[j] = String.valueOf(-j);
     		  }
    	}
    	
    	//save values in the numbers array without duplicate
    	for (int i = 0; i < num; i++) {
    		if(Integer.parseInt(str[i]) > 0) {
    			numbers[s] = Integer.parseInt(str[i]);
			       s++;
    		}
    		battery[i] = i+1;
		}
    	//check if there missing values and set it in the numbers array
    	missing(numbers, s, num);
    	for (int i = 0; i < num; i++) {
			System.out.print(numbers[i]+ "  ");
			System.out.println("  /"+battery[i]);
		}
    }
}
