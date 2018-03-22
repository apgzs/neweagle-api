package com.neweagle.api.comm.web.base;

import com.baomidou.mybatisplus.plugins.PaginationInterceptor;
import com.neweagle.api.comm.exception.base.BusinessException;
import com.neweagle.api.comm.utils.BeanValidators;
import com.neweagle.api.comm.utils.Collections3;
import com.neweagle.api.comm.utils.StringHelper;
import com.neweagle.api.comm.web.json.JsonResult;
import com.neweagle.api.comm.web.json.JsonResultType;
import com.neweagle.api.comm.web.json.ReturnCode;
import org.apache.commons.lang3.StringEscapeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.validation.ConstraintViolationException;
import java.beans.PropertyEditorSupport;
import java.util.List;

/**
 * Created by tjp on 2017/6/30.
 */
public class SuperController {
    @Autowired
    private PaginationInterceptor paginationInterceptor;
    /**
     * 初始化数据绑定
     * 1. 将所有传递进来的String进行HTML编码，防止XSS攻击
     * 2. 将字段中Date类型转换为String类型
     *
     * @param binder the binder
     */
    @InitBinder
    public void initBinder(WebDataBinder binder) {
        // String类型转换，将所有传递进来的String进行HTML编码，防止XSS攻击
        binder.registerCustomEditor(String.class, new PropertyEditorSupport() {
            @Override
            public void setAsText(String text) {
                setValue(text == null ? null : StringEscapeUtils.unescapeHtml4(text));
            }
            @Override
            public String getAsText() {
                Object value = getValue();
                return value != null ? value.toString() : "";
            }
        });
        // String类型转换，将所有传递进来的String进行HTML编码，防止XSS攻击
       // binder.registerCustomEditor(String.class, new StringEditor());
        // Date 类型转换
       // binder.registerCustomEditor(Date.class, new DateEditor());
    }

    /**
     * Handle business exception map.
     *
     * @param ex the ex
     * @return the map
     */
    @ExceptionHandler(BusinessException.class)
    @ResponseStatus(HttpStatus.OK)
    public JsonResult handleBusinessException(BusinessException ex) {
        return makeErrorMessage("1001", "Business Error", ex.getMessage());
    }

    /**
     * Handle constraint violation exception map.
     *
     * @param ex the ex
     * @return the map
     */
    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public JsonResult handleConstraintViolationException(ConstraintViolationException ex) {
        List<String> list = BeanValidators.extractMessage(ex);
        return makeErrorMessage(ReturnCode.INVALID_FIELD,
                "Invalid Field", Collections3.convertToString(list, ";"));
    }

    /**
     * Make error message map.
     *
     * @param code  the code
     * @param error the error
     * @param desc  the desc
     * @return the map
     */
    protected JsonResult makeErrorMessage(String code, String error, String desc) {
        if (StringHelper.isEmpty(desc)){
            desc = "Please check the interface or parameters";
        }
        if (StringHelper.isNotEmpty(error)){
            if (StringHelper.isNotEmpty(code)){
                if (code.equals("1001")){
                    return JsonResult.error(error+":"+desc);
                }
                if (code.equals("1002")){
                    return JsonResult.error(JsonResultType.FailVip.code,error+":"+desc);
                }
                if (code.equals("1003")){
                    return JsonResult.error(JsonResultType.FailCertification.code,error+":"+desc);
                }
            }
            return JsonResult.error(error+":"+desc);
        }
        if (StringHelper.isNotEmpty(code)){
            if (code.equals("1001")){
                return JsonResult.error(desc);
            }
            if (code.equals("1002")){
                return JsonResult.error(JsonResultType.FailVip.code,desc);
            }
            if (code.equals("1003")){
                return JsonResult.error(JsonResultType.FailCertification.code,desc);
            }
        }
        return JsonResult.error(desc);
    }


}
