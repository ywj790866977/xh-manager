//package com.xh.core.config.support;
//
//import com.zaxxer.hikari.HikariDataSource;
//import java.sql.Connection;
//import java.sql.SQLException;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//
//public class AutoDBHikariDataSource extends HikariDataSource {
//
//	public final static Logger logger = LoggerFactory.getLogger(HikariDataSource.class);
//
//	@Override
//	public Connection getConnection() throws SQLException {
//		String dbName = DynamicDataSource.getSiteDB();
//		Connection conn = super.getConnection();
//		conn.setCatalog(dbName);
//		logger.debug("==> select database: {}", dbName);
//
//		return conn;
//	}
//
//}
