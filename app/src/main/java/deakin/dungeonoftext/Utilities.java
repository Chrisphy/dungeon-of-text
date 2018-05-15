package deakin.dungeonoftext;

public class Utilities {
    //Declare variables
    static int x = 4, y = 4, a = 0, b = 0, level = 0;
    static int room;
    static int[][] matrix = new int[x][y];

    //Function to initial matrix to track player's progress
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
        b++;
        matrix[a][b] = 1;
    }
    static void moveRight(){
        b--;
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
    //Function to get the current room which player in
    static int getRoom(){
        return a*4+b;
    }

}
