package design_pattern.strategy_pattern.intf;

import design_pattern.strategy_pattern.constant.ReChargeTypeEnum;

/**
 * 抽象策略角色Strategy接口
 * @author 夸克
 * @create 2018/7/23 17:56
 */
public interface Strategy {
    // 定义计算recharge的方法
    Double calRecharge(Double charge);
}
