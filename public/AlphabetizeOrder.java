import java.util.*;

public class AlphabetizeOrder implements Comparator<Recipe> {
    public int compare(Recipe r1, Recipe r2) {
        int diff = (r1.getName()).compareTo(r2.getName());
        return diff;
    }
}