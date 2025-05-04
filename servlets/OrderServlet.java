import java.io.IOException;

import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Enumeration;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/orderServlet")
public class orderServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException {
		res.setContentType("text/html");
        PrintWriter out = res.getWriter();
        String itemName = req.getParameter("itemName");
        String itemPrice = req.getParameter("itemPrice");
     // int num = Integer.parseInt(itemPrice);
        String quantity="1";
        String total_price="500";
        try (Connection con = DBConnection.getConnection()) {
            PreparedStatement ps = con.prepareStatement("insert into orders(item_name,quantity,price,total_price) values(?,?,?,?)");
            ps.setString(1,itemName );
            ps.setInt(2,50);
            ps.setInt(3,1000 );
            ps.setInt(4,200);
            int rs=ps.executeUpdate();
         }
         catch (SQLException e) {
             e.printStackTrace();
         }
         out.println("<nav>\r\n"
        		 +"<a href=\"nonveg\">Menu</a>\r\n"
        		 + "a href=\"orderServlet\">orders</a>\r\n"
        		 + "</nav>");
        out.println("<!DOCTYPE html><html><body>");
        out.println("<h2>Item Added to Cart</h2>");
        out.println("<p><strong>Item:</strong> " + itemName + "</p>");
        out.println("<p><strong>Price:</strong> ₹" + itemPrice + "</p>");
        out.println("<a href='nonveg'>Go back</a>");
        out.println("</body></html>");
    }
}
