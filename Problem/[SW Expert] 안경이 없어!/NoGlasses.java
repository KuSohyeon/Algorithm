//[SW Expert] 7272.안경이 없어!
/*
5
ABCD EFGH
ABCD PBZO
PRQO OQAD
ZXCV HJKL
BBBB BBB

 출력:
#1 DIFF
#2 SAME
#3 SAME
#4 SAME
#5 DIFF
 */

import java.util.Scanner;

public class NoGlasses {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		int T = sc.nextInt();

		for(int tc=1;tc<=T;tc++) {
			
			boolean flag = false;
			
			char [] crr1 = sc.next().toCharArray();
			char [] crr2 = sc.next().toCharArray();

			if(crr1.length!=crr2.length) {
				System.out.println("#"+tc+" "+"DIFF");
				continue;
			}

			for(int i=0;i<crr1.length;i++) {
				switch(crr1[i]) {
				case 'A':
				case 'D':
				case 'O':
				case 'P':
				case 'Q':
				case 'R':
					crr1[i]='1';
					break;
				case 'B':
					crr1[i]='2';
					break;
				default:
					crr1[i]='0';
					break;
				}
				
				switch(crr2[i]) {
				case 'A':
				case 'D':
				case 'O':
				case 'P':
				case 'Q':
				case 'R':
					crr2[i]='1';
					break;
				case 'B':
					crr2[i]='2';
					break;
				default:
					crr2[i]='0';
					break;
				}
				
				if(crr1[i]!=crr2[i]) {
					flag=true;
					break;
				}
			}
			
			String result = "SAME";
			
			if(flag) {
				result = "DIFF";
			}
			
			System.out.println("#"+tc+" "+result);
		}
		sc.close();
	}
}
