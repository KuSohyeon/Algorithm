import java.util.HashMap;

public class 완주하지못한선수 {
	public static String solution(String[] participants, String[] completion) {
		String answer = "";
		HashMap<String, Integer> hash = new HashMap<String, Integer>();

		// hashMap에 참가자 명단 넣어주기
		for (int i = 0; i < participants.length; i++) {
			if (!hash.containsKey(participants[i])) {
				hash.put(participants[i], 1);
			} else {
				hash.put(participants[i], hash.get(participants[i]) + 1);
			}
		}

		// 참가자 - 완주자 = 1
		for (int i = 0; i < completion.length; i++) {
			int num = hash.get(completion[i]);
			if (num != 1) {
				hash.put(completion[i], num - 1);
			} else {
				hash.remove(completion[i]);
			}
		}

		// 결국 한명만 남을거니까 answer에 반납
		for(String key : hash.keySet()) {
			answer = key;
		}
		return answer;
	}

	public static void main(String[] args) {
		String[] s1 = {"marina", "josipa", "nikola", "vinko", "filipa"};
		String[] s2 = {"marina", "josipa", "nikola", "vinko"};

		System.out.println(solution(s1, s2));
	}
}
