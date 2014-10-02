package Net;

/**
 * Created by svt on 02.10.2014.
 */
public class LogPass {
    private String login = null;
    private String password = null;

    public LogPass() {
    }

    public LogPass(String login, String password) {
        this.login = login;
        this.password = password;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public void setLogin(String login) {

        this.login = login;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "LogPass{" +
                "login='" + login + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof LogPass)) return false;

        LogPass logPass = (LogPass) o;

        if (login != null ? !login.equals(logPass.login) : logPass.login != null) return false;
        if (password != null ? !password.equals(logPass.password) : logPass.password != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = login != null ? login.hashCode() : 0;
        result = 31 * result + (password != null ? password.hashCode() : 0);
        return result;
    }


}
