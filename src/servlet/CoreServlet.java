package servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import util.SignUtil;
import service.CoreService;
/**
 * Created by limingwu on 2016/10/26.
 */

@WebServlet(name = "CoreServlet")


public class CoreServlet extends HttpServlet {
    private static final long serialVersionUID = 4440739483644821986L;


    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String signature = request.getParameter("signature");

        String timestamp = request.getParameter("timestamp");

        String nonce = request.getParameter("nonce");

        String echostr = request.getParameter("echostr");

        PrintWriter out = response.getWriter();

        if (SignUtil.checkSignature(signature, timestamp, nonce)) {
            out.print(echostr);
        }
        out.close();
        out = null;
    }


    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");


        String respMessage = CoreService.processRequest(request);


        PrintWriter out = response.getWriter();
        out.print(respMessage);
        out.close();
    }

}
