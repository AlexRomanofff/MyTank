package CarShop;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Date;

public class Shop {

    private ArrayList<Car> cars;

    private Client [] clients = new Client[20];
    private ArrayList<Sell>  sales = new ArrayList<>();

    public ArrayList<Car> getCars() {
        return cars;
    }

    public void setCars(ArrayList<Car> cars) {
        this.cars = cars;
    }

    public Client[] getClients() {
        return clients;
    }

    public void setClients(Client[] clients) {
        this.clients = clients;
    }

    public ArrayList<Sell> getSales() {
        return sales;
    }

    public void setSales(ArrayList<Sell> sales) {
        this.sales = sales;
    }

    public Shop () {
       cars = new ArrayList<>();
       sales = new ArrayList<>();
       createStorage();
       createClientBase();
   }

    private Car initCar (Manufacturer man, String model, double price, EngineKind ek, Color color) {
        Car car = new Car();
        car.setManufacturer(man);
        car.setModel(model);
        car.setPrice(price);
        car.setEngineKind(ek);
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

       addToStorage(initCar(Manufacturer.TOYOTA, "Camry", 34000, EngineKind.BENZINE, Color.BLACK),5);
       addToStorage(initCar(Manufacturer.TOYOTA, "Corolla", 25000, EngineKind.DIESEL, Color.WHITE),5);
       addToStorage(initCar(Manufacturer.TOYOTA, "Land Cruiser", 50000, EngineKind.BENZINE, Color.BLACK),2);
       addToStorage(initCar(Manufacturer.TOYOTA, "Prius", 38000, EngineKind.HYBRID,Color.GREY),5);
       addToStorage(initCar(Manufacturer.KIA, "Sportage", 38000, EngineKind.DIESEL,Color.BROWN),5);
       addToStorage(initCar(Manufacturer.KIA, "Cerato", 27000, EngineKind.BENZINE,Color.BLUE), 3);
       addToStorage(initCar(Manufacturer.KIA, "Rio", 22000, EngineKind.BENZINE,  Color.WHITE),3);
       addToStorage(initCar(Manufacturer.NISSAN, "Leaf", 32000, EngineKind.ELECTRIC, Color.BLUE),3);

    }
    public Client initClient (String fullName, String adress, String phoneNumber) {
        Client client = new Client();
        client.setFullname(fullName);
        client.setAdress(adress);
        client.setPhoneNumber(phoneNumber);
        return  client;
    }

    public void addToClientBase (Client client) {
        for (int i=0; i<clients.length; i++) {
            if (clients[i]==null) {
                clients[i]=client;
                return;
            }
        }
    }
    public void createClientBase () {
        addToClientBase(initClient("Ivanov Illya", "Kyiv, Nagornaya str.34", "493 34 23"));
        addToClientBase(initClient("Shevchenko Oleksii", "Kyiv, Artema str.11, f.54", "563 34 23"));
        addToClientBase(initClient("Zaharova Elena", "Lviv, Svobody str.24", "112 87 88"));
    }

    public void sellAuto (Car car, Client client) {
        Sell sell = new Sell();
        car.setPrice(car.getPrice()-setDiscount(car.getPrice()));
        sell.setCar(car);
        sell.setClient(findClient(client.getFullname()));
        sell.setData(new Date());
        addNewSell(sell);
        changeCountAuto(car, -1);
    }

    private int changeCountAuto(Car car, int count) {
        for (int i=0; i<cars.size(); i++) {

               if (car.getManufacturer() == cars.get(i).getManufacturer()&& car.getModel()==cars.get(i).getModel()) {
                   cars.get(i).setCount(cars.get(i).getCount()+count);
                  return 1;

            }
        }return 0;
    }

    private void addNewSell(Sell sell) {
           sales.add(sell);
    }

    public Client findClient(String fullName) {
        Client client = new Client();
        int availabilityClient = 0;
        for (int i=0; i<clients.length; i++) {
            if (clients[i]!=null) {
                if (clients[i].getFullname().equals(fullName)) {
                    client = clients[i];
                    availabilityClient = 1;
                    break;
                }
            }
        }
        if (availabilityClient==0) {
            client.setFullname(fullName);
            addToClientBase(client);
        } return client;
    }

    public Car findCar(Manufacturer man, String model) {
        Car car = new Car();
        for (int i=0; i<cars.size(); i++) {
            if (man == cars.get(i).getManufacturer()&& cars.get(i).getModel()==model) {
               car = cars.get(i);
                break;
            }
        }
        return car;
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
