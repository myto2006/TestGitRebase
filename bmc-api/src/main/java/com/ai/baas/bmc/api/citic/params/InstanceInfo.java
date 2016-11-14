package com.ai.baas.bmc.api.citic.params;

import com.ai.opt.base.vo.BaseInfo;

/**
 * 修改服务实例接口入参
 * @author gaogang
 *
 */
public class InstanceInfo extends BaseInfo{

	
	private static final long serialVersionUID = 1L;
	/**
	 * 实例id
	 */
	public String instanceId;

	public String getInstanceId() {
		return instanceId;
	}

	public void setInstanceId(String instanceId) {
		this.instanceId = instanceId;
	}
	
	
}
