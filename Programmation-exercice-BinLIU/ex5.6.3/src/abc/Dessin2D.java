package abc;
import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Panel;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import javax.swing.JComponent;
import javax.swing.JFrame;

@SuppressWarnings("serial")
class DrawComponent extends JComponent{
    private static final int DEFAULT_WIDTH=600;
    private static final int DEFAULT_HEIGHT=400;
    
    ArrayList<Object> squres;
    //Rectangle2D rect;
    Object obj;
    int SQURESIZE = 30;
    int flag;
    
    
    public DrawComponent (int flag){
    	this.flag=flag;
    	setBackground(Color.white);
    	
    	squres = new ArrayList<Object>();
    	obj = null;

		addMouseListener(new MouseMoniter());
		addMouseMotionListener(new MouseMonitorHandler());
	
	}
   
    
    @Override
    public void paintComponent(Graphics g)
    {
        Graphics2D g2d=(Graphics2D)g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        
       /* double leftX=100;
        double topY=100;
        double width=120;
        double height=100;
        
        Rectangle2D re=new Rectangle2D.Double(leftX,topY,width,height);
        g2d.draw(re);
        
        
        double centerX=380;
        double centerY=150;
        double redius=50;
        
        Ellipse2D circle=new Ellipse2D.Double();
        circle.setFrameFromCenter(centerX, centerY,centerX+redius,centerY+redius);
        g2d.draw(circle);*/
        
        //System.out.print(flag);
        for(Object obj : squres) {
			g2d.draw((Rectangle2D)obj);
		}
        
       /* for(Object obj : squres) {
			g2d.draw((Rectangle2D)obj);
		}*/
    }
    
//	如果包含鼠标点击的坐标则不绘制，否则加入集合进行绘制。
		public Rectangle2D find(Point2D point) {
			for(Object r: squres) {
				if(((Rectangle2D) r).contains(point)) {
				return (Rectangle2D) r;
				}
			}
			return null;
		}

		//	添加点击要画的矩形。
		public void addSqures(Point2D p) {
			double x = p.getX() - SQURESIZE;
			double y = p.getY() - SQURESIZE/2;
			
			obj = new Rectangle2D.Double(x, y, 2*SQURESIZE, SQURESIZE);
			squres.add(obj);
			
			repaint();
		}
		
    
    public Dimension getPreferredSize(){
        return new Dimension(DEFAULT_WIDTH,DEFAULT_HEIGHT);
    }
    
    private class MouseMoniter extends MouseAdapter {
		//	不用mousecliked是为了让用户操作立即得到响应。不必等到鼠标松开。
		public void mousePressed(MouseEvent e) {
			obj = find(e.getPoint());
			if(obj == null) {
			addSqures(e.getPoint());
			}
		}
		
		
	}
	
	private class MouseMonitorHandler implements MouseMotionListener {
		//	鼠标进入矩形后变成十字架
		public void mouseMoved(MouseEvent e) {
			if(find(e.getPoint()) == null) setCursor(Cursor.getDefaultCursor());
			else setCursor(Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR));
		}
		
		//	拖动矩形
		public void mouseDragged(MouseEvent e) {
			if(obj != null) {
				int x = e.getX();
				int y = e.getY();
				
				((Rectangle2D) obj).setFrame(x-SQURESIZE, y-SQURESIZE/2, 2*SQURESIZE, SQURESIZE);
				repaint();
			}
		}
	}
}


@SuppressWarnings("serial")
class DrawFrame extends JFrame implements ActionListener{
	Button button1;
	Button button2;
	Button button3;
	
	int flag=0;
	DrawFrame(int flag){
		this.flag=flag;
	}
	public void AddButton(Panel buttonPanel) {			
	    button1 = new Button ("rectangle");
	    button2 = new Button ("cercle");
	    button3 = new Button ("rectangle et cercle");		
    
    	
    	buttonPanel.add(button1);
    	buttonPanel.add(button2);
    	buttonPanel.add(button3);
    	
    	
    	button1.addActionListener((ActionListener) this);
    	button2.addActionListener((ActionListener) this);
    	button3.addActionListener((ActionListener) this);
    	
		validate();
		
    	
	}
	
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==button1) {     
			flag=0;
	    }else if(e.getSource()==button2) {
	    	flag=1;
	    }
		else if(e.getSource()==button3) {
			flag=2;
		}
		
		print();
	}
	
	public void paintingTable(JFrame frame,Panel buttonPanel,DrawComponent paintingGround) {			
		
		frame.setLayout(new BorderLayout());
    	
    	frame.add(BorderLayout.NORTH,buttonPanel);
    	frame.add(BorderLayout.CENTER,paintingGround);
    	
    	pack();
    	
	}
	
	public int print() {
		return flag;
	}

}
	

public class Dessin2D {
	
    public static void main(String[] args) {
		int flag=0;
    	
    	DrawFrame frame=new DrawFrame(flag);
        frame.setTitle("Dessin 2D");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //setSize(600,480);
               
        
        Panel buttonPanel = new Panel();
        frame.AddButton(buttonPanel);
       // System.out.print(">"+frame.print());
        
    	DrawComponent paintingGround=new DrawComponent(flag);
    	frame.paintingTable(frame,buttonPanel,paintingGround);
        
      
        //frame.setResizable(false);
        frame.setVisible(true);
        
        
    }

}