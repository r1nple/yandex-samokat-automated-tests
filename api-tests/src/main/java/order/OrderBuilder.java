package order;

import lombok.NoArgsConstructor;

@NoArgsConstructor //Генерация конструктора без параметров
public class OrderBuilder {

    private String firstName;
    private String lastName;
    private String address;
    private String stationMetro;
    private String numberPhone;
    private Integer timeRent;
    private String dateDelivery;
    private String comment;
    private String[] color;

    public OrderBuilder setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public OrderBuilder setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public OrderBuilder setAddress(String address) {
        this.address = address;
        return this;
    }

    public OrderBuilder setStationMetro(String stationMetro) {
        this.stationMetro = stationMetro;
        return this;
    }

    public OrderBuilder setNumberPhone(String numberPhone) {
        this.numberPhone = numberPhone;
        return this;
    }

    public OrderBuilder setTimeRent(Integer timeRent) {
        this.timeRent = timeRent;
        return this;
    }

    public OrderBuilder setDateDelivery(String dateDelivery) {
        this.dateDelivery = dateDelivery;
        return this;
    }

    public OrderBuilder setComment(String comment) {
        this.comment = comment;
        return this;
    }

    public OrderBuilder setColor(String[] color) {
        this.color = color;
        return this;
    }

    //Метод для создания объекта Order
    public Order build() {
        return new Order(firstName, lastName, address, stationMetro, numberPhone, timeRent, dateDelivery, comment, color);
    }

}
