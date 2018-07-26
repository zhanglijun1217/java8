package design_pattern.strategy_pattern.strategy;

import design_pattern.strategy_pattern.constant.ReChargeTypeEnum;
import design_pattern.strategy_pattern.intf.Strategy;

/**
 * @author 夸克
 * @create 2018/7/24 14:54
 */
public class MobileStrategy implements Strategy {

    @Override
    public Double calRecharge(Double charge) {
        return charge;
    }
}
