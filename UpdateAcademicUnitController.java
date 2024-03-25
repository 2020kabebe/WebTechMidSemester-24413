package controller;

import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
//import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import dao.AcademicUnitDao;
import java.io.IOException;
import java.io.PrintWriter;
import Model.AcademicUnit;
import Model.EAcademicUnit;
import javax.servlet.annotation.WebServlet;

@WebServlet("/UpdateAcademicUnitController")
public class UpdateAcademicUnitController {
    
private AcademicUnitDao academicUnitDao;
    
    public void init() throws ServletException {
        academicUnitDao = new AcademicUnitDao(); 
    }
    
     protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
         doPut(request, response);
    }

    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Update Academic Unit
       Long academicUnitId = Long.parseLong(request.getParameter("academicUnitId"));
        String academicUnitName = request.getParameter("academicUnitName");
        String academicUnitType = request.getParameter("academicUnitType");
        String parentIdStr = request.getParameter("parentId");

        AcademicUnit academicUnit = new AcademicUnit();
        academicUnit.setAcademicUnitId(academicUnitId);
        academicUnit.setAcademicUnitName(academicUnitName);
        academicUnit.setAcademicUnitType(EAcademicUnit.valueOf(academicUnitType));
        if (parentIdStr != null && !parentIdStr.isEmpty()) {
            Long parentId = Long.parseLong(parentIdStr);
            academicUnit.setParentId(new AcademicUnit(parentId, "", null, null));
        }

        academicUnitDao.updateAcademicUnit(academicUnit);

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
        out.println("<p>Academic Unit Updated successfully!</p>");
        out.println("<a href=\"academicUnit.html\" class=\"button\">Go Back</a>");
        out.println("</div>");
        out.println("</body>");
        out.println("</html>");
    }

}
