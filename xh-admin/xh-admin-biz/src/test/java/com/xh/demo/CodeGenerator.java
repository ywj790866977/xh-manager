package com.xh.demo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.core.exceptions.MybatisPlusException;
import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import org.apache.commons.lang3.StringUtils;

// 演示例子，执行 main 方法控制台输入模块表名回车自动生成对应项目目录中
public class CodeGenerator {
	private static final String path = "D:\\code\\xh\\xh-manager\\xh-admin\\xh-admin-biz" ;
	/**
	 * <p>
	 * 读取控制台内容
	 * </p>
	 */
	public static String scanner(String tip) {
		Scanner scanner = new Scanner(System.in);
		StringBuilder help = new StringBuilder();
		help.append("请输入" + tip + "：");
		System.out.println(help.toString());
		if (scanner.hasNext()) {
			String ipt = scanner.next();
			if (StringUtils.isNotEmpty(ipt)) {
				return ipt;
			}
		}
		throw new MybatisPlusException("请输入正确的" + tip + "！");
	}

	public static void main(String[] args) {
		// 1.代码生成器
		AutoGenerator mpg = new AutoGenerator();

		// 2.全局配置
		GlobalConfig gc = new GlobalConfig();
//		String projectPath = System.getProperty("user.dir");
		gc.setOutputDir(path+ "/src/main/java"); // 生成代码的路径，
		gc.setAuthor("rubyle");  // 作者信息
		gc.setOpen(false); // 是否打开资源管理
		gc.setServiceName("%Service"); // 去掉service头I
		gc.setIdType(IdType.ASSIGN_ID); // 主键策略
		gc.setDateType(DateType.ONLY_DATE); // 日期类型
		gc.setSwagger2(true);
		mpg.setGlobalConfig(gc);

		// 3.数据源配置
		DataSourceConfig dsc = new DataSourceConfig();
		dsc.setUrl(
				"jdbc:mysql://45.76.218.204:3306/gl?useUnicode=true&useSSL=false&characterEncoding=utf8&serverTimezone=Asia/Shanghai");
		// dsc.setSchemaName("public");
		dsc.setDriverName("com.mysql.cj.jdbc.Driver");
		dsc.setUsername("root");
		dsc.setPassword("niunan789");
		mpg.setDataSource(dsc);

		// 4.包配置
		PackageConfig pc = new PackageConfig();
		// com.gl.service
		pc.setParent("com.xh");
		pc.setModuleName("admin");
		pc.setEntity("entity");
		pc.setController("controller");
		pc.setService("service");
		pc.setMapper("mapper");
		mpg.setPackageInfo(pc);

		// 自定义配置
		InjectionConfig cfg = new InjectionConfig() {
			@Override
			public void initMap() {
				// to do nothing
			}
		};
		String templatePath = "/templates/mapper.xml.vm";

		// 自定义输出配置
		List<FileOutConfig> focList = new ArrayList<>();
		// 自定义配置会被优先输出
		focList.add(new FileOutConfig(templatePath) {
			@Override
			public String outputFile(TableInfo tableInfo) {
				// 自定义输出文件名 ， 如果你 Entity 设置了前后缀、此处注意 xml 的名称会跟着发生变化！！
				return path + "/src/main/resources/mapper/"  + tableInfo.getEntityName() + "Mapper" + StringPool.DOT_XML;
			}
		});

		 /*
        cfg.setFileCreate(new IFileCreate() {
            @Override
            public boolean isCreate(ConfigBuilder configBuilder, FileType fileType, String filePath) {
                // 判断自定义文件夹是否需要创建
                checkDir("调用默认方法创建的目录");
                return false;
            }
        });
        */
		cfg.setFileOutConfigList(focList);
		mpg.setCfg(cfg);

		// 配置模板
		TemplateConfig templateConfig = new TemplateConfig();
		// 配置自定义输出模板
		// 指定自定义模板路径，注意不要带上.ftl/.vm, 会根据使用的模板引擎自动识别
//         templateConfig.setEntity("templates/entity.java");
//		templateConfig.setController("template/controller.java");
//		templateConfig.setEntity("template/entity.java");
//		templateConfig.setService("template/service.java");
		templateConfig.setXml(null);
		mpg.setTemplate(templateConfig);

		// 5.策略配置
		StrategyConfig strategy = new StrategyConfig();
		strategy.setNaming(NamingStrategy.underline_to_camel);// 表名生成策略(下划线转驼峰命名)
		strategy.setColumnNaming(NamingStrategy.underline_to_camel); // 列名生成策略(下划线转驼峰命名)
		//strategy.setSuperEntityClass("com.baomidou.ant.common.BaseEntity"); // 自定义实体父类
		strategy.setEntityLombokModel(true); // 是否启动Lombok配置
		strategy.setRestControllerStyle(true);  // 是否启动REST风格配置
		//strategy.setSuperControllerClass("com.baomidou.ant.common.BaseController");  // 自定义controller父类
		strategy.setSuperEntityColumns("id"); // 写于父类中的公共字段
		strategy.setInclude(scanner("表名，多个英文逗号分割").split(","));
		strategy.setControllerMappingHyphenStyle(true);
		strategy.setTablePrefix(pc.getModuleName() + "_");
		mpg.setStrategy(strategy);
//		mpg.setTemplateEngine(new FreemarkerTemplateEngine());
		mpg.execute();
	}

}