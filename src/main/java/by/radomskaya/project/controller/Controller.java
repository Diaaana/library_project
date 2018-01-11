package by.radomskaya.project.controller;

import by.radomskaya.project.command.ActionFactory;
import by.radomskaya.project.command.Command;
import by.radomskaya.project.command.common.EmptyCommand;
import by.radomskaya.project.exception.CommandException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

@WebServlet(name = "Servlet", urlPatterns = {"/controller"})
public class Controller extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Optional<Command> commandOptional = ActionFactory.defineCommand(request.getParameter("command"));
        Command command = commandOptional.orElse(new EmptyCommand());
        String page = null;
        try {
            page = command.execute(request);
        } catch (CommandException e) {
            e.printStackTrace();
           // request.getRequestDispatcher("jsp/start.jsp").forward(request, response); //!!!
        }

        if (page != null) {
            RequestDispatcher dispatcher = request.getRequestDispatcher(page);
            dispatcher.forward(request, response);
        } else {
            response.sendRedirect(request.getContextPath() + "index.jsp");
        }
    }
















    /*
    public Controller() {
        super();
    }
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String command = req.getParameter("command");

        if ("go_to_registration_page".equals(command)) {
            RequestDispatcher dispatcher = req.getRequestDispatcher("jsp/registration.jsp");
            dispatcher.forward(req, resp);
        } else if ("registration".equals(command)) {
            String surname = req.getParameter("surname");
            String name = req.getParameter("name");
            String middleName = req.getParameter("middleName");
            int age = (Integer.parseInt(req.getParameter("age")));
            String phone = req.getParameter("phone");
            String mail = req.getParameter("mail");
            String login = req.getParameter("login");
            String password = req.getParameter("password");

            Reader reader = new Reader();

            reader.setSurname(surname);
            reader.setName(name);
            reader.setMiddleName(middleName);
            reader.setAge(age);
            reader.setPhoneNumber(phone);
            reader.setMail(mail);
            reader.setLogin(login);
            reader.setPassword(password);


            try {
                DAOFactory.getInstance().getReaderDAO().addReader(reader);
            } catch (DAOException e) {
                e.printStackTrace();//изменить
            }

            HttpSession session = req.getSession(true);
            session.setAttribute("role", "user");
            resp.sendRedirect("Controller?command=after_registration&message=successful");
        } else if ("after_registration".equals(command)) {
            RequestDispatcher dispatcher = req.getRequestDispatcher("jsp/start.jsp");
            dispatcher.forward(req, resp);
        } else if ("login".equals(command)) {
            boolean flag = false;
            String login = req.getParameter("login");
            String password = req.getParameter("password");
            Reader reader = new Reader();
            reader.setLogin(login);
            reader.setPassword(password);

            try {
                List<Reader> listReader = DAOFactory.getInstance().getReaderDAO().getLoginPassword();
                for (int i = 0; i < listReader.size(); i++) {
                    if (listReader.get(i).getLogin().equals(login) && listReader.get(i).getPassword().equals(password)) {
                        flag = true;
                        break;
                    } else {
                        flag = false;
                    }
                }
                if (flag == true) {
                    resp.sendRedirect("Controller?command=after_login&message=successful");
                }
            } catch (DAOException e) {
                e.printStackTrace();
            }
        } else if ("after_login".equals(command)) {
            RequestDispatcher dispatcher = req.getRequestDispatcher("jsp/start.jsp");
            dispatcher.forward(req, resp);
        }

    }*/
}
