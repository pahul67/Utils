/**
 * 
 */
package com.thomasww.tools.http;

import java.io.IOException;
import java.io.InputStream;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.Proxy.Type;
import java.net.Socket;
import java.net.SocketAddress;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.impl.Log4JLogger;
import org.apache.http.Header;
import org.apache.http.HttpException;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.conn.scheme.PlainSocketFactory;
import org.apache.http.impl.client.ContentEncodingHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

/**
 * @author Paul Thomas <paul@thomasww.com>
 *
 */
public class Client {

	/**
	 * The logger.
	 *
	 * @author Paul Thomas <paul@thomasww.com>
	 */
	private static Log log = new Log4JLogger("org.da.agg.http");

	/**
	 * The unique instance of the <code>Client</code>.  
	 */
	private static Client instance = null;

	/**
	 * The default empty constructor.
	 */
	private Client() {

	}

	/**
	 * This method instantiates the unique instance of the <code>Client</code> if needed and 
	 * returns the unique instance of the <code>Client</code>.
	 * 
	 * @author Paul Thomas <paul@thomasww.com>
	 * @return The unique instance of the <code>Client</code>
	 */
	public static Client getInstance() {
		if (instance == null)
			instance = new Client();
		return instance;
	}

	/**
	 * This method connects to the Internet and retrieves the feed at the given url. It 
	 * makes a call to the <code>getFeed(String url, int port)</code> method with the 
	 * default port(80).
	 * 
	 * @author Paul Thomas <paul@thomasww.com>
	 * @param url The url of the feed to get.
	 * @return The content of the feed.
	 */
	public InputStream getFeed(String url) {
		return getFeed(url,80);
	}

	/**
	 * This method connects to the Internet and retrieves the feed at the given url.
	 * 
	 * @author Paul Thomas <paul@thomasww.com>
	 * @param url The url of the feed to get.
	 * @param port The port of the feed to get.
	 * @return The content of the feed.
	 */
	private InputStream getFeed(String url, int port) {
		InputStream feed = null;
		HttpGet get = new HttpGet(url);
		HttpClient httpClient = new ContentEncodingHttpClient();
	//	HostConfiguration hostConf = new HostConfiguration();
	//	hostConf.setHost(url.substring(0, url.indexOf("/", 8)), port);
	//	httpClient.setHostConfiguration(hostConf);
		try {
			SocketAddress addr = new InetSocketAddress("217.216.162.213", 50259);
			Proxy proxy = new Proxy(Type.SOCKS, addr);
			HttpResponse resp = httpClient.execute(get);
			//System.out.println("Resp code: " + respCode);
			if (resp.getStatusLine().getStatusCode() == 200) {
				Header[] headers = resp.getAllHeaders();
				for (Header header:headers) {
					System.out.println(header.getName() + "|" + header.getValue());
				}
				System.out.println(EntityUtils.toString(resp.getEntity()));//get.getResponseBodyAsStream();
			} else {
				throw new Exception("Error while connecting to the feed, returned: "+resp);
			}
		} catch (HttpException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// TODO Auto-generated method stub
		return feed;
	}

	/**
	 * Main method for quick tests purposes.
	 * 
	 * @author Paul Thomas <paul@thomasww.com>
	 * @param args The arguments to use.
	 */
	public static void main(String[] args) {
		Client client = Client.getInstance();
		InputStream test = client.getFeed("http://itunes.apple.com/us/rss/topfreeapplications/limit=300/genre=6018/xml");
		//InputStream test = client.getFeed("https://www.googleapis.com/language/translate/v2/detect?q=Books&key=AIzaSyCiDzrYxVK2X6UDetJXeEBU8bOdxutOkro");
		System.out.println(test);
//		Session session = null;
//
//		try{
//			// This step will read hibernate.cfg.xml and prepare hibernate for use
//			log.info("Just checking");
//			try {
//				SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
//				session =sessionFactory.openSession();
//			} catch(HibernateException e) {
//				log.fatal("Error creating the session: "+e.getMessage());
//				e.printStackTrace();
//				System.out.println("Error creating the session: "+e.getMessage());
//				return;
//			}
//			log.info("Another check!");
//			log.info("Session: "+session);
//			System.out.println("Session: "+session);
//			Stream stream = new Stream();
//			//stream.setId(1L);
//			stream.setUrl("http://itunes.apple.com/fr/rss/topfreeapplications/limit=300/genre=6015/xml");
//			stream.setName("iTunes - FR - Top Free Finance");
//			stream.setGroup(1L);
//			stream.setLastUpdate(new Date());
//			session.save(stream);
//			log.info("Done");
//			System.out.println("Done");
//		}catch(Exception e){
//			e.printStackTrace();
//			log.fatal("Exception: "+e.getMessage());
//			System.out.println("Exception: "+e.getMessage());
//		}finally{
//			// Actual contact insertion will happen at this step
//			System.out.println("Everything done (supposedly, if this is the only line it sucks)");
//			if (session != null) {
//				try {
//					session.flush();
//					session.close();
//				}catch(HibernateException exc) {
//					log.fatal("Exception: "+exc.getMessage());
//					exc.printStackTrace();
//				}
//			}
//		}
	}
}
