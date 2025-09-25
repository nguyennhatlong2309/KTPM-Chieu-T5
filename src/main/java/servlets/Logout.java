package servlets;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "logout", urlPatterns = "/logout")       
public class Logout extends HttpServlet {           // thiếu header comment cho class

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {       // thiếu comment mô tả hành vi của phương thức

        response.setContentType("text/html");
        response.setStatus(HttpServletResponse.SC_OK);
        request.getSession().invalidate();      // check code 3 + 37: Không kiểm tra session có tồn tai trước khi gọi invalidate()
        request.getRequestDispatcher("index.jsp").include(request,response);        // check code 5: thiếu xử lý thông báo hoặc xác nhận đăng xuất
    }
}
