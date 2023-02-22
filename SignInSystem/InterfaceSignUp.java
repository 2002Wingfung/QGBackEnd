package winter_holiday_training_camp.Feb11th.xiaoA;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
//注册
public interface InterfaceSignUp {
    void signUp() throws Exception;
    void enterInformation(List<ArrayList<?>> connection,ArrayList<Object> list) throws SQLException;
    void enterEmailAddress(List<ArrayList<?>> connection,ArrayList<Object> list) throws SQLException;
    //输入用户名
    default String inputUserName(){
        System.out.println("请输入用户名：");
        return new Scanner(System.in).next();
    }
    default String enterEmail(){
        return new Scanner(System.in).next();
    }
    default long enterPhoneNumber(){
        return new Scanner(System.in).nextLong();
    }

    default String enterPassword(){
        return new Scanner(System.in).next();
    }
    default boolean readAllPhoneNumber(List<ArrayList<?>> connection,long phoneNumber){
        for (Object number:connection.get(3)){
            if (number.equals(phoneNumber)){
                return true;
            }
        }
        return false;
    }
    default boolean readAllEmail(List<ArrayList<?>> connection,String email){
        for (Object number:connection.get(4)){
            if (number.equals(email)){
                return true;
            }
        }
        return false;
    }

    default boolean readPhoneNumber(List<ArrayList<?>> connection, String userName,long phoneNumber){
        for (int i=0;i<connection.get(1).size();i++){
            if (connection.get(1).get(i).equals(userName)){
                if (connection.get(3).get(i).equals(phoneNumber)){
                    return true;
                }
            }
        }
        return false;
    }
    default boolean readEmail(List<ArrayList<?>> connection, String userName,String email){
        for (int i=0;i<connection.get(1).size();i++){
            if (connection.get(1).get(i).equals(userName)){
                if (connection.get(4).get(i).equals(email)){
                    return true;
                }
            }
        }
        return false;
    }
    //已完成，找回密码或注册时需要多次输入密码的功能
    default String repeatedPassword(){
        Scanner scanner1=new Scanner(System.in);
        String password1 = scanner1.next();
        System.out.println("请再次输入密码：");
        String password2 = scanner1.next();
        if (password2.equals(password1)){
            System.out.println("成功！");
            return password1;
        }else {
            System.out.println("两次输入的密码不相同，请重新输入密码：");
            return repeatedPassword();
        }
    }
    default long repeatedPhoneNumber(){
        return new Scanner(System.in).nextLong();

    }
    default String repeatedEmail(){
        return new Scanner(System.in).next();
    }
    //判断注册时用户名是否重复
    default String repeatedUserName() {
        System.out.println("请输入用户名：");
        Scanner scanner=new Scanner(System.in);
        return scanner.next();
    }

    default void insert(Statement statement,ArrayList<Object> list) throws SQLException {
        statement.executeUpdate("insert into signIn values (null,'"+list.get(0)+"','"+list.get(1)+"',"+list.get(2)+",'"+list.get(3)+"')");

    }
}
