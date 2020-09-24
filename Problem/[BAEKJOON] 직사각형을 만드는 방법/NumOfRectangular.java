//[백준] 직사각형을 만드는 방법

import java.util.Scanner;

public class NumOfRectangular {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int N = sc.nextInt();
		
		int cnt = N;// 일단 높이가 1인 직사각형은 cnt에 바로 넣어주기
		// 높이가 2인 직사각형 부터 반복문 돌리기
		for(int i=2;i<N;i++) {
			for(int j=2;j<N;j++) {// 2*2부터 시작 -> 2*1=1*2니까
				if(i*j>N) {//직사각형 만드는데 현재 가지고 있는 수보다 더 많이 필요하다면 break 
					break;
				}
				if(i>j) { // 회전해서 같으면 안됨 
					if(i*j==j*i) continue; // 이전에 했다면 넘겨주기
				}
				cnt++; // 만들 수 있는 직사각형 개수 늘려주기 
				
			}
		}
		System.out.println(cnt);
	}
}
