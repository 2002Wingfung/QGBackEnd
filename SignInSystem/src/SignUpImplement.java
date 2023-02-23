
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Wingfung Hung
 */
public class SignUpImplement implements InterfaceSignUp,InterfaceSignIn{
    @Override
    public void signUp() throws Exception {
        List<ArrayList<?>> connection=connection();
        String newUserName=inputUserName();
        //判断该用户名与数据库中的是否重复
        while (readUserName(connection,newUserName)){
            System.out.println("该用户名已存在！");
            newUserName=inputUserName();
        }
        System.out.println("请输入密码：");

        String password=repeatedPassword();
        ArrayList<Object> list=new ArrayList<>();
        list.add(newUserName);
        list.add(password);
        enterInformation(connection,list);

    }

    @Override
    public void signIn(String userName) {

    }

    @Override
    public void enterInformation(List<ArrayList<?>> connection,ArrayList<Object> list) throws SQLException {
        System.out.println("请输入手机号用于重置密码：");
        long phoneNumber=enterPhoneNumber();
        if (readAllPhoneNumber(connection,phoneNumber)){
            System.out.println("该手机号码已被注册过！请重新输入：");
            enterInformation(connection,list);
        }else {
            list.add(phoneNumber);
            System.out.println("请输入电子邮箱用于重置密码：");
            enterEmailAddress(connection,list);
        }
    }

    @Override
    public void enterEmailAddress(List<ArrayList<?>> connection,ArrayList<Object> list) throws SQLException {
        String email=enterEmail();
        if (readAllEmail(connection,email)){
            System.out.println("该电子邮箱已被注册过！请重新输入：");
            enterEmailAddress(connection,list);
        }else {
            list.add(email);
            System.out.println("注册成功！");
            //写入所有数据到数据库
            writeInformation(list);
        }
    }



    public void writeInformation(ArrayList<Object> list) throws SQLException {
        Connection connection= DriverManager.getConnection("jdbc:mysql://localhost:3306/mysql_test","root","root");
        Statement statement=connection.createStatement();
        insert(statement,list);
    }
}
