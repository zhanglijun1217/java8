package design_pattern.strategy_pattern.main;

import design_pattern.strategy_pattern.constant.ReChargeTypeEnum;
import design_pattern.strategy_pattern.context.Context;

/**
 * @author 夸克
 * @create 2018/7/26 23:30
 */
public class StrategyMain {

    public static void main(String[] args) {
        Context context = new Context();

        /**
         * 计算四种计算方式
         */
        Double aDouble = context.calRecharge(100D, ReChargeTypeEnum.E_BANK.getValue());
        Double bDouble = context.calRecharge(100D, ReChargeTypeEnum.BUSI_ACCOUNTS.getValue());
        Double cDouble = context.calRecharge(100D, ReChargeTypeEnum.MOBILE.getValue());
        Double dDouble = context.calRecharge(100D, ReChargeTypeEnum.CARD_RECHARGE.getValue());

        System.out.println(aDouble + "\t" + bDouble + "\t" + cDouble + "\t" + dDouble);
    }
}
