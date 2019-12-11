package com.util;

import com.exception.BaseException;
import org.json.JSONObject;

import javax.servlet.ServletContext;
import javax.servlet.ServletRequest;
import java.sql.Timestamp;
import java.text.*;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * <p>
 * Title:
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Copyright: Copyright (c) 2002
 * </p>
 * <p>
 * Company:
 * </p>
 *
 * @author unascribed
 * @version 1.0
 */

public class Utils {
	private static NumberFormat NUMBER_FORMAT0; // localized "#,##0"
    private static NumberFormat NUMBER_FORMAT1; // localized "#,##0.0"
    public static String GENERAL_UNIT_KILOBYTES = "KB";
    public static String GENERAL_UNIT_MEGABYTES = "MB";
    public static String GENERAL_UNIT_GIGABYTES = "GB";
    public static String GENERAL_UNIT_TERABYTES = "TB";

	public static void log(ServletContext servletContext, String message) {
		servletContext.log(message);
	}

	protected static ClassLoader getCurrentClassLoader(Object defaultObject) {
		ClassLoader loader = Thread.currentThread().getContextClassLoader();
		if (loader == null) {
			loader = defaultObject.getClass().getClassLoader();
		}
		return loader;
	}

	public static String getDisplayString(String bundleName, String id,
			Object params[], Locale locale) {
		String text = null;
		ResourceBundle bundle = ResourceBundle.getBundle(bundleName, locale, getCurrentClassLoader(params));
		//ResourceBundle bundle = ResourceBundle.getBundle(bundleName, locale);
		try {
			text = bundle.getString(id);
		} catch (MissingResourceException e) {
			text = "!! key " + id + " not found !!";
		}
		if (params != null) {
			MessageFormat mf = new MessageFormat(text, locale);
			text = mf.format(params, new StringBuffer(), null).toString();
		}
		return text;
	}

	public static String getDisplayString(String bundleName, String id,
			Object params[], String localeString) {
		String[] langArr = localeString.split("_");
		Locale locale = new Locale(langArr[0], langArr[1]);
		String text = null;
		ResourceBundle bundle = ResourceBundle.getBundle(bundleName, locale, getCurrentClassLoader(params));
		//ResourceBundle bundle = ResourceBundle.getBundle(bundleName, locale);
		try {
			text = bundle.getString(id);
		} catch (MissingResourceException e) {
			text = "!! key " + id + " not found !!";
		}
		if (params != null) {
			MessageFormat mf = new MessageFormat(text, locale);
			text = mf.format(params, new StringBuffer(), null).toString();
		}
		return text;
	}

	public static Timestamp getCurrentTimeStamp() {
		Timestamp today = new Timestamp(System.currentTimeMillis());

		return today;
	}

	public static String timeStamp2DateString(Timestamp pDateValue,
                                              String pDateFormat) {

		SimpleDateFormat formatter = new SimpleDateFormat(pDateFormat);
		return formatter.format(pDateValue);
	}

	public static String ifBlank(String inputData, String outputData)
			throws Exception {
		if (inputData == null || inputData.trim().equals("") || inputData.trim().equals("null"))
			return outputData;
		else
			return inputData;
	}

	public static String cnvDateToString(Date inputDate, String format)	throws Exception {
			//String targetFormat = CourseOutlineUtil.TARGEt_FORMAT_DATE;
			String targetFormat = format;
			targetFormat = targetFormat.toUpperCase();
			String retDate = "";
			String hh = "";
			String mm = "";
			String ss = "";
			if (inputDate != null) {
				Calendar calendarObj = Calendar.getInstance(Locale.ENGLISH);
				calendarObj.setTime(inputDate);

				int day = calendarObj.get(Calendar.DAY_OF_MONTH);
				int month = calendarObj.get(Calendar.MONTH) + 1;
				int year = calendarObj.get(Calendar.YEAR);
				int hours = calendarObj.get(Calendar.HOUR_OF_DAY);
				int minutes = calendarObj.get(Calendar.MINUTE);
				int seconds = calendarObj.get(Calendar.SECOND);

				/*			Debug.println(" day = " + day + " month = " + month + " year = " + year +  "\n"
								+ " hours = " + hours + " minutes = " + minutes + " seconds = "+ seconds);*/

				if (day < 10)
					retDate = "0" + day;
				else
					retDate = (new Integer(day)).toString();

				if (month < 10) {
					if (targetFormat.equals("DD/MM/YYYY")) {
						retDate = retDate + "/0" + month + "/" + year;
					}
					if (targetFormat.equals("YYYY/MM/DD")) {
						retDate = year + "/0" + month + "/" + retDate;
					}
					if (targetFormat.equals("MM/DD/YYYY")) {
						retDate = "/0" + month + "/" + retDate + year;
					}
					if (targetFormat.equals("DD/MM/YYYY HH24:MI:SS")
						|| targetFormat.equals("DD/MM/YYYY HH:MM:SS")) {
						if (hours < 10)
							hh = "0" + Integer.toString(hours);
						else
							hh = Integer.toString(hours);
						if (minutes < 10)
							mm = "0" + Integer.toString(minutes);
						else
							mm = Integer.toString(minutes);
						if (seconds < 10)
							ss = "0" + Integer.toString(seconds);
						else
							ss = Integer.toString(seconds);

						retDate = retDate + "/0" + month + "/" + year + " " + hh + ":" + mm + ":" + ss;
					}
				} else {
					if (targetFormat.equals("DD/MM/YYYY")) {
						retDate = retDate + "/" + month + "/" + year;
					}

					if (targetFormat.equals("YYYY/MM/DD")) {
						retDate = year + "/" + month + "/" + retDate;
					}
					if (targetFormat.equals("MM/DD/YYYY")) {
						retDate = month + "/" + retDate + year;
					}
					if (targetFormat.equals("DD/MM/YYYY HH24:MI:SS")
						|| targetFormat.equals("DD/MM/YYYY HH:MM:SS")) {
						if (hours < 10)
							hh = "0" + Integer.toString(hours);
						else
							hh = Integer.toString(hours);
						if (minutes < 10)
							mm = "0" + Integer.toString(minutes);
						else
							mm = Integer.toString(minutes);
						if (seconds < 10)
							ss = "0" + Integer.toString(seconds);
						else
							ss = Integer.toString(seconds);

						retDate = retDate + "/" + month + "/" + year + " " + hh + ":" + mm + ":" + ss;
					}
				}
			} else {
				if (targetFormat.equals("DD/MM/YYYY")) {
					retDate = "00/00/0000";
				}
				if (targetFormat.equals("YYYY/MM/DD")) {
					retDate = "0000/00/00";
				}
				if (targetFormat.equals("DD/MM/YYYY HH24:MI:SS")
					|| targetFormat.equals("DD/MM/YYYY HH:MM:SS")) {
					retDate = "00/00/0000 00:00:00";
				}
			}
			return retDate;
		}

	public static String cvtDateTimeToStringFormat(Date dateSource,String formatString,String localeString){
		Date rDate = new Date();
		SimpleDateFormat sf = new SimpleDateFormat(formatString.toString());
		try {
			Locale.setDefault(Locale.US);
			Calendar c = Calendar.getInstance();
			c.setTimeInMillis(dateSource.getTime());
			if(localeString.equalsIgnoreCase("th_TH")){
				c.set(Calendar.YEAR,c.get(Calendar.YEAR)-543);
				//Locale.setDefault(new Locale("en", "US"));
			    //sf = new SimpleDateFormat(formatString.toString());
			}
			rDate.setTime(c.getTimeInMillis());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			return null;
		}
		return sf.format(rDate);
	}

	public static String cvtDateForShow(Date sDateValue,String locale){
		if(sDateValue == null || sDateValue.equals("")) return "";
		//Locale.setDefault(Locale.US);
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy",Locale.US);
		String strDateLocal=formatter.format(sDateValue);
		if(locale.equals("th_TH")){
			strDateLocal= dateStringAD2BE(strDateLocal,"dd/MM/yyyy");
		}
		return strDateLocal;
	}

	public static Timestamp cvtDateTimeForDB(String sDateValue,String timeValue, String locale){
		if (sDateValue == null || sDateValue.equals("")) return null;

		if (sDateValue.indexOf(" ") > 0) sDateValue = sDateValue.substring(0, sDateValue.indexOf(" "));
		if (timeValue == null || timeValue.length() == 0) {
			/*Date nDate = new Date();
			SimpleDateFormat sTime = new SimpleDateFormat("HH:mm:ss");
			timeValue = sTime.format(nDate);*/
			SimpleDateFormat sTime = new SimpleDateFormat("HH:mm:ss");
			Date nDate = new Date(sDateValue + " " + "00:00:00");
			timeValue = sTime.format(nDate);
		} else {
			timeValue = timeValue.replace('.', ':');
			SimpleDateFormat sTime = new SimpleDateFormat("HH:mm:ss");
			Date nDate = new Date(sDateValue + " " + timeValue);
			timeValue = sTime.format(nDate);
		}
		Date dDateLocal = new Date();
		try {
			if (locale.equals("th_TH")) {
				dDateLocal = cnvStringToDate(dateStringBE2AD(sDateValue, "dd/MM/yyyy") + " " + timeValue);
			} else {
				dDateLocal = cnvStringToDate(sDateValue + " " + timeValue);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		//Locale.setDefault(Locale.US);
		SimpleDateFormat sDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.US);
		Timestamp tDate = Timestamp.valueOf(sDateFormat.format(dDateLocal));
		return tDate;
	}

	/**
	 * Convert date string to date value.
	 * @return date value.
	 * @param inpDate the date string in the format of "dd/mm/yyyy". Example. "15/01/2002".
	 */
	public static Date cnvStringToDate(String inpDate) {
		Date date = null;
		boolean convert = false;
		if (inpDate == null || inpDate.length() == 0 || inpDate.equals("null")) {
			return date;
		}
		if ((inpDate != null) || (inpDate.length() >= 10)) {
			int ind1 = inpDate.indexOf('/');
			int ind2 = inpDate.lastIndexOf('/');
			if ((ind1 == 2) && (ind2 == 5))
				convert = true;
		}
		Calendar calendar = Calendar.getInstance(Locale.ENGLISH);
		if (convert) {
			int day = Integer.parseInt(inpDate.substring(0, 2));
			int month = Integer.parseInt(inpDate.substring(3, 5));
			int year = Integer.parseInt(inpDate.substring(6, 10));

			if (inpDate.length() > 10) {
				int hh = Integer.parseInt(inpDate.substring(11, 13));
				int mm = Integer.parseInt(inpDate.substring(14, 16));
				if (inpDate.length() > 16) {
					int ss = Integer.parseInt(inpDate.substring(17, 19));
					calendar.set(year, month - 1, day, hh, mm, ss);
				} else
					calendar.set(year, month - 1, day, hh, mm);
			} else {
				calendar.set(year, month - 1, day);
			}
		}
		return calendar.getTime();
	}

	/**
	 * Converts date-time string from Ad to BE using given pattern.
	 * Example : dateStringAd2Be(25/01/2002,"dd/MM/yyyy") returns 25/01/2545
	 * @return the formatted date-time string in BE.
	 * @param pDateValue the date-time string to be formatted into BE.
	 * @param pDateFormat the date format pattern.
	 **/
	public static String dateStringAD2BE(String pDateValue, String pDateFormat) {
		if ((pDateValue != null) && (pDateValue.length() > 0)) {
			// Convert format content to upper case
			pDateFormat = pDateFormat.trim().toUpperCase();

			// Find each position of day, month and year of source format
			int indSrcFirstD = pDateFormat.indexOf('D');
			int indSrcLastD = pDateFormat.lastIndexOf('D');
			int indSrcFirstM = pDateFormat.indexOf('M');
			int indSrcLastM = pDateFormat.lastIndexOf('M');
			int indSrcFirstY = pDateFormat.indexOf('Y');
			int indSrcLastY = pDateFormat.lastIndexOf('Y');
			int trgYearLength = indSrcLastY - indSrcFirstY;
			// put data into target format
			String sDD = pDateValue.substring(indSrcFirstD, indSrcLastD + 1);
			String sMM = pDateValue.substring(indSrcFirstM, indSrcLastM + 1);
			String sYY = pDateValue.substring(indSrcFirstY, indSrcLastY + 1);
			int iYY = Integer.parseInt(sYY);
			iYY = iYY + 543;
			sYY = String.valueOf(iYY);
			return buildNewFormat(sDD, sMM, sYY, pDateFormat, trgYearLength);
		} else {
			return "";
		}
	}

	/**
	 * Converts date-time string from BE to AD using given format pattern.
	 * Example : dateStringBE2AD("25/01/2545","dd/MM/yyyy") returns  25/01/2002
	 * @return the formatted date-time string in BE.
	 * @param pDateValue the date-time string to be formatted into AD.
	 * @param pDateFormat the date format pattern.
	 */
	public static String dateStringBE2AD(String pDateValue, String pDateFormat) {
		if ((pDateValue != null) && (pDateValue.length() > 0)) {
			// Convert format content to upper case
			pDateFormat = pDateFormat.trim().toUpperCase();

			// Find each position of day, month and year of source format
			int indSrcFirstD = pDateFormat.indexOf('D');
			int indSrcLastD = pDateFormat.lastIndexOf('D');
			int indSrcFirstM = pDateFormat.indexOf('M');
			int indSrcLastM = pDateFormat.lastIndexOf('M');
			int indSrcFirstY = pDateFormat.indexOf('Y');
			int indSrcLastY = pDateFormat.lastIndexOf('Y');
			int trgYearLength = indSrcLastY - indSrcFirstY;

			// put data into target format
			String sDD = pDateValue.substring(indSrcFirstD, indSrcLastD + 1);
			String sMM = pDateValue.substring(indSrcFirstM, indSrcLastM + 1);
			String sYY = pDateValue.substring(indSrcFirstY, indSrcLastY + 1);
			int iYY = Integer.parseInt(sYY);
			iYY = iYY - 543;
			sYY = String.valueOf(iYY);

			return buildNewFormat(sDD, sMM, sYY, pDateFormat, trgYearLength);
		} else {
			return "";
		}
	}

	/**
	 * Build the day,month and year into the date string using given pattern.
	 * @param pDD the day string.
	 * @param pMM the month string.
	 * @param pYY the year string.
	 * @param pFormat the date format pattern.
	 * @param pYearLength the length of year.
	 **/
	/**
	* Example1 : buildNewFormat("25","01","2545","dd/MM/yyyy",4) returns 25/01/2545
	* Example2 : buildNewFormat("25","01","2002","dd/MM/yyyy",4) returns 25/01/2002
	* Example3 : buildNewFormat("25","01","02","dd/MM/yyyy",2) returns 25/01/02
	**/
	private static String buildNewFormat(
		String pDD,
		String pMM,
		String pYY,
		String pFormat,
		int pYearLength) {

		pFormat = pFormat.toUpperCase();
		char cFormat[] = pFormat.toCharArray();
		int i;
		StringBuffer result = new StringBuffer();

		for (i = 0; i < cFormat.length; i++) {
			if (cFormat[i] == 'D') {
				result.append(pDD);
				i++;
			} else
				if (cFormat[i] == 'M') {
					result.append(pMM);
					i++;
				} else
					if (cFormat[i] == 'Y') {
						result.append(pYY);
						i = i + pYearLength;
					} else
						result.append(cFormat[i]);

		}
		return result.toString();

	}

	/**
	 * Get the current date string using given pattern.
	 * Example : currentDate("dd/MM/yyyy"); returns  25/01/2002
	 * @return date-time string.
	 * @param pDateFormat the date format pattern.
	 */
	public static String currentDate(String pDateFormat) {
		String result = null;
		try {
			SimpleDateFormat dateFormatter = new SimpleDateFormat(pDateFormat,Locale.US);
			result = dateFormatter.format(new Date());
		} catch (Exception ex) {
		}

		return result;
	}

	public static String numberFormat(double input,String format) {
		String output = "";
		output = (new DecimalFormat(format)).format(input);
		return output;
	}

	public static Date cvtDateTimeFormat(String dateString,String formatString,String localeString)throws BaseException, ParseException{
		Date rDate = new Date();
		try {
			Locale.setDefault(Locale.US);
			SimpleDateFormat sf = new SimpleDateFormat(formatString.toString());
			rDate = sf.parse(dateString);
			Calendar c = Calendar.getInstance();
			c.setTimeInMillis(rDate.getTime());
			if (localeString.equalsIgnoreCase("th_TH")) {
				c.set(c.YEAR, c.get(c.YEAR) - 543);
			}
			rDate.setTime(c.getTimeInMillis());
		} catch (ParseException e) {

			throw e;
		}
		return rDate;
	}

	/**
	 * Converts the date-time string from old pattern into target pattern.
	 * Example :changeDateFormat("25/01/2545","dd/MM/yyyy","yyyy/mm/dd") returns 2545/01/25
	 * @return the formatted date-time string or null if the input date is not corrected.
	 * @param pDateValue the date-time string to be formatted into new pattern.
	 * @param formatSource the source pattern.
	 * @param formatTarget the target pattern.
	 **/
	public static String changeDateFormat(
		String pDateValue,
		String formatSource,
		String formatTarget) {

		String result = null;
		try {
			// Convert format content to upper case
			formatSource = formatSource.trim().toUpperCase();
			formatTarget = formatTarget.trim().toUpperCase();

			// Find each position of day, month and year of source format
			int indSrcFirstD = formatSource.indexOf('D');
			int indSrcLastD = formatSource.lastIndexOf('D');
			int indSrcFirstM = formatSource.indexOf('M');
			int indSrcLastM = formatSource.lastIndexOf('M');
			int indSrcFirstY = formatSource.indexOf('Y');
			int indSrcLastY = formatSource.lastIndexOf('Y');

			// determine target year length; 2 or 4 digits
			int indTrgLastY = formatTarget.lastIndexOf('Y');
			int indTrgFirstY = formatTarget.indexOf('Y');
			int trgYearLength = indTrgLastY - indTrgFirstY;

			// put data into target format
			String sDD = pDateValue.substring(indSrcFirstD, indSrcLastD + 1);
			String sMM = pDateValue.substring(indSrcFirstM, indSrcLastM + 1);
			String sYY = pDateValue.substring(indSrcFirstY, indSrcLastY + 1);

			// get result using internal private method
			result = buildNewFormat(sDD, sMM, sYY, formatTarget, trgYearLength);

		} catch (Exception ex) {
		}

		return result;

	}


	/**
	 * Compare Two string return true if stringOne more than stringTwo.
	 * @return boolean.
	 * @param stringOne and stringTwo the string value.
	 */
	public static boolean stringOneMoreThanStringTwo(String stringOne, String stringTwo) {
		int i = stringOne.compareTo(stringTwo);
		if (i > 0) {
			return true;
		}
		return false;
	}

	public static boolean checkDate(String dateFrom, String dateTo) {
		boolean valError = false;
		try {
			if (dateFrom == null || dateFrom.equals("")) return false;
			if (dateTo == null || dateTo.equals("")) return false;

			Date dDateFrom = cnvStringToDate(dateFrom);
			Date dDateTo = cnvStringToDate(dateTo);
			if (dDateFrom.getTime() > dDateTo.getTime()) {
				valError = true;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return valError;
	}

    /**
	 * Convert date string to date BuddhistEra using given pattern and language.
	 * Example. if you assigned language ="T"
	 	1.SHORT       = 25/1/2545
		2.MEDIUM      = 25 �.�. 2545
		3.LONG        = 25 ���Ҥ� 2545
		4.FULL        = �ѹ�ء���� 25 ���Ҥ� �.�. 2545
		5.FULLMEDIUM  = �ѹ�ء���� 25 �.�. 2545

		if you assigned language ="E"
		1.SHORT       = 25/1/2002
		2.MEDIUM      = 25 �.�. 2002
		3.LONG        = 25 ���Ҥ� 2002
		4.FULL        = �ѹ�ء���� 25 ���Ҥ� �.�. 2002
		5.FULLMEDIUM  = �ѹ�ء���� 25 �.�. 2002
	 * @param strDate the date string Example. 05/02/2002
	 * @param format the target format such as SHORT,MEDIUM,LONG,FULL,FULLMEDIUM.
	 * @param language the language such as  E or T.
	 * @return the formatted date string.
	 */
	public static String cnvStringDateToBuddhistEra(
		String strDate,
		String format,
		String language)
		throws Exception {
		String dateThai = "";
		if (strDate.equals("00/00/0000")) {
			strDate = "";
			return strDate;
		}
		try {
			if (!validateDate(strDate))
				return "InValid Date ";

			String [] monthThai = { "���Ҥ�","����Ҿѹ��","�չҤ�","����¹","����Ҥ�","�Զع�¹","�á�Ҥ�","�ԧ�Ҥ�","�ѹ��¹","���Ҥ�","��Ȩԡ�¹","�ѹ�Ҥ�"} ;
			String [] shortMonthThai = { "�.�.","�.�.","��.�.","��.�.","�.�.","��.�.","�.�.","�.�.","�.�.","�.�.","�.�.","�.�."} ;
			String [] dayThai ={"�ҷԵ��","�ѹ���","�ѧ���","�ظ","����ʺ��","�ء��","�����"};
			String [] monthEnglish = {"January","February","March","April","May","June","July","August","September","October","November","December"} ;
			String [] shortMonthEng = {"Jan","Feb","Mar","Apr","May","Jun","Jul","Aug","Sep","Oct","Nov","Dec"} ;
			String [] dayEng ={"Sunday","Monday","Tueday","Wednesday","Thursday","Friday","Saturday"};

			Date newDate = cnvStringToDate(strDate);
			Calendar calendar = Calendar.getInstance(Locale.ENGLISH) ;
			calendar.setTime(newDate) ;
			int dayOfWeek =    calendar.get(Calendar.DAY_OF_WEEK) ;
			int day = calendar.get(Calendar.DAY_OF_MONTH) ;
			int month =  calendar.get(Calendar.MONTH) ;
			int yearAD = calendar.get(Calendar.YEAR) ;
			int yearBC = yearAD + 543 ;
			//System.out.println("  dayOfWeek ="+dayOfWeek+" day ="+day+" month= "+month+" yearAD="+yearAD);
			//String dateStr = (new SimpleDateFormat("dd/MM/yyyy")).format(newDate);
			//System.out.println(" DateStr = "+dateStr);
			String monthStr="";
			if (language.equalsIgnoreCase("T")) {
				if (format.equalsIgnoreCase("SHORT")) {
					if(month<9){
						dateThai =  cnvByteToString(Integer.toString(day)+"/0"+Integer.toString(month+1)+"/"+Integer.toString(yearBC)) ;
					} else {
						dateThai =  cnvByteToString(Integer.toString(day)+"/"+Integer.toString(month+1)+"/"+Integer.toString(yearBC)) ;
					}
					System.out.println("DateThai =  " + dateThai);
					return dateThai;
				}
				if (format.equalsIgnoreCase("MEDIUM")) {
					monthStr = shortMonthThai[month] ;
					dateThai =cnvByteToString( Integer.toString(day)+" "+monthStr+" "+Integer.toString(yearBC) );
					return dateThai;
				}
				if (format.equalsIgnoreCase("LONG")) {
					monthStr = monthThai[month] ;
					dateThai =cnvByteToString( Integer.toString(day)+" "+monthStr+" "+Integer.toString(yearBC) );
					return dateThai;
				}
				if (format.equalsIgnoreCase("FULL")) {
					monthStr = monthThai[month] ;
					dateThai =cnvByteToString( "�ѹ"+dayThai[dayOfWeek-1]+"��� "+ Integer.toString(day)+" "+monthStr+"  �.�. "+Integer.toString(yearBC) );
					return dateThai;
				}
				if (format.equalsIgnoreCase("FULLMEDIUM")) {
					monthStr = shortMonthThai[month] ;
					dateThai =cnvByteToString( "�ѹ"+dayThai[dayOfWeek-1]+"��� "+ Integer.toString(day)+" "+monthStr+" "+Integer.toString(yearBC) );
					return dateThai;
				}
			} else {
				if (format.equalsIgnoreCase("SHORT")) {
					dateThai =  Integer.toString(day)+"/"+Integer.toString(month+1)+"/"+Integer.toString(yearAD) ;
					return dateThai;
				}
				if (format.equalsIgnoreCase("MEDIUM")) {
					monthStr = shortMonthEng[month] ;
					dateThai = Integer.toString(day)+" "+monthStr+" "+Integer.toString(yearAD) ;
					return dateThai;
				}
				if (format.equalsIgnoreCase("LONG")) {
					monthStr = monthEnglish[month] ;
					dateThai = Integer.toString(day)+" "+monthStr+" "+Integer.toString(yearAD) ;
					return dateThai;
				}
				if (format.equalsIgnoreCase("FULL")) {
					monthStr = monthEnglish[month] ;
					dateThai =dayEng[dayOfWeek-1]+"  "+ Integer.toString(day)+" "+monthStr+" "+Integer.toString(yearAD) ;
					return dateThai;
				}
				if (format.equalsIgnoreCase("FULLMEDIUM")) {
					monthStr = shortMonthEng[month] ;
					dateThai =dayEng[dayOfWeek-1]+"  "+ Integer.toString(day)+" "+monthStr+" "+Integer.toString(yearAD) ;
					return dateThai;
				}
			}
		} catch (Exception ex) {
			System.out.println("Error : " + ex);
		}
		return null;
	}

	/**
	 * Validate the date string is corrected date value.
	 	Example.
		1. 33/04/2002  is false
		2. 28/02/2002  is true
		3. 28/13/2002  is false
		4. 29/02/2002  is false
	 * @return boolean true if corrected date.
	 * @param dateStr the date string.
	 */
	public static boolean validateDate(String inputDate) {
		if (inputDate.indexOf("/") < 0)
			return false;

		Calendar calendarObj = Calendar.getInstance(Locale.ENGLISH);

		int dayInt = Integer.parseInt(inputDate.substring(0, inputDate.indexOf("/")));
		inputDate = inputDate.substring(inputDate.indexOf("/") + 1);
		int monthInt = Integer.parseInt(inputDate.substring(0, inputDate.indexOf("/")));
		int yearInt = Integer.parseInt(inputDate.substring(inputDate.indexOf("/") + 1));

		calendarObj.set(yearInt, monthInt - 1, dayInt);
		int day = calendarObj.get(Calendar.DAY_OF_MONTH);
		int month = calendarObj.get(Calendar.MONTH) + 1;
		int year = calendarObj.get(Calendar.YEAR);

		//	Debug.println("<<source>> = " + dayInt + "/" + monthInt +"/" + yearInt  + " <<new>> = " + day + "/" + month +"/" + year);

		if (dayInt == day && monthInt == month && yearInt == year)
			return true;
		else
			return false;

	}

	/**
	 * Check OS
	 */
	public static String checkOs() throws Exception {
		String code = null;
		String osName = System.getProperty("os.name");
		if (osName.equals("Windows NT")) {
			code = "TIS620";
		} else
			if (osName.equals("Solaris")) {
				code = "TIS620";
			} else
				code = "TIS620";
		return code;
	}

	/**
	 *  Convert byte  to string
	 * @param inputstr the String value
	 */
	public static String cnvByteToString(String inputstr) throws Exception {
		String code = checkOs();
		String outputstr = null;
		String osName = System.getProperty("os.name");
		try {
			if (osName.equals("Solaris")) {
				byte[] b = inputstr.getBytes(code);
				outputstr = new String(b);
			} else {
				outputstr = inputstr;
			}
		} catch (java.io.UnsupportedEncodingException e) {
			//log.debug("Unsupported Encoding");
		}
		return outputstr;
	}

	public static double cnvStringToNumber(String input){
		String outputStr="0";
		if (( input != null) && (input.length()>0 ))
			outputStr = replaceStrWithStr(input,",","");
		return Double.parseDouble(outputStr);
	}

//	 for separate input string follow by input  and add to return vector
	public static String replaceStrWithStr(
		String inputData,
		String originalStr,
		String replaceStr) {

		StringBuffer outputData = new StringBuffer();
		if (inputData.length() > 0) {
			while (inputData.length() > 0) {
				int index = inputData.indexOf(originalStr);
				if (index == -1)
					return outputData.append(inputData).toString();
				else {
					outputData.append(inputData.substring(0, index));
					outputData.append(replaceStr);
				}
				inputData = inputData.substring(index + originalStr.length());
			}
		}
		return outputData.toString();
	}

	public static String convertToUnicode(String strToUnicode){
		if(strToUnicode == null) return "";
		StringBuffer sbStrToUnicode = new StringBuffer();
		char[] characters = strToUnicode.toCharArray();
		for (int i = 0; i < characters.length; i++) {
			char c = characters[i];
			if (c < 128 && c != 38)  sbStrToUnicode.append(c);
			else if (c == 128) sbStrToUnicode.append("&");
			else sbStrToUnicode.append("&#" + (int) c + ";");

			//sbStrToUnicode.append("&#" + (int) c);
			//sbStrToUnicode.append(";");
		}

		return sbStrToUnicode.toString();
	}

	public static String convertTimestampToString(Timestamp t) {
		// t.toString() should be: "yyyy-mm-dd hh:mm:ss.nanoseconds"
		String tOld = t.toString();

		if (tOld.length() < 21)
			return "";

		String tNew = tOld.substring(8, 10) + "/"
				+ tOld.substring(5, 7) + "/"
				+ tOld.substring(0, 4) + " "
				+ tOld.substring(11, 13) + ":"
				+ tOld.substring(14, 16) + ":"
				+ tOld.substring(17, 19);

		return tNew;
	}
	public static String toUnitbytes(long bytes) {
		Locale currentLocale = new Locale("en", "US");

		NUMBER_FORMAT0 = NumberFormat.getNumberInstance(currentLocale);
        NUMBER_FORMAT0.setMaximumFractionDigits(0);
        NUMBER_FORMAT0.setMinimumFractionDigits(0);
        NUMBER_FORMAT0.setGroupingUsed(true);

        NUMBER_FORMAT1 = NumberFormat.getNumberInstance(currentLocale);
        NUMBER_FORMAT1.setMaximumFractionDigits(1);
        NUMBER_FORMAT1.setMinimumFractionDigits(1);
        NUMBER_FORMAT1.setGroupingUsed(true);


        if (bytes < 0) {
            return "? " + GENERAL_UNIT_KILOBYTES;
        }
        long   unitValue; // the multiple associated with the unit
        String unitName;  // one of localizable units
        if (bytes < 0xA00000) {                // below 10MB, use KB
            unitValue = 0x400;
            unitName = GENERAL_UNIT_KILOBYTES;
        } else if (bytes < 0x280000000L) {     // below 10GB, use MB
            unitValue = 0x100000;
            unitName = GENERAL_UNIT_MEGABYTES;
        } else if (bytes < 0xA0000000000L) {   // below 10TB, use GB
            unitValue = 0x40000000;
            unitName = GENERAL_UNIT_GIGABYTES;
        } else {                                // at least 10TB, use TB
            unitValue = 0x10000000000L;
            unitName = GENERAL_UNIT_TERABYTES;
        }
        NumberFormat numberFormat; // one of localizable formats
        if ((double)bytes * 100 / unitValue < 99995)
            // return a minimum "100.0xB", and maximum "999.9xB"
            numberFormat = NUMBER_FORMAT1; // localized "#,##0.0"
        else
            // return a minimum "1,000xB"
            numberFormat = NUMBER_FORMAT0; // localized "#,##0"
        try {
            return numberFormat.format((double)bytes / unitValue) + " " + unitName;
        } catch(ArithmeticException ae) {
            return "0 " + unitName;
            // internal java error, just return 0.
        }
    }

	public static boolean validateEmail(String email) throws Exception {
		boolean isValid = false;
		//Initialize reg ex for email.
		String expression = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";
		CharSequence inputStr = email;
		//Make the comparison case-insensitive.
		Pattern pattern = Pattern.compile(expression,Pattern.CASE_INSENSITIVE);
		Matcher matcher = pattern.matcher(inputStr);
		if(matcher.matches()){
			isValid = true;
		}
		return isValid;
	}

	public static Timestamp cvtDateTimeForDBWithTime(String sDateValue,String timeValue, String locale, String time){
		if(sDateValue == null || sDateValue.equals("")) return null;

		if(sDateValue.indexOf(" ") > 0)	sDateValue = sDateValue.substring(0, sDateValue.indexOf(" "));
		if(timeValue == null || timeValue.length()==0){
			/*Date nDate = new Date();
			SimpleDateFormat sTime = new SimpleDateFormat("HH:mm:ss");
			timeValue = sTime.format(nDate);*/
			SimpleDateFormat sTime = new SimpleDateFormat("HH:mm:ss");
			Date nDate = new Date(sDateValue+" "+time);
			timeValue=sTime.format(nDate);
		}else{
			timeValue = timeValue.replace('.', ':');
			SimpleDateFormat sTime = new SimpleDateFormat("HH:mm:ss");
			Date nDate = new Date(sDateValue+" "+timeValue);
			timeValue=sTime.format(nDate);
		}
		Date dDateLocal = new Date();
		try {
			if(locale.equals("th_TH")){
				dDateLocal= cnvStringToDate(dateStringBE2AD(sDateValue,"dd/MM/yyyy")+" "+timeValue);
			}else{
				dDateLocal= cnvStringToDate(sDateValue +" "+timeValue);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		//Locale.setDefault(Locale.US);
		SimpleDateFormat sDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss" ,Locale.US);
		Timestamp tDate = Timestamp.valueOf(sDateFormat.format(dDateLocal));
		return tDate;
	}

	public static JSONObject requestParamsToJSON(ServletRequest req) throws Exception {
		JSONObject jsonObj = new JSONObject();
		Map<String, String[]> params = req.getParameterMap();
		for (Map.Entry<String, String[]> entry : params.entrySet()) {
			String v[] = entry.getValue();
			Object o = (v.length == 1) ? v[0] : v;
			jsonObj.put(entry.getKey(), o);
		}
		return jsonObj;
	}
}
