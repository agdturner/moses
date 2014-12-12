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
 * <code>CASKS12cDataRecord</code> and collections of
 * <code>CASKS12cDataRecords</code>.
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
public class CASKS12cDataHandler extends AbstractCASDataHandler {

    /**
     * Creates a new instance of <code>CASKS12cDataHandler</code> for handling
     * CASKS12cDataRecords stored in a formatted <code>File</code> The default
     * <code>File</code> is hard coded. To specify the <code>File</code> use
     * <code>CASKS12cDataHandler(File)</code>. To set a different default
     * <code>File</code> edit the source code and recompile.
     * @throws java.io.IOException
     */
    public CASKS12cDataHandler() throws IOException {
        // this( new File(
        // "C:/Work/Projects/MoSeS/Workspace/CASKS12cDataRecords.dat" ) );
        // Want also to setDirectory();
        // initMemoryReserve();
        // Default this.directory, this.file, this.randomAccessFile
        File directory = new File("C:/Work/Projects/MoSeS/Workspace/");
        this.init(directory);
        this._File = new File(directory, "CASKS12cDataRecords.dat");
        if (!this._File.exists()) {
            this._File.createNewFile();
        }
        this._RecordLength = new CASKS12cDataRecord().getSizeInBytes();
        // log("this.recordLength " + this.recordLength);
        this._RandomAccessFile = new RandomAccessFile(this._File, "r");
    }

    /**
     * Creates a new instance of CASKS12cDataHandler with Records loaded from
     * formattedFile.
     *
     * @param formattedFile
     *            Formatted file of CASKS12cDataRecords
     */
    public CASKS12cDataHandler(File formattedFile) {
        // initMemoryReserve();
        this.init(formattedFile.getParentFile());
        this._RecordLength = new CASKS12cDataRecord().getSizeInBytes();
        load(formattedFile);
        log("CASKS12cDataRecords loaded successfully");
    }

    /**
     * @param args
     *            the command line arguments No arguments are used.
     * @throws java.io.IOException
     */
    public static void main(String[] args) throws IOException {
        CASKS12cDataHandler aCASKS12cDataHandler = new CASKS12cDataHandler();
        aCASKS12cDataHandler.run();
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
     * Loads <code>CASKS12cDataRecords</code> and prints out n randomly
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
                "C:/Work/Projects/MoSeS/Workspace/Leeds/MSOA/CASKS12cDataRecords.dat");
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
                "KS12cEnglandOA.csv");
        RecordID = format(infile, RecordID);
        log(infile.toString() + " formatted successfully " + RecordID + " records"); // 165665
        long0 = RecordID;
        // Load Wales
        infile = new File(
                directory,
                "KS12cWalesOA.csv");
        RecordID = format(infile, RecordID);
        log(infile.toString() + " formatted successfully " + (RecordID - long0) + " records"); // 9769
        long0 = RecordID;
        // Load Scotland
        infile = new File(
                directory,
                "KS12cScotlandOA.csv");
        RecordID = format(infile, RecordID);
        log(infile.toString() + " formatted successfully " + (RecordID - long0) + " records"); // 42604
        long0 = RecordID;
        // Load Northern Ireland
        infile = new File(
                directory,
                "KS12cNorthernIrelandOA.csv");
        RecordID = format(infile, RecordID);
        log(infile.toString() + " formatted successfully " + (RecordID - long0) + " records"); // 5022
        _RandomAccessFile.close();
        load(_File);
        print(20, new Random());
    }

    /**
     * Uses a <code>BufferedReader<code> and a <code>StreamTokenizer</code> to
     * read lines from the sourceFile <code>File</code>. The lines are converted
     * to <code>CASKS12cDataRecords</code> and written to
     * <code>this.tRandomAccessFile</code>.
     *
     * @param sourceFile
     *            The source CASKS12cDataRecords file to be formatted and
     *            written to <code>this.tRandomAccessFile</code>.
     * @param RecordID
     *            The <code>RecordID</code> to assign to the first
     *            <code>CASKS12cDataRecord</code>.
     * @return The <code>RecordID</code> assigned to the last
     *         <code>CASKS12cDataRecords</code>.
     * @throws java.io.IOException
     */
    protected long format(File sourceFile, long RecordID) throws IOException {
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
        CASKS12cDataRecord aCASKS12cDataRecord = new CASKS12cDataRecord();
        boolean print = false;
        int int1000 = 1000;
        // Skip the first line
        int tokenType = aStreamTokenizer.nextToken();
        while (tokenType != StreamTokenizer.TT_EOL) {
            tokenType = aStreamTokenizer.nextToken();
        }
        tokenType = aStreamTokenizer.nextToken();
        while (tokenType != StreamTokenizer.TT_EOF) {
            switch (tokenType) {
                case StreamTokenizer.TT_EOL:
                    long0 = RecordID % int1000;
                    print = (long0 == longZero);
                    if (print) {
                        string2 = aCASKS12cDataRecord.toString();
                        log(string2);
                        string2 = string0;
                    }
                    // Write out
                    aCASKS12cDataRecord.write(_RandomAccessFile);
                    RecordID++;
                    break;
                case StreamTokenizer.TT_WORD:
                    string1 = aStreamTokenizer.sval;
                    aCASKS12cDataRecord = new CASKS12cDataRecord(RecordID, string1);
                    break;
            }
            string1 = string0;
            tokenType = aStreamTokenizer.nextToken();
        }
        log("Number of Records loaded = " + RecordID);
        return RecordID;
    }

    /**
     * @return a <code>CASKS12cDataRecord</code> with
     *         <code>AbstractCASDataRecord.RecordID = RecordID</code>
     * @param RecordID
     *            The RecordID of the CASKS12cDataRecord to be returned.
     */
    public AbstractCASDataRecord getDataRecord(long RecordID) {
        return getCASKS12cDataRecord(RecordID);
    }

    /**
     * @return a <code>CASKS12cDataRecord</code> with
     *         <code>CASKS12cDataRecord.RecordID = RecordID</code>
     * @param RecordID
     *            The RecordID of the CASKS12cDataRecord to be returned.
     */
    public CASKS12cDataRecord getCASKS12cDataRecord(long RecordID) {
        CASKS12cDataRecord result = null;
        try {
            this._RandomAccessFile.seek(_RecordLength * RecordID);
            result = new CASKS12cDataRecord(this._RandomAccessFile);
        } catch (IOException aIOException) {
            System.err.println(aIOException.getLocalizedMessage());
            System.exit(Generic_ErrorAndExceptionHandler.IOException);
        }
        return result;
    }

    /**
     * Aggregates <code>CASKS12cDataRecords</code> from OA To Ward for the OA
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
        CASKS12cDataRecord aCASKS12cDataRecord;
        CASKS12cDataRecord bCASKS12cDataRecord;
        String zoneCode;
        Object zoneCodeWard;
        // long newRecordID = startRecordIDForLeeds - 1L;
        long newRecordID = -1L;
        for (long RecordID = startRecordID; RecordID < endRecordID; RecordID++) {
            aCASKS12cDataRecord = (CASKS12cDataRecord) getDataRecord(RecordID);
            zoneCode = new String(aCASKS12cDataRecord.getZone_Code());
            zoneCodeWard = zoneCode.substring(0, 6);
            if (result.containsKey(zoneCodeWard)) {
                bCASKS12cDataRecord = (CASKS12cDataRecord) result.get(zoneCodeWard);
                result.remove(zoneCodeWard);
                result.put(zoneCodeWard, aCASKS12cDataRecord.aggregate(bCASKS12cDataRecord));
            } else {
                result.put(zoneCodeWard, aCASKS12cDataRecord);
            }
        }
        write(aRandomAccessFile, result);
    }

    /**
     * Aggregates <code>CASKS12cDataRecords</code> from OA To MSOA for the OA
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
        CASKS12cDataRecord aCASKS12cDataRecord;
        CASKS12cDataRecord bCASKS12cDataRecord;
        String zoneCode;
        Object zoneCodeMSOA;
        // long newRecordID = startRecordIDForLeeds - 1L;
        long newRecordID = -1L;
        for (long RecordID = startRecordID; RecordID < endRecordID; RecordID++) {
            aCASKS12cDataRecord = (CASKS12cDataRecord) getDataRecord(RecordID);
            zoneCode = new String(aCASKS12cDataRecord.getZone_Code());
            zoneCodeMSOA = lookUpMSOAfromOAHashMap.get(zoneCode);
            if (result.containsKey(zoneCodeMSOA)) {
                bCASKS12cDataRecord = (CASKS12cDataRecord) result.get(zoneCodeMSOA);
                result.remove(zoneCodeMSOA);
                result.put(zoneCodeMSOA, aCASKS12cDataRecord.aggregate(bCASKS12cDataRecord));
            } else {
                result.put(zoneCodeMSOA, aCASKS12cDataRecord);
            }
        }
        write(aRandomAccessFile, result);
    }
}