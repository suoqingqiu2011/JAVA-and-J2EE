package fr.uvsq.fr.uvsq.bin563;
import java.awt.Button;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Shape;

import java.awt.geom.Point2D;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import junit.framework.TestCase;

public class ShapeMainTest extends TestCase{
	ShapeMain shapmain;
	ShapeMain.PaintingGround paintingGround ;
	Graphics2D gMock ;
	@BeforeEach
	protected void setUp() throws Exception {
		super.setUp();
		try {
			shapmain=new ShapeMain();		
			paintingGround = shapmain.new PaintingGround();
			gMock = Mockito.mock(Graphics2D.class);
	    } catch (Exception e) {
			 e.printStackTrace();
        }
	}
	
	@Test
	public void testchangeColor() throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException{	
		try {	
			Class<?> clazz = shapmain.getClass();  
			Field field = clazz.getDeclaredField("btRectangle");  
			field.setAccessible(true);              
						
			Button bton=(Button) field.get(shapmain);
			shapmain.changeColor(bton);
			Color expectedColor = Color.GRAY;
			//System.out.println(bton.getBackground());
			assertEquals(bton.getBackground(),expectedColor);
		} catch (Exception e) {
			e.printStackTrace();
        }
	}
	
	
	@Test
	public void testaddSqures()  {
		try {	
			Class<?> clazz = shapmain.getClass();  
			Field field = clazz.getDeclaredField("drawType");  
			field.setAccessible(true);              
	
			Point2D p = new Point2D.Float(0, 0);;
			shapmain.addSqures(p);
			
			int num_rect=(Integer) field.get(shapmain);
			int expecteddrawtype = 0;
			System.out.println(num_rect);
			assertEquals(num_rect,expecteddrawtype);
		} catch (Exception e) {
			e.printStackTrace();
        }
	}
	
	@Test
	public void testpaint() {
		ArrayList<Object> squres = new ArrayList<Object>();
	    //expectations	
	    Color expectedColor = Color.black;

	    paintingGround.paint(gMock);
	    Mockito.verify(gMock).setColor(expectedColor);
	    
	    for(Object expectedObject : squres) {	
	    	Mockito.verify(gMock).draw((Shape) expectedObject);
	    }  
	}
	
	
	@Test
	public void testfind() {
		ShapeMain mockedApp2 = Mockito.mock(ShapeMain.class);
		Object expectedobj = new Object();
		Point2D p=null;
	    Mockito.when(mockedApp2.find(p)).thenReturn(expectedobj);
	    
	}
	
	@Test
	public void testsauver_dessin() throws IOException{	
		try {
			 Class<?> clazz = paintingGround.getClass();  
             Field field1 = clazz.getDeclaredField("fileoutput");  
             field1.setAccessible(true);      
             paintingGround.sauver_dessin();
             
             FileOutputStream f_out=(FileOutputStream) field1.get(paintingGround);	 
			 assertNotNull(f_out);
			 
			 Field field2 = clazz.getDeclaredField("fileinput"); 
			 field2.setAccessible(true); 
			 paintingGround.charger_dessin();
			 
			 FileInputStream f_in=(FileInputStream) field2.get(paintingGround);
			 assertNotNull(f_in);
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testlistenerbtRectangle() throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
		
		Class<?> clazz = shapmain.getClass();  
        Field field = clazz.getDeclaredField("drawType");  
        field.setAccessible(true);
        
		shapmain.btRectangle.addActionListener(shapmain);
		
		int num_drawtype=(Integer) field.get(shapmain); 
		
		//System.out.println(num_drawtype);
		
		assertEquals(num_drawtype, 0);
	}
	
	@Test
	public void testlistenerbtRound() throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
		
		Class<?> clazz = shapmain.getClass();  
        Field field = clazz.getDeclaredField("drawType");  
        field.setAccessible(true);
        
		shapmain.btRound.addActionListener(shapmain);
		
		int num_drawtype=(Integer) field.get(shapmain); 
		
		//System.out.println(num_drawtype);
		
		assertEquals(num_drawtype, 0);
	}
	
	@Test
	public void testlistenerbtRectangleRound() throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
		
		Class<?> clazz = shapmain.getClass();  
        Field field = clazz.getDeclaredField("drawType");  
        field.setAccessible(true);
        
		shapmain.btRectangleRound.addActionListener(shapmain);
		
		int num_drawtype=(Integer) field.get(shapmain); 
		
		//System.out.println(num_drawtype);
		
		assertEquals(num_drawtype, 0);
	}
	
	@Test
	public void testlistenerbtSave() throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
		
		Class<?> clazz = shapmain.getClass();  
        Field field = clazz.getDeclaredField("drawType");  
        field.setAccessible(true);
        
		shapmain.btSave.addActionListener(shapmain);
		
		int num_drawtype=(Integer) field.get(shapmain); 
		
		//System.out.println(num_drawtype);
		
		assertEquals(num_drawtype, 0);
	}
	
	@Test
	public void testlistenerbtCharge() throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
		
		Class<?> clazz = shapmain.getClass();  
        Field field = clazz.getDeclaredField("drawType");  
        field.setAccessible(true);
        
		shapmain.btCharge.addActionListener(shapmain);
		
		int num_drawtype=(Integer) field.get(shapmain); 
		
		//System.out.println(num_drawtype);
		
		assertEquals(num_drawtype, 0);
	}
	
}
