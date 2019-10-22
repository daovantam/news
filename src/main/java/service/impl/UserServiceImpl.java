package service.impl;

import dao.IUserDAO;
import dao.impl.UserDaoImpl;
import model.UserModel;
import model.request.UserRequest;
import service.IUserService;

import javax.servlet.http.HttpServletRequest;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

public class UserServiceImpl implements IUserService {

    private IUserDAO userDAO;

    public UserServiceImpl() {
        this.userDAO = new UserDaoImpl();
    }

    @Override
    public UserModel findUserByUserNameAndPassword(UserRequest userRequest) {
        return userDAO.findUserByUserNameAndPassword(userRequest.getUserName(), userRequest.getPassword());
    }

    @Override
    public void insertUser(HttpServletRequest servletRequest, UserModel userModel) {
        Date date = new Date();
        userModel.setCreatedDate(new Timestamp(date.getTime()));
        userDAO.insertUser(userModel);
    }

    @Override
    public void updateUser(HttpServletRequest servletRequest, UserModel userModel, Long id) {
        Date date = new Date();
        userModel.setCreatedDate(new Timestamp(date.getTime()));
        userDAO.updateUser(userModel, userModel.getId());
    }

    @Override
    public List<UserModel> findAll() {
        return userDAO.findAll();
    }

    @Override
    public UserModel findUserById(Long id) {
        return userDAO.findUserById(id);
    }


}
