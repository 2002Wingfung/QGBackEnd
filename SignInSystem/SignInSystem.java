package winter_holiday_training_camp.Feb11th.xiaoA;

import java.util.Scanner;

public class SignInSystem {
    public static void main(String[] args) throws Exception {
        menu();
    }
    public static void menu() throws Exception {
        //new SignInImplement().connection();
        System.out.println("请输入你的用户名：");
        System.out.println("若还未注册，则直接按0+Enter键进行注册。");
        Scanner scanner=new Scanner(System.in);
        String userName = scanner.next();
        if ("0".equals(userName)){
            System.out.println("开始注册！");
            new SignUpImplement().signUp();
        }else {
            new SignInImplement().signIn(userName);
        }
    }
}
