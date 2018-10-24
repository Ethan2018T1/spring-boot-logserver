package com.cx.logserver.service.impl;
import com.cx.logserver.common.util.JsonUtil;
import com.cx.logserver.common.vo.LogMessage;
import com.cx.logserver.service.ElasticSearchService;
import com.cx.logserver.service.LogManageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author: cx
 * @Description:
 * @Date: Created in 16:32 2018/10/10
 */
@Service
public class LogManageServiceImpl implements LogManageService {
    @Autowired
    private ElasticSearchService elasticSearchService;

    @Override
    public boolean onMessage(String message) {
        try {
            LogMessage messagevo = JsonUtil.convertToObject(message, LogMessage.class);
            return elasticSearchService.saveToEs(messagevo);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
