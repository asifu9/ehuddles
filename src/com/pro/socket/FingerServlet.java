package com.pro.socket;

import java.io.IOException;
import java.io.InputStream;
import java.io.InterruptedIOException;
import java.io.PrintStream;
import java.net.InetAddress;
import java.net.Socket;
import java.util.StringTokenizer;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class FingerServlet
 */
@WebServlet("/FingerServlet")
public class FingerServlet extends HttpServlet {
	 /*
     * Port number for finger daemon.
     */
    static final int FINGER_PORT = 79;
    private static final String timeout_msg = "Timeout reading from server";

    /**
     * Handles a single finger request from the client.
     */
    public void doGet (HttpServletRequest req, HttpServletResponse res)
	throws ServletException, IOException
    {
	String user = req.getParameter("user");
	String hosts = req.getParameter("hosts");
	String verbose = req.getParameter("verbose");

	res.setContentType("text/html");

	ServletOutputStream out = res.getOutputStream();
	out.println("<html>");
	out.println("<head><title>Finger Servlet</title></head>");
	out.println("<body>");
	out.println("<h2>Finger results:</h2>");
	out.println("<pre>");
	if (hosts == null) {
	    finger(out, user, null, "yes".equalsIgnoreCase(verbose)) ;
	} else {
	    StringTokenizer st = new StringTokenizer(hosts, ",");
	    while (st.hasMoreTokens()) {
		String host = st.nextToken();
		out.println("[" + host + "]");
		try {
		    finger(out, user, host, "yes".equalsIgnoreCase(verbose));
		} catch (IOException e) {
		    out.println(e.toString());
		}
		out.println();
	    }
	}
	out.println("</pre>");
	out.println("</body></html>");
    }

    /*
     * Sends finger output for a user and host to the specified output
     * stream.
     */
    private void finger(ServletOutputStream out, String user, String host,
		boolean verbose)
	throws IOException
    {
	// open connection to finger daemon
	Socket s;
	out.println(" testing herer");
	    s = new Socket(InetAddress.getLocalHost(), FINGER_PORT);
	// send finger command
	    
	PrintStream socket_out = new PrintStream(s.getOutputStream());
	socket_out.print("welcome yaar");
	if (verbose) {
	    socket_out.print("/W ");
	}
	if (user != null) {
	    socket_out.print(user);
	}
	socket_out.print("\r\n");
	socket_out.flush();
	// copy results to output stream
	// s.setSoTimeout(30000);
	InputStream in = s.getInputStream();
	byte[] buf = new byte[2048];
	int len;
	try {
	    while ((len = in.read(buf, 0, buf.length)) != -1) {
		out.write(buf, 0, len);
	    }
	}
	catch (InterruptedIOException ioe) {
	    timeout_msg.getBytes(0, timeout_msg.length(), buf, 0);
	    out.write(buf, 0, timeout_msg.length());
	    ioe.printStackTrace();
	}
	finally {
	    socket_out.close();
	    s.close();
	}
    }
}