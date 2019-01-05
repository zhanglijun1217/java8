## Java8相关特性学习项目 (持续更新)

#### 函数式接口学习 functioninterface package

- 函数式接口：函数式接口有三个方法。                                 
 1. 唯一的抽象方法。2.default定义的普通方法。3.使用static定义的静态方法。
- 函数式接口都有一个注解 @FunctionInterface
- 函数式接口：有且仅有一个抽象方法，Object的public方法除外。

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

#### Stream API应用学习 lambda package 

- java8中加入了新的stream api。这里的stream和IO流不一样，它更像具有Iterable的集合类，但行为和集合类又有所不同。
最新添加的Stream API把真正的函数式编程风格引入到Java中，极大的提供Java程序员的生产力，让程序员写出高效率、干净、
简洁的代码。
- 关于stream api的几个学习博客
1. [Stream api中方法介绍](https://blog.csdn.net/sun_promise/article/details/51480257)
2. [ibm中关于stream的介绍](https://www.ibm.com/developerworks/cn/java/j-lo-java8streamapi/)
3. [个人写的一个list转map的总结](https://zhanglijun1217.github.io/blog/2018/08/12/Java8%E4%B8%ADlist%E8%BD%ACmap%E6%96%B9%E6%B3%95%E6%80%BB%E7%BB%93/#more)

#### 接口中默认方法学习
- java8之后加入了接口中的默认方法，即可以在接口中实现default修饰的方法。这里去学习一下。
1. 实现类可以直接使用父接口中定义的default方法。
2. 接口可以重写父接口中定义的default方法。
3. 实现类可以重写父接口中定义的方法。
4. 当父类和父接口都存在default方法时，使用父类中重写的default方法。
特别的，如果一个类实现了两个接口，这两个接口中有同名的default方法签名时，此时会编译不通过，必须在子类中重写这个default方法
- 一次工作中遇到的接口默认方法的实践
1. [java8中的接口默认方法](https://zhanglijun1217.github.io/blog/2018/10/10/java8%E5%A2%9E%E5%8A%A0%E7%9A%84%E6%8E%A5%E5%8F%A3%E4%B8%AD%E9%BB%98%E8%AE%A4%E6%96%B9%E6%B3%95/)

#### time_api的学习
1. java8中加入了关于时间的api，主要是LocalDateTime的应用，这里根据一篇博客地址去学习了相关便捷的API。
2. 博客地址：[LocalDateTime的认识](http://rensanning.iteye.com/blog/2034622)

#### 设计模式学习 design_pattern package

- strategy_pattern 策略模式，定义一系列的算法,把每一个算法封装起来,并且使它们可相互替换。本模式使得算法可独立于使用它的客户而变化。策略模式由下边角色构成：
    1. 抽象策略角色：策略类，通常是由一个接口或者抽象类实现。
    2. 具体策略角色：包装了相关的算法和行为。
    3. context角色：持有一个策略类的引用，最终给客户端调用。
