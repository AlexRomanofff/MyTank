package CarShop.db;

import CarShop.*;
import CarShop.model.*;

import java.sql.*;
import java.sql.DriverManager;
import java.util.ArrayList;
import java.util.List;

public class CarController implements Controller {

    String url = "jdbc:mysql://localhost:3306/shop?useSSL=false";
    String userName = "root";
    String password = "Mashar2004";
    Connection con=null;

    public CarController () {
        try {
            con = DriverManager.getConnection(url, userName, password);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }



    @Override
    public List<Car> uploadCars() {
         List<Car> cars = new ArrayList<>();
        try {

            Statement st = con.createStatement();
            String sql = "select * from car";

            ResultSet rst = st.executeQuery(sql);

            while (rst.next()) {
                Car car = new Car();
                car.setCarID(rst.getInt(1));
                car.setManufacturer(Manufacturer.valueOf(rst.getString(2)));
                car.setModel(rst.getString(3));
                car.setEngineKind(EngineKind.valueOf(rst.getString(4)));
                car.setColor(Color.valueOf(rst.getString(5)));
                car.setPrice(rst.getDouble(6));
                car.setCount(rst.getInt(7));
                cars.add(car);
            }
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }

        return cars;

    }

    @Override
    public List<Client> uploadClients() {
        List<Client> clients = new ArrayList<>();
        try {

            Statement st = con.createStatement();
            String sql = "select * from client";

            ResultSet rst = st.executeQuery(sql);

            while (rst.next()) {
               Client client = new Client();
                client.setId(rst.getInt(1));
                client.setFullName(rst.getString(2));
                client.setPhoneNumber(rst.getString(3));
                client.setAdress(rst.getString(4));

                clients.add(client);
            }
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }

        return clients;

    }

    @Override
    public void addCar(Car car) {

        try {

            String sql = "insert into car (manufacturer, model, engine_kind, color, price, count) " +
                    "values (?,?,?,?,?,?)";

            PreparedStatement statement = con.prepareStatement(sql);
            statement.setString(1, car.getManufacturer().toString());
            statement.setString(2,car.getModel());
            statement.setString(3, car.getEngineKind().toString());
            statement.setString(4, car.getColor().toString());
            statement.setDouble(5, car.getPrice());
            statement.setInt(6, car.getCount());

            int result = statement.executeUpdate();
            System.out.println(result);


        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void addClient(Client client) {

        try {
            String sql = "insert into client (full_name, phone , address) " +
                    "values (?,?,?)";

            PreparedStatement statement = con.prepareStatement(sql);
            statement.setString(1, client.getFullName());
            statement.setString(2, client.getPhoneNumber());
            statement.setString(3, client.getAdress());

            int result = statement.executeUpdate();
            System.out.println(result);

        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }
    @Override
    public void changeCarCount (Car car, int count) {
        try {

            String sql = "update car set count=? where id=?";
            PreparedStatement statement = con.prepareStatement(sql);

            statement.setInt(1, count);
            statement.setInt(2, car.getCarID());
            int result = statement.executeUpdate();
            System.out.println(result);

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void addSell (Sell sell) {
        try {
            Connection connect = DriverManager.getConnection(url,userName,password);
//            String sql1 = "SET foreign_key_checks = 0";
//            Statement stat1 = connect.createStatement();
//            stat1.executeUpdate(sql1);
            String sql = "insert into sell ( fk_carID, fk_clientId, date) values (?, ?, ?)";
            PreparedStatement stat = connect.prepareStatement(sql);
            stat.setInt(1, sell.getCar().getCarID());
            stat.setInt(2, sell.getClient().getId());
            stat.setDate(3, sell.getData());
            stat.executeUpdate();
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public List<Sell> uploadSells() {
        List<Car> cars = uploadCars();
        List<Client> clients = uploadClients();
        List<Sell> sells = new ArrayList<>();
        try {

            String sql = "select * from sell";
            Statement statement = con.createStatement();
            ResultSet result =  statement.executeQuery(sql);


            while (result.next()) {
                Sell sell = new Sell();
                sell.setCar(getCarByID(cars, result));
                sell.setClient(getClientByID(clients, result));
                sell.setData(result.getDate(4));
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return sells;
    }

    private Client getClientByID(List<Client> clients, ResultSet result) throws SQLException {
        for (Client client : clients) {
            if (client.getId()==result.getInt(3)) {
               return client;
            }
        }return null;
    }

    private Car getCarByID(List<Car> cars, ResultSet result) throws SQLException {
        for (Car car: cars) {
            if (car.getCarID()==result.getInt(2)) {
               return car;
            }
        } return null;
    }
}
