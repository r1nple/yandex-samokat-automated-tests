package requests;


//Класс для работы с паролем при авторизации
public class RequestPassword {

    private String password;

    public RequestPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
