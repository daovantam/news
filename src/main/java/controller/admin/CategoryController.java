package controller.admin;

import model.CategoryModel;
import service.ICategoryService;
import service.impl.CategoryServiceImpl;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/admin/category")
public class CategoryController extends HttpServlet {

    public static final String TYPE = "type";
    public static final String ACTION_EDIT = "edit";
    public static final String ACTION_CREATE = "create";
    public static final String EMPTY = "";

    private ICategoryService categoryService;

    public CategoryController() {
        this.categoryService = new CategoryServiceImpl();
    }

//    @Override
//    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        List<CategoryModel> categoryModels = categoryService.findAll();
//        req.setAttribute("listCategory", categoryModels);
//        String type = req.getParameter(TYPE);
//        String view = EMPTY;
//
//        if (type != null) {
//            if (ACTION_EDIT.equals(type)) {
//                String parameter = req.getParameter("id");
//                if (!parameter.equals("")) { // khong the null
//                    long id = Long.parseLong(parameter);
//                    CategoryModel categoryModel = categoryService.findCategoryById(id);
//                    if (categoryModel != null) {
//                        req.setAttribute("categoryModel", categoryModel);
//                        view = "/views/admin/category/edit.jsp";
//                    } else {
//                        req.setAttribute("message", "CategoryModel isn't exist");
//                    }
//
//                }
//
//            }
//            if (ACTION_CREATE.equals(type)){
//                view = "/views/admin/category/edit.jsp";
//            }
//        }else {
//            view = "/views/admin/category/list.jsp";
//        }
//        RequestDispatcher requestDispatcher = req.getRequestDispatcher(view);
//        requestDispatcher.forward(req, resp);
//    }

        @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String type = req.getParameter("type");
        String view = "/views/admin/category/list.jsp";
        if(type != null ){
            if (type.equals("edit")) {
                String i = req.getParameter("id");
                long id = Long.parseLong(i);
                CategoryModel categoryModel = categoryService.findCategoryById(id);
                req.setAttribute("categoryModel", categoryModel);
            }
            List<CategoryModel> list = categoryService.findAll();
            req.setAttribute("listCategory", list);
            view = "/views/admin/category/edit.jsp";
        }else {
            List<CategoryModel> list = categoryService.findAll();
            req.setAttribute("listCategory", list);
        }
        RequestDispatcher requestDispatcher = req.getRequestDispatcher(view);
        requestDispatcher.forward(req, resp);
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
