package winter_holiday_training_camp.ATMSystem;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class ATMSystem {
    public static void main(String[] args) {
        // 1、定义账户类
        // 2、定义一个集合容器，负责以后存储全部的账户对象，进行相关的业务操作.
        ArrayList<Account> accounts=new ArrayList<>();
        Scanner scanner=new Scanner(System.in);
        // 3、展示系统的首页
        while (true) {
            System.out.println("==========ATM系统===========");
            System.out.println("1、账户登录");
            System.out.println("2、账户开户");


            System.out.println("请您选择操作：");
            int command =scanner.nextInt();

            switch (command){
                case 1:
                    // 用户登录操作
                    login(accounts,scanner);
                    break;
                case 2:
                    // 用户账户开户
                    register(accounts,scanner);
                    break;
                default:
                    System.out.println("您输入的操作命令不存在");
            }
        }
    }

    /**
     * 登录功能
     * @param accounts 全部账户对象的集合
     * @param scanner 扫描器
     */
    private static void login(ArrayList<Account> accounts,Scanner scanner){
        System.out.println("==========系统登录操作==========");
        // 1、判断账户集合中是否存在账户，如果不存在账户，登录功能不能进行。
        if (accounts.size()==0){
            System.out.println("对不起，当前系统中无任何账户，请先开户，再来登录~");
            return;// 卫语言风格，结束方法的执行。
        }
        // 2、正式进入登录换作
        while (true) {
            System.out.println("请您输入卡号");
            String cardId=scanner.next();
            // 3、判断卡号是否存在: 根据卡号去账户集合中查询账户对象。
            Account account=getAccountByCardId(cardId,accounts);
            if (account!=null){
                while (true) {
                    // 卡号是存在的
                    // 4、让用户输入密码，认证密码
                    System.out.println("请您输入密码");
                    String password=scanner.next();
                    // 判断当前账户对象的密码是否与用户输入的密码一致
                    if (account.getPassword().equals(password)){
                        // 登录成功了
                        System.out.println("恭喜您，"+account.getUserName()+"先生/女士进入系统，您的卡号是："+account.getCardId());
                        //.... 查询 转账 取款 ....
                        //展示登录后的操作页
                        showUserCommand(scanner,account,accounts);
                        return;
                    }else {
                        System.out.println("对不起，您输入的密码有误");
                    }
                }
            }
        }
    }

    /**
     * 展示登录后的操作页
     */
    private static void showUserCommand(Scanner scanner,Account account,ArrayList<Account> accounts) {
        while (true) {
            System.out.println("==========用户操作页==========");
            System.out.println("1、查询账户");
            System.out.println("2、存款");
            System.out.println("3、取款");
            System.out.println("4、转账");
            System.out.println("5、修改密码");
            System.out.println("6、退出");
            System.out.println("7、注销账户");
            System.out.println("请选择:");
            int command= scanner.nextInt();
            switch (command){
                case 1:
                    // 查询账户(展示当前登录的账户信息)
                    showAccount(account);
                    break;
                case 2://存款
                    depositMoney(scanner,account);
                    break;
                case 3://取款
                    drawMoney(scanner,account);
                    break;
                case 4://转账
                    transferMoney(scanner,account,accounts);
                    break;
                case 5://修改密码
                    updatePassword(scanner,account);
                    return;
                case 6://退出
                    System.out.println("退出成功");
                    return;
                case 7://注销账户
                    if (deleteAccount(scanner,account,accounts)){
                        // 销户成功了，回到首页
                        return;
                    }else {
                        // 没有销户成功，还是回到操作页
                        break;
                    }
                default:
                    break;
            }
        }
    }

    /**
     * 销户
     * @param scanner 扫描器
     * @param account 当前账户
     * @param accounts 所有账户的集合
     */
    private static boolean deleteAccount(Scanner scanner, Account account, ArrayList<Account> accounts) {
        System.out.println("==========用户销户==========");
        System.out.println("您真的要销户吗？输入\"y\"确认销户，输入其他字符则不销户。");
        String rs=scanner.next();
        switch (rs){
            case "y":
                // 真正的销户
                // 从当前账户集合中，删除当前账户对象，销毁就完成了。
                if (account.getMoney()>0){
                    System.out.println("您账户中还有钱没有取完，不允许销户~");
                }else {
                    accounts.remove(account);
                    System.out.println("您的账户销户完成~~");
                    return true;
                }
                break;
            default:
                System.out.println("好的，当前账户继续保留~");
        }
        return false;
    }

    /**
     * 修改密码
     * @param scanner 扫描器
     * @param account 当前账户
     */
    private static void updatePassword(Scanner scanner, Account account) {
        System.out.println("==========用户修改密码==========");
        while (true){
            System.out.println("请您输入当前密码：");
            String password=scanner.next();
            // 1、判断这个密码是否正确
            if (account.getPassword().equals(password)){
                while (true){
                    // 密码正确
                    // 2、输入新密码。
                    System.out.println("请您输入新密码:");
                    String newPassword=scanner.next();
                    System.out.println("请您确认新密码:");
                    String okPassword=scanner.next();
                    if (newPassword.equals(okPassword)){
                        // 2次密码一致，可以修改了
                        account.setPassword(okPassword);
                        System.out.println("恭喜您，您密码修改成功了~~");
                        return;
                    }else {
                        System.out.println("您输入的2次密码不一致~~");
                    }
                }
            }else {
                System.out.println("您输入的密码不正确。");
            }
        }
    }

    /**
     * 转账
     * @param scanner 扫描器
     * @param account 当前账户
     * @param accounts 所有账户
     */
    private static void transferMoney(Scanner scanner, Account account, ArrayList<Account> accounts) {
        System.out.println("==========用户转账操作==========");
        // 1、判断是否足够2个账户
        if (accounts.size()<2){
            System.out.println("当前系统中，不足2个账户，不能进行转账，请去开户吧~~");
            return;
        }

        // 2、判断自己的账户是否有钱
        if (account.getMoney()==0){
            System.out.println("对不起，您的余额为0，不能进行转账");
            return;
        }

        while (true){
            // 3、真正开始转账
            System.out.println("请您输入对方账户的卡号:");
            String cardId=scanner.next();

            // 这个卡号不能是自己的卡号
            if (cardId.equals(account.getCardId())){
                System.out.println("对不起，您不可以给自已进行转账~~");
                continue;// 结束当次执行，死循环进入下一次
            }
            // 判断这个卡号是存在的: 根据这个卡号去查询对方账户对象。
            Account account1=getAccountByCardId(cardId,accounts);
            if (account1==null){
                System.out.println("对不起，您输入对方的这个账号不存在~~");
            }else {
                // 这个账户对象存在了: 继续认证他的姓氏
                String userName=account1.getUserName();
                String tip="*"+userName.substring(1);
                System.out.println("请您输入["+ tip +"]的姓氏");
                String preName=scanner.next();

                // 认证姓氏是否输入正确。
                if (userName.startsWith(preName)){
                    while (true){
                        // 认证通过，真正开始转账了
                        System.out.println("请您输入转账金额：");
                        double money=scanner.nextDouble();
                        // 判断余额是否足够
                        if (money>account.getMoney()){
                            System.out.println("对不起，您的余额不足，您最多可以转账: "+account.getMoney()+"元");
                        }else {
                            // 余额足够，可以转了
                            account.setMoney(account.getMoney()-money);
                            account1.setMoney(account1.getMoney()+money);
                            System.out.println("转账成功! 您的账户还剩余:"+account.getMoney());
                            return;
                        }
                    }
                }else {
                    System.out.println("对不起，您输入的信息有误~~");
                }
            }
        }
    }

    /**
     * 取钱功能
     * @param scanner 扫描器
     * @param account 当前用户
     */
    private static void drawMoney(Scanner scanner, Account account) {
        System.out.println("==========用户取钱操作==========");
        // 1、判断是否足够100元。
        if (account.getMoney()<100){
            System.out.println("对不起，当前账户中不够100元，不能取钱~");
            return;
        }
        while (true){
            // 2、提示用户输入取钱金额
            System.out.println("请您输入取款金额：");
            double money=scanner.nextDouble();

            // 3、判断这个金额是否满足要求。
            if (money>account.getQuotaMoney()){
                System.out.println("对不起，您当前取款金额超过每次限额，每次最多可取:"+account.getQuotaMoney()+"元");
            }else {
                // 没有超过当次限额。
                // 4、判断是否超过了账户的总余额
                if (money>account.getMoney()){
                    System.out.println("余额不足，您账户目前总余额是: "+account.getMoney()+"元");
                }else {
                    // 可以取钱了。
                    System.out.println("恭喜您，取钱"+ money +"元，成功!");
                    // 更新余额
                    account.setMoney(account.getMoney()-money);
                    // 取钱结束了。
                    showAccount(account);
                    return;
                }
            }
        }
    }

    /**
     * 存钱
     * @param scanner 扫描器
     * @param account 当前用户
     */
    private static void depositMoney(Scanner scanner, Account account) {
        System.out.println("==========用户存钱操作==========");
        System.out.println("请输入您存入的金额：");
        double money=scanner.nextDouble();

        // 更新账户余额: 原来的钱 + 新存入的钱
        account.setMoney(account.getMoney()+money);
        System.out.println("恭喜您，存钱成功，当前账户信息如下:");
        showAccount(account);
    }

    private static void showAccount(Account account) {
        System.out.println("==========当前账户信息如下==========");
        System.out.println("卡号:"+account.getCardId());
        System.out.println("户主："+account.getUserName());
        System.out.println("余额："+account.getMoney());
        System.out.println("限额；"+account.getQuotaMoney());
    }

    /**
     * 用户开户的功能实现
     * @param accounts 接收的账户集合
     * @param scanner 输入对象
     */
    private static void register(ArrayList<Account> accounts,Scanner scanner){
        System.out.println("==========系统开户操作==========");
        // 1、创建一个账户对象，用于后期封装账户信息。
        Account account=new Account();

        // 2、录入当前这个账户的信息，注入到账户对象中去
        System.out.println("请您输入账户用户名：");
        String userName=scanner.next();
        account.setUserName(userName);

        while (true){
            System.out.println("请您输入账户密码：");
            String password= scanner.next();
            System.out.println("请您输入确认密码：");
            String okPassword=scanner.next();
            if (okPassword.equals(password)){
                // 密码认证通过，可以注入给账户对象
                account.setPassword(password);
                break;// 密码已经录入成功了，死循环没有必要继续了!
            }else {
                System.out.println("对不起，您输入的2次密码不一致，请重新确认");
            }
        }
        System.out.println("请您输入账户当次限额");
        double quotaMoney=scanner.nextDouble();
        account.setQuotaMoney(quotaMoney);

        // 为账户随机生成一个8位且与其他账户的卡号不重复的号码。(独立功能，独立成方法)。
        String cardId=getRandomCardId(accounts);
        account.setCardId(cardId);

        // 3、把账户对象添加到账户集合中去。
        accounts.add(account);
        System.out.println("恭喜您，"+userName+"先生/女士，您开户成功了，您的卡号是："+cardId+"，请您妥善保管卡号！");

    }

    /**
     * 为账户生成8位与其他账户卡号不同的号码。
     */
    private static String getRandomCardId(ArrayList<Account> accounts){
        Random r=new Random();
        while (true) {
            // 1、先生成8位数字
            String cardId="";

            for (int i = 0; i < 8; i++) {
                cardId+=r.nextInt(10);

            }
            // 2、判断这个8位的卡号是否与其他账户的卡号重复了
            // 根据这个卡号去查询账户的对象
            Account account=getAccountByCardId(cardId,accounts);
            if (account==null){
                // 说明cardId 此时没有重复，这个卡号是一个新卡号了，可以使用这个卡号作为新注册账户的卡号了
                return cardId;
            }
        }
    }

    /**
     * * 根据卡号查询出一个账户对象出来
     * @param cardId 卡号
     * @param accounts 全部账户的集合
     * @return  账户对象 / null
     */
    private static Account getAccountByCardId(String cardId,ArrayList<Account> accounts){
        for (int i = 0; i < accounts.size(); i++) {
            Account account=accounts.get(i);
            if (account.getCardId().equals(cardId)){
                return account;
            }
        }
        return null;
    }
}
