package dao;

import model.RoleModel;

import java.util.List;

public interface IRoleDAO extends IGenericDAO<RoleModel> {
    RoleModel findRoleById(long id);

    void insertRole(RoleModel roleModel);

    void updateRole(RoleModel roleModel, Long id);

    List<RoleModel> findAll();

}
