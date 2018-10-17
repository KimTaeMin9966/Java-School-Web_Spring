package net.koreate.interceptor;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import net.koreate.service.BoardService;
import net.koreate.vo.ReplyBoardVo;
import net.koreate.vo.UserVo;

public class AuthInterceptor extends HandlerInterceptorAdapter {
	
	@Inject
	BoardService service;
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		System.out.println("AuthInterceptor preHandle START");

		String requestInfo = request.getRequestURI();
		System.out.println("requestInfo : " + requestInfo);
		
		HttpSession session = request.getSession();
		Object user = session.getAttribute("userInfo");
		
		if (user == null) {
			response.sendRedirect("/user/signIn");
			return false;
		} else {
			UserVo userVo = (UserVo)user;
			int uno = userVo.getUno();
			
			String bno = request.getParameter("bno");
			System.out.println("bno : " + bno);
			
			if(bno != null && !bno.equals("")) {
				int num = Integer.parseInt(bno);
				ReplyBoardVo boardVo = service.readReply(num);
			
				if(requestInfo.equals("/board/replyRegister")) { return true; }
				
				if(uno == boardVo.getUno()) { System.out.println("사용자 정보가 일치합니다."); return true; }
				else {
					if (requestInfo.equals("/board/replyRegister")) { return true; }
					else { response.sendRedirect("/board/readPage?bno=" + num); return false; }
				}
			} else {
				if(requestInfo.equals("/board/register")) { return true; }
				else { response.sendRedirect("/board/listReply"); return false; }
			}
		}
	}
	
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
		System.out.println("AuthInterceptor postHandle START");
		
		super.postHandle(request, response, handler, modelAndView);
	}
}
