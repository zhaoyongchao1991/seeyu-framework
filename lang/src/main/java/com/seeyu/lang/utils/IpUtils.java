package com.seeyu.lang.utils;

import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * ip地址处理
 */
public class IpUtils {


    /**
     * 指定分隔符分割IP地址
     * 192.168.0.112:9200, 192.168.0.113:9200, 192.168.0.115:9200
     * @param addr
     * @param delim
     * @return
     */
    public static List<IpAddress> splitAddresses(String addr, String delim){
        List<IpAddress> ipAddresses = new LinkedList<IpAddress>();
        StringTokenizer st = new StringTokenizer(addr, delim);
        while (st.hasMoreTokens()){
            ipAddresses.add(splitAddress(st.nextToken()));
        }
        return ipAddresses;
    }

    /**
     * 分割ip地址, 分割成ip和端口
     * 固定格式 ip:port 127.0.0.1:80
     * @param addr
     * @return
     */
    public static IpAddress splitAddress(String addr){
        addr = addr.trim();
        String[] ss = addr.split(":");
        String ip = ss[0].trim();;
        Integer port = null;
        if(ss.length > 1 && ss[1].length() != 0){
            port = Integer.valueOf(ss[1]);
        }
        return new IpAddress(ip, port);
    }

    public static class IpAddress{
        String ip;
        Integer port;
        private IpAddress(String ip, Integer port){
            this.ip = ip;
            this.port = port;
        }

        public String getIp() {
            return ip;
        }

        public void setIp(String ip) {
            this.ip = ip;
        }

        public Integer getPort() {
            return port;
        }

        public void setPort(Integer port) {
            this.port = port;
        }

        @Override
        public String toString() {
            return ip + ":" + port;
        }
    }


}
