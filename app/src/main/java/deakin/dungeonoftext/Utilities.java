package deakin.dungeonoftext;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.FileWriter;
import java.io.IOException;

public class Utilities {
    //Declare variables
    static int x = 4, y = 4, a = 0, b = 0;
    static int[][] matrix = new int[x][y];

    //Function to intitial matrix to track player's progress
    static void initialMatrix(){
        for(int i=0; i<x; i++){
            for(int j=0;j<y;j++){
                matrix[i][j] = 0;
            }
        }
    }
    //Function to set when player start a new game
    static void newGame(){
        matrix[a][b] = 1;
    }
    //Function which will be called when player has a move
    //It will be assigned to the navigation buttons
    static void moveUp(){
        a++;
        matrix[a][b] = 1;
    }
    static void moveDown(){
        a--;
        matrix[a][b] = 1;
    }
    static void moveLeft(){
        a++;
        matrix[a][b] = 1;
    }
    static void moveRight(){
        a--;
        matrix[a][b] = 1;
    }
    //Function to convert matrix to an string
    static String matrixToString(){
        StringBuilder strBuild = new StringBuilder();
        for(int i=0; i<x; i++){
            for(int j=0;j<y;j++){
                strBuild.append(matrix[i][j]);
                strBuild.append(",");
            }
        }
        return strBuild.toString();
    }
    //Function to convert String back to matrix
    static int[][] stringToMatrix(String str){
        int[][] tempInt = new int[x][y];
        String[] tempString = str.split(",");//Will need catch to check
        for(int i=0; i<tempString.length; i++){
            tempInt[i/y][i%y] = Integer.parseInt(tempString[i]);//Will need catch Exception
        }
        return tempInt;
    }
    /**Function to limited navigation buttons - May assign to button later
    static void checkButtons(){
        if(Utilities.a == 0) {
            dwnbtn.setVisibility(View.GONE);
        }else {dwnbtn.setVisibility(View.VISIBLE);}
        if(Utilities.a == 3) {
            upbtn.setVisibility(View.GONE);
        }else {upbtn.setVisibility(View.VISIBLE);}
        if(Utilities.b == 0) {
            rightbtn.setVisibility(View.GONE);
        }else {rightbtn.setVisibility(View.VISIBLE);}
        if(Utilities.b == 3) {
            leftbtn.setVisibility(View.GONE);
        }else {leftbtn.setVisibility(View.VISIBLE);}
    }
     */
    //Function to write progress to JSON file
    static void writeMatrixToFile() throws JSONException{
        JSONObject obj = new JSONObject();
        obj.put("Matrix1", matrixToString());

        try {
            FileWriter file = new FileWriter("Data/move.json");
            file.write(obj.toString());
        }catch(IOException e){
            e.printStackTrace();
        }

    }
    //Function to read progress to JSON file
}
