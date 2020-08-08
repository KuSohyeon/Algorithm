//[SW Expert] 암호문1(ArrayList)

/*
11
449047 855428 425117 532416 358612 929816 313459 311433 472478 589139 568205 
7
I 1 5 400905 139831 966064 336948 119288 I 8 6 436704 702451 762737 557561 810021 771706 I 3 8 389953 706628 552108 238749 661021 498160 493414 377808 I 13 4 237017 301569 243869 252994 I 3 4 408347 618608 822798 370982 I 8 2 424216 356268 I 4 10 512816 992679 693002 835918 768525 949227 628969 521945 839380 479976 

출력:
#1 449047 400905 139831 408347 512816 992679 693002 835918 768525 949227
 */

import java.util.ArrayList;
import java.util.Scanner;

public class Password {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		for(int tc=1;tc<=10;tc++) {

			int N = sc.nextInt();
			
			ArrayList<Integer> password = new ArrayList<>();

			for(int i=0;i<N;i++) {
				password.add(sc.nextInt());
			}
			
			int M = sc.nextInt();//명령어의 개수
			
			for(int i=0;i<M;i++) {
				char ch = sc.next().charAt(0); // | 받아 주기
				int start = sc.nextInt();//시작 하는 번호
				int num = sc.nextInt(); // 몇 개?
				
				for(int j=0;j<num;j++) {
					password.add(start+j, sc.nextInt());
				}
			}
			
			

			System.out.print("#"+tc+" ");
			for(int i=0;i<10;i++) {
				System.out.print(password.get(i)+" ");
			}
			System.out.println();
		}
		sc.close();

	}
}
