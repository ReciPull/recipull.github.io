//Back_End_Processor.java
import java.sql.*;
import java.util.*;

public class Back_End_Processor{
    public static void main(String[] args){
        //args are ingredients
        List<String> ingInputs = new ArrayList<String>();
        for(int i = 0; i < args.length; i++){
            ingInputs.add(args[i]);
        }

        TreeMap<Recipe, Integer> recMap;
        boolean testing = true;

        if (testing == false){
        //Database Connection
            Database db = new Database();
            db.getConnection("name");
            recMap = db.getData();
        }
        else{
            recMap = new TreeMap<Recipe, Integer>();
            
            //Manual Insertion
            Recipe one = new Recipe();
            Recipe two = new Recipe();
            Recipe three = new Recipe();

            one.setName("Potatoes, Onions, and Garlic");
            one.setDescription("A description");
            one.setTime(1);
            one.addIngredient("Potatoes", 10);
            one.addIngredient("Onions", 9);
            one.addIngredient("Garlic", 8);
            one.calculateFreq();

            two.setName("Potatoes, Onions, Garlic, and Chili Peppers");
            two.setDescription("Another description");
            two.setTime(2);
            two.addIngredient("Potatoes", 10);
            two.addIngredient("Onions", 9);
            two.addIngredient("Garlic", 8);
            two.addIngredient("Chili Peppers", 7);
            two.calculateFreq();

            three.setName("Potatoes, Onions, Garlic, Chili Peppers, and Mushrooms");
            three.setDescription("Another description again");
            three.setTime(3);
            three.addIngredient("Potatoes", 10);
            three.addIngredient("Onions", 9);
            three.addIngredient("Garlic", 8);
            three.addIngredient("Chili Peppers", 7);
            three.addIngredient("Mushrooms", 6);
            three.calculateFreq();

            recMap.put(one, 3);
            recMap.put(two, 4);
            recMap.put(three, 5);
        }

        List<Recipe> hits = new ArrayList<Recipe>();
        List<Recipe> one_Off = new ArrayList<Recipe>();
        List<Recipe> remainders = new ArrayList<Recipe>(); //List that hold Recipes that matches the user inputted ingredeints
                                    //along with a list that holds recipes that are close
        // for(Map.Entry<Recipe,Integer> entry : recMap.entrySet()) {
        //     Recipe r = entry.getKey();
        //     TreeMap<String, Integer> ingredients = r.returnIngr();
        //     for(String i : ingr){
        //         if(ingredients.containsKey(i) == true){
        //             recMap.put(r, recMap.get(r) - 1);
        //         }
        //     }
        // }

        for(String i : ingInputs){
            for(Map.Entry<Recipe,Integer> entry : recMap.entrySet()) {
                Recipe r = entry.getKey();
                TreeMap<String, Integer> ingRecipes = r.returnIngr();
                if(ingRecipes.containsKey(i) == true){
                    recMap.put(r, recMap.get(r) - 1);
                }
            }
        }

        for(Map.Entry<Recipe, Integer> entry : recMap.entrySet()){
            if (entry.getValue() == 0) {
                hits.add(entry.getKey());
            }
            else if (entry.getValue() == 1) {
                one_Off.add(entry.getKey());
            }
            else {
                remainders.add(entry.getKey());
            }
        }
        System.out.println("Matches:");
        hits.get(0).printOutput();
        System.out.println("\nMissing just one ingredient:");
        one_Off.get(0).printOutput();
        System.out.println("\nMissing a few ingredients:");
        remainders.get(0).printOutput();
    }
}