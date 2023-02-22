package winter_holiday_training_camp.Feb11th.xiaoA;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

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
//        Scanner scanner1=new Scanner(System.in);
//        String password1 = scanner1.next();
//        System.out.println("请再次输入密码：");
//        String password2 = scanner1.next();
//        if (password2.equals(password1)){
//            System.out.println("成功！");
//        }else {
//            System.out.println("两次输入的密码不相同，请重新输入密码：");
//            String Password = repeatedPassword();
//        }
        String password=repeatedPassword();
        //System.out.println(password);
        //请输入手机号和邮箱用作找回密码
//        System.out.println("请输入手机号用与重置密码：");
//        if (readAllPhoneNumber(connection,enterPhoneNumber())){
//            System.out.println("该手机号码已被注册过！请重新输入：");
//        }else {
//            if (readAllEmail(connection,enterEmail())){
//                System.out.println("该电子邮箱已被注册过！请重新输入：");
//
//            }else {
//                System.out.println("注册成功！");
//                //写入所有数据到数据库
//            }
//        }
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
//        ResultSet resultSet=statement.executeQuery("select * from stu");
//        ResultSetMetaData metaData= resultSet.getMetaData();
//        int columnCount= metaData.getColumnCount();
        insert(statement,list);
    }
}
