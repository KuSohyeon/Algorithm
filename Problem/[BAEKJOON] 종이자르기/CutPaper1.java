import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

//리스트 세로용 가로용 2개 만들어서 입력할 때 나눠서 담기
// Collectons.sort 해서 오름차순으로 정렬해주기
// 그리고 이제 각 가로 길이 세로 길이 계산해서 새로운 리스트에 담아주기(가로길이, 세로길이용)
// for문 두개로 가로 길이당 세로길ㄷ이 다 돌려보고 곱해서 max 값을 업데이트를 해주기
public class CutPaper1 {
	static List<Integer> garo,sero;
	static int W,H,N;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		W = sc.nextInt();
		H = sc.nextInt();
		N = sc.nextInt();
		
		if(N==0) { // 안자를 때 가지치기
			System.out.println(W*H);
			return;
		}
		
		garo = new ArrayList<Integer>();
		sero = new ArrayList<Integer>();

		for(int i=0;i<N;i++) {
			int state = sc.nextInt();
			if(state==0) {
				garo.add(sc.nextInt());
			}else {
				sero.add(sc.nextInt());
			}
		}
		
		Collections.sort(garo);
		Collections.sort(sero);
		
		int [] width = new int[sero.size()+1];
		int [] height = new int[garo.size()+1];
		
		// 무조건 잘랐을 때
		
		//가로 길이 계산
		if(sero.size()!=0) {
			width[0]=sero.get(0);
			for(int i=1;i<sero.size();i++) {
				width[i]=sero.get(i)-sero.get(i-1);
			}
			width[sero.size()]=W-sero.get(sero.size()-1);
		}
		//세로 길이 계산
		if(garo.size()!=0) {
			height[0]=garo.get(0);
			for(int i=1;i<garo.size();i++) {
				height[i]=garo.get(i)-garo.get(i-1);
			}
			height[garo.size()]=H-garo.get(garo.size()-1);
		}
		
		// 만약 가로나 세로 중 어디 안잘렸으면 그냥 가로/세로 길이 넣어주기
		if(garo.size()==0) {
			height[0]=H;
		}
		if(sero.size()==0) {
			width[0]=W;
		}
		
		// 넓이 계산
		int max=0;
		for(int i=0;i<width.length;i++) {
			for(int j=0;j<height.length;j++) {
				int area = width[i]*height[j];
				max = Math.max(max, area);
			}
		}
		
		System.out.println(max);
	}
}
