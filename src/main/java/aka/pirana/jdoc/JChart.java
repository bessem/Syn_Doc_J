/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aka.pirana.jdoc;

import aka.pirana.jdoc.model.Consultation;
import java.awt.Color;
import java.awt.Dimension;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.swing.JPanel;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYSplineRenderer;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RectangleInsets;

/**
 *
 * @author aka
 */
public class JChart extends ApplicationFrame{
    private Long id;
    private static EntityManagerFactory factory ;
    private static EntityManager manager;
    public JChart(String title, Long callerID) {
        super(title);
        factory = Persistence.createEntityManagerFactory("jdocPU");
        manager = factory.createEntityManager();    
        id = callerID;
        JPanel jpanel = createChartPanel();
        jpanel.setPreferredSize(new Dimension(500, 270));
        getContentPane().add(jpanel);
    }

    private JPanel createChartPanel() {
        NumberAxis numberaxis = new NumberAxis("Date");
		numberaxis.setAutoRangeIncludesZero(false);
		NumberAxis numberaxis1 = new NumberAxis("%");
		numberaxis1.setAutoRangeIncludesZero(false);
		XYSplineRenderer xysplinerenderer = new XYSplineRenderer();
		XYPlot xyplot = new XYPlot(SampleGenerator(), numberaxis, numberaxis1, xysplinerenderer);
		xyplot.setBackgroundPaint(Color.lightGray);
		xyplot.setDomainGridlinePaint(Color.white);
		xyplot.setRangeGridlinePaint(Color.white);
		xyplot.setAxisOffset(new RectangleInsets(4D, 4D, 4D, 4D));
		JFreeChart jfreechart = new JFreeChart("JDocSplineRenderer", JFreeChart.DEFAULT_TITLE_FONT, xyplot, true);
		ChartUtilities.applyCurrentTheme(jfreechart);
		ChartPanel chartpanel = new ChartPanel(jfreechart);
		return chartpanel;
    }

    private XYDataset SampleGenerator() {
        manager.getTransaction().begin();
        CriteriaBuilder cb = manager.getCriteriaBuilder();
        CriteriaQuery<Consultation> query = cb.createQuery(Consultation.class);
        Root<Consultation> sm = query.from(Consultation.class);
        query.where(cb.equal(sm.get("patient.id"), id.toString()));
        TypedQuery<Consultation> q = manager.createQuery(query);
        List<Consultation> results = q.getResultList();
        XYSeries xyWeightSeries = new XYSeries("Weight");
        XYSeries xyFatSeries = new XYSeries("Fat");
        XYSeries xyMuscleSeries = new XYSeries("Muscle");
        XYSeries xyWaterSeries = new XYSeries("Water");
        int i = 2;
        results.stream().map((result) -> {
            xyWeightSeries.add(i, result.getC_weight());
            return result;
        }).map((result) -> {
            xyFatSeries.add(i, result.getC_gm());
            return result;
        }).map((result) -> {
            xyMuscleSeries.add(i, result.getC_mm());
            return result;
        }).forEach((result) -> {
            xyWaterSeries.add(i, result.getC_hm());
        });
        XYSeriesCollection xyseriescollection = new XYSeriesCollection(xyWeightSeries);
        xyseriescollection.addSeries(xyFatSeries);
        xyseriescollection.addSeries(xyMuscleSeries);
        xyseriescollection.addSeries(xyWaterSeries);
        return xyseriescollection;
    }
    
}
