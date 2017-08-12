package com.sbh.common;

public class ComLogInfoConstant {

	/**
	 * 日志类型
	 * @author chenzhuo
	 *
	 */
	public enum EVENT_TYPE{
		login(1,"登录"),
		logout(2,"登出"),
		
		login_oms(3,"登录OMS"),
		logout_oms(4,"登出OMS");
		
		Integer type;
		String eventName;
		public Integer getType() {
			return type;
		}
		
		public String getEventName(){
			return eventName;
		}
		EVENT_TYPE(Integer type,String eventName){
			this.type=type;
			this.eventName=eventName;
		}
	}
	
	public enum SUCCESS{
		YES(1),
		NO(0);
		Integer code;
		public Integer getCode(){
			return this.code;
		}
		
		SUCCESS(Integer code){
			this.code=code;
		}
	}
}
