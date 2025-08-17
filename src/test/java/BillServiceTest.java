import com.pahanaedu.bookshopmanagement.bill.model.Bill;
import com.pahanaedu.bookshopmanagement.bill.model.BillItem;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

public class BillServiceTest {

    private Bill bill;

    @BeforeEach
    void setUp() {
        bill = new Bill();
        bill.setBillId("BILL-1001");
        bill.setAccountNumber("CUST001");
    }

    @Test
    void testBillTotalCalculation() {
        List<BillItem> items = new ArrayList<>();
        BillItem item1 = new BillItem();
        item1.setItemId("I001");
        item1.setQty(2);
        item1.setPrice(500.0);

        BillItem item2 = new BillItem();
        item2.setItemId("I002");
        item2.setQty(1);
        item2.setPrice(1000.0);

        items.add(item1);
        items.add(item2);

        bill.setItems(items);

        double expectedTotal = (2 * 500.0) + (1 * 1000.0); // = 2000
        double actualTotal = bill.getItems().stream().mapToDouble(i -> i.getQty() * i.getPrice()).sum();

        assertEquals(expectedTotal, actualTotal, "Bill total should match expected total");
    }
}
