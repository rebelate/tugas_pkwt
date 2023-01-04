package dev.server.service;

import dev.server.dto.CategoryDto;
import dev.server.payload.Response;
import dev.server.entity.Category;
import dev.server.repository.CategoryRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Map;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

@Service
public class CategoryService implements ICategoryService {
    private final CategoryRepository categoryRepository;
    Logger logger = LoggerFactory.getLogger("Category Service");
    private static final String ALREADY_EXIST = "Category already exist";
    private static final String NOT_EXIST = "Category does not exist";
    private static final String CREATE_FAIL = "Failed creating new category";
    private static final String CREATE_SUCCESS = "Category created successfully";
    private static final String UPDATE = "Category updated successfully";

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public Response getCategoryList() {
        var allCategories = categoryRepository.findAll();
        var categories = Map.of("names", allCategories.stream().map(Category::getName).toList());
        return Response.generate(categories);
    }

    @Override
    public Response getCategoryById(int categoryId) {
        var optionalCategory = categoryRepository.findById(categoryId);
        if (optionalCategory.isEmpty()) {
            return Response.generate(BAD_REQUEST, NOT_EXIST);
        }
        return Response.generate(optionalCategory.get());
    }

    @Override
    public Response createCategory(CategoryDto categoryDto) {
        if (alreadyExist(categoryDto.name())) return Response.generate(BAD_REQUEST, ALREADY_EXIST);

        var category = new Category();
        if (categoryDto.name() != null
        ) {
            category.setName(categoryDto.name());
        } else {
            return Response.generate(BAD_REQUEST, CREATE_FAIL);
        }
        return Response.generate(CREATE_SUCCESS, categoryRepository.save(category));
    }

    @Override
    public Response updateCategoryById(int categoryId, CategoryDto categoryDto) {
        if (alreadyExist(categoryDto.name())) return Response.generate(BAD_REQUEST, ALREADY_EXIST);

        var optionalCategory = categoryRepository.findById(categoryId);
        if (optionalCategory.isEmpty()) {
            return Response.generate(BAD_REQUEST, NOT_EXIST);
        }
        var category = optionalCategory.get();
        if (categoryDto.name() != null
        ) {
            category.setName(categoryDto.name());
        }
        logger.info("UPDATED BOOK WITH ID " + category.getId());
        return Response.generate(UPDATE, categoryRepository.save(category));
    }

    @Override
    public Response deleteCategoryById(int categoryId) {
        var bookExist = categoryRepository.existsById(categoryId);
        if (bookExist)
            categoryRepository.deleteById(categoryId);
        else
            return Response.generate(BAD_REQUEST, NOT_EXIST);
        logger.info("DELETED CATEGORY WITH ID " + categoryId);
        return Response.generate(String.format("id %s deleted", categoryId));
    }

    @Override
    public boolean alreadyExist(String name) {
        return categoryRepository.findByName(name).isPresent();
    }
}
