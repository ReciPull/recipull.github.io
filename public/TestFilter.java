import java.util.ArrayList;
import java.sql.*;

import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.BeforeClass;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class TestFilter {
    public Driver d;
    public Connection conn;

    @Before 
    public void executeBeforeAllTests() throws ClassNotFoundException, SQLException {
        d = new Driver();
        String myDriver = "com.mysql.jdbc.Driver";
        String myURL = "jdbc:mysql://mysql-recipull.crcqvo2k4dml.us-west-2.rds.amazonaws.com:3306/recipull_rds_db";
        Class.forName(myDriver);
        conn = DriverManager.getConnection(myURL,"cs48_ajara","ajara2019");
    }

    @Test 
    public void testSpecificTag() {
        String ingIdList = "25, 26, 57";
        String recipeList = d.getRecipeIdList(conn, (Integer)3, ingIdList);
        ArrayList<Recipe> recipeOutput = new ArrayList<Recipe>();
        recipeOutput = d.createRecipes(conn, recipeList);
        ArrayList<Recipe> finalOutput = new ArrayList<Recipe>();
        finalOutput = d.orderRecipes(recipeOutput, (Integer)3);
        ArrayList<Recipe> taggedOutput = new ArrayList<Recipe>();
        taggedOutput = d.getTaggedRecipes("breakfast",finalOutput);

        assertEquals("Scrambled Eggs", taggedOutput.get(0).getName());
        assertEquals("Eggs Over Sweet Potatoes", taggedOutput.get(1).getName());
        assertEquals("Eggs Baked in Avocado", taggedOutput.get(2).getName());
        assertEquals("French Toast", taggedOutput.get(3).getName());
        assertEquals("Steak Hash", taggedOutput.get(4).getName());
        assertEquals("Breakfast Skillet", taggedOutput.get(5).getName());
    }

    @Test
    public void testAlphabetical() {
        String ingIdList = "25, 26, 57";
        String recipeList = d.getRecipeIdList(conn, (Integer)3, ingIdList);
        ArrayList<Recipe> recipeOutput = new ArrayList<Recipe>();
        recipeOutput = d.createRecipes(conn, recipeList);
        ArrayList<Recipe> finalOutput = new ArrayList<Recipe>();
        finalOutput = d.orderRecipes(recipeOutput, (Integer)3);
        ArrayList<Recipe> taggedOutput = new ArrayList<Recipe>();
        taggedOutput = d.alphabetize(finalOutput);

        assertEquals("Blueberry Skillet Dump Cake", taggedOutput.get(0).getName());
        assertEquals("Breakfast Skillet", taggedOutput.get(1).getName());
        assertEquals("Brownies", taggedOutput.get(2).getName());
        assertEquals("Chicken Chow Mein", taggedOutput.get(3).getName());
        assertEquals("Chickpea and Spinach Bowl", taggedOutput.get(4).getName());
        assertEquals("Chocolate Chip Cookie Cheesecake Bars", taggedOutput.get(5).getName());
        assertEquals("Chocolate Chip Cookies", taggedOutput.get(6).getName());
        assertEquals("Eggs Baked in Avocado", taggedOutput.get(7).getName());
        assertEquals("Eggs Over Sweet Potatoes", taggedOutput.get(8).getName());
        assertEquals("Flourless Almond Butter Cookies", taggedOutput.get(9).getName());
        assertEquals("French Toast", taggedOutput.get(10).getName());
        assertEquals("Garlic Shrimp Zoodles", taggedOutput.get(11).getName());
        assertEquals("Green Bean Salad", taggedOutput.get(12).getName());
        assertEquals("Guacamole", taggedOutput.get(13).getName());
        assertEquals("Honey Balsamic Chicken", taggedOutput.get(14).getName());
        assertEquals("Lemon Bars", taggedOutput.get(15).getName());
        assertEquals("Lemon Garlic Asparagus Pasta", taggedOutput.get(16).getName());
        assertEquals("Loaded Potato", taggedOutput.get(17).getName());
        assertEquals("Mac and Cheese", taggedOutput.get(18).getName());
        assertEquals("Mashed Potatoes", taggedOutput.get(19).getName());
        assertEquals("Red Pepper and Mushroom Pasta", taggedOutput.get(20).getName());
        assertEquals("Salmon with Lemon", taggedOutput.get(21).getName());
        assertEquals("Scrambled Eggs", taggedOutput.get(22).getName());
        assertEquals("Spaghetti Carbonara", taggedOutput.get(23).getName());
        assertEquals("Spinach Pea Pasta", taggedOutput.get(24).getName());
        assertEquals("Steak Hash", taggedOutput.get(25).getName());
        assertEquals("Tofu and Roasted Vegetables", taggedOutput.get(26).getName());
        assertEquals("Upgraded Ramen", taggedOutput.get(27).getName());
    }
}