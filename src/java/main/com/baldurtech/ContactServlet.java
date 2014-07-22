package com.baldurtech;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.DriverManager;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ContactServlet extends HttpServlet
{
    public void doGet(HttpServletRequest req,HttpServletResponse resp) throws ServletException,IOException
    {
        resp.getWriter().println("Contact List");
        Connection conn = null;
        try
        {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
        }catch(Exception ex)
        {
            //ignore;
        }
        
        try
        {
            conn = DriverManager.getConnection("jdbc:mysql://localhost/test?user=root&password=");
            resp.getWriter().println(conn);
        }catch(SQLException ex)
        {
            resp.getWriter().println("SQLException: "  + ex.getMessage());
            resp.getWriter().println("SQLStates: " + ex.getSQLState());
            resp.getWriter().println("ERROR: " + ex.getErrorCode());
        }
    }
}
