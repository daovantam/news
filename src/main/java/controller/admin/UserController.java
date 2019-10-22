package controller.admin;

import model.CategoryModel;
import model.RoleModel;
import model.UserModel;
import service.IRoleService;
import service.IUserService;
import service.impl.RoleServiceImpl;
import service.impl.UserServiceImpl;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/admin/user")
public class UserController extends HttpServlet {

    public static final String TYPE = "type";
    public static final String ACTION_EDIT = "edit";
    public static final String ACTION_CREATE = "create";
    public static final String EMPTY = "";

    private IUserService userService;
    private IRoleService roleService;

    public UserController() {

        this.roleService = new RoleServiceImpl();
        this.userService = new UserServiceImpl();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<UserModel> userModels = userService.findAll();
        req.setAttribute("listUser", userModels);
        String type = req.getParameter(TYPE);
        String view = EMPTY;
        if (isNotNull(type)) {
            if (ACTION_EDIT.equals(type)) {
                String parameter = req.getParameter("id");
                if (!isEmpty(parameter) && isNotNull(parameter)) {
                    Long id = Long.parseLong(parameter);
                    UserModel userModel = userService.findUserById(id);
                    if (isNotNull(userModel)) {
                        req.setAttribute("userModel", userModel);
                    } else {
                        req.setAttribute("message", "UserModel isn't exist");
                    }
                }
            }
            List<RoleModel> roleModelList = roleService.findAll();
            req.setAttribute("roles", roleModelList);
            view = "/views/admin/user/edit.jsp";
        } else {
            view = "/views/admin/user/list.jsp";
        }
        RequestDispatcher rs = req.getRequestDispatcher(view);
        rs.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }

    private boolean isNotNull(Object object) {
        return object != null;
    }

    public boolean isEmpty(String string) {
        return EMPTY.equals(string);
    }

}
