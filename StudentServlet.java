package Servlets;

import dao.StudentDao;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import Model.Student;


@WebServlet(name = "StudentServlet", urlPatterns = {"/students"})
public class StudentServlet extends HttpServlet {

    StudentDao studentDao = new StudentDao();
    
    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException {
        String firstName = req.getParameter("firstName");
        String lastName = req.getParameter("lastName");
        String dateOfBirth = req.getParameter("dateOfBirth");

        Student student = new Student(firstName, lastName, dateOfBirth);
        StudentDao st = new StudentDao();
        student = st.createStudent(student);

        if (student != null) {
            res.getWriter().println("Student Registered");
            res.sendRedirect("students");
        } else {
            res.getWriter().println("failed to register student");
        }

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException {
        res.setContentType("text/html");

        PrintWriter out = res.getWriter();
        out.println("<!DOCTYPE html>");
        out.println("<html>");
        out.println("<head>");
        out.println("<meta charset=\"UTF-8\">");
        out.println("<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">");
        out.println("<title>Student List</title>");
        out.println("<style>");
        out.println("table {");
        out.println("    width: 100%;");
        out.println("    border-collapse: collapse;");
        out.println("}");
        out.println("table, th, td {");
        out.println("    border: 1px solid #ddd;");
        out.println("    padding: 8px;");
        out.println("    text-align: left;");
        out.println("}");
        out.println("th {");
        out.println("    background-color: #f2f2f2;");
        out.println("}");
        out.println("button {");
        out.println("    padding: 5px 10px;");
        out.println("    background-color: #007bff;");
        out.println("    color: #fff;");
        out.println("    border: none;");
        out.println("    border-radius: 3px;");
        out.println("    cursor: pointer;");
        out.println("}");
        out.println("</style>");
        out.println("</head>");
        out.println("<body>");

        out.println("<h2>Student List</h2>");
        out.println("<table>");
        out.println("<tr>");
        out.println("<th>First Name</th>");
        out.println("<th>Last Name</th>");
        out.println("<th>Date of Birth</th>");
        out.println("<th>Actions</th>");
        out.println("</tr>");

        List<Student> students = studentDao.getAllStudents();
        for (Student student : students) {
            out.println("<tr>");
            out.println("<td>" + student.getFirstName() + "</td>");
            out.println("<td>" + student.getLastName() + "</td>");
            out.println("<td>" + student.getDateOfBirth() + "</td>");
            out.println("<td>");
            out.println("<button onclick=\"updateStudent(" + student.getStudentId() + ")\">Update</button>");
            out.println("<button onclick=\"deleteStudent(" + student.getStudentId() + ")\">Delete</button>");
            out.println("</td>");
            out.println("</tr>");
        }

        out.println("</table>");

        out.println("<script>");
        out.println("function updateStudent(id) {");
        out.println("  window.location.href = 'updatest?id=' + id;");
        out.println("}");
        out.println("function deleteStudent(id) {");
        out.println("  if (confirm('Are you sure you want to delete this student?')) {");
        out.println("    window.location.href = 'students?id=' + id;");
        out.println("  }");
        out.println("}");
        out.println("</script>");

        out.println("</body>");
        out.println("</html>");
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse res) throws IOException {
        // Get the student ID from the request
        String studentIdStr = req.getParameter("studentId");

        if (studentIdStr != null && !studentIdStr.isEmpty()) {
            long studentId = Long.parseLong(studentIdStr);
            Student student = new  Student();
            student.setStudentId(studentId);
            // Call the DAO to delete the student
            Student deleted = studentDao.DeleteStudent(student);

            if (deleted != null) {
                res.getWriter().println("Student deleted successfully");
            } else {
                res.getWriter().println("Failed to delete student");
            }
        } else {
            res.getWriter().println("Invalid student ID provided");
        }
    }

}
