package Solution;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Solution {

	private ArrayList<int[]> triangle;  //ArrayList of integer arrays for representing the given triangle which is stored in the file

	
	Solution(String fileName){  //constructor with file name parameter
		triangle = new ArrayList<int[]>(); //creating an empty ArrayList of int arrays object.
		fillTriangle(fileName); //filling the ArrayList by reading the file
	}
	
	
	public void fillTriangle(String fileName) {
		File file = new File(fileName);
		BufferedReader br=null;
		try {
			br = new BufferedReader(new FileReader(file));
			String st; 
			while ((st = br.readLine()) != null) { //reading file line by line
				String[] itemStr = st.split(" ");  //splitting the line according to whitespace
				int[] itemInt=new int[itemStr.length]; //creating an integer array for storing the one single line of rectangle
				for(int i=0;i<itemStr.length;i++) { //converting all the String item of rectangle in a single line to integer
					itemInt[i]=Integer.parseInt(itemStr[i]);
				}
				triangle.add(itemInt); //add the integer array to triangle.
			}
		} catch (IOException e) {
			e.printStackTrace();
		} 
	}
	
	
	public int getMaxSum() {
		ArrayList<int[]> triangleTmp = new ArrayList<int[]>(); //creating a temp triangle(dynamic programming table) for bottom up processing the triangle
		
		for(int i=0;i<triangle.size();i++) {  // copy all items of trangle to temp triangle
			int line[]=new int[triangle.get(i).length]; 
			for(int j=0;j<triangle.get(i).length;j++) {
				line[j]=triangle.get(i)[j];
			}
			triangleTmp.add(line);
		}
		
		for(int i=triangleTmp.size()-1;i-1>=0;i--) { 				//starts bottom of the triangle 
			for(int j=0;j<triangleTmp.get(i-1).length;j++) { 		//iterate all items in a single line of rectangle
				if(!isPrimeNumber(triangle.get(i-1)[j])) { 			//checking whether if the item of rectangle is non prime number(above item)
					if(j==0) { 										//if the item is beginning of the line, we shouldn't look the left diagonal of this item.
						if(triangleTmp.get(i)[j+1]<triangleTmp.get(i)[j]) { 		//if below item bigger than the right diagonal item
							if(!isPrimeNumber(triangle.get(i)[j])) { 				//and if below item is not a prime number
								triangleTmp.get(i-1)[j]=triangleTmp.get(i-1)[j]+triangleTmp.get(i)[j]; 			// then add the below item to above item
							}  																					
							else {
								if(!isPrimeNumber(triangle.get(i)[j+1])) {   							//if right diagonal small but not prime then add the right diagonal
									triangleTmp.get(i-1)[j]=triangleTmp.get(i-1)[j]+triangleTmp.get(i)[j+1];
								}
							}	
						}
						else {   				//if right diagonal bigger than the below item
							if(!isPrimeNumber(triangle.get(i)[j+1])) {    		//same logic continues for the other situations
								triangleTmp.get(i-1)[j]=triangleTmp.get(i-1)[j]+triangleTmp.get(i)[j+1];
							}
							else {
								if(!isPrimeNumber(triangle.get(i)[j])) {
									triangleTmp.get(i-1)[j]=triangleTmp.get(i-1)[j]+triangleTmp.get(i)[j];
								}
							}
						}
					}
					else {
						if(triangleTmp.get(i)[j+1]<triangleTmp.get(i)[j] && triangleTmp.get(i)[j-1]<triangleTmp.get(i)[j]) {
							if(!isPrimeNumber(triangle.get(i)[j])) {
								triangleTmp.get(i-1)[j]=triangleTmp.get(i-1)[j]+triangleTmp.get(i)[j];
							}
							else if(triangleTmp.get(i)[j+1]<triangleTmp.get(i)[j-1]) {
								if(!isPrimeNumber(triangle.get(i)[j-1])) {
									triangleTmp.get(i-1)[j]=triangleTmp.get(i-1)[j]+triangleTmp.get(i)[j-1];
								}
								else if(!isPrimeNumber(triangle.get(i)[j+1])) {
									triangleTmp.get(i-1)[j]=triangleTmp.get(i-1)[j]+triangleTmp.get(i)[j+1];
								}
							}
							else {
								if(!isPrimeNumber(triangle.get(i)[j+1])) {
									triangleTmp.get(i-1)[j]=triangleTmp.get(i-1)[j]+triangleTmp.get(i)[j+1];
								}
								else if(!isPrimeNumber(triangle.get(i)[j-1])) {
									triangleTmp.get(i-1)[j]=triangleTmp.get(i-1)[j]+triangleTmp.get(i)[j-1];
								}
							}
							
						}
						else if(triangleTmp.get(i)[j]<triangleTmp.get(i)[j+1] && triangleTmp.get(i)[j-1]<triangleTmp.get(i)[j+1]) {
							
							
							if(!isPrimeNumber(triangle.get(i)[j+1])) {
								triangleTmp.get(i-1)[j]=triangleTmp.get(i-1)[j]+triangleTmp.get(i)[j+1];
							}
							else if(triangleTmp.get(i)[j]<triangleTmp.get(i)[j-1]) {
								if(!isPrimeNumber(triangle.get(i)[j-1])) {
									triangleTmp.get(i-1)[j]=triangleTmp.get(i-1)[j]+triangleTmp.get(i)[j-1];
								}
								else if(!isPrimeNumber(triangle.get(i)[j])) {
									triangleTmp.get(i-1)[j]=triangleTmp.get(i-1)[j]+triangleTmp.get(i)[j];
								}
							}
							else {
								if(!isPrimeNumber(triangle.get(i)[j])) {
									triangleTmp.get(i-1)[j]=triangleTmp.get(i-1)[j]+triangleTmp.get(i)[j];
								}
								else if(!isPrimeNumber(triangle.get(i)[j-1])) {
									triangleTmp.get(i-1)[j]=triangleTmp.get(i-1)[j]+triangleTmp.get(i)[j-1];
								}
							}							
						}
						else {
							if(!isPrimeNumber(triangle.get(i)[j-1])) {
								triangleTmp.get(i-1)[j]=triangleTmp.get(i-1)[j]+triangleTmp.get(i)[j-1];
							}
							else if(triangleTmp.get(i)[j]<triangleTmp.get(i)[j+1]) {
								if(!isPrimeNumber(triangle.get(i)[j+1])) {
									triangleTmp.get(i-1)[j]=triangleTmp.get(i-1)[j]+triangleTmp.get(i)[j+1];
								}
								else if(!isPrimeNumber(triangle.get(i)[j])) {
									triangleTmp.get(i-1)[j]=triangleTmp.get(i-1)[j]+triangleTmp.get(i)[j];
								}
							}
							else {
								if(!isPrimeNumber(triangle.get(i)[j])) {
									triangleTmp.get(i-1)[j]=triangleTmp.get(i-1)[j]+triangleTmp.get(i)[j];
								}
								else if(!isPrimeNumber(triangle.get(i)[j+1])) {
									triangleTmp.get(i-1)[j]=triangleTmp.get(i-1)[j]+triangleTmp.get(i)[j+1];
								}
							}
						}
					}
				}
			}
		}
		return triangleTmp.get(0)[0];
	}
	
	
	boolean isPrimeNumber(int num) { //if the number is prime then return true otherwise return false
		boolean flag = true;
		if(num<=1)
			return false;
        for(int i=2;i<=num/2;i++){ //iterating until middle of the num/2 is enough
            if(num % i == 0){
                flag = false;
                break;
            }
        }
        return flag;
	}
	
	
	public static void main(String[] args) {
		
		Solution solution1 = new Solution("src/Solution/triangle1");  //giving file name to the constructor
		System.out.println("Max Sum For triangle1: "+solution1.getMaxSum());  // printing the solution
		
		System.out.println();
		
		Solution solution2 = new Solution("src/Solution/triangle2");
		System.out.println("Max Sum For triangle2: "+solution2.getMaxSum());
	}
}

