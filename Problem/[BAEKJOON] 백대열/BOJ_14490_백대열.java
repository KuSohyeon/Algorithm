import java.util.Scanner;

public class BOJ_14490_백대열 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		String s = sc.next();
		
		String [] arr = s.split(":");
	
		int num = 0;
		int a = Integer.parseInt(arr[0]);
		int b = Integer.parseInt(arr[1]);
		if(a > b) {
			num = gcd(a, b);
		} else {
			num = gcd(b, a);
		}
		
		System.out.println(a/num + ":" + b/num);
	}
	static int gcd(int a, int b) {
		while(b > 0) {
			int tmp = b;
			b = a % b;
			a = tmp;
		}
		return a;
	}
}
