package jdbc1;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;


/**
 *
 * @author a47
 */
public class JDBC1 extends Application {
    
    @Override
    public void start(Stage primaryStage) {
        GridPane gridPane=new GridPane();
        Text txt=new Text("Name");   
        TextField tf=new TextField();
        Text txt1=new Text();
        
        Button btn= new Button("Save");
        ChoiceBox title = new ChoiceBox();
        title.getItems().addAll("Mr", "Mrs","Miss", "Dr", "Pst", "Hon","Jnr","Capt","Eng");
      
        gridPane.setMinSize(600,400);
        gridPane.setPadding(new Insets(10,10,10,10));
        gridPane.setVgap(10);
        gridPane.setHgap(10);
        gridPane.setAlignment(Pos.CENTER);


        gridPane.add(txt1, 0, 0);
        gridPane.add(txt, 0, 1);
        
        gridPane.add(title,1,1);
        gridPane.add(tf, 2, 1);
        gridPane.add(btn, 0, 4);
        primaryStage.setTitle("Save Employee Data Form");
        
        //Eventhandling for the save button
         btn.setOnMouseClicked((new EventHandler<MouseEvent>() { 
         public void handle(MouseEvent event) {               
                 
                final String employee_name = tf.getText();
                final String title_option = (String)title.getValue(); 
                
               
                try
                {
                    //step one
                    Class.forName("com.mysql.cj.jdbc.Driver"); 
                    
                    //step two
                    Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/jdbc1","root","");
                    
                    //step three
                    Statement st = con.createStatement();
                    
                    //Step Four
                    String mySQL_st = "INSERT INTO `employee`(`title`,`name`) VALUES ("+"'"+title_option+"'"+","+"'"+employee_name+"'"+")";
                    System.out.println(mySQL_st);
                    st.executeUpdate(mySQL_st);
                    
                    //Step Five
                    con.close();
                    
                    txt1.setText("Saving successful");
                }
                catch(Exception ee){System.out.println(ee);txt1.setText("Saving NOT successful");;}
                        
              
              } 
         }));
        
        Scene scene=new Scene (gridPane);
        primaryStage.setScene(scene);
        primaryStage.show();
        
    }


    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}