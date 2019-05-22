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
        assertEquals("Guacamole", finalOutput.get(4).getName());
        assertEquals("French Toast", finalOutput.get(5).getName());
        assertEquals("Mashed Potatoes", finalOutput.get(6).getName());
        assertEquals("Tofu and Roasted Vegetables", finalOutput.get(7).getName());
        assertEquals("Green Bean Salad", finalOutput.get(8).getName());

    }
}