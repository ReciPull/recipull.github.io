//Database.java
import java.util.List;
import java.util.TreeMap;
import java.sql.*;

public class Database {
    public Database(){
        //STUB
    }

    public Database getConnection(String name){
        //STUB
        return this;
    }

    //returns a map with recipes as keys and number of ingredients as values
    public TreeMap<Recipe, Integer> getData() {
        //STUB
        TreeMap<Recipe, Integer> temp = new TreeMap<Recipe,Integer>();
        return temp;
    }
}