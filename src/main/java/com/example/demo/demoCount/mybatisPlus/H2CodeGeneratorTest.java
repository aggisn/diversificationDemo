package com.example.demo.demoCount.mybatisPlus;


import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.PackageConfig.Builder;
import com.baomidou.mybatisplus.generator.config.converts.MySqlTypeConvert;
import com.baomidou.mybatisplus.generator.config.querys.MySqlQuery;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import com.baomidou.mybatisplus.generator.fill.Column;
import com.baomidou.mybatisplus.generator.fill.Property;
import com.baomidou.mybatisplus.generator.keywords.MySqlKeyWordsHandler;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;


/**
 * @author ZLJ
 * @description
 * @date 2022/1/17
 */

public class H2CodeGeneratorTest {
    // 设置自定义路径

    private final static String CONTROLLER_PATH = "E:\\workspace\\diversificationDemo\\src\\main";
    private final static String outputDir = CONTROLLER_PATH + "\\java";
    private final static String entity = CONTROLLER_PATH + "\\java\\com\\example\\demo\\entity";
    private final static String mapper = CONTROLLER_PATH + "\\java\\com\\example\\demo\\mapper";
    private final static String mapperXml = CONTROLLER_PATH + "\\resources\\mapper";
    private final static String service = CONTROLLER_PATH + "\\java\\com\\example\\demo\\service";
    private final static String serviceImpl = CONTROLLER_PATH + "\\java\\com\\example\\demo\\service\\impl";
    private final static String controller = CONTROLLER_PATH + "\\java\\com\\example\\demo\\controller";

//    private final static String CONTROLLER_PATH = "D:\\SDZGYJGL\\cuiheng\\safety-supervision-backend\\ruoyi-admin\\src\\main";
//    private final static String OTHERS_PATH = "D:\\SDZGYJGL\\cuiheng\\safety-supervision-backend\\ruoyi-system\\src\\main";
//    private final static String OTHERS_PATH1 = "D:\\SDZGYJGL\\cuiheng\\safety-supervision-backend\\ruoyi-common\\src\\main";
//    private final static String outputDir = CONTROLLER_PATH + "\\java";
//    private final static String entity = OTHERS_PATH1 + "\\java\\com\\ruoyi\\common\\core\\domain\\entity";
//    private final static String mapper = OTHERS_PATH + "\\java\\com\\ruoyi\\system\\mapper";
//    private final static String mapperXml = OTHERS_PATH + "\\\\resources\\\\mapper";
//    private final static String service = OTHERS_PATH + "\\java\\com\\ruoyi\\system\\service";
//    private final static String serviceImpl = OTHERS_PATH + "\\java\\com\\ruoyi\\system\\service\\impl";
//    private final static String controller = CONTROLLER_PATH + "\\java\\com\\ruoyi\\web\\controller\\business";
//

/**
 * 执行初始化数据库脚本
 *//*

     */
/*  @BeforeAll
    public static void before() throws SQLException {
        Connection conn = DATA_SOURCE_CONFIG.getConn();
        InputStream inputStream = H2CodeGeneratorTest.class.getResourceAsStream("/sql/init.sql");
        ScriptRunner scriptRunner = new ScriptRunner(conn);
        scriptRunner.setAutoCommit(true);
        scriptRunner.runScript(new InputStreamReader(inputStream));
        conn.close();
    }*/


    /**
     * 数据源配置
     */

    private static final DataSourceConfig DATA_SOURCE_CONFIG = new DataSourceConfig
            .Builder("jdbc:mysql://172.0.0.1:3306/safe_new?" +
            "useSSL=false&useUnicode=true&characterEncoding=utf-8&zeroDateTimeBehavior=convertToNull&autoReconnect=true&serverTimezone=GMT%2B8",
            "root", "123")
            .dbQuery(new MySqlQuery())
            .schema("mybatis-plus")
            .typeConvert(new MySqlTypeConvert())
            .keyWordsHandler(new MySqlKeyWordsHandler())
            .build();


    /**
     * 策略配置
     *
     * @return
     */

    private StrategyConfig.Builder strategyConfig() {
        return new StrategyConfig.Builder();
    }


    /**
     * 全局配置
     */

    private GlobalConfig.Builder globalConfig() {
        return new GlobalConfig.Builder().fileOverride()
                .fileOverride()
              //  .disableOpenDir()
                .outputDir(outputDir)
                .author("zlj")
                .dateType(DateType.TIME_PACK)
                .commentDate("yyyy-MM-dd");
    }


    /**
     * 包配置 com.example.demo
     */

    private Builder packageConfig() {
        return new Builder()
                .parent("com.example.demo");
        //   .parent("com.speedchina.hd");
    }


    /**
     * 模板配置
     *
     * @return
     */

    private TemplateConfig templateConfig() {
        return new TemplateConfig.Builder()
                //.disable(TemplateType.ENTITY)
                .entity("/templates/entity.java")
                .service("/templates/service.java")
                .serviceImpl("/templates/serviceImpl.java")
                .mapper("/templates/mapper.java")
                .mapperXml("/templates/mapper.xml")
                .controller("/templates/controller.java")
                .build();
    }


    /**
     * 注入配置
     */

    private InjectionConfig.Builder injectionConfig() {
        // 测试自定义输出文件之前注入操作，该操作再执行生成代码前 debug 查看
        return new InjectionConfig.Builder().beforeOutputFile((tableInfo, objectMap) -> {
            System.out.println("tableInfo: " + tableInfo.getEntityName() + " objectMap: " + objectMap.size());
        });
    }


    /**
     * 自定义模板生成的文件路径
     *
     * @see OutputFile
     */

    @Test
    public void testCustomTemplatePath() {
        Map<OutputFile, String> pathInfo = new HashMap<>();
        pathInfo.put(OutputFile.mapperXml, mapperXml);
        pathInfo.put(OutputFile.mapper, mapper);
        pathInfo.put(OutputFile.entity, entity);
        pathInfo.put(OutputFile.controller, controller);
        pathInfo.put(OutputFile.service, service);
        pathInfo.put(OutputFile.serviceImpl, serviceImpl);

        AutoGenerator generator = new AutoGenerator(DATA_SOURCE_CONFIG);
        //去掉表前缀
        generator.strategy(strategyConfig()
//                         .addInclude("security_monitoring_equipment_information").addTablePrefix("t_", "c_","t_risks_")
                       .addInclude("emergency_exercise_plan").addTablePrefix("t_", "c_","t_risks_")
                .entityBuilder()
                .enableLombok()
                .enableTableFieldAnnotation()
                .addTableFills(new Column("created", FieldFill.INSERT))
                .addTableFills(new Property("updated", FieldFill.INSERT_UPDATE))
                .addIgnoreColumns("deleted")
                .formatFileName("%sEntity")
                .build()
                .serviceBuilder()
                .formatServiceFileName("%sService")
                .formatServiceImplFileName("%sServiceImpl")
                .build()
                .controllerBuilder()
                .formatFileName("%sController")
                .build()
                .mapperBuilder()
                .enableMapperAnnotation()
                .formatMapperFileName("%sMapper")
                .formatXmlFileName("%sXml")
                .build()
        );


        generator.template(templateConfig());
        generator.packageInfo(packageConfig().pathInfo(pathInfo).build());
        generator.global(globalConfig().build());
        generator.execute();
    }


}

