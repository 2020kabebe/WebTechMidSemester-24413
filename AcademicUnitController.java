package controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import dao.AcademicUnitDao;
import java.io.IOException;
import java.io.PrintWriter;
import Model.AcademicUnit;
import Model.EAcademicUnit;

@WebServlet("/AcademicUnitController")
public class AcademicUnitController extends HttpServlet {

    private AcademicUnitDao academicUnitDao;

    @Override
    public void init() throws ServletException {
        academicUnitDao = new AcademicUnitDao();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doDelete(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String academicUnitName = request.getParameter("academicUnitName");
        EAcademicUnit academicUnitType = EAcademicUnit.valueOf(request.getParameter("academicUnitType"));
        String parent = request.getParameter("parentId");
        AcademicUnit theAcademicUnit = new AcademicUnit();
        if (parent != null) {
            Long parentId = Long.parseLong(parent);
            theAcademicUnit.setAcademicUnitId(parentId);
        }else{
            theAcademicUnit = null;
        }

        AcademicUnit academicUnit = new AcademicUnit(academicUnitName, academicUnitType, theAcademicUnit);
        academicUnitDao.createAcademicUnit(academicUnit);

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
        out.println("<p>Academic Unit Inserted successfully!</p>");
        out.println("<a href=\"academicUnit.html\" class=\"button\">Go Back</a>");
        out.println("</div>");
        out.println("</body>");
        out.println("</html>");
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Long academicUnitId = Long.parseLong(request.getParameter("academicUnitId"));

        AcademicUnit academicUnit = new AcademicUnit();
        academicUnit.setAcademicUnitId(academicUnitId);
        academicUnitDao.DeleteAcademicUnit(academicUnit);

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
        out.println("<p>Academic Unit deleted successfully!</p>");
        out.println("<a href=\"academicUnit.html\" class=\"button\">Go Back</a>");
        out.println("</div>");
        out.println("</body>");
        out.println("</html>");
    }
}
