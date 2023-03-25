package myServlet;

import java.sql.*;
import java.util.logging.Logger;

public class JDBCConnection {
    private static final String url = "jdbc:mysql://localhost:3306/users";
    private static final String username = "root";
    private static final String password = "admin";
    private static Connection connection = null;

    public static void connect(){
        try {
            connection = DriverManager.getConnection(url, username, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static ResultSet executeQuery(String query) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(query);
        ResultSet result = null;
        try{
            result = statement.executeQuery();
        } catch (SQLException e){
            e.printStackTrace();
        }
        return result;
    }

    private static void executeUpdate(String query) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(query);
        try{
            statement.executeUpdate();
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    public static boolean containsUser(User user) throws SQLException {
        connect();
        int result = 0;
        try {
            result = executeQuery("SELECT COUNT(*) FROM user \n WHERE user.login = '"
                    + user.getLogin() + '\'').getInt("1");

            disconnect();
        } catch (SQLException e){
            disconnect();
        }

        return result !=0;
    }

    public static void addUser(User user) throws SQLException {
        connect();
        try{
            executeUpdate("INSERT INTO user (login, password, email) VALUES ('" + user.getLogin() + "','"
                    + user.getPassword() + "','" + user.getEmail() + "')");
            disconnect();
        }catch (SQLException e){
            disconnect();
        }
    }

    public static User getUserByLogin(String login) throws SQLException {
        connect();
        ResultSet result = null;
        try{
            result = executeQuery("SELECT * FROM user WHERE user.login ='" + login + "'");
        } catch (SQLException e){
            e.printStackTrace();
        }
        if (!result.next()) {
            return null;
        }
        User resultUser = new User(result.getString(1), result.getString(2), result.getString(3));
        return resultUser;
    }

    public static boolean authUser(User user, String loginPassword){
        if (user == null)
            return false;

        return user.getPassword().equals(loginPassword);
    }

    private static void disconnect(){

        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
