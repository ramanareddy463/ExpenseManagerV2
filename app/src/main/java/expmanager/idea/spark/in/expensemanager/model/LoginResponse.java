package expmanager.idea.spark.in.expensemanager.model;

/**
 * Created by Ramana.Reddy on 3/14/2017.
 */

public class LoginResponse {

    private String token;
    private User user;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
