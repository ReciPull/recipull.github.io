import java.util.HashMap;
import java.util.ArrayList;

public class Recipe implements Comparable<Recipe>{
    private String name;
    private String url;
    private String imgUrl;
    private String description;
    private int votes;
    private int numIng;
    private HashMap<String, Boolean> tags;

    //constructor
    public Recipe(){
        this.name = "";
        this.url = "";
        this.imgUrl = "";
        this.description = "";
        this.votes = 0;
        this.numIng = 0;
        this.tags = new HashMap<String, Boolean>();
    }

    //Setters
    public void setName(String n) { name = n ;}
    public void setURL(String url) { this.url = url ;}
    public void setImgURL(String imgUrl) { this.imgUrl = imgUrl ;}
    public void setDescription(String d) { description = d; }
    public void setVotes(int v) { votes = v; }
    public void setNumIng(int numIng) { this.numIng = numIng; }
    public String getName() { return name; }
    public String getURL() { return url; }
    public String getImgURL() { return imgUrl; }
    public int getVotes() { return votes; }
    public int getNumIng() { return numIng; }
    public String getDesc() { return description; }
    public String getTags() {
        String tagNames = "";
        for(String x : this.tags.keySet()) {
            tagNames += x+", ";
        }
        if(!(tagNames.length() == 0)) {
            tagNames = tagNames.substring(0, tagNames.length()-2);
        }
        return tagNames;
    }

    public boolean checkTags(String tagName) {
        for(String key : tags.keySet()) {
            if(key.equals(tagName)) {
                return tags.get(key);
            }
        }
        return false;
    }

    public void addTag(String t){
        tags.put(t, true);
    }

    public int compareTo(Recipe other){
        int n = this.name.compareTo(other.name);
        return n;
    }
} 