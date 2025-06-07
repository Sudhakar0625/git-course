import java.util.ArrayList; 
 import java.util.Collections; 
 class Test 
 { 
 public static void main(String[] args) 
 { 
 ArrayList<Integer> l= new ArrayList<Integer>(); 
 l.add(10); 
 l.add(0); 
 l.add(15); 
 l.add(5); 
 l.add(20); 
 System.out.println("Before Sorting:1"+l); 
 Collections.sort(l,(I1,I2)->(I1<I2)?1:(I1>I2)?-1:0); 
 System.out.println("After Sorting:"+l); 
 } 
 } 

