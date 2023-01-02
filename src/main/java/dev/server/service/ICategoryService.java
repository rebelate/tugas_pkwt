package dev.server.service;

import dev.server.dto.Response;
import dev.server.dto.CategoryDto;

public interface ICategoryService {
    Response getCategoryList();

    Response getCategoryById(int categoryId);

    Response createCategory(CategoryDto categoryDto);

    Response updateCategoryById(int categoryId, CategoryDto categoryDto);

    Response deleteCategoryById(int categoryId);

    boolean alreadyExist(String name);
}
