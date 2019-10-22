package service;

import model.UserModel;
import model.request.UserRequest;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface IUserService {
    UserModel findUserByUserNameAndPassword(UserRequest userRequest);

    void insertUser(HttpServletRequest servletRequest, UserModel userModel);

    void updateUser(HttpServletRequest servletRequest, UserModel userModel, Long id);

    List<UserModel> findAll();

    UserModel findUserById(Long id);
}
