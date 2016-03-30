package CarShop;

import CarShop.db.CarController;
import CarShop.db.Controller;
import CarShop.model.*;

import java.util.*;
import java.sql.Date;

public class Shop {

    private List<Car> cars;
    private List<Client> clients;
    private List<Sell>  sales;
    private Reports reports;

    private Controller controller = new CarController();

    public Controller getController() {
        return controller;
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
       sales = controller.uploadSells();
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

//    public List<Sell>

    public void addToStorage(Car car, int count) {
        if (changeCountAuto(car, count) == 1) {
            return;
        } else {
            car.setCount(count);
            cars.add(car);
        }
    }
    public void createStorage () {

       cars = controller.uploadCars();
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
        controller.addClient(client);
    }

    public void createClientBase () {
//        addToClientBase(initClient("Ivanov Illya", "Kyiv, Nagornaya str.34", "493 34 23"));
//        addToClientBase(initClient("Shevchenko Oleksii", "Kyiv, Artema str.11, f.54", "563 34 23"));
//        addToClientBase(initClient("Zaharova Elena", "Lviv, Svobody str.24", "112 87 88"));
       clients = controller.uploadClients();
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
                   controller.changeCarCount(car, cars.get(i).getCount());
                  return 1;

            }
        }return 0;
    }

    private void addNewSell(Sell sell) {
           sales.add(sell);
          controller.addSell(sell);
    }

    public Client findClient(String fullName) {

        for (Client c : clients) {

                if (c.getFullName().equals(fullName)) {
                    return c;
                }

        } return null;
    }

//    public Car findCar(Manufacturer man, String model) {
//        Car car = new Car();
//        for (int i=0; i<cars.size(); i++) {
//            if (man == cars.get(i).getManufacturer()&& cars.get(i).getModel()==model) {
//               car = cars.get(i);
//                break;
//            }
//        }
//        return car;
//    }

    public void addNewCar (Car car) {
        cars.add(car);
        controller.addCar(car);
    }

    public List<Sell> getSalesByData (Date date) {
        List<Sell> sells = new ArrayList<>();
        System.out.println(date);
        for (Sell sell: sales) {
            System.out.println(sell.getData());
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
