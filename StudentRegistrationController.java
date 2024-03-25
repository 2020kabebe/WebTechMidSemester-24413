package controller;

import Model.AcademicUnit;
import Model.Semester;
import Model.Student;
import dao.StudentRegistrationDao;
import Model.StudentRegistration;
import dao.StudentDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

@WebServlet("/StudentRegistrationController")
public class StudentRegistrationController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private StudentRegistrationDao studentRegistrationDao;

    public void init() {
        studentRegistrationDao = new StudentRegistrationDao();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doDelete(request, response);
    }

   @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Delete Student
        Long Id = Long.parseLong(request.getParameter("reditrationId"));
        StudentRegistration studentRegistration = new StudentRegistration();
        studentRegistration.setRegistrationId(Id);
        studentRegistrationDao.DeleteStudentRegistration(studentRegistration);
        //teacherDao.DeleteTeacher(teacher); 
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
        out.println("<p>Registration deleted successfully!</p>");
        out.println("<a href=\"studentRegistration.html\" class=\"button\">Go Back</a>");
        out.println("</div>");
        out.println("</body>");
        out.println("</html>");
    }

        @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Insert Student
        
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Long studentId = Long.parseLong(request.getParameter("studentId")) ;
        Date registrationDate=null ;
        try {
            registrationDate = dateFormat.parse(request.getParameter("registrationDate"));
        } catch (ParseException ex) {
            Logger.getLogger(StudentRegistrationController.class.getName()).log(Level.SEVERE, null, ex);
        }
        Long departmentId = Long.parseLong(request.getParameter("departmentId")) ;
        Long semesterId = Long.parseLong(request.getParameter("semesterId"));
        
        StudentRegistration studentRegis = new StudentRegistration();
        Student student = new Student();
        student.setStudentId(studentId);
        studentRegis.setStudent(student);
        studentRegis.setRegistrationDate(registrationDate);
        AcademicUnit department = new AcademicUnit();
        department.setAcademicUnitId(departmentId);
        studentRegis.setDepartment(department);
        Semester sem = new Semester();
        sem.setSemesterId(semesterId);
        studentRegis.setSemester(sem);
        
        studentRegistrationDao.createStudentRegistration(studentRegis);

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
        out.println("<p>Registration Inserted successfully!</p>");
        out.println("<a href=\"studentRegistration.html\" class=\"button\">Go Back</a>");
        out.println("</div>");
        out.println("</body>");
        out.println("</html>");
    }
}
