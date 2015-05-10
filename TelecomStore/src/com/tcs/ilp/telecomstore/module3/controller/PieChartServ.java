package com.tcs.ilp.telecomstore.module3.controller;

import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.IOException;
import java.io.OutputStream;

import javax.imageio.ImageIO;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartRenderingInfo;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.entity.StandardEntityCollection;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;

import com.tcs.ilp.telecomstore.module3.dao.PieChartDAO;

/**
 * Servlet implementation class PieChartServ
 */
public class PieChartServ extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PieChartServ() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		this.doPost(request, response);
	}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		PieChartDAO pie=new PieChartDAO();
		 PieDataset result = new DefaultPieDataset();
		result=pie.createDataset();
	
		JFreeChart chart = ChartFactory.createPieChart("Pie Chart ", result, true, true, false);
		
		response.setContentType("image/png");
		
		BufferedImage buffImg=chart.createBufferedImage(500, 400);
		ServletOutputStream out = response.getOutputStream();
		ImageIO.write((RenderedImage)buffImg, "png", out);
		request.getSession().setAttribute("Chart",chart);
		
	     } 
	
}

