package jp.ac.isc.cloud;

import java.io.*;
import java.sql.*;

import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.*;

/**
 * Servlet implementation class UserUpdateServlet
 */
@WebServlet("/UserUpdateServlet")
public class UserUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		try {
			Connection users = null;
			try {
				request.setCharacterEncoding("utf-8");
				Class.forName("com.mysql.jdbc.Driver");
				users = DriverManager.getConnection("jdbc:mysql://localhost/servlet_db", "root", "");
				String id = request.getParameter("updateId");
				String name = request.getParameter("updateName");
				String picture = request.getParameter("updatepicture");
				Statement state = users.createStatement();

				if (name.length() != 0) {
					state.executeUpdate("UPDATE user_table SET name='" + name + "' WHERE id ='" + id + "'");
				}
				if (picture.length() != 0) {
					state.executeUpdate("UPDATE user_table SET picture='" + picture + "' WHERE id ='" + id + "'");
				}

				state.close();
				users.close();
				response.sendRedirect("/select"); //UserSelectServletを呼び出す
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
