import java.sql.*;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.HashMap;

public class Driver {
    private Scanner s = new Scanner(System.in);

    public ArrayList<Recipe> getTaggedRecipes(String tagName, ArrayList<Recipe> initRecipes) {
        ArrayList<Recipe> finalRecipes = new ArrayList<Recipe>();
        boolean isTagged = false;
        for(Recipe x : initRecipes) {
            isTagged = x.checkTags(tagName);
            if(isTagged) {
                finalRecipes.add(x);
            }
        }
        // caller must check if arrayList is empty
        return finalRecipes;
    }

    public void setNumIngData(int numIng, int recipeId) {
        try {
            String myDriver = "com.mysql.jdbc.Driver";
            String myURL = "jdbc:mysql://mysql-recipull.crcqvo2k4dml.us-west-2.rds.amazonaws.com:3306/recipull_rds_db";
            Class.forName(myDriver);
            Connection conn = DriverManager.getConnection(myURL,"cs48_ajara","ajara2019");

            String query = "UPDATE recipes SET num_ingredients = ? WHERE recipe_id = ?";
            PreparedStatement preparedStmt = conn.prepareStatement(query);
            preparedStmt.setInt(1, numIng);
            preparedStmt.setInt(2, recipeId);

            preparedStmt.executeUpdate();
        } catch (Exception e) {
            System.err.println("Got an exception! ");
            System.err.println(e.getMessage());
        }
    }

    public void updateVote(int votes, String recipe) {
        try {
            String myDriver = "com.mysql.jdbc.Driver";
            String myURL = "jdbc:mysql://mysql-recipull.crcqvo2k4dml.us-west-2.rds.amazonaws.com:3306/recipull_rds_db";
            Class.forName(myDriver);
            Connection conn = DriverManager.getConnection(myURL,"cs48_ajara","ajara2019");

            String query = "UPDATE recipes SET recipe_vote = ? WHERE recipe_name = ?";
            PreparedStatement preparedStmt = conn.prepareStatement(query);
            preparedStmt.setInt   (1, votes);
            preparedStmt.setString(2, recipe);

            preparedStmt.executeUpdate();

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
        } catch (Exception e) {
            System.err.println("Got an exception! ");
            System.err.println(e.getMessage());
        }
    }

    public void run(String ingredients) {
        try {
            String myDriver = "com.mysql.jdbc.Driver";
            String myURL = "jdbc:mysql://mysql-recipull.crcqvo2k4dml.us-west-2.rds.amazonaws.com:3306/recipull_rds_db";
            Class.forName(myDriver);
            Connection conn = DriverManager.getConnection(myURL,"cs48_ajara","ajara2019");
            
            //Scanner s = new Scanner(System.in);
            //System.out.print("Enter an ingredient separated by only commas (no spaces between commas): ");
            //String ingInput = s.nextLine();
            /*String[] ingList = ingInput.split(":",0);
            String ingredients = "";

            for(int i = 0; i < ingList.length; i++) {
                if(i == (ingList.length-1)) {
                    ingredients += "'"+ingList[i]+"'";
                } else {
                    ingredients += "'"+ingList[i]+"', ";
                }
            }*/

            //System.out.println(ingredients);

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
            for(int i = 0; i < numIng; i++) {
                for(Integer key : ingFrequency.keySet()) {
                    if((ingFrequency.get(key)).equals(numIng-i)) {
                        finalRecipes += key+", ";
                        setNumIngData(numIng-i, key);
                        successRecipes++;
                    }
                }
            }
            if(successRecipes > 0)
                finalRecipes = finalRecipes.substring(0, finalRecipes.length()-2);
            if(successRecipes == 0) {
                System.out.println("No recipes containing all "+numIng+" ingredients found.");
                s.close();
                return;
            }
            
            String query3 = "SELECT recipes.recipe_name, recipes.recipe_url, recipes.recipe_img_url, recipes.recipe_tags, recipes.recipe_vote, recipes.description, recipes.num_ingredients FROM recipes WHERE recipes.recipe_id IN ("+finalRecipes+");";

            Statement st3 = conn.createStatement();
            ResultSet rs3 = st3.executeQuery(query3);

            String recipeName = "";
            String recipeURL = "";
            String recipeImgURL = "";
            String recipeTags = "";
            String recipeDesc = "";
            int recipeVote = 0;
            int numIngIncluded = 0;
            ArrayList<Recipe> recipeList = new ArrayList<Recipe>();

            while (rs3.next()) {
                Recipe r = new Recipe();

                recipeName = rs3.getString("recipe_name");
                r.setName(recipeName);
                recipeURL = rs3.getString("recipe_url");
                r.setURL(recipeURL);
                recipeImgURL = rs3.getString("recipe_img_url");
                r.setImgURL(recipeImgURL);
                recipeTags = rs3.getString("recipe_tags");
                String[] tags = recipeTags.split(", ");
                for(String tagName : tags) {
                    r.addTag(tagName);
                }
                recipeVote = rs3.getInt("recipe_vote");
                r.setVotes(recipeVote);
                numIngIncluded = rs3.getInt("num_ingredients");
                r.setNumIng(numIngIncluded);
                recipeDesc = rs3.getString("description");
                r.setDescription(recipeDesc);
                recipeList.add(r);

                // print the results
                //System.out.format("%s, %s, %s \n", recipeName, recipeTags, recipeVote);
            } 
            ArrayList<Recipe> orderedRecipes = new ArrayList<Recipe>();

            for(int i = 0; i < numIng; i++) {
                for(Recipe r : recipeList) {
                    if(((Integer)r.getNumIng()).equals(numIng-i)) {
                        orderedRecipes.add(r);
                    }
                }
            }

            for(Recipe x : orderedRecipes) {
                System.out.println(x.getNumIng()+":\t"+x.getName()+": "+x.getTags());
                System.out.println(x.getURL()+"\n"+x.getImgURL()+"\n"+x.getDesc());
                System.out.println("---------------------------");
            }

            
            System.out.print("Enter a tag: ");
            String userTag = s.nextLine();
            ArrayList<Recipe> taggedRecipes = getTaggedRecipes(userTag,orderedRecipes);

            System.out.println("AFTER FILTER: ");
            for(Recipe x : taggedRecipes) {
                System.out.println(x.getNumIng()+":\t"+x.getName()+": "+x.getTags());
                System.out.println(x.getURL()+"\n"+x.getImgURL()+"\n"+x.getDesc());
                System.out.println("---------------------------");
            }
            updateVote(8, "grilled cheese");
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