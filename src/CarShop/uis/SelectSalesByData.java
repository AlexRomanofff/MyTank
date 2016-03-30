package CarShop.uis;


import CarShop.Shop;
import CarShop.model.Sell;


import javax.swing.*;
import javax.swing.text.MaskFormatter;
import java.awt.*;
import java.awt.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.util.*;


public class SelectSalesByData {

    private JFrame frame;
    private Shop shop;
    TableTransaction tt;

    public SelectSalesByData (Shop shop, TableTransaction tt) {

        this.shop = shop;
        this.tt = tt;
        frame = new JFrame("Selection Sales");
        frame.setMinimumSize(new Dimension(300, 200));
        frame.setLocation(300,100);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        frame.getContentPane().add(createPanel());
        frame.pack();
        frame.setVisible(true);
    }
    private JPanel createPanel () {
        JPanel pan = new JPanel();
        pan.setLayout(new GridBagLayout());
        JFormattedTextField tf = new JFormattedTextField(format());
        tf.setColumns(10);

        JComboBox<String> dayBox = new JComboBox<>();
        fillDataBox(dayBox,1,31);

        JComboBox<String> monthBox = new JComboBox<>();
        fillDataBox(monthBox, 1, 12);

        JComboBox<String> yearBox = new JComboBox<>();
        fillDataBox(yearBox, 2015,2016);

        JButton ok = new JButton("Select");
        JButton cancel = new JButton("Cancel");

        JLabel dayLabel = new JLabel("Day");
        JLabel monthLabel = new JLabel("Month");
        JLabel yearLabel= new JLabel("Year");


        pan.add(dayLabel, new GridBagConstraints(3, 0, 1, 1, 0, 0, GridBagConstraints.NORTH, GridBagConstraints.NONE, new Insets(0,0,0,0), 0,0));
        pan.add(monthLabel, new GridBagConstraints(2, 0, 1, 1, 0, 0, GridBagConstraints.NORTH, GridBagConstraints.NONE, new Insets(0,0,0,0), 0,0));
        pan.add(yearLabel, new GridBagConstraints(1, 0, 1, 1, 0, 0, GridBagConstraints.NORTH, GridBagConstraints.NONE, new Insets(0,0,0,0), 0,0));

        pan.add(dayBox, new GridBagConstraints(3, 1, 1, 1, 0, 0, GridBagConstraints.NORTH, GridBagConstraints.NONE, new Insets(0,0,0,0), 0,0));
        pan.add(monthBox, new GridBagConstraints(2, 1, 1, 1, 0, 0, GridBagConstraints.NORTH, GridBagConstraints.NONE, new Insets(0,0,0,0), 0,0));
        pan.add(yearBox, new GridBagConstraints(1, 1, 1, 1, 0, 0, GridBagConstraints.NORTH, GridBagConstraints.NONE, new Insets(0,0,0,0), 0,0));

        pan.add(ok, new GridBagConstraints(1, 2, 1, 1, 0, 0, GridBagConstraints.NORTH, GridBagConstraints.NONE, new Insets(30,0,0,0), 0,0));
        pan.add(cancel, new GridBagConstraints(2, 2, 2, 1, 0, 0, GridBagConstraints.NORTH, GridBagConstraints.NONE, new Insets(30,25,0,0), 0,0));


        monthBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int m = Integer.valueOf(monthBox.getItemAt(monthBox.getSelectedIndex()));
                dayBox.removeAllItems();
                if(m==4 || m==6 || m==9 || m==11) {
                    fillDataBox(dayBox,1,30);
                } else if (m==2) {
                    int y = Integer.valueOf(yearBox.getItemAt(yearBox.getSelectedIndex()));
                    if(y%4==0) {
                        fillDataBox(dayBox,1,29);
                    } else {
                        fillDataBox(dayBox,1,28);
                    }
                } else {
                    fillDataBox(dayBox,1,31);
                }
            }
        });

        cancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
            }
        });

        ok.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String day = dayBox.getItemAt(dayBox.getSelectedIndex());
                String month = monthBox.getItemAt(monthBox.getSelectedIndex());
                String year = yearBox.getItemAt(yearBox.getSelectedIndex());

                Date date = Date.valueOf(year+"-"+month+"-"+day);
                tt.addNew(shop.getSalesByData(date));
        }
        });

        return pan;
    }
    private MaskFormatter format () {
       MaskFormatter form = null;
        try {
            form = new MaskFormatter("2016-##-##");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return form;
    }

    public void fillDataBox (JComboBox<String> day, int beginValue, int endValue) {
        for(int i=beginValue; i<=endValue; i++) {
            String value = Integer.toString(i);
            if (value.length()==1) {
                value = "0"+value;
            }
            day.addItem(value);
        }
    }
}
