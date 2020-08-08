//[SW Expert] 암호생성기(Queue)
/*
1
9550 9556 9550 9553 9558 9551 9551 9551 
2
2419 2418 2423 2415 2422 2419 2420 2415 
3
7834 7840 7840 7835 7841 7835 7835 7838 
4
4088 4087 4090 4089 4093 4085 4090 4084 
5
2945 2946 2950 2948 2942 2943 2948 2947 
6
670 667 669 671 670 670 668 671 
7
8869 8869 8873 8875 8870 8872 8871 8873 
8
1709 1707 1712 1712 1714 1710 1706 1712 
9
10239 10248 10242 10240 10242 10242 10245 10235 
10
6580 6579 6574 6580 6583 6580 6577 6581 

 */


import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class MakePassword {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		Queue <Integer> q = new LinkedList<>();
		
		for(int tc=1;tc<=10;tc++) {
			//입력
			int N = sc.nextInt(); //tc번호
			
			for(int i=0;i<8;i++) {
				q.offer(sc.nextInt());
			}
			
			int cnt=1;
			while(true) {
				int p = q.poll();
				
				if(p<=cnt) {
					q.offer(0);
					break;
				}
				q.offer(p-cnt++);
				
				if(cnt == 6) {
					cnt=1;
				}
			}
			
			
			System.out.print("#"+tc+" ");
			for(int i=0;i<8;i++) {
				System.out.print(q.poll()+" ");
			}
			System.out.println();

		}
		sc.close();
	}
}
