package order;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//POJO класс
@Data //Генерация методов(сеттеров, геттеров и т.д.) для всех полей POJO-класса
@NoArgsConstructor //Генерация конструктора без параметров
@AllArgsConstructor //Генерация конструктора с параметрами
public class Order {

    private String firstName;
    private String lastName;
    private String address;
    private String stationMetro;
    private String numberPhone;
    private Integer timeRent;
    private String dateDelivery;
    private String comment;
    private String[] color;

    //Метод для создания объекта строителя OrderBuilder, для последовательного ввода данных
    public static OrderBuilder builder() {
        return new OrderBuilder();
    }

}
