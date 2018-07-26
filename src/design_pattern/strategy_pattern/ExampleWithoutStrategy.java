package design_pattern.strategy_pattern;

import design_pattern.strategy_pattern.constant.ReChargeTypeEnum;

/**
 * 未使用策略模式
 * @author 夸克
 * @create 2018/7/23 17:44
 */
public class ExampleWithoutStrategy {

    // 最容易想到的是用 if-else 去写 （或者switch case去写）
    public Double calReCharge(Double charge, ReChargeTypeEnum type) {
        if (type.equals(ReChargeTypeEnum.E_BANK)) {
            return charge * 0.85;
        } else if (type.equals(ReChargeTypeEnum.BUSI_ACCOUNTS)) {
            return charge * 0.90;
        } else if (type.equals(ReChargeTypeEnum.MOBILE)) {
            return charge;
        } else if (type.equals(ReChargeTypeEnum.CARD_RECHARGE)) {
            return charge * 1.01;
        }
        return null;
    }
}
