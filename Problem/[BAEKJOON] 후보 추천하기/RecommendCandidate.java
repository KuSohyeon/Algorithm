//[백준] 후보 추천하기

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class RecommendCandidate {
	static int N;
	static Student [] candidates;
	static class Student implements Comparable<Student>{
		int num;
		int order;
		int cnt;
		public Student(int num, int order, int cnt) {
			super();
			this.num = num;
			this.order = order;
			this.cnt = cnt;
		}
		@Override
		public String toString() {
			return "Student [num=" + num + ", order=" + order + ", cnt=" + cnt + "]";
		}
		@Override
		public int compareTo(Student o) {
			return Integer.compare(this.num, o.num);
		}
		
	}
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		candidates = new Student[N];
		
		int n = Integer.parseInt(br.readLine());
		
		int order = 0;
		
		st = new StringTokenizer(br.readLine());
		for(int i=0;i<n;i++) {
			int person = Integer.parseInt(st.nextToken());
			boolean flag = false;
			for(int j=0;j<N;j++) {
				if(candidates[j]!=null) { // 같은 번호의 후보자일 경우는 투표수만 늘려주기
					if(candidates[j].num == person) {
						candidates[j].cnt++;
						flag = true;
						break;
					}
				}
				else { // 후보자에 내 이름이 없을 경우, 사진틀이 아직 비어있다면 거기 넣어주기
					candidates[j] = new Student(person, order++, 1);
					flag = true;
					break;
				}
			}
			// 후보자가 가득 차있는 경우 가장 오래되고, 가장 적은 투표수의 후보자를 삭제하고 그 자리에 넣는다.
			if(!flag) {
				int min = 987654321; // 최소로 투표받은 학생을 찾아야함
				int old = 987654321; // 제일 오래된 학생은 order이 작을 것이니까 이것도 큰값으로 초기화
				int idx = 0;
				for(int j=0;j<N;j++) {
					if(candidates[j].cnt<min) {
						min = candidates[j].cnt;
						old = candidates[j].order;
						idx = j;
					}else if(candidates[j].cnt==min) {
						if(old>candidates[j].order) {
							old = candidates[j].order;
							idx = j;
						}
					}
				}
				candidates[idx] = new Student(person, order++, 1);
			}
		}
		
		Arrays.sort(candidates); // 학생 번호 순으로 소트
		
		for(int i=0;i<N;i++) {
			System.out.print(candidates[i].num+" ");
		}
	}
}
