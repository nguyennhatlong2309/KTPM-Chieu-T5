package servlets;
import beans.Cart;
import beans.User;
import model.UserDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "login", urlPatterns = "/login")
public class Login extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");           // check code 3 : chưa kiểm tra null hoặc chuỗi rỗng trước khi sử dụng đầu vào
        String pass = request.getParameter("pass");             // check code 3 : chưa kiểm tra null hoặc chuỗi rỗng trước khi sử dụng đầu vào

        if (UserDAO.login(email, pass)) {                   // check code 15 + 37: không kiểm tra giá trị đầu vào trước khi gọi DAO + Không xử lí ngoại lệ khi gọi DAO
            System.out.println("LOGIN SUCCESS");    
            /* Get the current user */
            User user = UserDAO.getUser(email);             // check code 19 + 37: không kiểm tra user có null + không xử lý ngoại lệ khi gọi DAO

            Cart cart = new Cart(user.getUserID());
            /* get new session or create new if it doesn't exist */
            HttpSession session = request.getSession(true);
            /* adding user to session to access it in other servlets */
            session.setAttribute("user", user);
            session.setAttribute("cart", cart);

            /* send it to welcome page */               // check code 47 + 49: comment chưa rõ ràng + comment không nhất quán với hành vi 
            request.setAttribute("successMessage", "Success.");     // check code 10 + 62: chuỗi nên là hằng số + Không dùng được do sendRedirect
            response.sendRedirect("index.jsp");
        } else {
            System.out.println("LOGIN FAILURE");
            System.out.println("email: " + email);

            request.setAttribute("errorMessage", "Wrong email or password.");
            request.getRequestDispatcher("login.jsp").forward(request, response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}