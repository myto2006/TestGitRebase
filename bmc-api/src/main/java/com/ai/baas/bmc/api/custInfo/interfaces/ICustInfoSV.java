package com.ai.baas.bmc.api.custInfo.interfaces;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.ai.baas.bmc.api.custInfo.params.BlCustinfoParams;
import com.ai.baas.bmc.api.custInfo.params.BlCustinfoVo;
import com.ai.baas.bmc.api.custInfo.params.CustInfoParams;
import com.ai.baas.bmc.api.custInfo.params.ExtCustParams;
import com.ai.opt.base.exception.BusinessException;
import com.ai.opt.base.exception.SystemException;
import com.ai.opt.base.vo.BaseResponse;


/**
 * 客户信息dubbo服务<br>
 * Date: 2016年3月15日 <br>
 * Copyright (c) 2016 asiainfo.com <br>
 * 
 * @author wangzhi
 */ 
@Path("/cust/service")
@Consumes({ MediaType.APPLICATION_JSON })
@Produces({ MediaType.APPLICATION_JSON, MediaType.TEXT_XML })
public interface ICustInfoSV {

	
	@interface CustNotify{}
	/**
	 * 将客户信息基础资料同步插入到计费域对应的表中
	 *
	 * @return BaaS-000000成功；其他失败
	 * @throws BusinessException
	 * @throws SystemException
	 * @author wangzhi 
	 * @ApiCode BaaS-0001
	 * @param cust java beans 对象
	 * @RestRelativeURL cust/service/sync/custNotify
	 */
	@Path("/sync/custNotify")
    @POST
	public String custNotify(CustInfoParams custInfo)throws BusinessException, SystemException;
	
	/**
	 * 查询客户表信息
	 *
	 * @return BaaS-000000成功；其他失败
	 * @throws BusinessException
	 * @throws SystemException
	 * @author wangly8 
	 * @ApiCode 
	 * @param 
	 * @RestRelativeURL cust/service/getBlCustInfos
	 */
	@Path("/getBlCustInfos")
    @POST
	public List<BlCustinfoVo> getBlCustInfos(BlCustinfoParams queryParam)throws BusinessException, SystemException;

	/**
	 * 设置
	 *
	 * @return BaaS-000000成功；其他失败
	 * @throws BusinessException
	 * @throws SystemException
	 * @author wangly8 
	 * @ApiCode 
	 * @param 
	 * @RestRelativeURL cust/service/setPolicyId
	 */
	@Path("/setPolicyId")
    @POST
	public BaseResponse setPolicyId(ExtCustParams custInfo)throws BusinessException, SystemException;
	
}
