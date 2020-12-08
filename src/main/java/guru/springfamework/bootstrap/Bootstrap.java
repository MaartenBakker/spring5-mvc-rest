package guru.springfamework.bootstrap;

import guru.springfamework.domain.Category;
import guru.springfamework.domain.Customer;
import guru.springfamework.domain.Vendor;
import guru.springfamework.repositories.CategoryRepository;
import guru.springfamework.repositories.CustomerRepository;
import guru.springfamework.repositories.VendorRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * Created by jt on 9/24/17.
 */
@Component
public class Bootstrap implements CommandLineRunner{

    private final CategoryRepository categoryRepository;
    private final CustomerRepository customerRepository;
    private final VendorRepository vendorRepository;

    public Bootstrap(CategoryRepository categoryRepository, CustomerRepository customerRepository, VendorRepository vendorRepository) {
        this.categoryRepository = categoryRepository;
        this.customerRepository = customerRepository;
        this.vendorRepository = vendorRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        loadCategories();
        loadCustomers();
        loadVendors();
    }

    private void loadCustomers() {
        Customer johnConnor = new Customer();
        johnConnor.setFirstName("John");
        johnConnor.setLastName("Connor");

        Customer theTerminator = new Customer();
        theTerminator.setFirstName("The");
        theTerminator.setLastName("Terminator");

        Customer roboCop = new Customer();
        roboCop.setFirstName("Rob");
        roboCop.setLastName("O'Cop");

        customerRepository.save(johnConnor);
        customerRepository.save(theTerminator);
        customerRepository.save(roboCop);

        System.out.println("CustomerData Loaded = " + customerRepository.count());
    }

    private void loadCategories() {
        Category fruits = new Category();
        fruits.setName("Fruits");

        Category dried = new Category();
        dried.setName("Dried");

        Category fresh = new Category();
        fresh.setName("Fresh");

        Category exotic = new Category();
        exotic.setName("Exotic");

        Category nuts = new Category();
        nuts.setName("Nuts");

        categoryRepository.save(fruits);
        categoryRepository.save(dried);
        categoryRepository.save(fresh);
        categoryRepository.save(exotic);
        categoryRepository.save(nuts);

        System.out.println("CategoryData Loaded = " + categoryRepository.count());
    }

    private void loadVendors() {
        Vendor vendor = new Vendor();
        vendor.setName("Vendor Stratocaster");

        Vendor vendor2 = new Vendor();
        vendor2.setName("Vendor Drecht");

        vendorRepository.save(vendor);
        vendorRepository.save(vendor2);

        System.out.println("VendorData Loaded = " + vendorRepository.count());

    }
}
