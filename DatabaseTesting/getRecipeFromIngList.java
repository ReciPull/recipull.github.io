import java.sql.*;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.HashMap;

public class getRecipeFromIngList {
    public static void main(String[] args) {
        try {
            String myDriver = "com.mysql.jdbc.Driver";
            String myURL = "jdbc:mysql://mysql-recipull.crcqvo2k4dml.us-west-2.rds.amazonaws.com:3306/recipull_rds_db";
            Class.forName(myDriver);
            Connection conn = DriverManager.getConnection(myURL,"cs48_ajara","ajara2019");
            
            Scanner s = new Scanner(System.in);
            System.out.print("Enter an ingredient separated by only commas (no spaces between commas): ");
            String ingInput = s.nextLine();
            String[] ingList = ingInput.split(",",0);
            String ingredients = "";

            for(int i = 0; i < ingList.length; i++) {
                if(i == (ingList.length-1)) {
                    ingredients += "'"+ingList[i]+"'";
                } else {
                    ingredients += "'"+ingList[i]+"', ";
                }
            }

            String query = "SELECT ingredients.ingredient_id FROM ingredients WHERE ingredient_name IN ("+ingredients+");";

            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            ArrayList<Integer> ing = new ArrayList<Integer>();
            String ingIdList = "";
            int counter = 0;

            while (rs.next()) {
                ing.add(rs.getInt("ingredient_id"));
                ingIdList += (ing.get(counter)).toString() + ", ";
                
                counter++;
                // print the results
                //System.out.format("%s \n", ingredientId);
            } 

            if(ing.size() > 0)
                ingIdList = ingIdList.substring(0, ingIdList.length()-2);

            String query2 = "SELECT pivot.recipe_id FROM pivot WHERE ingredient_id IN ("+ingIdList+");";

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

            HashMap<Integer, Integer> ingFrequency = new HashMap<>(); 
            for(int i = 0; i < arr.size(); i++) {
                if(!(ingFrequency.containsKey(arr.get(i)))) {
                    ingFrequency.put(arr.get(i), 1);
                } else {
                    ingFrequency.put(arr.get(i), ingFrequency.get(arr.get(i)) + 1);
                }
            }

            String finalRecipes = "";

            int successRecipes = 0;
            Integer numIng = ing.size();
            for(Integer key : ingFrequency.keySet()) {
                if((ingFrequency.get(key)).equals(numIng)) {
                    finalRecipes += key+", ";
                    successRecipes++;
                }
            }
            if(successRecipes > 0)
                finalRecipes = finalRecipes.substring(0, finalRecipes.length()-2);
            if(successRecipes == 0) {
                System.out.println("No recipes containing all "+numIng+" ingredients found.");
                s.close();
                return;
            }
            
            String query3 = "SELECT recipes.recipe_name, recipes.recipe_tags, recipes.recipe_vote FROM recipes WHERE recipes.recipe_id IN ("+finalRecipes+");";

            Statement st3 = conn.createStatement();
            ResultSet rs3 = st3.executeQuery(query3);

            String recipeName = "";
            String recipeTags = "";
            int recipeVote = 0;

            while (rs3.next()) {
                recipeName = rs3.getString("recipe_name");
                recipeTags = rs3.getString("recipe_tags");
                recipeVote = rs3.getInt("recipe_vote");

                // print the results
                System.out.format("%s, %s, %s \n", recipeName, recipeTags, recipeVote);
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
