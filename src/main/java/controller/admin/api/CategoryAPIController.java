package controller.admin.api;

import model.CategoryModel;
import service.ICategoryService;
import service.impl.CategoryServiceImpl;
import utils.ModelToJsonUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Serializable;

@WebServlet("/api/admin/category")
public class CategoryAPIController extends HttpServlet implements Serializable {

    private ICategoryService categoryService;

    public CategoryAPIController() {
        this.categoryService = new CategoryServiceImpl();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setContentType("application/json");
        CategoryModel categoryModel = ModelToJsonUtil.of(req.getReader()).toModel(CategoryModel.class);
        categoryService.insertCategory(req,categoryModel);
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setContentType("application/json");

        CategoryModel categoryModel = ModelToJsonUtil.of(req.getReader()).toModel(CategoryModel.class);
        categoryService.updateCategory(req,categoryModel,categoryModel.getId());
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doDelete(req, resp);
    }
}
