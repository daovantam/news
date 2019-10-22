package service.impl;

import dao.ICategoryDAO;
import dao.impl.CategoryDaoImpl;
import model.CategoryModel;
import service.ICategoryService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class CategoryServiceImpl implements ICategoryService {

    private ICategoryDAO categoryDAO;

    public CategoryServiceImpl() {

        categoryDAO = new CategoryDaoImpl();
    }

    @Override
    public List<CategoryModel> findAll() {

        return categoryDAO.findAll();

    }

    @Override
    public void insertCategory(HttpServletRequest request, CategoryModel categoryModel) {
        categoryDAO.insertCategory(categoryModel);
    }

    @Override
    public void updateCategory(HttpServletRequest request, CategoryModel categoryModel, Long id) {
        categoryDAO.updateCategory(categoryModel.getId(), categoryModel);
    }

    @Override
    public CategoryModel findCategoryById(Long id) {
        return categoryDAO.findCategoryById(id);
    }


}
