package com.jun.chu.arch.support;

import org.apache.log4j.Logger;

import java.io.PrintStream;
import java.lang.reflect.Method;

/**
 * Fuiou系统日志处理公共类
 */
public class LogWriter {
	private static org.apache.log4j.Logger sLogger = null;

	static {
		init(Logger.getRootLogger());
		redirectErrLog();
	}

	/**
	 * 取得日志实例
	 *
	 * @return
	 */
	public static void init(Logger logger) {
		sLogger = logger;
		if (sLogger != null) {
			sLogger.setAdditivity(false);
		}
	}

	/**
	 * 记录调试信息
	 *
	 * @param aMsg
	 *            调试信息
	 */
	public static void debug(String aMsg) {
		sLogger.debug(aMsg);
	}

	/**
	 * 记录调试信息
	 *
	 * @param aObject
	 *            调试对象
	 * @param aMsg
	 *            调试信息
	 */
	public static void debug(Object aObject, String aMsg) {
		sLogger.debug("[" + aObject.getClass().getName() + "]:" + aMsg);
	}

	/**
	 * 记录调试信息
	 *
	 * @param aObject
	 *            调试对象
	 * @param aMsg
	 *            调试信息
	 */
	public static void debug(Object aObject, Throwable aThrowable) {
		sLogger.debug(aObject, aThrowable);
	}

	/**
	 * 记录基本信息
	 *
	 * @param aMsg
	 *            基本信息
	 */
	public static void info(String aMsg) {
		sLogger.info(aMsg);
	}

	/**
	 * 记录基本信息
	 *
	 * @param aObject
	 *            调试对象
	 * @param aMsg
	 *            基本信息
	 */
	public static void info(Object aObject, String aMsg) {
		sLogger.info("[" + aObject.getClass().getName() + "]:" + aMsg);
	}

	/**
	 * 记录警告信息
	 *
	 * @param aMsg
	 *            警告信息
	 */
	public static void warn(String aMsg) {
		sLogger.warn(aMsg);
	}

	/**
	 * 记录警告信息
	 *
	 * @param aObject
	 *            调试对象
	 * @param msg
	 *            警告信息
	 */
	public static void warn(Object aObject, String aMsg) {
		sLogger.warn("[" + aObject.getClass().getName() + "]:" + aMsg);
	}

	/**
	 * 记录错误信息
	 *
	 * @param aMsg
	 *            错误信息
	 */
	public static void error(String aMsg) {
		sLogger.error(aMsg);
	}

	/**
	 * 记录错误信息
	 *
	 * @param aObject
	 *            调试对象
	 * @param aMsg
	 *            错误信息
	 */
	public static void error(Object aObject, String aMsg) {

		sLogger.error("[" + aObject.getClass().getName() + "]:" + aMsg);
	}

	/**
	 * 记录调试信息
	 *
	 * @param aObject
	 *            调试对象
	 * @param aMsg
	 *            调试信息
	 */
	public static void error(Object aObject, Throwable aThrowable) {
		sLogger.error(aObject, aThrowable);
	}

	/**
	 * 记录失败信息
	 *
	 * @param aMsg
	 *            失败信息
	 */
	public static void fatal(String aMsg) {
		sLogger.fatal(aMsg);
	}

	/**
	 * 记录失败信息
	 *
	 * @param aObject
	 *            调试对象
	 * @param aMsg
	 *            失败信息
	 */
	public static void fatal(Object aObject, String aMsg) {
		sLogger.fatal("[" + aObject.getClass().getName() + "]:" + aMsg);
	}

	public static String objectPrint(String msg, Object bean) {
		sLogger.info(msg);
		Method[] methods = bean.getClass().getMethods();
		for (int i = 0; i < methods.length; i++) {
			try {
				String name = methods[i].getName();
				if (name.startsWith("get")) {
					if (name.endsWith("Class")) {
						continue;
					}
					Object obj = methods[i].invoke(bean);
					printData("  [" + name.substring(3, name.length()) + "]:" + obj);
				}
			} catch (Exception e) {
				e.printStackTrace();
				continue;
			}
		}
		return "";
	}

	private static void redirectErrLog() {
		PrintStream ps = new PrintStream(System.out) {
			public void println(char[] x) {
				LogWriter.info(x == null ? null : new String(x));
			}

			public void println(String x) {
				LogWriter.info(x);
			}

			@Override
			public void println(Object obj) {
				LogWriter.info(obj == null ? null : obj.toString());
			}

			@Override
			public void print(Object obj) {
				LogWriter.info(obj == null ? null : obj.toString());
			}
		};
		System.setOut(ps);
		System.setErr(ps);

	}

	private static void printData(String msg) {
		System.out.println("      " + msg);
	}

}
