package data.user;

import java.io.Serializable;

public class User implements Serializable{
    private String email;
    private String password;
    private String nickname;
    private boolean isAdmin;

    public static final int WRONGPW = -1;
    public static final int RIGHTPW = 1;
    public static final int NOUSER = 2;
    public static final int SYSERROR = 3;

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    public void setAdmin(boolean admin) {
        isAdmin = admin;
    }
}
