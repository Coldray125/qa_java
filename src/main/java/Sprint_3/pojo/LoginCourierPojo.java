package Sprint_3.pojo;

public class LoginCourierPojo {

    private String login;
    private String password;

    public LoginCourierPojo(String login, String password) {
        this.login = login;
        this.password = password;
    }

    public LoginCourierPojo() {
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
