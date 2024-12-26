package edu.uoc.epcsd.productcatalog.domain.repository;

import edu.uoc.epcsd.productcatalog.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProductRepository {

    List<Product> findAllProducts();

    Optional<Product> findProductById(Long id);

    List<Product> findProductsByExample(Product product);

    Long createProduct(Product product);

    void deleteProduct(Product product);
}
