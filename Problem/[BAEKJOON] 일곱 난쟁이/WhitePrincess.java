//[백준] 일곱 난쟁이
/*
20
7
23
19
10
15
25
8
13
출력 :
7
8
10
13
19
20
23
 */
import java.util.Arrays;
import java.util.Scanner;

public class WhitePrincess {
	static int [] input;
	static int N = 7; //난쟁이 수는 7명
	static int [] smallBoys;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		input = new int[9]; //아홉 난쟁이 입력받을 배열
		smallBoys = new int[7]; // 찐 난쟁이 저장할 배열
		
		// 입력
		for(int i=0;i<9;i++) {
			input[i]=sc.nextInt();
		}
		
		// 9명 중 7명을 뽑아하니까 조합을 이용
		comb(0,0);
	}
	private static void comb(int cnt, int start) {
		if(cnt==N) { //만약 7명을 다 뽑을 경우
			int sum=0;
			for(int s : smallBoys) { //난쟁이 키의 합은 100
				sum += s;
			}
			if(sum==100) { // 만약 일곱 난쟁이 찾을 경우 (가능한 정답이 여러가지일 경우 아무거나 출력)
				Arrays.sort(smallBoys); //키 순으로 정렬 
				for(int s: smallBoys) { //난쟁이 화면에 출력 후
					System.out.println(s);
				}
				System.exit(0); //시스템 종료
			}
			return; //7명 다 뽑을 경우 return
		}
		for(int i=start;i<9;i++) {
			smallBoys[cnt]=input[i];
			comb(cnt+1,i+1);
		}
		
	}
	
} 
