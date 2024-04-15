
import cz.cvut.fel.ts1.shop.Order;
import cz.cvut.fel.ts1.shop.StandardItem;
import cz.cvut.fel.ts1.shop.ShoppingCart;
import cz.cvut.fel.ts1.shop.Item;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class OrderTest {

    @Test
    public void testConstructorWithState() {
        ShoppingCart cart = new ShoppingCart();
        Item item1 = new StandardItem(1, "Item 1", 10.0f, "Category", 5);
        Item item2 = new StandardItem(2, "Item 2", 15.0f, "Category", 8);
        cart.addItem(item1);
        cart.addItem(item2);

        Order order = new Order(cart, "John Doe", "123 Main St", 1);

        assertEquals("John Doe", order.getCustomerName());
        assertEquals("123 Main St", order.getCustomerAddress());
        assertEquals(1, order.getState());
        assertEquals(2, order.getItems().size());
        assertTrue(order.getItems().contains(item1));
        assertTrue(order.getItems().contains(item2));
    }

    @Test
    public void testConstructorWithoutState() {
        ShoppingCart cart = new ShoppingCart();
        Item item1 = new StandardItem(1, "Item 1", 10.0f, "Category", 5);
        Item item2 = new StandardItem(2, "Item 2", 15.0f, "Category", 8);
        cart.addItem(item1);
        cart.addItem(item2);

        Order order = new Order(cart, "Jane Doe", "456 Elm St");

        assertEquals("Jane Doe", order.getCustomerName());
        assertEquals("456 Elm St", order.getCustomerAddress());
        assertEquals(0, order.getState());
        assertEquals(2, order.getItems().size());
        assertTrue(order.getItems().contains(item1));
        assertTrue(order.getItems().contains(item2));
    }
}
