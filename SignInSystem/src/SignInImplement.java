import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * 登录
 * @author Wingfung Hung
 * */
public class SignInImplement implements InterfaceSignIn,InterfaceSignUp{

    @Override
    public void signIn(String userName) throws Exception {

        List<ArrayList<?>> connection=connection();
        System.out.println("请输入密码：");
        Scanner scanner=new Scanner(System.in);
        String password=scanner.next();
        if (readPassword(connection,userName,password)) {
            System.out.println("你好！"+userName);
        }else {
            System.out.println("用户名或密码错误！\n重新尝试请按1+Enter;忘记密码请输入其他字符+Enter：");
            String number="1";
            if (new Scanner(System.in).next().equals(number)){
                this.signIn(inputUserName());
            }else {
                if (readUserName(connection,userName)){
                    System.out.println("请输入注册时填写的手机号码：");
                    long phoneNumber=scanner.nextLong();
                    //在数据库中寻找对应用户名的手机号和邮箱地址
                    if (readPhoneNumber(connection,userName,phoneNumber)){
                        //重置密码
                        System.out.println("手机号码正确！请输入新的密码：");
                        changePassword(repeatedPassword(),userName);
                    }else {
                        System.out.println("手机号码错误！\n请输入注册时填写的电子邮箱：");
                        String email = scanner.next();
                        if (readEmail(connection,userName,email)){
                            System.out.println("电子邮箱正确！\n请输入新的密码：");
                            //调用函数多次输入密码
                            changePassword(repeatedPassword(),userName);
                        }else {
                            System.out.println("电子邮箱错误！\n重置密码失败，自动退出系统！");
                            System.exit(1);
                        }
                    }
                }else {
                    System.out.println("用户名有误！请重新输入：");
                    this.signIn(inputUserName());
                }
            }
        }
    }

    public SignInImplement() {
    }
    /**
     *将重置后的密码写入数据库
     */
    public void changePassword(String password,String userName) throws SQLException {
        //将重置后的密码写入数据库
        Connection connection= DriverManager.getConnection("jdbc:mysql://localhost:3306/mysql_test","root","root");
        Statement statement=connection.createStatement();
        statement.executeUpdate("update signIn set password ='"+password+"' where userName='"+userName+"'");
    }


    @Override
    public void signUp() {

    }

    @Override
    public void enterInformation(List<ArrayList<?>> connection,ArrayList<Object> list) {

    }

    @Override
    public void enterEmailAddress(List<ArrayList<?>> connection,ArrayList<Object> list) {

    }
}
