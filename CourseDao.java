
package dao;

import java.util.List;
import Model.Course;
import org.hibernate.Session;


public class CourseDao {
     public Course createCourse(Course course){
        try {
            Session ss = HibernateUtil.getSessionFactory().openSession();
            ss.save(course);
            ss.beginTransaction().commit();
            return course;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
        }
    public Course updateCourse(Course course) {
        try {
            Session ss = HibernateUtil.getSessionFactory().openSession();
            ss.update(course);
            ss.beginTransaction().commit();
            return course;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    public Course DeleteCourse(Course course) {
        try {
            Session ss = HibernateUtil.getSessionFactory().openSession();
            ss.delete(course);
            ss.beginTransaction().commit();
            return course;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    public Course findCourseById(Course course) {
        try {
            Session ss = HibernateUtil.getSessionFactory().openSession();
            Course theCourse =(Course)ss.get(Course.class, course.getId());
            return theCourse;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    public List<Course> getAllCourses() {
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            List<Course> courses = session.createQuery("FROM Course").list();
            session.close(); 
            return courses;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    public Course getCourseById(Long courseId) {
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            Course course = (Course) session.get(Course.class, courseId);
            session.close();
            return course;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
