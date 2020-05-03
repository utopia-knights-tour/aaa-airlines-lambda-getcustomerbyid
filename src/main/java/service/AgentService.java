package service;

import java.sql.Connection;
import java.sql.SQLException;

import dao.CustomerDao;
import datasource.HikariCPDataSource;
import entity.Customer;

public class AgentService {

	public Customer getCustomerById(Long id) throws ClassNotFoundException, SQLException {
		Connection connection = null;
		try {
			connection = HikariCPDataSource.getConnection();
			return new CustomerDao(connection).get(id);
		} catch (SQLException e) {
			throw e;
		} finally {
			connection.close();
		}
	}
}
