package application;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.geometry.Pos;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;


public class TicTacToe extends Application{
private char turn = 'O';
private Cell[][] cell = new Cell[3][3];
private Label gameStatus = new Label("O's turn to play!");

@Override 
public void start(Stage primaryStage) {
    Button play = new Button("RESTART");
    play.setStyle("-fx-font-size: 20pt; -fx-font-weight: bold;" );
    
  GridPane gameScreen = new GridPane(); 
  for (int row = 0; row < 3; row++)
    for (int column = 0; column < 3; column++)
    	gameScreen.add(cell[row][column] = new Cell(), row, column);

  play.setOnAction(new EventHandler<ActionEvent>()
  {
      @Override 
      public void handle(ActionEvent e) {
    	  gameScreen.getChildren().clear();
    	  for (int row = 0; row < 3; row++)
    		    for (int column = 0; column < 3; column++)
    		    	gameScreen.add(cell[row][column] = new Cell(), row, column);

turn = 'X';
gameStatus.setText(turn + "'s turn.");
         }
     });
  
  BorderPane b = new BorderPane();


  HBox topBar = new HBox();
  topBar.setAlignment(Pos.CENTER);
  topBar.getChildren().add(play);
  b.setTop(topBar);
  b.setCenter(gameScreen);
  b.setBottom(gameStatus);
  
  Scene s = new Scene(b, 600, 600);
  primaryStage.setTitle("Tic-Tac-Toe"); 
  primaryStage.setScene(s); 
  primaryStage.show();    
  primaryStage.setResizable(false);
  

}
	  public class Cell extends Pane {
		    
		    private char gamePiece = ' ';

		    public Cell() {
		      setStyle("-fx-border-color: grey"); 
		      this.setPrefSize(500, 500);
		    this.setOnMouseClicked(mouse -> {
				try {
					onClick();
				} catch (FileNotFoundException e1) {
					e1.printStackTrace();
				}
			});
		    }

		  
		 

	
		    public void setgamePiece(char c) throws FileNotFoundException {
		      gamePiece = c;
		      
		      if (gamePiece == 'X') {
		    	  FileInputStream inputstream = new FileInputStream(".\\images\\x.jpg"); 
		    	  Image image = new Image(inputstream, 200,175  ,false,false); 
		    	  ImageView imageView = new ImageView(image);    
		    
		        this.getChildren().add(imageView);
		      }
		      else if (gamePiece == 'O') {
		    	  FileInputStream inputstream = new FileInputStream(".\\images\\o.jpg"); 
		    	  Image image = new Image(inputstream, 200,175,false,false); 
		    	  ImageView imageView = new ImageView(image);    
		   
		        this.getChildren().add(imageView);
		      }
		    }

		
		    private void onClick() throws FileNotFoundException {
		
		        if(gamePiece == ' ' && turn != ' ') {
		          setgamePiece(turn);

		       
		          if(draw()) {
		        	  gameStatus.setText("Draw");
		        	  turn = ' '; 
		          }
		          else if(gameOver(turn)) {
			            gameStatus.setText("Congratulations, " + turn + " has won the game! Press restart to play again!");
			            turn = ' '; 
		          }
		          else {
		         
		        	  turn = (turn == 'X') ? 'O' : 'X';
		          
		        	  gameStatus.setText(turn + "'s turn.");
		          }
		        }
		      }
		    public boolean draw() {
		        for (int row = 0; row < 3; row++)
		          for (int column = 0; column < 3; column++)
		            if (cell[row][column].gamePiece == ' ')
		              return false;

		        return true;
		      }

		      public boolean gameOver(char gamePiece) {
		        for (int row = 0; row < 3; row++)
		          if (cell[row][0].gamePiece == gamePiece && cell[row][1].gamePiece == gamePiece&& cell[row][2].gamePiece== gamePiece) {
		            return true;
		          }

		        for (int column = 0; column < 3; column++)
		          if (cell[0][column].gamePiece ==  gamePiece && cell[1][column].gamePiece == gamePiece&& cell[2][column].gamePiece == gamePiece) {
		            return true;
		          }

		        if (cell[0][0].gamePiece == gamePiece && cell[1][1].gamePiece == gamePiece && cell[2][2].gamePiece == gamePiece) {
		          return true;
		        }

		        else if(cell[0][2].gamePiece == gamePiece && cell[1][1].gamePiece== gamePiece && cell[2][0].gamePiece == gamePiece) {
		          return true;
		        }

		        return false;
		      }
		  }
	
	
	
	 public static void main(String[] args) {
		    launch(args);
		  }
}
