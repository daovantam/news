package dao.impl;

import dao.ICategoryDAO;
import mapper.CategoryMapper;
import model.CategoryModel;

import java.util.List;

public class CategoryDaoImpl extends AbtractDAO<CategoryModel> implements ICategoryDAO {
    public static void main(String[] args) {
        ICategoryDAO categoryDAO = new CategoryDaoImpl();
        CategoryModel categoryModel = new CategoryModel();

//        categoryModel.setName("Du Lịch");
//        categoryDAO.insertCategory(categoryModel);

        categoryModel.setName("Xã Hội");
        categoryDAO.updateCategory((long) 1, categoryModel);
    }

    @Override
    public List<CategoryModel> findAll() {
        String sql = "SELECT * FROM category";
        return findByProperties(sql, new CategoryMapper());
    }

    @Override
    public void insertCategory(CategoryModel categoryModel) {
        String sql = "INSERT INTO category(name) VALUES (?)";
        insert(
                sql, categoryModel.getName()
        );
    }

    @Override
    public void updateCategory(Long id, CategoryModel categoryModel) {
        String sql = "UPDATE category\n" +
                "SET name=?\n" +
                "WHERE id=?";
        update(
                sql, categoryModel.getName(), id
        );
    }

    @Override
    public CategoryModel findCategoryById(Long id) {
        String sql = "SELECT * FROM category WHERE id = ?";
        List<CategoryModel> categoryModelList = findByProperties(sql, new CategoryMapper(), id);
        return categoryModelList.isEmpty() ? null : categoryModelList.get(0);
    }


}
