import java.sql.*;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Collections;

public class Driver {
    private Scanner s = new Scanner(System.in);
    private ArrayList<Recipe> selectedRecipes;

    public ArrayList<Recipe> alphabetize(ArrayList<Recipe> initRecipes) {
        ArrayList<Recipe> temp = new ArrayList<Recipe>();
        for(Recipe x : initRecipes) 
            temp.add(x);
        Collections.sort(temp, new AlphabetizeOrder());
        return temp;
        // maybe clicking on filter pulls from recipes innerHTML and sends string, then parse through to re-make recipes, then access name area and return re-sorted string
    }
    /*
    public String alphabetize(String recipeString) {
        ArrayList<Recipe> original = new ArrayList<Recipe>();

        String[] recipes = recipeString.split("|");
        for(String x : recipes) {
            String[] fields = x.split("`");
            Recipe r = new Recipe();
            r.setName(fields[0]);
            String[] tags = fields[1].split(", ");
            for(String t : tags) 
                r.addTag(t);
            r.setURL(fields[2]);
            r.setImgURL(fields[3]);
            r.setDesc(fields[4]);
            r.setNumIng(Integer.parseInt(fields[5]));
            original.add(r);
        }
        Collections.sort(original, new AlphabetizeOrder());

        String taggedOutput = "";
        for(Recipe x : original) {
            taggedOutput += "`"+x.getName()+"`"+x.getTags()+"`"+x.getURL()+"`"+x.getImgURL()+"`"+x.getDesc()+"`"+x.getNumIng()+" of your ingredients are included.|";
        }
        return taggedOutput;
    }
    */

    public ArrayList<Recipe> sortByVote(ArrayList<Recipe> initRecipes) {
        ArrayList<Recipe> temp = new ArrayList<Recipe>();
        for(Recipe x : initRecipes) 
            temp.add(x);
        Collections.sort(temp, new NumericalOrder());
        return temp;
        // maybe clicking on filter pulls from recipes innerHTML and sends string, then parse through to re-make recipes, then access votes area and return re-sorted string
            // so argument would be a string instead (d.sortByVote(whatever.innerHTML))
    }

    /*
    public String getTaggedRecipes(String recipeString) {
        ArrayList<Recipe> original = new ArrayList<Recipe>();

        String[] recipes = recipeString.split("|");
        for(String x : recipes) {
            String[] fields = x.split("`");
            Recipe r = new Recipe();
            r.setName(fields[0]);
            String[] tags = fields[1].split(", ");
            for(String t : tags) 
                r.addTag(t);
            r.setURL(fields[2]);
            r.setImgURL(fields[3]);
            r.setDesc(fields[4]);
            r.setNumIng(Integer.parseInt(fields[5]));
            original.add(r);
        }

        try {
            String myDriver = "com.mysql.jdbc.Driver";
            String myURL = "jdbc:mysql://mysql-recipull.crcqvo2k4dml.us-west-2.rds.amazonaws.com:3306/recipull_rds_db";
            Class.forName(myDriver);
            Connection conn = DriverManager.getConnection(myURL,"cs48_ajara","ajara2019");

            for(Recipe x : original) {
                String query = "SELECT recipes.recipe_vote FROM recipes WHERE recipe_id = "+x.getName()+";";

                Statement st = conn.createStatement();
                ResultSet rs = st.executeQuery(query);

                int recipeVotes = 0;

                while (rs.next()) {
                    recipeVotes = rs.getInt("recipe_vote");
                    x.setVotes(recipeVotes);
                } 
            }
            st.close();
        } catch (Exception e) {
            System.err.println("Got an exception! ");
            System.err.println(e.getMessage());
        }

        Collections.sort(original, new NumericalOrder());

        String taggedOutput = "";
        for(Recipe x : original) {
            taggedOutput += "`"+x.getName()+"`"+x.getTags()+"`"+x.getURL()+"`"+x.getImgURL()+"`"+x.getDesc()+"`"+x.getNumIng()+" of your ingredients are included.|";
        }
        return taggedOutput;
    }
    */

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
        // maybe clicking on filter pulls from recipes innerHTML and sends string, then parse through to re-make recipes, then access votes area and return re-sorted string
    }

    /*
    public String getTaggedRecipes(String tagName, String recipeString) {
        ArrayList<Recipe> original = new ArrayList<Recipe>();

        String[] recipes = recipeString.split("|");
        for(String x : recipes) {
            String[] fields = x.split("`");
            Recipe r = new Recipe();
            r.setName(fields[0]);
            String[] tags = fields[1].split(", ");
            for(String t : tags) 
                r.addTag(t);
            r.setURL(fields[2]);
            r.setImgURL(fields[3]);
            r.setDesc(fields[4]);
            r.setNumIng(Integer.parseInt(fields[5]));
            original.add(r);
        }
        ArrayList<Recipe> finalRecipes = new ArrayList<Recipe>();
        boolean isTagged = false;
        for(Recipe x : original) {
            isTagged = x.checkTags(tagName);
            if(isTagged) {
                finalRecipes.add(x);
            }
        }

        String taggedOutput = "";
        for(Recipe x : finalRecipes) {
            taggedOutput += "`"+x.getName()+"`"+x.getTags()+"`"+x.getURL()+"`"+x.getImgURL()+"`"+x.getDesc()+"`"+x.getNumIng()+" of your ingredients are included.|";
        }
        return taggedOutput;
    }
    */

    public void setNumIngData(int numIng, int recipeId, Connection conn) {
        try {
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

            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query2);

            String selectedRecipe = "";
            int recipeVotes = 0;

            while (rs.next()) {
                selectedRecipe = rs.getString("recipe_name");
                recipeVotes = rs.getInt("recipe_vote");

                
                //System.out.format("%s, %s \n", selectedRecipe, recipeVotes);
            } 
            // return string with vote number 
            // FIND A WAY TO UPDATE RECIPE OBJECT VOTE FIELD FROM HERE 
            // HOW ARE WE EVEN GOING TO ACCESS THE RECIPE OBJECT FROM THE CARD ALONE?
            st.close();
        } catch (Exception e) {
            System.err.println("Got an exception! ");
            System.err.println(e.getMessage());
        }
    }

    public ArrayList<Integer> getIngredientId(String ingredients, Connection conn) {
        ArrayList<Integer> ing = new ArrayList<Integer>();
        try {
            String query = "SELECT ingredients.ingredient_id FROM ingredients WHERE ingredient_name IN ("+ingredients+");";

            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            int counter = 0;

            while (rs.next()) {
                ing.add(rs.getInt("ingredient_id"));
                
                counter++;
            } 

            st.close();
            return ing;
        } catch (Exception e) {
            System.err.println("Got an exception! ");
            System.err.println(e.getMessage());
        }
        return ing;
    }

    public String getIngredientIdList(ArrayList<Integer> ing) {
        String ingIdList = "";

        for(Integer x: ing) {
            ingIdList += x.toString() + ", ";
        }
        if(ing.size() > 0)
            ingIdList = ingIdList.substring(0, ingIdList.length()-2);
        return ingIdList;
    }

    public String getRecipeIdList(Connection conn, Integer numIng, String ingIdList) {
        try {
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
            for(int i = 0; i < numIng; i++) {
                for(Integer key : ingFrequency.keySet()) {
                    if((ingFrequency.get(key)).equals(numIng-i)) {
                        finalRecipes += key+", ";
                        setNumIngData(numIng-i, key, conn);
                        successRecipes++;
                    }
                }
            }
            if(successRecipes > 0)
                finalRecipes = finalRecipes.substring(0, finalRecipes.length()-2);
            if(successRecipes == 0) {
                System.out.println("No recipes containing any of the "+numIng+" ingredients found.");
                s.close();
                return "No Recipes";
            }
            st2.close();
            return finalRecipes;
        } catch (Exception e) {
            System.err.println("Got an exception! ");
            System.err.println(e.getMessage());
        }
        return "No Recipes";
    }

    public ArrayList<Recipe> createRecipes(Connection conn, String finalRecipes) {
        ArrayList<Recipe> recipeList = new ArrayList<Recipe>();
        if(finalRecipes.compareTo("No Recipes") == 0) {
            return recipeList;
        }
        try  {
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
            } 
            st3.close();
            return recipeList;
        } catch (Exception e) {
            System.err.println("Got an exception! ");
            System.err.println(e.getMessage());
        }
        return recipeList;
    }

    public ArrayList<Recipe> orderRecipes(ArrayList<Recipe> recipeList, Integer numIng) {
        ArrayList<Recipe> orderedRecipes = new ArrayList<Recipe>();

        for(int i = 0; i < numIng; i++) {
            for(Recipe r : recipeList) {
                if(((Integer)r.getNumIng()).equals(numIng-i)) {
                    orderedRecipes.add(r);
                }
            }
        }
        return orderedRecipes;
    }

    public String run(String ingredients) {
        try {
            String myDriver = "com.mysql.jdbc.Driver";
            String myURL = "jdbc:mysql://mysql-recipull.crcqvo2k4dml.us-west-2.rds.amazonaws.com:3306/recipull_rds_db";
            Class.forName(myDriver);
            Connection conn = DriverManager.getConnection(myURL,"cs48_ajara","ajara2019");

            ArrayList<Integer> ingIds = getIngredientId(ingredients, conn);
            String ingIdList = getIngredientIdList(ingIds);

            Integer numIng = ingIds.size();
            String finalRecipes = getRecipeIdList(conn, numIng, ingIdList);
            if(finalRecipes.compareTo("No Recipes") == 0) {
                System.out.println("No recipes containing all "+numIng+" ingredients found.");
                s.close();
                return "No Recipes";
            }
            ArrayList<Recipe> recipeList = createRecipes(conn, finalRecipes);

            ArrayList<Recipe> orderedRecipes = orderRecipes(recipeList, numIng);

            String finalOutput = "";
            for(Recipe x : orderedRecipes) {
                finalOutput += "`"+x.getName()+"`"+x.getTags()+"`"+x.getURL()+"`"+x.getImgURL()+"`"+x.getDesc()+"`"+x.getNumIng()+" of your ingredients are included."+"`"+x.getVotes()+"|";
            }
            if(finalOutput.length() > 1) {
                finalOutput = finalOutput.substring(0, finalOutput.length()-1);
            }
            //updateVote(8, "grilled cheese", conn);
            s.close();
            return finalOutput;
        } catch (Exception e) {
            System.err.println("Got an exception! ");
            System.err.println(e.getMessage());
        }
        return "No Recipes";
    }

    public String runAlexa(String ingredients) {
        try {
            String myDriver = "com.mysql.jdbc.Driver";
            String myURL = "jdbc:mysql://mysql-recipull.crcqvo2k4dml.us-west-2.rds.amazonaws.com:3306/recipull_rds_db";
            Class.forName(myDriver);
            Connection conn = DriverManager.getConnection(myURL,"cs48_ajara","ajara2019");

            ArrayList<Integer> ingIds = getIngredientId(ingredients, conn);
            String ingIdList = getIngredientIdList(ingIds);

            Integer numIng = ingIds.size();
            String finalRecipes = getRecipeIdList(conn, numIng, ingIdList);
            if(finalRecipes.compareTo("No Recipes") == 0) {
                System.out.println("No recipes containing all "+numIng+" ingredients found.");
                s.close();
                return "No Recipes";
            }
            ArrayList<Recipe> recipeList = createRecipes(conn, finalRecipes);

            ArrayList<Recipe> orderedRecipes = orderRecipes(recipeList, numIng);

            String finalOutput = "";
            for(Recipe x : orderedRecipes) {
                finalOutput += "`"+x.getName()+"`"+x.getURL();
            }
            // if(finalOutput.length() > 1) {
            //     finalOutput = finalOutput.substring(0, finalOutput.length()-1);
            // }
            //updateVote(8, "grilled cheese", conn);
            s.close();
            return finalOutput;
        } catch (Exception e) {
            System.err.println("Got an exception! ");
            System.err.println(e.getMessage());
        }
        return "No Recipes";
    }
}