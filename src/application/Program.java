package application;

import db.DB;

import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class Program {
    public static void main(String[] args) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try{
            connection = DB.getConnection();
            preparedStatement = connection.prepareStatement(
                        "insert into seller "
                        + "(Name, Email, BirthDate, BaseSalary, DepartmentId) "
                        + "values (?, ?, ?, ?, ?)",
                        Statement.RETURN_GENERATED_KEYS);

            preparedStatement.setString(1, "Carl Purple");
            preparedStatement.setString(2, "carl@gmail.com");
            preparedStatement.setDate(3, new java.sql.Date(sdf.parse("22/04/1985").getTime()));
            preparedStatement.setDouble(4, 3000.0);
            preparedStatement.setInt(5, 4);

            int rowsAffected = preparedStatement.executeUpdate();

            if (rowsAffected > 0){
                ResultSet resultSet = preparedStatement.getGeneratedKeys();

                while(resultSet.next()){
                    int id = resultSet.getInt(1);
                    System.out.println("Done! Id = " + id);
                }

                System.out.printf("Done! %d rows affected", rowsAffected);
            }
            else{
                System.out.println("No rows affected");
            }
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        catch (ParseException e){
            e.printStackTrace();
        }
        finally {
            DB.closeStatement(preparedStatement);
            DB.closeConnection();
        }
    }
}
