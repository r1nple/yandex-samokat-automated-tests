package courier;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//POJO класс
@Data //Генерация методов(сеттеров, геттеров и т.д.) для всех полей POJO-класса
@NoArgsConstructor //Генерация конструктора без параметров
@AllArgsConstructor //Генерация конструктора с параметрами
public class Courier {

    private String login;
    private String password;
    private String firstName;

}
