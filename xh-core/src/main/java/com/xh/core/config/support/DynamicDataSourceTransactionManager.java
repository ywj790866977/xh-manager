//package com.xh.core.config.support;
//
//import org.springframework.jdbc.datasource.DataSourceTransactionManager;
//import org.springframework.transaction.TransactionDefinition;
//
//
//public class DynamicDataSourceTransactionManager extends DataSourceTransactionManager {
//
//	/**
//	 *
//	 */
//	private static final long serialVersionUID = 1L;
//
//	@Override
//	protected void doBegin(Object transaction, TransactionDefinition definition) {
//		boolean readOnly = definition.isReadOnly();
//		if (readOnly) {
//			DynamicDataSourceHolder.putDataSource(DynamicDataSourceHolder.SLAVE);
//		} else {
//			DynamicDataSourceHolder.putDataSource(DynamicDataSourceHolder.MASTER);
//		}
//		super.doBegin(transaction, definition);
//	}
//
//	@Override
//	protected void doCleanupAfterCompletion(Object transaction) {
//		super.doCleanupAfterCompletion(transaction);
//		DynamicDataSourceHolder.clearDataSource();
//	}
//}
