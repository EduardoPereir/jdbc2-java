package application;

import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class Program {
    public static void main(String[] args){

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Connection conn = null;
        PreparedStatement pst = null;

        try{
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/jdbcdatabase", "root", "eduardop$filho");
            pst = conn.prepareStatement("INSERT INTO seller " +
                    "(Name, Email, BirthDate, BaseSalary, DepartmentId) "
                    + "VALUES "
                    + "(?, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
            pst.setString(1, "Du Caicara");
            pst.setString(2, "duds@gmail.com");
            pst.setDate(3, new Date(sdf.parse("6/10/2312").getTime()));
            pst.setDouble(4, 3000.00);
            pst.setInt(5, 1);

            int rowsAffected = pst.executeUpdate();

            if( rowsAffected > 0){
                ResultSet rs = pst.getGeneratedKeys();
                while(rs.next()){
                    System.out.println("Done! Id = " + rs.getInt(1));
                }
            }else {
                System.out.println(" None rows are affected");
            }

            System.out.println("Done! Rows affected = " + rowsAffected);

        }catch (SQLException | ParseException e){
            throw new RuntimeException(e.getMessage());
        }
        try {
            pst.close();
            conn.close();
        }catch (SQLException e ){
            e.printStackTrace();
        }

    }
}