package guru.springfamework.services;

import guru.springfamework.api.v1.mapper.VendorMapper;
import guru.springfamework.api.v1.model.CustomerDTO;
import guru.springfamework.api.v1.model.VendorDTO;
import guru.springfamework.repositories.VendorRepository;

import java.util.List;
import java.util.stream.Collectors;

public class VendorServiceImpl implements VendorService {
    public static final String VENDORS_URL = "/api/v1/vendors";

    private VendorRepository vendorRepository;
    private VendorMapper vendorMapper;

    public VendorServiceImpl(VendorRepository vendorRepository, VendorMapper vendorMapper) {
        this.vendorRepository = vendorRepository;
        this.vendorMapper = vendorMapper;
    }

    private VendorDTO addUrlToVendorDTO(VendorDTO vendorDTO, Long Id) {
        vendorDTO.setVendorUrl(VENDORS_URL + "/" + Id);

        return  vendorDTO;
    }

    @Override
    public List<VendorDTO> getAllVendors() {
        return vendorRepository
                .findAll()
                .stream()
                .map(vendor -> {
                    VendorDTO vendorDTO = vendorMapper.vendorToVendorDTO(vendor);
                    return addUrlToVendorDTO(vendorDTO, vendor.getId());
                })
                .collect(Collectors.toList());
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
