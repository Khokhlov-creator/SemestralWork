import cz.cvut.fel.ts1.shop.EShopController;
import cz.cvut.fel.ts1.shop.Item;
import cz.cvut.fel.ts1.shop.ShoppingCart;
import cz.cvut.fel.ts1.storage.NoItemInStorage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class EShopControllerTests {

    private EShopController eShopController;

    @BeforeEach
    void setUp() {
        eShopController = new EShopController();
        EShopController.startEShop();
    }

    @Test
    void testPurchaseShoppingCartWithItems() {
        // Arrange
        ShoppingCart cart = eShopController.newCart();
        Item item1 = mock(Item.class);
        Item item2 = mock(Item.class);
        when(item1.getName()).thenReturn("Item 1");
        when(item2.getName()).thenReturn("Item 2");
        cart.addItem(item1);
        cart.addItem(item2);

        // Act and Assert
        // Verify that an error message is printed for an empty cart
        assertThrows(NoItemInStorage.class, () -> {
            eShopController.purchaseShoppingCart(cart, "John Doe", "123 Main St.");
        });
        // There's no direct way to assert the printed message, so we could check if a certain method was called
        // Alternatively, we can refactor the EShopController to throw a custom exception for this scenario
    }



    @Test
    void testPurchaseShoppingCartWithEmptyCart() {
        // Arrange
        ShoppingCart cart = EShopController.newCart();

        // Act and Assert
        // Verify that an error message is printed for an empty cart
        assertThrows(NoItemInStorage.class, () -> {
            EShopController.purchaseShoppingCart(cart, "John Doe", "123 Main St.");
        });
        // There's no direct way to assert the printed message, so we could check if a certain method was called
        // Alternatively, we can refactor the EShopController to throw a custom exception for this scenario
    }

}
