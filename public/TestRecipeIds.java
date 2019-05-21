import java.util.ArrayList;
import java.sql.*;

import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.BeforeClass;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class TestRecipeIds {
    public Driver d;
    public Connection conn;

    /*@Before 
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
        String recipeList = d.getRecipeIdList(ingIdList);
        assertEquals("", recipeList);
    }

    @Test 
    public void testSingleIngredient() {
        String ingredients = "'orange'";
        ArrayList<Integer> ingIds = new ArrayList<Integer>();
        ingIds = d.getIngredientId(ingredients, conn);
        String ingList = d.getIngredientIdList(ingIds);
        assertEquals("45", ingList);
    }

    @Test 
    public void testMultiWordIngredient() {
        String ingredients = "'maple syrup'";
        ArrayList<Integer> ingIds = new ArrayList<Integer>();
        ingIds = d.getIngredientId(ingredients, conn);
        String ingList = d.getIngredientIdList(ingIds);
        assertEquals("37", ingList);
    }*/
}