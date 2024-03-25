package controller;

import dao.UserDao;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import Model.Role;
import Model.User;

@WebServlet("/SignUpServlet")
public class SignUpServlet extends HttpServlet{
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String role = request.getParameter("role"); // Convert role string to enum
        
        User newUser = new User();
        newUser.setEmail(email);
        newUser.setPassword(password);
        newUser.setStatus(Role.valueOf(role));
        
        UserDao userDao = new UserDao();
        userDao.createUser(newUser);
        
        // Redirect to login page
        response.sendRedirect("login.html");
    }
}
