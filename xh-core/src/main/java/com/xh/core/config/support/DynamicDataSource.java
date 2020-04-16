package com.xh.core.config.support;

import com.xh.core.config.DynamicDataSourceConfig;
import com.xh.core.user.UserContext;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

public class DynamicDataSource extends AbstractRoutingDataSource {
	
	private final static String SITE_CONTROL = "kk_control";

	public final static Logger logger = LoggerFactory.getLogger(DynamicDataSource.class);
	private static final ThreadLocal<Object> siteh = new ThreadLocal<Object>();
	private List<Object> slaveDataSources = new ArrayList<Object>();
	private AtomicInteger squence = new AtomicInteger(0);

	private static UserContext userContext;
	
	public DynamicDataSource (UserContext userContext) {
		DynamicDataSource.userContext = userContext;
	}

	public static UserContext getUserContext() {
		return userContext;
	}

	@Override
	public void afterPropertiesSet() {
		super.afterPropertiesSet();
	}

	@Override
	protected Object determineCurrentLookupKey() {
		Object key = "";
		if (DynamicDataSourceHolder.isMaster()) {
			key = DynamicDataSourceHolder.MASTER;
		} else {
			key = getSlaveKey();
		}
		logger.debug("==> select datasource key [{}]", key);
		return key;
	}
	
	@Override
	public Connection getConnection() throws SQLException {
		Connection conn = super.getConnection();
		try{
			String dbName = DynamicDataSource.getSiteDB();
			conn.setCatalog(dbName);
			conn.nativeSQL("select 1");
			//logger.debug("==> select database: {}", dbName);
			return conn;
		}catch (Exception e){
			e.printStackTrace();
			logger.error("==> getConnection error: {}",e);
			conn.setCatalog("ks_00000");
			return conn;
		}
	}

	public final static void setSiteDB(Object site) {
		siteh.set(site);
	}
	
	public final static Object getSite() {
		return siteh.get();
	}
	
	public final static void removeSite() {
		siteh.remove();
	}
	
	public final static String getSiteDB() {
		if(DynamicDataSourceConfig.siteVal != 0) {
		    if(DynamicDataSourceConfig.siteVal == -1) {
		    	logger.debug("==> site val [{}]", DynamicDataSourceConfig.siteVal);
		    	return SITE_CONTROL;
		    }
		}

		Object site = siteh.get();
		//logger.debug("==> site val [{}]", site);
		if (null != site) {
			String siteStr = site.toString();

			Integer siteV = Integer.valueOf(siteStr);

			return DEF_SITE_DATABASE_PREFIX + String.format("%05d", siteV);
		}

		Integer siteId = userContext.getSiteId();
		if(null == siteId){
			return DEF_SITE_DATABASE_PREFIX + siteId;
		}
		return DEF_SITE_DATABASE_PREFIX + String.format("%05d", siteId);
	}

	public void setSlaveDataSources(List<Object> slaveDataSources) {
		this.slaveDataSources = slaveDataSources;
	}

	public Object getSlaveKey() {
		if (squence.intValue() == Integer.MAX_VALUE) {
			synchronized (squence) {
				if (squence.intValue() == Integer.MAX_VALUE) {
					squence = new AtomicInteger(0);
				}
			}
		}
		int idx = squence.getAndIncrement() % slaveDataSources.size();
		return slaveDataSources.get(idx);
	}


	private static final String DEF_SITE_DATABASE_PREFIX 	= 	"ks_";

}
