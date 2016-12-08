package com.example;

import java.io.IOException;
import java.util.Properties;

import javax.batch.operations.JobOperator;
import javax.batch.runtime.BatchRuntime;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns="/")
public class PayrollSubmitterServlet extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	
		resp.getOutputStream().write("Oi, sou um Servlet!!".getBytes());
		
		try {
			resp.getOutputStream().write(("\n::START::"+Long.toString(startNewBatchJob())).getBytes());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		System.out.println("::OK::");
	}
	
	
	private long startNewBatchJob() throws Exception {
        JobOperator jobOperator = BatchRuntime.getJobOperator();
        Properties props = new Properties();
        
        ServletContext context = getServletContext();
        String path = context.getRealPath("/WEB-INF/classes/payroll-data/input.csv");
        
        props.setProperty("payrollInputDataFileName", path);
        return jobOperator.start("SimplePayrollJob", props);
	}

}
