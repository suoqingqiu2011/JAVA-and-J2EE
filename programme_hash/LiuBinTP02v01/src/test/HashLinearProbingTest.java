package test;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Before;
import org.junit.Test;

import LiuBin_TP02_v01.HashLinearProbing;

public class HashLinearProbingTest {
	HashLinearProbing hp;
	
	int[] Val;
	int M = 11; //la capacit¨¦
	char[] Key ;
	int[] Values ;
	
	@Before
	public void setUp() throws Exception {
		hp = new HashLinearProbing();
		Val = new int[10];
		M = 11;
		Key = new char[M] ;
		Values = new int[M];
		
		int i = 0;
		while(i < 10) {  Val[i]=i;  i++; }
		
		int t = 0;
		while(t < M) {  Key[t]=' '; t++;}
	}
	
	char[] K = {'B','O','E','P','V','L','X','N','K','M'};
	char[] K_put_test = {'B','X','O','E','P','N','K','M',' ','V','L'};
	int[] Val_put_test = {0,6,1,2,3,7,8,9,0,4,5};
	@Test
	public void test_put() throws IOException {
		int j = 0;
		while(j < 10) {	 hp.put(Key, Values,null, K[j], Val[j] , 0, 0);  j++;	}
			
		assertArrayEquals(K_put_test,Key);	
		assertArrayEquals(Val_put_test,Values);
	}
	
	@Test
	public void test_get() throws IOException { 
		int valueReturn1[][] = hp.get(K_put_test,Val_put_test,null,'X');
		
		System.out.println(valueReturn1[0][0]);
		assertEquals(6,valueReturn1[0][0]);
	}
	
	char[] K_remove_test = {'B','X','O','E','P','K','M',' ',' ','V','L'};
	int[] Val_remove_test = {0,6,1,2,3,8,9,0,0,4,5};
	@Test
	public void test_remove() throws IOException {
		Key = hp.remove(K_put_test,Values,null,'N');
				
		assertArrayEquals(K_remove_test,Key);
		//assertArrayEquals(Val_remove_test,Values);

	}
	

}
