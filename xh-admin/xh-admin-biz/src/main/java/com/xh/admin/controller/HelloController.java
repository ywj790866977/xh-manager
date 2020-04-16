package com.xh.admin.controller;

import com.xh.admin.service.impl.HelloServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import java.io.IOException;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Lenovo
 */
@RestController
@RequestMapping("/test")
@Api(tags = "测试")
public class HelloController {


    /**
     * hello
     *
     * @param req  请求
     * @param resp 响应
     * @throws IOException
     */
    @ApiOperation(value = "hello",notes = "测试用例")
    @GetMapping("/v1/hello")
    public String getHello(HttpServletRequest req, HttpServletResponse resp) {
            return new String("123");

    }
}
