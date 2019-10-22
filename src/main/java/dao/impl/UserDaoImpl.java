package dao.impl;

import dao.IUserDAO;
import mapper.UserMapper;
import model.UserModel;

import java.util.List;

public class UserDaoImpl extends AbtractDAO<UserModel> implements IUserDAO {
    @Override
    public UserModel findUserByUserNameAndPassword(String userName, String password) {
        String sql = "SELECT * FROM user Where username = ? AND password = ?";
        List<UserModel> userModels = findByProperties(sql, new UserMapper(), userName, password);
        return userModels.isEmpty() ? null : userModels.get(0);
    }

    @Override
    public void insertUser(UserModel userModel) {
        String sql = "INSERT INTO user(username, password, firstname, lastname, createddate, email, sex, roleid) VALUES (?,?,?,?,?,?,?,?)";
        insert(
                sql, userModel.getUserName(), userModel.getPassword(),
                userModel.getFirstName(), userModel.getLastName(),
                userModel.getCreatedDate(), userModel.getEmail(),
                userModel.getSex(), userModel.getRoleId()
        );
    }

    @Override
    public void updateUser(UserModel userModel, Long id) {
        String sql = "UPDATE user\n" +
                "SET username=?,password=?,\n" +
                "    firstname=?,lastname=?,\n" +
                "    createddate=?,email=?,\n" +
                "    sex=?,roleid=?\n" +
                "WHERE id=?";
        update(
                sql, userModel.getUserName(), userModel.getPassword(),
                userModel.getFirstName(), userModel.getLastName(),
                userModel.getCreatedDate(), userModel.getEmail(),
                userModel.getSex(), userModel.getRoleId(), id
        );
    }

    @Override
    public List<UserModel> findAll() {
        String sql = "SELECT * FROM user";
        return findByProperties(sql, new UserMapper());
    }

    @Override
    public UserModel findUserById(Long id) {
        String sql = "SELECT * FROM user WHERE id = ?";
        List<UserModel> list = findByProperties(sql, new UserMapper(), id);
        return list.isEmpty() ? null : list.get(0);
    }

}