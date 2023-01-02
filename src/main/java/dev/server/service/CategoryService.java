package dev.server.service;

import dev.server.dto.CategoryDto;
import dev.server.dto.Response;
import dev.server.entity.Category;
import dev.server.repository.CategoryRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

@Service
public class CategoryService implements ICategoryService {
    private final CategoryRepository categoryRepository;
    Logger logger = LoggerFactory.getLogger("Category Service");
    private static final String NOT_EXIST = "Category does not exist";
    private static final String CREATE_FAILED = "Failed creating new category";
    private static final String CREATE_SUCCESS = "Category created successfully";

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public Response getCategoryList() {
        return Response.generate(categoryRepository.findAll());
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
        var category = new Category();
        if (categoryDto.name() != null
        ) {
            category.setName(categoryDto.name());
            return Response.generate(CREATE_SUCCESS, categoryRepository.save(category));
        } else return Response.generate(BAD_REQUEST, CREATE_FAILED);
    }

    @Override
    public Response updateCategoryById(int categoryId, CategoryDto categoryDto) {
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
        return Response.generate(categoryRepository.save(category));
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
}
