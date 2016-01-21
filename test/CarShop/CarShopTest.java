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
    public void checkDiscount5percent () {
        double price = 35000;
        double expectedDiscount = 0.05;

        shop.setDiscount(price);
        assertEquals(expectedDiscount, shop.setDiscount(price), 0);

    }
    @Test
    public void checkDiscount10persent () {
        double price = 50000;
        double expectedDiscount = 0.1;

        shop.setDiscount(price);
        assertEquals(expectedDiscount, shop.setDiscount(price), 0);

    }
    @Test
    public void checkDiscount0persent () {
        double price = 20000;
        double expectedDiscount = 0.0;

        shop.setDiscount(price);
        assertEquals(expectedDiscount, shop.setDiscount(price), 0);

    }


}
