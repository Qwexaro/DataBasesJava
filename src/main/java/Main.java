import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Main {
    public static void main(String[] args) {
        ConfigPassword configPassword = new ConfigPassword();
        String user = "root";
        String url = "jdbc:mysql://localhost:3306/courses";
        String password = configPassword.getPassword(); // get info of password

        try {
            Connection connection = DriverManager.getConnection(url, user, password);
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM courses");

            while (resultSet.next()){
              String currentNameCourse = resultSet.getString("name");
                System.out.println("\"" + currentNameCourse + "\"");
            }

            resultSet.close();
            statement.close();
            connection.close();

        } catch (Exception ex) {
            ex.getMessage();
        }
    }
}
