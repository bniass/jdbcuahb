package uahb.coursjava;

import java.sql.*;
import java.util.Scanner;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        Scanner sc = new Scanner(System.in);
        try {
            DatabaseHelper db = new DatabaseHelper();
            System.out.println("Saisir le libelle du service");
            String libelle = sc.nextLine();
            String sql = "SELECT * FROM service WHERE libelle = ?";
            System.out.println(sql);
            Object[] params = {libelle};
            db.myPreparedStatement(sql, params);
            ResultSet rs = db.myExecuteQuery();
            if(rs.next()){
                System.out.println("Ce service exite déja !!!");
            }
            else{
               // inserrer le service
               sql = "INSERT INTO service values(null, ?)";
               db.myPreparedStatement(sql, params);
               System.out.println(sql);
               int n = db.myExecuteUpdate();
               System.out.println("Nb lignes inserées = "+n);
            }
            rs.close();
            sql = "SELECT * FROM service";
            System.out.println(sql);
            Object[] newparams = {};
            db.myPreparedStatement(sql, newparams);
            rs = db.myExecuteQuery();
            while (rs.next()){
                System.out.println("ID = "+rs.getInt("id")+", " +
                        "LIBELLE = "+rs.getString(2));
            }
            rs.close();
            db.closeConnection();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
