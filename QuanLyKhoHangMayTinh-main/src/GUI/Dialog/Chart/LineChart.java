package GUI.Dialog.Chart;

import DTO.ThongKe.ThongKeDoanhThuDTO;
import java.awt.BorderLayout;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.JPanel;
import org.knowm.xchart.XChartPanel;
import org.knowm.xchart.XYChart;
import org.knowm.xchart.XYChartBuilder;
import org.knowm.xchart.style.markers.SeriesMarkers;

public class LineChart extends javax.swing.JFrame {
    private ArrayList<ThongKeDoanhThuDTO> arr;
    private List<XYChart> charts;
    private List<Date> listTimeline = new ArrayList<>();
    private List<Double> listExpense = new ArrayList<>();
    private List<Double> listIncome = new ArrayList<>();
    private List<Double> listProfit = new ArrayList<>();
    private String groupBy;
    private String datePattern;
    private String xAxisChartTitle;
    private int selectedChartIndex = 0;
    
    private final String CB_VALUE_DISPLAY_ALL = "Tất cả";
    private final String CB_VALUE_EXPENSE = "Chi phí";
    private final String CB_VALUE_INCOME = "Doanh thu";
    private final String CB_VALUE_PROFIT = "Lợi nhuận";
    
    public LineChart() {
        initComponents();
        setLocationRelativeTo(null);
    }
    
    public LineChart(ArrayList<ThongKeDoanhThuDTO> arr, String groupBy) {
        initComponents();
        setLocationRelativeTo(null);
        this.arr = arr;
        this.groupBy = groupBy;
        setModelComboBox();
        handleGroupBy();
        separateData();
        this.charts = getCharts();
        displayChart(0);

        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent evt) {
                displayChart(selectedChartIndex);
            }
        });
    }
    
    private void setModelComboBox() {
        cbChartOption.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { CB_VALUE_DISPLAY_ALL, CB_VALUE_EXPENSE, CB_VALUE_INCOME, 
            CB_VALUE_PROFIT }));
    }
    
    private void separateData() {
        for (int i = 0; i < arr.size(); ++i) {
            listTimeline.add(arr.get(i).getTimeline());
            listExpense.add(Double.valueOf(Long.toString(arr.get(i).getExpense())));
            listIncome.add(Double.valueOf(Long.toString(arr.get(i).getIncome())));
            listProfit.add(Double.valueOf(Long.toString(arr.get(i).getProfit())));
        }
    }
    
    private void handleGroupBy() {
        switch (this.groupBy) {
            case "date":
                this.xAxisChartTitle = "Ngày";
                this.datePattern = "dd/MM/yyyy";
                break;
            case "month":
                this.xAxisChartTitle = "Tháng";
                this.datePattern = "MM/yyyy";
                break;
            case "year":
                this.xAxisChartTitle = "Năm";
                this.datePattern = "yyyy";
                break;
            default:
        }
    }
    
    public void displayChart(int position) {
        JPanel chartPanel = new XChartPanel<XYChart>(this.charts.get(position));
        pChartContainer.removeAll();
        pChartContainer.add(chartPanel, BorderLayout.CENTER);
        pChartContainer.repaint();
        pChartContainer.validate();
    }
    
    private List<XYChart> getCharts() {
        int numCharts = 4;
        List<XYChart> charts = new ArrayList<XYChart>();
        
        for (int i = 0; i < numCharts; ++i) {
            XYChart chart = new XYChartBuilder().xAxisTitle(this.xAxisChartTitle).yAxisTitle("VND").width(1280).height(960).build();
            chart.getStyler().setDatePattern(this.datePattern);
            chart.getStyler().setYAxisDecimalPattern("###,###,### VND");
            switch (i) {
                case 0:
                    chart.addSeries(CB_VALUE_EXPENSE, this.listTimeline, this.listExpense).setMarker(SeriesMarkers.NONE);
                    chart.addSeries(CB_VALUE_INCOME, this.listTimeline, this.listIncome).setMarker(SeriesMarkers.NONE);
                    chart.addSeries(CB_VALUE_PROFIT, this.listTimeline, this.listProfit).setMarker(SeriesMarkers.NONE);
                    break;
                case 1:
                    chart.addSeries(CB_VALUE_EXPENSE, this.listTimeline, this.listExpense).setMarker(SeriesMarkers.NONE);
                    break;
                case 2:
                    chart.addSeries(CB_VALUE_INCOME, this.listTimeline, this.listIncome).setMarker(SeriesMarkers.NONE);
                    break;
                case 3:
                    chart.addSeries(CB_VALUE_PROFIT, this.listTimeline, this.listProfit).setMarker(SeriesMarkers.NONE);
                    break;
            }
            chart.getStyler().setCursorEnabled(true);
            charts.add(chart);
        }
        return charts;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pContainer = new javax.swing.JPanel();
        pHeader = new javax.swing.JPanel();
        cbChartOption = new javax.swing.JComboBox<>();
        pChartContainer = new javax.swing.JPanel();
        placeholder = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Thống kê doanh thu");

        pContainer.setLayout(new java.awt.BorderLayout());

        pHeader.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        pHeader.setPreferredSize(new java.awt.Dimension(800, 38));

        cbChartOption.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        cbChartOption.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbChartOptionActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pHeaderLayout = new javax.swing.GroupLayout(pHeader);
        pHeader.setLayout(pHeaderLayout);
        pHeaderLayout.setHorizontalGroup(
            pHeaderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pHeaderLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(cbChartOption, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(250, Short.MAX_VALUE))
        );
        pHeaderLayout.setVerticalGroup(
            pHeaderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pHeaderLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(cbChartOption, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pContainer.add(pHeader, java.awt.BorderLayout.PAGE_START);

        pChartContainer.setLayout(new java.awt.BorderLayout());

        javax.swing.GroupLayout placeholderLayout = new javax.swing.GroupLayout(placeholder);
        placeholder.setLayout(placeholderLayout);
        placeholderLayout.setHorizontalGroup(
            placeholderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 800, Short.MAX_VALUE)
        );
        placeholderLayout.setVerticalGroup(
            placeholderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 562, Short.MAX_VALUE)
        );

        pChartContainer.add(placeholder, java.awt.BorderLayout.CENTER);

        pContainer.add(pChartContainer, java.awt.BorderLayout.CENTER);

        getContentPane().add(pContainer, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cbChartOptionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbChartOptionActionPerformed
        // TODO add your handling code here:
        // String.valueOf(cbChartOption.getSelectedItem())
        switch (String.valueOf(cbChartOption.getSelectedItem())) {
            case CB_VALUE_DISPLAY_ALL:
                selectedChartIndex = 0;
                break;
            case CB_VALUE_EXPENSE:
                selectedChartIndex = 1;
                break;
            case CB_VALUE_INCOME:
                selectedChartIndex = 2;
                break;
            case CB_VALUE_PROFIT:
                selectedChartIndex = 3;
                break;
        }
        displayChart(selectedChartIndex);
    }//GEN-LAST:event_cbChartOptionActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(LineChart.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(LineChart.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(LineChart.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(LineChart.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new LineChart().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> cbChartOption;
    private javax.swing.JPanel pChartContainer;
    private javax.swing.JPanel pContainer;
    private javax.swing.JPanel pHeader;
    private javax.swing.JPanel placeholder;
    // End of variables declaration//GEN-END:variables
}
