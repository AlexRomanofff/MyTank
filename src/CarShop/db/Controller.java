package CarShop.db;

import CarShop.model.Car;
import CarShop.model.Client;
import CarShop.model.Sell;

import java.util.List;

public interface Controller {

    void addCar(Car car);

    void addClient (Client client);

    List<Car> uploadCars();

    List<Client> uploadClients();

    List<Sell> uploadSells();

    void changeCarCount(Car car, int count);

    void addSell(Sell sell);

    void closeConnection();
}
