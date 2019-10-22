package dao;

import model.NewsModel;
import paging.PageAble;

import java.util.List;

public interface INewsDAO extends IGenericDAO<NewsModel> {
    void insertNews(NewsModel model);

    void updateNews(Long id, NewsModel updateModel);

    List<NewsModel> getAll(PageAble pageAble);

    NewsModel findNewsById(Long id);

    long countAllNews();

}
