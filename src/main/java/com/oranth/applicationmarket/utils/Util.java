package com.oranth.applicationmarket.utils;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.net.InetAddress;
import java.net.URL;
import java.net.URLConnection;
import java.net.UnknownHostException;
import java.security.MessageDigest;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.StringTokenizer;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sun.misc.BASE64Encoder;

public class Util {
	/**
	 * 判断字符串是否为空
	 * 
	 * @param s
	 * @return
	 */
	public static boolean isEmpty(String s) {
		if (s == null) {
			return true;
		}
		return s.length() == 0;
	}

	/**
	 * 判断list 是否为空
	 * 
	 * @param s
	 * @return
	 */
	public static boolean isEmpty(Collection<?> s) {
		if (s == null) {
			return true;
		}
		return s.size() == 0;
	}

	public static String ajax_decode(String str) {
		str = str.replace("{@bai@}", "%");
		str = str.replace("{@dan@}", "'");
		str = str.replace("{@shuang@}", "\"");
		str = str.replace("{@kong@}", " ");
		str = str.replace("{@zuojian@}", "<");
		str = str.replace("{@youjian@}", ">");
		str = str.replace("{@and@}", "&");
		str = str.replace("{@tab@}", "\t");
		str = str.replace("{@jia@}", "+");
		return str;
	}



	/**
	 * 获取带分时间
	 */
	public static String getyyyyMMddHHmm() {
		Date d = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		return sdf.format(d);
	}

	/**
	 * //获取带毫秒时间
	 */
	public static String getyyyyMMddHHmmss() {
		Date d = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return sdf.format(d);
	}

	// 获取带毫秒时间戳
	public static String getyyyyMMddHHmmssSSS() {
		Date d = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS");
		return sdf.format(d);
	}

	// 获取日期
	public static String getyyyyMMdd() {
		Date d = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		return sdf.format(d);
	}

	// 获取日期
	public static String getyyyy_MM_dd() {
		Date d = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		return sdf.format(d);
	}

	// 前一天
	public static String getq_yyyy_MM_dd() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		long l = System.currentTimeMillis() - 1000l * 60 * 60 * 24;
		return sdf.format(l);
	}

	// 前一月
	public static String getqy_yyyy_MM_dd() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		long l = System.currentTimeMillis() - (1000l * 60 * 60 * 24 * 30);
		return sdf.format(l);
	}

	// 前一周
	public static String getq_zy_yyyy_MM_dd() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		long l = System.currentTimeMillis() - 1000l * 60 * 60 * 24 * 7;
		return sdf.format(l);
	}

	// 获取10000-100000的随机数
	public static int getRandom() {
		int max = 100000;
		int min = 10000;
		Random random = new Random();
		int s = random.nextInt(max) % (max - min + 1) + min;
		return s;
	}

	// 获取ip--暂时不使用
	public static String getIps(HttpServletRequest request) {
		String ip = request.getHeader("x-forwarded-for");
		if ((ip == null) || (ip.length() == 0) || ("unknown".equalsIgnoreCase(ip))) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if ((ip == null) || (ip.length() == 0) || ("unknown".equalsIgnoreCase(ip))) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if ((ip == null) || (ip.length() == 0) || ("unknown".equalsIgnoreCase(ip))) {
			ip = request.getRemoteAddr();
		}
		if (ip.equals("127.0.0.1")) {
			// log.error("获取客户ip失败：" + ip);
			ip = getUUID();
		}
		return ip;
	}

	// 获取ip
	public static String getIp(HttpServletRequest request) {
		String ip = request.getHeader("X-Pounded-For");
		if (ip != null) {
			return ip;
		}

		ip = request.getHeader("x-forwarded-for");

		if (ip == null) {
			ip = request.getRemoteAddr();
			if (ip.equals("127.0.0.1") || ip.equals("0:0:0:0:0:0:0:1")) {
				InetAddress address;
				try {
					address = InetAddress.getLocalHost();
					ip = address.getHostAddress();
				} catch (UnknownHostException e) {
					e.printStackTrace();
				}

			}
			return ip;
		} else {
			StringTokenizer tokenizer = new StringTokenizer(ip, ",");
			for (int i = 0; i < tokenizer.countTokens() - 1; i++) {
				tokenizer.nextElement();
			}

			ip = tokenizer.nextToken().trim();

			if (ip.equals("")) {
				ip = null;
			}
		}

		if (ip == null) {
			ip = "0.0.0.0";
		}

		return ip;
	}

	// 获取uuid
	public static String getUUID() {
		String uuid = UUID.randomUUID().toString().trim().replaceAll("-", "");
		return uuid;
	}

	@SuppressWarnings("restriction")
	public static String MD5(String str) {
		String newstr = "";
		try {
			// 确定计算方法
			MessageDigest md5 = MessageDigest.getInstance("MD5");
			BASE64Encoder base64en = new BASE64Encoder();
			// 加密后的字符串
			newstr = base64en.encode(md5.digest(str.getBytes("utf-8")));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return newstr;
	}

	
	
	public static List<String> sendGet(String url, String param) {
		ArrayList<String> result = new ArrayList<>();
		BufferedReader in = null;
		try {
			String urlNameString = url;
			if (param.length() > 0) {
				urlNameString = urlNameString + "?" + param;
			}
			// System.out.println(urlNameString);
			URL realUrl = new URL(urlNameString);
			// 打开和URL之间的连接
			URLConnection connection = realUrl.openConnection();
			// 设置通用的请求属性
			connection.setRequestProperty("accept", "*/*");
			connection.setRequestProperty("connection", "Keep-Alive");
			connection.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
			// 建立实际的连接
			connection.connect();
			// 获取所有响应头字段
			Map<String, List<String>> map = connection.getHeaderFields();
			// 遍历所有的响应头字段
			for (String key : map.keySet()) {
				System.out.println(key + "--->" + map.get(key));
			}
			// 定义 BufferedReader输入流来读取URL的响应
			in = new BufferedReader(new InputStreamReader(connection.getInputStream(), "utf-8"));
			String line;
			while ((line = in.readLine()) != null) {
				result.add(line);
			}
		} catch (Exception e) {
			System.out.println("发送GET请求出现异常！" + e);
			e.printStackTrace();
		}
		// 使用finally块来关闭输入流
		finally {
			try {
				if (in != null) {
					in.close();
				}
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return result;
	}

	// 百度插入文件后返回json的处理
	/*
	 * public static String updatebaiduresult(HttpServletRequest request,String
	 * result){ Gson gson = new Gson(); Map<String, String> map=
	 * gson.fromJson(result, Map.class);
	 * if(map!=null&&map.get("state")!=null&&"SUCCESS".equals(map.get("state")))
	 * { String url=map.get("url"); url=url.replaceAll("/upload", "");
	 * StringBuffer requesturl = request.getRequestURL(); String tempContextUrl
	 * = requesturl.delete(requesturl.length() -
	 * request.getRequestURI().length(),
	 * requesturl.length()).append(request.getContextPath()).toString();
	 * url=tempContextUrl+"/file"+url; System.out.println(url); map.put("url",
	 * url); result=gson.toJson(map); } return result; }
	 */
	/**
	 * 转移html内容
	 * 
	 * @param html
	 * @return
	 */
	public static String replacezhuanyi(String html) {
		html = html.replaceAll("<script>", "&lt;script&gt;");
		html = html.replaceAll("</script>", "&lt;/script&gt;");
		return html;
	}

	/**
	 * ipIsValid("192.167.1.1-192.168.5.10", "192.168.3.54") 返回true和false
	 */
	public static boolean ipIsValid(String ipSection, String ip) {
		if (ipSection == null)
			throw new NullPointerException("IP段不能为空！");
		if (ip == null)
			throw new NullPointerException("IP不能为空！");
		ipSection = ipSection.trim();
		ip = ip.trim();
		final String REGX_IP = "((25[0-5]|2[0-4]\\d|1\\d{2}|[1-9]\\d|\\d)\\.){3}(25[0-5]|2[0-4]\\d|1\\d{2}|[1-9]\\d|\\d)";
		final String REGX_IPB = REGX_IP + "\\-" + REGX_IP;
		if (!ipSection.matches(REGX_IPB) || !ip.matches(REGX_IP))
			return false;
		int idx = ipSection.indexOf('-');
		String[] sips = ipSection.substring(0, idx).split("\\.");
		String[] sipe = ipSection.substring(idx + 1).split("\\.");
		String[] sipt = ip.split("\\.");
		long ips = 0L, ipe = 0L, ipt = 0L;
		for (int i = 0; i < 4; ++i) {
			ips = ips << 8 | Integer.parseInt(sips[i]);
			ipe = ipe << 8 | Integer.parseInt(sipe[i]);
			ipt = ipt << 8 | Integer.parseInt(sipt[i]);
		}
		if (ips > ipe) {
			long t = ips;
			ips = ipe;
			ipe = t;
		}
		return ips <= ipt && ipt <= ipe;
	}
	/*
	 * public static void main(String[] args) { long currentTimeMillis =
	 * System.currentTimeMillis(); for (int i = 0; i <10000; i++) { boolean
	 * ipisstop = ipisstop("10.10.1.1", "192.169.5.10", "192.169.1.0"); //
	 * System.out.println(ipisstop); }
	 * System.out.println(System.currentTimeMillis()-currentTimeMillis); }
	 */

	public static boolean ipisstop(String startip, String endip, String targetip) {
		String[] startsplit = startip.split("\\.");
		String[] endsplit = endip.split("\\.");
		String[] targetsplit = targetip.split("\\.");
		boolean flag = false;
		if (startsplit.length == 4 && endsplit.length == 4 && targetsplit.length == 4) {
			for (int i = Integer.parseInt(startsplit[0]); i <= Integer.parseInt(endsplit[0]); i++) {
				if (i == Integer.parseInt(targetsplit[0])) {
					flag = true;
					break;
				}
			}
			if (!flag) {
				// System.out.println("1");
				return false;
			}
			flag = false;
			for (int i = Integer.parseInt(startsplit[1]); i <= Integer.parseInt(endsplit[1]); i++) {
				if (i == Integer.parseInt(targetsplit[1])) {
					flag = true;
					break;
				}
			}
			if (!flag) {
				// System.out.println("2");
				return false;
			}
			flag = false;
			for (int i = Integer.parseInt(startsplit[2]); i <= Integer.parseInt(endsplit[2]); i++) {
				if (i == Integer.parseInt(targetsplit[2])) {
					flag = true;
					break;
				}
			}
			if (!flag) {
				// System.out.println("3");
				return false;
			}
			flag = false;
			for (int i = Integer.parseInt(startsplit[3]); i <= Integer.parseInt(endsplit[3]); i++) {
				if (i == Integer.parseInt(targetsplit[3])) {
					flag = true;
					break;
				}
			}
			if (!flag) {
				// System.out.println("4");
				return false;
			}
		} else {
			System.out.println("传入ip错误");
			return false;
		}
		return true;
	}

	public static String equalsql(String string) {
		string = string.replace(" ", "");
		string = string.replace("（", "");
		string = string.replace("，", "");
		string = string.replace("）", "");
		string = string.replace(":", "");
		string = string.replace("的", "");
		string = string.replace("和", "");
		StringBuffer sb = new StringBuffer();
		// name like '%%' or name like '%%'
		char[] charArray = string.toCharArray();
		for (int i = 0; i < charArray.length; i++) {
			if (i == 0) {
				sb.append("t1.name like '%" + charArray[i] + "%'");
			} else {
				sb.append(" or t1.name like '%" + charArray[i] + "%'");
			}
		}
		return sb.toString();
	}
	public static String getFileFormatSuffix(String fileName){
		String fileFormatSuffix = fileName.substring(fileName.lastIndexOf("."));
		return fileFormatSuffix;
	}

	static Logger logger = LoggerFactory.getLogger("Util.isPojonull(Object T)");
	/**
	 * 判断实例类参数是否为空
	 * @param T
	 * @return
	 */
	public static boolean isPojonull(Object T){
		Class cla = T.getClass();
		try {
			Field[] fields = cla.getDeclaredFields();
			Method[] methods = cla.getDeclaredMethods();
			String methodName = "";
			for (Field field : fields) {
				field.setAccessible(true);
				for(Method getMethod : methods){
					methodName = getMethod.getName();
					if(methodName.equalsIgnoreCase("get"+field.getName())){
						if(getMethod.invoke(T) == null || getMethod.invoke(T) == ""){
							logger.error(methodName);
							return true;
						}
					}
				}
			}
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	/**
	 * 	enum TypeName
	 * video,
	 *     other,
	 *     game,
	 *     edu,
	 *     tools
	 * @param s
	 * @return
	 */

	public static int getTypeId(String s){
//
		try{
			TypeName typeName = TypeName.valueOf(TypeName.class,s);
			switch (typeName){
				case video :
					return 1;
				case other :
					return 2;
				case game :
					return 3;
				case tools :
					return 4;
				default:
					return 0;
			}
		}catch (Exception e){
			try {
				return Integer.valueOf(s);
			}catch (Exception e1){
				return 0;
			}
		}
	}
	public static String getType(int s){
		switch (s){
			case 1 :
				return TypeName.video.name();
			case 2 :
				return TypeName.other.name();
			case 3 :
				return TypeName.game.name();
			case 4 :
				return TypeName.tools.name();
			default:
				return "";
		}
	}
}
