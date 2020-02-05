package websocket;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/UserNameServlet")
public class UserNameServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter printWriter = response.getWriter();
        HttpSession httpSession = request.getSession(true);
        String username = request.getParameter("username");
        httpSession.setAttribute("username", username);
        if (username != null) {
            printWriter.println("   <html>                                                                                                               ");
            printWriter.println("   <head><title> WebSocket </title> </head>                                                                             ");
            printWriter.println("     <body>                                                                                                             ");
            printWriter.println("       <mark> username: " + username + "</mark><br>                                                                     ");
            printWriter.println("       <textarea id=\"messagesTextArea\" readonly = \"readonly\" rows = \"10\" cols = \"45\"> </textarea><br>           ");
            printWriter.print("         <input type=\"text\" id = \"messageText\" size=\"50\" />                                                         ");
            printWriter.println("       <input type =\"button\" value=\"Send\" onclick=\"sendMessage();\"/>                                              ");
            printWriter.println("       <script>                                                                                                         ");
            printWriter.println("          var websocket = new WebSocket(\"ws://localhost:8080/WebSocket/chatroomServerEndpoint\");                      ");
            printWriter.println("          websocket.onmessage = function processMessage(message) {                                                      ");
            printWriter.println("          var jsonData = JSON.parse(message.data);                                                                      ");
            printWriter.println("          if(jsonData.message != null)  messagesTextArea.value += jsonData.message + \"\\n\";                           ");
            printWriter.println("          }                                                                                                             ");
            printWriter.println("          function sendMessage() {                                                                                      ");
            printWriter.println("          websocket.send(messageText.value);                                                                            ");
            printWriter.println("          messageText.value = \"\";                                                                                     ");
            printWriter.println("          }                                                                                                             ");
            printWriter.println("        </script>                                                                                                       ");
            printWriter.println("     </body>                                                                                                            ");
            printWriter.println("  </html>                                                                                                               ");
        }
    }
}
