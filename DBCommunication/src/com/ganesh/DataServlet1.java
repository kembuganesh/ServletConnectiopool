package com.ganesh;



import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;







public class DataServlet1 extends HttpServlet {
	
		Connection con;
		PreparedStatement ps;
		
		public void init() {
			try {
				
				Class.forName("oracle.jdbc.driver.OracleDriver");
				con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","c##ganesh123","ganesh439");
				//create preparedstatement
				ps=con.prepareStatement("select ename,esal from emp66 where empno=?");
				
			}
			catch(Exception e) {
				e.printStackTrace();
			}
			
			
		}
		public void doGet(HttpServletRequest req,HttpServletResponse res)throws ServletException,IOException{
			
					try {
						
						//get PrintWriter object
						PrintWriter pw=res.getWriter();
						res.setContentType("text/html");
						// set value to parameter of the query
						
						
						//read form data from form page
						int no=Integer.parseInt(req.getParameter("teno"));
						
						ps.setInt(1, no);
						//Execute Query
						ResultSet rs=ps.executeQuery();
						//process th resultset object and dispaly emp details
						if(rs.next()) {
							//System.out.println("rs.next()");
							pw.println("<br>Emp name is :"+rs.getString(1));
							pw.println("<br> salary is  :"+rs.getString(2));
						}
						
						rs.close();
						pw.close();
					}catch(Exception e) {
						e.printStackTrace();
			
					}
					
		}
		public void doPost(HttpServletRequest req,HttpServletResponse res)throws ServletException,IOException{
			doGet(req,res);
		}
		public void destroy() {
			try {
				if(ps!=null) 
					ps.close();
				}
			catch(Exception e1) {
				e1.printStackTrace();
			}
			try {
				if(con!=null) 
				con.close();
			}
			catch(Exception e2) {
				e2.printStackTrace();
			}
			
			
		}
	}



