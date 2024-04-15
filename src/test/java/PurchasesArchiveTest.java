import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.HashMap;

import org.junit.jupiter.api.Test;

import cz.cvut.fel.ts1.archive.ItemPurchaseArchiveEntry;
import cz.cvut.fel.ts1.shop.Item;
import cz.cvut.fel.ts1.shop.Order;
import cz.cvut.fel.ts1.shop.StandardItem;
import cz.cvut.fel.ts1.archive.PurchaseArchive;

public class PurchasesArchiveTest {

    @Test
    public void testPrintItemPurchaseStatistics() {
        // Mock ItemPurchaseArchiveEntry
        ItemPurchaseArchiveEntry entry1 = mock(ItemPurchaseArchiveEntry.class);
        when(entry1.toString()).thenReturn("ITEM  Item 1   HAS BEEN SOLD 5 TIMES");

        ItemPurchaseArchiveEntry entry2 = mock(ItemPurchaseArchiveEntry.class);
        when(entry2.toString()).thenReturn("ITEM  Item 2   HAS BEEN SOLD 3 TIMES");

        // Mock HashMap
        HashMap<Integer, ItemPurchaseArchiveEntry> itemPurchaseArchive = new HashMap<>();
        itemPurchaseArchive.put(1, entry1);
        itemPurchaseArchive.put(2, entry2);

        // Create PurchasesArchive instance
        PurchaseArchive purchasesArchive = new PurchaseArchive(itemPurchaseArchive, new ArrayList<>());
    }
    @Test
    public void testGetHowManyTimesHasBeenItemSold() {
        // Mock ItemPurchaseArchiveEntry
        ItemPurchaseArchiveEntry entry1 = mock(ItemPurchaseArchiveEntry.class);
        when(entry1.getCountHowManyTimesHasBeenSold()).thenReturn(5);

        ItemPurchaseArchiveEntry entry2 = mock(ItemPurchaseArchiveEntry.class);
        when(entry2.getCountHowManyTimesHasBeenSold()).thenReturn(3);

        // Mock HashMap
        HashMap<Integer, ItemPurchaseArchiveEntry> itemPurchaseArchive = new HashMap<>();
        itemPurchaseArchive.put(1, entry1);
        itemPurchaseArchive.put(2, entry2);

        // Create PurchasesArchive instance
        PurchaseArchive purchasesArchive = new PurchaseArchive(itemPurchaseArchive, new ArrayList<>());

        // Test
        assertEquals(5, purchasesArchive.getHowManyTimesHasBeenItemSold(new StandardItem(1, "", 0.0f, "", 0)));
        assertEquals(3, purchasesArchive.getHowManyTimesHasBeenItemSold(new StandardItem(2, "", 0.0f, "", 0)));
    }
    @Test
    public void testPutOrderToPurchasesArchive() {
        // Mock Order
        Order order = mock(Order.class);

        // Prepare items
        Item item1 = new StandardItem(1, "Item 1", 10.0f, "Category", 5);
        Item item2 = new StandardItem(2, "Item 2", 15.0f, "Category", 8);

        // Create a list of items
        ArrayList<Item> items = new ArrayList<>();
        items.add(item1);
        items.add(item2);

        // Mock the behavior of the order object
        when(order.getItems()).thenReturn(items);

        // Mock ItemPurchaseArchiveEntry
        ItemPurchaseArchiveEntry entry1 = mock(ItemPurchaseArchiveEntry.class);
        ItemPurchaseArchiveEntry entry2 = mock(ItemPurchaseArchiveEntry.class);

        // Mock HashMap
        HashMap<Integer, ItemPurchaseArchiveEntry> itemPurchaseArchive = new HashMap<>();
        itemPurchaseArchive.put(1, entry1);
        itemPurchaseArchive.put(2, entry2);

        // Create PurchasesArchive instance
        PurchaseArchive purchasesArchive = new PurchaseArchive(itemPurchaseArchive, new ArrayList<>());

        // Invoke the method under test
        purchasesArchive.putOrderToPurchasesArchive(order);

        // Verify interactions with mock objects
        verify(order).getItems(); // Ensure getItems() method is called on the order
        verify(entry1).increaseCountHowManyTimesHasBeenSold(1); // Verify interactions with entry1
        verify(entry2).increaseCountHowManyTimesHasBeenSold(1); // Verify interactions with entry2
    }

}
