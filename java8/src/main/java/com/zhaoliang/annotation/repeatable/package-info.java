/**
 * 1. 只能用public或默认(default)这两个访问权修饰.例如,String value();这里把方法设为defaul默认类型.
 * <p>
 * 2. 参数成员只能用基本类型byte,short,char,int,long,float,double,boolean八种基本数据类型和
 * String,Enum,Class,annotations等数据类型,以及这一些类型的数组.例如,String value();这里的参数成员就为String;
 * <p>
 * 3. 如果只有一个参数成员,最好把参数名称设为”value”,后加小括号.
 * <p>
 * {@link java.lang.annotation.Inherited} 用来声明一个注解，其中的每一个方法实际上是声明了一个配置参数。
 * 方法的名称就是参数的名称，返回值类型就是参数的类型（返回值类型只能是基本类型、
 * Class、String、enum）。可以通过default来声明参数的默认值。
 * {@link java.lang.annotation.Inherited} 元注解是一个标记注解，@Inherited阐述了某个被标注的类型是被继承的。
 * 如果一个使用了@Inherited修饰的annotation类型被用于一个class，则这个annotation将被用于该class的子类。
 * 此类型是被标注过的class的子类所继承。类并不从它所实现的接口继承annotation，方法并不从它所重载的方法继承annotation。
 * 当@Inherited annotation类型标注的annotation的Retention是RetentionPolicy.RUNTIME，则反射API增强了这种继承性。
 * 如果我们使用java.lang.reflect去查询一个@Inherited annotation类型的annotation时，反射代码检查将展开工作：检查
 * class和其父类，直到发现指定的annotation类型被发现，或者到达类继承结构的顶层。
 * <p>
 * {@link java.lang.annotation.Documented} 用于表示自定义注解可以被javadoc之类的工具文档化，没有成员。
 */
package com.zhaoliang.annotation.repeatable;
