
import cz.cvut.fel.ts1.storage.ItemStock;
import cz.cvut.fel.ts1.shop.StandardItem;
import cz.cvut.fel.ts1.shop.Item;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class ItemStockTest {

    @Test
    public void testConstructor() {
        Item item = new StandardItem(1, "Test Item", 10.0f, "Test Category", 5);
        ItemStock itemStock = new ItemStock(item);

        assertEquals(item, itemStock.getItem());
        assertEquals(0, itemStock.getCount());
    }

    @Test
    public void testIncreaseItemCount() {
        Item item = new StandardItem(1, "Test Item", 10.0f, "Test Category", 5);
        ItemStock itemStock = new ItemStock(item);

        itemStock.IncreaseItemCount(5);
        assertEquals(5, itemStock.getCount());

        itemStock.IncreaseItemCount(3);
        assertEquals(8, itemStock.getCount());
    }

    @Test
    public void testDecreaseItemCount() {
        Item item = new StandardItem(1, "Test Item", 10.0f, "Test Category", 5);
        ItemStock itemStock = new ItemStock(item);

        itemStock.IncreaseItemCount(10);
        assertEquals(10, itemStock.getCount());

        itemStock.decreaseItemCount(5);
        assertEquals(5, itemStock.getCount());

        itemStock.decreaseItemCount(3);
        assertEquals(2, itemStock.getCount());
    }
}
