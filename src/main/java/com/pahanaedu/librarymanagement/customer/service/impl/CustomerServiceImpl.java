package com.pahanaedu.librarymanagement.customer.service.impl;

import com.pahanaedu.librarymanagement.customer.dao.CustomerDAO;
import com.pahanaedu.librarymanagement.customer.dao.impl.CustomerDAOImpl;
import com.pahanaedu.librarymanagement.customer.dto.CustomerDTO;
import com.pahanaedu.librarymanagement.customer.mapping.CustomerMapping;
import com.pahanaedu.librarymanagement.customer.model.Customer;
import com.pahanaedu.librarymanagement.customer.service.CustomerService;

import javax.servlet.ServletException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class CustomerServiceImpl implements CustomerService {

    private final CustomerDAO customerDAO = new CustomerDAOImpl();

    @Override
    public List<CustomerDTO> getAll() throws SQLException {
        List<Customer> customers = customerDAO.getAll();
        List<CustomerDTO> customerDTOList = new ArrayList<>();

        for (Customer customer : customers) {
            customerDTOList.add(CustomerMapping.customerToDto(customer));
        }

        return customerDTOList;
    }

    @Override
    public CustomerDTO getById(String accountNumber) throws SQLException {
        Customer customer = ((CustomerDAOImpl) customerDAO).getById(accountNumber);
        if (customer != null) {
            return CustomerMapping.customerToDto(customer);
        }
        return null;
    }

    @Override
    public void addCustomer(CustomerDTO dto) throws SQLException {
        dto.setAccountNumber(UUID.randomUUID().toString());
        Customer customer = CustomerMapping.dtoToCustomer(dto);
        customerDAO.save(customer);
    }

    @Override
    public void updateCustomer(CustomerDTO dto) throws SQLException {
        Customer customer = CustomerMapping.dtoToCustomer(dto);
        customerDAO.update(customer);
    }

    @Override
    public void deleteCustomer(String accountNumber) throws SQLException {
        ((CustomerDAOImpl) customerDAO).delete(accountNumber);
    }
}
