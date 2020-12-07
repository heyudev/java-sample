package com.heyudev.common.util;

import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.Enumeration;

/**
 * 取出网卡列表中的最后一个网卡的ipv4的地址作为本机的ip地址
 * 获取Linux 机器名称,和IP mac 获取不到
 */
public class InetAddressUtil {

    private static String clientIp = "127.0.0.1";
    private static String hostName = null;

    static {
        try {
            Enumeration allNetInterfaces = NetworkInterface.getNetworkInterfaces();
            InetAddress ip;
            while (allNetInterfaces.hasMoreElements()) {
                NetworkInterface netInterface = (NetworkInterface) allNetInterfaces.nextElement();
                if (netInterface.isLoopback()) {
                    continue;
                }
                Enumeration addresses = netInterface.getInetAddresses();
                while (addresses.hasMoreElements()) {
                    ip = (InetAddress) addresses.nextElement();
                    if (ip != null && ip instanceof Inet4Address && !clientIp.equals(ip.getHostAddress())) {
                        clientIp = ip.getHostAddress();
                        hostName = ip.getHostName();
                        break;
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String getClientIP() {
        return clientIp;
    }

    public static String getHostName() {
        return hostName;
    }
}

