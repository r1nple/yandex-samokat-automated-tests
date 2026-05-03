package generators;

import order.Order;

public class GeneratorOrder {

    public Order getOrder(String firstName, String lastName, String address, String stationMetro, String numberPhone, Integer timeRent, String dateDelivery, String comment, String[] color) {
        return new Order(firstName, lastName, address, stationMetro, numberPhone, timeRent, dateDelivery, comment, color);
    }

}
