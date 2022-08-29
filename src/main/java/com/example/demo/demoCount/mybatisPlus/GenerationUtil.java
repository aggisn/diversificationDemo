/*
package com.example.demo.demoCount.mybatisPlus;

import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.VelocityTemplateEngine;

import java.util.ArrayList;
import java.util.List;




public class GenerationUtil {

	// 控制层路径
	private final static String CONTROLLER_PATH = "E:\\workspace\\ diversificationDemo";
	// 其他路径
	private final static String EXCEPT_CONTROLLER_PATH = "E:\\workspace\\ diversificationDemo";
	// 父级包路径
	private final static String PARENT_PACKAGE = "com.example.demo";
	// 控制类包路径
	private final static String CONTROLLER_PACKAGE = "controller";
	// 控制类包路径
	private final static String ENTITY_PACKAGE = "entity";
	// 业务类包路径
	private final static String SERVICE_PACKAGE = "service";
	// 业务实现类包路径
	private final static String SERVICEIMPL_PACKAGE = "service.impl";
	// 数据层接口包路径
	private final static String MAPPER_PACKAGE = "mapper";
	// xml路径
	private final static String MAPPERXML_PACKAGE = "";

	//request 路径
	private final static String REQUEST_PACKAGE ="domain.request";
	private static final String PARAM_PACKAGE ="domain.param" ;
	private static final String DTO_PACKAGE ="domain.DTO" ;
	private static final String VO_PACKAGE = "domain.VO";
	// 表前缀
	private final static String TABLE_PREFIX = "t_";
	// 数据库驱动
	private final static String DATASOURCE_DRIVER = "com.mysql.cj.jdbc.Driver";
	// 数据库url
	private final static String DATASOURCE_URL = "jdbc:mysql://127.0.0.1:3306/zlj?useSSL=false&useUnicode=true"
			+ "&characterEncoding=utf-8&zeroDateTimeBehavior=convertToNull&nullCatalogMeansCurrent=true&autoReconnect=true&serverTimezone=GMT%2B8&allowMultiQueries=true";
	// 数据库用户
	private final static String DATASOURCE_USERNAME = "root";
	// 数据库密码
	private final static String DATASOURCE_PASSWORD = "root";
	// 表名 多个用,分隔
	private final static String TABLENAME = "t_user";




	public static void main(String[] args) {
		gererateController();
		gererateExceptController();
		generateEntity();
		generateRequest();
		generateParam();
		generateDTO();
		generateVO();
	}

	public static void gererateController() {
		// 代码生成器
		AutoGenerator mpg = getAutoGenerator();
		// 配置模板
		TemplateConfig templateConfig = new TemplateConfig();
		// 配置自定义输出模板
		// 指定自定义模板路径，注意不要带上.ftl/.vm, 会根据使用的模板引擎自动识别
		templateConfig.setController("templates/controller.java");
		templateConfig.setService("");
		templateConfig.setEntity("");
		templateConfig.setMapper("");
		templateConfig.setXml("");
		templateConfig.setServiceImpl("");
		mpg.setTemplate(templateConfig);
		// 策略配置
		StrategyConfig strategy = new StrategyConfig();
		strategy.setNaming(NamingStrategy.underline_to_camel);
		strategy.setColumnNaming(NamingStrategy.underline_to_camel);

		strategy.setEntityLombokModel(true);
		strategy.setRestControllerStyle(true);
		// 公共父类

		// 写于父类中的公共字段
		strategy.setSuperEntityColumns("id");
		strategy.setInclude(TABLENAME);
		strategy.setControllerMappingHyphenStyle(true);
		// 去掉表前缀
		strategy.setTablePrefix(TABLE_PREFIX);
		mpg.setStrategy(strategy);
		mpg.setTemplateEngine(new VelocityTemplateEngine());
		mpg.execute();
	}

	public static void gererateExceptController() {
		// 代码生成器
		AutoGenerator mpg = getAutoGenerator();
		// 包配置
		PackageConfig pc = new PackageConfig();
		// 父路径
		pc.setParent(PARENT_PACKAGE);


		// 实体类包名
		pc.setEntity(ENTITY_PACKAGE);
		// 数据层包名
		pc.setMapper(MAPPER_PACKAGE);
		// 业务层
		pc.setService(SERVICE_PACKAGE);
		// 业务层接口
		pc.setServiceImpl(SERVICEIMPL_PACKAGE);


		mpg.setPackageInfo(pc);

		// 自定义配置
		InjectionConfig cfg = new InjectionConfig() {
			@Override
			public void initMap() {
				// to do nothing
			}
		};


		// 如果模板引擎是 velocity
		String templatePath = "/templates/mapper.xml.vm";

		// 自定义输出配置
		List<FileOutConfig> focList = new ArrayList<>();
		// 自定义配置会被优先输出
		focList.add(new FileOutConfig(templatePath) {
			@Override
			public String outputFile(TableInfo tableInfo) {
				// 自定义输出文件名 ， 如果你 Entity 设置了前后缀、此处注意 xml 的名称会跟着发生变化！！
				return CONTROLLER_PATH + "/src/main/resources/mapper/" + MAPPERXML_PACKAGE + "/"
						+ tableInfo.getEntityName() + "Mapper" + StringPool.DOT_XML;
			}
		});

		cfg.setFileOutConfigList(focList);
		mpg.setCfg(cfg);

		// 配置模板
		TemplateConfig templateConfig = new TemplateConfig();

		// 配置自定义输出模板
		// 指定自定义模板路径，注意不要带上.ftl/.vm, 会根据使用的模板引擎自动识别
		// templateConfig.setEntity("templates/entity2.java");
		// templateConfig.setService();
		templateConfig.setController("");
		templateConfig.setEntity("");
		templateConfig.setMapper("templates/mapper.java");
		templateConfig.setService("templates/service.java");
		templateConfig.setServiceImpl("templates/serviceImpl.java");
		templateConfig.setXml("templates/mapper.xml");

		mpg.setTemplate(templateConfig);

		// 策略配置
		StrategyConfig strategy = new StrategyConfig();
		strategy.setNaming(NamingStrategy.underline_to_camel);
		strategy.setColumnNaming(NamingStrategy.underline_to_camel);

//				strategy.setSuperEntityClass("你自己的父类实体,没有就不用设置!");
		strategy.setEntityLombokModel(true);
		strategy.setRestControllerStyle(true);
		// 公共父类
//				strategy.setSuperControllerClass("你自己的父类控制器,没有就不用设置!");
		// 写于父类中的公共字段
		strategy.setSuperEntityColumns("id");
		strategy.setInclude(TABLENAME);
		strategy.setControllerMappingHyphenStyle(true);
		// 去掉表前缀
		strategy.setTablePrefix(TABLE_PREFIX);
		mpg.setStrategy(strategy);
		mpg.setTemplateEngine(new VelocityTemplateEngine());
		mpg.execute();
	}

	public static void generateRequest() {
		// 代码生成器
		AutoGenerator mpg = getAutoGenerator();
		GlobalConfig config = mpg.getGlobalConfig();
		config.setEntityName(toCamelString().concat("Request"));
		mpg.setGlobalConfig(config);
		mpg.setPackageInfo(mpg.getPackageInfo().setEntity(REQUEST_PACKAGE));
		// 配置模板
		TemplateConfig templateConfig = new TemplateConfig();
		// 配置自定义输出模板
		// 指定自定义模板路径，注意不要带上.ftl/.vm, 会根据使用的模板引擎自动识别
		templateConfig.setController("");
		templateConfig.setService("");
		templateConfig.setEntity("templates/Entity.java");
		templateConfig.setMapper("");
		templateConfig.setXml("");
		templateConfig.setServiceImpl("");
		mpg.setTemplate(templateConfig);
		// 策略配置
		StrategyConfig strategy = new StrategyConfig();
		strategy.setNaming(NamingStrategy.underline_to_camel);
		strategy.setColumnNaming(NamingStrategy.underline_to_camel);
//		strategy.setSuperEntityClass("你自己的父类实体,没有就不用设置!");
		strategy.setEntityLombokModel(true);
		strategy.setRestControllerStyle(true);
		// 公共父类
//		strategy.setSuperControllerClass("你自己的父类控制器,没有就不用设置!");
		// 写于父类中的公共字段
		strategy.setSuperEntityColumns("id");
		strategy.setInclude(TABLENAME);
		strategy.setControllerMappingHyphenStyle(true);
		// 去掉表前缀
		strategy.setTablePrefix(TABLE_PREFIX);
		mpg.setStrategy(strategy);
		mpg.setTemplateEngine(new VelocityTemplateEngine());
		mpg.execute();
	}

	public static void generateVO() {
		// 代码生成器
		AutoGenerator mpg = getAutoGenerator();
		GlobalConfig config = mpg.getGlobalConfig();
		config.setEntityName(toCamelString().concat("VO"));
		mpg.setGlobalConfig(config);
		mpg.setPackageInfo(mpg.getPackageInfo().setEntity(VO_PACKAGE));
		// 配置模板
		TemplateConfig templateConfig = new TemplateConfig();
		// 配置自定义输出模板
		// 指定自定义模板路径，注意不要带上.ftl/.vm, 会根据使用的模板引擎自动识别
		templateConfig.setController("");
		templateConfig.setService("");
		templateConfig.setEntity("templates/Entity.java");
		templateConfig.setMapper("");
		templateConfig.setXml("");
		templateConfig.setServiceImpl("");
		mpg.setTemplate(templateConfig);
		// 策略配置
		StrategyConfig strategy = new StrategyConfig();
		strategy.setNaming(NamingStrategy.underline_to_camel);
		strategy.setColumnNaming(NamingStrategy.underline_to_camel);
//		strategy.setSuperEntityClass("你自己的父类实体,没有就不用设置!");
		strategy.setEntityLombokModel(true);
		strategy.setRestControllerStyle(true);
		// 公共父类
//		strategy.setSuperControllerClass("你自己的父类控制器,没有就不用设置!");
		// 写于父类中的公共字段
		strategy.setSuperEntityColumns("id");
		strategy.setInclude(TABLENAME);
		strategy.setControllerMappingHyphenStyle(true);
		// 去掉表前缀
		strategy.setTablePrefix(TABLE_PREFIX);
		mpg.setStrategy(strategy);
		mpg.setTemplateEngine(new VelocityTemplateEngine());
		mpg.execute();
	}

	public static void generateDTO() {
		// 代码生成器
		AutoGenerator mpg = getAutoGenerator();
		GlobalConfig config = mpg.getGlobalConfig();
		config.setEntityName(toCamelString().concat("DTO"));
		mpg.setGlobalConfig(config);
		mpg.setPackageInfo(mpg.getPackageInfo().setEntity(DTO_PACKAGE));
		// 配置模板
		TemplateConfig templateConfig = new TemplateConfig();
		// 配置自定义输出模板
		// 指定自定义模板路径，注意不要带上.ftl/.vm, 会根据使用的模板引擎自动识别
		templateConfig.setController("");
		templateConfig.setService("");
		templateConfig.setEntity("templates/Entity.java");
		templateConfig.setMapper("");
		templateConfig.setXml("");
		templateConfig.setServiceImpl("");
		mpg.setTemplate(templateConfig);
		// 策略配置
		StrategyConfig strategy = new StrategyConfig();
		strategy.setNaming(NamingStrategy.underline_to_camel);
		strategy.setColumnNaming(NamingStrategy.underline_to_camel);
//		strategy.setSuperEntityClass("你自己的父类实体,没有就不用设置!");
		strategy.setEntityLombokModel(true);
		strategy.setRestControllerStyle(true);
		// 公共父类
//		strategy.setSuperControllerClass("你自己的父类控制器,没有就不用设置!");
		// 写于父类中的公共字段
		strategy.setSuperEntityColumns("id");
		strategy.setInclude(TABLENAME);
		strategy.setControllerMappingHyphenStyle(true);
		// 去掉表前缀
		strategy.setTablePrefix(TABLE_PREFIX);
		mpg.setStrategy(strategy);
		mpg.setTemplateEngine(new VelocityTemplateEngine());
		mpg.execute();
	}

	public static void generateParam() {
		// 代码生成器
		AutoGenerator mpg = getAutoGenerator();
		GlobalConfig config = mpg.getGlobalConfig();
		config.setEntityName(toCamelString().concat("Param"));
		mpg.setGlobalConfig(config);
		mpg.setPackageInfo(mpg.getPackageInfo().setEntity(PARAM_PACKAGE));
		// 配置模板
		TemplateConfig templateConfig = new TemplateConfig();
		// 配置自定义输出模板
		// 指定自定义模板路径，注意不要带上.ftl/.vm, 会根据使用的模板引擎自动识别
		templateConfig.setController("");
		templateConfig.setService("");
		templateConfig.setEntity("templates/param.java");
		templateConfig.setMapper("");
		templateConfig.setXml("");
		templateConfig.setServiceImpl("");
		mpg.setTemplate(templateConfig);
		// 策略配置
		StrategyConfig strategy = new StrategyConfig();
		strategy.setNaming(NamingStrategy.underline_to_camel);
		strategy.setColumnNaming(NamingStrategy.underline_to_camel);
//				strategy.setSuperEntityClass("你自己的父类实体,没有就不用设置!");
		strategy.setEntityLombokModel(true);
		strategy.setRestControllerStyle(true);
		// 公共父类
//				strategy.setSuperControllerClass("你自己的父类控制器,没有就不用设置!");
		// 写于父类中的公共字段
		strategy.setSuperEntityColumns("id");
		strategy.setInclude(TABLENAME);
		strategy.setControllerMappingHyphenStyle(true);
		// 去掉表前缀
		strategy.setTablePrefix(TABLE_PREFIX);
		mpg.setStrategy(strategy);
		mpg.setTemplateEngine(new VelocityTemplateEngine());

		mpg.execute();
	}

	public static void generateEntity() {
		// 代码生成器
		AutoGenerator mpg = getAutoGenerator();
		GlobalConfig config = mpg.getGlobalConfig();
		config.setEntityName(toCamelString().concat("Entity"));
		mpg.setGlobalConfig(config);
		mpg.setPackageInfo(mpg.getPackageInfo().setEntity(ENTITY_PACKAGE));
		// 配置模板
		TemplateConfig templateConfig = new TemplateConfig();
		// 配置自定义输出模板
		// 指定自定义模板路径，注意不要带上.ftl/.vm, 会根据使用的模板引擎自动识别
		templateConfig.setController("");
		templateConfig.setService("");
		templateConfig.setEntity("templates/Entity.java");
		templateConfig.setMapper("");
		templateConfig.setXml("");
		templateConfig.setServiceImpl("");
		mpg.setTemplate(templateConfig);
		// 策略配置
		StrategyConfig strategy = new StrategyConfig();
		strategy.setNaming(NamingStrategy.underline_to_camel);
		strategy.setColumnNaming(NamingStrategy.underline_to_camel);
//				strategy.setSuperEntityClass("你自己的父类实体,没有就不用设置!");
		strategy.setEntityLombokModel(true);
		strategy.setRestControllerStyle(true);
		// 公共父类
//				strategy.setSuperControllerClass("你自己的父类控制器,没有就不用设置!");
		// 写于父类中的公共字段
		strategy.setSuperEntityColumns("id");
		strategy.setInclude(TABLENAME);
		strategy.setControllerMappingHyphenStyle(true);
		// 去掉表前缀
		strategy.setTablePrefix(TABLE_PREFIX);
		mpg.setStrategy(strategy);
		mpg.setTemplateEngine(new VelocityTemplateEngine());

		mpg.execute();
	}

	public static String toCamelString() {
		String camelStr = new String("");
		String[] split = TABLENAME.replaceAll(TABLE_PREFIX, "").split("_");
		for (int i = 0; i < split.length; i++) {
			String concat = split[i].substring(0, 1).toUpperCase().concat(split[i].substring(1));

			camelStr = camelStr.concat(concat);
		}
		return camelStr;
	}

	private static AutoGenerator getAutoGenerator() {
		// 代码生成器
		AutoGenerator mpg = new AutoGenerator();
		// 全局配置
		GlobalConfig gc = new GlobalConfig();
		// 输出路径
		gc.setOutputDir(CONTROLLER_PATH + "/src/main/java");
		gc.setAuthor("zlj");
		gc.setOpen(false);
		gc.setServiceName(toCamelString().concat("Service"));
		// gc.setSwagger2(true); 实体属性 Swagger2 注解
		mpg.setGlobalConfig(gc);
		// 数据源配置
		DataSourceConfig dsc = new DataSourceConfig();
		// 数据库url
		dsc.setUrl(DATASOURCE_URL);
		// dsc.setSchemaName("public");
		dsc.setDriverName(DATASOURCE_DRIVER);
		// 用户名
		dsc.setUsername(DATASOURCE_USERNAME);
		// 密码
		dsc.setPassword(DATASOURCE_PASSWORD);
		mpg.setDataSource(dsc);

		// 包配置
		PackageConfig pc = new PackageConfig();
		pc.setParent(PARENT_PACKAGE);


		// 控制类包名
		pc.setController(CONTROLLER_PACKAGE);
		// 实体类包名
		pc.setEntity(ENTITY_PACKAGE);
		// 数据层包名
		pc.setMapper(MAPPER_PACKAGE);
		// 业务层包名
		pc.setService(SERVICE_PACKAGE);
		// 业务层接口
		pc.setServiceImpl(SERVICEIMPL_PACKAGE);

		mpg.setPackageInfo(pc);
		return mpg;
	}
}
*/
