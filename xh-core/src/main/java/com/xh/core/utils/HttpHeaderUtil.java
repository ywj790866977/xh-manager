//package com.xh.core.utils;
//
//import java.util.Enumeration;
//import java.util.HashMap;
//import java.util.Map;
//import javax.servlet.http.HttpServletRequest;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//
//public class HttpHeaderUtil {
//
//    private static Logger logger= LoggerFactory.getLogger(HttpHeaderUtil.class);
//
//
//    public static Map<String, String> getHeader(HttpServletRequest request){
//        Map<String, String> header=new HashMap<>();
//        Enumeration<String> names = request.getHeaderNames();
//        while (names.hasMoreElements()){
//            String headerName = names.nextElement();
//            Enumeration<String> headers = request.getHeaders(headerName);
//            while (headers.hasMoreElements()) {
//                String headerValue = headers.nextElement();
//                header.put(headerName,headerValue);
//            }
//
//        }
//        return header;
//    }
//
//
//}
