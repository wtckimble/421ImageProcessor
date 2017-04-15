import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JComponent;

public class HistogramLabel extends JComponent {
  
  private int marginTop = 5;
  private int marginLeft = 5;
  
  private Color plotColor;
  private int dataarray[] = null;
  
  public HistogramLabel() {
    this(Color.WHITE);
  }
  
  public HistogramLabel(Color plotColor) {
    this.plotColor = plotColor;
  }
   
  public void paint(Graphics graphics) {
    Graphics2D g2d = (Graphics2D)graphics;
    int width = this.getWidth();
    int height = this.getHeight();
    g2d.setColor(Color.BLACK);
    g2d.fillRect(0, 0, width, height);
    g2d.setColor(plotColor);
    g2d.drawRect(marginLeft, marginTop, width - marginLeft * 2, height - marginTop * 2);
    if (dataarray == null)
      return;
    double xstep = (double)(width - marginLeft * 2) / (dataarray.length + 1);
    int maxdata = 0;
    int sum = 0;
    int total = 0;
    for (int i=1; i<dataarray.length-1; i++) {
      if (dataarray[i] > maxdata) maxdata = dataarray[i];
      sum += dataarray[i] * i;
      total += dataarray[i];
    }
    sum /= total;
    for (int i=0; i<dataarray.length; i++) {
      int x = (int)(marginLeft + ((i+1) * xstep));
      int starty = height - marginTop;
      double length = dataarray[i] / (double)maxdata;
      int endy = (int)(starty - (length * (height - marginTop * 2)));
      g2d.drawLine(x, starty, x, endy);
    }
    g2d.setColor(Color.YELLOW);
    int sumX = (int)(marginLeft+((sum+1)*xstep));
    g2d.drawLine(sumX, height - marginTop, sumX, marginTop);    

  }
  
  public void setData(int dataarray[]) {
    this.dataarray = dataarray;
  }
  
}
