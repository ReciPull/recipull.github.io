import java.sql.*;
import java.util.Scanner;

public class Vote {
    public static void main(String[] args) {
        try {
            String myDriver = "com.mysql.jdbc.Driver";
            String myURL = "jdbc:mysql://mysql-recipull.crcqvo2k4dml.us-west-2.rds.amazonaws.com:3306/recipull_rds_db";
            Class.forName(myDriver);
            Connection conn = DriverManager.getConnection(myURL,"cs48_ajara","ajara2019");
            
            Scanner s = new Scanner(System.in);
            System.out.print("Enter a number: ");
            String num = s.nextLine();
            int votes = Integer.parseInt(num);
            System.out.print("Enter a recipe: ");
            String recipe = s.nextLine();

            String query = "UPDATE recipes SET recipe_vote = ? WHERE recipe_name = ?";
            PreparedStatement preparedStmt = conn.prepareStatement(query);
            preparedStmt.setInt   (1, votes);
            preparedStmt.setString(2, recipe);

            preparedStmt.executeUpdate();

            //Statement st = conn.createStatement();
            //ResultSet rs = st.executeQuery(query);

            /*while (rs.next()) {
                ing.add(rs.getInt("ingredient_id"));
                ingIdList += (ing.get(counter)).toString() + ", ";
                
                counter++;
                // print the results
                //System.out.format("%s \n", ingredientId);
            } */

            String query2 = "SELECT * FROM recipes WHERE recipe_name = '"+recipe+"';";

            Statement st2 = conn.createStatement();
            ResultSet rs2 = st2.executeQuery(query2);

            String selectedRecipe = "";
            int recipeVotes = 0;

            while (rs2.next()) {
                selectedRecipe = rs2.getString("recipe_name");
                recipeVotes = rs2.getInt("recipe_vote");

                
                System.out.format("%s, %s \n", selectedRecipe, recipeVotes);
            } 
        
            //st.close();
            st2.close();
            s.close();
        } catch (Exception e) {
            System.err.println("Got an exception! ");
            System.err.println(e.getMessage());
        }
    }
}