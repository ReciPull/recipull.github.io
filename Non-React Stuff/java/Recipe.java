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
    public void setName(String n) { this.name = n ;}
    public void setDescription(String d) { this.description = d; }
    public void setTime(int t) { this.time = t; } //may be blank
    public void addIngredient(String n, int f){
        ingredients.put(n, f);
    }

    public void addTag(String t){
        tags.put(t, true);
    }

    //getters
    public String getName() { return this.name; }
    public String getDescription() { return this.description; }
    public int getTime() { return this.time; }
    public int getNumIngr(){ return this.numIngr; }

    public TreeMap<String,Integer> returnIngr(){
        return ingredients;
    }

    //other functions
    public void printOutput() {
        System.out.println("Recipe Name: " + name);
        System.out.println("Description: " + description);
    }

    public int compareTo(Recipe other){
        int n = this.name.compareTo(other.name);
        return n;
    }

    //obsolete functions
    public void calculateFreq(){
        for(int f : ingredients.values()){
            this.freq += f;
        }
    }
    public int getFreq(){
        if(freq != 0){
            return this.freq;
        }
        else{
            return -1;
        }
    }
} 