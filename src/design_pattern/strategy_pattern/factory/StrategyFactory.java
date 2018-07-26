package design_pattern.strategy_pattern.factory;

import design_pattern.strategy_pattern.constant.ReChargeTypeEnum;
import design_pattern.strategy_pattern.intf.Strategy;
import design_pattern.strategy_pattern.strategy.BusiAcctStrategy;
import design_pattern.strategy_pattern.strategy.CardStrategy;
import design_pattern.strategy_pattern.strategy.EBankStrategy;
import design_pattern.strategy_pattern.strategy.MobileStrategy;
import java.util.HashMap;
import java.util.Map;

/**
 * 策略工厂 负责Strategy实例的创建 根据传入的type实现创建不同的策略
 * @author 夸克
 * @create 2018/7/24 15:01
 */
public class StrategyFactory {

    private static StrategyFactory factory = new StrategyFactory();

    private static Map<ReChargeTypeEnum, Strategy> map = new HashMap<>();
    static {
        map.put(ReChargeTypeEnum.E_BANK, new EBankStrategy());
        map.put(ReChargeTypeEnum.BUSI_ACCOUNTS, new BusiAcctStrategy());
        map.put(ReChargeTypeEnum.MOBILE, new MobileStrategy());
        map.put(ReChargeTypeEnum.CARD_RECHARGE, new CardStrategy());
    }
    /**
     * getInstance方法进行初始化
     * @return
     */
    public static StrategyFactory getInstance() {
        return factory;
    }

    public Strategy creator(ReChargeTypeEnum type) {
        return map.get(type);
    }

}
