package context;

import javax.naming.spi.NamingManager;

import context.MyContext;

/**
 * @author William Hill <william.hill@gmail.com>
 *
 */
public class MyContextFactory {
	/**
	 * do not instantiate this class directly. Use the factory method.
	 */
	private MyContextFactory() {}
	
	public static MyContext createMyContext(String dbDriver) throws Exception {

		try { 
			MyContext ctx = new MyContext();
			Class.forName(dbDriver);	
			NamingManager.setInitialContextFactoryBuilder(ctx); 			
			return ctx;
		}
		catch(Exception e) {
			throw new Exception("Error Initializing Context: " + e.getMessage(),e);
		}
	}	
}
