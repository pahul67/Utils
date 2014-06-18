package com.cpumedia.hmafp;

import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.lang.Integer;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.*;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.lang.IndexOutOfBoundsException;
import java.util.Scanner;

/**
 * "Hide My Ass" list of free proxies
 * @author William Hill <william.hill@gmail.com>
 *
 */
public class HMAFreeProxyList	{

	private List<HMAFreeProxy> proxyList = null;
	private	String strFilename = "hma_proxies.txt";
  	private final static String strEncoding = "UTF-8";
    private final static String strNL = System.getProperty("line.separator");
	
	public HMAFreeProxyList()	{

		proxyList = new ArrayList<HMAFreeProxy>();

	}

	/**
	 * Add an HMAFreeProxy to this list.
	 *
	 * @param hmafp
	 *
	 * @author William Hill <william.hill@gmail.com>
	 */
 	public void add(HMAFreeProxy hmafp)	{

		proxyList.add(hmafp);

	}

	/**
	 * Add an HMAFreeProxyList to this list. Returns true is this HMAFreeProxyList was changed
	 *
	 * @param list
	 * @return boolean
	 *
	 * @author William Hill <william.hill@gmail.com>
	 */
	public boolean addAll(HMAFreeProxyList list)	{

		return proxyList.addAll(list.getArrayList());

	}

	/**
	 * Returns the number of elements in this list.
	 *
	 * @return int
	 *
	 * @author William Hill <william.hill@gmail.com>
	 */
	public int size()	{

		return proxyList.size();

	}

	/**
	 * Clears all of the elements in this list.
	 *
	 *
	 * @author William Hill <william.hill@gmail.com>
	 */
	public void clear()	{

		proxyList.clear();

	}

	/**
	 * Returns the HMAFreeproxy at the specified position in this list.
	 *
	 * @param index
	 * @return HMAFreeproxy
	 *
	 * @author William Hill <william.hill@gmail.com>
	 */
	public HMAFreeProxy get(int index) throws IndexOutOfBoundsException	{

		return (HMAFreeProxy)proxyList.get(index);

	}

	/**
	 * Return this HMAFreeProxyList internal ArrayList<HMAFreeProxy>.
	 *
	 * @return ArrayList<HMAFreeProxy>
	 *
	 * @author William Hill <william.hill@gmail.com>
	 */
    private List<HMAFreeProxy> getArrayList() {
    
    	return proxyList;

    }

	/**
	 * Return all HMAFreeProxy for specified country, speed and connectivity
	 *
	 * @param iso	2-letter ISO code of selected country
	 * @param speed	minimum required speed (0-100), -1 if no any speed acceptable
	 * @param connectivity	minimum required connectivity (0-100), -1 if no any connectivity acceptable
	 * @return HMAFreeProxyList
	 *
	 * @author William Hill <william.hill@gmail.com>
	 */
	public HMAFreeProxyList getMostRecentByCriteria(String iso, int speed, int connectivity)	{

		HMAFreeProxyList ret = new HMAFreeProxyList();

		sortByDate(true);

        for (HMAFreeProxy hmafp : proxyList) {
	        if (hmafp.strISO.equals(iso) && hmafp.speed >= speed && hmafp.connectivity >= connectivity) {
	        	ret.add(hmafp);
	        }
        }

		return ret;
	}

	/**
	 * Return most recent HMAFreeProxy for specified country
	 *
	 * @param iso	2-letter ISO code of selected country
	 * @return HMAFreeProxy
	 *
	 * @author William Hill <william.hill@gmail.com>
	 */
	public HMAFreeProxy getMostRecentByCountry(String iso)	{

		HMAFreeProxy ret = null;

		sortByDate(true);

        for (HMAFreeProxy hmafp : proxyList) {
	        if (hmafp.strISO.equals(iso)) {
	        	ret = hmafp;
	        	return ret;
	        }
        }

		return ret;
	}
	
	/**
	 * Return all HMAFreeProxy for specified country
	 *
	 * @param iso	2-letter ISO code of selected country
	 * @return HMAFreeProxyList
	 *
	 * @author William Hill <william.hill@gmail.com>
	 */
	public HMAFreeProxyList getAllCountry(String iso)	{

		HMAFreeProxyList ret = new HMAFreeProxyList();

		sortByDate(true);

        for (HMAFreeProxy hmafp : proxyList) {
	        if (hmafp.strISO.equals(iso)) {
	        	ret.add(hmafp);
	        }
        }

		return ret;
	}

	private class DateComparator implements Comparator<HMAFreeProxy> {
	    public int compare(HMAFreeProxy o1, HMAFreeProxy o2) {
    	    return (o1.strTS.compareTo(o2.strTS));
    	}
	}

	/**
	 * Sort this HMAFreeProxyList by date
	 *
	 * @param desc	true for descending order
	 *
	 * @author William Hill <william.hill@gmail.com>
	 */
	public void sortByDate(boolean desc)	{
	
		Collections.sort(proxyList, new DateComparator());
		if(desc)	{
			Collections.reverse(proxyList);
		}
		
	}

	/**
	 * Writes this HMAFreeProxyList specified file
	 *
	 * @param filename	file to be written to
	 *
	 * @author William Hill <william.hill@gmail.com>
	 */
	public void write(String filename)   {
		strFilename = filename;
		write();
    }
  
	/**
	 * Reads into this HMAFreeProxyList from specified file
	 *
	 * @param filename	file to be read from
	 *
	 * @author William Hill <william.hill@gmail.com>
	 */
	public void read(String filename)  {
		strFilename = filename;
		read();
	}

	/**
	 * Writes this HMAFreeProxyList to "hma_proxies.txt" in current directory
	 *
	 * @author William Hill <william.hill@gmail.com>
	 */
	public void write()   {
    	Writer out = null;
    	try {
	    	out = new OutputStreamWriter(new FileOutputStream(strFilename), strEncoding);
      		
	        for (HMAFreeProxy hmafp : proxyList) {
				out.write(hmafp.strTS + "," + hmafp.strISO + "," + Integer.toString(hmafp.speed) + "," + Integer.toString(hmafp.connectivity) + "," + hmafp.strType + "," + hmafp.strAnonymity + "," + hmafp.strIP + "," + hmafp.strPort + strNL);
    	    }

    	}
    	catch(IOException ioe) {
     		ioe.printStackTrace();
    	}
    	/*
    	catch(FileNotFoundException fnfe) {
     		fnfe.printStackTrace();
    	}
    	*/
    	finally {
    		if(out != null) {
    			try {
		     		out.close();
		    	}
    			catch(IOException ioe) {
		     		ioe.printStackTrace();
		    	}
    			
	    	}
    	}
    }
  
	/**
	 * Reads into this HMAFreeProxyList from "hma_proxies.txt" in current directory
	 *
	 * @author William Hill <william.hill@gmail.com>
	 */
	public void read()  {
    	String text = null;
		HMAFreeProxy hmafp = null;
	    Scanner scanner = null;
    	try {
	    	scanner = new Scanner(new FileInputStream(strFilename), strEncoding);
      		while (scanner.hasNextLine()){
				text = scanner.nextLine();

				String[] s = text.split(",");
				if(s != null)	{
					hmafp = new HMAFreeProxy();
			        hmafp.strTS = s[0];
        			hmafp.strISO = s[1];
        			hmafp.speed = Integer.parseInt(s[2]);
        			hmafp.connectivity = Integer.parseInt(s[3]);
        			hmafp.strType = s[4];
        			hmafp.strAnonymity = s[5];
        			hmafp.strIP = s[6];
        			hmafp.strPort = s[7];
        			this.add(hmafp);
		      	}
				
      		}
    	}
    	catch(IOException ioe){
			ioe.printStackTrace();
    	}
    	finally	{
    		if(scanner != null)	{
	      		scanner.close();
	    	}
    	}
	}

	/**
	 * @param i
	 * @param j
	 * 
	 * @author Paul Thomas <paul@thomasww.com>
	 */
	public HMAFreeProxyList geMostRecentBySpeed(int speed, int connectivity) {

		HMAFreeProxyList ret = new HMAFreeProxyList();

		sortByDate(true);

        for (HMAFreeProxy hmafp : proxyList) {
	        if (hmafp.speed >= speed && hmafp.connectivity >= connectivity) {
	        	ret.add(hmafp);
	        }
        }

		return ret;
		// TODO Auto-generated method stub
		
	}

}
