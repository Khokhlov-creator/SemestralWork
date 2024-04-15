

import cz.cvut.fel.ts1.shop.StandardItem;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

public class StandardItemTest {

    @Test
    public void testConstructor() {
        StandardItem item = new StandardItem(1, "Test Item", 10.0f, "Test Category", 5);
        assertEquals(1, item.getID());
        assertEquals("Test Item", item.getName());
        assertEquals(10.0f, item.getPrice(), 0.001);
        assertEquals("Test Category", item.getCategory());
        assertEquals(5, item.getLoyaltyPoints());
    }

    @Test
    public void testCopy() {
        StandardItem original = new StandardItem(1, "Original Item", 20.0f, "Original Category", 10);
        StandardItem copy = original.copy();
        assertEquals(original, copy);
        assertNotSame(original, copy);
    }

    @Test
    public void testEquals() {
        StandardItem item1 = new StandardItem(1, "Item 1", 15.0f, "Category", 8);
        StandardItem item2 = new StandardItem(1, "Item 1", 15.0f, "Category", 8);
        StandardItem item3 = new StandardItem(2, "Item 2", 20.0f, "Category", 8);

        assertEquals(item1, item2);
        assertNotEquals(item1, item3);
    }
}
