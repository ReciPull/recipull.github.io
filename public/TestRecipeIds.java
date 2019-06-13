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
        assertEquals("4, 6, 3, 8, 14, 17, 21, 24, 34, 36, 1, 2, 5, 7, 9, 10, 11, 13, 16, 19, 22, 25, 26, 27, 28, 30, 33, 35", recipeList);
    }

    @Test 
    public void testSingleIngredient() {
        String ingIdList = "11";
        String recipeList = d.getRecipeIdList(conn, (Integer)1, ingIdList);
        assertEquals("0, 2", recipeList);
    }

    @Test 
    public void testNoRecipes() {
        String ingIdList = "0";
        String recipeList = d.getRecipeIdList(conn, (Integer)1, ingIdList);
        assertEquals("No Recipes", recipeList);
    }
}