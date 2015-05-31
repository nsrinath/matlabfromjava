import matlabcontrol.*;

public class MatlabHelper {
	
	static MatlabProxy matlabProxy = null;

	/**
	 * Uses Singleton Pattern to return a MatlabProxy object 
	 * 
	 */
	public static MatlabProxy getProxy() throws MatlabConnectionException {
		
		if (matlabProxy != null)
			return matlabProxy;

		// create objects only once
		MatlabProxyFactoryOptions matlabProxyFactoryOptions = new MatlabProxyFactoryOptions.Builder()
	    									.setUsePreviouslyControlledSession(true)
										    .setHidden(true)
										    .setMatlabLocation(null).build(); 
		MatlabProxyFactory matlabProxyFactory = new MatlabProxyFactory(matlabProxyFactoryOptions);
		matlabProxy = matlabProxyFactory.getProxy();	
		return matlabProxy;
	}

}
