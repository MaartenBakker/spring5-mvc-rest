package guru.springfamework.controllers.v1;

import guru.springfamework.api.v1.model.CustomerDTO;
import guru.springfamework.api.v1.model.CustomerListDTO;
import guru.springfamework.services.CustomerService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(CustomerController.BASE_URL)
public class CustomerController {
    public static final String BASE_URL = "/api/v1/customers";
    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping()
    public CustomerListDTO listCustomers() {
        return new CustomerListDTO(customerService.getAllCustomers());
    }

    @GetMapping("/{userId}")
    public CustomerDTO getCustomerById (@PathVariable Long userId){

        return customerService.getCustomerById(userId);
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public CustomerDTO createNewCustomer(@RequestBody CustomerDTO customerDto) {

        return customerService.createNewCustomer(customerDto);
    }

    @PutMapping("/{userId}")
    public CustomerDTO updateCustomer(@RequestBody CustomerDTO customerDTO, @PathVariable Long userId) {

        return customerService.replaceCustomerById(userId, customerDTO);
    }

    @PatchMapping("/{userId}")
    public CustomerDTO patchCustomer(@RequestBody CustomerDTO customerDTO, @PathVariable Long userId) {

        return customerService.updateCustomerById(userId, customerDTO);
    }

    @DeleteMapping("/{userId}")
    public void deleteCustomer(@PathVariable Long userId) {
        customerService.deleteCustomerById(userId);
    }

}
