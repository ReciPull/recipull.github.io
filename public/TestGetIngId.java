import java.util.ArrayList;
import java.sql.*;

import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.BeforeClass;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class TestGetIngId {
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
    public void testMultIngredientId() {
        String ingredients = "'flour', 'salt', 'egg'";
        ArrayList<Integer> ingIds = new ArrayList<Integer>();
        ingIds = d.getIngredientId(ingredients, conn);
        assertEquals((Integer)25,ingIds.get(0));
        assertEquals((Integer)26,ingIds.get(1));
        assertEquals((Integer)57,ingIds.get(2)); 
        assertEquals(3,ingIds.size());
    }

    @Test 
    public void testSingleIngredientId() {
        String ingredients = "'orange'";
        ArrayList<Integer> ingIds = new ArrayList<Integer>();
        ingIds = d.getIngredientId(ingredients, conn);
        assertEquals((Integer)45,ingIds.get(0));
        assertEquals(1,ingIds.size());
    }

    @Test 
    public void testMultiWordIngredientId() {
        String ingredients = "'maple syrup'";
        ArrayList<Integer> ingIds = new ArrayList<Integer>();
        ingIds = d.getIngredientId(ingredients, conn);
        assertEquals((Integer)37,ingIds.get(0));
        assertEquals(1,ingIds.size());
    }
}