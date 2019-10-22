package mapper;

import model.UserModel;
import utils.MapUtil;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserMapper implements RowMapper<UserModel> {
    @Override
    public UserModel map(ResultSet rs) throws SQLException {
        UserModel user = MapUtil.mapRow(new UserModel(), rs);
        return user;
    }
}
