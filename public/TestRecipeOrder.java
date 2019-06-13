import java.util.ArrayList;
import java.sql.*;

import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.BeforeClass;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class TestRecipeOrder {
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
    public void testOrder() {
        String ingIdList = "25, 26, 57";
        String recipeList = d.getRecipeIdList(conn, (Integer)3, ingIdList);
        ArrayList<Recipe> recipeOutput = new ArrayList<Recipe>();
        recipeOutput = d.createRecipes(conn, recipeList);
        ArrayList<Recipe> finalOutput = new ArrayList<Recipe>();
        finalOutput = d.orderRecipes(recipeOutput, (Integer)3);
        
        assertEquals("Brownies", finalOutput.get(0).getName());
        assertEquals("Chocolate Chip Cookies", finalOutput.get(1).getName());
        assertEquals("Scrambled Eggs", finalOutput.get(2).getName());
        assertEquals("Mac and Cheese", finalOutput.get(3).getName());
        assertEquals("Upgraded Ramen", finalOutput.get(4).getName());
        assertEquals("Eggs Over Sweet Potatoes", finalOutput.get(5).getName());
        assertEquals("Spaghetti Carbonara", finalOutput.get(6).getName());
        assertEquals("Eggs Baked in Avocado", finalOutput.get(7).getName());
        assertEquals("Lemon Bars", finalOutput.get(8).getName());
        assertEquals("Flourless Almond Butter Cookies", finalOutput.get(9).getName());
        assertEquals("Guacamole", finalOutput.get(10).getName());
        assertEquals("French Toast", finalOutput.get(11).getName());
        assertEquals("Mashed Potatoes", finalOutput.get(12).getName());
        assertEquals("Tofu and Roasted Vegetables", finalOutput.get(13).getName());
        assertEquals("Green Bean Salad", finalOutput.get(14).getName());
        assertEquals("Honey Balsamic Chicken", finalOutput.get(15).getName());
        assertEquals("Spinach Pea Pasta", finalOutput.get(16).getName());
        assertEquals("Lemon Garlic Asparagus Pasta", finalOutput.get(17).getName());
        assertEquals("Salmon with Lemon", finalOutput.get(18).getName());
        assertEquals("Chickpea and Spinach Bowl", finalOutput.get(19).getName());
        assertEquals("Steak Hash", finalOutput.get(20).getName());
        assertEquals("Breakfast Skillet", finalOutput.get(21).getName());
        assertEquals("Red Pepper and Mushroom Pasta", finalOutput.get(22).getName());
        assertEquals("Chicken Chow Mein", finalOutput.get(23).getName());
        assertEquals("Loaded Potato", finalOutput.get(24).getName());
        assertEquals("Garlic Shrimp Zoodles", finalOutput.get(25).getName());
        assertEquals("Blueberry Skillet Dump Cake", finalOutput.get(26).getName());
        assertEquals("Chocolate Chip Cookie Cheesecake Bars", finalOutput.get(27).getName());
    }
}