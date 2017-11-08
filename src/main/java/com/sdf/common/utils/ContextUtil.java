package com.sdf.common.utils;

import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


/**
 * Context帮助类
 *
 * @author Jonny
 * @version 1.0.0
 * @date 2015-7-17
 */
public class ContextUtil {


    public static String getIpAddrByRequest(HttpServletRequest request) {

        String ip = request.getHeader("X-Real-IP");// Cdn-Src-Ip

        if (!StringUtil.isNotEmpty(ip) || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }

        if (!StringUtil.isNotEmpty(ip) || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("X-Forwarded-For");
        }

        if (!StringUtil.isNotEmpty(ip) || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }

        if (!StringUtil.isNotEmpty(ip) || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }

        if (!StringUtil.isNotEmpty(ip) || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_CLIENT_IP");
        }

        if (!StringUtil.isNotEmpty(ip) || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");
        }

        return ip;
    }

    /**
     * 本机IP
     *
     * @return
     * @throws SocketException
     */
    public static String getIpAddr() throws SocketException {
        String localip = null;// 本地IP，如果没有配置外网IP则返回它
        String netip = null;// 外网IP
        Enumeration<NetworkInterface> netInterfaces = NetworkInterface
                .getNetworkInterfaces();
        InetAddress ip = null;
        boolean finded = false;// 是否找到外网IP
        while (netInterfaces.hasMoreElements() && !finded) {
            NetworkInterface ni = netInterfaces.nextElement();
            Enumeration<InetAddress> address = ni.getInetAddresses();
            while (address.hasMoreElements()) {
                ip = address.nextElement();
                if (!ip.isSiteLocalAddress() && !ip.isLoopbackAddress()
                        && ip.getHostAddress().indexOf(":") == -1) {// 外网IP
                    netip = ip.getHostAddress();
                    finded = true;
                    break;
                } else if (ip.isSiteLocalAddress() && !ip.isLoopbackAddress()
                        && ip.getHostAddress().indexOf(":") == -1
                        && ip instanceof Inet4Address) {// 内网IP
                    localip = ip.getHostAddress();
                    finded = true;
                    break;
                }
            }
        }
        if (StringUtil.isNotEmpty(netip)) {
            return netip;
        } else {
            return localip;
        }
    }

    /**
     * 通过IP地址获取MAC地址
     *
     * @return mac String
     * @throws Exception
     */
    public static String getMACAddress() throws Exception {
        String macAddress = "";
        String localip = null;// 本地IP，如果没有配置外网IP则返回它
        String netip = null;// 外网IP
        InetAddress inetAddress = null;
        Enumeration<NetworkInterface> netInterfaces = NetworkInterface
                .getNetworkInterfaces();
        InetAddress ip = null;
        boolean finded = false;// 是否找到外网IP
        while (netInterfaces.hasMoreElements() && !finded) {
            NetworkInterface ni = netInterfaces.nextElement();
            Enumeration<InetAddress> address = ni.getInetAddresses();
            while (address.hasMoreElements()) {
                ip = address.nextElement();
                inetAddress = ip;
                if (!ip.isSiteLocalAddress() && !ip.isLoopbackAddress()
                        && ip.getHostAddress().indexOf(":") == -1) {// 外网IP
                    netip = ip.getHostAddress();
                    finded = true;
                    break;
                } else if (ip.isSiteLocalAddress() && !ip.isLoopbackAddress()
                        && ip.getHostAddress().indexOf(":") == -1
                        && ip instanceof Inet4Address) {// 内网IP
                    localip = ip.getHostAddress();
                    finded = true;
                    break;
                }
            }
        }
        byte[] mac = NetworkInterface.getByInetAddress(inetAddress)
                .getHardwareAddress();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < mac.length; i++) {
            if (i != 0) {
                sb.append("-");
            }
            // mac[i] & 0xFF 是为了把byte转化为正整数
            String s = Integer.toHexString(mac[i] & 0xFF);
            sb.append(s.length() == 1 ? 0 + s : s);
        }
        // 把字符串所有小写字母改为大写成为正规的mac地址并返回
        macAddress = sb.toString().trim().toUpperCase();
        return macAddress;
    }

    public static void main(String[] args) throws Exception {
        System.out.println("------------------------ getIpAddr() = "
                + getIpAddr());
        System.out.println("------------------------ getMACAddress() = "
                + getMACAddress());
    }
}
