package CarShop;


import CarShop.uis.TableTransaction;


public class Demo {

    public static void main(String[] args) throws Exception {



//        SplashScreen splash = SplashScreen.getSplashScreen();
//        Thread.sleep(5000);
//        splash.close();
//        DB_Connection carController = new SQL_Connection();
        Shop shop = new Shop();

//        Reports reports = new Reports();
//        Car car1 = new Car();
//        car1.setManufacturer(Manufacturer.RENAULT);
//        car1.setEngineKind(EngineKind.ELECTRIC);
//        car1.setModel("Fluence");
//        car1.setColor(Color.WHITE);
//        car1.setPrice(33000);
//        shop.addToStorage(car1,5);
//        car = shop.findCar(Manufacturer.TOYOTA, "Corolla");
//        shop.sellAuto(car, shop.getClients().get(0));
//        Client client = new Client();
//        client.setFullName("Holovko Olexandr");
//        client.setAdress("Kyiv, Shevchenka str.22, f.112");
//        client.setPhoneNumber("234 34 34");
//        car = shop.findCar(Manufacturer.KIA, "Rio");
//        shop.sellAuto(car, shop.getClients().get(1));
//        car = shop.findCar(Manufacturer.NISSAN,"Leaf");
//        shop.sellAuto(car, client);


//                     new CarIsNotAvailableUI();
            new TableTransaction(shop);
//            new NewClientUI("privet");
    }
}
