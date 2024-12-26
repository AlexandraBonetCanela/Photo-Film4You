package edu.uoc.epcsd.productcatalog.infrastructure.repository.jpa;


import edu.uoc.epcsd.productcatalog.application.rest.request.FindCategoriesByCriteria;
import edu.uoc.epcsd.productcatalog.domain.Category;
import edu.uoc.epcsd.productcatalog.domain.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.springframework.data.domain.ExampleMatcher.GenericPropertyMatchers.contains;
import static org.springframework.data.domain.ExampleMatcher.GenericPropertyMatchers.exact;

@Component
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class CategoryRepositoryImpl implements CategoryRepository {

    private final SpringDataCategoryRepository jpaRepository;

    @Override
    public List<Category> findAllCategories() {
        return jpaRepository.findAll().stream().map(CategoryEntity::toDomain).collect(Collectors.toList());
    }

    @Override
    public Optional<Category> findCategoryById(Long id) {
        return jpaRepository.findById(id).map(CategoryEntity::toDomain);
    }

    public List<Category> findCategoriesByExample(Category category) {

        ExampleMatcher matcher = ExampleMatcher.matching()
                .withMatcher("name", contains().ignoreCase())
                .withMatcher("description", contains().ignoreCase());


        CategoryEntity categoryEntity = CategoryEntity.fromDomain(category);
        if (category.getParentId() != null) {
            categoryEntity.setParent(jpaRepository.findById((category.getParentId())).orElseThrow(IllegalArgumentException::new));
        }

        Example<CategoryEntity> example = Example.of(categoryEntity, matcher);

        return jpaRepository.findAll(example).stream().map(CategoryEntity::toDomain).collect(Collectors.toList());
    }

    public List<Category> findCategoriesByCriteria(FindCategoriesByCriteria findCategoriesByCriteria) {

        ExampleMatcher matcher = ExampleMatcher.matchingAll();
        if(findCategoriesByCriteria.getName() != null) {
            matcher.withMatcher("name", contains().ignoreCase());
        }
        if(findCategoriesByCriteria.getDescription() != null) {
            matcher.withMatcher("description", contains().ignoreCase());
        }
        if(findCategoriesByCriteria.getParentId() != null) {
            matcher.withMatcher("parentId", exact());
        }

        Category category = Category.builder().name(findCategoriesByCriteria.getName()).description(findCategoriesByCriteria.getDescription()).parentId(findCategoriesByCriteria.getParentId()).build();
        CategoryEntity categoryEntity = CategoryEntity.fromDomain(category);
        Example<CategoryEntity> example = Example.of(categoryEntity, matcher);

        return jpaRepository.findAll(example).stream().map(CategoryEntity::toDomain).collect(Collectors.toList());
    }

    @Override
    public Long createCategory(Category category) {

        CategoryEntity parentCategory = null;
        if (category.getParentId() != null) {
            parentCategory = jpaRepository.findById(category.getParentId()).orElseThrow(IllegalArgumentException::new);
        }

        CategoryEntity categoryEntity = CategoryEntity.fromDomain(category);
        categoryEntity.setParent(parentCategory);

        return jpaRepository.save(categoryEntity).getId();
    }
}
