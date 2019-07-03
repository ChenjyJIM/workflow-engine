package com.graduate.engine.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.graduate.engine.exception.BusinessException;
import com.graduate.engine.model.Login;
import com.graduate.engine.response.ResultMessageBuilder;
import org.apache.shiro.SecurityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

public abstract class AbstractController {
    private static final Logger logger = LoggerFactory.getLogger(AbstractController.class);

    protected Login getUser() {
        return (Login) SecurityUtils.getSubject().getPrincipal();
    }

    protected Long getUserId() {
        return getUser().getLoginId();
    }

    protected Long getPersonId() {
        return getUser().getPersonId();
    }
    /**
     * 请求模板
     *
     * @param executor 请求执行器
     */
    public void templateRequest(RequestExecutor executor) {
        //获取response对象
        HttpServletResponse response = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
                .getResponse();
        try {
            Object result = executor.doExecute();
            writeAjaxJSONResponse(ResultMessageBuilder.build(true, "success", result), response);
        } catch (IllegalArgumentException | BusinessException e) {
            logger.error(e.getMessage());
            writeAjaxJSONResponse(ResultMessageBuilder.build(false, 400, e.getMessage()), response);
        } catch (Exception e) {
            logger.error("请求失败", e);
            writeAjaxJSONResponse(ResultMessageBuilder.build(false, 500, "系统异常"), response);
        }
    }

    /**
     * description:send the ajax response back to the client side.
     *
     * @param responseObj
     * @param response
     */
    protected void writeAjaxJSONResponse(Object responseObj, HttpServletResponse response) {
        response.setCharacterEncoding("UTF-8");
        response.setHeader("Content-Type", "application/json");
        response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
        response.setHeader("Pragma", "no-cache");
        response.setDateHeader("Expires", 0);

        PrintWriter writer = getWriter(response);
        writeAjaxJSONResponse(responseObj, writer);
    }

    /**
     * description:send the ajax response back to the client side.
     *
     * @param responseObj
     * @param writer
     */
    protected void writeAjaxJSONResponse(Object responseObj, PrintWriter writer) {
        if (writer == null || responseObj == null) {
            return;
        }
        try {
            SerializerFeature[] serializerFeatures = {SerializerFeature.DisableCircularReferenceDetect,
                    SerializerFeature.WriteMapNullValue};
            writer.write(JSON.toJSONString(responseObj, serializerFeatures));
        } finally {
            writer.flush();
            writer.close();
        }
    }
    /**
     * @param response
     * @return
     */
    protected PrintWriter getWriter(HttpServletResponse response) {
        if (response == null) {
            return null;
        }

        PrintWriter writer = null;
        try {
            writer = response.getWriter();
        } catch (Exception e) {
            logger.error("unknow exception", e);
        }

        return writer;
    }


    @FunctionalInterface
    public static interface RequestExecutor {
        /**
         * 执行业务
         * @return
         * @throws BusinessException
         */
        Object doExecute() throws BusinessException;
    }
}
