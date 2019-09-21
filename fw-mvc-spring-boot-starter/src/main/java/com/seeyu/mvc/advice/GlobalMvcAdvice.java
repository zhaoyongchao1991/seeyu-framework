package com.seeyu.mvc.advice;

import com.seeyu.lang.utils.StringUtils;
import org.springframework.beans.TypeMismatchException;
import org.springframework.beans.propertyeditors.PropertiesEditor;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.InitBinder;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Pattern;

/**
 * @author seeyu
 * @date 2019/7/8
 */
@ControllerAdvice
public class GlobalMvcAdvice {


    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(Date.class, new PropertiesEditor(){
            private final String date_format_pattern_1 = "^\\d{4}-\\d{2}-\\d{2}$";
            private final String date_format_pattern_2 = "^\\d{4}-\\d{2}-\\d{2}\\s\\d{2}:\\d{2}:\\d{2}$";

            private final String date_format_pattern_3 = "^\\d{4}/\\d{2}/\\d{2}$";
            private final String date_format_pattern_4 = "^\\d{4}/\\d{2}/\\d{2}\\s\\d{2}:\\d{2}:\\d{2}$";

            @Override
            public void setAsText(String source) throws IllegalArgumentException {
                if(StringUtils.isEmpty(source)){
                    return;
                }
                SimpleDateFormat sdf=getDate(source);
                try {
                    setValue(sdf.parseObject(source));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }

            private SimpleDateFormat getDate(String source) {
                SimpleDateFormat sdf;
                if (Pattern.matches(date_format_pattern_1, source)) {
                    sdf=new SimpleDateFormat("yyyy-MM-dd");
                }else if (Pattern.matches(date_format_pattern_2, source)) {
                    sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                }else if (Pattern.matches(date_format_pattern_3, source)) {
                    sdf=new SimpleDateFormat("yyyy/MM/dd");
                }else if (Pattern.matches(date_format_pattern_4, source)) {
                    sdf=new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
                }else {
                    throw new TypeMismatchException(source, java.sql.Date.class);
                }
                return sdf;
            }
        });
    }



}
