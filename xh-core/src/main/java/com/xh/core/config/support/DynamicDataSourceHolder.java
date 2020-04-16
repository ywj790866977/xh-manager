package com.xh.core.config.support;

public class DynamicDataSourceHolder {

	public static final String MASTER 	= 	"master";
	public static final String SLAVE 	= 	"slave";

	private static final ThreadLocal<String> dsh = new ThreadLocal<String>();

	public static void putDataSource(String key) {
		dsh.set(key);
	}
	public static void clearDataSource() {
		dsh.remove();
	}
	public static boolean isMaster() {
		return MASTER.equals(dsh.get());
	}
}
