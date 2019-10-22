package dao;

import model.UserModel;

import java.util.List;

public interface IUserDAO extends IGenericDAO<UserModel> {
    UserModel findUserByUserNameAndPassword(String userName, String password);

    void insertUser(UserModel userModel);

    void updateUser(UserModel userModel, Long id);

    List<UserModel> findAll();

    UserModel findUserById(Long id);


}
