/**
 * @author Wingfung Hung
 */
public class Account {
    /*成员变量，私有*/

    /**自动生成的卡号*/
    private String cardId;
    /** 用户名*/
    private String userName;
    /** 密码*/
    private String password;
    /** 账户余额*/
    private double money;
    /** 每次取现额度*/
    private double quotaMoney;

    public String getCardId() {
        return cardId;
    }

    public void setCardId(String cardId) {
        this.cardId = cardId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money = money;
    }

    public double getQuotaMoney() {
        return quotaMoney;
    }

    public void setQuotaMoney(double quotaMoney) {
        this.quotaMoney = quotaMoney;
    }
}
