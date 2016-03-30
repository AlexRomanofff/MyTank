package CarShop;


import CarShop.model.Car;
import CarShop.model.EngineKind;
import CarShop.model.Sell;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class Reports {
    Data date = new Data();
    Shop shop;
    public Reports (Shop shop) {
        this.shop = shop;
    }

    public void priceList (ArrayList<Car> cars) {
        System.out.println("----------Price List------------");
        for (Car car: cars) {
                System.out.printf("%-8s%-15s%,.1f%s%n", car.getManufacturer(),car.getModel(), car.getPrice(),"$");
        }
    }
    public void restOnStorage (ArrayList<Car> cars)    {
        System.out.println("--------Rest on Storage---------");
        for (Car car: cars) {
            System.out.printf("%-8s%-15s%d%5s%n" ,car.getManufacturer(), car.getModel(), car.getCount(), "auto");

        }
    }



    public void countSellLastDays(Sell[]sells, int days) {


        System.out.println("---------Amount Sales for the last "+ days + " days: ---------");
        System.out.print("(");

        while (days > 0) {
            int amount = 0;
            String date = this.date.time(days - 1);
            for (int i = 0; i < sells.length; i++) {
                if (sells[i] != null) {
                    if (sells[i].getData().equals(date)) {
                        amount++;
                    }
                }
            }
            if (days > 1) {
                System.out.print(amount + ", ");
                days--;

            } else {
                System.out.println(amount + ")");
                return;
            }
        }
    }
    public void reportForMonth(Sell[]sells) {



        int days = 30;
        int count = 0;
        double totalProfit=0;
        System.out.println( "-----------------------Report of Sales for Month-------------------------");
        System.out.printf( "%10s%20s%21s%19s%n", "date", "client", "auto", "price");
        System.out.println( "-------------------------------------------------------------------------");
        while (days > 0) {

            String date = this.date.time(days - 1);
            for (int i = 0; i < sells.length; i++) {
                if (sells[i] != null) {
                    if (sells[i].getData().equals(date)) {
                        count++;
                        totalProfit = totalProfit+sells[i].getCar().getPrice();

                        System.out.printf( "%-20s%-20s%-8s%-15s%,8.1f%s%n", sells[i].getData(),
                                sells[i].getClient().getFullName(),sells[i].getCar().getManufacturer(),
                                sells[i].getCar().getModel(), sells[i].getCar().getPrice(),"$");

                    }
                }else {

                }
            }days--;
        }
        System.out.println("-------------------------------TOTAL-------------------------------------");
        System.out.printf("%5s%5s%28s%,33.1f%s%n", count, "cars",  "Gross Income", totalProfit, "$");
    }

    public void findAutoByEngine(EngineKind engine, ArrayList<Car> cars) {
        System.out.println("----------Cars with "+engine + " engine:----------------");
        for (Car car: cars) {
            System.out.printf("%-8s%-15s%,12.1f%s%n",car.getManufacturer(), car.getModel(),car.getPrice(), "$");
                }
            }

        public List<Sell> selectSalesForData(Date data) {
            List<Sell> dataSell = new ArrayList<>();
            for (Sell sell: shop.getSales()) {
                if (data==sell.getData()) {
                    dataSell.add(sell);
                }
            }return dataSell;
        }
}
