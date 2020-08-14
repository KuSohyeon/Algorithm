//[SW Expert] 1983. 조교의 성적매기기

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.StringTokenizer;

public class GradeCheck {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine());
		
		for(int tc=1;tc<=T;tc++) {
			
			st = new StringTokenizer(br.readLine().trim());
			int people = Integer.parseInt(st.nextToken());
			int who = Integer.parseInt(st.nextToken());
			
			Data [] arr = new Data[people];
			//입력
			for(int i=0;i<people;i++) {
				st = new StringTokenizer(br.readLine().trim());
				int mid = Integer.parseInt(st.nextToken());
				int fin = Integer.parseInt(st.nextToken());
				int rep = Integer.parseInt(st.nextToken());
				double score = (mid*0.35 + fin*0.45 + rep*0.2);
				arr[i]= new Data(i+1,mid,fin,rep,score);//하나씩 배열에 넣어주기

			}
			
			
			String gpa = null;//tc마다 초기화
			int idx=0;
			Arrays.sort(arr);

			//소트 후 학점을 알고 싶은 사람이 몇번째 있는지 찾기
			for(int i=0;i<people;i++) {
				if(arr[i].idx==who) {
					idx=i;
					break;
				}
			}
			
	
			if(idx<people/10) {
				gpa = "A+";
			}else if(idx<people/10*2) {
				gpa = "A0";
			}else if(idx<people/10*3) {
				gpa = "A-";
			}else if(idx<people/10*4) {
				gpa = "B+";
			}else if(idx<people/10*5) {
				gpa = "B0";
			}else if(idx<people/10*6) {
				gpa = "B-";
			}else if(idx<people/10*7) {
				gpa = "C+";
			}else if(idx<people/10*8) {
				gpa = "C0";
			}else if(idx<people/10*9) {
				gpa = "C-";
			}else {
				gpa = "D";
			}
			

			
			System.out.println("#"+tc+" "+ gpa);
		}
	}
	
	static class Data implements Comparable<Data>{
		int idx;
		int mid;
		int fin;
		int rep;
		double score;
		
		public Data(int idx, int mid, int fin, int rep, double score) {
			super();
			this.idx = idx;
			this.mid = mid;
			this.fin = fin;
			this.rep = rep;
			this.score = score;
		}

		
		@Override
		public int compareTo(Data o) {//오름차순으로 정렬(Override는 함수명, 파라미터, 타입 아무것도 건드리면 안되니까 소수점 단위일 경우 o가 더 크면 자리 바꾸기
			
			return (o.score-this.score>0)?1:-1;
		}
		
		
	}
}
