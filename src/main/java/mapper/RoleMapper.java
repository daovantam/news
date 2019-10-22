package mapper;

import model.RoleModel;
import utils.MapUtil;

import java.sql.ResultSet;
import java.sql.SQLException;

public class RoleMapper implements RowMapper<RoleModel> {
    @Override
    public RoleModel map(ResultSet rs) throws SQLException {
        RoleModel roleModel = MapUtil.mapRow(new RoleModel(), rs);
        return roleModel;
    }
}
