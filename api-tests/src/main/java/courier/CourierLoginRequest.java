package courier;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//POJO класс для авторизации
@Data //Генерация геттеров, сеттеров и т.д.
@NoArgsConstructor //Генерация конструктора без параметров
@AllArgsConstructor //Генерация конструктора с параметрами
public class CourierLoginRequest {

    private String login;
    private String password;

}
