/**
 * A component of a library for
 * <a href="http://www.geog.leeds.ac.uk/people/a.turner/projects/MoSeS">MoSeS</a>.
 *
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2.1 of the License, or (at your option) any later version.
 *
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307  USA.
 */
package uk.ac.leeds.ccg.andyt.projects.moses.io;

import uk.ac.leeds.ccg.andyt.census.core.Census_AbstractDataRecord;
import uk.ac.leeds.ccg.andyt.census.core.Census_AbstractDataHandler;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.RandomAccessFile;
import java.io.StreamTokenizer;
import java.util.HashMap;
import java.util.Iterator;
import java.util.StringTokenizer;
import uk.ac.leeds.ccg.andyt.generic.core.Generic_ErrorAndExceptionHandler;
import uk.ac.leeds.ccg.andyt.generic.io.Generic_IO;

/**
 * A specialist handler for accessing CASKS002Records and information about the
 * collection.
 */
public class ToyModelHSARDataHandler extends Census_AbstractDataHandler {

	/**
	 * A reference to a FileOutputStream for writing data
	 */
	public HashMap _ZoneCodeToRecordIDStartEndLookup;

	/** Creates a new instance of ToyModelDataHandler */
	public ToyModelHSARDataHandler() {
		this._RecordLength = new ToyModelHSARDataRecord().getSizeInBytes();
	}

	/**
	 * Creates a new ToyModelDataHandler for Records loaded from _File.
	 * 
	 * @param _File
	 *            Formatted File of ToyModelDataRecords
     * @throws java.io.IOException
	 */
	public ToyModelHSARDataHandler(File _File) throws IOException {
		if (_File.toString().endsWith("ThisFile")) {
			ToyModelHSARDataHandler _ToyModelHSARDataHandler = (ToyModelHSARDataHandler) Generic_IO.readObject(_File);
			load(_ToyModelHSARDataHandler._File);
			this._RecordLength = _ToyModelHSARDataHandler._RecordLength;
			// this.recordLength = new
			// ToyModelHSARDataRecord().getSizeInBytes();
			this._LookUpMSOAfromOAHashMap = _ToyModelHSARDataHandler._LookUpMSOAfromOAHashMap;
			this._RecordIDZoneCodeHashMap = _ToyModelHSARDataHandler._RecordIDZoneCodeHashMap;
			this._ZoneCodeToRecordIDStartEndLookup = _ToyModelHSARDataHandler._ZoneCodeToRecordIDStartEndLookup;
		} else {
			init(_File.getParentFile());
			load(_File);
			this._RecordLength = new ToyModelDataRecord_1().getSizeInBytes();
		}
		System.out.println("" + _File + " loaded successfully");
	}

	/**
	 * @param args
	 *            the command line arguments
	 */
	public static void main(String[] args) {
		try {
			ToyModelHSARDataHandler a_ToyModelHSARDataHandler = new ToyModelHSARDataHandler();
			a_ToyModelHSARDataHandler.run(args);
		} catch (Exception e) {
			e.printStackTrace();
		} catch (Error e) {
			e.printStackTrace();
		}
	}

	/**
	 * Highest level controller for processing runs
	 */
	private void run(String[] args) throws Exception {
		if (args.length > 0) {
			File directory = new File(args[0]);
			if (directory.exists()) {
				format(args[0]);
			} else {
				throw new Exception();
			}
		} else {
			String directory = "C:/Work/Projects/MoSeS/data/ToyModel/";
			format(directory);
		}
		load();
	}

	/**
	 * Highest level controller for processing runs
     * @param directory
     * @throws java.io.IOException
	 */
	public void format(String directory) throws IOException {
		format(new File(directory, "LEEDS001.TXT"));
		format(new File(directory, "LEEDS015.TXT"));
		format(new File(directory, "LEEDS025.TXT"));
		format(new File(directory, "BIRMHM01.TXT"));
		format(new File(directory, "BIRMHM15.TXT"));
		format(new File(directory, "BIRMHM25.TXT"));
		format(new File(directory, "BRADFD01.TXT"));
		format(new File(directory, "BRADFD15.TXT"));
		format(new File(directory, "BRADFD25.TXT"));
		format(new File(directory, "LIVERP01.TXT"));
		format(new File(directory, "LIVERP15.TXT"));
		format(new File(directory, "LIVERP25.TXT"));
		format(new File(directory, "NEWCST01.TXT"));
		format(new File(directory, "NEWCST15.TXT"));
		format(new File(directory, "NEWCST25.TXT"));
		format(new File(directory, "MANCHR01.TXT"));
		format(new File(directory, "MANCHR15.TXT"));
		format(new File(directory, "MANCHR25.TXT"));
		format(new File(directory, "SHEFFD01.TXT"));
		format(new File(directory, "SHEFFD15.TXT"));
		format(new File(directory, "SHEFFD25.TXT"));
	}

	/**
	 * Highest level controller for processing runs
	 */
	private void load() throws IOException {
		String directory = "C:/Work/Projects/MoSeS/data/ToyModel/";
		ToyModelHSARDataHandler a_ToyModelHSARDataHandler = new ToyModelHSARDataHandler(
				new File(directory, "LEEDS001.TXT.ThisFile"));
		Iterator a_Iterator = a_ToyModelHSARDataHandler._ZoneCodeToRecordIDStartEndLookup
				.keySet().iterator();
		Object key;
		Object value;
		int[] t_RecordIDStartEnd;
		while (a_Iterator.hasNext()) {
			key = a_Iterator.next();
			value = a_ToyModelHSARDataHandler._ZoneCodeToRecordIDStartEndLookup
					.get(key);
			t_RecordIDStartEnd = (int[]) value;
			System.out.println("" + (String) key + " StartRecord "
					+ t_RecordIDStartEnd[0] + " endRecord "
					+ t_RecordIDStartEnd[1]);
		}
		ToyModelHSARDataRecord[] ward = a_ToyModelHSARDataHandler
				.getToyModelHSARDataRecords("00DAFA");
		for (int i = 0; i < ward.length; i++) {
			System.out.println(ward[i].toString());
		}
		// ToyModelHSARDataHandler a_ToyModelHSARDataHandler = new
	}

	/**
	 * Formats input.
	 * 
	 * @param a_ToyModelHSARDataRecordASCIIFile
	 *            File to format.
     * @throws java.io.IOException
	 */
	public void format(File a_ToyModelHSARDataRecordASCIIFile)
			throws IOException {
		// initMemoryReserve();
		this._RecordLength = new ToyModelHSARDataRecord().getSizeInBytes();
		this._File = new File(a_ToyModelHSARDataRecordASCIIFile.getParent(),
				a_ToyModelHSARDataRecordASCIIFile.getName() + ".DAT");
		this._RandomAccessFile = new RandomAccessFile(this._File, "rw");
		this._RandomAccessFile.seek(0);
		this._ZoneCodeToRecordIDStartEndLookup = new HashMap();
		BufferedReader tBufferedReader = new BufferedReader(
				new InputStreamReader(new FileInputStream(
						a_ToyModelHSARDataRecordASCIIFile)));
		StreamTokenizer tStreamTokenizer = new StreamTokenizer(tBufferedReader);
		Generic_IO.setStreamTokenizerSyntax1(tStreamTokenizer);
		long lineCount = 0L;
		ToyModelHSARDataRecord a_ToyModelHSARDataRecord = new ToyModelHSARDataRecord();
		boolean readHouseholdCodeLine = true;
		int personInHouseholdCount = 0;
		int numberOfPersonsInHousehold = 0;
		int RecordID = -1;
		int householdCount = 0;
		// char[] tab = new char[1];
		// tab[0] = '\t';
		// String tabString = new String( tab );
		// Read data
		int tokenType = tStreamTokenizer.nextToken();
		String zoneCode = null;
		String zoneCode0 = "";
		int[] t_RecordIDStartEnd = null;
		StringTokenizer aStringTokenizer = new StringTokenizer(
				tStreamTokenizer.sval);
		zoneCode0 = aStringTokenizer.nextToken(" ");
		personInHouseholdCount = 0;
		householdCount++;
		t_RecordIDStartEnd = new int[2];
		while (tokenType != StreamTokenizer.TT_EOF) {
			switch (tokenType) {
			case StreamTokenizer.TT_EOL:
				break;
			case StreamTokenizer.TT_WORD:
				aStringTokenizer = new StringTokenizer(tStreamTokenizer.sval);
				if (readHouseholdCodeLine) {
					numberOfPersonsInHousehold = Integer.valueOf(
							aStringTokenizer.nextToken(" ")).intValue();
					zoneCode = aStringTokenizer.nextToken(" ");
					personInHouseholdCount = 0;
					householdCount++;
					if (!zoneCode.equalsIgnoreCase(zoneCode0)) {
						t_RecordIDStartEnd[1] = RecordID;
						this._ZoneCodeToRecordIDStartEndLookup.put(zoneCode0,
								t_RecordIDStartEnd);
						t_RecordIDStartEnd = new int[2];
						t_RecordIDStartEnd[0] = RecordID + 1;
						zoneCode0 = zoneCode;
					}
					readHouseholdCodeLine = false;
				} else {
					RecordID++;
					a_ToyModelHSARDataRecord = new ToyModelHSARDataRecord(
							RecordID, zoneCode, aStringTokenizer);
					a_ToyModelHSARDataRecord.write(this._RandomAccessFile);
					// System.out.println("" +
					// this.tRandomAccessFile.getFilePointer() );
					personInHouseholdCount++;
					if (personInHouseholdCount == numberOfPersonsInHousehold) {
						readHouseholdCodeLine = true;
					}
				}
			default:
				break;
			}
			tokenType = tStreamTokenizer.nextToken();
		}
		tBufferedReader.close();
		System.out.println("Number of Households loaded = "
				+ (householdCount + 1));
		System.out.println("Number of Persons = " + (RecordID + 1));
		Generic_IO.writeObject(
                this,
                new File(a_ToyModelHSARDataRecordASCIIFile.getParent(), a_ToyModelHSARDataRecordASCIIFile.getName()
				+ ".ThisFile"));
	}

	// /**
	// * Loads formatted file and prints out some values
	// */
	// private void print( int n, Random t_Random ) {
	// try {
	// long length = this.tRandomAccessFile.length();
	// int numberOfRecords = ( int ) ( length / recordLength );
	// long RecordID;
	// ToyModelDataRecord_1 toyModelDataRecord = null;
	// for ( int i = 0; i < n; i ++ ) {
	// this.tRandomAccessFile.seek( ( long ) ( t_Random.nextInt( numberOfRecords
	// ) ) * recordLength );
	// toyModelDataRecord = new ToyModelDataRecord_1( this.tRandomAccessFile );
	// System.out.println( toyModelDataRecord.toString() );
	// }
	// } catch ( IOException ioe0 ) {
	// ioe0.printStackTrace();
	// }
	// }

	/**
	 * @param RecordID
	 *            The RecordID of the ISAR to be returned.
     * @return 
	 */
	public Census_AbstractDataRecord getDataRecord(long RecordID) {
		// return getCASKS002Record( RecordID );
		return new ToyModelDataRecord_1();
	}

	/**
     *
     * @param string
     * @return 
     */
	protected int parseInt(String string) {
		try {
			return Integer.valueOf(string);
		} catch (NumberFormatException nfe0) {
			return Integer.MIN_VALUE;
		}
	}

	/**
	 * @param RecordID
	 *            The RecordID of the ToyModelDataRecord to be returned.
     * @return 
	 */
	protected ToyModelHSARDataRecord getToyModelHSARDataRecord(long RecordID) {
		ToyModelHSARDataRecord result = null;
		try {
			this._RandomAccessFile.seek(this._RecordLength * RecordID);
			result = new ToyModelHSARDataRecord(this._RandomAccessFile);
		} catch (IOException aIOException) {
            log(aIOException.getLocalizedMessage());
            System.exit(Generic_ErrorAndExceptionHandler.IOException);
        }
		return result;
	}

	/**
	 * This is a convenience method for accessing all the records for a given
	 * zoneCode
     * @param zoneCode
     * @return 
     * @throws java.io.IOException 
	 */
	public ToyModelHSARDataRecord[] getToyModelHSARDataRecords(String zoneCode)
			throws IOException {
		int[] tRecordIDStartEnd = (int[]) this._ZoneCodeToRecordIDStartEndLookup
				.get(zoneCode);
		int nRecs = (tRecordIDStartEnd[1] - tRecordIDStartEnd[0]) + 1;
		ToyModelHSARDataRecord[] result = new ToyModelHSARDataRecord[nRecs];
		this._RandomAccessFile.seek(this._RecordLength
				* (long) tRecordIDStartEnd[0]);
		for (int i = tRecordIDStartEnd[0]; i < nRecs; i++) {
			result[i] = new ToyModelHSARDataRecord(this._RandomAccessFile);
			// result[ i ] = getToyModelHSARDataRecord( i );
		}
		return result;
	}

}