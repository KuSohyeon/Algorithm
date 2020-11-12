//[프로그래머스] 카카오 불량사용자
/**
 * 완탐
 * user_id의 배열의 크기는 1~8 => 완탐  OK
 * 순서에 상관없이 요소가 같다면 같은 경우가 되기 때문에 set에 넣어서 필터링
 * boolean isSameString(String a, String b) 함수를 넣어 두 문자열의 일치여부를 판단
 * boolean isBannedUsers(Set<String> set, String [] banned_id)
 * : 뽑힌 set이 banned_id 의 목록과 일치하는지 판단
 */

import java.util.*;

public class BadCredit {
	static String[] user, banned;
	private static Set<Set<String>> result;

	public static int solution(String[] user_id, String[] banned_id) {
		user = user_id;
		banned = banned_id;
		result = new HashSet<>();
		dfs(new LinkedHashSet<>());
		return result.size();
	}

	private static void dfs(Set<String> set) {
		if (set.size() == banned.length) {
			if (isBannedUsers(set)) { // 중복경우가 없다면 result에 추가
				result.add(new HashSet<>(set));
			}

			return;
		}

		for (String userId : user) {
			if (!set.contains(userId)) {
				set.add(userId);
				dfs(set);
				set.remove(userId); // 백트래킹
			}
		}

	}
	// 뽑힌 set이 bannedUser와 일치하는지 판단 여부
	private static boolean isBannedUsers(Set<String> set) {
		int i = 0;

		for (String user : set) {
			if (!isSameString(user, banned[i++])) {
				return false;
			}
		}

		return true;
	}
	// 두 문자열 비교
	private static boolean isSameString(String a, String b) {
		if (a.length() != b.length()) {
			return false;
		}

		for (int i = 0; i < a.length(); i++) {
			if (b.charAt(i) == '*')
				continue;

			if (a.charAt(i) != b.charAt(i)) {
				return false;
			}
		}

		return true;
	}

	public static void main(String[] args) {
		String[] user_id = { "frodo", "fradi", "crodo", "abc123", "frodoc" };
		 String [] banned_id = {"fr*d*", "abc1**"};
//		String[] banned_id = { "fr*d*", "*rodo", "******", "******" };
//		 String [] banned_id = {"*rodo", "*rodo", "******"};
		solution(user_id, banned_id);
		System.out.println(result.size());
	}
}
