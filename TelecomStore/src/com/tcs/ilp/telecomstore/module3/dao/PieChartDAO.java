package com.tcs.ilp.telecomstore.module3.dao;



import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JFrame;

import org.apache.log4j.Logger;
import org.jfree.chart.ChartFactory;

import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PiePlot3D;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;
import org.jfree.util.Rotation;
import org.jfree.util.SortOrder;

import com.tcs.ilp.telecomstore.module3.connect.ConnectionFactory;
import com.tcs.ilp.telecomstore.module3.log4j.Log4j;


public class PieChartDAO extends JFrame {

	private static final long serialVersionUID = 1L;
	
	
	Logger _appLogger=Log4j.get_appLogger();

	public  PieDataset createDataset() {
		
		int r_id=777;
		System.out.println(r_id);
		 DefaultPieDataset result = new DefaultPieDataset();
		// private static Logger _appLogger=logger.get_appLogger();
	        try{
	        	Connection con=ConnectionFactory.getConnection();
	    		Statement stmt=null;
	    		ResultSet rs1=null,rs2=null,rs3=null,rs4=null;
	    		
	    		String sql1="select count(product_status) from PRODUCT_STOCK_TBL p  join ORDER_TBL o on p.order_id=o.order_id  where (p.product_status='Available' or p.product_status='Allocated')and o.retailer_id="+r_id+"";
	    		stmt=con.createStatement();
	    		rs1=stmt.executeQuery(sql1);
	    		while(rs1.next())
	    		{
	    			result.setValue("Processing", rs1.getInt(1));
	    		}		
	    		
	    		String sql2="select count(product_status) from PRODUCT_STOCK_TBL p  join ORDER_TBL o on p.order_id=o.order_id  where  p.product_status='Dispatched' and o.retailer_id="+r_id+"";
	    		rs2=stmt.executeQuery(sql2);
	    		while(rs2.next())
	    		{
	    			result.setValue("Dispatched", rs2.getInt(1));
	    		}		
	    		
	    		
	    		
	    		String sql3="select count(product_status) from PRODUCT_STOCK_TBL p  join ORDER_TBL o on p.order_id=o.order_id  where  p.product_status='Cancelled' and o.retailer_id="+r_id+"";
	    		rs3=stmt.executeQuery(sql3);
	    		while(rs3.next())
	    		{
	    			result.setValue("Cancelled", rs3.getInt(1));
	    		}		
	    			
	    		
	        
	        	String sql4="select count(product_status) from PRODUCT_STOCK_TBL p  join ORDER_TBL o on p.order_id=o.order_id  where  p.product_status='Received' and o.retailer_id="+r_id+" ";
	        	rs4=stmt.executeQuery(sql4);
	        	while(rs4.next())
	        	{
    			result.setValue("Received", rs4.getInt(1));
	        	}		
	        		
	        	}
	    		catch(SQLException e)
	    		{
	    			_appLogger.error("Exception" +e.getMessage());
	    			//System.out.println(e);
	    			//_appLogger.error(e);}
	    		}
	        result.sortByKeys(SortOrder.DESCENDING);
	    	return result;
	        
	    }
	 
	 
	 public JFreeChart createChart(PieDataset dataset, String title) {
	        
	        JFreeChart chart = ChartFactory.createPieChart3D(
	            title,  				// chart title
	            dataset,                // data
	            true,                   // include legend
	            true,
	            false
	        );

	        PiePlot3D plot = (PiePlot3D) chart.getPlot();
	        plot.setStartAngle(290);
	        plot.setDirection(Rotation.CLOCKWISE);
	        plot.setForegroundAlpha(0.5f);
	        return chart;
	        
	    }	


	

	
	 
	

		 
	 }



