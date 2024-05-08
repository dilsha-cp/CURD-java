import java.io.*;
import java.sql.*;
import java.util.Scanner;

public class curd {
    public static void main(String args[]) {
        Connection con;
        Statement stmt;
        ResultSet rs, rs1;
        ResultSetMetaData rd, rd1;
        Scanner s = new Scanner(System.in);
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/db_new?characterEncoding=utf8", "root", "");
            stmt = con.createStatement();

            String qur1 = "CREATE TABLE Department(don INT PRIMARY KEY, dname VARCHAR(25))";
            String qur2 = "CREATE TABLE EMP(eno INT PRIMARY KEY, ename VARCHAR(25), esal INT, don INT, FOREIGN KEY(don) REFERENCES Department(don))";
            stmt.executeUpdate(qur1);
            stmt.executeUpdate(qur2);
            System.out.println("Table created successfully........");

            System.out.println("Enter the number of records to insert for emp and department:");
            int n = s.nextInt();

            // Inserting records into Department table
            for (int i = 0; i < n; i++) {
                System.out.println("Enter the don and dname:");
                int don = s.nextInt();
                String dname = s.next();
                String qur3 = "INSERT INTO Department VALUES (" + don + ", '" + dname + "')";
                stmt.executeUpdate(qur3);
                System.out.println((i + 1) + " record is inserted");
            }

            // Displaying records from Department table with ResultSetMetaData
            String qur4 = "SELECT * FROM Department";
            rs = stmt.executeQuery(qur4);
            rd = rs.getMetaData();
            int c = rd.getColumnCount();
            for (int i = 1; i <= c; i++) {
                System.out.print(rd.getColumnName(i) + "\t");
            }
            System.out.println();
            while (rs.next()) {
                for (int i = 1; i <= c; i++) {
                    System.out.print(rs.getString(i) + "\t");
                }
                System.out.println();
            }

            // Inserting records into Emp table
            for (int i = 0; i < n; i++) {
                System.out.println("Enter the eno, ename, esal, and dno:");
                int eno = s.nextInt();
                String ename = s.next();
                int esal = s.nextInt();
                int dno = s.nextInt();
                String qur5 = "INSERT INTO Emp VALUES (" + eno + ", '" + ename + "', " + esal + ", " + dno + ")";
                stmt.executeUpdate(qur5);
                System.out.println((i + 1) + " record is inserted");
            }

            // Displaying records from Emp table with ResultSetMetaData
            String qur6 = "SELECT * FROM Emp";
            rs1 = stmt.executeQuery(qur6);
            rd1 = rs1.getMetaData();
            int cc = rd1.getColumnCount();
            for (int i = 1; i <= cc; i++) {
                System.out.print(rd1.getColumnName(i) + "\t");
            }
            System.out.println();
            while (rs1.next()) {
                for (int i = 1; i <= cc; i++) {
                    System.out.print(rs1.getString(i) + "\t");
                }
                System.out.println();
            }

            // Updating records in Emp table
            String qury7 = "UPDATE Emp SET esal = esal + 1000 WHERE ename = 'dilsha'";
            stmt.executeUpdate(qury7);
            System.out.println("Record is updated");

            // Deleting records from Emp table
            System.out.println("Enter the eno to delete:");
            int delEno = s.nextInt();
            String qury8 = "DELETE FROM Emp WHERE eno = " + delEno;
            stmt.executeUpdate(qury8);
            System.out.println("Record is deleted");

            // Closing resources
            rs.close();
            rs1.close();
            stmt.close();
            con.close();

        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
