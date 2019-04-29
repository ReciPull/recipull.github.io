import java.util.TreeMap;
import java.util.HashMap;

public class Recipe implements Comparable<Recipe>{
    private String name;
    private String description;
    private TreeMap<String, Integer> ingredients;
    private int numIngr;
    private int time;
    private int freq;
    private HashMap<String, Boolean> tags;

    //constructor
    public Recipe(){
        this.name = "";
        this.description = "";
        this.time = 0;
        this.freq = 0;
        ingredients = new TreeMap<String, Integer>();
        tags = new HashMap<String, Boolean>();
    }

    //Setters
    public void setName(String n) { name = n ;}
    public void setDescription(String d) { description = d; }
    public void setTime(int t) { time = t; } //may be blank
    public void addIngredient(String n, int f){
        ingredients.put(n, f);
    }
    public void calculateFreq(){
        for(int f : ingredients.values()){
            freq += f;
        }
    }
    public void addTag(String t){
        tags.put(t, true);
    }

    public int getNumIngr(){
        return numIngr;
    }

    public TreeMap<String,Integer> returnIngr(){
        return ingredients;
    }

    public void printOutput() {
        System.out.println("Recipe Name: " + name);
        System.out.println("Description: " + description);
    }

    public int compareTo(Recipe other){
        int n = this.name.compareTo(other.name);
        return n;
    }
} 