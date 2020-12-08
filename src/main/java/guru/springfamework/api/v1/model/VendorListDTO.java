package guru.springfamework.api.v1.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;

@Data
@AllArgsConstructor
public class VendorListDTO {

    private ArrayList<VendorDTO> vendors;

}
