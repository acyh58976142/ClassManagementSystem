package cn.kgc.util;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.beanutils.BeanUtils;

public class WEBUtils {

	public static <T>T param2bean(HttpServletRequest req, T t) {
		// TODO Auto-generated method stub
		
		Map map = req.getParameterMap();
		
		
		try {
			BeanUtils.populate(t, map);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return t;
	}

}
