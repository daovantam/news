package controller.web;

import model.request.UserRequest;
import security.Authentication;
import service.IRoleService;
import service.IUserService;
import service.impl.RoleServiceImpl;
import service.impl.UserServiceImpl;
import utils.MapClientToServerUtil;
import utils.SessionUtil;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = {"/login", "/logout"})
public class LoginController extends HttpServlet {

    private IUserService userService;
    private IRoleService roleService;

    public LoginController() {
        this.userService = new UserServiceImpl();
        this.roleService = new RoleServiceImpl();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String message = req.getParameter("message");
        String action = req.getParameter("action");
        if (message != null) {
            if (message.equals("loginError"))
                req.setAttribute("message", "Tài khoản hoặc mật khẩu bị sai");
            else if (message.equals("permissionDenied"))
                req.setAttribute("message", "Bạn không đủ quyền truy cập vào đường dẫn này");
            else if (message.equals("dontLogin"))
                req.setAttribute("message", "Bạn chưa đăng nhập");
        }
        if (action != null && action.equals("logout")) {
            resp.sendRedirect("/trangChu");
            SessionUtil.removeValue(req, "USER");

        } else {
            RequestDispatcher requestDispatcher = req.getRequestDispatcher("/views/login.jsp");
            requestDispatcher.forward(req, resp);
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserRequest userRequest = MapClientToServerUtil.toModel(UserRequest.class, req);
        String url = Authentication
                .newModel(userRequest.getUserName()
                        , userRequest.getPassword())
                .urlRedirect(req);

        resp.sendRedirect(url);
    }
}
