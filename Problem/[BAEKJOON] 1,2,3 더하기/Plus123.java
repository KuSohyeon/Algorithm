//[백준] 1,2,3 더하기

import java.util.Scanner;

public class Plus123 {
	static int [] arr;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int T= sc.nextInt();
		
		for(int tc=0;tc<T;tc++) {
			int num = sc.nextInt();
			
			arr = new int[num+1];
			if(num==1){
                System.out.println(1);
				continue;
              }
			if(num==2) {
				System.out.println(2);
				continue;
			}else if(num==3) {
				System.out.println(4);
				continue;
			}
			
			arr[1]=1;
			arr[2]=2;
			arr[3]=4;
			
			for(int i=4;i<=num;i++) {
				arr[i] = arr[i-1] + arr[i-2] + arr[i-3];
			}
			
			
			System.out.println(arr[num]);
		}
	}
}
