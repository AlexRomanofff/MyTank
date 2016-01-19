package CarShop;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
public class CarShopTest {
    Shop shop;
    @Before
    public void init () {
        shop = new Shop();
    }
    @Test
    public void checkDiscount () {
        double price = 35000;
        double price1 = 50000;
        double price2 = 20000;
        double expectedDiscount = 0.05;
        double expectedDiscount1 = 0.1;
        double expectedDiscount2 = 0.0;

        shop.setDiscount(price);
        assertEquals(expectedDiscount, shop.setDiscount(price), 0);
        assertEquals(expectedDiscount1, shop.setDiscount(price1), 0);
        assertEquals(expectedDiscount2, shop.setDiscount(price2), 0);

    }


}
