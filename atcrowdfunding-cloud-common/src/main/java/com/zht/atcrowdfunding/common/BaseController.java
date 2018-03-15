/**
 * 
 */
package com.zht.atcrowdfunding.common;

import com.zht.atcrowdfunding.common.bean.AjaxResult;

/**
 * @author : ZHT
 * @date : 2018年1月26日
 */
public abstract class BaseController {

	private ThreadLocal<AjaxResult> resultLocal = new ThreadLocal<>();
	
	protected void start() {
		resultLocal.set(new AjaxResult());
	}
	
	protected Object end() {
		Object obj = resultLocal.get();
		resultLocal.remove();
		return obj;
	}
	
	protected void data(Object data) {
		AjaxResult result = resultLocal.get();
		result.setData(data);
	}
	
	protected void success() {
		success(true);
	}
	
	protected void success(boolean flg) {
		AjaxResult result = resultLocal.get();
		result.setSuccess(flg);
	}
	
	protected void fail() {
		success(false);
	}
}
