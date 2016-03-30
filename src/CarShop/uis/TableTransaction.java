package CarShop.uis;


import CarShop.model.Car;
import CarShop.model.Sell;
import CarShop.Shop;


import javax.swing.*;
import java.awt.*;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.List;


public class TableTransaction extends JPanel {
    private Shop shop;
    private JFrame frame;

    public TableTransaction(Shop shop) {


        this.shop = shop;
        frame = new JFrame("Transactions");
        frame.setMinimumSize(new Dimension(600, 400));
        frame.setLocation(300,100);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        frame.getContentPane().add(createTransactionPanel(shop.getSales()));

        JMenuBar menuBar = new JMenuBar();
        JMenu menu = new JMenu("      File      ");
        JMenu menu1 = new JMenu("      Edit      ");
        JMenu menu2 = new JMenu("   Reports  ");


        frame.setJMenuBar(menuBar);
        menuBar.add(menu);
        menuBar.add(menu1);
        menuBar.add(menu2);

        JMenuItem addClientItem = new JMenuItem("Add new Customer");
        JMenuItem addNewCar = new JMenuItem("Add new Car");
        menu1.add(addClientItem);
        menu1.add(addNewCar);


        JMenuItem buyMenuItem = new JMenuItem("Buy Car");
        buyMenuItem.setPreferredSize(new Dimension (100,20));
        menu.add(buyMenuItem);

        JMenuItem sellsByData = new JMenuItem("Select sales by date");
        menu2.add(sellsByData);

        JMenuItem priceList = new JMenuItem("Price List");
        menu2.add(priceList);

        sellsByData.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                 new SelectSalesByData(shop, TableTransaction.this);
            }
        });

        buyMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               frame.dispose();
               shop.createClientBase();
               shop.createStorage();
               new ShopWindow(shop);
            }
        });

        priceList.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.getContentPane().removeAll();
                frame.getContentPane().add(priceList());
                frame.setVisible(true);
                frame.pack();
            }
        });

        addClientItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new NewClientUI(shop);
            }
        });

        addNewCar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                 new NewCarUI(shop);
            }
        });

        frame.pack();
        frame.setVisible(true);
    }
    private JPanel createTransactionPanel(List<Sell> sells) {
        JPanel pan = new JPanel();
        pan.setLayout(new GridBagLayout());

        Object [] dataColumns = {"TID", "Data","Customer", "Car", "Price"};
        Object [][] data = new Object[30][5];

        fillTable(data, sells);
        JTable table = new JTable(data, dataColumns);
        table.setSelectionForeground(Color.RED);
        table.setShowGrid(false);
        JScrollPane scrollPane = new JScrollPane(table);
        table.setFillsViewportHeight(false);

        table.getColumnModel().getColumn(1).setPreferredWidth(100);
        table.getColumnModel().getColumn(2).setPreferredWidth(140);
        table.getColumnModel().getColumn(3).setPreferredWidth(150);
        table.getColumnModel().getColumn(4).setPreferredWidth(100);

        table.setPreferredScrollableViewportSize(new Dimension(500, 270));
        table.setFillsViewportHeight(true);

        pan.add(scrollPane);
        return pan;
    }

    public void fillTable(Object[][] data, List<Sell> sells) {
        int i = 0;
        Locale loc = new Locale("English");
        SimpleDateFormat sdf = new SimpleDateFormat("dd MMM yyyy",loc);

        for (Sell s : sells) {

                data[i][0] = i+1;
                data[i][1] = sdf.format(s.getData());
                data[i][2] = s.getClient().getFullName();
                data[i][3] = s.getCar().getManufacturer() + " " + s.getCar().getModel();
                data[i][4] = s.getCar().getPrice()+"$";
                i++;

        }
    }
    private JPanel priceList () {
        JPanel pan = new JPanel();
        pan.setLayout(new GridBagLayout());

        Object [] dataColumns = {"Car", "Engine", "Price"};
        Object [][] data = new Object[20][3];
        shop.createStorage();
        int i=0;
        for(Car car: shop.getCars()) {
            data[i][0] = car.getManufacturer()+" "+car.getModel();
            data[i][1] = car.getEngineKind();
            data[i][2] = car.getPrice()+"$";
            i++;
        }

        JTable table = new JTable(data, dataColumns);
        table.setSelectionForeground(Color.RED);
        table.setShowGrid(false);
        JScrollPane scrollPane = new JScrollPane(table);
        table.setFillsViewportHeight(false);

        table.getColumnModel().getColumn(0).setPreferredWidth(100);

        table.setPreferredScrollableViewportSize(new Dimension(500, 270));
        table.setFillsViewportHeight(true);

        pan.add(scrollPane);
        return pan;

}
    public void addNew (List<Sell> sells) {
        frame.getContentPane().removeAll();
        frame.getContentPane().add(createTransactionPanel(sells));
        frame.pack();
        frame.setVisible(true);
    }
}
