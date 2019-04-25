//Back_End_Processor.java
import java.sql.*;
import java.util.*;

public class Back_End_Processor{
    public static void main(String[] args){
        //Class.forName("");
        //Connection con = DriverManager.getConnection("url here", "user here", "password here");
        //from https://www.javatpoint.com/example-to-connect-to-the-mysql-database

        //args are ingredients
        List<String> ingr = new ArrayList<String>();
        for(int i = 0; i < args.length; i++){
            ingr.add(args[i]);
        }

        Database db = new Database();
        db.getConnection("name");
        TreeMap<Recipe, Integer> recMap = db.getData();

        List<Recipe> hits, one_Off; //List that hold Recipes that matches the user inputted ingredeints
                                    //along with a list that holds recipes that are close
        for(Map.Entry<Recipe,Integer> entry : recMap.entrySet()) {
            Recipe r = entry.getKey();
            TreeMap<String, Integer> ingredients = r.returnIngr();
            for(String i : ingr){
                if(ingredients.containsKey(i) == true){
                    recMap.put(r, recMap.get(r) - 1);
                }
            }
        }
    }
}