package context;

import java.io.PrintWriter;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.sql.DataSource;

/**
 * @author William Hill <william.hill@gmail.com>
 *
 */
public class MyDataSource implements DataSource, Serializable {
	
	private String cnxStr;
    private String userStr;
    private String pwStr;
    
    private static final long serialVersionUID = 1L;
    
    MyDataSource(String cnxStr, String user, String pw) {
        this.cnxStr = cnxStr;
        this.userStr = user;
        this.pwStr = pw;
    }
    
    public Connection getConnection() throws SQLException
    {
        return DriverManager.getConnection(cnxStr, userStr, pwStr);
    }

	public Connection getConnection(String username, String password)
			throws SQLException {return null;}
	public PrintWriter getLogWriter() throws SQLException {return null;}
	public int getLoginTimeout() throws SQLException {return 0;}
	public void setLogWriter(PrintWriter out) throws SQLException {	}
	public void setLoginTimeout(int seconds) throws SQLException {}
	
	public boolean isWrapperFor(Class<?> iface)  throws SQLException { return true; }
    
    public <T> T unwrap(java.lang.Class<T> interfaces) throws SQLException{ 
        try { 
            return interfaces.cast(this); 
        } catch (ClassCastException cce) { 
            throw new SQLException(); 
        } 
    }

}
