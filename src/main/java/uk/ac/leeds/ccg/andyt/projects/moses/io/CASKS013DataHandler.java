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

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.RandomAccessFile;
import java.io.StreamTokenizer;
import java.util.HashMap;
import java.util.Random;
import java.util.TreeMap;
import uk.ac.leeds.ccg.andyt.generic.core.Generic_ErrorAndExceptionHandler;
import uk.ac.leeds.ccg.andyt.generic.io.Generic_StaticIO;

/**
 * A <code>class</code> for handling an individual
 * <code>CASKS013DataRecord</code> and collections of
 * <code>CASKS013DataRecords</code>.
 * <ul>
 * <li>Used for formatting source data and testing it can be loaded to and
 * retrieved from a formatted file.</li>
 * <li>Developed for <a href="http://www.ncess.ac.uk/moses">MoSeS</a>.</li>
 * <li>Copyright (C) 2006 <a
 * href="http://www.geog.leeds.ac.uk/people/a.turner/">Andy Turner</a>, <a
 * href="http://www.leeds.ac.uk//">University of Leeds</a>.</li>
 * </ul>
 * 
 * @author <a href="http://www.geog.leeds.ac.uk/people/a.turner/">Andy
 *         Turner</a>
 * @version 1.0.0, 2006-08-10
 * @see AbstractCASDataRecord
 */
public class CASKS013DataHandler extends AbstractCASDataHandler {

    /**
     * Creates a new instance of <code>CASKS013DataHandler</code> for handling
     * CASKS013DataRecords stored in a formatted <code>File</code> The default
     * <code>File</code> is hard coded. To specify the <code>File</code> use
     * <code>CASKS013DataHandler(File)</code>. To set a different default
     * <code>File</code> edit the source code and recompile.
     * @throws java.io.IOException
     */
    public CASKS013DataHandler() throws IOException {
        // this( new File(
        // "C:/Work/Projects/MoSeS/Workspace/CASKS013DataRecords.dat" ) );
        // Want also to setDirectory();
        // initMemoryReserve();
        // Default this.directory, this.file, this.randomAccessFile
        File directory = new File("C:/Work/Projects/MoSeS/Workspace/");
        this.init(directory);
        this._File = new File(directory, "CASKS013DataRecords.dat");
        if (!this._File.exists()) {
            this._File.createNewFile();
        }
        this._RecordLength = new CASKS013DataRecord().getSizeInBytes();
        // System.out.println("this.recordLength " + this.recordLength);
        this._RandomAccessFile = new RandomAccessFile(this._File, "r");
    }

    /**
     * Creates a new instance of CASKS013DataHandler with Records loaded from
     * formattedFile.
     *
     * @param formattedFile
     *            Formatted file of CASKS013DataRecords
     * @throws java.io.IOException
     */
    public CASKS013DataHandler(File formattedFile) throws IOException {
        // initMemoryReserve();
        this.init(formattedFile.getParentFile());
        this._RecordLength = new CASKS013DataRecord().getSizeInBytes();
        load(formattedFile);
        System.out.println("CASKS013DataRecords loaded successfully");
    }

    /**
     * @param args
     *            the command line arguments No arguments are used.
     * @throws java.io.IOException
     */
    public static void main(String[] args) throws IOException {
        CASKS013DataHandler aCASKS013DataHandler = new CASKS013DataHandler();
        aCASKS013DataHandler.run();
    }

    /**
     * Top level run method
     */
    private void run() throws IOException {
        // run1( true, 20 );
        runAggregate();
        run2(20);
    }

    /**
     * Aggregates <code>CASKS013DataRecords</code>
     */
    private void runAggregate() throws IOException {
        // Aggregate to MSOA for Leeds
        long nDataRecords = this.getNDataRecords();
        long nrecordsInLeeds = 2439L;
        long startRecordIDForLeeds = 56749L;
        long startRecordID = startRecordIDForLeeds;
        long endRecordID = startRecordIDForLeeds + nrecordsInLeeds;
        File _File = new File(
                "C:/Work/Projects/MoSeS/Workspace/Leeds/CASKS013DataRecordsMSOA.dat");
        _File.createNewFile();
        RandomAccessFile _RandomAccessFile = new RandomAccessFile(_File, "rw");
        aggregateOAToMSOA(_RandomAccessFile, startRecordID, endRecordID);
        _RandomAccessFile.close();
        CASKS013DataHandler _CASKS013DataHandler = new CASKS013DataHandler(
                _File);
        nDataRecords = _CASKS013DataHandler.getNDataRecords();
        System.out.println("nDataRecords " + nDataRecords);
        CASKS013DataRecord _CASKS013DataRecord = new CASKS013DataRecord();
        _File = new File(
                "C:/Work/Projects/MoSeS/Workspace/Leeds/CASKS013DataRecordsMSOA.csv");
        PrintWriter _PrintWriter = new PrintWriter(_File);
        _PrintWriter.println(_CASKS013DataRecord.toCSVStringFields());
        for (long along = 0L; along < nDataRecords; along++) {
            _CASKS013DataRecord = _CASKS013DataHandler.getCASKS013DataRecord(along);
            _PrintWriter.println(_CASKS013DataRecord.toCSVString());
            System.out.println(_CASKS013DataRecord.toString());
        }
        _PrintWriter.flush();
        _PrintWriter.close();
    }

    /**
     * Loads <code>CASKS013DataRecords</code> and prints out n randomly
     *
     * @param loadFromSource
     *            If true, data is loaded from hardcoded source files as
     *            downloaded from casweb. Otherwise, data is loaded from
     *            <code>this.formattedFile</code>
     * @param n
     *            The number of loaded data records to print out.
     */
    private void run2(int n) throws IOException {
        File file = new File(
                "C:/Work/Projects/MoSeS/Workspace/Leeds/CASKS013DataRecordsMSOA.dat");
        load(file);
        print(n, new Random());
    }

    /**
     * Loads <code>CAS001DataRecords</code> and prints out n randomly
     * @param directory to load source data from
     * @param n the number of loaded data records to print out.
     * @throws java.io.IOException
     */
    protected void formatSourceData(
            File directory,
            int n)
            throws IOException {
        _RandomAccessFile = new RandomAccessFile(this._File, "rw");
        // Load from source
        File infile;
        long long0 = 0L;
        long RecordID = 0L;
        // Load England
        infile = new File(
                directory,
                "KS013EnglandOA.csv");
        RecordID = format(infile, RecordID, "England");
        System.out.println(infile.toString() + " formatted successfully " + RecordID + " records"); // 165665
        long0 = RecordID;
        // Load Wales
        infile = new File(
                directory,
                "KS013WalesOA.csv");
        RecordID = format(infile, RecordID, "Wales");
        System.out.println(infile.toString() + " formatted successfully " + (RecordID - long0) + " records"); // 9769
        long0 = RecordID;
        // Load Scotland
        infile = new File(
                directory,
                "/KS013ScotlandOA.csv");
        RecordID = format(infile, RecordID, "Scotland");
        System.out.println(infile.toString() + " formatted successfully " + (RecordID - long0) + " records"); // 42604
        long0 = RecordID;
        // Load Northern Ireland
        infile = new File(
                directory,
                "KS013NorthernIrelandOA.csv");
        RecordID = format(infile, RecordID, "Northern Ireland");
        System.out.println(infile.toString() + " formatted successfully " + (RecordID - long0) + " records"); // 5022
        _RandomAccessFile.close();
        print(20, new Random());
    }

    /**
     * Uses a <code>BufferedReader<code> and a <code>StreamTokenizer</code> to
     * read lines from the sourceFile <code>File</code>. The lines are converted
     * to <code>CASKS013DataRecords</code> and written to
     * <code>this.tRandomAccessFile</code>.
     *
     * @param sourceFile
     *            The source CASKS013DataRecords file to be formatted and
     *            written to <code>this.tRandomAccessFile</code>.
     * @param RecordID
     *            The <code>RecordID</code> to assign to the first
     *            <code>CASKS013DataRecord</code>.
     * @param country
     *            Identifies type of table
     *            <ul>
     *            <li>country.equalsIgnoreCase("Northern Ireland") treated as
     *            Northern Ireland CASKS013DataRecord</li>
     *            <li>country.equalsIgnoreCase("Scotland") treated as Scotland
     *            CASKS013DataRecord</li>
     *            <li>country.equalsIgnoreCase("England") treated as England
     *            CASKS013DataRecord</li>
     *            <li>country.equalsIgnoreCase("Wales") treated as Wales
     *            CASKS013DataRecord</li>
     *            </ul>
     * @return The <code>RecordID</code> assigned to the last
     *         <code>CASKS013DataRecords</code>.
     * @throws java.io.IOException
     */
    protected long format(File sourceFile, long RecordID, String country)
            throws IOException {
        System.out.println("format( File( " + sourceFile.toString() + " ), RecordID( " + RecordID + " ))");
        BufferedReader aBufferedReader = new BufferedReader(
                new InputStreamReader(new FileInputStream(sourceFile)));
        StreamTokenizer aStreamTokenizer = new StreamTokenizer(aBufferedReader);
        Generic_StaticIO.setStreamTokenizerSyntax1(aStreamTokenizer);
        String string0 = new String();
        String string1;
        String string2;
        long long0;
        long longZero = 0L;
        CASKS013DataRecord aCASKS013DataRecord = new CASKS013DataRecord();
        boolean print = false;
        int int10000 = 10000;
        // Skip the first line
        int tokenType = aStreamTokenizer.nextToken();
        while (tokenType != StreamTokenizer.TT_EOL) {
            tokenType = aStreamTokenizer.nextToken();
        }
        tokenType = aStreamTokenizer.nextToken();
        while (tokenType != StreamTokenizer.TT_EOF) {
            switch (tokenType) {
                case StreamTokenizer.TT_EOL:
                    long0 = RecordID % int10000;
                    print = (long0 == longZero);
                    if (print) {
                        string2 = aCASKS013DataRecord.toString();
                        System.out.println(string2);
                        string2 = string0;
                    }
                    // Write out
                    aCASKS013DataRecord.write(_RandomAccessFile);
                    RecordID++;
                    break;
                case StreamTokenizer.TT_WORD:
                    string1 = aStreamTokenizer.sval;
                    aCASKS013DataRecord = new CASKS013DataRecord(RecordID, string1,
                            country);
                    break;
            }
            string1 = string0;
            tokenType = aStreamTokenizer.nextToken();
        }
        System.out.println("Number of Records loaded = " + RecordID);
        return RecordID;
    }

    /**
     * @return a <code>CASKS013DataRecord</code> with
     *         <code>AbstractCASDataRecord.RecordID = RecordID</code>
     * @param RecordID
     *            The RecordID of the CASKS013DataRecord to be returned.
     */
    public AbstractCASDataRecord getDataRecord(long RecordID) {
        return getCASKS013DataRecord(RecordID);
    }

    /**
     * @return a <code>CASKS013DataRecord</code> with
     *         <code>CASKS013DataRecord.RecordID = RecordID</code>
     * @param RecordID
     *            The RecordID of the CASKS013DataRecord to be returned.
     */
    public CASKS013DataRecord getCASKS013DataRecord(long RecordID) {
        CASKS013DataRecord result = null;
        try {
            this._RandomAccessFile.seek(_RecordLength * RecordID);
            result = new CASKS013DataRecord(this._RandomAccessFile);
        } catch (IOException aIOException) {
            System.err.println(aIOException.getLocalizedMessage());
            System.exit(Generic_ErrorAndExceptionHandler.IOException);
        }
        return result;
    }

    /**
     * Aggregates <code>CASKS013DataRecords</code> from OA To Ward for the OA
     * records in the range [startRecordID,endRecordID] and writes the results
     * to aRandomAccessFile
     *
     * @param aRandomAccessFile
     *            <code>RandomAccessFile</code> to which results are written
     * @param startRecordID
     *            The first OA RecordID in the sequence to be aggregated.
     * @param endRecordID
     *            The last OA RecordID in the sequence to be aggregated.
     * @throws java.io.IOException
     */
    public void aggregateOAToWard(RandomAccessFile aRandomAccessFile,
            long startRecordID, long endRecordID) throws IOException {
        TreeMap result = new TreeMap();
        CASKS013DataRecord aCASKS013DataRecord;
        CASKS013DataRecord bCASKS013DataRecord;
        String zoneCode;
        Object zoneCodeWard;
        // long newRecordID = startRecordIDForLeeds - 1L;
        long newRecordID = -1L;
        for (long RecordID = startRecordID; RecordID < endRecordID; RecordID++) {
            aCASKS013DataRecord = (CASKS013DataRecord) getDataRecord(RecordID);
            zoneCode = new String(aCASKS013DataRecord.getZone_Code());
            zoneCodeWard = zoneCode.substring(0, 6);
            if (result.containsKey(zoneCodeWard)) {
                bCASKS013DataRecord = (CASKS013DataRecord) result.get(zoneCodeWard);
                result.remove(zoneCodeWard);
                result.put(zoneCodeWard, aCASKS013DataRecord.aggregate(bCASKS013DataRecord));
            } else {
                result.put(zoneCodeWard, aCASKS013DataRecord);
            }
        }
        write(aRandomAccessFile, result);
    }

    /**
     * Aggregates <code>CASKS013DataRecords</code> from OA To MSOA for the OA
     * records in the range [startRecordID,endRecordID] and writes the results
     * to aRandomAccessFile
     *
     * @param aRandomAccessFile
     *            <code>RandomAccessFile</code> to which results are written
     * @param startRecordID
     *            The first OA RecordID in the sequence to be aggregated.
     * @param endRecordID
     *            The last OA RecordID in the sequence to be aggregated.
     * @throws java.io.IOException
     */
    public void aggregateOAToMSOA(RandomAccessFile aRandomAccessFile,
            long startRecordID, long endRecordID) throws IOException {
        TreeMap result = new TreeMap();
        HashMap lookUpMSOAfromOAHashMap = get_LookUpMSOAfromOAHashMap();
        CASKS013DataRecord aCASKS013DataRecord;
        CASKS013DataRecord bCASKS013DataRecord;
        String zoneCode;
        Object zoneCodeMSOA;
        // long newRecordID = startRecordIDForLeeds - 1L;
        long newRecordID = -1L;
        for (long RecordID = startRecordID; RecordID < endRecordID; RecordID++) {
            aCASKS013DataRecord = (CASKS013DataRecord) getDataRecord(RecordID);
            zoneCode = new String(aCASKS013DataRecord.getZone_Code());
            zoneCodeMSOA = lookUpMSOAfromOAHashMap.get(zoneCode);
            if (result.containsKey(zoneCodeMSOA)) {
                bCASKS013DataRecord = (CASKS013DataRecord) result.get(zoneCodeMSOA);
                result.remove(zoneCodeMSOA);
                result.put(zoneCodeMSOA, aCASKS013DataRecord.aggregate(bCASKS013DataRecord));
            } else {
                result.put(zoneCodeMSOA, aCASKS013DataRecord);
            }
        }
        write(aRandomAccessFile, result);
    }
}