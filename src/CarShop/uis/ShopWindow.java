package CarShop.uis;

import CarShop.model.Car;
import CarShop.model.Client;
import CarShop.Shop;
import CarShop.model.EngineKind;

import javax.swing.*;
import java.awt.*;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


public class ShopWindow extends JPanel {

    private Shop shop;
    private int indexProduct = 0;
    private JFrame frame;

    public ShopWindow(Shop shop) {
        this.shop = shop;
        this.frame = new JFrame("Shop");
        frame.setMinimumSize(new Dimension(700, 500));
        frame.setLocation(300,100);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        frame.getContentPane().add(createShopPanel());

        frame.pack();
        frame.setVisible(true);

    }

    private JPanel createShopPanel() {
        JPanel pan = new JPanel();
        pan.setLayout(new GridBagLayout());
        Font labelFont = new Font(Font.SERIF, Font.BOLD, 20);
        JLabel lCustomer = new JLabel("Customer");
        lCustomer.setFont(labelFont);
        JComboBox<String> tClient = new JComboBox<>();

        tClient.addItem("New Client");
        for (Client client: shop.getClients()) {
            tClient.addItem(client.getFullName());
        }
//        tClient.setColumns(15);
        tClient.setBackground(Color.WHITE);

        pan.add(lCustomer, new GridBagConstraints(0, 0, 1, 1, 0, 0,GridBagConstraints.LINE_START, GridBagConstraints.NONE, new Insets(0,0,15,15), 0,0));
        pan.add(tClient, new GridBagConstraints(1, 0, 1, 1, 0, 0,GridBagConstraints.LINE_START, 0, new Insets(0,0,15,0), 0,0));

        JLabel model = new JLabel("Model");
        JLabel engineType = new JLabel("Engine Type");
        model.setFont(new Font(Font.SERIF, Font.BOLD, 15));
        engineType.setFont(new Font(Font.SERIF, Font.BOLD, 15));

        pan.add(model, new GridBagConstraints(1, 1, 1, 1, 0, 0,GridBagConstraints.LINE_START, 0, new Insets(0,0,0,0), 0,0));
        pan.add(engineType, new GridBagConstraints(2, 1, 1, 1, 0, 0,GridBagConstraints.LINE_START, 0, new Insets(0,25,0,0), 0,0));


//        ButtonGroup bg = new ButtonGroup();

//        JPanel radioPanel = new JPanel();
        JLabel lProduct = new JLabel("Car");
        lProduct.setFont(labelFont);

        JComboBox<String> cars = new JComboBox<>();

        fillFieldCars(cars);

//        radioPanel.setLayout(new GridLayout(3, 0));

//        ActionListener rbListener = new RBListener();
//        for (int i=0; i<shop.getCars().size(); i++) {
//            Car car = shop.getCars().get(i);
//
//            JRadioButton rb = new JRadioButton(car.getManufacturer().toString() + " "+car.getModel().toString());
//            rb.setActionCommand(String.valueOf(i));
//            rb.addActionListener(rbListener);
//
//            if (i==0) {
//                rb.setSelected(true);
//            }
//
//            bg.add(rb);
//            radioPanel.add(rb);
//
//        }
//        cars.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//
//            }
//        });
        JComboBox<EngineKind> engine = new JComboBox<>();
            engine.addItem(shop.getCars().get(indexProduct).getEngineKind());


        pan.add(lProduct, new GridBagConstraints(0, 2, 1, 1, 0, 0,GridBagConstraints.LINE_START, GridBagConstraints.NONE, new Insets(0,0,0,0), 0,0));
        pan.add(cars, new GridBagConstraints(1, 2, 1, 1, 0, 0,GridBagConstraints.LINE_START, GridBagConstraints.NONE, new Insets(0,0,0,0), 0,0));
        pan.add(engine, new GridBagConstraints(2, 2, 1, 1, 0, 0,GridBagConstraints.LINE_START, GridBagConstraints.NONE, new Insets(0,25,0,0), 0,0));


        JButton buy = new JButton("Buy");
        buy.setFont(labelFont);
        pan.add(buy, new GridBagConstraints(3, 5, 1, 1, 0, 0,GridBagConstraints.LINE_START, GridBagConstraints.NONE, new Insets(30,0,0,0), 0,0));

        JButton returns = new JButton("Back");
        returns.setFont(labelFont);
        pan.add(returns, new GridBagConstraints(0, 5, 1, 1, 0, 0,GridBagConstraints.LINE_START, GridBagConstraints.NONE, new Insets(30,0,0,0), 0,0));

        JLabel lPrice = new JLabel("Price");
        lPrice.setFont(labelFont);
        pan.add(lPrice, new GridBagConstraints(0, 4, 1, 1, 0, 0,GridBagConstraints.LINE_START, GridBagConstraints.NONE, new Insets(20,0,0,0), 0,0));

        double price = shop.getCars().get(indexProduct).getPrice();
        JTextField lPriceValue = new JTextField();
        lPriceValue.setFont(new Font(Font.MONOSPACED, Font.BOLD, 20));
        lPriceValue.setText(Double.toString(price)+" $");
        lPriceValue.setEditable(false);

        pan.add(lPriceValue, new GridBagConstraints(1, 4, 1, 1, 0, 0,GridBagConstraints.LINE_START, GridBagConstraints.NONE, new Insets(20,0,0,0), 0,0));

        cars.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String carName = cars.getItemAt(cars.getSelectedIndex());
                engine.removeAllItems();

                for (Car car : shop.getCars()) {

                    if ((car.getManufacturer()+" "+car.getModel()).equals(carName)) {
                        EngineKind en = car.getEngineKind();
                        engine.addItem(en);
                    }
                }
            }
        });

        engine.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String carName = cars.getItemAt(cars.getSelectedIndex());
                for (Car car : shop.getCars()) {
                    if ((car.getManufacturer()+" "+car.getModel()).equals(carName)&&
                            car.getEngineKind().equals(engine.getItemAt(engine.getSelectedIndex()))) {

                        double price = car.getPrice();
                        lPriceValue.setText(Double.toString(price)+" $");
                        return;
                    }
                }


            }
        });

        buy.addActionListener( new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name  = tClient.getSelectedItem().toString();
                System.out.println(name);
                Client client =  shop.findClient(name);
//

                if (client==null) {
                   new NewClientUI(shop);
                    frame.dispose();
                } else {
                   Car car = getCarFromStorage(cars, engine);

                    if (car.getCount() == 0) {
                        new CarIsNotAvailableUI();
                        return;
                    }
                    shop.sellAuto(car, client);
                    new TableTransaction(shop);
                    frame.dispose();
                }
            }
        });
        returns.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new TableTransaction(shop);
                frame.dispose();
            }
        });

        return pan;
    }

    private Car getCarFromStorage(JComboBox<String> cars, JComboBox<EngineKind> engine) {
        String carName = cars.getItemAt(cars.getSelectedIndex());
        Car car=null;
        for (Car carr:shop.getCars()) {
            if ((carr.getManufacturer()+" "+carr.getModel()).equals(carName)&&
                    carr.getEngineKind().equals(engine.getItemAt(engine.getSelectedIndex()))) {
                car = carr;
            }
        }
        return car;
    }

    private void fillFieldCars(JComboBox<String> cars) {
        String previousCar = null;

        for (Car car : shop.getCars()) {
            String carName = car.getManufacturer()+" "+car.getModel();
            if (!carName.equals(previousCar)) {
                cars.addItem(carName);
            }
            previousCar = carName;

        }
    }

    private class RBListener implements ActionListener {
           @Override
           public void actionPerformed(ActionEvent e) {

               indexProduct = Integer.parseInt(e.getActionCommand());



           }
       }

}
