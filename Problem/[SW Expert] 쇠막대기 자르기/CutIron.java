//[SWEA] 5432.쇠막대기 자르기
/*
2
()(((()())(())()))(())
(((()(()()))(())()))(()())
#1 17
#2 24
 */
import java.util.Scanner;

public class CutIron {
	static int result, iron;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int T = sc.nextInt();
		
		for(int tc=1;tc<=T;tc++) {
			char [] crr = sc.next().toCharArray();
			
			int cnt=0;
			iron=0;
			result=0;
			
			for(int i=0;i<crr.length-1;i++) {
				
				char a = crr[i];
				char b = crr[i+1];
				
				if(a=='(' && b==')') { //만약 레이저인 경우
					result+= iron; //조각에 현재 쇠막대기 개수 더해주고
					iron -= cnt; //이미 끝난 쇠막대기는 현재 쇠막대기에서 갯수 빼주기
					cnt=0;// 끝난 쇠막대기 처리 다했으니까 0으로 만들기
					i++; //() <- i+1까지 처리했으니 i++해서 넘겨주기
				}
				else if(a=='(') { //여는 괄호는 쇠막대기의 시작
					iron++; //쇠막대기 개수 더해주기
				}
				else if(a==')') { //닫힌 괄호는 쇠막대기의 끝
					cnt++; //바로 빼주면 안되니까 일단 표시해주고 레이저로 자른 다음에 빼주기
				}

			}
			
			result+=iron; // 마지막에 잘리고 남은 막대기 처리해주기 (()) 앞에꺼만 처리해줬으니 뒤에껀 반복문 끝나고 처리해줘야함!!!
			
			System.out.println("#"+tc+" "+result);
		}
	}
}
