package service;

import model.RoleModel;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface IRoleService {
    RoleModel findRoleById(long id);

    void insertRole(HttpServletRequest servletRequest, RoleModel roleModel);

    void updateRole(HttpServletRequest servletRequest, RoleModel roleModel, Long id);

    List<RoleModel> findAll();
}
