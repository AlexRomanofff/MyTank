package CarShop.model;


import CarShop.model.Car;
import CarShop.model.Client;

import java.sql.Date;

public class Sell {

    private Car car = new Car();
    private Client client = new Client();
    private Date data;


    public Sell () {

    }

    public void setCar(Car car) {
        this.car = car;
    }

    public Car getCar() {

        return car;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }
}
