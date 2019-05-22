import java.util.*;

public class NumericalOrder implements Comparator<Recipe> {
    public int compare(Recipe r1, Recipe r2) {
        return (r1.getVotes())-(r2.getVotes());
    }
}