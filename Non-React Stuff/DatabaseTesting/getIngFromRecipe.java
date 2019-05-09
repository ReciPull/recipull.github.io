// Alexis Cole and Rhianna So
import java.sql.*;
import java.util.Scanner;
import java.util.ArrayList;

public class getIngFromRecipe {
    public static void main(String[] args) {
        try {
            String myDriver = "com.mysql.jdbc.Driver";
            String myURL = "jdbc:mysql://mysql-recipull.crcqvo2k4dml.us-west-2.rds.amazonaws.com:3306/recipull_rds_db";
            Class.forName(myDriver);
            Connection conn = DriverManager.getConnection(myURL,"cs48_ajara","ajara2019");
            
            Scanner s = new Scanner(System.in);
            System.out.print("Enter a recipe name: ");
            String recipeInput = s.nextLine();

            String query = "SELECT recipes.recipe_id FROM recipes WHERE recipe_name = '"+recipeInput+"';";

            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            int recipeId = 0;

            while (rs.next()) {
                recipeId = rs.getInt("recipe_id");
                
                // print the results
                //System.out.format("%s \n", ingredientId);
            } 

            String query2 = "SELECT pivot.ingredient_id FROM pivot WHERE pivot.recipe_id = "+recipeId+";";

            Statement st2 = conn.createStatement();
            ResultSet rs2 = st2.executeQuery(query2);

            int count = 0;
            String ingredients = "";
            ArrayList<Integer> arr = new ArrayList<Integer>();

            while (rs2.next()) {
                arr.add(rs2.getInt("ingredient_id"));
                ingredients += (arr.get(count)).toString() + ", ";
                
                count++;
            } 

            if(arr.size() > 0)
                ingredients = ingredients.substring(0, ingredients.length()-2);

            String query3 = "SELECT ingredients.ingredient_name, ingredients.ingredient_category FROM ingredients WHERE ingredients.ingredient_id IN ("+ingredients+");";

            Statement st3 = conn.createStatement();
            ResultSet rs3 = st3.executeQuery(query3);

            String ingName = "";
            String ingCat = "";

            while (rs3.next()) {
                ingName = rs3.getString("ingredient_name");
                ingCat = rs3.getString("ingredient_category");

                // print the results
                System.out.format("%s, %s \n", ingName, ingCat);
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
