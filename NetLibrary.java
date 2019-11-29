import java.util.*;
public class NetLibrary {
	static Random r = new Random();
	public static double[][] Matrix(int rows, int cols){
		double[][] matrix = new double[rows][cols];
		for(int i = 0; i<rows; i++) {
			for(int j = 0; j<cols;j++) {
				matrix[i][j] = 0;
			}
		}
		return matrix;
	}
	
	public static void printmatrix(double[][] matrix) {
		for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++)
                System.out.print(matrix[i][j] + " ");
            System.out.println(); 
        }
	}
	
	public static double[][] multiply(double[][] matrix, double n) {
		int rows = matrix.length;
		int columns = matrix[0].length;
		double[][] result = new double[rows][columns];
		for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++)
                result[i][j] = matrix[i][j] * n; 
        }
		return result;
	}
	
	public static double[][] multiply(double[][] matrix, double[][] n) {
		if (matrix.length != n.length) {
			throw new IllegalArgumentException("A:Rows: " + matrix.length + " did not match B:Rows " + n.length + ".");
		}else if(matrix[0].length != n[0].length) {
			throw new IllegalArgumentException("A:Columns: " + matrix[0].length + " did not match B:Columns " + n[0].length + ".");
		}else {
		
			int rows = matrix.length;
			int columns = matrix[0].length;
			double[][] result = new double[rows][columns];
			for (int i = 0; i < rows; i++) {
	            for (int j = 0; j < columns; j++)
	                result[i][j] = matrix[i][j] * n[i][j]; 
	        }
			return result;
		}
	}
	
	public static double[][] mult(double[][] matrix, double[][] n){
		int rows = matrix.length;
		int columns = matrix[0].length;
		int rows2 = n.length;
		int cols2 = n[0].length;
		double[][] result = new double[rows][cols2];
		for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols2; j++) {
                result[i][j] = 0.0;
            }
		}
		for (int i = 0; i < rows; i++) { // aRow
			for (int j = 0; j < cols2; j++) { // bColumn
				result[i][j] = matrix[i][0] * n[0][j]; // Will for later coding I used Bad Code here fix this for better results
			}
		}
	return result;
}
	
	public static double[][] add(double[][] matrix, double n) {
		int rows = matrix.length;
		int columns = matrix[0].length;
		double[][] result = new double[rows][columns];
		for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++)
                result[i][j] += n; 
        }
		return result;
	}
	
	public static double[][] add(double[][] matrix, double[][] n){
		if (matrix.length != n.length) {
			throw new IllegalArgumentException("A:Rows: " + matrix.length + " did not match B:Rows " + n.length + ".");
		}else if(matrix[0].length != n[0].length) {
			throw new IllegalArgumentException("A:Columns: " + matrix[0].length + " did not match B:Columns " + n[0].length + ".");
		}else {
		
			int rows = matrix.length;
			int columns = matrix[0].length;
			double[][] result = new double[rows][columns];
			for (int i = 0; i < rows; i++) {
	            for (int j = 0; j < columns; j++)
	                result[i][j] = matrix[i][j] + n[i][j]; 
	        }
			return result;
		}
	}
	
	public static double[][] subtract(double[][] matrix, double n) {
		int rows = matrix.length;
		int columns = matrix[0].length;
		double[][] result = new double[rows][columns];
		for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++)
                result[i][j] -= n; 
        }
		return result;
	}
	
	public static double[][] subtract(double[][] matrix, double[][] n) {
		if (matrix.length != n.length) {
			throw new IllegalArgumentException("A:Rows: " + matrix.length + " did not match B:Rows " + n.length + ".");
		}else if(matrix[0].length != n[0].length) {
			throw new IllegalArgumentException("A:Columns: " + matrix[0].length + " did not match B:Columns " + n[0].length + ".");
		}else {
		
			int rows = matrix.length;
			int columns = matrix[0].length;
			double[][] result = new double[rows][columns];
			for (int i = 0; i < rows; i++) {
	            for (int j = 0; j < columns; j++)
	                result[i][j] = matrix[i][j] - n[i][j]; 
	        }
			return result;
		}
	}
	
	public static double[][] randomize(double[][] matrix){
		for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++)
                matrix[i][j] = -4 +(4 - (-4)) * r.nextDouble();
        }
		return matrix;
	}
	
	public static double[][] transpose(double[][] matrix){
		int rows = matrix.length;
		int cols = matrix[0].length;
		
		double[][] result = new double[cols][rows];
		for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++)
                result[j][i] = matrix[i][j];
        }
		return result;
	}
	
	public static double sigmoid(double x)  // Activation Function
	{
	    return 1 / (1 + Math.exp(-x));
	}
	
	public static double sigmoid_p(double x) {  // Derivative of Activation Function
		return NetLibrary.sigmoid(x) * (1-NetLibrary.sigmoid(x));
	}
	
	public static double[][] map(double[][] matrix){
		int rows = matrix.length;
		int columns = matrix[0].length;
		for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
            		matrix[i][j] = sigmoid(matrix[i][j]);
            }
        }
		return matrix;
	}
	
	public static double[][] map_p(double[][] matrix){
		int rows = matrix.length;
		int columns = matrix[0].length;
		double[][] result = new double[rows][columns];
		for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
            		result[i][j] = sigmoid_p(matrix[i][j]);
            }
        }
		return result;
	}
	
	public static double[][] fromArray(double[] arr) {
		double[][] m = new double[arr.length][1];
		for(int i = 0; i < arr.length; i++) {
			m[i][0] = arr[i];
		}
		return m;
	}
	
	public static double[] toArray(double[][] matrix) {
		int index = 0;
		double[] arr = new double[(matrix.length * matrix[0].length)];
		for(int i = 0; i<matrix.length;i++) {
			for(int j = 0; j<matrix[i].length; j++) {
				arr[index] = matrix[i][j];
				index++;
			}
		}
		return arr;
		
	}
	
	public static double toNum(double[] arr) {
		double x =0;
		for(int i =0; i<arr.length; i++) {
			x += arr[i];
		}
		return x;
	}
	
	public static double[][] dot(double[][] matrix, double[][] matrix2) {
		int aRows = matrix.length;
		int aColumns = matrix[0].length;
		int bRows = matrix2.length;
		int bColumns = matrix2[0].length;
		if (aColumns != bRows) {
			throw new IllegalArgumentException("A:Rows: " + aColumns + " did not match B:Columns " + bRows + ".");
		}
		double[][] C = new double[aRows][bColumns];
		for (int i = 0; i < aRows; i++) {
			for (int j = 0; j < bColumns; j++) {
				C[i][j] = 0.00000;
			}
		}
		for (int i = 0; i < aRows; i++) { // aRow
			for (int j = 0; j < bColumns; j++) { // bColumn
				for (int k = 0; k < aColumns; k++) { // aColumn
					C[i][j] += matrix[i][k] * matrix2[k][j];
				}
			}
		}
		return C;
	}
}
