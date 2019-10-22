package service.impl;

import dao.INewsDAO;
import dao.impl.NewsDaoImpl;
import model.NewsModel;
import paging.PageAble;
import service.INewsService;

import javax.servlet.http.HttpServletRequest;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

public class NewsServiceImpl implements INewsService {

    private INewsDAO newsDAO;

    public NewsServiceImpl() {
        newsDAO = new NewsDaoImpl();
    }

    @Override
    public void insertNews(HttpServletRequest request, NewsModel newsModel) {
        Date date = new Date();
        newsModel.setCreatedDate(new Timestamp(date.getTime()));
        newsModel.setCreatedBy("");
        newsModel.setStatus("PENDING");
        newsDAO.insertNews(newsModel);
    }

    @Override
    public List<NewsModel> findAll(PageAble pageAble) {
        return newsDAO.getAll(pageAble);
    }

    @Override
    public void updateNews(HttpServletRequest request, NewsModel newsModel, Long id) {
        Date date = new Date();
        newsModel.setCreatedDate(new Timestamp(date.getTime()));
        newsModel.setCreatedBy("");
        newsModel.setModifiedBy("");
        newsModel.setModifiedDate(new Timestamp(date.getTime()));
        newsModel.setStatus("PENDING");
        newsDAO.updateNews(newsModel.getId(), newsModel);
    }

    @Override
    public NewsModel findNewsById(Long id) {
        return newsDAO.findNewsById(id);
    }

    @Override
    public long countAllNews() {
        return newsDAO.countAllNews();
    }
}
