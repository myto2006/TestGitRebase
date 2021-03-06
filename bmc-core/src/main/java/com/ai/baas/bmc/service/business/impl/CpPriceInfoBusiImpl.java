package com.ai.baas.bmc.service.business.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ai.baas.bmc.api.proferentialprocuct.params.ProductQueryParam;
import com.ai.baas.bmc.api.proferentialprocuct.params.ProductQueryVO;
import com.ai.baas.bmc.api.proferentialprocuct.params.RelatedVO;
import com.ai.baas.bmc.service.business.interfaces.ICpPriceInfoBusi;
import com.ai.baas.bmc.constants.BmcConstants;
import com.ai.baas.bmc.dao.interfaces.CpPriceInfoMapper;
import com.ai.baas.bmc.dao.mapper.bo.CpPriceInfo;
import com.ai.baas.bmc.dao.mapper.bo.CpPriceInfoCriteria;
import com.ai.opt.sdk.util.CollectionUtil;
import org.springframework.transaction.annotation.Transactional;

@Component
@Transactional
public class CpPriceInfoBusiImpl implements ICpPriceInfoBusi {
	@Autowired
	private CpPriceInfoMapper cpPriceInfoMapper;

	@Override
	public Long addCpPriceInfo(CpPriceInfo info) {
		//1 是添加，0是删除
	
		cpPriceInfoMapper.insert(info);
		/*if(cpPriceInfoId>0){ //刷新缓存
			DshmUtil.getIdshmSV().initLoader("cp_price_info",JSON.toJSONString(info),1);	
		}*/
		return info.getPriceInfoId();
	}
	
	

	@Override
	public int delCpRpriceInfo(CpPriceInfo info) {
		//TODO 还需要在缓存中进行更新
		CpPriceInfoCriteria example=new CpPriceInfoCriteria();
		CpPriceInfoCriteria.Criteria criteria = example.or();
		criteria.andTenantIdEqualTo(info.getTenantId()).andPriceInfoIdEqualTo(info.getPriceInfoId());
		
		int count=cpPriceInfoMapper.updateByExampleSelective(info, example);
		/*if(count>0){
			DshmUtil.getIdshmSV().initLoader("cp_price_info",JSON.toJSONString(info),0);	
		}*/
		return count;
	}


	/**
	 * 获取资费信息表
	 */
	@Override
	public List<CpPriceInfo> getCpPriceInfo(ProductQueryVO vo) {
		
		CpPriceInfoCriteria example=new CpPriceInfoCriteria();

		CpPriceInfoCriteria.Criteria criteria = example.or();
		criteria.andTenantIdEqualTo(vo.getTenantId());
		criteria.andActiveStatusNotEqualTo(BmcConstants.ProferName.DEL);
		List<String> list=new ArrayList<String>();
		list.add(BmcConstants.ProferName.DR_MINUS);
		list.add(BmcConstants.ProferName.DR_OFFER);
		criteria.andChargeTypeIn(list);
		if(vo.getProductId()!=null){
			criteria.andPriceInfoIdEqualTo(vo.getProductId());
		}

		if(vo.getActiveDate()!=null){
			criteria.andActiveTimeGreaterThanOrEqualTo(vo.getActiveDate());
		}
		if(vo.getInvalidDate()!=null){
			criteria.andActiveTimeGreaterThanOrEqualTo(vo.getInvalidDate());
		}
		if(vo.getProductName()!=null){
			criteria.andPriceNameLike("%"+vo.getProductName()+"%");
		}
		if(vo.getProferType()!=null){
			criteria.andChargeTypeEqualTo(vo.getProferType());
		}
		if(vo.getPageNo()!=null&&vo.getPageSize()!=null){
			example.setLimitStart((vo.getPageNo()-1)*vo.getPageSize());
			example.setLimitEnd(vo.getPageSize());
		}
		example.setOrderByClause("create_time desc");
		return cpPriceInfoMapper.selectByExample(example);
	}
	@Override
	public int getCpPriceInfoCount(ProductQueryVO vo){

		CpPriceInfoCriteria example=new CpPriceInfoCriteria();
		CpPriceInfoCriteria.Criteria criteria = example.or();
		criteria.andTenantIdEqualTo(vo.getTenantId());
		criteria.andActiveStatusNotEqualTo(BmcConstants.ProferName.DEL);
		List<String> list=new ArrayList<String>();
		list.add(BmcConstants.ProferName.DR_MINUS);
		list.add(BmcConstants.ProferName.DR_OFFER);
		criteria.andChargeTypeIn(list);
		if(vo.getProductId()!=null){
			criteria.andPriceInfoIdEqualTo(vo.getProductId());
		}
	
		if(vo.getActiveDate()!=null){
			criteria.andActiveTimeGreaterThan(vo.getActiveDate());
		}
		if(vo.getInvalidDate()!=null){
			criteria.andActiveTimeGreaterThan(vo.getInvalidDate());
		}
		if(vo.getProductName()!=null){
			criteria.andPriceNameLike("%"+vo.getProductName()+"%");
		}
		if(vo.getProferType()!=null){
			criteria.andChargeTypeEqualTo(vo.getProferType());
		}
		
		return cpPriceInfoMapper.countByExample(example);
	}


	@Override
	public CpPriceInfo getCpPriceInfo(ProductQueryParam param) {
		CpPriceInfoCriteria sql=new CpPriceInfoCriteria();
		CpPriceInfoCriteria.Criteria criteria=sql.createCriteria();
		criteria.andTenantIdEqualTo(param.getTenantId());
		criteria.andPriceInfoIdEqualTo(param.getProductId());
		return cpPriceInfoMapper.selectByExample(sql).get(0);
	}



	@Override
	public CpPriceInfo getCpPriceInfo(RelatedVO vo) {
		CpPriceInfoCriteria sql=new CpPriceInfoCriteria();
		CpPriceInfoCriteria.Criteria criteria=sql.createCriteria();
		criteria.andTenantIdEqualTo(vo.getTenantId());
		criteria.andPriceInfoIdEqualTo(vo.getProductId());
		List<CpPriceInfo> list=cpPriceInfoMapper.selectByExample(sql);
		if(!CollectionUtil.isEmpty(list)){
			return list.get(0);
		}
		return null;
	}



	@Override
	public void updatePriceInfo(CpPriceInfo info) {
		CpPriceInfoCriteria sql=new CpPriceInfoCriteria();
		CpPriceInfoCriteria.Criteria criteria=sql.createCriteria();
		criteria.andTenantIdEqualTo(info.getTenantId());
		criteria.andPriceInfoIdEqualTo(info.getPriceInfoId());
	
		cpPriceInfoMapper.updateByExampleSelective(info,sql);
		
		//TODO 需要维护一个缓存
	}
	
	/**
	 * 通过priceCode修改信息
	 * @param info
	 * @author zhangzd
	 * @ApiDocMethod
	 * @ApiCode
	 */
	@Override
	public void updatePriceInfoByPriceCode(CpPriceInfo info) {
		CpPriceInfoCriteria sql=new CpPriceInfoCriteria();
		CpPriceInfoCriteria.Criteria criteria=sql.createCriteria();
		criteria.andPriceCodeEqualTo(info.getPriceCode());
		//
		this.cpPriceInfoMapper.updateByExampleSelective(info, sql);
	}


	/**
	 * 通过priceCode删除信息
	 * @param info
	 * @author zhangzd
	 * @ApiDocMethod
	 * @ApiCode
	 */
	@Override
	public void deletePriceInfoByPriceCode(CpPriceInfo info) {
		CpPriceInfoCriteria sql=new CpPriceInfoCriteria();
		CpPriceInfoCriteria.Criteria criteria=sql.createCriteria();
		criteria.andPriceCodeEqualTo(info.getPriceCode());
		//
		this.cpPriceInfoMapper.deleteByExample(sql);
	}


	/**
	 * 根据priceCode查询信息
	 * @param info
	 * @return
	 * @author zhangzd
	 * @ApiDocMethod
	 * @ApiCode
	 */
	@Override
	public CpPriceInfo getCpPriceInfoByPriceCode(CpPriceInfo info) {
		CpPriceInfoCriteria sql=new CpPriceInfoCriteria();
		CpPriceInfoCriteria.Criteria criteria=sql.createCriteria();
		criteria.andPriceCodeEqualTo(info.getPriceCode());
		
		List<CpPriceInfo> cpPriceInfoList = this.cpPriceInfoMapper.selectByExample(sql);
		CpPriceInfo cpPriceInfo = new CpPriceInfo();
		if(null != cpPriceInfoList){
			cpPriceInfo = cpPriceInfoList.get(0);
		}
		return cpPriceInfo;
	}
}

