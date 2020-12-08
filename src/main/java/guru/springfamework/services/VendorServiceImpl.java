package guru.springfamework.services;

import guru.springfamework.api.v1.mapper.VendorMapper;
import guru.springfamework.api.v1.model.CustomerDTO;
import guru.springfamework.api.v1.model.VendorDTO;
import guru.springfamework.repositories.VendorRepository;

import java.util.List;

public class VendorServiceImpl implements VendorService {
    private VendorRepository vendorRepository;
    private VendorMapper vendorMapper;

    public VendorServiceImpl(VendorRepository vendorRepository, VendorMapper vendorMapper) {
        this.vendorRepository = vendorRepository;
        this.vendorMapper = vendorMapper;
    }

    @Override
    public List<VendorDTO> getAllVendors() {
        return null;
    }

    @Override
    public VendorDTO getVendorById(Long Id) {
        return null;
    }

    @Override
    public VendorDTO createNewVendor(VendorDTO vendorDTO) {
        return null;
    }

    @Override
    public VendorDTO replaceVendorById(Long Id, VendorDTO vendorDTO) {
        return null;
    }

    @Override
    public VendorDTO updateVendorById(Long Id, VendorDTO vendorDTO) {
        return null;
    }

    @Override
    public void deleteVendorById(Long Id) {

    }
}
