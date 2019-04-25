// Alexis Cole and Rhianna So
import java.sql.*;

public class sqlTest {
    public static void main(String[] args) {
        try {
            String myDriver = "com.mysql.jdbc.Driver";
            String myURL = "jdbc:mysql://localhost:3306/recipull_rds_db";
            Class.forName(myDriver);
            Connection conn = DriverManager.getConnection(myURL,"cs48_ajara","ajara2019");

            String query = "SELECT * FROM recipull_rds_db.ingredients";

            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);

            while (rs.next()) {
                String name = rs.getString("ingredient_name");
                String cat = rs.getString("ingredient_category");
                boolean common = rs.getBoolean("common_ingredient");
                
                // print the results
                System.out.format("%s, %s, %s, %s, %s, %s\n", name, cat, common);
            } 
            st.close();
        } catch (Exception e) {
            System.err.println("Got an exception! ");
            System.err.println(e.getMessage());
        }
    }
}
