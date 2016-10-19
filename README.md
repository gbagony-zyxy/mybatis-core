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

				   作用:将查询结果按照sql列名pojo属性名一致性映射到一个大大的pojo中(包含所有需要的属性)
				   场合:常见一些明细记录的展示,比如用户购买商品明细,将关联查询信息全部展示在页面时,此时可直接使用resultType将每一条记录映射到pojo中,在前端页面遍历list(list中是pojo)即可
	2、resultMap：定义resultMap,使用resultMap作为statement的输出映射,保证列名和属性名保持一致
				 使用association和collection完成一对一和一对多高级映射,对映射有特殊需求的

				association:
					作用:将关联查询信息映射到一个pojo对象中
					场合:可以方便地将关联信息映射到一对一的关系中
						使用resultType无法将查询结果映射到pojo对象的pojo属性中,根据对结果集查询遍历的需要选择使用resultType还是resultMap

				  collection:
					作用:将关联查询信息映射到一个list集合中
动态映射:

	动态sql：
	1、对查询条件进行判断(<if/>语句)<where/>可以自动去掉条件中的第一个and
	
	
高级映射 查询缓存 Spring整合 ognl

高级映射：

	数据库级别的关系
	一对一 <association/>、
	一对多 <collection/>、
	多对多查询:组合使用<association/>与<collection/>支持延迟加载
	延迟加载：先从单表查询、需要时再去关联表去关联查询,大大提高数据库性能,cuz单表查询比关联表快
查询缓存：减轻数据压力,提升数据库性能

	一级缓存:SqlSession级别(插入删除更新关闭会清空缓存区域数据)
		在SqlSession中有一个HashMap用于存储缓存数据,不通的SqlSession之间的缓存数据HashMap互不影响
	二级缓存:Mapper级别(pojo类需要实现序列化,为了将缓存数据取出,实现反序列操作,二级缓存的存储介质不一定在内存,可能在硬盘或远程)
		多个SqlSession操作同一个Mapper的sql语句(二级缓存的范围更广),多个SqlSession可以共用二级缓存,二级缓存是跨SqlSession
		不通Mapper拥有不同的二级缓存区域(缓存区域是按照namespace划分的),即每一个namespace的Mapper有一个二级缓存区域
		对数据实时要求高的sql可以不使用缓存(useCache="false")
		刷新缓存(flushCache="true",其实就是清空缓存)

mybatis与ehcache整合

	1、分布缓存:系统并发、性能(集群部署),若不分布缓存则缓存数据在各个服务器单独存储
	2、整合方法:mybatis提供了一个cache接口

mybatis与Spring整合

	1、mapper/dao全部由Spring管理
	2、以单例方式来管理SqlSessionFactory
		配置:SqlSessionFactoryBean、MapperScannerConfiguer(扫描包下的Mapper)、MapperFactoryBean(针对单个Mapper的bean设值)

mybatis逆向工程

	可以针对单表自动生成mybatis执行所需的代码
	逆向工程(Reverse Engineering)