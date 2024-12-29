package p1.teamtodo.user.model;

import p1.teamtodo.common.ResponseResult;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class UserNickname {

    // 정민
    private static final String[] ADJECTIVES = {"귀여운", "사랑스러운", "소중한", "똑똑한", "재빠른", "사악한", "무서운", "토실한", "둔둔한", "고능한", "깜찍한", "애교부리는", "차분한", "배고픈", "인사하는", "계절타는", "얼죽아", "날쌘"};
    private static final String[] NOUNS = {"토끼", "강아지", "고양이", "햄스터", "앵무새", "거북이", "금붕어", "물고기", "물개", "물범", "팬더", "열대어", "재롱이", "도마뱀", "펭귄", "친칠라", "카피바라", "카멜레온", "사슴", "북극곰", "하트물범", "너구리", "라쿤", "알파카", "다람쥐"};
    private static final Random RANDOM = new Random();

    public static String createRandomUserNickname() {
        String nickname;
        String adjective = ADJECTIVES[RANDOM.nextInt(ADJECTIVES.length)];
        String noun = NOUNS[RANDOM.nextInt(NOUNS.length)];
        int randomNumber = RANDOM.nextInt(9000) + 1000; // 1000~9999 범위의 숫자 생성
        nickname = adjective + noun + "#" + randomNumber;
        return nickname;
    }

    public static String createdUserNicknameWithNumber(String originalNickname) {
        return originalNickname + "#" + (RANDOM.nextInt(9000) + 1000);
    }

    public static String getUserNicknameWithOutNumber(String fullNickname) {
        try {
            return fullNickname.substring(0, fullNickname.indexOf("#"));
        } catch (Exception e) {
            return null;
        }
    }
}
