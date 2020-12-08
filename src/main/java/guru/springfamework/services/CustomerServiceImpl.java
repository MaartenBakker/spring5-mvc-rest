package guru.springfamework.services;

import guru.springfamework.api.v1.mapper.CustomerMapper;
import guru.springfamework.api.v1.model.CustomerDTO;
import guru.springfamework.domain.Customer;
import guru.springfamework.repositories.CustomerRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CustomerServiceImpl implements CustomerService {
    public static final String CUSTOMERS_URL = "/api/v1/customers/";

    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;

    public CustomerServiceImpl(CustomerRepository customerRepository, CustomerMapper customerMapper) {
        this.customerRepository = customerRepository;
        this.customerMapper = customerMapper;
    }

    private CustomerDTO addCustomerUrlToDTO(CustomerDTO customerDTO, Long Id) {
        customerDTO.setCustomerUrl(CUSTOMERS_URL + Id);

        return customerDTO;
    }

    @Override
    public List<CustomerDTO> getAllCustomers() {

        return customerRepository
                .findAll()
                .stream()
                .map(customer -> {
                    CustomerDTO customerDTO = customerMapper.customerToCustomerDTO(customer);
                    addCustomerUrlToDTO(customerDTO, customer.getId());
                    return customerDTO;
                })
                .collect(Collectors.toList());
    }

    @Override
    public CustomerDTO getCustomerById(Long Id) {

        return customerRepository.findById(Id)
                .map(customerMapper::customerToCustomerDTO)
                .map(customer -> addCustomerUrlToDTO(customer, Id)).orElseThrow(ResourceNotFoundException::new);
    }

    @Override
    public CustomerDTO createNewCustomer(CustomerDTO customerDTO) {
        Customer customer = customerMapper.customerDTOtoCustomer(customerDTO);

        return saveAndReturnDTO(customer);
    }

    private CustomerDTO saveAndReturnDTO(Customer customer) {
        Customer savedCustomer = customerRepository.save(customer);

        CustomerDTO returnDTO = customerMapper.customerToCustomerDTO(savedCustomer);

        addCustomerUrlToDTO(returnDTO, savedCustomer.getId());

        return returnDTO;
    }

    @Override
    public CustomerDTO replaceCustomerById(Long Id, CustomerDTO customerDTO) {
        Customer customer = customerMapper.customerDTOtoCustomer(customerDTO);
        customer.setId(Id);

        return saveAndReturnDTO(customer);
    }

    @Override
    public CustomerDTO updateCustomerById(Long Id, CustomerDTO customerDTO) {
        return customerRepository.findById(Id).map(customer -> {
           if(customerDTO.getFirstName() != null) {
               customer.setFirstName(customerDTO.getFirstName());
           }

           if(customerDTO.getLastName() != null) {
               customer.setLastName(customerDTO.getLastName());
           }

           CustomerDTO returnDTO = customerMapper.customerToCustomerDTO(customerRepository.save(customer));
           addCustomerUrlToDTO(returnDTO, Id);

           return returnDTO;

        }).orElseThrow(RuntimeException::new);
    }

    @Override
    public void deleteCustomerById(Long Id) {
        customerRepository.deleteById(Id);
    }
}
