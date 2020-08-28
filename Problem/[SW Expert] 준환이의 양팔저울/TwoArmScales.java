//[SW Expert] 준환이의 양팔 저울
/*
3
3
1 2 4
3
1 2 3
9
1 2 3 5 6 4 7 8 9
출력 :
#1 15
#2 17
#3 35583723
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class TwoArmScales {
	static int N,totalCnt; 
	static int [] arr; //input
	static boolean [] isSelected;
	static boolean [] check;
	static int [] per; //순열로 줄세운거 저장해주는 배열
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine());
		for(int tc=1;tc<=T;tc++) {
			totalCnt = 0;
			
			N = Integer.parseInt(br.readLine());
			
			arr = new int[N];// input
			per = new int[N];// 순열 저장용
			isSelected = new boolean[N];//순열때 쓸꺼
			check = new boolean[N]; //순열로 만든 배열 체크용
			
			//입력
			st = new StringTokenizer(br.readLine());
			for(int i=0;i<N;i++) {
				arr[i]=Integer.parseInt(st.nextToken());
			}
			perm(0);
			
			System.out.println("#"+tc+" " +totalCnt);
		}
	}
	private static void perm(int cnt) {//순서에 따라 무게가 달라지므로 순열 만들어주기
		if(cnt==N) {
			dfs(0,0,0,0,per);// 배열 만들어지면 subset만드는 dfs에 만든 배열 파라미터로 넘겨주기
			return;
		}
		for(int i=0;i<N;i++) {
			if(isSelected[i]) continue;
			isSelected[i]=true;
			per[cnt]=arr[i];
			perm(cnt+1);
			isSelected[i]=false;
		}
		
	}
	private static void dfs(int cnt,int lc,long w1, long w2,int [] per) {//전달받은 순열로 부분집합 만들기
		if(w1<w2) {//혹시 중간에 무게 엎어지면 return;
			return;
		}
		if(cnt==N) {//N일때 그만하기
			++totalCnt;
			return;
		}
		check[cnt]=true;
		dfs(cnt+1,lc+1,w1+per[cnt],w2,per);
		check[cnt]=false;
		dfs(cnt+1,lc,w1,w2+per[cnt],per);
		
	}
}
