package service;

import model.NewsModel;
import paging.PageAble;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface INewsService {
    void insertNews(HttpServletRequest request, NewsModel newsModel);

    List<NewsModel> findAll(PageAble pageAble);

    void updateNews(HttpServletRequest request, NewsModel newsModel, Long id);

    NewsModel findNewsById(Long id);

    long countAllNews();

}
