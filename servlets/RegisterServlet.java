package com.RegisterApp;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import jakarta.servlet.ServletException;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class RegisterServlet extends HttpServlet {
    /**
	 * 
	 */

	private static final long serialVersionUID = 1L;
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    String username = request.getParameter("username");
	    String email = request.getParameter("email");
	    String password = request.getParameter("password");

	    response.setContentType("text/html");
	    try (Connection conn = DBConnection.getConnection();
	         PreparedStatement stmt = conn.prepareStatement("INSERT INTO users (username, email, password) VALUES (?, ?, ?)")) {

	        stmt.setString(1, username);
	        stmt.setString(2, email);
	        stmt.setString(3, password);

	        int rowsInserted = stmt.executeUpdate();

	        if (rowsInserted > 0) {
	            // ✅ Redirect to login page after successful registration
	            response.sendRedirect("Login.html");
	        } else {
	            PrintWriter out = response.getWriter();
	            out.println("<h3>Registration failed</h3>");
	        }
	    } catch (SQLException e) {
	        PrintWriter out = response.getWriter();
	        if (e.getMessage().contains("Duplicate entry")) {
	            out.println("<h3>Error: Username or Email already exists. Choose a different one.</h3>");
	        } else {
	            e.printStackTrace();
	            out.println("<h3>Error: " + e.getMessage() + "</h3>");
	        }
	    }
	}
}
