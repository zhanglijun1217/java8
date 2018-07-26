## Java8相关特性学习项目 (持续更新)

#### 函数式接口学习 functioninterface package

- 函数式接口：函数式接口有三个方法。                                 
 1. 唯一的抽象方法。2.default定义的普通方法。3.使用static定义的静态方法。
- 函数式接口都有一个注解 @FunctionInterface

#### optional容器学习 optional package 

- of方法。of方法通过工厂方法创建Optional类。需要注意的是，创建对象时传入的参数不能为null。如果传入参数为null，则抛出NullPointerException 。
- ofNullable方法，为指定的值创建一个Optional 如果指定的值为null，则返回一个空的Optional
- isPresent方法 Optional对象中存在返回true 不存在返回false
- get方法 如果Optional有值则返回，否则抛出NoSuchElementException
- ifPresent方法 如果Optional实例有值则为其调用Consumer,否则不做处理
 Consumer类包含一个抽象方法。该抽象方法对传入的值进行处理，没有返回值。
- orElse方法 如果有值将其返回，否则返回其他的指定值
- orElseGet方法 与OrElse方法相似，不同在于默认值的处理。OrElse方法将传入的字符串作为默认值 orElseGet方法则可以接受Supplier接口的实现来生成默认值 可以接受一个lambda的来作为默认值
- orElseThrow方法 如果有值则将其返回，否则抛出supplier接口创建的异常
- map方法 如果有值，则对其执行mapping函数得到返回值。如果返回值不为null，则创建包含mapping返回值的Optional作为map的返回值，否则返回空的Optional
- flatMap方法，与map方法类似。不同点在于 flatMap方法中的mapping方法返回值必须是Optional类型的 而map方法中mapping方法返回值可以是任意类型
- filter方法 如果有值并且满足断言条件返回包含该值得Optional，否则返回空Optional

#### lambda表达式应用学习 lambda package 

#### 设计模式学习 design_pattern package

- strategy_pattern 策略模式，定义一系列的算法,把每一个算法封装起来,并且使它们可相互替换。本模式使得算法可独立于使用它的客户而变化。策略模式由下边角色构成：
    1. 抽象策略角色：策略类，通常是由一个接口或者抽象类实现。
    2. 具体策略角色：包装了相关的算法和行为。
    3. context角色：持有一个策略类的引用，最终给客户端调用。