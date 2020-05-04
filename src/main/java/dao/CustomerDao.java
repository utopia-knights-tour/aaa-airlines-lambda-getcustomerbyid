package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import entity.Customer;

public class CustomerDao {

	private Connection connection;

	public CustomerDao(Connection connection) {
		this.connection = connection;
	}

	public Customer get(Long id) throws SQLException {
		PreparedStatement pstmt = connection.prepareStatement("SELECT * FROM Customer WHERE Customer.customerId = ?");
		pstmt.setLong(1, id);
		ResultSet rs = pstmt.executeQuery();
		if (rs.next()) {
			Customer customer = new Customer();
			customer.setCustomerId(rs.getLong("customerId"));
			customer.setCustomerName(rs.getString("customerName"));
			customer.setCustomerAddress(rs.getString("customerAddress"));
			customer.setCustomerPhone(rs.getString("customerPhone"));
			return customer;
		}
		return null;
	}
}
