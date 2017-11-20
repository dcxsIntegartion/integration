/**
 * @className:GlobalDataBinder.java
 * @description: 日期转换工具
 * @author:yuhaiyang
 * @Created: 2015年12月11日
 * @company 成都百维
 */

package team.union.sys_sp.util;

import java.beans.PropertyEditorSupport;
import java.util.Date;

import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.support.WebBindingInitializer;
import org.springframework.web.context.request.WebRequest;

/**
 * Description: 全局类型转换
 * Author: liuzh
 * Update: liuzh(2014-05-26 13:08)
 */
public class GlobalDataBinder implements WebBindingInitializer {
    /**
     * 智能日期转换，针对四种格式日期：
     * 1.2014-05-26
     * 2.1401951570548
     * 3.2014-05-26 00:00
     * 4.2014-05-26 00:00:00
     */
    private class SmartDateEditor extends PropertyEditorSupport {
        /**
         * 根据2014-05-26 00:00:00长度来判断选择哪种转换方式
         */
        @Override
        public void setAsText(String text) throws IllegalArgumentException {
            if (text == null || text.length() == 0) {
                setValue(null);
            } else {
                try {
                    if (text.length() == 10) {
                        setValue(DateUtil.convertStrToDate(text, DateUtil.yyyyMMdd));
                    } else if (text.length() == 13) {
                        setValue(new Date(Long.parseLong(text)));
                    } else if (text.length() == 16) {
                        setValue(DateUtil.convertStrToDate(text, DateUtil.yyyyMMddHHmm));
                    } else if (text.length() == 19) {
                        setValue(DateUtil.convertStrToDate(text, DateUtil.yyyyMMddHHmmss));
                    } else {
                        throw new IllegalArgumentException("转换日期失败: 日期长度不符合要求!");
                    }
                } catch (Exception ex) {
                    throw new IllegalArgumentException("转换日期失败: " + ex.getMessage(), ex);
                }
            }
        }

        /**
         * 转换为日期字符串
         */
        @Override
        public String getAsText() {
            Date value = (Date) getValue();
            String dateStr = null;
            if (value == null) {
                return "";
            } else {
                try {
                    dateStr = DateUtil.convertDateToStr(value, DateUtil.yyyyMMddHHmmss);
                    if (dateStr.endsWith(" 00:00:00")) {
                        dateStr = dateStr.substring(0, 10);
                    } else if (dateStr.endsWith(":00")) {
                        dateStr = dateStr.substring(0, 16);
                    }
                    return dateStr;
                } catch (Exception ex) {
                    throw new IllegalArgumentException("转换日期失败: " + ex.getMessage(), ex);
                }
            }
        }
    }

    public void initBinder(WebDataBinder binder, WebRequest request) {
        //日期格式转换
        binder.registerCustomEditor(Date.class, new SmartDateEditor());
    }

}