package guru.springfamework.services;

import guru.springfamework.api.v1.model.CustomerDTO;
import guru.springfamework.api.v1.model.VendorDTO;

import java.util.List;

public interface VendorService {

    List<VendorDTO> getAllVendors();

    VendorDTO getVendorById(Long Id);

    VendorDTO createNewVendor(VendorDTO vendorDTO);

    VendorDTO replaceVendorById(Long Id, VendorDTO vendorDTO);

    VendorDTO updateVendorById(Long Id, VendorDTO vendorDTO);

    void deleteVendorById(Long Id);

}
