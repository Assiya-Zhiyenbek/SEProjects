package DataBaseLayer;

import java.sql.*;

public class Database_User {

    static final String jdbc = "com.mysql.cj.jdbc.Driver";

    public boolean isValidUser (String username, String userpwd){

        boolean isValid = false;

        Connection conn = null;
        Statement st = null;
        ResultSet rs;
        String sql = "";

        try{
            Class.forName(jdbc);

            System.out.println("Connecting....");

            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/seproj"+
                    "?verifyServerCertificate=false"+
                    "&useSSL=false"+
                    "&requireSSL=false"+
                    "&useLegacyDatetimeCode=false"+
                    "&amp"+
                    "&serverTimezone=UTC", "root", "");
            st = conn.createStatement();

            sql = "SELECT * FROM users WHERE user_nickname = \""+ username + "\" AND user_pwd = \"" + userpwd + "\"";

            System.out.println(sql);

            rs = st.executeQuery(sql);

            if(rs.next()){
                isValid = true;
            }

            rs.close();
            st.close();
            conn.close();

        }catch(SQLException sx){
            sx.printStackTrace();
        }catch(Exception ex){
            ex.printStackTrace();
        }finally{

            try{
                if(st!=null){
                    st.close();
                }
            }catch (SQLException se1){
            }
            try{
                if(conn!=null){
                    conn.close();
                }
            }catch(SQLException se2){
                se2.printStackTrace();
            }
        }

        System.out.println("Closing connection....");

        return isValid;
    }
}
