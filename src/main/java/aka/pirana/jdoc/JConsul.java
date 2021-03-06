/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aka.pirana.jdoc;

import aka.pirana.jdoc.model.Consultation;
import aka.pirana.jdoc.model.Patient;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import org.jfree.ui.RefineryUtilities;

/**
 *
 * @author aka
 */
public class JConsul extends javax.swing.JFrame {
    private Long callerID;
    private static EntityManagerFactory factory ;
    private static EntityManager manager;
    /**
     * Creates new form JConsul
     */
    public JConsul(){}
    public JConsul(Long id) {
        factory = Persistence.createEntityManagerFactory("jdocPU");
        manager = factory.createEntityManager();    
        initComponents();
        this.callerID = id;
        fillDataTable();
    }
    public void fillDataTable(){
        manager.getTransaction().begin();
        CriteriaBuilder cb = manager.getCriteriaBuilder();
        CriteriaQuery<Consultation> query = cb.createQuery(Consultation.class);
        Root<Consultation> sm = query.from(Consultation.class);
        query.where(cb.equal(sm.get("patient.id"), callerID.toString()));
        TypedQuery<Consultation> q = manager.createQuery(query);
        List<Consultation> results = q.getResultList();
        System.out.println(results.toString());
        List<String> columns = new ArrayList<String>();
        columns.add("Weight");
        columns.add("Fat");
        columns.add("Muscle");
        columns.add("Water");
        List<String[]> values = new ArrayList<String[]>();
        TableModel tableModel;
        for (Consultation cons : results) {
            values.add(new String[] {cons.getC_weight().toString(),cons.getC_gm().toString(),
                cons.getC_mm().toString(),cons.getC_hm().toString()});
        }
        tableModel = new DefaultTableModel(values.toArray(new Object[][] {}), columns.toArray());
        resTab = new JTable(tableModel);
        resTab.setVisible(rootPaneCheckingEnabled);
    }
     /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        weight_txt = new javax.swing.JTextField();
        gm_txt = new javax.swing.JTextField();
        mm_txt = new javax.swing.JTextField();
        hm_txt = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        resTab = new javax.swing.JTable();
        save_Btn = new javax.swing.JButton();
        chartGen_Btn = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("Weight");

        jLabel2.setText("Fat");

        jLabel3.setText("Muscle");

        jLabel4.setText("Body Water");

        resTab.setModel(new javax.swing.table.DefaultTableModel());
        jScrollPane1.setViewportView(resTab);

        save_Btn.setText("Save");
        save_Btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                save_BtnActionPerformed(evt);
            }
        });

        chartGen_Btn.setText("Draw Chart");
        chartGen_Btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chartGen_BtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel3)
                            .addComponent(jLabel2))
                        .addGap(29, 29, 29)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(gm_txt, javax.swing.GroupLayout.DEFAULT_SIZE, 56, Short.MAX_VALUE)
                                    .addComponent(mm_txt)
                                    .addComponent(weight_txt))
                                .addGap(23, 23, 23)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 307, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(hm_txt, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(60, 60, 60)
                        .addComponent(save_Btn)
                        .addGap(203, 203, 203)
                        .addComponent(chartGen_Btn)))
                .addContainerGap(128, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(41, 41, 41)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(weight_txt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(gm_txt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(mm_txt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3)))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(hm_txt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(save_Btn)
                    .addComponent(chartGen_Btn))
                .addContainerGap(234, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void save_BtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_save_BtnActionPerformed
        if(callerID.equals(null)){
            JOptionPane.showMessageDialog(this, "Unable to recieve Patient ID");  
            return;
        }
        Consultation consultation = new Consultation();
        try{
            manager.getTransaction().begin();
            consultation.setPatient(manager.find(Patient.class, callerID));
            consultation.setC_date(Date.valueOf(LocalDate.now()));
            consultation.setC_weight(Float.parseFloat(weight_txt.getText()));
            consultation.setC_gm(Float.parseFloat(gm_txt.getText()));
            consultation.setC_mm(Float.parseFloat(mm_txt.getText()));
            consultation.setC_hm(Float.parseFloat(hm_txt.getText()));
            manager.persist(consultation);
            manager.getTransaction().commit();
        }catch(Exception e){
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, e.getMessage());   
        }
    }//GEN-LAST:event_save_BtnActionPerformed

    private void chartGen_BtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chartGen_BtnActionPerformed
        JChart jChart = new JChart("Current Patient", callerID);
        jChart.pack();
        RefineryUtilities.centerFrameOnScreen(jChart);
        jChart.setVisible(rootPaneCheckingEnabled);
    }//GEN-LAST:event_chartGen_BtnActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton chartGen_Btn;
    private javax.swing.JTextField gm_txt;
    private javax.swing.JTextField hm_txt;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField mm_txt;
    private javax.swing.JTable resTab;
    private javax.swing.JButton save_Btn;
    private javax.swing.JTextField weight_txt;
    // End of variables declaration//GEN-END:variables
}
