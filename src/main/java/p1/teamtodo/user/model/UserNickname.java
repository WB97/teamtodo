package p1.teamtodo.user.model;

import p1.teamtodo.common.ResponseResult;

public class UserNickname {

    public static String createRandomUserNickname() {
        String[] names = {"날쌘다람쥐", "빛모임재개", "오가리과자",
                "오크궁수대장", "고블린돌격대장", "여우주머니",
                "진주비빕밥", "등살힘재개", "대머리마법사",
                "충격성형기", "만다라작법", "종합채탄기"
        };

        int index = (int)(Math.random() * 10);
        return names[index] + "#";
    }

    public static String getUserNicknameWithOutNumber(String fullNickname) {
        try {
            return fullNickname.substring(0, fullNickname.indexOf("#"));
        } catch (Exception e) {
            return null;
        }
    }
}
