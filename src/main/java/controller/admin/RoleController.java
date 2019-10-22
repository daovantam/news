package controller.admin;

import model.RoleModel;
import service.IRoleService;
import service.impl.RoleServiceImpl;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import static controller.admin.CategoryController.ACTION_EDIT;
import static controller.admin.CategoryController.EMPTY;
import static controller.admin.CategoryController.TYPE;
import static controller.admin.NewsController.ACTION_CREATE;

@WebServlet("/admin/role")
public class RoleController extends HttpServlet {

    public static final String TYPE = "type";
    public static final String ACTION_EDIT = "edit";
    public static final String ACTION_CREATE = "create";
    public static final String EMPTY = "";

    private IRoleService roleService;

    public RoleController() {
        this.roleService = new RoleServiceImpl();
    }

     @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
         String type = req.getParameter("type");
         String view = "/views/admin/role/list.jsp";
         if(type != null ){
             if (type.equals("edit")) {
                 String i = req.getParameter("id");
                 long id = Long.parseLong(i);
                 RoleModel roleModel = roleService.findRoleById(id);
                 req.setAttribute("roleModel", roleModel);
             }
             List<RoleModel> list = roleService.findAll();
             req.setAttribute("listRole", list);
             view = "/views/admin/role/edit.jsp";
         }else {
             List<RoleModel> list =roleService.findAll();
             req.setAttribute("listRole", list);
         }
         RequestDispatcher requestDispatcher = req.getRequestDispatcher(view);
         requestDispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}
