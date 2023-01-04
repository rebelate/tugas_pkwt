package dev.server.controller;

import dev.server.dto.CategoryDto;
import dev.server.payload.Response;
import dev.server.service.CategoryService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/categories")
public class CategoryController {
    private final CategoryService userService;

    public CategoryController(CategoryService userService) {
        this.userService = userService;
    }

    @GetMapping
    Response Get() {
        return userService.getCategoryList();
    }

    @PostMapping
    Response Post(@RequestBody CategoryDto categoryDto) {
        return userService.createCategory(categoryDto);
    }

    @GetMapping("/{categoryId}")
    Response Get(@PathVariable("categoryId") int id) {
        return userService.getCategoryById(id);
    }

    @PutMapping("/{categoryId}")
    Response Put(@PathVariable("categoryId") int id, @RequestBody CategoryDto categoryDto) {
        return userService.updateCategoryById(id, categoryDto);
    }

    @DeleteMapping("/{CategoryDto}")
    Response Delete(@PathVariable("CategoryDto") int id) {
        return userService.deleteCategoryById(id);
    }

}
