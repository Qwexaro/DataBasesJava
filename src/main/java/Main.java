import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;
import java.util.Locale;

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
            while (resultSet.next()) {
                Course currentCourse = new Course();
                currentCourse.setId(resultSet.getInt("id"));
                currentCourse.setName(resultSet.getString("name"));
                currentCourse.setDuration(resultSet.getInt("duration"));
                currentCourse.setType(CourseType.valueOf(resultSet.getString("type")));
                currentCourse.setDescription(resultSet.getString("description"));
                currentCourse.setTeacherId(resultSet.getInt("teacher_id"));
                currentCourse.setStudentsCount(resultSet.getInt("students_count"));
                currentCourse.setPrice(resultSet.getDouble("price"));
                currentCourse.setPricePerHour(resultSet.getDouble("price_per_hour"));
                Course.addCourse(currentCourse);
            }


            resultSet.close();
            statement.close();
            connection.close();
        } catch (Exception ex) {
            ex.getMessage();
        }

    }
}
