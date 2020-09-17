//[백준] 비밀 이메일
/*
입력 : boudonuimilcbsai
출력 : bombonisuuladici
 */
import java.util.Scanner;

//해독방법
//1. 글자 수 세기
//2. 행렬 만들기(R,C값 찾기)
//3. 열 순으로 채우기
//4. 행 순으로 출력

public class Email {
	static char [] letter;
	static int R,C,nR,nC;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		letter = sc.next().toCharArray();

		int length= letter.length;

		R=0;
		C=0;
		nR=0;
		nC=0;

		//행렬 만들기
		for(int i=1;i<length;i++) {
			int tmp=length%i;
			if(tmp==0) {
				int num = length/i;
				if(num<i) continue;
				nR=i;
				nC=num;
				if(nR>R) {
					R=nR;
					C=nC;
				}
			}
		}

//		System.out.println(R+" "+C);

		// 배열에 저장
		char [][] matrix = new char[R][C];
		int cnt=0;
		
		// 해독화
		for(int j=0;j<C;j++) {
			for(int i=0;i<R;i++) {
				matrix[i][j]=letter[cnt++];
			}
		}

		// 출력
		for(int i=0;i<R;i++) {
			for(int j=0;j<C;j++) {
				System.out.print(matrix[i][j]);
			}
		}


	}

}

