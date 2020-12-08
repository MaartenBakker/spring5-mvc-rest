package guru.springfamework.services;

import guru.springfamework.api.v1.mapper.VendorMapper;
import guru.springfamework.api.v1.model.VendorDTO;
import guru.springfamework.domain.Vendor;
import guru.springfamework.repositories.VendorRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

public class VendorServiceImplTest {

    public static final String URL_VENDORS = "/api/v1/vendors";
    public static final String URL_VENDORS_ID_1 = URL_VENDORS + "/1";
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
        //given
        Vendor vendor1 = new Vendor();
        vendor1.setId(ID);

        Vendor vendor2 = new Vendor();

        when(vendorRepository.findAll()).thenReturn(Arrays.asList(vendor1, vendor2));

        //when
        List<VendorDTO> vendorDTOS = vendorService.getAllVendors();

        //then
        assertEquals(2, vendorDTOS.size());
        assertEquals(URL_VENDORS_ID_1, vendorDTOS.get(0).getVendorUrl());
    }

    @Test
    public void getVendorById() {
        //given
        Vendor vendor = new Vendor();
        vendor.setId(ID);
        vendor.setName(VENDOR_NAME);

        when(vendorRepository.findById(anyLong())).thenReturn(Optional.of(vendor));

        //when
        VendorDTO vendorDTO = vendorService.getVendorById(ID);

        //then
        assertNotNull(vendorDTO);
        assertEquals(VENDOR_NAME, vendorDTO.getName());
        assertEquals(URL_VENDORS_ID_1, vendorDTO.getVendorUrl());
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