package com.pahanaedu.bookshopmanagement.business.customer.service.impl;

import com.pahanaedu.bookshopmanagement.persistence.customerDAO.CustomerDAO;
import com.pahanaedu.bookshopmanagement.persistence.customerDAO.impl.CustomerDAOImpl;
import com.pahanaedu.bookshopmanagement.business.customer.dto.CustomerDTO;
import com.pahanaedu.bookshopmanagement.business.customer.mapping.CustomerMapping;
import com.pahanaedu.bookshopmanagement.business.customer.model.Customer;
import com.pahanaedu.bookshopmanagement.business.customer.service.CustomerService;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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
    public void addCustomer(CustomerDTO dto, int userId) throws SQLException {
        dto.setActive(true);
        dto.setUnitConsumed(0);
        Customer customer = CustomerMapping.dtoToCustomer(dto);
        customer.setUserId(userId);
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
