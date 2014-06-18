package sandbox;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import org.apache.commons.io.IOUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.MethodNotSupportedException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.DefaultHttpRequestFactory;
import org.apache.http.impl.client.DefaultHttpClient;

import context.MyContext;
import context.MyContextFactory;

public class ImageUrlFixer {

	/**
	 * @param args
	 *
	 * @author Paul Thomas <paul@thomasww.com>
	 */
	public static void main(String[] args) {
		DataSource ds = null;
		try  {
			MyContext ctx = MyContextFactory.createMyContext("com.mysql.jdbc.Driver");
			ctx.addDataSource("java:/comp/env/jdbc/DADBTEST", "jdbc:mysql://46.105.127.38:3306/decodeapps1.1test?useUnicode=true&characterEncoding=UTF-8", "paul", "po9l");
			ctx.addDataSource("java:/comp/env/jdbc/DADB", "jdbc:mysql://46.105.127.38:3306/decodeapps1.1?useUnicode=true&characterEncoding=UTF-8", "paul", "po9l");
			ds = (DataSource) ctx.lookup("java:/comp/env/jdbc/DADB");
		} catch (Exception e)  {
			e.printStackTrace();
		} 
		Connection connection = null;
		Statement statement = null;
		ResultSet resultSet = null;
		String query = "SELECT uid, url_logo FROM VARIANT WHERE uid < 374;";
		System.out.println("SELECT uid, url_logo FROM VARIANT WHERE  uid < 374;");
		try {
			connection = ds.getConnection();
			statement = connection.createStatement();
            resultSet = statement.executeQuery(query);
        	statement = connection.createStatement();
			DefaultHttpRequestFactory factory = new DefaultHttpRequestFactory();
			DefaultHttpClient client = new DefaultHttpClient();
            while (resultSet.next()) {
            	String logoUri = resultSet.getString("url_logo");
            	System.out.println("Variant ID: " + resultSet.getLong("uid") + "|url: " + "http://" + logoUri.substring(33) + "|path: " + logoUri);
            //	System.out.println("UPDATE VARIANT set url_logo = '" + resultSet.getString("url_logo").substring(4) + "' WHERE uid = " + resultSet.getLong("uid") + ";");
            //	statement.addBatch("UPDATE VARIANT set url_logo = '" + resultSet.getString("url_logo").substring(4) + "' WHERE uid = " + resultSet.getLong("uid") + ";");
            	String hostname = logoUri.substring(33);
            	hostname = hostname.substring(0,hostname.indexOf("/"));
            	System.out.println(hostname);
            	String path = logoUri.substring(33);
            	path = path.substring(path.indexOf("/"));
            	System.out.println(path);
            	try {
            		HttpEntity entity = client.execute(new HttpHost(hostname), factory.newHttpRequest("GET", path)).getEntity();
					InputStream in = entity.getContent();
					File file = new File("/Users/paul/tmp/" + logoUri.substring(0,logoUri.lastIndexOf("/")));
					file.mkdirs();
					file = new File("/Users/paul/tmp/" + logoUri);
					file.createNewFile();
				    OutputStream out = new FileOutputStream(file);
				    IOUtils.copy(in, out);
					//FileOutputStream out = new FileOutputStream(file);
					//byte[] b = new byte[256];
					//while (in.read(b) > -1) {
						//out.write(b);
					//}
					out.close();
					in.close();
					System.out.println("Saved " + file.getName());
					//entity.consumeContent();
				} catch (IllegalStateException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (ClientProtocolException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (MethodNotSupportedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} finally {
					client.getConnectionManager().closeExpiredConnections();
				}
            }
            //statement.executeBatch();
		}catch (Exception e) {
			e.printStackTrace();
		}
		/*try {
			connection = ds.getConnection();
			statement = connection.createStatement();
			for (int i = 1; i < 374; i++) {
				String query = "INSERT INTO WATCHFOLDER_APP (watchfolder,app) values (" + 2 + "," + i +");";
				System.out.println("SELECT uid, url_logo FROM VARIANT WHERE url_logo like '/tmp/%';");
				statement.addBatch(query);
			}
			statement.executeBatch();
		}catch (Exception e) {
			e.printStackTrace();
		}*/
	}

}
