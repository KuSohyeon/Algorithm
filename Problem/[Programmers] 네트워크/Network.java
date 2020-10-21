//[프로그래머스] 네트워크

/*
3
1 1 0
1 1 0
0 0 1
* 2
3
1 1 0 
1 1 1
0 1 1
* 1
*/

import java.util.Scanner;

public class Network {
	static boolean [] v;
	public static void main(String[] args) {
		 Scanner sc = new Scanner(System.in);
		 
		 int n = sc.nextInt();
		 int [][] computers = new int[n][n];
		 
		 for(int i=0;i<n;i++) {
			 for(int j=0;j<n;j++) {
				 computers[i][j]=sc.nextInt();
			 }
		 }
		 
		 int answer = 0;
	     v = new boolean[n];
		 
		 for(int i=0;i<n;i++){
			 if(v[i]) continue;
	         dfs(i,n,computers);  
	         answer++;
	       }
		 
		 System.out.println(answer);
		 
	}
	private static void dfs(int num,int n,int [][] computers) {
		v[num]=true;
		for(int i=0;i<n;i++) {
			if(i== num || v[i]) continue;
			if(computers[num][i]==1) {
				dfs(i,n,computers);
			}
		}
		
	}
	
}
