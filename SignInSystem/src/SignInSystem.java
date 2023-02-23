
import java.util.Scanner;

/**
 * @author Wingfung Hung
 */
public class SignInSystem {
    public static void main(String[] args) throws Exception {
        menu();
    }

    public static void menu() throws Exception {
        System.out.println("请输入你的用户名：");
        System.out.println("若还未注册，则直接按0+Enter键进行注册。");
        Scanner scanner=new Scanner(System.in);
        String userName = scanner.next();
        String number="0";
        if (number.equals(userName)){
            System.out.println("开始注册！");
            new SignUpImplement().signUp();
        }else {
            new SignInImplement().signIn(userName);
        }
    }
}
