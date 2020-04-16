package com.xh.admin.service.impl;

import com.xh.admin.service.HelloService;
import org.springframework.stereotype.Service;

/**
 * @author rubyle
 */
@Service
public class HelloServiceImpl implements HelloService {

    /**
     * getHello
     *
     * @return String;
     */
    @Override
    public String getHello() {
        return "Hello ";
    }
}
