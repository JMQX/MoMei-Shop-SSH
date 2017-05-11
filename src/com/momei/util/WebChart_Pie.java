package com.momei.util;

import java.awt.BasicStroke;
import java.awt.Color;
import java.io.PrintWriter;
import java.text.DecimalFormat;
import java.text.NumberFormat;

import javax.servlet.http.HttpSession;

import org.jfree.chart.ChartRenderingInfo;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.entity.StandardEntityCollection;
import org.jfree.chart.labels.StandardPieSectionLabelGenerator;
import org.jfree.chart.labels.StandardPieToolTipGenerator;
import org.jfree.chart.plot.PiePlot;
import org.jfree.chart.servlet.ServletUtilities;
import org.jfree.data.general.DefaultPieDataset;

public class WebChart_Pie {

    private DefaultPieDataset data = new DefaultPieDataset();

    public void setValue(String key, double value) {

        data.setValue(key, value);

    }

    public String generatePieChart(String title, HttpSession session,

                                   PrintWriter pw) {

        String filename = null;

        try {

            //创建chart对象

            PiePlot plot = new PiePlot(data);
            resetPiePlot(plot);  
            
            //在统计图片上建连结

            /*plot.setURLGenerator(new StandardPieURLGenerator("url",

                    "section"));*/

            plot.setToolTipGenerator(new StandardPieToolTipGenerator());

            JFreeChart chart = new JFreeChart("", JFreeChart.DEFAULT_TITLE_FONT,

                                              plot, true);

            chart.setBackgroundPaint(java.awt.Color.white); //设置图片的背景色

            chart.setTitle(title);

            //把生成的图片放到临时目录

            ChartRenderingInfo info = new ChartRenderingInfo(new

                    StandardEntityCollection());

            //550是图片长度，350是图片高度

            filename = ServletUtilities.saveChartAsPNG(chart, 550, 350, info,

                    session);

            ChartUtilities.writeImageMap(pw, filename, info, false);

            pw.flush();

        } catch (Exception e) {

            System.out.println("Exception - " + e.toString());

            e.printStackTrace(System.out);

            filename = "public_error_550x350.png";

        }

        return filename;

    }
    
    private static void resetPiePlot(PiePlot plot) {  
        String unitSytle = "{0}={1}({2})";  
           
         plot.setNoDataMessage("无对应的数据，请重新查询。");  
        plot.setNoDataMessagePaint(Color.red);  
           
        //指定 section 轮廓线的厚度(OutlinePaint不能为null)  
        plot.setOutlineStroke(new BasicStroke(0));  
          
         //设置第一个 section 的开始位置，默认是12点钟方向  
        plot.setStartAngle(90);           
  
        plot.setToolTipGenerator(new StandardPieToolTipGenerator(unitSytle,  
                 NumberFormat.getNumberInstance(),  
                 new DecimalFormat("0.00%")));  
           
         //指定图片的透明度  
         plot.setForegroundAlpha(0.65f);  
           
         //引出标签显示样式  
         plot.setLabelGenerator(new StandardPieSectionLabelGenerator(unitSytle,  
                NumberFormat.getNumberInstance(),  
                 new DecimalFormat("0.00%")));  
               
         //图例显示样式  
         plot.setLegendLabelGenerator(new StandardPieSectionLabelGenerator(unitSytle,  
                 NumberFormat.getNumberInstance(),  
                 new DecimalFormat("0.00%")));  
     }  
    

}