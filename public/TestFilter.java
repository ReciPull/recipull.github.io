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
        assertEquals("French Toast", taggedOutput.get(1).getName());
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

        assertEquals("Brownies", taggedOutput.get(0).getName());
        assertEquals("Chocolate Chip Cookies", taggedOutput.get(1).getName());
        assertEquals("French Toast", taggedOutput.get(2).getName());
        assertEquals("Green Bean Salad", taggedOutput.get(3).getName());
        assertEquals("Guacamole", taggedOutput.get(4).getName());
        assertEquals("Mac and Cheese", taggedOutput.get(5).getName());
        assertEquals("Mashed Potatoes", taggedOutput.get(6).getName());
        assertEquals("Scrambled Eggs", taggedOutput.get(7).getName());
        assertEquals("Tofu and Roasted Vegetables", taggedOutput.get(8).getName());
    }
}