package by.radomskaya.project;

import by.radomskaya.project.exception.DAOException;
import by.radomskaya.project.pool.ConnectionPool;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Main {
    private static final String SELECT_COMPUTER = "SELECT * FROM library.books";
    public static void main(String[] args) throws SQLException, DAOException {
        ConnectionPool connectionPool = new ConnectionPool();
        connectionPool.getConnection();
        Statement statement = connectionPool.getConnection().createStatement();
        ResultSet resultSet = statement.executeQuery(SELECT_COMPUTER);
        while (resultSet.next()) {
            System.out.println(resultSet.getInt("id_book"));
        }
    }
}
