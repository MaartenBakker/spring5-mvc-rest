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

    private CustomerDTO addCustomerUrlToDTO(CustomerDTO customerDTO, Long id) {
        customerDTO.setCustomerUrl(CUSTOMERS_URL + id);

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
    public CustomerDTO getCustomerById(Long id) {
        Optional<Customer> customerOptional = customerRepository.findById(id);

        return customerOptional
                .map(customerMapper::customerToCustomerDTO)
                .map(customer -> addCustomerUrlToDTO(customer, id)).orElse(null);
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
    public CustomerDTO saveCustomerByDTO(Long id, CustomerDTO customerDTO) {
        Customer customer = customerMapper.customerDTOtoCustomer(customerDTO);
        customer.setId(id);

        return saveAndReturnDTO(customer);
    }

    @Override
    public CustomerDTO patchCustomer(Long id, CustomerDTO customerDTO) {
        return customerRepository.findById(id).map(customer -> {
           if(customerDTO.getFirstName() != null) {
               customer.setFirstName(customerDTO.getFirstName());
           }

           if(customerDTO.getLastName() != null) {
               customer.setLastName(customerDTO.getLastName());
           }

           CustomerDTO returnDTO = customerMapper.customerToCustomerDTO(customerRepository.save(customer));
           addCustomerUrlToDTO(returnDTO, id);

           return returnDTO;

        }).orElseThrow(RuntimeException::new);
    }
}
