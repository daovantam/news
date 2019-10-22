package mapper;

import model.CategoryModel;
import utils.MapUtil;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CategoryMapper implements RowMapper<CategoryModel> {

    @Override
    public CategoryModel map(ResultSet rs) throws SQLException {
        CategoryModel categoryModel = MapUtil.mapRow(new CategoryModel(), rs);
        return categoryModel;
    }
}
