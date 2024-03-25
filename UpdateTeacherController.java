package controller;

import Model.Course;
import Model.EQualification;
import Model.Teacher;
import dao.TeacherDao;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class UpdateTeacherController extends HttpServlet{
    private static final long serialVersionUID = 1L;
    private TeacherDao teacherDao;
    
    public void init() {
        teacherDao = new TeacherDao();
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPut(request, response);
    }
    
      @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Update Student
       Long teacherId = Long.parseLong(request.getParameter("teacherId"));
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String qualification = request.getParameter("qualification");
        // Assuming courseId is provided
        Long courseId = Long.parseLong(request.getParameter("courseId"));

        Teacher teacher = new Teacher();
        teacher.setId(teacherId);
        teacher.setFirstName(firstName);
        teacher.setLastName(lastName);
        teacher.setQualification(EQualification.valueOf(qualification));
        Course course = new Course() ;
         course.setId(courseId);
        teacher.setCourse(course);     
        
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
        out.println("<p>Teacher Updated successfully!</p>");
        out.println("<a href=\"teacher.html\" class=\"button\">Go Back</a>");
        out.println("</div>");
        out.println("</body>");
        out.println("</html>");
    }
}
