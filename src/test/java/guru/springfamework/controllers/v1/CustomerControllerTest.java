package guru.springfamework.controllers.v1;

import guru.springfamework.api.v1.model.CustomerDTO;
import guru.springfamework.controllers.RestResponseEntityExceptionHandler;
import guru.springfamework.services.CustomerService;
import guru.springfamework.services.ResourceNotFoundException;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class CustomerControllerTest extends AbstractRestControllerTest {

    public static final String FIRST_NAME = "John";
    public static final String LAST_NAME = "Connor";
    public static final String CUSTOMER_URL = "/api/v1/customers";
    public static final String CUSTOMER_URL_ID_1 = CUSTOMER_URL + "/1";

    @Mock
    CustomerService customerService;

    @InjectMocks
    CustomerController customerController;

    MockMvc mockMvc;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        mockMvc = MockMvcBuilders.standaloneSetup(customerController)
                .setControllerAdvice(new RestResponseEntityExceptionHandler())
                .build();
    }

    @Test
    public void listCustomers() throws Exception {
        CustomerDTO customer1 = new CustomerDTO();
        customer1.setFirstName(FIRST_NAME);
        customer1.setLastName(LAST_NAME);

        CustomerDTO customer2 = new CustomerDTO();
        customer2.setFirstName("Bobby");
        customer2.setLastName("Bones");

        when(customerService.getAllCustomers()).thenReturn(Arrays.asList(customer1, customer2));

        mockMvc.perform(get(CUSTOMER_URL)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.customers", hasSize(2)));
    }

    @Test
    public void getCustomerById() throws Exception {
        CustomerDTO customer1 = new CustomerDTO();
        customer1.setFirstName(FIRST_NAME);
        customer1.setLastName(LAST_NAME);

        when(customerService.getCustomerById(anyLong())).thenReturn(customer1);

        mockMvc.perform(get(CUSTOMER_URL_ID_1)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstName", equalTo(FIRST_NAME)));

    }

    @Test
    public void getCustomerByIdNotFound() throws Exception {
        when(customerService.getCustomerById(anyLong())).thenThrow(ResourceNotFoundException.class);

        mockMvc.perform(get(CUSTOMER_URL + "/11")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    public void createNewCustomer() throws Exception {
        CustomerDTO customerDto = new CustomerDTO();
        customerDto.setFirstName(FIRST_NAME);
        customerDto.setLastName(LAST_NAME);

        CustomerDTO returnCustomerDto = new CustomerDTO();
        returnCustomerDto.setFirstName(customerDto.getFirstName());
        returnCustomerDto.setLastName(customerDto.getLastName());
        returnCustomerDto.setCustomerUrl(CUSTOMER_URL_ID_1);

        when(customerService.createNewCustomer(customerDto)).thenReturn(returnCustomerDto);

        mockMvc.perform(post(CUSTOMER_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(customerDto)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.firstName", equalTo("John")))
                .andExpect(jsonPath("$.customer_url", equalTo(CUSTOMER_URL + "/1")));
    }

    @Test
    public void updateCustomer() throws Exception {
        //given
        CustomerDTO customerDto = new CustomerDTO();
        customerDto.setFirstName(FIRST_NAME);
        customerDto.setLastName(LAST_NAME);

        CustomerDTO returnCustomerDto = new CustomerDTO();
        returnCustomerDto.setFirstName(customerDto.getFirstName());
        returnCustomerDto.setLastName(customerDto.getLastName());
        returnCustomerDto.setCustomerUrl(CUSTOMER_URL_ID_1);

        when(customerService.replaceCustomerById(anyLong(), any(CustomerDTO.class))).thenReturn(returnCustomerDto);

        //when/then
        mockMvc.perform(put(CUSTOMER_URL_ID_1)
        .contentType(MediaType.APPLICATION_JSON)
        .content(asJsonString(customerDto)))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.firstName", equalTo(FIRST_NAME)))
        .andExpect(jsonPath("$.lastName", equalTo(LAST_NAME)))
        .andExpect(jsonPath("$.customer_url", equalTo(CUSTOMER_URL_ID_1)));
    }

    @Test
    public void patchCustomer() throws Exception {
        //given
        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setFirstName(FIRST_NAME);

        CustomerDTO returnDTO = new CustomerDTO();
        returnDTO.setFirstName(customerDTO.getFirstName());
        returnDTO.setLastName(LAST_NAME);
        returnDTO.setCustomerUrl(CUSTOMER_URL_ID_1);

        when(customerService.updateCustomerById(anyLong(), any(CustomerDTO.class))).thenReturn(returnDTO);

        //then/when
        mockMvc.perform(patch(CUSTOMER_URL_ID_1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(customerDTO)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstName", equalTo(FIRST_NAME)))
                .andExpect(jsonPath("$.lastName", equalTo(LAST_NAME)))
                .andExpect(jsonPath("$.customer_url", equalTo(CUSTOMER_URL_ID_1)));
    }

    @Test
    public void deleteCustomer() throws Exception {

        mockMvc.perform(delete(CUSTOMER_URL_ID_1)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        verify(customerService).deleteCustomerById(anyLong());
    }


}
