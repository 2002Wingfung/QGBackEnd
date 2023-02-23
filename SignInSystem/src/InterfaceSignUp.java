import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
/**
 *注册
 * @author Wingfung Hung
 */
public interface InterfaceSignUp {
    /**
     * 注册抽象方法
     * //@throws Exception
     */
    void signUp() throws Exception;

    /**
     * 输入信息抽象方法
     * @param connection List< ArrayList<?>>对象
     * @param list
     * //@throws SQLException
     */
    void enterInformation(List<ArrayList<?>> connection,ArrayList<Object> list) throws SQLException;

    /**
     * 输入电子邮箱
     * @param connection List< ArrayList<?>>对象
     * @param list ArrayList< Object>对象
     * //@throws SQLException
     */
    void enterEmailAddress(List<ArrayList<?>> connection,ArrayList<Object> list) throws SQLException;

    /**
     * 输入用户名
     * @return 返回刚刚输入的用户名。
     */
    default String inputUserName(){
        System.out.println("请输入用户名：");
        return new Scanner(System.in).next();
    }

    /**
     * 输入邮箱地址
     * @return 返回刚刚输入的邮箱地址
     */
    default String enterEmail(){
        return new Scanner(System.in).next();
    }

    /**
     * 输入电话号码
     * @return 返回刚刚输入的电话号码
     */
    default long enterPhoneNumber(){
        return new Scanner(System.in).nextLong();
    }

    /**
     * 读取数据库中所有的电话号码，用于注册账号时。
     * @param connection List< ArrayList<?>>对象
     * @param phoneNumber 刚刚输入的电话号码
     * @return 返回true则说明电话号码已存在，返回false则说明电话号码不存在。
     */
    default boolean readAllPhoneNumber(List<ArrayList<?>> connection,long phoneNumber){
        int index=3;
        for (Object number:connection.get(index)){
            if (number.equals(phoneNumber)){
                return true;
            }
        }
        return false;
    }

    /**
     * 读取数据库中所有的电子邮箱，用于注册账号时
     * @param connection List< ArrayList<?>>对象
     * @param email 刚刚输入的电子邮箱
     * @return 返回true则说明电子邮箱已存在，返回false则说明电子邮箱不存在。
     */
    default boolean readAllEmail(List<ArrayList<?>> connection,String email){
        int index=4;
        for (Object number:connection.get(index)){
            if (number.equals(email)){
                return true;
            }
        }
        return false;
    }

    /**
     * 读取电话号码
     * @param connection List< ArrayList<?>>对象
     * @param userName 用户名
     * @param phoneNumber 刚刚输入的电话号码
     * @return 返回true则说明电话号码已存在，返回false则说明电话号码不存在。
     */
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

    /**
     * 读取电子邮箱，用于修改密码时。
     * @param connection List< ArrayList<?>>对象
     * @param userName 用户名
     * @param email 刚刚输入的电子邮箱
     * @return 返回true则说明电子邮箱已存在，返回false则说明电子邮箱不存在。
     */
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

    /**
     * 已完成，找回密码或注册时需要多次输入密码的功能
     * @return 返回确认输入的密码
     */
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

    /**
     * 插入数据到数据库中
     * @param statement statement对象
     * @param list ArrayList< Object>对象
     * //@throws SQLException
     */
    default void insert(Statement statement,ArrayList<Object> list) throws SQLException {
        statement.executeUpdate("insert into signIn values (null,'"+list.get(0)+"','"+list.get(1)+"',"+list.get(2)+",'"+list.get(3)+"')");

    }
}
