package dao;

import model.CategoryModel;

import java.util.List;

public interface ICategoryDAO extends IGenericDAO<CategoryModel> {

    List<CategoryModel> findAll();

    void insertCategory(CategoryModel categoryModel);

    void updateCategory(Long id, CategoryModel categoryModel);

    CategoryModel findCategoryById(Long id);

}
