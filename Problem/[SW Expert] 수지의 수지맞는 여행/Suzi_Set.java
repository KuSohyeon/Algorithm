

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;


public class Suzi_Set {
	static char [][] map;
	static Set<Character> v;
	static int R,C,max;
	static int [] dy = {-1,1,0,0};
	static int [] dx = {0,0,-1,1};
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine());
		for(int tc=1;tc<=T;tc++) {
			max = 0;
			v = new HashSet<Character>();
			
			st = new StringTokenizer(br.readLine().trim());
			R = Integer.parseInt(st.nextToken());
			C = Integer.parseInt(st.nextToken());
			
			map = new char[R][];
			
			for(int i=0;i<R;i++) {
				map[i] = br.readLine().toCharArray(); 
			}
			
			dfs(0,0);
			
			
			
			
			System.out.println("#"+tc+" "+max);
		}
	}
	private static void dfs(int i, int j) {
		
		if(v.size()==26) {
			max=26;
			return;
		}
		
		max = Math.max(max, v.size());
		
		v.add(map[i][j]);
		int ni,nj;
		
		
		for(int d=0;d<4;d++) {
			ni = i + dy[d];
			nj = j + dx[d];
			
			if(ni<0 || ni>=R || nj<0 || nj>=C) continue;
			if(v.contains(map[ni][nj])) continue;
			
			
			dfs(ni,nj);
			
			v.remove(map[ni][nj]);
		}
		
	}
}
