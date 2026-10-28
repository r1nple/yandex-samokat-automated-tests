package generators;

import com.github.javafaker.Faker;
import courier.Courier;

public class GeneratorCourier {
    //Использование библиотеки JavaFaker для генерации фиктивных данных
    private final Faker faker;

    public GeneratorCourier() {
        this.faker = new Faker();
    }

    //Метод генерации фиктивных данных для курьера
    public Courier getRandom() {

        String login = "_courier" + faker.internet().uuid();
        String password = faker.internet().password();
        String firstName = faker.name().firstName();

        return new Courier(login, password, firstName);

    }

}
