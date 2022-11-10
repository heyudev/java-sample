package com.heyudev.base;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.heyudev.common.http.HttpClientUtil;
import com.heyudev.common.http.HttpPoolClient;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.util.*;

/**
 * @author heyudev
 * @date 2021/06/04
 */
public class Main {

    private static void has1(){
        String l = "00000000000000000000000000001011";
        Integer a=10;
        System.out.println(Integer.bitCount(10));
        System.out.println(Long.bitCount(10L));
        System.out.println(Integer.bitCount(11));
        System.out.println(Long.bitCount(11L));

        int count =0;
        while (a!=0) {
            System.out.println(Integer.toBinaryString(a) +"--"+Integer.toBinaryString(a-1));
            a = a&(a-1);
            System.out.println(Integer.toBinaryString(a));
            count++;
        }
        System.out.println(count);
    }

    public static void main(String[] args) {
//        System.out.println('c'-'a');
//
//        Long a = 100L;
//
//        Integer b = 10;
//        System.out.println(a>0);
//        System.out.println(a<0);
//        System.out.println(b<0);
//        System.out.println(b>0);
//        System.out.println(a>b);
//        System.out.println(a<b);

//        String str ="[{'expItemId':21,'configValue':''},{'expItemId':22,'configValue':'{'size':4, 'K': 3, 'P': 0.3}'}]";
//        String str ="{\"ab_test001\":[{\"abExpCode\":\"10003\",\"configValue\":\"{}\"},{\"abExpCode\":\"1004\",\"configValue\":\"\"}],\"ab_test003\":[{\"abExpCode\":\"1005\",\"configValue\":\"\"},{\"abExpCode\":\"22\",\"configValue\":\"{\\\"size\\\":4, \\\"K\\\": 3, \\\"P\\\": 0.3}\"}]}";
//        JSONObject jsonObject = JSONObject.parseObject(str);
//        Map<String,String> map = JSONObject.parseObject(str,Map.class);
//        Map<String,String> map1 = JSONObject.parseObject(str, new TypeReference<Map<String,String>>(){});
//        Map<String , List<AbExpItemDTO>> map2 = JSONObject.parseObject(str, new TypeReference<Map<String,List<AbExpItemDTO>>>(){});
//        System.out.println(map);
//        System.out.println(map1);
//        System.out.println(jsonObject);
//        System.out.println(map2);

//        String clientIp = "222.128.117.208";
//        System.out.println(getRegionInfo(clientIp));

//        has1();

//        String s = "0000010011000000010001000100110001011100001011000010110000111111";
//        String s = "1100111111101111101111000011100000111111000101110000010000000000";
//        int mid = s.length()/2;
//        System.out.println(mid);
//        System.out.println(s.substring(0, mid));
//        System.out.println(s.substring(mid));
//        String s = "11111111111111111111111111111111";
//        Integer integer = Integer.parseInt(s,2);
//        System.out.println(integer);
//        Long x = Long.parseLong(s,2);
//        System.out.println(x);

//        Long l = 0L;
//        Integer b =100000;
//        Long bb =100000L;
//        System.out.println(l+b);
//        System.out.println(l+bb);

//        Set<Long> set = new HashSet<>();
//        set.add(1L);
//        set.add(11L);
//        set.add(12L);
//        set.add(16L);
//        set.add(2L);
//        set.add(161L);
//        System.out.println(set.toString());
//        System.out.println(JSON.toJSONString(set));

//        List<Long> list = new ArrayList<>();
//        for (Long i=0L;i<10L;i++) {
//            list.add(i);
//        }
//        System.out.println(list);
//        list = filter(list);
//        System.out.println(list);


//        String sid = "weixin_openid_o0w175WAYG51kqbpMZJNvx3EfQRw-e3d4-1654497038582";
//
//        String mid = sid.substring(0, sid.length()-19);
//        System.out.println(mid);


//        final String donateRateFormat = "1000票约占捐献总额的%s";
//        System.out.println(String.format(donateRateFormat, 1)+"%");
//
//
//        LocalDateTime localDateTime = LocalDateTime.now().plusDays(-7);
//        int dayOfWeek = localDateTime.getDayOfWeek().getValue();
//        LocalDateTime lastMondayLocalDateTime = localDateTime.plusDays(-7 - (1 - dayOfWeek));
//
//        System.out.println(lastMondayLocalDateTime);
//
//        LocalDateTime lastSundayLocalDateTime = localDateTime.plusDays(7 - dayOfWeek);
//
//        System.out.println(lastSundayLocalDateTime);

//        Integer sumPoint = 298818;
//        Double rate = 0.0;
//        if (Objects.nonNull(sumPoint)) {
//            System.out.println("1 = "+ sumPoint);
//            if (sumPoint < 1000) {
//                rate = 100.0;
//                System.out.println("11 = "+ rate);
//            } else {
//                rate = 1000.0 / sumPoint * 100;
//                System.out.println("111 = "+ rate);
//            }
//        }
//        BigDecimal rateBig = new BigDecimal(rate);
//        Double ratePrecent = rateBig.setScale(1,BigDecimal.ROUND_HALF_UP).doubleValue();
//        if (Objects.nonNull(ratePrecent) && ratePrecent > 0.1) {
//            System.out.println("1111 = "+ ratePrecent);
//            System.out.println("11111 = "+ ratePrecent.toString());
//        } else {
//            System.out.println("111111 = "+ 0.1);
//        }

//        LocalDateTime localDateTime = LocalDateTime.now();
//        System.out.println(LocalDateTime.of(localDateTime.toLocalDate(), LocalDateTime.MIN.toLocalTime()));
//        System.out.println(LocalDateTime.of(localDateTime.toLocalDate(), LocalDateTime.MAX.toLocalTime()));
//        System.out.println(LocalDateTime.of(localDateTime.toLocalDate(), LocalDateTime.MIN.toLocalTime()).atZone(ZoneId.systemDefault()).toInstant().toEpochMilli());
//        System.out.println(LocalDateTime.of(localDateTime.toLocalDate(), LocalDateTime.MAX.toLocalTime()).atZone(ZoneId.systemDefault()).toInstant().toEpochMilli());

//        Integer type=2;
//        int type2 =2;
//
//        System.out.println(type);
//        System.out.println(type2);
//        System.out.println((byte)type2);

        String s = "123,125,21,";
        String[] ss = s.split(",");

        System.out.println(ss.length);
        System.out.println(ss[ss.length-1]);
        System.out.println(ss[ss.length]);
    }

    public static List<Long> filter(List<Long> list){
        Iterator<Long> iterator = list.iterator();
        while (iterator.hasNext()) {
            Long l = iterator.next();
            System.out.println(Objects.equals(l/2,0L));
            if (Objects.equals(l/2,0L)) {
                iterator.remove();
            }
        }
        return list;
    }

    private static RegionVO getRegionInfo(String clientIp) {
        final HttpPoolClient httpPoolClientDefault = HttpClientUtil.useDefault();
        RegionVO regionVO = new RegionVO();
        Map<String, Object> param = new HashMap<>();
        BaseInfoDTO baseInfoDTO = new BaseInfoDTO();
        baseInfoDTO.setClientIp(clientIp);
        param.put("baseInfo", baseInfoDTO);
        try {
            String getByIpUrl = "http://testapi-internal.piaoquantv.com/base-service/region/getByIp";
            Optional<String> optionalS = httpPoolClientDefault.postJson(getByIpUrl, JSON.toJSONString(param));
            System.out.println(optionalS);
            if (optionalS.isPresent()) {
                JSONObject jsonObject = JSON.parseObject(optionalS.get());
                if (Objects.nonNull(jsonObject) && Objects.equals(jsonObject.getInteger("code"),0)) {
                    regionVO = JSON.parseObject(jsonObject.getString("data"), RegionVO.class);
                }
            }
        } catch (Exception e) {
        }
        return regionVO;
    }
     static class BaseInfoDTO {
         /// 用户信息
         private String token;
         private Long loginUid;

         /// 应用信息
         private Integer appVersionCode;
         private Integer appType;

         /// 设备信息
         private String machineCode;
         private String platform;
         private String systemVersion;
         private String machineInfo;
         private String networkType;
         private String clientIp;

         // pageSource相关的参数
         private String pageSource;

         // 某次操作相关的参数
         private Long clientTimestamp;
         private String sessionId;
         private String requestId;

         public String getToken() {
             return token;
         }

         public void setToken(String token) {
             this.token = token;
         }

         public Long getLoginUid() {
             return loginUid;
         }

         public void setLoginUid(Long loginUid) {
             this.loginUid = loginUid;
         }

         public Integer getAppVersionCode() {
             return appVersionCode;
         }

         public void setAppVersionCode(Integer appVersionCode) {
             this.appVersionCode = appVersionCode;
         }

         public Integer getAppType() {
             return appType;
         }

         public void setAppType(Integer appType) {
             this.appType = appType;
         }

         public String getMachineCode() {
             return machineCode;
         }

         public void setMachineCode(String machineCode) {
             this.machineCode = machineCode;
         }

         public String getPlatform() {
             return platform;
         }

         public void setPlatform(String platform) {
             this.platform = platform;
         }

         public String getSystemVersion() {
             return systemVersion;
         }

         public void setSystemVersion(String systemVersion) {
             this.systemVersion = systemVersion;
         }

         public String getMachineInfo() {
             return machineInfo;
         }

         public void setMachineInfo(String machineInfo) {
             this.machineInfo = machineInfo;
         }

         public String getNetworkType() {
             return networkType;
         }

         public void setNetworkType(String networkType) {
             this.networkType = networkType;
         }

         public String getClientIp() {
             return clientIp;
         }

         public void setClientIp(String clientIp) {
             this.clientIp = clientIp;
         }

         public String getPageSource() {
             return pageSource;
         }

         public void setPageSource(String pageSource) {
             this.pageSource = pageSource;
         }

         public Long getClientTimestamp() {
             return clientTimestamp;
         }

         public void setClientTimestamp(Long clientTimestamp) {
             this.clientTimestamp = clientTimestamp;
         }

         public String getSessionId() {
             return sessionId;
         }

         public void setSessionId(String sessionId) {
             this.sessionId = sessionId;
         }

         public String getRequestId() {
             return requestId;
         }

         public void setRequestId(String requestId) {
             this.requestId = requestId;
         }

         @Override
         public String toString() {
             return String.format(
                     "BaseInfoDTO [token=%s, appVersionCode=%s, appType=%s, machineCode=%s, platform=%s, systemVersion=%s, machineInfo=%s, networkType=%s, clientIp=%s, pageSource=%s, clientTimestamp=%s, sessionId=%s, requestId=%s]",
                     token, appVersionCode, appType, machineCode, platform, systemVersion, machineInfo, networkType, clientIp,
                     pageSource, clientTimestamp, sessionId, requestId);
         }

     }

     static  class RegionVO {
         /**
          * 国家
          */
         private String country = "";
         /**
          * 省份
          */
         private String province = "";
         /**
          * 城市
          */
         private String city = "";
         /**
          * 区域
          */
         private String area = "";
         /**
          * 行政编码
          */
         private String adCode = "";

         public String getCountry() {
             return country;
         }

         public void setCountry(String country) {
             this.country = country;
         }

         public String getProvince() {
             return province;
         }

         public void setProvince(String province) {
             this.province = province;
         }

         public String getCity() {
             return city;
         }

         public void setCity(String city) {
             this.city = city;
         }

         public String getArea() {
             return area;
         }

         public void setArea(String area) {
             this.area = area;
         }

         public String getAdCode() {
             return adCode;
         }

         public void setAdCode(String adCode) {
             this.adCode = adCode;
         }

         @Override
         public String toString() {
             return "RegionVO{" +
                     "country='" + country + '\'' +
                     ", province='" + province + '\'' +
                     ", city='" + city + '\'' +
                     ", area='" + area + '\'' +
                     ", adCode='" + adCode + '\'' +
                     '}';
         }
     }

    static  class AbExpItemDTO {
        private String abExpCode;
        private String configValue;

        public String getAbExpCode() {
            return abExpCode;
        }

        public void setAbExpCode(String abExpCode) {
            this.abExpCode = abExpCode;
        }

        public String getConfigValue() {
            return configValue;
        }

        public void setConfigValue(String configValue) {
            this.configValue = configValue;
        }
    }
}
