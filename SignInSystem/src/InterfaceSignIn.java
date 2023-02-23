import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * 登录
 * @author Wingfung Hung
 */
public interface InterfaceSignIn {
    /**
     * 登录接口
     * @param userName 用户名
     * //@throws Exception
     */
    void signIn(String userName) throws Exception;

    /**
     *读取数据库中的用户名
     * @param connection List< ArrayList<?>>对象
     * @param userName 输入的用户名
     * @return 返回true则说明该用户名已存在于数据库中，返回false则说明该用户名不存在
     */
    default boolean readUserName(List<ArrayList<?>> connection,String userName){
        //connection.get()
        for (Object savedUserName:connection.get(1)){
            if (userName.equals(savedUserName)){
                return true;
            }
        }
        return false;
    }


    /**
     * 读取密码
     * @param connection List< ArrayList<?>>对象
     * @param userName 用户名
     * @param password 密码
     * @return 返回true则密码正确，返回false则密码错误
     */
    default boolean readPassword(List<ArrayList<?>> connection,String userName,String password){
        for (int i=0;i<connection.get(1).size();i++){
            if (connection.get(1).get(i).equals(userName)){
                if (connection.get(2).get(i).equals(password)){
                    return true;
                }
            }
        }
        return false;
    }

    /**
     *连接数据库模块
     * @return List< ArrayList<?>>对象
     * //@throws Exception
     */

    default List<ArrayList<?>> connection() throws Exception{
        //1: 获取连接
        Connection connection= DriverManager.getConnection("jdbc:mysql://localhost:3306/mysql_test","root","root");

        //2: 执行sql
        Statement statement=connection.createStatement();
        ResultSet resultSet=statement.executeQuery("select * from signIn");
        ArrayList<Integer> idList=new ArrayList<>();
        ArrayList<String> userNameList=new ArrayList<>();
        ArrayList<String> passwordList=new ArrayList<>();
        ArrayList<Long> phoneNumberList=new ArrayList<>();
        ArrayList<String> emailList=new ArrayList<>();
        List<ArrayList<?>> arrayLists=new ArrayList<>();
        arrayLists.add(idList);
        arrayLists.add(userNameList);
        arrayLists.add(passwordList);
        arrayLists.add(phoneNumberList);
        arrayLists.add(emailList);

        //3: 处理结果集
        //获取每一行数据
        while (resultSet.next()){

            //获取每一列数据
            int id=resultSet.getInt(1);
            idList.add(id);
            String userName=resultSet.getNString(2);
            //索引编号为1，则为第一列，而不是从0开始，从0开始是错的。
            userNameList.add(userName);
            String password=resultSet.getNString("password");
            passwordList.add(password);
            long phoneNumber=resultSet.getLong("phoneNumber");
            phoneNumberList.add(phoneNumber);
            String email=resultSet.getNString(5);
            emailList.add(email);

        }

        //4.关闭连接
        resultSet.close();
        statement.close();
        connection.close();
        return arrayLists;
    }
}
