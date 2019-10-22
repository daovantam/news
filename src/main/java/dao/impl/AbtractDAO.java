package dao.impl;

import dao.IGenericDAO;
import mapper.RowMapper;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AbtractDAO<T> implements IGenericDAO<T> {

    public Connection getConnection() {
        String url = "jdbc:mysql://localhost:3306/news";
        String user = "root";
        String password = "1234";
        try {
            Class.forName("com.mysql.jdbc.Driver");
            return DriverManager.getConnection(url, user, password);
        } catch (ClassNotFoundException e) {
            return null;
        } catch (SQLException e) {
            return null;
        }
    }

    public void insert(String sql, Object... parameters) {
        Connection connection = null;
        PreparedStatement ps = null;
        connection = getConnection();
        try {
            connection.setAutoCommit(false);
            ps = connection.prepareStatement(sql);
            setParameters(ps, parameters);
            ps.executeUpdate();
            connection.commit();
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        } finally {
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void update(String sql, Object... parameters) {
        Connection connection = null;
        PreparedStatement ps = null;
        connection = getConnection();
        try {
            connection.setAutoCommit(false);
            ps = connection.prepareStatement(sql);
            setParameters(ps, parameters);
            ps.executeUpdate();
            connection.commit();
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        } finally {
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public List<T> findByProperties(String sql, RowMapper<T> mapper, Object... parameters) {
        Connection connection = getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<T> results = new ArrayList<T>();
        try {
            ps = connection.prepareStatement(sql);
            setParameters(ps, parameters);
            rs = ps.executeQuery();
            while (rs.next()) {
                T object = mapper.map(rs);
                results.add(object);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return results;
    }

    @Override
    public long countAll(String sql, Object... parameters) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            int count = 0;
            connection = getConnection();
            statement = connection.prepareStatement(sql);
            setParameters(statement, parameters);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                count = resultSet.getInt(1);
            }
            return count;
        } catch (SQLException e) {
            return 0;
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
                if (statement != null) {
                    statement.close();
                }
                if (resultSet != null) {
                    resultSet.close();
                }
            } catch (SQLException e) {
                return 0;
            }
        }
    }

    private void setParameters(PreparedStatement ps, Object... parameters) throws SQLException {
        for (int i = 0; i < parameters.length; i++) {
            int index = i + 1;
            if (parameters[i] instanceof String) {
                ps.setString(index, (String) parameters[i]);
            } else if (parameters[i] instanceof Long) {
                ps.setLong(index, (Long) parameters[i]);
            } else if (parameters[i] instanceof Integer) {
                ps.setInt(index, (Integer) parameters[i]);
            } else if (parameters[i] instanceof Timestamp) {
                ps.setTimestamp(index, (Timestamp) parameters[i]);
            } else if (parameters[i] instanceof Boolean) {
                ps.setBoolean(index, (Boolean) parameters[i]);
            }
        }
    }
}
