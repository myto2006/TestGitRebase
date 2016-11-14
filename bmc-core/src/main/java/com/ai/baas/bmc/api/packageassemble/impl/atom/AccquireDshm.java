package com.ai.baas.bmc.api.packageassemble.impl.atom;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.ai.baas.bmc.util.DshmUtil;
import com.ai.baas.bmc.util.TableConstants;
import com.ai.baas.bmc.util.TableConstants.PackageInfo;
import com.ai.baas.bmc.util.TableConstants.PriceDetail;
import com.ai.baas.bmc.util.TableConstants.SubsComm;
import com.ai.opt.base.exception.BusinessException;
import com.alibaba.fastjson.JSON;

public class AccquireDshm {

    private static final Log LOG = LogFactory.getLog(AccquireDshm.class);
    public static Map<String, String> getBlSubsComm(String inputMessage) {
        
        Map<String, String> params = new TreeMap<>();
        params.put(SubsComm.PRODUCT_ID, inputMessage);               
        List<Map<String,String>> subsCommResults = DshmUtil.getClient().list(TableConstants.SUBS_COMM)
                .where(params)
                .executeQuery(DshmUtil.getCacheClient());
        LOG.info("subsCommResults.size :"+subsCommResults.size());
        LOG.info("subsCommResults: "+JSON.toJSONString(subsCommResults)); 
        if(subsCommResults.size()!=1){
            
            throw new BusinessException("【SubsComm表读取错误】");
        }
        return subsCommResults.get(0);
    }

    public static Map<String, String> getBlSubsCommExt(String inputMessage) {
        Map<String, String> params = new TreeMap<>();
        params.put(SubsComm.PRODUCT_ID, inputMessage);     
        params.put(SubsComm.EXT_NAME, "apportion_list");
        List<Map<String,String>> subsCommExtResults = DshmUtil.getClient().list(TableConstants.SUBS_COMM_EXT)
                .where(params)
                .executeQuery(DshmUtil.getCacheClient());
        LOG.info("params:"+ JSON.toJSONString(params));
        LOG.info("subsCommExtResults.size :"+subsCommExtResults.size());
        LOG.info("subsCommExtResults: "+JSON.toJSONString(subsCommExtResults)); 
        if(subsCommExtResults.size()!=1){
           
            throw new BusinessException("【SubsCommExt表读取错误】");
        }
        return subsCommExtResults.get(0);
    }

    public static Map<String, String> getBlUserinfo(String subs_id, String tenant_id) {
        Map<String, String> params = new TreeMap<>();
        params.put(SubsComm.SUBS_ID, subs_id);        
        params.put(SubsComm.TENANT_ID, tenant_id);
        List<Map<String, String>> userinfoResults = DshmUtil.getClient().list(TableConstants.USER_INFO)
                .where(params)
                .executeQuery(DshmUtil.getCacheClient());
        LOG.info("userinfoResults.size : "+userinfoResults.size());
        LOG.info("userinfoResults: "+JSON.toJSONString(userinfoResults)); 
        if(userinfoResults.size()!=1){
            
            throw new BusinessException("【bluserinfo表读取错误】");
        }
        return userinfoResults.get(0);
    }

    public static Map<String, String> getPackageinfo(String detail_code) {
        Map<String, String> params = new TreeMap<>();
        params.put(PackageInfo.DETAIL_CODE, detail_code);        
        List<Map<String, String>> packageinfoResults = DshmUtil.getClient().list(TableConstants.PACKAGE_INFO)
                .where(params)
                .executeQuery(DshmUtil.getCacheClient());
        LOG.info("packageinfoResults.size : "+packageinfoResults.size());
        LOG.info("packageinfoResults: "+JSON.toJSONString(packageinfoResults)); 
        if(packageinfoResults.size()!=1){
            
            throw new BusinessException("【packageinfo表读取错误】");
        }
        return packageinfoResults.get(0);
    }

    public static Map<String, String> getPriceDetail(String price_code) {
        Map<String, String> params = new TreeMap<>();
        params.put(PriceDetail.PRICE_CODE, price_code);        
        List<Map<String, String>> pricedetailResults = DshmUtil.getClient().list(TableConstants.PRICE_DETAIL)
                .where(params)
                .executeQuery(DshmUtil.getCacheClient());
        LOG.info("pricedetailResults.size : "+pricedetailResults.size());
        LOG.info("pricedetailResults: "+JSON.toJSONString(pricedetailResults)); 
        if(pricedetailResults.size()!=1){
            throw new BusinessException("【priceDetail表读取错误】");
        }
        return pricedetailResults.get(0);
    }


}
