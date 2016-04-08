package CarShop;

import CarShop.db.DB_Connection;
import CarShop.db.DerbyConnection;
import CarShop.model.*;
import java.util.*;
import java.sql.Date;

public class Shop {

    private List<Car> cars;
    private List<Client> clients;
    private List<Sell>  sales;

    private DB_Connection dbConnection = new DerbyConnection();

    public DB_Connection getDbConnection() {
        return dbConnection;
    }

    public List<Car> getCars() {
        return cars;
    }


    public List<Client> getClients() {
        return clients;
    }


    public List<Sell> getSales() {
        return sales;
    }

    public void setClients(List<Client> clients) {
        this.clients = clients;
    }

    public Shop () {
       cars = new ArrayList<>();
       sales = new ArrayList<>();
       clients = new ArrayList<>();
       createClientBase();
       createStorage();
       sales = dbConnection.uploadSells();
       System.out.println(sales.size());


   }

    public Car initCar (Manufacturer man, String model, double price, EngineKind ek, int count, Color color) {
        Car car = new Car();
        car.setManufacturer(man);
        car.setModel(model);
        car.setPrice(price);
        car.setEngineKind(ek);
        car.setCount(count);
        car.setColor(color);
    return car;
    }

    public void addToStorage(Car car, int count) {
        if (changeCountAuto(car, count) == 1) {
            return;
        } else {
            car.setCount(count);
            cars.add(car);
        }
    }
    public void createStorage () {

       cars = dbConnection.uploadCars();
       cars.sort(new Comparator<Car>() {
            @Override
            public int compare(Car o1, Car o2) {
               return (o1.getManufacturer()+o1.getModel()).compareToIgnoreCase(o2.getManufacturer()+o2.getModel());
            }
        });

    }

    public Client initClient (String fullName, String adress, String phoneNumber) {
        Client client = new Client();
        client.setFullName(fullName);
        client.setAdress(adress);
        client.setPhoneNumber(phoneNumber);
        return  client;
    }

    public void addToClientBase (Client client) {
        clients.add(client);
        dbConnection.addClient(client);
    }

    public void createClientBase () {

       clients = dbConnection.uploadClients();
    }

    public void sellAuto (Car car, Client client) {
        Sell sell = new Sell();
        car.setPrice(car.getPrice()-setDiscount(car.getPrice())*car.getPrice());
        sell.setCar(car);
        sell.setClient(findClient(client.getFullName()));
        sell.setData(new Date(System.currentTimeMillis()));
        addNewSell(sell);
        changeCountAuto(car, -1);

    }

    private int changeCountAuto(Car car, int count) {
        for (int i=0; i<cars.size(); i++) {

               if (car.getCarID() == cars.get(i).getCarID()) {
                   cars.get(i).setCount(cars.get(i).getCount()+count);
                   dbConnection.changeCarCount(car, cars.get(i).getCount());
                  return 1;

            }
        }return 0;
    }

    private void addNewSell(Sell sell) {
          sales.add(sell);
          dbConnection.addSell(sell);
    }

    public Client findClient(String fullName) {

        for (Client c : clients) {

                if (c.getFullName().equals(fullName)) {
                    return c;
                }

        } return null;
    }



    public void addNewCar (Car car) {
        cars.add(car);
        dbConnection.addCar(car);
    }

    public List<Sell> getSalesByData (Date date) {
        List<Sell> sells = new ArrayList<>();

        for (Sell sell: sales) {
            if (sell.getData().equals(date)) {
                sells.add(sell);
            }
        }
        return sells;
    }
    public double setDiscount (double price) {
        double discount = 0;
        if (price>30000 && price<40000) {
            discount = 0.05;
        } else if (price>40000) {
            discount = 0.1;
        }
        return discount;
    }

}
