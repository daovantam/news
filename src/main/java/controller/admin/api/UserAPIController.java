package controller.admin.api;

import model.UserModel;
import service.IUserService;
import service.impl.UserServiceImpl;
import utils.ModelToJsonUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Serializable;

@WebServlet("/api/admin/user")
public class UserAPIController extends HttpServlet implements Serializable {

    private IUserService userService;

    public UserAPIController() {
        this.userService = new UserServiceImpl();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setContentType("application/json");
        UserModel userModel = ModelToJsonUtil.of(req.getReader()).toModel(UserModel.class);
        userService.insertUser(req, userModel);
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setContentType("application/json");
        UserModel userModel = ModelToJsonUtil.of(req.getReader()).toModel(UserModel.class);
        userService.updateUser(req,userModel,userModel.getId());
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doDelete(req, resp);
    }
}
