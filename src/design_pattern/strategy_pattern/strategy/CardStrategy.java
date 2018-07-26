package design_pattern.strategy_pattern.strategy;

import design_pattern.strategy_pattern.constant.ReChargeTypeEnum;
import design_pattern.strategy_pattern.intf.Strategy;

/**
 * @author 夸克
 * @create 2018/7/24 14:57
 */
public class CardStrategy implements Strategy {

    @Override
    public Double calRecharge(Double charge) {
        return charge + charge * 0.01;
    }
}
