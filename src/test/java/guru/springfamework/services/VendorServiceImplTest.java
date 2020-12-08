package guru.springfamework.services;

import guru.springfamework.api.v1.mapper.VendorMapper;
import guru.springfamework.repositories.VendorRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import static org.junit.Assert.*;

public class VendorServiceImplTest {

    public static final String URL_PREFIX = "/api/v1/vendors/";
    public static final Long ID = 1L;
    public static final String VENDOR_NAME = "Pot Vendory";

    VendorService vendorService;

    @Mock
    VendorRepository vendorRepository;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        vendorService = new VendorServiceImpl(vendorRepository, VendorMapper.INSTANCE);
    }

    @Test
    public void getAllVendors() {
    }

    @Test
    public void getVendorById() {
    }

    @Test
    public void createNewVendor() {
    }

    @Test
    public void replaceVendorById() {
    }

    @Test
    public void updateVendorById() {
    }

    @Test
    public void deleteVendorById() {
    }
}