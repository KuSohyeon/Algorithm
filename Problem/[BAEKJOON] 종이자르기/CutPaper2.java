import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

//리스트 세로용 가로용 2개 만들어서 입력할 때 나눠서 담기
// Collectons.sort 해서 오름차순으로 정렬해주기
// 그리고 이제 각 가로 길이 세로 길이 계산해서 새로운 리스트에 담아주기(가로길이, 세로길이용)
// for문 두개로 가로 길이당 세로길ㄷ이 다 돌려보고 곱해서 max 값을 업데이트를 해주기
public class CutPaper2 {
	static List<Integer> garo,sero;
	static int W,H,N;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		W = sc.nextInt();
		H = sc.nextInt();
		N = sc.nextInt();
		
		// 한번도 안자르는 경우 가지치기
		if(N==0) {
			System.out.println(W*H);
			return;
		}
		
		garo = new ArrayList<Integer>();
		sero = new ArrayList<Integer>();
		garo.add(0);
		sero.add(0);
		
		for(int i=0;i<N;i++) {
			int state = sc.nextInt();
			if(state==0) {
				garo.add(sc.nextInt());
			}else {
				sero.add(sc.nextInt());
			}
		}
		
		garo.add(H);
		sero.add(W);
		
		// 오름차순으로 정렬
		Collections.sort(garo);
		Collections.sort(sero);
		
		int [] width = new int[sero.size()];
		int [] height = new int[garo.size()];
		
		//가로 길이 계산
			for(int i=1;i<sero.size();i++) {
				width[i]=sero.get(i)-sero.get(i-1); // 현재꺼에서 앞에꺼를 빼면 그 길이 나옴
			}

		//세로 길이 계산
			for(int i=1;i<garo.size();i++) {
				height[i]=garo.get(i)-garo.get(i-1);// 현재꺼에서 앞에꺼를 빼면 그 길이 나옴
			}


		// 넓이 계산
		Arrays.sort(width);
		Arrays.sort(height);
		
		System.out.println(width[width.length-1]*height[height.length-1]);
	}
}
