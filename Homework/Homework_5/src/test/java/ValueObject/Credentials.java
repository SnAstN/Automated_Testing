package ValueObject;


// Объединение логически пароля и логина
public class Credentials{
    private final String LOGIN;
    private final String PASSWORD;
    public Credentials(String login, String password){
        LOGIN = login;
        PASSWORD = password;
    }
    public String getLOGIN() {
        return LOGIN;
    }
    public String getPASSWORD() {
        return PASSWORD;
    }
}
