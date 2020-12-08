package guru.springfamework.services;


import guru.springfamework.api.v1.model.CustomerDTO;

import java.util.List;

public interface CustomerService {

    List<CustomerDTO> getAllCustomers();

    CustomerDTO getCustomerById(Long Id);

    CustomerDTO createNewCustomer(CustomerDTO customerDTO);

    CustomerDTO replaceCustomerById(Long Id, CustomerDTO customerDTO);

    CustomerDTO updateCustomerById(Long Id, CustomerDTO customerDTO);

    void deleteCustomerById(Long Id);
}
