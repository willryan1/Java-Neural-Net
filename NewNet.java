import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

public class NewNet {
	public int input_nodes;
	public int hidden_nodes;
	public int output_nodes;
	public double learning_rate;
	public double[][] data;
	public int[] output;
	public double[][] weightsl1;
	public double[][] weightsl2;
	public double[][] biasl1;
	public double[][] biasl2;
	
	public NewNet(int input_nodes, int hidden_nodes, int output_nodes, double[][] data, double learning_rate, int[] output) {
		this.input_nodes = input_nodes;
		this.hidden_nodes = hidden_nodes;
		this.output_nodes = output_nodes;
		this.data = data;
		this.learning_rate = learning_rate;
		this.output = output;
		
		this.weightsl1 = NetLibrary.randomize(new double[input_nodes][hidden_nodes]);
		this.weightsl2 = NetLibrary.randomize(new double[hidden_nodes][output_nodes]);
		this.biasl1 = NetLibrary.randomize(new double[hidden_nodes][1]);
		this.biasl2 = NetLibrary.randomize(new double[output_nodes][1]);
	}
	
	public double sigmoid(double x)  // Activation Function
	{
	    return 1 / (1 + Math.exp(-x));
	}
	
	public double sigmoid_p(double x) {  // Derivative of Activation Function
		return this.sigmoid(x) * (1-this.sigmoid(x));
	}
	
	public double[][] feedforeward(double[][] input){
		//double[][] a = NetLibrary.dot(input, this.weightsl1);
		//double[][] b = NetLibrary.transpose(this.biasl1);
		
		double[][] hidden = NetLibrary.add(NetLibrary.dot(input, this.weightsl1), NetLibrary.transpose(this.biasl1));
		double[][] hiddenfinal = NetLibrary.map(hidden);
		
		//double[][] c = NetLibrary.dot(hiddenfinal, this.weightsl2);
		//double[][] d = NetLibrary.transpose(this.biasl2);
		double[][] zbefore = NetLibrary.add(NetLibrary.dot(hiddenfinal, this.weightsl2), NetLibrary.transpose(this.biasl2));
		double[][] z = NetLibrary.map(zbefore);
		return z;
	}
	
	public void train() {
		int ri = ThreadLocalRandom.current().nextInt(0, this.data.length + 0);
		double[][] point = {this.data[ri]};
		double[][] z1 = this.feedforeward(point);
		double[][] hidden = NetLibrary.add(NetLibrary.dot(point, this.weightsl1), NetLibrary.transpose(this.biasl1));
		double[][] hiddenfinal = NetLibrary.map(hidden);
		int target = this.output[ri];
		
		double z = NetLibrary.toNum(NetLibrary.toArray(z1));
		double cost1 = (z - target);
		double cost = cost1 * cost1;
		
		
		double d_cost = 2 * (z - target);
		//System.out.println(d_cost);
		
		double g = d_cost * this.sigmoid_p(z);
		double[][] hidden_g1 = (NetLibrary.multiply(NetLibrary.transpose(this.weightsl2), g));
		double[][] hidden_g = NetLibrary.multiply(hidden_g1, NetLibrary.map_p(hiddenfinal));
		//System.out.println(g);
		//NetLibrary.printmatrix(hidden_g);
		double[][] d = NetLibrary.transpose(hidden_g);
		//NetLibrary.printmatrix(d);
		//NetLibrary.printmatrix(point);
		double[][] weight_grad_l2 = NetLibrary.multiply(NetLibrary.multiply(hidden, g), this.learning_rate);
		double[][] weight_grad_l1 = NetLibrary.multiply(NetLibrary.mult(d, point), this.learning_rate);
		double bias_grad_l2 = z * this.learning_rate;
		double[][] bias_grad_l1 = NetLibrary.multiply(hidden_g, this.learning_rate);
		
		this.weightsl2 = NetLibrary.subtract(this.weightsl2, NetLibrary.transpose(weight_grad_l2));
		this.weightsl1 = NetLibrary.subtract(this.weightsl1, NetLibrary.transpose(weight_grad_l1));
		
		this.biasl2 = NetLibrary.subtract(this.biasl2, bias_grad_l2);
		this.biasl1 = NetLibrary.subtract(this.biasl1, NetLibrary.transpose(bias_grad_l1));
		
	}
	public void printdata() {
		NetLibrary.printmatrix(this.weightsl1);
		System.out.println();
		NetLibrary.printmatrix(this.weightsl2);
		System.out.println();
		NetLibrary.printmatrix(this.biasl1);
		System.out.println();
		NetLibrary.printmatrix(this.biasl2);
		System.out.println();
	}
	
	public void check_output() {
		int total_correct = 0;
		for(int i =0;i<this.data.length;i++) {
			double[][] point = {this.data[i]};
			double correct = this.output[i];
			NetLibrary.printmatrix(point);
			double[][] z = this.feedforeward(point);
			double z_n = NetLibrary.toArray(z)[0];
			System.out.print("Pred: ");
			NetLibrary.printmatrix(z);
			z_n = z_n > 0.5 ? 1 : 0;
			if(z_n == correct) {total_correct++;};
		}System.out.println("Percent Correct: " + ((total_correct / this.data.length) * 100));
	}
	
	public static void main (String[] args) {
		double[][] data = {{5.1,3.5,1.4,0.2},
			{4.9,3.0,1.4,0.2},
			{4.7,3.2,1.3,0.2},
			{4.6,3.1,1.5,0.2},
			{5.0,3.6,1.4,0.2},
			{5.4,3.9,1.7,0.4},
			{4.6,3.4,1.4,0.3},
			{5.0,3.4,1.5,0.2},
			{4.4,2.9,1.4,0.2},
			{4.9,3.1,1.5,0.1},
			{5.4,3.7,1.5,0.2},
			{4.8,3.4,1.6,0.2},
			{4.8,3.0,1.4,0.1},
			{4.3,3.0,1.1,0.1},
			{5.8,4.0,1.2,0.2},
			{5.7,4.4,1.5,0.4},
			{5.4,3.9,1.3,0.4},
			{5.1,3.5,1.4,0.3},
			{5.7,3.8,1.7,0.3},
			{5.1,3.8,1.5,0.3},
			{5.4,3.4,1.7,0.2},
			{5.1,3.7,1.5,0.4},
			{4.6,3.6,1.0,0.2},
			{5.1,3.3,1.7,0.5},
			{4.8,3.4,1.9,0.2},
			{5.0,3.0,1.6,0.2},
			{5.0,3.4,1.6,0.4},
			{5.2,3.5,1.5,0.2},
			{5.2,3.4,1.4,0.2},
			{4.7,3.2,1.6,0.2},
			{4.8,3.1,1.6,0.2},
			{5.4,3.4,1.5,0.4},
			{5.2,4.1,1.5,0.1},
			{5.5,4.2,1.4,0.2},
			{4.9,3.1,1.5,0.1},
			{5.0,3.2,1.2,0.2},
			{5.5,3.5,1.3,0.2},
			{4.9,3.1,1.5,0.1},
			{4.4,3.0,1.3,0.2},
			{5.1,3.4,1.5,0.2},
			{5.0,3.5,1.3,0.3},
			{4.5,2.3,1.3,0.3},
			{4.4,3.2,1.3,0.2},
			{5.0,3.5,1.6,0.6},
			{5.1,3.8,1.9,0.4},
			{4.8,3.0,1.4,0.3},
			{5.1,3.8,1.6,0.2},
			{4.6,3.2,1.4,0.2},
			{5.3,3.7,1.5,0.2},
			{5.0,3.3,1.4,0.2},
			{7.0,3.2,4.7,1.4},
			{6.4,3.2,4.5,1.5},
			{6.9,3.1,4.9,1.5},
			{5.5,2.3,4.0,1.3},
			{6.5,2.8,4.6,1.5},
			{5.7,2.8,4.5,1.3},
			{6.3,3.3,4.7,1.6},
			{4.9,2.4,3.3,1.0},
			{6.6,2.9,4.6,1.3},
			{5.2,2.7,3.9,1.4},
			{5.0,2.0,3.5,1.0},
			{5.9,3.0,4.2,1.5},
			{6.0,2.2,4.0,1.0},
			{6.1,2.9,4.7,1.4},
			{5.6,2.9,3.6,1.3},
			{6.7,3.1,4.4,1.4},
			{5.6,3.0,4.5,1.5},
			{5.8,2.7,4.1,1.0},
			{6.2,2.2,4.5,1.5},
			{5.6,2.5,3.9,1.1},
			{5.9,3.2,4.8,1.8},
			{6.1,2.8,4.0,1.3},
			{6.3,2.5,4.9,1.5},
			{6.1,2.8,4.7,1.2},
			{6.4,2.9,4.3,1.3},
			{6.6,3.0,4.4,1.4},
			{6.8,2.8,4.8,1.4},
			{6.7,3.0,5.0,1.7},
			{6.0,2.9,4.5,1.5},
			{5.7,2.6,3.5,1.0},
			{5.5,2.4,3.8,1.1},
			{5.5,2.4,3.7,1.0},
			{5.8,2.7,3.9,1.2},
			{6.0,2.7,5.1,1.6},
			{5.4,3.0,4.5,1.5},
			{6.0,3.4,4.5,1.6},
			{6.7,3.1,4.7,1.5},
			{6.3,2.3,4.4,1.3},
			{5.6,3.0,4.1,1.3},
			{5.5,2.5,4.0,1.3},
			{5.5,2.6,4.4,1.2},
			{6.1,3.0,4.6,1.4},
			{5.8,2.6,4.0,1.2},
			{5.0,2.3,3.3,1.0},
			{5.6,2.7,4.2,1.3},
			{5.7,3.0,4.2,1.2},
			{5.7,2.9,4.2,1.3},
			{6.2,2.9,4.3,1.3},
			{5.1,2.5,3.0,1.1},
			{5.7,2.8,4.1,1.3}};
		
		int[] output = {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1};
		
		NewNet n = new NewNet(4,4,1,data,0.5,output);
		for(int i = 0; i<20000;i++) {
			n.train();
		}
		n.check_output();
	}
}
