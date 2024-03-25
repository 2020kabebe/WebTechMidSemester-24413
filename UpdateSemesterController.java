package controller;

import Model.Semester;
import dao.SemesterDao;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@WebServlet("/UpdateSemesterController")
public class UpdateSemesterController extends HttpServlet {

    private SemesterDao semesterDao;
    
    @Override
    public void init() throws ServletException {
        semesterDao = new SemesterDao(); 
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPut(request, response);
    }
    
    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Update Semester
        Long semesterId = Long.parseLong(request.getParameter("semesterId"));
        String semesterName = request.getParameter("semesterName");
        Date startingDate = null;
        Date endDate = null;
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            startingDate = dateFormat.parse(request.getParameter("startingDate"));
            endDate = dateFormat.parse(request.getParameter("endDate"));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        Semester semester = new Semester(semesterId, semesterName, startingDate, endDate);
        semesterDao.updateSemester(semester); 

        PrintWriter out = response.getWriter();
        out.println("<!DOCTYPE html>");
        out.println("<html>");
        out.println("<head>");
        out.println("<title>Message</title>");
        out.println("<style>");
        out.println("body {");
        out.println("    font-family: Arial, sans-serif;");
        out.println("    background-color: #f4f4f4;");
        out.println("}");
        out.println(".container {");
        out.println("    margin: 0 auto;");
        out.println("    padding: 20px;");
        out.println("    background-color: #fff;");
        out.println("    border-radius: 8px;");
        out.println("    box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);");
        out.println("    width: 300px;");
        out.println("}");
        out.println(".button {");
        out.println("    display: block;");
        out.println("    width: 100%;");
        out.println("    padding: 10px;");
        out.println("    text-align: center;");
        out.println("    background-color: #007bff;");
        out.println("    color: #fff;");
        out.println("    text-decoration: none;");
        out.println("    border: none;");
        out.println("    border-radius: 5px;");
        out.println("    cursor: pointer;");
        out.println("}");
        out.println("</style>");
        out.println("</head>");
        out.println("<body>");
        out.println("<div class=\"container\">");
        out.println("<h2>Success</h2>");
        out.println("<p>Semester Updated successfully!</p>");
        out.println("<a href=\"semester.html\" class=\"button\">Go Back</a>");
        out.println("</div>");
        out.println("</body>");
        out.println("</html>");
    }
}
