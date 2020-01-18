package com.example.demo.config.Sercurty;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.session.FindByIndexNameSessionRepository;
import org.springframework.session.Session;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

/**
 * 当前系统登录用户实体
 */
@Component
public class CurrentUser
{
	private static final Logger logger = LoggerFactory.getLogger(CurrentUser.class);
	
	private static FindByIndexNameSessionRepository<Session> staticSessionRepository;
	
	@Resource
	private FindByIndexNameSessionRepository<Session> sessionRepository;
	
	@PostConstruct
	private void init() {
		CurrentUser.staticSessionRepository = this.sessionRepository;
	}
	
	private static SecurityContext readSecurityContextFromSession(HttpSession httpSession) {
		if (httpSession == null) {
			return null;
		}

		Object contextFromSession = httpSession.getAttribute(HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY);

		if (contextFromSession == null) {
			return null;
		}

		if (!(contextFromSession instanceof SecurityContext)) {
			return null;
		}

		if (logger.isTraceEnabled()) {
			logger.trace("Obtained a valid SecurityContext from "
					+ HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY + ": '" + contextFromSession + "'");
		}
		
		return (SecurityContext) contextFromSession;
	}
	
	public static UserDetails getUserDetails() {
		HttpServletRequest request = getRequest();
		
		SecurityContext securityContext = readSecurityContextFromSession(request.getSession());
		if(securityContext == null) {
			return null;
		}
		
		Object obj = securityContext.getAuthentication().getPrincipal();
		if (obj instanceof UserDetails) {
			return (UserDetails) obj;
		}
		
		return null;
	}
	
	/**
	 * 判断当前用户是否登录
	 * @param request
	 * @return
	 */
	public static boolean isLogin()
	{
		return CurrentUser.getUserDetails() == null?false:true;
	}
		
	/**
	 * 重新加载已经登录用户的信息<br>
	 * @param userNames WishUserDetails对象，不允许为NULL.
	 */
	public static void reloadOnlineUser(UserDetails userDetails)
	{
		if(userDetails == null)
			logger.error("userDetails is not allowed null");
		
		List<UserDetails> list = new ArrayList<>(1);
		list.add(userDetails);
		
		CurrentUser.reloadOnlineUsers(list);
	}
	
	/**
	 * 重新加载已经登录用户的信息<br>
	 * @param userNames WishUserDetails对象列表，不允许为NULL.
	 */
	public static void reloadOnlineUsers(List<UserDetails> userDetails)
	{
		if(userDetails == null)
			logger.error("userDetails is not allowed null");
		
		userDetails.forEach(userDetail -> {
			Map<String,Session> mapSessions = staticSessionRepository.findByIndexNameAndIndexValue(FindByIndexNameSessionRepository.PRINCIPAL_NAME_INDEX_NAME, userDetail.getUsername());
			mapSessions.entrySet().forEach(mapSession -> {
				Session session = mapSession.getValue();
				session.setAttribute("reload","1");
				staticSessionRepository.save(session);
			});
		});
	}
	
	public static HttpServletRequest getRequest()
	{
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
		return request;
	}
}

