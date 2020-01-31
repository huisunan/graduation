package com.hsn.mall.core.request.search;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.hsn.mall.core.bean.BaseSearchBean;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

/**
 * 日志查询DTO
 * @author huisunan
 * @date 2020/1/20 11:45
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class LogSearchDTO extends BaseSearchBean {
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDateTime start;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDateTime end;
}
