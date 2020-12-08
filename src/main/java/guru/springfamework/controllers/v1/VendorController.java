package guru.springfamework.controllers.v1;

import guru.springfamework.api.v1.model.VendorDTO;
import guru.springfamework.api.v1.model.VendorListDTO;
import guru.springfamework.services.VendorService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(VendorController.BASE_URL)
public class VendorController {
    public static final String BASE_URL = "/api/v1/vendors";
    private final VendorService vendorService;

    public VendorController(VendorService vendorService) {
        this.vendorService = vendorService;
    }

    @GetMapping
    public VendorListDTO listVendors() {
        return new VendorListDTO(vendorService.getAllVendors());
    }

    @GetMapping({"/{id}"})
    public VendorDTO getVendorById(@PathVariable Long id){
        return vendorService.getVendorById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public VendorDTO createNewVendor(@RequestBody VendorDTO vendorDTO){
        return vendorService.createNewVendor(vendorDTO);
    }

    @PutMapping({"/{id}"})
    public VendorDTO replaceVendorById(@PathVariable Long id, @RequestBody VendorDTO vendorDTO){
        return vendorService.replaceVendorById(id, vendorDTO);
    }

    @PatchMapping({"/{id}"})
    public VendorDTO updateVendorById(@PathVariable Long id, @RequestBody VendorDTO vendorDTO){
        return vendorService.updateVendorById(id, vendorDTO);
    }

    @DeleteMapping({"/{id}"})
    public void deleteVendor(@PathVariable Long id){
        vendorService.deleteVendorById(id);
    }

}
