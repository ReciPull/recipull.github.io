// Alexis Cole and Rhianna So
import java.sql.*;
import java.util.Scanner;
import java.util.ArrayList;

public class getRecipeFromIng {
    public static void main(String[] args) {
        try {
            String myDriver = "com.mysql.jdbc.Driver";
            String myURL = "jdbc:mysql://mysql-recipull.crcqvo2k4dml.us-west-2.rds.amazonaws.com:3306/recipull_rds_db";
            Class.forName(myDriver);
            Connection conn = DriverManager.getConnection(myURL,"cs48_ajara","ajara2019");
            
            Scanner s = new Scanner(System.in);
            System.out.print("Enter an ingredient: ");
            String ingInput = s.nextLine();

            String query = "SELECT ingredients.ingredient_id FROM ingredients WHERE ingredient_name = '"+ingInput+"';";

            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            int ingredientId = 0;

            while (rs.next()) {
                ingredientId = rs.getInt("ingredient_id");
                
                // print the results
                //System.out.format("%s \n", ingredientId);
            } 
            String query2 = "SELECT pivot.recipe_id FROM pivot WHERE ingredient_id = "+ingredientId+";";

            Statement st2 = conn.createStatement();
            ResultSet rs2 = st2.executeQuery(query2);

            int count = 0;
            String recipes = "";
            ArrayList<Integer> arr = new ArrayList<Integer>();

            while (rs2.next()) {
                arr.add(rs2.getInt("recipe_id"));
                recipes += (arr.get(count)).toString() + ", ";
                
                count++;
            } 

            if(arr.size() > 0)
                recipes = recipes.substring(0, recipes.length()-2);

            String query3 = "SELECT recipes.recipe_name FROM recipes WHERE recipes.recipe_id IN ("+recipes+");";

            Statement st3 = conn.createStatement();
            ResultSet rs3 = st3.executeQuery(query3);

            String recipeName = "";

            while (rs3.next()) {
                recipeName = rs3.getString("recipe_name");

                // print the results
                System.out.format("%s \n", recipeName);
            } 
            st.close();
            st2.close();
            st3.close();
            s.close();
        } catch (Exception e) {
            System.err.println("Got an exception! ");
            System.err.println(e.getMessage());
        }
    }
}
