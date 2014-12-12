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

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.RandomAccessFile;

/**
 * A <code>class</code> for holding the following collection of
 * <code>AbstractCASDataHandlers</code> so as to access respective
 * <code>AbstractCASDataRecords</code>:
 * <ul>
 * <li>CAS001DataHandler</li>
 * <li>CAS002DataHandler</li>
 * <li>CAS003DataHandler</li>
 * <li>CASKS002DataHandler</li>
 * <li>CASKS008DataHandler</li>
 * <li>CASKS010DataHandler</li>
 * <li>CASKS017DataHandler</li>
 * <li>CASKS020DataHandler</li>
 * <li>CASKS09bDataHandler</li>
 * <li>CASKS09cDataHandler</li>
 * <li>CASKS12bDataHandler</li>
 * <li>CASKS12cDataHandler</li>
 * <li>CASKS14bDataHandler</li>
 * <li>CASKS14cDataHandler</li>
 * </ul>
 * @version 2.0.0
 * @see CASDataRecord
 */
public class CASDataHandler extends AbstractCASDataHandler {

    /**
     * CAS002DataHandler
     */
    protected CAS002DataHandler _CAS002DataHandler;
    /**
     * CASKS002DataHandler
     */
    protected CASKS002DataHandler _CASKS002DataHandler;
    /**
     * CASKS008DataHandler
     */
    protected CASKS008DataHandler _CASKS008DataHandler;
    /**
     * CASKS010DataHandler
     */
    protected CASKS010DataHandler _CASKS010DataHandler;
    /**
     * CASKS015DataHandler
     */
    protected CASKS015DataHandler _CASKS015DataHandler;
    /**
     * CASKS017DataHandler
     */
    protected CASKS017DataHandler _CASKS017DataHandler;
    /**
     * CASKS020DataHandler
     */
    protected CASKS020DataHandler _CASKS020DataHandler;
    /**
     * CASKS023DataHandler
     */
    protected CASKS023DataHandler _CASKS023DataHandler;
    /**
     * CASKS09bDataHandler
     */
    protected CASKS09bDataHandler _CASKS09bDataHandler;
    /**
     * CASKS09cDataHandler
     */
    protected CASKS09cDataHandler _CASKS09cDataHandler;
    /**
     * CASKS12bDataHandler
     */
    protected CASKS12bDataHandler _CASKS12bDataHandler;
    /**
     * CASKS12c
     */
    protected CASKS12cDataHandler _CASKS12cDataHandler;
    /**
     * CASKS14b
     */
    protected CASKS14bDataHandler _CASKS14bDataHandler;
    /**
     * CASKS14c
     */
    protected CASKS14cDataHandler _CASKS14cDataHandler;
    /**
     * CASUV003
     */
    protected CASUV003DataHandler _CASUV003DataHandler;

    /**
     * Creates a new CASDataHandler
     * @throws java.io.IOException
     */
    public CASDataHandler() throws IOException {
    }

    /**
     * Creates a new CASDataHandler
     * @param _Directory
     * @throws java.io.IOException
     */
    public CASDataHandler(File _Directory) throws IOException {
        init(_Directory);
        init();
    }

    /**
     * Creates a new CASDataHandler using <code>Files</code> in _Directory
     *
     * @param _Directory
     *            The _Directory containing <code>Files</code>
     * @param aggregation
     *            A <code>String</code> part of the filename indicating the
     *            aggregation (e.g. OA, MSOA, WARD, etc... )
     */
    public CASDataHandler(
            File _Directory,
            String aggregation) {
        init(_Directory);
        if (aggregation.equalsIgnoreCase("OA")) {
            aggregation = "";
        }
        this._CAS001DataHandler = new CAS001DataHandler(new File(
                _Directory, ("CAS001DataRecords" + aggregation + ".dat")));
        this._CAS002DataHandler =
                new CAS002DataHandler(
                new File(_Directory, ("CAS002DataRecords" + aggregation + ".dat")));
        this._CAS003DataHandler =
                new CAS003DataHandler(
                new File(_Directory, ("CAS003DataRecords" + aggregation + ".dat")));
        this._CASKS002DataHandler = new CASKS002DataHandler(new File(_Directory, ("CASKS002DataRecords" + aggregation + ".dat")));
        this._CASKS008DataHandler = new CASKS008DataHandler(new File(_Directory, ("CASKS008DataRecords" + aggregation + ".dat")));
        this._CASKS010DataHandler = new CASKS010DataHandler(new File(_Directory, ("CASKS010DataRecords" + aggregation + ".dat")));
        this._CASKS015DataHandler = new CASKS015DataHandler(new File(_Directory, ("CASKS015DataRecords" + aggregation + ".dat")));
        this._CASKS017DataHandler = new CASKS017DataHandler(new File(_Directory, ("CASKS017DataRecords" + aggregation + ".dat")));
        this._CASKS020DataHandler = new CASKS020DataHandler(new File(_Directory, ("CASKS020DataRecords" + aggregation + ".dat")));
        this._CASKS023DataHandler = new CASKS023DataHandler(new File(_Directory, ("CASKS023DataRecords" + aggregation + ".dat")));
        this._CASKS09bDataHandler = new CASKS09bDataHandler(new File(_Directory, ("CASKS09bDataRecords" + aggregation + ".dat")));
        this._CASKS09cDataHandler = new CASKS09cDataHandler(new File(_Directory, ("CASKS09cDataRecords" + aggregation + ".dat")));
        this._CASKS12bDataHandler = new CASKS12bDataHandler(new File(_Directory, ("CASKS12bDataRecords" + aggregation + ".dat")));
        this._CASKS12cDataHandler = new CASKS12cDataHandler(new File(_Directory, ("CASKS12cDataRecords" + aggregation + ".dat")));
        this._CASKS14bDataHandler = new CASKS14bDataHandler(new File(_Directory, ("CASKS14bDataRecords" + aggregation + ".dat")));
        this._CASKS14cDataHandler = new CASKS14cDataHandler(new File(_Directory, ("CASKS14cDataRecords" + aggregation + ".dat")));
        this._CASUV003DataHandler = new CASUV003DataHandler(new File(_Directory, ("CASUV003DataRecords" + aggregation + ".dat")));
    }

    /**
     * Initialises all fields.
     * @throws java.io.IOException
     */
    protected void init() throws IOException {
        this._CAS001DataHandler = new CAS001DataHandler();
        this._CAS002DataHandler = new CAS002DataHandler();
        this._CAS003DataHandler = new CAS003DataHandler();
        this._CASKS002DataHandler = new CASKS002DataHandler();
        this._CASKS008DataHandler = new CASKS008DataHandler();
        this._CASKS010DataHandler = new CASKS010DataHandler();
        this._CASKS015DataHandler = new CASKS015DataHandler();
        this._CASKS017DataHandler = new CASKS017DataHandler();
        this._CASKS020DataHandler = new CASKS020DataHandler();
        this._CASKS023DataHandler = new CASKS023DataHandler();
        this._CASKS09bDataHandler = new CASKS09bDataHandler();
        this._CASKS09cDataHandler = new CASKS09cDataHandler();
        this._CASKS12bDataHandler = new CASKS12bDataHandler();
        this._CASKS12cDataHandler = new CASKS12cDataHandler();
        this._CASKS14bDataHandler = new CASKS14bDataHandler();
        this._CASKS14cDataHandler = new CASKS14cDataHandler();
        this._CASUV003DataHandler = new CASUV003DataHandler();
    }

    /**
     * @param args
     *            the command line arguments
     * @throws java.io.IOException
     */
    public static void main(String[] args) throws IOException {
        // TODO code application logic here
        CASDataHandler aCASDataHandler = new CASDataHandler();
        aCASDataHandler.run();
    }

    /**
     * Top level run method
     * @throws java.io.IOException
     */
    // private void run() throws IOException {
    public void run() throws IOException {
        // formatSourceData();
        aggregateOAToMSOAForLeeds();
        // aggregateOAToWardForLeeds();
    }

    // /**
    // * Returns this._CAS001DataHandler
    // */
    // public CAS001DataHandler getCAS001DataHandler() {
    // return this._CAS001DataHandler;
    // }
    /**
     * Returns this._CAS002DataHandler
     * @return 
     */
    public CAS002DataHandler getCAS002DataHandler() {
        return this._CAS002DataHandler;
    }

    // /**
    // * Returns this._CAS003DataHandler
    // */
    // public CAS003DataHandler getCAS003DataHandler() {
    // return this._CAS003DataHandler;
    // }
    /**
     * Returns this._CASKS002DataHandler
     * @return 
     */
    public CASKS002DataHandler getCASKS002DataHandler() {
        return this._CASKS002DataHandler;
    }

    /**
     * Returns this._CASKS008DataHandler
     * @return 
     */
    public CASKS008DataHandler getCASKS008DataHandler() {
        return this._CASKS008DataHandler;
    }

    /**
     * Returns this._CASKS010DataHandler
     * @return 
     */
    public CASKS010DataHandler getCASKS010DataHandler() {
        return this._CASKS010DataHandler;
    }

    /**
     * Returns this._CASKS015DataHandler
     * @return 
     */
    public CASKS015DataHandler getCASKS015DataHandler() {
        return this._CASKS015DataHandler;
    }

    /**
     * Returns this._CASKS017DataHandler
     * @return 
     */
    public CASKS017DataHandler getCASKS017DataHandler() {
        return this._CASKS017DataHandler;
    }

    /**
     * Returns this._CASKS020DataHandler
     * @return 
     */
    public CASKS020DataHandler getCASKS020DataHandler() {
        return this._CASKS020DataHandler;
    }

    /**
     * Returns this._CASKS023DataHandler
     * @return 
     */
    public CASKS023DataHandler getCASKS023DataHandler() {
        return this._CASKS023DataHandler;
    }

    /**
     * Returns this._CASKS09bDataHandler
     * @return 
     */
    public CASKS09bDataHandler getCASKS09bDataHandler() {
        return this._CASKS09bDataHandler;
    }

    /**
     * Returns this._CASKS09cDataHandler
     * @return 
     */
    public CASKS09cDataHandler getCASKS09cDataHandler() {
        return this._CASKS09cDataHandler;
    }

    /**
     * Returns this._CASKS12bDataHandler
     * @return 
     */
    public CASKS12bDataHandler getCASKS12bDataHandler() {
        return this._CASKS12bDataHandler;
    }

    /**
     * Returns this._CASKS12cDataHandler
     * @return 
     */
    public CASKS12cDataHandler getCASKS12cDataHandler() {
        return this._CASKS12cDataHandler;
    }

    /**
     * Returns this._CASKS14bDataHandler
     * @return 
     */
    public CASKS14bDataHandler getCASKS14bDataHandler() {
        return this._CASKS14bDataHandler;
    }

    /**
     * Returns this._CASKS14cDataHandler
     * @return 
     */
    public CASKS14cDataHandler getCASKS14cDataHandler() {
        return this._CASKS14cDataHandler;
    }

    /**
     * Returns this._CASUV003DataHandler
     * @return 
     */
    public CASUV003DataHandler getCASUV003DataHandler() {
        return this._CASUV003DataHandler;
    }

    /**
     * @param aRecordID
     *            The RecordID of the AbstractCASDataRecord to be returned.
     * @return 
     */
    public AbstractCASDataRecord getDataRecord(long aRecordID) {
        return new CASDataRecord(this, aRecordID);
    }

    /**
     * Load Source Data
     * @throws java.io.IOException
     */
    public void formatSourceData() throws IOException {
        // Load source data
        int n = 5;
        File directory;
        directory = new File(
                "C:/work/data/census/2001/CAS/CAS/");
        _CAS001DataHandler.formatSourceData(directory, n);
        _CAS002DataHandler.formatSourceData(directory, n);
        _CAS003DataHandler.formatSourceData(directory, n);
        directory = new File(
                "C:/work/data/census/2001/CAS/KeyStatistics/");
        _CASKS002DataHandler.formatSourceData(directory, n);
        _CASKS008DataHandler.formatSourceData(directory, n);
        _CASKS09bDataHandler.formatSourceData(directory, n);
        _CASKS09cDataHandler.formatSourceData(directory, n);
        _CASKS010DataHandler.formatSourceData(directory, n);
        _CASKS12bDataHandler.formatSourceData(directory, n);
        _CASKS12cDataHandler.formatSourceData(directory, n);
        _CASKS14bDataHandler.formatSourceData(directory, n);
        _CASKS14cDataHandler.formatSourceData(directory, n);
        _CASKS015DataHandler.formatSourceData(directory, n);
        _CASKS017DataHandler.formatSourceData(directory, n);
        _CASKS020DataHandler.formatSourceData(directory, n);
        _CASKS023DataHandler.formatSourceData(directory, n);
        directory = new File(
                "C:/work/data/census/2001/CAS/UV/");
        _CASUV003DataHandler.formatSourceData(directory, n);
    }

    /**
     * Aggregate From OA To Ward For Leeds
     * @throws java.io.IOException
     */
    public void aggregateOAToWardForLeeds() throws IOException {
        // Aggregate to Ward for Leeds
        long nrecordsInLeeds = 2439L; // 2439L
        long startRecordIDForLeeds = 56750L; // 56749L
        long startRecordID = startRecordIDForLeeds;
        long endRecordID = startRecordIDForLeeds + nrecordsInLeeds;
        File aFile;
        RandomAccessFile aRandomAccessFile;
        long nDataRecords;
        long along;
        PrintWriter aPrintWriter;

        // CAS001DataRecords
        aFile = new File(
                "C:/Work/Projects/MoSeS/Workspace/Leeds/CAS001DataRecordsWard.dat");
        aFile.createNewFile();
        aRandomAccessFile = new RandomAccessFile(aFile, "rw");
        _CAS001DataHandler.aggregateOAToWard(aRandomAccessFile, startRecordID,
                endRecordID);
        aRandomAccessFile.close();
        _CAS001DataHandler = new CAS001DataHandler(aFile);
        nDataRecords = _CAS001DataHandler.getNDataRecords();
        CAS001DataRecord aCAS001DataRecord = new CAS001DataRecord();
        aFile = new File(
                "C:/Work/Projects/MoSeS/Workspace/Leeds/CAS001DataRecordsWard.csv");
        aPrintWriter = new PrintWriter(aFile);
        aPrintWriter.println(aCAS001DataRecord.toCSVStringFields());
        for (along = 0L; along < nDataRecords; along++) {
            aCAS001DataRecord = _CAS001DataHandler.getCAS001DataRecord(along);
            aPrintWriter.println(aCAS001DataRecord.toCSVString());
            System.out.println(aCAS001DataRecord.toString());
        }
        aPrintWriter.flush();
        aPrintWriter.close();

        // CAS002DataRecords
        aFile = new File(
                "C:/Work/Projects/MoSeS/Workspace/Leeds/CAS002DataRecordsWard.dat");
        aFile.createNewFile();
        aRandomAccessFile = new RandomAccessFile(aFile, "rw");
        _CAS002DataHandler.aggregateOAToWard(aRandomAccessFile, startRecordID,
                endRecordID);
        aRandomAccessFile.close();
        _CAS002DataHandler = new CAS002DataHandler(aFile);
        nDataRecords = _CAS002DataHandler.getNDataRecords();
        System.out.println("nDataRecords " + nDataRecords);
        CAS002DataRecord aCAS002DataRecord = new CAS002DataRecord();
        aFile = new File(
                "C:/Work/Projects/MoSeS/Workspace/Leeds/CAS002DataRecordsWard.csv");
        aPrintWriter = new PrintWriter(aFile);
        aPrintWriter.println(aCAS002DataRecord.toCSVStringFields());
        for (along = 0L; along < nDataRecords; along++) {
            aCAS002DataRecord = _CAS002DataHandler.getCAS002DataRecord(along);
            aPrintWriter.println(aCAS002DataRecord.toCSVString());
            System.out.println(aCAS002DataRecord.toString());
        }
        aPrintWriter.flush();
        aPrintWriter.close();

        // CAS003DataRecords
        aFile = new File(
                "C:/Work/Projects/MoSeS/Workspace/Leeds/CAS003DataRecordsWard.dat");
        aFile.createNewFile();
        aRandomAccessFile = new RandomAccessFile(aFile, "rw");
        _CAS003DataHandler.aggregateOAToWard(aRandomAccessFile, startRecordID,
                endRecordID);
        aRandomAccessFile.close();
        _CAS003DataHandler = new CAS003DataHandler(aFile);
        nDataRecords = _CAS003DataHandler.getNDataRecords();
        System.out.println("nDataRecords " + nDataRecords);
        CAS003DataRecord aCAS003DataRecord = new CAS003DataRecord();
        aFile = new File(
                "C:/Work/Projects/MoSeS/Workspace/Leeds/CAS003DataRecordsWard.csv");
        aPrintWriter = new PrintWriter(aFile);
        aPrintWriter.println(aCAS003DataRecord.toCSVStringFields());
        for (along = 0L; along < nDataRecords; along++) {
            aCAS003DataRecord = _CAS003DataHandler.getCAS003DataRecord(along);
            aPrintWriter.println(aCAS003DataRecord.toCSVString());
            System.out.println(aCAS003DataRecord.toString());
        }
        aPrintWriter.flush();
        aPrintWriter.close();

        // CASKS002DataRecords
        aFile = new File(
                "C:/Work/Projects/MoSeS/Workspace/Leeds/CASKS002DataRecordsWard.dat");
        aFile.createNewFile();
        aRandomAccessFile = new RandomAccessFile(aFile, "rw");
        _CASKS002DataHandler.aggregateOAToWard(aRandomAccessFile,
                startRecordID, endRecordID);
        aRandomAccessFile.close();
        _CASKS002DataHandler = new CASKS002DataHandler(aFile);
        nDataRecords = _CASKS002DataHandler.getNDataRecords();
        System.out.println("nDataRecords " + nDataRecords);
        CASKS002DataRecord aCASKS002DataRecord = new CASKS002DataRecord();
        aFile = new File(
                "C:/Work/Projects/MoSeS/Workspace/Leeds/CASKS002DataRecordsWard.csv");
        aPrintWriter = new PrintWriter(aFile);
        aPrintWriter.println(aCASKS002DataRecord.toCSVStringFields());
        for (along = 0L; along < nDataRecords; along++) {
            aCASKS002DataRecord = _CASKS002DataHandler.getCASKS002DataRecord(along);
            aPrintWriter.println(aCASKS002DataRecord.toCSVString());
            System.out.println(aCASKS002DataRecord.toString());
        }
        aPrintWriter.flush();
        aPrintWriter.close();

        // CASKS008DataRecords
        aFile = new File(
                "C:/Work/Projects/MoSeS/Workspace/Leeds/CASKS008DataRecordsWard.dat");
        aFile.createNewFile();
        aRandomAccessFile = new RandomAccessFile(aFile, "rw");
        _CASKS008DataHandler.aggregateOAToWard(aRandomAccessFile,
                startRecordID, endRecordID);
        aRandomAccessFile.close();
        _CASKS008DataHandler = new CASKS008DataHandler(aFile);
        nDataRecords = _CASKS008DataHandler.getNDataRecords();
        System.out.println("nDataRecords " + nDataRecords);
        CASKS008DataRecord aCASKS008DataRecord = new CASKS008DataRecord();
        aFile = new File(
                "C:/Work/Projects/MoSeS/Workspace/Leeds/CASKS008DataRecordsWard.csv");
        aPrintWriter = new PrintWriter(aFile);
        aPrintWriter.println(aCASKS008DataRecord.toCSVStringFields());
        for (along = 0L; along < nDataRecords; along++) {
            aCASKS008DataRecord = _CASKS008DataHandler.getCASKS008DataRecord(along);
            aPrintWriter.println(aCASKS008DataRecord.toCSVString());
            System.out.println(aCASKS008DataRecord.toString());
        }
        aPrintWriter.flush();
        aPrintWriter.close();

        // CASKS010DataRecords
        aFile = new File(
                "C:/Work/Projects/MoSeS/Workspace/Leeds/CASKS010DataRecordsWard.dat");
        aFile.createNewFile();
        aRandomAccessFile = new RandomAccessFile(aFile, "rw");
        _CASKS010DataHandler.aggregateOAToWard(aRandomAccessFile,
                startRecordID, endRecordID);
        aRandomAccessFile.close();
        _CASKS010DataHandler = new CASKS010DataHandler(aFile);
        nDataRecords = _CASKS010DataHandler.getNDataRecords();
        System.out.println("nDataRecords " + nDataRecords);
        CASKS010DataRecord aCASKS010DataRecord = new CASKS010DataRecord();
        aFile = new File(
                "C:/Work/Projects/MoSeS/Workspace/Leeds/CASKS010DataRecordsWard.csv");
        aPrintWriter = new PrintWriter(aFile);
        aPrintWriter.println(aCASKS010DataRecord.toCSVStringFields());
        for (along = 0L; along < nDataRecords; along++) {
            aCASKS010DataRecord = _CASKS010DataHandler.getCASKS010DataRecord(along);
            aPrintWriter.println(aCASKS010DataRecord.toCSVString());
            System.out.println(aCASKS010DataRecord.toString());
        }
        aPrintWriter.flush();
        aPrintWriter.close();

        // CASKS015DataRecords
        aFile = new File(
                "C:/Work/Projects/MoSeS/Workspace/Leeds/CASKS015DataRecordsWard.dat");
        aFile.createNewFile();
        aRandomAccessFile = new RandomAccessFile(aFile, "rw");
        _CASKS015DataHandler.aggregateOAToWard(aRandomAccessFile,
                startRecordID, endRecordID);
        aRandomAccessFile.close();
        _CASKS015DataHandler = new CASKS015DataHandler(aFile);
        nDataRecords = _CASKS015DataHandler.getNDataRecords();
        System.out.println("nDataRecords " + nDataRecords);
        CASKS015DataRecord aCASKS015DataRecord = new CASKS015DataRecord();
        aFile = new File(
                "C:/Work/Projects/MoSeS/Workspace/Leeds/CASKS015DataRecordsWard.csv");
        aPrintWriter = new PrintWriter(aFile);
        aPrintWriter.println(aCASKS015DataRecord.toCSVStringFields());
        for (along = 0L; along < nDataRecords; along++) {
            aCASKS015DataRecord = _CASKS015DataHandler.getCASKS015DataRecord(along);
            aPrintWriter.println(aCASKS015DataRecord.toCSVString());
            System.out.println(aCASKS015DataRecord.toString());
        }
        aPrintWriter.flush();
        aPrintWriter.close();

        // CASKS017DataRecords
        aFile = new File(
                "C:/Work/Projects/MoSeS/Workspace/Leeds/CASKS017DataRecordsWard.dat");
        aFile.createNewFile();
        aRandomAccessFile = new RandomAccessFile(aFile, "rw");
        _CASKS017DataHandler.aggregateOAToWard(aRandomAccessFile,
                startRecordID, endRecordID);
        aRandomAccessFile.close();
        _CASKS017DataHandler = new CASKS017DataHandler(aFile);
        nDataRecords = _CASKS017DataHandler.getNDataRecords();
        System.out.println("nDataRecords " + nDataRecords);
        CASKS017DataRecord aCASKS017DataRecord = new CASKS017DataRecord();
        aFile = new File(
                "C:/Work/Projects/MoSeS/Workspace/Leeds/CASKS017DataRecordsWard.csv");
        aPrintWriter = new PrintWriter(aFile);
        aPrintWriter.println(aCASKS017DataRecord.toCSVStringFields());
        for (along = 0L; along < nDataRecords; along++) {
            aCASKS017DataRecord = _CASKS017DataHandler.getCASKS017DataRecord(along);
            aPrintWriter.println(aCASKS017DataRecord.toCSVString());
            System.out.println(aCASKS017DataRecord.toString());
        }
        aPrintWriter.flush();
        aPrintWriter.close();

        // CASKS020DataRecords
        aFile = new File(
                "C:/Work/Projects/MoSeS/Workspace/Leeds/CASKS020DataRecordsWard.dat");
        aFile.createNewFile();
        aRandomAccessFile = new RandomAccessFile(aFile, "rw");
        _CASKS020DataHandler.aggregateOAToWard(aRandomAccessFile,
                startRecordID, endRecordID);
        aRandomAccessFile.close();
        _CASKS020DataHandler = new CASKS020DataHandler(aFile);
        nDataRecords = _CASKS020DataHandler.getNDataRecords();
        System.out.println("nDataRecords " + nDataRecords);
        CASKS020DataRecord aCASKS020DataRecord = new CASKS020DataRecord();
        aFile = new File(
                "C:/Work/Projects/MoSeS/Workspace/Leeds/CASKS020DataRecordsWard.csv");
        aPrintWriter = new PrintWriter(aFile);
        aPrintWriter.println(aCASKS020DataRecord.toCSVStringFields());
        for (along = 0L; along < nDataRecords; along++) {
            aCASKS020DataRecord = _CASKS020DataHandler.getCASKS020DataRecord(along);
            aPrintWriter.println(aCASKS020DataRecord.toCSVString());
            System.out.println(aCASKS020DataRecord.toString());
        }
        aPrintWriter.flush();
        aPrintWriter.close();

        // CASKS023DataRecords
        aFile = new File(
                "C:/Work/Projects/MoSeS/Workspace/Leeds/CASKS023DataRecordsWard.dat");
        aFile.createNewFile();
        aRandomAccessFile = new RandomAccessFile(aFile, "rw");
        _CASKS023DataHandler.aggregateOAToWard(aRandomAccessFile,
                startRecordID, endRecordID);
        aRandomAccessFile.close();
        _CASKS023DataHandler = new CASKS023DataHandler(aFile);
        nDataRecords = _CASKS023DataHandler.getNDataRecords();
        System.out.println("nDataRecords " + nDataRecords);
        CASKS023DataRecord aCASKS023DataRecord = new CASKS023DataRecord();
        aFile = new File(
                "C:/Work/Projects/MoSeS/Workspace/Leeds/CASKS023DataRecordsWard.csv");
        aPrintWriter = new PrintWriter(aFile);
        aPrintWriter.println(aCASKS023DataRecord.toCSVStringFields());
        for (along = 0L; along < nDataRecords; along++) {
            aCASKS023DataRecord = _CASKS023DataHandler.getCASKS023DataRecord(along);
            aPrintWriter.println(aCASKS023DataRecord.toCSVString());
            System.out.println(aCASKS023DataRecord.toString());
        }
        aPrintWriter.flush();
        aPrintWriter.close();

        // CASKS09bDataRecords
        aFile = new File(
                "C:/Work/Projects/MoSeS/Workspace/Leeds/CASKS09bDataRecordsWard.dat");
        aFile.createNewFile();
        aRandomAccessFile = new RandomAccessFile(aFile, "rw");
        _CASKS09bDataHandler.aggregateOAToWard(aRandomAccessFile,
                startRecordID, endRecordID);
        aRandomAccessFile.close();
        _CASKS09bDataHandler = new CASKS09bDataHandler(aFile);
        nDataRecords = _CASKS09bDataHandler.getNDataRecords();
        System.out.println("nDataRecords " + nDataRecords);
        CASKS09bDataRecord aCASKS09bDataRecord = new CASKS09bDataRecord();
        aFile = new File(
                "C:/Work/Projects/MoSeS/Workspace/Leeds/CASKS09bDataRecordsWard.csv");
        aPrintWriter = new PrintWriter(aFile);
        aPrintWriter.println(aCASKS09bDataRecord.toCSVStringFields());
        for (along = 0L; along < nDataRecords; along++) {
            aCASKS09bDataRecord = _CASKS09bDataHandler.getCASKS09bDataRecord(along);
            aPrintWriter.println(aCASKS09bDataRecord.toCSVString());
            System.out.println(aCASKS09bDataRecord.toString());
        }
        aPrintWriter.flush();
        aPrintWriter.close();

        // CASKS09cDataRecords
        aFile = new File(
                "C:/Work/Projects/MoSeS/Workspace/Leeds/CASKS09cDataRecordsWard.dat");
        aFile.createNewFile();
        aRandomAccessFile = new RandomAccessFile(aFile, "rw");
        _CASKS09cDataHandler.aggregateOAToWard(aRandomAccessFile,
                startRecordID, endRecordID);
        aRandomAccessFile.close();
        _CASKS09cDataHandler = new CASKS09cDataHandler(aFile);
        nDataRecords = _CASKS09cDataHandler.getNDataRecords();
        System.out.println("nDataRecords " + nDataRecords);
        CASKS09cDataRecord aCASKS09cDataRecord = new CASKS09cDataRecord();
        aFile = new File(
                "C:/Work/Projects/MoSeS/Workspace/Leeds/CASKS09cDataRecordsWard.csv");
        aPrintWriter = new PrintWriter(aFile);
        aPrintWriter.println(aCASKS09cDataRecord.toCSVStringFields());
        for (along = 0L; along < nDataRecords; along++) {
            aCASKS09cDataRecord = _CASKS09cDataHandler.getCASKS09cDataRecord(along);
            aPrintWriter.println(aCASKS09cDataRecord.toCSVString());
            System.out.println(aCASKS09cDataRecord.toString());
        }
        aPrintWriter.flush();
        aPrintWriter.close();

        // CASKS12bDataRecords
        aFile = new File(
                "C:/Work/Projects/MoSeS/Workspace/Leeds/CASKS12bDataRecordsWard.dat");
        aFile.createNewFile();
        aRandomAccessFile = new RandomAccessFile(aFile, "rw");
        _CASKS12bDataHandler.aggregateOAToWard(aRandomAccessFile,
                startRecordID, endRecordID);
        aRandomAccessFile.close();
        _CASKS12bDataHandler = new CASKS12bDataHandler(aFile);
        nDataRecords = _CASKS12bDataHandler.getNDataRecords();
        System.out.println("nDataRecords " + nDataRecords);
        CASKS12bDataRecord aCASKS12bDataRecord = new CASKS12bDataRecord();
        aFile = new File(
                "C:/Work/Projects/MoSeS/Workspace/Leeds/CASKS12bDataRecordsWard.csv");
        aPrintWriter = new PrintWriter(aFile);
        aPrintWriter.println(aCASKS12bDataRecord.toCSVStringFields());
        for (along = 0L; along < nDataRecords; along++) {
            aCASKS12bDataRecord = _CASKS12bDataHandler.getCASKS12bDataRecord(along);
            aPrintWriter.println(aCASKS12bDataRecord.toCSVString());
            System.out.println(aCASKS12bDataRecord.toString());
        }
        aPrintWriter.flush();
        aPrintWriter.close();

        // CASKS12cDataRecords
        aFile = new File(
                "C:/Work/Projects/MoSeS/Workspace/Leeds/CASKS12cDataRecordsWard.dat");
        aFile.createNewFile();
        aRandomAccessFile = new RandomAccessFile(aFile, "rw");
        _CASKS12cDataHandler.aggregateOAToWard(aRandomAccessFile,
                startRecordID, endRecordID);
        aRandomAccessFile.close();
        _CASKS12cDataHandler = new CASKS12cDataHandler(aFile);
        nDataRecords = _CASKS12cDataHandler.getNDataRecords();
        System.out.println("nDataRecords " + nDataRecords);
        CASKS12cDataRecord aCASKS12cDataRecord = new CASKS12cDataRecord();
        aFile = new File(
                "C:/Work/Projects/MoSeS/Workspace/Leeds/CASKS12cDataRecordsWard.csv");
        aPrintWriter = new PrintWriter(aFile);
        aPrintWriter.println(aCASKS12cDataRecord.toCSVStringFields());
        for (along = 0L; along < nDataRecords; along++) {
            aCASKS12cDataRecord = _CASKS12cDataHandler.getCASKS12cDataRecord(along);
            aPrintWriter.println(aCASKS12cDataRecord.toCSVString());
            System.out.println(aCASKS12cDataRecord.toString());
        }
        aPrintWriter.flush();
        aPrintWriter.close();

        // CASKS14bDataRecords
        aFile = new File(
                "C:/Work/Projects/MoSeS/Workspace/Leeds/CASKS14bDataRecordsWard.dat");
        aFile.createNewFile();
        aRandomAccessFile = new RandomAccessFile(aFile, "rw");
        _CASKS14bDataHandler.aggregateOAToWard(aRandomAccessFile,
                startRecordID, endRecordID);
        aRandomAccessFile.close();
        _CASKS14bDataHandler = new CASKS14bDataHandler(aFile);
        nDataRecords = _CASKS14bDataHandler.getNDataRecords();
        System.out.println("nDataRecords " + nDataRecords);
        CASKS14bDataRecord aCASKS14bDataRecord = new CASKS14bDataRecord();
        aFile = new File(
                "C:/Work/Projects/MoSeS/Workspace/Leeds/CASKS14bDataRecordsWard.csv");
        aPrintWriter = new PrintWriter(aFile);
        aPrintWriter.println(aCASKS14bDataRecord.toCSVStringFields());
        for (along = 0L; along < nDataRecords; along++) {
            aCASKS14bDataRecord = _CASKS14bDataHandler.getCASKS14bDataRecord(along);
            aPrintWriter.println(aCASKS14bDataRecord.toCSVString());
            System.out.println(aCASKS14bDataRecord.toString());
        }
        aPrintWriter.flush();
        aPrintWriter.close();

        // CASKS14cDataRecords
        aFile = new File(
                "C:/Work/Projects/MoSeS/Workspace/Leeds/CASKS14cDataRecordsWard.dat");
        aFile.createNewFile();
        aRandomAccessFile = new RandomAccessFile(aFile, "rw");
        _CASKS14cDataHandler.aggregateOAToWard(aRandomAccessFile,
                startRecordID, endRecordID);
        aRandomAccessFile.close();
        _CASKS14cDataHandler = new CASKS14cDataHandler(aFile);
        nDataRecords = _CASKS14cDataHandler.getNDataRecords();
        System.out.println("nDataRecords " + nDataRecords);
        CASKS14cDataRecord aCASKS14cDataRecord = new CASKS14cDataRecord();
        aFile = new File(
                "C:/Work/Projects/MoSeS/Workspace/Leeds/CASKS14bDataRecordsWard.csv");
        aPrintWriter = new PrintWriter(aFile);
        aPrintWriter.println(aCASKS14cDataRecord.toCSVStringFields());
        for (along = 0L; along < nDataRecords; along++) {
            aCASKS14cDataRecord = _CASKS14cDataHandler.getCASKS14cDataRecord(along);
            aPrintWriter.println(aCASKS14cDataRecord.toCSVString());
            System.out.println(aCASKS14cDataRecord.toString());
        }
        aPrintWriter.flush();
        aPrintWriter.close();

        // CASUV003DataRecords
        aFile = new File(
                "C:/Work/Projects/MoSeS/Workspace/Leeds/CASUV003DataRecordsWard.dat");
        aFile.createNewFile();
        aRandomAccessFile = new RandomAccessFile(aFile, "rw");
        _CASUV003DataHandler.aggregateOAToWard(aRandomAccessFile,
                startRecordID, endRecordID);
        aRandomAccessFile.close();
        _CASUV003DataHandler = new CASUV003DataHandler(aFile);
        nDataRecords = _CASUV003DataHandler.getNDataRecords();
        System.out.println("nDataRecords " + nDataRecords);
        CASUV003DataRecord aCASUV003DataRecord = new CASUV003DataRecord();
        aFile = new File(
                "C:/Work/Projects/MoSeS/Workspace/Leeds/CASUV003DataRecordsWard.csv");
        aPrintWriter = new PrintWriter(aFile);
        aPrintWriter.println(aCASUV003DataRecord.toCSVStringFields());
        for (along = 0L; along < nDataRecords; along++) {
            aCASUV003DataRecord = _CASUV003DataHandler.getCASUV003DataRecord(along);
            aPrintWriter.println(aCASUV003DataRecord.toCSVString());
            System.out.println(aCASUV003DataRecord.toString());
        }
        aPrintWriter.flush();
        aPrintWriter.close();
    }

    /**
     * Aggregate From OA To MSOA For Leeds
     * @throws java.io.IOException
     */
    public void aggregateOAToMSOAForLeeds() throws IOException {
        // Aggregate to MSOA for Leeds
        long nrecordsInLeeds = 2439L;
        long startRecordIDForLeeds = 56749L;
        long startRecordID = startRecordIDForLeeds;
        long endRecordID = startRecordIDForLeeds + nrecordsInLeeds;
        File aFile;
        RandomAccessFile aRandomAccessFile;
        long nDataRecords;
        long along;
        PrintWriter aPrintWriter;

        // CAS001DataRecords
        File dir = new File(
                get_Directory(),
                "Leeds");
        //File dir = new File("C:/Work/Projects/MoSeS/Workspace/Leeds/");
        aFile = new File(dir,
                "CAS001DataRecordsMSOA.dat");
        aFile.createNewFile();
        aRandomAccessFile = new RandomAccessFile(aFile, "rw");
        _CAS001DataHandler.aggregateOAToMSOA(aRandomAccessFile, startRecordID,
                endRecordID);
        aRandomAccessFile.close();
        _CAS001DataHandler = new CAS001DataHandler(aFile);
        nDataRecords = _CAS001DataHandler.getNDataRecords();
        CAS001DataRecord aCAS001DataRecord = new CAS001DataRecord();
        aFile = new File(dir,
                "CAS001DataRecordsMSOA.csv");
        aPrintWriter = new PrintWriter(aFile);
        aPrintWriter.println(aCAS001DataRecord.toCSVStringFields());
        for (along = 0L; along < nDataRecords; along++) {
            aCAS001DataRecord = _CAS001DataHandler.getCAS001DataRecord(along);
            aPrintWriter.println(aCAS001DataRecord.toCSVString());
            System.out.println(aCAS001DataRecord.toString());
        }
        aPrintWriter.flush();
        aPrintWriter.close();

        // CAS002DataRecords
        aFile = new File(dir,
                "CAS002DataRecordsMSOA.dat");
        aFile.createNewFile();
        aRandomAccessFile = new RandomAccessFile(aFile, "rw");
        _CAS002DataHandler.aggregateOAToMSOA(aRandomAccessFile, startRecordID,
                endRecordID);
        aRandomAccessFile.close();
        _CAS002DataHandler = new CAS002DataHandler(aFile);
        nDataRecords = _CAS002DataHandler.getNDataRecords();
        System.out.println("nDataRecords " + nDataRecords);
        CAS002DataRecord aCAS002DataRecord = new CAS002DataRecord();
        aFile = new File(dir,
                "CAS002DataRecordsMSOA.csv");
        aPrintWriter = new PrintWriter(aFile);
        aPrintWriter.println(aCAS002DataRecord.toCSVStringFields());
        for (along = 0L; along < nDataRecords; along++) {
            aCAS002DataRecord = _CAS002DataHandler.getCAS002DataRecord(along);
            aPrintWriter.println(aCAS002DataRecord.toCSVString());
            System.out.println(aCAS002DataRecord.toString());
        }
        aPrintWriter.flush();
        aPrintWriter.close();

        // CAS003DataRecords
        aFile = new File(dir,
                "CAS003DataRecordsMSOA.dat");
        aFile.createNewFile();
        aRandomAccessFile = new RandomAccessFile(aFile, "rw");
        _CAS003DataHandler.aggregateOAToMSOA(aRandomAccessFile, startRecordID,
                endRecordID);
        aRandomAccessFile.close();
        _CAS003DataHandler = new CAS003DataHandler(aFile);
        nDataRecords = _CAS003DataHandler.getNDataRecords();
        System.out.println("nDataRecords " + nDataRecords);
        CAS003DataRecord aCAS003DataRecord = new CAS003DataRecord();
        aFile = new File(dir,
                "CAS003DataRecordsMSOA.csv");
        aPrintWriter = new PrintWriter(aFile);
        aPrintWriter.println(aCAS003DataRecord.toCSVStringFields());
        for (along = 0L; along < nDataRecords; along++) {
            aCAS003DataRecord = _CAS003DataHandler.getCAS003DataRecord(along);
            aPrintWriter.println(aCAS003DataRecord.toCSVString());
            System.out.println(aCAS003DataRecord.toString());
        }
        aPrintWriter.flush();
        aPrintWriter.close();

        // CASKS002DataRecords
        aFile = new File(dir,
                "CASKS002DataRecordsMSOA.dat");
        aFile.createNewFile();
        aRandomAccessFile = new RandomAccessFile(aFile, "rw");
        _CASKS002DataHandler.aggregateOAToMSOA(aRandomAccessFile,
                startRecordID, endRecordID);
        aRandomAccessFile.close();
        _CASKS002DataHandler = new CASKS002DataHandler(aFile);
        nDataRecords = _CASKS002DataHandler.getNDataRecords();
        System.out.println("nDataRecords " + nDataRecords);
        CASKS002DataRecord aCASKS002DataRecord = new CASKS002DataRecord();
        aFile = new File(dir,
                "CASKS002DataRecordsMSOA.csv");
        aPrintWriter = new PrintWriter(aFile);
        aPrintWriter.println(aCASKS002DataRecord.toCSVStringFields());
        for (along = 0L; along < nDataRecords; along++) {
            aCASKS002DataRecord = _CASKS002DataHandler.getCASKS002DataRecord(along);
            aPrintWriter.println(aCASKS002DataRecord.toCSVString());
            System.out.println(aCASKS002DataRecord.toString());
        }
        aPrintWriter.flush();
        aPrintWriter.close();

        // CASKS008DataRecords
        aFile = new File(dir,
                "CASKS008DataRecordsMSOA.dat");
        aFile.createNewFile();
        aRandomAccessFile = new RandomAccessFile(aFile, "rw");
        _CASKS008DataHandler.aggregateOAToMSOA(aRandomAccessFile,
                startRecordID, endRecordID);
        aRandomAccessFile.close();
        _CASKS008DataHandler = new CASKS008DataHandler(aFile);
        nDataRecords = _CASKS008DataHandler.getNDataRecords();
        System.out.println("nDataRecords " + nDataRecords);
        CASKS008DataRecord aCASKS008DataRecord = new CASKS008DataRecord();
        aFile = new File(dir,
                "CASKS008DataRecordsMSOA.csv");
        aPrintWriter = new PrintWriter(aFile);
        aPrintWriter.println(aCASKS008DataRecord.toCSVStringFields());
        for (along = 0L; along < nDataRecords; along++) {
            aCASKS008DataRecord = _CASKS008DataHandler.getCASKS008DataRecord(along);
            aPrintWriter.println(aCASKS008DataRecord.toCSVString());
            System.out.println(aCASKS008DataRecord.toString());
        }
        aPrintWriter.flush();
        aPrintWriter.close();

        // CASKS010DataRecords
        aFile = new File(dir,
                "CASKS010DataRecordsMSOA.dat");
        aFile.createNewFile();
        aRandomAccessFile = new RandomAccessFile(aFile, "rw");
        _CASKS010DataHandler.aggregateOAToMSOA(aRandomAccessFile,
                startRecordID, endRecordID);
        aRandomAccessFile.close();
        _CASKS010DataHandler = new CASKS010DataHandler(aFile);
        nDataRecords = _CASKS010DataHandler.getNDataRecords();
        System.out.println("nDataRecords " + nDataRecords);
        CASKS010DataRecord aCASKS010DataRecord = new CASKS010DataRecord();
        aFile = new File(dir,
                "CASKS010DataRecordsMSOA.csv");
        aPrintWriter = new PrintWriter(aFile);
        aPrintWriter.println(aCASKS010DataRecord.toCSVStringFields());
        for (along = 0L; along < nDataRecords; along++) {
            aCASKS010DataRecord = _CASKS010DataHandler.getCASKS010DataRecord(along);
            aPrintWriter.println(aCASKS010DataRecord.toCSVString());
            System.out.println(aCASKS010DataRecord.toString());
        }
        aPrintWriter.flush();
        aPrintWriter.close();

        // CASKS015DataRecords
        aFile = new File(dir,
                "CASKS015DataRecordsMSOA.dat");
        aFile.createNewFile();
        aRandomAccessFile = new RandomAccessFile(aFile, "rw");
        _CASKS015DataHandler.aggregateOAToMSOA(aRandomAccessFile,
                startRecordID, endRecordID);
        aRandomAccessFile.close();
        _CASKS015DataHandler = new CASKS015DataHandler(aFile);
        nDataRecords = _CASKS015DataHandler.getNDataRecords();
        System.out.println("nDataRecords " + nDataRecords);
        CASKS015DataRecord aCASKS015DataRecord = new CASKS015DataRecord();
        aFile = new File(dir,
                "CASKS015DataRecordsMSOA.csv");
        aPrintWriter = new PrintWriter(aFile);
        aPrintWriter.println(aCASKS015DataRecord.toCSVStringFields());
        for (along = 0L; along < nDataRecords; along++) {
            aCASKS015DataRecord = _CASKS015DataHandler.getCASKS015DataRecord(along);
            aPrintWriter.println(aCASKS015DataRecord.toCSVString());
            System.out.println(aCASKS015DataRecord.toString());
        }
        aPrintWriter.flush();
        aPrintWriter.close();

        // CASKS017DataRecords
        aFile = new File(dir,
                "CASKS017DataRecordsMSOA.dat");
        aFile.createNewFile();
        aRandomAccessFile = new RandomAccessFile(aFile, "rw");
        _CASKS017DataHandler.aggregateOAToMSOA(aRandomAccessFile,
                startRecordID, endRecordID);
        aRandomAccessFile.close();
        _CASKS017DataHandler = new CASKS017DataHandler(aFile);
        nDataRecords = _CASKS017DataHandler.getNDataRecords();
        System.out.println("nDataRecords " + nDataRecords);
        CASKS017DataRecord aCASKS017DataRecord = new CASKS017DataRecord();
        aFile = new File(dir,
                "CASKS017DataRecordsMSOA.csv");
        aPrintWriter = new PrintWriter(aFile);
        aPrintWriter.println(aCASKS017DataRecord.toCSVStringFields());
        for (along = 0L; along < nDataRecords; along++) {
            aCASKS017DataRecord = _CASKS017DataHandler.getCASKS017DataRecord(along);
            aPrintWriter.println(aCASKS017DataRecord.toCSVString());
            System.out.println(aCASKS017DataRecord.toString());
        }
        aPrintWriter.flush();
        aPrintWriter.close();

        // CASKS020DataRecords
        aFile = new File(dir,
                "CASKS020DataRecordsMSOA.dat");
        aFile.createNewFile();
        aRandomAccessFile = new RandomAccessFile(aFile, "rw");
        _CASKS020DataHandler.aggregateOAToMSOA(aRandomAccessFile,
                startRecordID, endRecordID);
        aRandomAccessFile.close();
        _CASKS020DataHandler = new CASKS020DataHandler(aFile);
        nDataRecords = _CASKS020DataHandler.getNDataRecords();
        System.out.println("nDataRecords " + nDataRecords);
        CASKS020DataRecord aCASKS020DataRecord = new CASKS020DataRecord();
        aFile = new File(dir,
                "CASKS020DataRecordsMSOA.csv");
        aPrintWriter = new PrintWriter(aFile);
        aPrintWriter.println(aCASKS020DataRecord.toCSVStringFields());
        for (along = 0L; along < nDataRecords; along++) {
            aCASKS020DataRecord = _CASKS020DataHandler.getCASKS020DataRecord(along);
            aPrintWriter.println(aCASKS020DataRecord.toCSVString());
            System.out.println(aCASKS020DataRecord.toString());
        }
        aPrintWriter.flush();
        aPrintWriter.close();

        // CASKS023DataRecords
        aFile = new File(dir,
                "CASKS023DataRecordsMSOA.dat");
        aFile.createNewFile();
        aRandomAccessFile = new RandomAccessFile(aFile, "rw");
        _CASKS023DataHandler.aggregateOAToMSOA(aRandomAccessFile,
                startRecordID, endRecordID);
        aRandomAccessFile.close();
        _CASKS023DataHandler = new CASKS023DataHandler(aFile);
        nDataRecords = _CASKS023DataHandler.getNDataRecords();
        System.out.println("nDataRecords " + nDataRecords);
        CASKS023DataRecord aCASKS023DataRecord = new CASKS023DataRecord();
        aFile = new File(dir,
                "CASKS023DataRecordsMSOA.csv");
        aPrintWriter = new PrintWriter(aFile);
        aPrintWriter.println(aCASKS023DataRecord.toCSVStringFields());
        for (along = 0L; along < nDataRecords; along++) {
            aCASKS023DataRecord = _CASKS023DataHandler.getCASKS023DataRecord(along);
            aPrintWriter.println(aCASKS023DataRecord.toCSVString());
            System.out.println(aCASKS023DataRecord.toString());
        }
        aPrintWriter.flush();
        aPrintWriter.close();

        // CASKS09bDataRecords
        aFile = new File(dir,
                "CASKS09bDataRecordsMSOA.dat");
        aFile.createNewFile();
        aRandomAccessFile = new RandomAccessFile(aFile, "rw");
        _CASKS09bDataHandler.aggregateOAToMSOA(aRandomAccessFile,
                startRecordID, endRecordID);
        aRandomAccessFile.close();
        _CASKS09bDataHandler = new CASKS09bDataHandler(aFile);
        nDataRecords = _CASKS09bDataHandler.getNDataRecords();
        System.out.println("nDataRecords " + nDataRecords);
        CASKS09bDataRecord aCASKS09bDataRecord = new CASKS09bDataRecord();
        aFile = new File(dir,
                "CASKS09bDataRecordsMSOA.csv");
        aPrintWriter = new PrintWriter(aFile);
        aPrintWriter.println(aCASKS09bDataRecord.toCSVStringFields());
        for (along = 0L; along < nDataRecords; along++) {
            aCASKS09bDataRecord = _CASKS09bDataHandler.getCASKS09bDataRecord(along);
            aPrintWriter.println(aCASKS09bDataRecord.toCSVString());
            System.out.println(aCASKS09bDataRecord.toString());
        }
        aPrintWriter.flush();
        aPrintWriter.close();

        // CASKS09cDataRecords
        aFile = new File(dir,
                "CASKS09cDataRecordsMSOA.dat");
        aFile.createNewFile();
        aRandomAccessFile = new RandomAccessFile(aFile, "rw");
        _CASKS09cDataHandler.aggregateOAToMSOA(aRandomAccessFile,
                startRecordID, endRecordID);
        aRandomAccessFile.close();
        _CASKS09cDataHandler = new CASKS09cDataHandler(aFile);
        nDataRecords = _CASKS09cDataHandler.getNDataRecords();
        System.out.println("nDataRecords " + nDataRecords);
        CASKS09cDataRecord aCASKS09cDataRecord = new CASKS09cDataRecord();
        aFile = new File(dir,
                "CASKS09cDataRecordsMSOA.csv");
        aPrintWriter = new PrintWriter(aFile);
        aPrintWriter.println(aCASKS09cDataRecord.toCSVStringFields());
        for (along = 0L; along < nDataRecords; along++) {
            aCASKS09cDataRecord = _CASKS09cDataHandler.getCASKS09cDataRecord(along);
            aPrintWriter.println(aCASKS09cDataRecord.toCSVString());
            System.out.println(aCASKS09cDataRecord.toString());
        }
        aPrintWriter.flush();
        aPrintWriter.close();

        // CASKS12bDataRecords
        aFile = new File(dir,
                "CASKS12bDataRecordsMSOA.dat");
        aFile.createNewFile();
        aRandomAccessFile = new RandomAccessFile(aFile, "rw");
        _CASKS12bDataHandler.aggregateOAToMSOA(aRandomAccessFile,
                startRecordID, endRecordID);
        aRandomAccessFile.close();
        _CASKS12bDataHandler = new CASKS12bDataHandler(aFile);
        nDataRecords = _CASKS12bDataHandler.getNDataRecords();
        System.out.println("nDataRecords " + nDataRecords);
        CASKS12bDataRecord aCASKS12bDataRecord = new CASKS12bDataRecord();
        aFile = new File(dir,
                "CASKS12bDataRecordsMSOA.csv");
        aPrintWriter = new PrintWriter(aFile);
        aPrintWriter.println(aCASKS12bDataRecord.toCSVStringFields());
        for (along = 0L; along < nDataRecords; along++) {
            aCASKS12bDataRecord = _CASKS12bDataHandler.getCASKS12bDataRecord(along);
            aPrintWriter.println(aCASKS12bDataRecord.toCSVString());
            System.out.println(aCASKS12bDataRecord.toString());
        }
        aPrintWriter.flush();
        aPrintWriter.close();

        // CASKS12cDataRecords
        aFile = new File(dir,
                "CASKS12cDataRecordsMSOA.dat");
        aFile.createNewFile();
        aRandomAccessFile = new RandomAccessFile(aFile, "rw");
        _CASKS12cDataHandler.aggregateOAToMSOA(aRandomAccessFile,
                startRecordID, endRecordID);
        aRandomAccessFile.close();
        _CASKS12cDataHandler = new CASKS12cDataHandler(aFile);
        nDataRecords = _CASKS12cDataHandler.getNDataRecords();
        System.out.println("nDataRecords " + nDataRecords);
        CASKS12cDataRecord aCASKS12cDataRecord = new CASKS12cDataRecord();
        aFile = new File(dir,
                "CASKS12cDataRecordsMSOA.csv");
        aPrintWriter = new PrintWriter(aFile);
        aPrintWriter.println(aCASKS12cDataRecord.toCSVStringFields());
        for (along = 0L; along < nDataRecords; along++) {
            aCASKS12cDataRecord = _CASKS12cDataHandler.getCASKS12cDataRecord(along);
            aPrintWriter.println(aCASKS12cDataRecord.toCSVString());
            System.out.println(aCASKS12cDataRecord.toString());
        }
        aPrintWriter.flush();
        aPrintWriter.close();

        // CASKS14bDataRecords
        aFile = new File(dir,
                "CASKS14bDataRecordsMSOA.dat");
        aFile.createNewFile();
        aRandomAccessFile = new RandomAccessFile(aFile, "rw");
        _CASKS14bDataHandler.aggregateOAToMSOA(aRandomAccessFile,
                startRecordID, endRecordID);
        aRandomAccessFile.close();
        _CASKS14bDataHandler = new CASKS14bDataHandler(aFile);
        nDataRecords = _CASKS14bDataHandler.getNDataRecords();
        System.out.println("nDataRecords " + nDataRecords);
        CASKS14bDataRecord aCASKS14bDataRecord = new CASKS14bDataRecord();
        aFile = new File(dir,
                "CASKS14bDataRecordsMSOA.csv");
        aPrintWriter = new PrintWriter(aFile);
        aPrintWriter.println(aCASKS14bDataRecord.toCSVStringFields());
        for (along = 0L; along < nDataRecords; along++) {
            aCASKS14bDataRecord = _CASKS14bDataHandler.getCASKS14bDataRecord(along);
            aPrintWriter.println(aCASKS14bDataRecord.toCSVString());
            System.out.println(aCASKS14bDataRecord.toString());
        }
        aPrintWriter.flush();
        aPrintWriter.close();

        // CASKS14cDataRecords
        aFile = new File(dir,
                "CASKS14cDataRecordsMSOA.dat");
        aFile.createNewFile();
        aRandomAccessFile = new RandomAccessFile(aFile, "rw");
        _CASKS14cDataHandler.aggregateOAToMSOA(aRandomAccessFile,
                startRecordID, endRecordID);
        aRandomAccessFile.close();
        _CASKS14cDataHandler = new CASKS14cDataHandler(aFile);
        nDataRecords = _CASKS14cDataHandler.getNDataRecords();
        System.out.println("nDataRecords " + nDataRecords);
        CASKS14cDataRecord aCASKS14cDataRecord = new CASKS14cDataRecord();
        aFile = new File(dir,
                "CASKS14bDataRecordsMSOA.csv");
        aPrintWriter = new PrintWriter(aFile);
        aPrintWriter.println(aCASKS14cDataRecord.toCSVStringFields());
        for (along = 0L; along < nDataRecords; along++) {
            aCASKS14cDataRecord = _CASKS14cDataHandler.getCASKS14cDataRecord(along);
            aPrintWriter.println(aCASKS14cDataRecord.toCSVString());
            System.out.println(aCASKS14cDataRecord.toString());
        }
        aPrintWriter.flush();
        aPrintWriter.close();

        // CASUV003DataRecords
        aFile = new File(dir,
                "CASUV003DataRecordsMSOA.dat");
        aFile.createNewFile();
        aRandomAccessFile = new RandomAccessFile(aFile, "rw");
        _CASUV003DataHandler.aggregateOAToMSOA(aRandomAccessFile,
                startRecordID, endRecordID);
        aRandomAccessFile.close();
        _CASUV003DataHandler = new CASUV003DataHandler(aFile);
        nDataRecords = _CASUV003DataHandler.getNDataRecords();
        System.out.println("nDataRecords " + nDataRecords);
        CASUV003DataRecord aCASUV003DataRecord = new CASUV003DataRecord();
        aFile = new File(dir,
                "CASUV003DataRecordsMSOA.csv");
        aPrintWriter = new PrintWriter(aFile);
        aPrintWriter.println(aCASUV003DataRecord.toCSVStringFields());
        for (along = 0L; along < nDataRecords; along++) {
            aCASUV003DataRecord = _CASUV003DataHandler.getCASUV003DataRecord(along);
            aPrintWriter.println(aCASUV003DataRecord.toCSVString());
            System.out.println(aCASUV003DataRecord.toString());
        }
        aPrintWriter.flush();
        aPrintWriter.close();
    }

    /**
     * @return The number of <code>AbstractDataRecords</code> in
     *         <code>this.tRandomAccessFile</code> For full UK file this should
     *         be 18435255
     */
    @Override
    public long getNDataRecords() {
        return this._CAS001DataHandler.getNDataRecords();
    }
}
