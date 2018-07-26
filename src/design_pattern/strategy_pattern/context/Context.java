package design_pattern.strategy_pattern.context;

import design_pattern.strategy_pattern.factory.StrategyFactory;
import design_pattern.strategy_pattern.constant.ReChargeTypeEnum;
import design_pattern.strategy_pattern.intf.Strategy;

/**
 * 策略模式中的环境角色 context
 * @author 夸克
 * @create 2018/7/24 14:57
 */
public class Context {

    private Strategy strategy;

    public Double calRecharge(Double charge, Integer type) {
        // 利用一个工厂去生成对应的策略
        strategy = StrategyFactory.getInstance().creator(ReChargeTypeEnum.from(type));
        if (strategy == null) {
            throw new RuntimeException("策略生成错误");
        }
        return strategy.calRecharge(charge);
    }
}
