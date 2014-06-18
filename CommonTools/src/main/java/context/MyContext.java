package context;

import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.naming.spi.InitialContextFactory;
import javax.naming.spi.InitialContextFactoryBuilder;

import context.MyDataSource;

/**
 * @author William Hill <william.hill@gmail.com>
 *
 */
public class MyContext extends InitialContext implements InitialContextFactoryBuilder, InitialContextFactory {

	Map<Object,Object> dataSources;
	
	MyContext() throws NamingException {
		super();
		dataSources = new HashMap<Object,Object>();
	}
	
	public void addDataSource(String name, String cnxStr, String user, String pw) {
		this.
		dataSources.put(name, new MyDataSource(cnxStr, user, pw));
	}

	public InitialContextFactory createInitialContextFactory(
			Hashtable<?, ?> hsh) throws NamingException {
		dataSources.putAll(hsh);
		return this;
	}

	public Context getInitialContext(Hashtable<?, ?> arg0)
			throws NamingException {
		return this;
	}

	@Override
	public Object lookup(String name) throws NamingException {
		Object ret = dataSources.get(name);
		return (ret != null) ? ret : super.lookup(name);
	}	
}
