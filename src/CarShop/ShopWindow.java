package CarShop;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.NumberFormat;
import java.util.Date;


public class ShopWindow extends JPanel {

    private Shop shop;
    private int indexProduct = 0;
    JFrame frame;

    public ShopWindow(Shop shop) {
        this.shop = shop;
        this.frame = new JFrame("Shop");
        frame.setMinimumSize(new Dimension(800, 600));
        frame.setLocation(300,100);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        frame.getContentPane().add(createShopPanel());

        frame.pack();
        frame.setVisible(true);

    }

    private JPanel createShopPanel() {
        JPanel pan = new JPanel();
        pan.setLayout(new GridBagLayout());

        JLabel lCustomer = new JLabel("Customer");

        JTextField tClient = new JTextField();
        tClient.setColumns(15);
        pan.add(lCustomer, new GridBagConstraints(0, 0, 1, 1, 0, 0,GridBagConstraints.LINE_START, GridBagConstraints.NONE, new Insets(0,0,0,0), 0,0));
        pan.add(tClient, new GridBagConstraints(1, 0, 1, 1, 0, 0,GridBagConstraints.LINE_START, 0, new Insets(0,0,0,0), 0,0));

        ButtonGroup bg = new ButtonGroup();

        JPanel radioPanel = new JPanel();
        JLabel lProduct = new JLabel("Cars");
        radioPanel.setLayout(new GridLayout(3, 0));

        ActionListener rbListener = new RBListener();
        for (int i=0; i<shop.getCars().size(); i++) {
            Car car = shop.getCars().get(i);

            JRadioButton rb = new JRadioButton(car.getManufacturer().toString() + " "+car.getModel().toString());
            rb.setActionCommand(String.valueOf(i));
            rb.addActionListener(rbListener);
            if (i==0) {
                rb.setSelected(true);
            }

            bg.add(rb);
            radioPanel.add(rb);
        }

        pan.add(lProduct, new GridBagConstraints(0, 2, 1, 1, 0, 0,GridBagConstraints.LINE_START, GridBagConstraints.NONE, new Insets(0,0,0,0), 0,0));
        pan.add(radioPanel, new GridBagConstraints(1, 2, 1, 1, 0, 0,GridBagConstraints.LINE_START, GridBagConstraints.NONE, new Insets(0,0,0,0), 0,0));


//        JLabel lCount = new JLabel("Quantity");
//        NumberFormat nf = NumberFormat.getNumberInstance();
//        JFormattedTextField tfQuantity = new JFormattedTextField(nf);
//        tfQuantity.setColumns(2);
//        tfQuantity.setValue(1);

//        pan.add(lCount, new GridBagConstraints(0, 3, 1, 1, 0, 0,GridBagConstraints.LINE_START, GridBagConstraints.NONE, new Insets(0,0,0,0), 0,0));
//        pan.add(tfQuantity, new GridBagConstraints(1, 3, 1, 1, 0, 0,GridBagConstraints.LINE_START, GridBagConstraints.NONE, new Insets(0,0,0,0), 0,0));

        JButton buy = new JButton("Buy");
        pan.add(buy, new GridBagConstraints(1, 4, 1, 1, 0, 0,GridBagConstraints.LINE_START, GridBagConstraints.NONE, new Insets(0,0,0,0), 0,0));
        buy.addActionListener( new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name  = tClient.getText();
                Client client =  shop.findClient(name);
                Car c = shop.getCars().get(indexProduct);
                shop.sellAuto(c, client);
                new TableTransaction(shop);
                frame.dispose();

            }
        });

        return pan;
    }
       private class RBListener implements ActionListener {
           @Override
           public void actionPerformed(ActionEvent e) {
               indexProduct = Integer.parseInt(e.getActionCommand());
           }
       }

}
