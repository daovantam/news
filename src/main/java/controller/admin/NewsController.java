package controller.admin;

import model.CategoryModel;
import model.NewsModel;
import model.response.ModelResponse;
import paging.PageRequest;
import service.ICategoryService;
import service.INewsService;
import service.impl.CategoryServiceImpl;
import service.impl.NewsServiceImpl;
import utils.MapClientToServerUtil;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/admin/news")
public class NewsController extends HttpServlet {

    public static final String TYPE = "type";
    public static final String ACTION_EDIT = "edit";
    public static final String ACTION_CREATE = "create";
    public static final String EMPTY = "";


    private INewsService newsService;
    private ICategoryService categoryService;

    public NewsController() {
        this.newsService = new NewsServiceImpl();
        this.categoryService = new CategoryServiceImpl();
    }

//    @Override
//    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        List<NewsModel> newsModels = newsService.findAll();
//        req.setAttribute("models", newsModels);
//        String type = req.getParameter(TYPE);
//        String view = EMPTY;
//        if (isNotNull(type)) {
//            if (ACTION_EDIT.equals(type)) {
//                String parameter = req.getParameter("id");
//                if (!isEmpty(parameter) && isNotNull(parameter)) {
//                    Long id = Long.parseLong(parameter);
//                    NewsModel newsModel = newsService.findNewsById(id);
//                    if (isNotNull(newsModel)) {
//                        req.setAttribute("model", newsModel);
//                    } else {
//                        req.setAttribute("message", "NewsModel isn't exist");
//                    }
//                }
//            }
//            List<CategoryModel> categoryModels = categoryService.findAll();
//            req.setAttribute("categories", categoryModels);
//            view = "/views/admin/news/edit.jsp";
//        } else {
//            view = "/views/admin/news/list.jsp";
//        }
//        PageRequest pageRequest = MapClientToServerUtil.toModel(PageRequest.class,req);
//        long totalItem = newsService.countAllNews();
//        long totalPage = (long) Math.ceil((double) totalItem/pageRequest.getLimit());
//        List<NewsModel> list = newsService.findAll(pageRequest);
//        ModelResponse<NewsModel> result = new ModelResponse<>();
//        result.setList(list);
//        result.setTotalItem(totalItem);
//        result.setTotalPage(totalPage);
//        result.setPage(pageRequest.getPage());
//        req.setAttribute("model",result);
//        RequestDispatcher rs = req.getRequestDispatcher(view);
//        rs.forward(req, resp);
//
//    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String type = req.getParameter("type");
        String view = "/views/admin/news/list.jsp";
        if(type != null ){
            if (type.equals("edit")) {
                String i = req.getParameter("id");
                long id = Long.parseLong(i);
                NewsModel news = newsService.findNewsById(id);
                req.setAttribute("model", news);
            }
            List<CategoryModel> list = categoryService.findAll();
            req.setAttribute("categories", list);
            view = "/views/admin/news/edit.jsp";
        }else {
            PageRequest pageRequest = MapClientToServerUtil.toModel(PageRequest.class,req);
            long totalItem = newsService.countAllNews();
            long totalPage = (long) Math.ceil((double) totalItem/pageRequest.getLimit());
            List<NewsModel> list = newsService.findAll(pageRequest);
            ModelResponse<NewsModel> result = new ModelResponse<>();
            result.setList(list);
            result.setTotalItem(totalItem);
            result.setTotalPage(totalPage);
            result.setPage(pageRequest.getPage());
            req.setAttribute("model",result);
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
