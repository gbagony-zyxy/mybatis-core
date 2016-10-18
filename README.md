# mybatis-core

mybatis能够对输入输出参数进行自动输入映射和输出映射

核心：对sql语句进行灵活操作

SqlMapConfig.xml(全局配置文件,不固定,数据源、事务等运行环境)

	SQLSessionFactory->SQLSession(接口)->Executor(执行器,基本执行器和缓存执行器)->mapped statement->db

parameterType 指定参数类型

resultType 指定输出结果的类型

* #{} 表示占位符
* ${} 表示拼接符号,可能会引起sql注入

		selectOne 表示查询出一条记录
		selectList 表示查询出一个列表

自增主键返回:
非自增主键返回:

SqlSession(是线程不安全的，在它的实现类中除了有接口中的方法,还有数据域属性)最佳应用场合在方法体内，定义成局部变量

原始dao存在的问题：

	1、存在大量的重复代码,硬编码
	2、调用SqlSession方法时传入变量,由于SqlSession方法使用泛型，即使变量类型传入错误，在编译阶段也不会报错

遵循的开发规范：

	1、在mapper.xml中namespace等于mapper接口地址(全路径)
	2、mapper.java接口中的方法名和mapper.xml中statement的id一致
	3、mapper.java接口中的方法输入参数类型和mapper.xml中statement的parameterType指定的类型一致
	4、mapper.java接口中的方法返回类型与mapper.xml中resultType


代理对象内部调用selectOne或selectList:

	如果mapper方法返回单个pojo对象,代理对象内部通过selectOne查询数据库。
	如果mapper方法返回集合对象,代理对象内部通过selectList查询数据库。
	如果返回的是一个集合，而调用的是selectOne方法就会抛出异常

mapper接口方法参数只能有一个是否影响系统开发:
notice:-->


全局配置文件SqlMapConfig.xml配置内容如下:

properties(属性)：

	不要在properties元素体内添加任何属性值,只将属性定义在properties文件中

settings(全局配置参数)：会影响mybatis的运行效率

	开启二级缓存,开启延迟加载
typeAliases(类型别名): 重点
	
typeHandlers(类型处理器)：
objectFactory(对象工厂)
plugins(插件)
environments(环境集合属性对象)

	environment(环境子属性对象)
		transactionManager(事务管理)
		dataSource(数据源)
mappers(映射器):

	1、<mapper resource=""/>加载单个映射文件
	2、<mapper class=""/>通过mapper接口来加载映射文件。规范：需要将mapper接口类名和mapper.xml映射文件名保持一致，且在一个目录中
	3、<package name=""/>批量加载mapper文件。推荐使用。规范：需要将mapper接口类名和mapper.xml映射文件名保持一致，且在一个目录中

映射+接口
	
输入映射:

	parameterType指定输入类型(简单类型,hashmap,包装对象)

输出映射:

	1、resultType：只有当查询出来的列名和pojo中的属性名一致，该列才可以映射成功
				   如果查询出来的列名和pojo中的属性名全部不一致,则不会创建pojo对象
				   只要查询出来的列名和pojo中的属性有一个一致,就会创建pojo对象
				   根据返回值类型来动态选择selectOne或者selectList
	2、resultMap：定义resultMap,使用resultMap作为statement的输出映射,保证列名和属性名保持一致

动态映射:

	动态sql：
	1、对查询条件进行判断(<if/>语句)<where/>可以自动去掉条件中的第一个and
	
	
高级映射 查询缓存 Spring整合 ognl

高级映射：

	数据库级别的关系
	一对一 <association/>、
	一对多 <collection/>、
	多对多查询<>
	延迟加载
查询缓存：

	一级缓存
	二级缓存
mybatis与Spring整合

mybatis逆向工程
