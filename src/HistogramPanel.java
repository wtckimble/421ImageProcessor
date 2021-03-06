import java.awt.image.BufferedImage;
import javax.swing.JDialog;
import javax.swing.JFrame;

public class HistogramPanel extends javax.swing.JPanel {
  
  private JDialog dialog = null;
  private JFrame frame;
  
  /** Creates new form HistogramPanel */
  public HistogramPanel() {
    initComponents();
  }
  
  private int[] calculateHistogram(BufferedImage image) {
    int greylevelArray[] = new int[256];
    if (image == null)
      return greylevelArray;

   // write  your code here to update the histogram in greylevelArray[] 
    
    int width = image.getWidth();
        int height = image.getHeight();
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                int pixel = image.getRGB(x, y);
                int alpha = (pixel >> 24) & 0xff;
                int red = (pixel >> 16) & 0xff;
                int green = (pixel >> 8) & 0xff;
                int blue = (pixel) & 0xff;

	// add your code here to modify the RGB pixel values
                double average = (red + green + blue)/3;
                greylevelArray[(int)average] ++;
            }
        }
    
    return greylevelArray;
  }
  
  public void showDialog(BufferedImage image) {
    if (dialog == null) {
      frame = new JFrame();
      dialog = new JDialog(frame, false);
      dialog.getContentPane().add(this);
      dialog.setSize(360, 240);
      dialog.setTitle("Histogram");
    } else {
      
    }
    int histogram[] = calculateHistogram(image);
    histogramLabel1.setData(histogram);
    dialog.setVisible(true);
  }
  
  /** This method is called from within the constructor to
   * initialize the form.
   * WARNING: Do NOT modify this code. The content of this method is
   * always regenerated by the Form Editor.
   */
  // <editor-fold defaultstate="collapsed" desc=" Generated Code ">//GEN-BEGIN:initComponents
  private void initComponents() {
    histogramLabel1 = new HistogramLabel();
    jButton1 = new javax.swing.JButton();

    setLayout(new java.awt.BorderLayout());

    add(histogramLabel1, java.awt.BorderLayout.CENTER);

    jButton1.setFont(new java.awt.Font("Arial", 0, 9));
    jButton1.setText("Close");
    jButton1.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        closeActionPerformed(evt);
      }
    });

    add(jButton1, java.awt.BorderLayout.SOUTH);

  }// </editor-fold>//GEN-END:initComponents
  
  private void closeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_closeActionPerformed
    dialog.setVisible(false);
  }//GEN-LAST:event_closeActionPerformed
  
  // Variables declaration - do not modify//GEN-BEGIN:variables
  private HistogramLabel histogramLabel1;
  private javax.swing.JButton jButton1;
  // End of variables declaration//GEN-END:variables
  
}
