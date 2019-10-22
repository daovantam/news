package dao.impl;

import dao.IRoleDAO;
import mapper.RoleMapper;
import model.RoleModel;

import java.util.List;

public class RoleDaoImpl extends AbtractDAO<RoleModel> implements IRoleDAO {
    @Override
    public RoleModel findRoleById(long id) {
        String sql = "SELECT * FROM role WHERE roleid = ?";

        List<RoleModel> list = findByProperties(sql, new RoleMapper(), id);

        return list.isEmpty() ? null : list.get(0);
    }

    @Override
    public void insertRole(RoleModel roleModel) {
        String sql = "INSERT INTO role(rolename) VALUES (?)";
        insert(
                sql, roleModel.getRoleName()
        );
    }

    @Override
    public void updateRole(RoleModel roleModel, Long id) {
        String sql = "UPDATE role\n" +
                "SET rolename=?\n" +
                "WHERE roleid=?";
        update(
                sql, roleModel.getRoleName(), id
        );
    }

    @Override
    public List<RoleModel> findAll() {
        String sql = "SELECT * FROM role";
        return findByProperties(sql, new RoleMapper());
    }


}
