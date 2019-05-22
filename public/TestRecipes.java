import java.util.ArrayList;
import java.sql.*;

import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.BeforeClass;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class TestRecipes {
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
    public void testMultIngredient() {
        String ingIdList = "25, 26, 57";
        String recipeList = d.getRecipeIdList(conn, (Integer)3, ingIdList);
        ArrayList<Recipe> recipeOutput = new ArrayList<Recipe>();
        recipeOutput = d.createRecipes(conn, recipeList);
        
        assertEquals("Guacamole", recipeOutput.get(0).getName());
        assertEquals("French Toast", recipeOutput.get(1).getName());
        assertEquals("Scrambled Eggs", recipeOutput.get(2).getName());
        assertEquals("Brownies", recipeOutput.get(3).getName());
        assertEquals("Mashed Potatoes", recipeOutput.get(4).getName());
        assertEquals("Chocolate Chip Cookies", recipeOutput.get(5).getName());
        assertEquals("Tofu and Roasted Vegetables", recipeOutput.get(6).getName());
        assertEquals("Mac and Cheese", recipeOutput.get(7).getName());
        assertEquals("Green Bean Salad", recipeOutput.get(8).getName());
    }

    @Test 
    public void testSingleIngredient() {
        String ingIdList = "11";
        String recipeList = d.getRecipeIdList(conn, (Integer)1, ingIdList);
        ArrayList<Recipe> recipeOutput = new ArrayList<Recipe>();
        recipeOutput = d.createRecipes(conn, recipeList);
        assertEquals("Grilled Cheese", recipeOutput.get(0).getName());
        assertEquals("French Toast", recipeOutput.get(1).getName());    
    }

    @Test 
    public void testNoRecipes() {
        String ingIdList = "20";
        String recipeList = d.getRecipeIdList(conn, (Integer)1, ingIdList);
        ArrayList<Recipe> recipeOutput = new ArrayList<Recipe>();
        recipeOutput = d.createRecipes(conn, recipeList);
        assertEquals(0, recipeOutput.size());
    }
}