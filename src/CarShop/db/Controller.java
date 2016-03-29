package CarShop.db;

import CarShop.model.Car;
import CarShop.model.Client;
import CarShop.model.Sell;

import java.util.List;

public interface Controller {
    void addCar(Car car);
    void addClient (Client client);
    List<Car> uploadCars();
    void changeCarCount(Car car, int count);
    List<Client> uploadClients();
    List<Sell> uploadSells();
    void addSell(Sell sell);
}
