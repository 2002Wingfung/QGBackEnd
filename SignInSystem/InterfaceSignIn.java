package winter_holiday_training_camp.Feb11th.xiaoA;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

//登录
public interface InterfaceSignIn {
    void signIn(String userName) throws Exception;
    //弄一个方法来读取数据库中的用户名
    default boolean readUserName(List<ArrayList<?>> connection,String userName){
        //connection.get()
        for (Object savedUserName:connection.get(1)){
            //System.out.println(savedUserName);
            if (userName.equals(savedUserName)){
                return true;
            }
        }
        return false;
    }
    default int readUserNameIndex(List<ArrayList<?>> connection,String userName){
        int i;
        for (i=0;i<connection.get(1).size();i++){
            if (userName.equals(connection.get(1).get(i))){
                break;
            }
        }
        return i+1;
    }
    default boolean readPassword(List<ArrayList<?>> connection,String userName,String password){
        //
//        for (Object savedUserName:connection.get(2)){
//            //System.out.println(savedUserName);
//            if (password.equals(savedUserName)){
//                return true;
//            }
//        }
//        return false;
        for (int i=0;i<connection.get(1).size();i++){
            if (connection.get(1).get(i).equals(userName)){
                if (connection.get(2).get(i).equals(password)){
                    return true;
                }
            }
        }
        return false;
    }

    //连接数据库模块已完成
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
        while (resultSet.next()){//获取每一行数据
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
