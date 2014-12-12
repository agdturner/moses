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
import java.io.RandomAccessFile;
import java.io.StreamTokenizer;
import java.util.HashMap;
import java.util.Random;
import java.util.TreeMap;
import uk.ac.leeds.ccg.andyt.generic.core.Generic_ErrorAndExceptionHandler;
import uk.ac.leeds.ccg.andyt.generic.io.Generic_StaticIO;

/**
 * A <code>class</code> for handling an individual
 * <code>CASKS015DataRecord</code> and collections of
 * <code>CASKS015DataRecords</code>.
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
public class CASKS015DataHandler extends AbstractCASDataHandler {

    /**
     * Creates a new instance of <code>CASKS015DataHandler</code> for handling
     * CASKS015DataRecords stored in a formatted <code>File</code> The default
     * <code>File</code> is hard coded. To specify the <code>File</code> use
     * <code>CASKS015DataHandler(File)</code>. To set a different default
     * <code>File</code> edit the source code and recompile.
     * @throws java.io.IOException
     */
    public CASKS015DataHandler() throws IOException {
        // this( new File(
        // "C:/Work/Projects/MoSeS/Workspace/CASKS015DataRecords.dat" ) );
        // Want also to setDirectory();
        // initMemoryReserve();
        // Default this.directory, this.file, this.randomAccessFile
        File directory = new File("C:/Work/Projects/MoSeS/Workspace/");
        this.init(directory);
        this._File = new File(directory, "CASKS015DataRecords.dat");
        if (!this._File.exists()) {
            this._File.createNewFile();
        }
        this._RecordLength = new CASKS015DataRecord().getSizeInBytes();
        // log("this.recordLength " + this.recordLength);
        this._RandomAccessFile = new RandomAccessFile(this._File, "r");
    }

    /**
     * Creates a new instance of CASKS015DataHandler with Records loaded from
     * formattedFile.
     *
     * @param formattedFile
     *            Formatted file of CASKS015DataRecords
     */
    public CASKS015DataHandler(File formattedFile) {
        // initMemoryReserve();
        this.init(formattedFile.getParentFile());
        this._RecordLength = new CASKS015DataRecord().getSizeInBytes();
        load(formattedFile);
        log("CASKS015DataRecords loaded successfully");
    }

    /**
     * @param args
     *            the command line arguments No arguments are used.
     * @throws java.io.IOException
     */
    public static void main(String[] args) throws IOException {
        CASKS015DataHandler aCASKS015DataHandler = new CASKS015DataHandler();
        aCASKS015DataHandler.run();
    }

    /**
     * Top level run method
     */
    private void run() throws IOException {
        // run1( true, 20 );
        // run1( false, 20 );
        run2(20);
    }

    /**
     * Loads <code>CASKS015DataRecords</code> and prints out n randomly
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
                "C:/Work/Projects/MoSeS/Workspace/Leeds/CASKS015DataRecordsMSOA.dat");
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
                "KS015EnglandOA.csv");
        RecordID = format(infile, RecordID, "England");
        log(infile.toString() + " formatted successfully " + RecordID + " records"); // 165665
        long0 = RecordID;
        // Load Wales
        infile = new File(
                directory,
                "KS015WalesOA.csv");
        RecordID = format(infile, RecordID, "Wales");
        log(infile.toString() + " formatted successfully " + (RecordID - long0) + " records"); // 9769
        long0 = RecordID;
        // Load Scotland
        infile = new File(
                directory,
                "KS015ScotlandOA.csv");
        RecordID = format(infile, RecordID, "Scotland");
        log(infile.toString() + " formatted successfully " + (RecordID - long0) + " records"); // 42604
        long0 = RecordID;
        // Load Northern Ireland
        infile = new File(
                directory,
                "KS015NorthernIrelandOA.csv");
        RecordID = format(infile, RecordID, "Northern Ireland");
        log(infile.toString() + " formatted successfully " + (RecordID - long0) + " records"); // 5022
        _RandomAccessFile.close();
        load(_File);
        print(20, new Random());
    }

    /**
     * Uses a <code>BufferedReader<code> and a <code>StreamTokenizer</code> to
     * read lines from the sourceFile <code>File</code>. The lines are converted
     * to <code>CASKS015DataRecords</code> and written to
     * <code>this.tRandomAccessFile</code>.
     *
     * @param sourceFile
     *            The source CASKS015DataRecords file to be formatted and
     *            written to <code>this.tRandomAccessFile</code>.
     * @param RecordID
     *            The <code>RecordID</code> to assign to the first
     *            <code>CASKS015DataRecord</code>.
     * @param country
     *            Identifies type of table
     *            <ul>
     *            <li>country.equalsIgnoreCase("Northern Ireland") treated as
     *            Northern Ireland CASKS015DataRecord</li>
     *            <li>country.equalsIgnoreCase("Scotland") treated as Scotland
     *            CASKS015DataRecord</li>
     *            <li>country.equalsIgnoreCase("England") treated as England
     *            CASKS015DataRecord</li>
     *            <li>country.equalsIgnoreCase("Wales") treated as Wales
     *            CASKS015DataRecord</li>
     *            </ul>
     * @return The <code>RecordID</code> assigned to the last
     *         <code>CASKS015DataRecords</code>.
     * @throws java.io.IOException
     */
    protected long format(File sourceFile, long RecordID, String country)
            throws IOException {
        log("format( File( " + sourceFile.toString() + " ), RecordID( " + RecordID + " ))");
        BufferedReader aBufferedReader = new BufferedReader(
                new InputStreamReader(new FileInputStream(sourceFile)));
        StreamTokenizer aStreamTokenizer = new StreamTokenizer(aBufferedReader);
        Generic_StaticIO.setStreamTokenizerSyntax1(aStreamTokenizer);
        String string0 = new String();
        String string1;
        String string2;
        long long0;
        long longZero = 0L;
        CASKS015DataRecord aCASKS015DataRecord = new CASKS015DataRecord();
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
                        string2 = aCASKS015DataRecord.toString();
                        log(string2);
                        string2 = string0;
                    }
                    // Write out
                    aCASKS015DataRecord.write(_RandomAccessFile);
                    RecordID++;
                    break;
                case StreamTokenizer.TT_WORD:
                    string1 = aStreamTokenizer.sval;
                    aCASKS015DataRecord = new CASKS015DataRecord(RecordID, string1,
                            country);
                    break;
            }
            string1 = string0;
            tokenType = aStreamTokenizer.nextToken();
        }
        log("Number of Records loaded = " + RecordID);
        return RecordID;
    }

    /**
     * @return a <code>CASKS015DataRecord</code> with
     *         <code>AbstractCASDataRecord.RecordID = RecordID</code>
     * @param RecordID
     *            The RecordID of the CASKS015DataRecord to be returned.
     */
    public AbstractCASDataRecord getDataRecord(long RecordID) {
        return getCASKS015DataRecord(RecordID);
    }

    /**
     * @return a <code>CASKS015DataRecord</code> with
     *         <code>CASKS015DataRecord.RecordID = RecordID</code>
     * @param RecordID
     *            The RecordID of the CASKS015DataRecord to be returned.
     */
    public CASKS015DataRecord getCASKS015DataRecord(long RecordID) {
        CASKS015DataRecord result = null;
        try {
            this._RandomAccessFile.seek(_RecordLength * RecordID);
            result = new CASKS015DataRecord(this._RandomAccessFile);
        } catch (IOException aIOException) {
            log(aIOException.getLocalizedMessage());
            System.exit(Generic_ErrorAndExceptionHandler.IOException);
        }
        return result;
    }

    /**
     * Aggregates <code>CASKS015DataRecords</code> from OA To Ward for the OA
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
        CASKS015DataRecord aCASKS015DataRecord;
        CASKS015DataRecord bCASKS015DataRecord;
        String zoneCode;
        Object zoneCodeWard;
        // long newRecordID = startRecordIDForLeeds - 1L;
        long newRecordID = -1L;
        for (long RecordID = startRecordID; RecordID < endRecordID; RecordID++) {
            aCASKS015DataRecord = (CASKS015DataRecord) getDataRecord(RecordID);
            zoneCode = new String(aCASKS015DataRecord.getZone_Code());
            zoneCodeWard = zoneCode.substring(0, 6);
            if (result.containsKey(zoneCodeWard)) {
                bCASKS015DataRecord = (CASKS015DataRecord) result.get(zoneCodeWard);
                result.remove(zoneCodeWard);
                result.put(zoneCodeWard, aCASKS015DataRecord.aggregate(bCASKS015DataRecord));
            } else {
                result.put(zoneCodeWard, aCASKS015DataRecord);
            }
        }
        write(aRandomAccessFile, result);
    }

    /**
     * Aggregates <code>CASKS015DataRecords</code> from OA To MSOA for the OA
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
        CASKS015DataRecord aCASKS015DataRecord;
        CASKS015DataRecord bCASKS015DataRecord;
        String zoneCode;
        Object zoneCodeMSOA;
        // long newRecordID = startRecordIDForLeeds - 1L;
        long newRecordID = -1L;
        for (long RecordID = startRecordID; RecordID < endRecordID; RecordID++) {
            aCASKS015DataRecord = (CASKS015DataRecord) getDataRecord(RecordID);
            zoneCode = new String(aCASKS015DataRecord.getZone_Code());
            zoneCodeMSOA = lookUpMSOAfromOAHashMap.get(zoneCode);
            if (result.containsKey(zoneCodeMSOA)) {
                bCASKS015DataRecord = (CASKS015DataRecord) result.get(zoneCodeMSOA);
                result.remove(zoneCodeMSOA);
                result.put(zoneCodeMSOA, aCASKS015DataRecord.aggregate(bCASKS015DataRecord));
            } else {
                result.put(zoneCodeMSOA, aCASKS015DataRecord);
            }
        }
        write(aRandomAccessFile, result);
    }
}