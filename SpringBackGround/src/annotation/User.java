package annotation;

public class User {
    private String username;

    @Encrypt
    private String password; // 암호화 필요, Spring 이 특정 필드 @Encrypt 가 붙은 필드에 대하여 암호화 하는걸 흉내내보자.

    public User() {
    }

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "User{" +
                "password='" + password + '\'' +
                ", username='" + username + '\'' +
                '}';
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
