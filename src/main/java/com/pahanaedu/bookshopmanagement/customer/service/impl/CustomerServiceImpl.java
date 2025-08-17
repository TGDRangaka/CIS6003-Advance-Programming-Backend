package com.pahanaedu.bookshopmanagement.customer.service.impl;

import com.pahanaedu.bookshopmanagement.customer.dao.CustomerDAO;
import com.pahanaedu.bookshopmanagement.customer.dao.impl.CustomerDAOImpl;
import com.pahanaedu.bookshopmanagement.customer.dto.CustomerDTO;
import com.pahanaedu.bookshopmanagement.customer.mapping.CustomerMapping;
import com.pahanaedu.bookshopmanagement.customer.model.Customer;
import com.pahanaedu.bookshopmanagement.customer.service.CustomerService;

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
//        dto.setAccountNumber(UUID.randomUUID().toString());
        dto.setActive(true);
        dto.setUnitConsumed(0);
        Customer customer = CustomerMapping.dtoToCustomer(dto);
        customerDAO.save(customer);
    }

    @Override
    public void updateCustomer(CustomerDTO dto, String id) throws SQLException {
        Customer customer = CustomerMapping.dtoToCustomer(dto);
        customerDAO.update(customer, id);
    }

    @Override
    public void deleteCustomer(String accountNumber) throws SQLException {
        ((CustomerDAOImpl) customerDAO).delete(accountNumber);
    }

    @Override
    public boolean isAccountNumberExist(String accountNumber) throws SQLException {
        return customerDAO.isAccountNumberExist(accountNumber);
    }
}
