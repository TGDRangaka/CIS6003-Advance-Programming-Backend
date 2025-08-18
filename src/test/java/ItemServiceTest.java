import com.pahanaedu.bookshopmanagement.item.dto.ItemDTO;
import com.pahanaedu.bookshopmanagement.item.model.Item;
import com.pahanaedu.bookshopmanagement.item.service.ItemService;
import com.pahanaedu.bookshopmanagement.item.service.impl.ItemServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

public class ItemServiceTest {

    private ItemService itemService;

    @BeforeEach
    void setUp() {
        itemService = new ItemServiceImpl();
    }

    @Test
    void testAddItem() throws SQLException {
        ItemDTO i = new ItemDTO();
        i.setId(6);
        i.setName("Data Structures Book");
        i.setCategory("Books");
        i.setPrice(1200.0);
        i.setQty(10);

        itemService.addItem(i);
        ItemDTO saved = itemService.getById(6);

        assertNotNull(saved);
        assertEquals("Data Structures Book", saved.getName());
        assertEquals(1200.0, saved.getPrice());
    }

    @Test
    void testUpdateItemStock() throws SQLException {
        ItemDTO dto = new ItemDTO();
        dto.setId(2);
        dto.setName("Exercise Book");
        dto.setPrice(500.0);
        dto.setQty(15);
        dto.setCategory("Books");
        itemService.updateItem(dto, 2);

        ItemDTO updated = itemService.getById(2);
        assertEquals(15, updated.getQty());
    }
}
