package service.impl;

import dao.IRoleDAO;
import dao.impl.RoleDaoImpl;
import model.RoleModel;
import service.IRoleService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class RoleServiceImpl implements IRoleService {

    private IRoleDAO roleDAO;

    public RoleServiceImpl() {
        this.roleDAO = new RoleDaoImpl();
    }

    @Override
    public RoleModel findRoleById(long id) {
        return roleDAO.findRoleById(id);
    }

    @Override
    public void insertRole(HttpServletRequest servletRequest, RoleModel roleModel) {
        roleDAO.insertRole(roleModel);
    }

    @Override
    public void updateRole(HttpServletRequest servletRequest, RoleModel roleModel, Long id) {
        roleDAO.updateRole(roleModel, roleModel.getRoleId());
    }

    @Override
    public List<RoleModel> findAll() {
        return roleDAO.findAll();
    }
}
