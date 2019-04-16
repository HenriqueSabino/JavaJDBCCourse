package application;

import db.DB;
import db.DbIntegrityException;

import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class Program {
    public static void main(String[] args) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try{
            connection = DB.getConnection();
            preparedStatement = connection.prepareStatement(
                    "DELETE FROM department "
                    + "WHERE department.Id = ?"
            );

            preparedStatement.setInt(1, 2);

            int rowsAffected = preparedStatement.executeUpdate();

            System.out.printf("Done! %d row(s) affected", rowsAffected);
        }
        catch (SQLException e){
            throw new DbIntegrityException(e.getMessage());
        }
        finally {
            DB.closeStatement(preparedStatement);
            DB.closeConnection();
        }
    }
}
