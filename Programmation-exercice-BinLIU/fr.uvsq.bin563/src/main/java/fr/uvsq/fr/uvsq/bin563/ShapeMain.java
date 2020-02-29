package fr.uvsq.fr.uvsq.bin563;


import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.Container;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Panel;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.MouseMotionListener;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import javax.swing.ButtonGroup;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.UIManager;


@SuppressWarnings("serial")
public class ShapeMain extends JFrame implements ActionListener,MouseListener,MouseMotionListener{
	int x,y,x1,y1,x2,y2,width,height;
	boolean isFirstPoint = true;

	int drawType =0; // or = PaintingGround.RECTANGLE;
	
	ArrayList<Object> squres = new ArrayList<Object>();
    Object obj= null;
    int SQURESIZE = 30;

	ButtonGroup btg = new ButtonGroup();
	
	Button btRectangle = new Button("Rectangle(affich and move)");
	Button btRound = new Button("Round(affich and move)");
	Button btRectangleRound = new Button("Rectangle and Round(move)");
	
	Button btSave = new Button("Save");
	Button btCharge = new Button("Load");

	ArrayList<Button> Btnlist = new ArrayList<Button>();
		
	Panel buttonPanel = new Panel();
	Panel buttonPanelFile = new Panel();
	
	PaintingGround paintingGround = new PaintingGround();
	
	int x_obj;
	int y_obj;
	ArrayList<Integer> dist_x = new ArrayList<Integer>();
	ArrayList<Integer> dist_y = new ArrayList<Integer>();
	
	public static void main(String[] args) {
		try{
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		}catch(Exception e) {
			e.printStackTrace();
		}
		new ShapeMain();
	}


	public ShapeMain() {
		
	buttonPanel.add(btRectangle);
	buttonPanel.add(btRound);
	buttonPanel.add(btRectangleRound);
	buttonPanel.setBackground(Color.lightGray);
	btRectangle.setBackground(Color.white);
	btRound.setBackground(Color.white);
	btRectangleRound.setBackground(Color.white);
	
	Btnlist.add(btRectangle);
	Btnlist.add(btRound);
	Btnlist.add(btRectangleRound);
	
	buttonPanelFile.add(btSave);
	buttonPanelFile.add(btCharge);
	buttonPanelFile.setBackground(Color.lightGray);
	btSave.setBackground(Color.white);
	btCharge.setBackground(Color.white);
	
	Container cp = this;
	
	cp.setLayout(new BorderLayout());
	
	cp.add(BorderLayout.NORTH,buttonPanel);
	cp.add(BorderLayout.CENTER,paintingGround);
	cp.add(BorderLayout.SOUTH,buttonPanelFile);
	
	setTitle("Dessin 2D");
	setLocation(300,150);
	setSize(600,480);
	setVisible(true);
	
	setDefaultCloseOperation(EXIT_ON_CLOSE);
		
	paintingGround.addMouseListener(new MouseAdapter() {
		public void mousePressed(MouseEvent e) {
			obj = find(e.getPoint());
			
			if(obj == null) {
				addSqures(e.getPoint());
				
			}else if(obj!=null){
				int i=0;
				x_obj=e.getX();
				y_obj=e.getY();
				int a,b;
				//System.out.println("x_obj:"+x_obj +" y_obj:"+ y_obj);
				for(Object s: squres) {
					a=((Shape) s).getBounds().x-x_obj;
					b=((Shape) s).getBounds().y-y_obj;
					//System.out.println("s_x "+((Shape) s).getBounds().x+" dist "+a);
					//System.out.println("s_y "+((Shape) s).getBounds().y+" dist "+b);
					dist_x.add(a);
					dist_y.add(b);
					dist_x.set(i,a);
					dist_y.set(i,b);
					
					//System.out.println("dist_x["+i+"]"+dist_x.get(i) +" dist_y["+i+"]"+dist_y.get(i));
					i++;
				}
			}
		}
	});

	paintingGround.addMouseMotionListener(new MouseMotionAdapter() {
			public void mouseMoved(MouseEvent e) {
				// System.out.println(e.getPoint().getClass().toString());
				if(find(e.getPoint()) == null) 
					setCursor(Cursor.getDefaultCursor());
				else 
					setCursor(Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR));
			}
			
			
			public void mouseDragged(MouseEvent e) {
				int x = e.getX();
				int y = e.getY();
				if(obj != null) {
					// System.out.println(obj.getClass().toString());
					 if(drawType== PaintingGround.RECTANGLE) {
						if(obj.getClass().toString().contains("class java.awt.geom.Rectangle2D$Double")) {
							((Rectangle2D) obj).setFrame(x-SQURESIZE, y-SQURESIZE/2, 2*SQURESIZE, SQURESIZE);
					    }else {
					    	System.out.println("If you want to move a round, please choose the button 'Round'.");
					    }
						 
					 }else if(drawType==PaintingGround.ROUND) {
						 if(obj.getClass().toString().contains("class java.awt.geom.Ellipse2D$Double")) {
						     ((Ellipse2D) obj).setFrame(x-SQURESIZE/2, y-SQURESIZE/2, SQURESIZE, SQURESIZE);
						 }else {
							 System.out.println("If you want to move a rectangle, please choose the button 'Rectangle'.");
						 }
						 
					 }
					 
					 int i=0;
					 if(squres!=null) {
						 if(drawType== PaintingGround.RECTANGLE_ROUND && squres.getClass().toString().contains("class java.util.ArrayList")) {							
							 //System.out.println("x_obj:"+x_obj +" y_obj:"+ y_obj);
							 
							 for(Object ob: squres) {
								 if(ob.getClass().toString().contains("class java.awt.geom.Rectangle2D$Double")) {
									
									 ((Rectangle2D) ob).setFrame( (x-SQURESIZE/2)+dist_x.get(i), (y-SQURESIZE/2)+dist_y.get(i), 2*SQURESIZE, SQURESIZE);
								 }else if(ob.getClass().toString().contains("class java.awt.geom.Ellipse2D$Double")){
									
									 ((Ellipse2D) ob).setFrame( (x-SQURESIZE/2)+dist_x.get(i), (y-SQURESIZE/2)+dist_y.get(i), SQURESIZE, SQURESIZE);
								 }
								 i++;
							 }			 
						 }
					 }
					repaint();
				}
			}
	});
	
	btRectangle.addActionListener(new ActionListener(){
		public void actionPerformed(ActionEvent evn) {
		drawType = PaintingGround.RECTANGLE;
		changeColor(btRectangle);
		//System.out.println(drawType);
		}
	});
	
	btRound.addActionListener(new ActionListener(){
		public void actionPerformed(ActionEvent evn) {
		drawType = PaintingGround.ROUND;
		changeColor(btRound);
		//System.out.println(drawType);
		}
	});
	
	btRectangleRound.addActionListener(new ActionListener(){
		public void actionPerformed(ActionEvent evn) {
		drawType = PaintingGround.RECTANGLE_ROUND;
		changeColor(btRectangleRound);
		//System.out.println(drawType);
		}
	});
	
	btSave.addActionListener(new ActionListener(){
		public void actionPerformed(ActionEvent evn) {
			paintingGround.sauver_dessin();
		}
	});
	
	btCharge.addActionListener(new ActionListener(){
		public void actionPerformed(ActionEvent evn) {
			try {
				paintingGround.charger_dessin();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
		}
	});
	
	}
	
	public void changeColor(Button Btn){	
		for(Button bt : Btnlist){
			if(bt == Btn){
				bt.setBackground(Color.GRAY);
			}else{
				bt.setBackground(Color.white);
			}
		}
	}
	

	public void actionPerformed(ActionEvent e) {
	// TODO Auto-generated method stub
	
	}
	
	public void mouseClicked(MouseEvent e) {
	// TODO Auto-generated method stub
	
	}
	
	public void mouseReleased(MouseEvent e) {
	// TODO Auto-generated method stub
	
	}
	
	public void mouseEntered(MouseEvent e) {
	// TODO Auto-generated method stub
	
	}
	
	public void mouseExited(MouseEvent e) {
	// TODO Auto-generated method stub
	
	}
	
	
	
		public Object find(Point2D point) {		
			for(Object r: squres) {			
				if(((Shape) r).contains(point)) {
				    if(drawType== PaintingGround.RECTANGLE || drawType==PaintingGround.ROUND) {
					    
						return (Object) r;						
					}else if(drawType== PaintingGround.RECTANGLE_ROUND ) {
						
						return squres;	
					}
				}
			}
			
			return null;
		}

		public void addSqures(Point2D p) {
			double x_p = p.getX();
			double y_p = p.getY();			
			
			 if(drawType== PaintingGround.RECTANGLE) {
				    obj = new Rectangle2D.Double(x_p - SQURESIZE, y_p - SQURESIZE/2, 2*SQURESIZE, SQURESIZE);
					squres.add(obj);	
				}else if(drawType==PaintingGround.ROUND) {
					Ellipse2D circle=new Ellipse2D.Double();
				    circle.setFrameFromCenter(x_p,y_p,x_p+SQURESIZE/2,y_p+SQURESIZE/2);
					obj=circle;
					squres.add(obj);
				}else if(drawType==PaintingGround.RECTANGLE_ROUND){
					System.out.println("Can't add the new shape!!! You can just move the existent shapes .");
				}else {
					System.out.println("Can't add the new shape!!!");
				}			
			repaint();
		}
		
		class PaintingGround extends JPanel {
			
			public static final int RECTANGLE = 1;
			public static final int ROUND = 2;
			public static final int RECTANGLE_ROUND = 3;
			Graphics g ;
			Graphics2D g2d=(Graphics2D)g;
		
			public PaintingGround() {
				setBackground(Color.white);
				squres = new ArrayList<Object>();
		    	obj = null;  	
			}
			//BufferedImage paintImage = new BufferedImage(500, 400, BufferedImage.TYPE_INT_BGR);
					
			public void paint(Graphics g) {
				//g2d = (Graphics2D) paintImage.createGraphics();
				super.paint(g);
				g2d=(Graphics2D)g;
				
				g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
				g2d.setColor(Color.black);	
							
				for(Object obj : squres) {					
					 g2d.draw((Shape)obj);			
				}				
				 //	System.out.println(drawType);		
			}
			
			//String savepath = "src/picture.jpg";
			FileOutputStream fileoutput;
			FileInputStream fileinput;
			public void sauver_dessin() {
				
				try{
					fileoutput = new FileOutputStream("src/sauvegarde_dessin.ser");
					ObjectOutputStream l_oos = new ObjectOutputStream (fileoutput);
					l_oos.writeObject(squres);
					
					l_oos.flush();
					l_oos.close();				
					repaint();		
					
					JOptionPane.showMessageDialog(null, "Success to save it.�r(^��^)�q");
					//System.out.println("Success to save it.");
					
				}catch(java.io.IOException e){
					e.printStackTrace();
				}
			}
			
			@SuppressWarnings("unchecked")
			public void charger_dessin() throws ClassNotFoundException {
				try{
					fileinput = new FileInputStream("src/sauvegarde_dessin.ser");
					@SuppressWarnings("resource")
					ObjectInputStream l_ois = new ObjectInputStream(fileinput);						
					squres = (ArrayList<Object>) l_ois.readObject();
					
					for(Object obj : squres) {					
						 g2d.draw((Shape)obj);			
					}	
					repaint();
					JOptionPane.showMessageDialog(null, "Success to load the file .�r(�s���t)�q");
					//System.out.println("Success to load it.");					
				}
				catch(java.io.IOException err){
					System.out.println("\n Erreur to load it.\n\n");
				}
			}		
		}

		public void mouseDragged(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}


		public void mouseMoved(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}


		public void mousePressed(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}		
}

