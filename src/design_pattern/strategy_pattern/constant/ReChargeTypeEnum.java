package design_pattern.strategy_pattern.constant;

import java.util.Arrays;

/**
 * 策略模式
 * 支付方式枚举类
 *
 * @author 夸克
 * @create 2018/7/23 17:33
 */
public enum ReChargeTypeEnum {
    E_BANK(1, "网银"),

    BUSI_ACCOUNTS(2, "商户账号"),

    MOBILE(3, "手机充值"),

    CARD_RECHARGE(4, "充值卡");

    private int value;

    private String description;

    private ReChargeTypeEnum(int value, String description) {
        this.value = value;
        this.description = description;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public static ReChargeTypeEnum from(int value) {
        return Arrays.stream(values()).filter(element -> element.getValue() == value).findFirst()
                .orElseThrow(
                        () -> new IllegalArgumentException("error value for ReChargeTypeEnum"));
    }
}
