import matlabcontrol.*;

public class MatlabScriptRunner {

	/**
	 * @param matlabProxy requires a valid {@link MatlabProxy} object
	 * @param scriptFolder the folder where the script exists
	 * @param script2Run is the matlab script that needs to be executed
	 * @return error code (success code)
	 * @throws MatlabConnectionException - the caller will handle/throw this
	 * @throws MatlabInvocationException - the caller will handle/throw this
	 */
	public static int runMatlabScript(MatlabProxy matlabProxy, String scriptFolder, String script2Run)
			throws MatlabConnectionException, MatlabInvocationException {
		if (matlabProxy != null) {

			// set up input and output to/from the matlab script
			// This could be path to the required CSV file
			String input = "[1, 2, 3, 4, 5]";
			int expectedNumOfOutputArgs = 2; 
			
			// execute the script
			String addPathCmd = "addpath('" + scriptFolder + "')";
			String rmPathCmd = "rmpath('" + scriptFolder + "')";
			matlabProxy.eval(addPathCmd); 
//			Object[] returnVals = matlabProxy.returningFeval("outputs", 2, "[12.7, 45.4, 98.9, 26.6, 53.1]");
			Object[] returnVals = matlabProxy.returningFeval(script2Run, expectedNumOfOutputArgs, input);
			matlabProxy.eval(rmPathCmd);
	        
			// get the result double from matlab
			for (int i = 0; i < returnVals.length; i++) {
				System.out
						.println("Printing iteration " + i + " of returnVals");
				double[] tmp = (double[]) returnVals[i];
				for (int j = 0; j < tmp.length; j++) {
					System.out
							.println("inside results " + j + " = " + tmp[j]);
				}
			}
			return 0;
		} 
		System.err.println("Error: Null proxy object - Initialize proxy before using it");
		return -1;
	}

	public static void main(String[] args) {
		MatlabProxy matlabProxy = null;
		final String SCRIPTS_FOLDER = "./scripts";
		final String SCRIPT_TO_RUN = "outputs";
		try {
			matlabProxy = MatlabHelper.getProxy();
			int rc = runMatlabScript(matlabProxy, SCRIPTS_FOLDER, SCRIPT_TO_RUN);
			if (rc == -1) {
				System.err.println("Error executing matlab script");
			}
			matlabProxy.exit();
		} catch (MatlabConnectionException e) {
			e.printStackTrace();
		} catch (MatlabInvocationException e) {
			e.printStackTrace();
		}

	}

}