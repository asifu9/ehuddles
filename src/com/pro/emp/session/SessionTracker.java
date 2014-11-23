package com.pro.emp.session;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import com.pro.emp.domain.EmpInfo;

/**
 * Servlet implementation class SessionTracker
 */
public class SessionTracker implements HttpSessionListener {
	private static final long serialVersionUID = 1L;
	private static Set<HttpSession> sessions = new HashSet<HttpSession>();   
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SessionTracker() {
        super();
        // TODO Auto-generated constructor stub
    }

	@Override
	public void sessionCreated(HttpSessionEvent arg0) {
		// TODO Auto-generated method stub
		//EmpInfo info=(EmpInfo) arg0.getSource();
		System.out.println(" added to session " );//+ info.getEmpName());
		sessions.add(arg0.getSession());
	}

	@Override
	public void sessionDestroyed(HttpSessionEvent arg0) {
		//EmpInfo info=(EmpInfo) arg0.getSource();
		System.out.println(" removed to session ");//) + info.getEmpName());
		sessions.remove(arg0.getSession());
		
	}
	
	public static List<HttpSession> getSessions() {
	    List<HttpSession> sessionsList = new ArrayList<HttpSession>(sessions);
	    Collections.sort(sessionsList, new SessionComparator());
	    return Collections.unmodifiableList(sessionsList);
	  }

	public static class SessionComparator implements Comparator<HttpSession> {
	    public int compare(HttpSession session1, HttpSession session2) {
	      return (int) (session1.getLastAccessedTime() - session2.getLastAccessedTime());
	    }
	  }
}
