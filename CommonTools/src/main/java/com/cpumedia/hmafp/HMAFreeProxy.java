package com.cpumedia.hmafp;

/**
 * "Hide My Ass" free proxy data
 * @author William Hill <william.hill@gmail.com>
 *
 */
public class HMAFreeProxy	{

	/**
   	 * The last time this free proxy was tested
     */
    public String strTS = null;

	/**
   	 * 2-letter ISO country code
     */
	public String strISO = null;

	/**
   	 * Connection speed (0-100)
     */
	public int speed;

	/**
   	 * Connectivity rating (0-100)
     */
	public int connectivity;

	/**
   	 * Protocol type: HTTP, HTTPS, sock4/5
     */
	public String strType = null;

	/**
   	 * Type of anonymity
     */
	public String strAnonymity = null;

	/**
   	 * IP address
     */
	public String strIP = null;

	/**
   	 * Port number
     */
	public String strPort = null;

}
