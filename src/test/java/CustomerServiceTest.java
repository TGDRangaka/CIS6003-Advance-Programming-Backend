import com.pahanaedu.bookshopmanagement.customer.dto.CustomerDTO;
import com.pahanaedu.bookshopmanagement.customer.model.Customer;
import com.pahanaedu.bookshopmanagement.customer.service.CustomerService;
import com.pahanaedu.bookshopmanagement.customer.service.impl.CustomerServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

public class CustomerServiceTest {

    private CustomerService customerService;

    @BeforeEach
    void setUp() {
        customerService = new CustomerServiceImpl();
    }

    @Test
    void testCreateCustomer() throws SQLException {
        CustomerDTO c = new CustomerDTO();
        c.setAccountNumber("CUST003");
        c.setName("John Doe");
        c.setEmail("john@mail.com");
        c.setPhoneNumber("0771234567");
        c.setUnitConsumed(0);
        c.setActive(true);

        customerService.addCustomer(c, 1);
        CustomerDTO saved = customerService.getById("CUST003");

        assertNotNull(saved);
        assertEquals("John Doe", saved.getName());
        assertEquals(0, saved.getUnitConsumed());
    }

    @Test
    void testUpdateCustomer() throws SQLException {
        CustomerDTO c = customerService.getById("CUST001");
        c.setEmail("new@mail.com");
        customerService.updateCustomer(c, c.getAccountNumber());

        CustomerDTO updated = customerService.getById("CUST001");
        assertEquals("new@mail.com", updated.getEmail());
    }
}
