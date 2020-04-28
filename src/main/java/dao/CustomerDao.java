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
			customer.setId(rs.getLong("customerId"));
			customer.setName(rs.getString("customerName"));
			customer.setAddress(rs.getString("customerAddress"));
			customer.setPhone(rs.getString("customerPhone"));
			return customer;
		}
		return null;
	}
}
