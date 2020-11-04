import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class OrderHeight {
	static int N,M,result;
	static int [][] map;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine());
		
		for(int tc=1;tc<=T;tc++) {
			
			N = Integer.parseInt(br.readLine());
			M = Integer.parseInt(br.readLine());
			
			map = new int[N+1][N+1];
			
			for(int i=0;i<M;i++) {
				st = new StringTokenizer(br.readLine().trim());
				
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				
				map[a][b]=1; // 나 보다 키 크면 1
				map[b][a]=-1; // 나 보다 키 작으면 -1
				
			}
			
			result=0;
			
			for(int i=1;i<=N;i++) {
				int total = 0;
				total += bfsH(i); // 나보다 작은애 세리기
				total += bfsS(i); // 나보다 큰 애 세리기
				if(total==N-1){ // 나보다 작은애 + 큰애 = 나 빼고 인원수면 내 키가 몇번째인지 알 수 있음
					result++;
				}
			}
			System.out.println("#"+tc+" "+result);
		}
	}
	// 나보다 작은 사람 숫자 세리기
	private static int bfsS(int num) {
		Queue<Integer> q = new LinkedList<Integer>();
		boolean [] v = new boolean [N+1];
		
		q.offer(num);
		v[num]=true;
		int cnt=0;
		while(!q.isEmpty()) {
			int now = q.poll();
			
			for(int i=1;i<=N;i++) {
				if(map[now][i]==-1 && !v[i]) { // 나보다 작고 카운트 한 적이 없다면
					cnt++; // 나보다 작은애 숫자를 더해주고
					v[i]=true;// 방문처리 후 
					q.offer(i); // 큐에 넣어주기
				}
			}
			
		}
		
		return cnt;
	}
	// 나보다 큰 사람 숫자 세리기
	private static int bfsH(int num) {
		Queue<Integer> q = new LinkedList<Integer>();
		boolean [] v = new boolean [N+1];
		
		q.offer(num);
		v[num]=true;
		int cnt=0;
		while(!q.isEmpty()) {
			int now = q.poll();

			for(int i=1;i<=N;i++) {
				if(map[now][i]==1 && !v[i]){
					cnt++;
					v[i]=true;
					q.offer(i);
				}
			}
			
		}
		
		return cnt;
		
	}
	
	

}
