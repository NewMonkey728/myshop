package com.qf.myshop.tool;
/**
 * druid连接池工具类
 * @author sx
 * @version 1.0 2020年11月16日
 */

import java.io.IOException;
import java.util.Properties;
import com.mchange.v2.c3p0.ComboPooledDataSource;

public class DruidTool {
	//创建c3p0连接池
	public static ComboPooledDataSource ds=new ComboPooledDataSource();
	
//	//创建连接池工厂
//	public static DruidDataSourceFactory factory=new DruidDataSourceFactory();
//	//声明连接池
//	public static DruidDataSource ds=null;
//	static {
//		
//		try {
//			//创建配置文件对象
//			Properties p=new Properties();
//			//用配置文件对象通过流加载jdbc的配置文件
//			p.load(DruidTool.class.getClassLoader().getResourceAsStream("jdbc.properties"));
//			//通过边接池工厂获得连接池对象
//			ds=(DruidDataSource) factory.createDataSource(p);
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}
	
	
	
}
