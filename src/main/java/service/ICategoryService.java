package service;

import model.CategoryModel;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface ICategoryService {

    List<CategoryModel> findAll();

    void insertCategory(HttpServletRequest request, CategoryModel categoryModel);

    void updateCategory(HttpServletRequest request, CategoryModel categoryModel, Long id);

    CategoryModel findCategoryById(Long id);
}
