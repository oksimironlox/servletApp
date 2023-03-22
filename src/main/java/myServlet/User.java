package myServlet;

public class User {
    private final String login;
    private final String password;
    private final String email;

    public User(String login, String email, String password) {
        this.login = login;
        this.password = password;
        this.email = email;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

}
