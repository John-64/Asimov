package controller;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import model.*;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "ServletRemoveFromCart", value = "/ServletRemoveFromCart")
public class ServletRemoveFromCart extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");

        int id_product = Integer.parseInt(request.getParameter("id_product"));

        HttpSession session = request.getSession();
        CartBean cartBean = (CartBean) session.getAttribute("cart");
        UserBean userBean = (UserBean) session.getAttribute("user");
        CartDAO cartDAO = new CartDAO();
        ProductDAO productDAO = new ProductDAO();

        cartBean.removeProduct(id_product);

        if(userBean != null){
            cartDAO.doDelete(userBean.getId_utente());

            for(ConnectionProductCart connection : cartBean.getCartList()){
                cartDAO.doSave(userBean.getId_utente(), connection.getId_product(), connection.getQuantity());
            }
        }

        JSONArray jsonArray = new JSONArray();

        for(ConnectionProductCart connection : cartBean.getCartList()){
            JSONObject jsonProduct = new JSONObject();

            ProductBean p = productDAO.doRetrieveById(connection.getId_product());

            jsonProduct.put("id", p.getId());
            jsonProduct.put("nome", p.getNome());
            jsonProduct.put("qty", connection.getQuantity());
            jsonProduct.put("img", p.getImmagine());
            jsonProduct.put("price", p.getPrezzo() * connection.getQuantity());

            jsonArray.put(jsonProduct);
        }

        session.setAttribute("cart", cartBean);
        PrintWriter out = response.getWriter();
        out.write(String.valueOf(jsonArray));
        out.flush();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
