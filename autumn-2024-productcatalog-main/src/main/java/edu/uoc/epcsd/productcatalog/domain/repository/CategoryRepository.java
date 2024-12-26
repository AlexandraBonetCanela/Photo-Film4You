package edu.uoc.epcsd.productcatalog.domain.repository;

import edu.uoc.epcsd.productcatalog.application.rest.request.FindCategoriesByCriteria;
import edu.uoc.epcsd.productcatalog.domain.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CategoryRepository {

    List<Category> findAllCategories();

    Optional<Category> findCategoryById(Long id);

    List<Category> findCategoriesByExample(Category category);

    List<Category> findCategoriesByCriteria(FindCategoriesByCriteria findCategoriesByCriteria);

    Long createCategory(Category category);

}
