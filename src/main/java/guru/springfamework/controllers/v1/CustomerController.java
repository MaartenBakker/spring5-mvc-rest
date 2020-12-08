package guru.springfamework.controllers.v1;

import guru.springfamework.api.v1.model.CustomerDTO;
import guru.springfamework.api.v1.model.CustomerListDTO;
import guru.springfamework.services.CustomerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(CustomerController.BASE_URL)
public class CustomerController {
    public static final String BASE_URL = "/api/v1/customers";
    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping()
    public ResponseEntity<CustomerListDTO> listCustomers() {
        return new ResponseEntity<>(
                new CustomerListDTO(customerService.getAllCustomers()), HttpStatus.OK);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<CustomerDTO> getCustomerById (@PathVariable Long userId){

        return new ResponseEntity<>(customerService.getCustomerById(userId), HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<CustomerDTO> createNewCustomer(@RequestBody CustomerDTO customerDto) {

        return new ResponseEntity<>(customerService.createNewCustomer(customerDto), HttpStatus.CREATED);
    }

    @PutMapping("/{userId}")
    public ResponseEntity<CustomerDTO> updateCustomer(@RequestBody CustomerDTO customerDTO, @PathVariable Long userId) {

        return new ResponseEntity<>(customerService.saveCustomerByDTO(userId, customerDTO), HttpStatus.OK);
    }

    @PatchMapping("/{userId}")
    public ResponseEntity<CustomerDTO> patchCustomer(@RequestBody CustomerDTO customerDTO, @PathVariable Long userId) {

        return new ResponseEntity<>(customerService.patchCustomer(userId, customerDTO), HttpStatus.OK);
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<Void> deleteCustomer(@PathVariable Long userId) {
        customerService.deleteCustomerById(userId);

        return new ResponseEntity<>(HttpStatus.OK);
    }

}
