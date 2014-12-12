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

import java.io.IOException;
import java.io.RandomAccessFile;

/**
 * A <code>class</code> collection of the following
 * <code>AbstractCASDataRecords</code>:
 * <ul>
 * <li>CAS001DataRecord</li>
 * <li>CAS003DataRecord</li>
 * <li>CAS044DataRecord</li>
 * <li>CASKS006DataRecord</li>
 * <li>CASKS008DataRecord</li>
 * </ul>
 * <ul>
 * <li>Developed for <a href="http://www.ncess.ac.uk/moses">MoSeS</a>.</li>
 * <li>Copyright (C) 2006 <a
 * href="http://www.geog.leeds.ac.uk/people/a.turner/">Andy Turner</a>, <a
 * href="http://www.leeds.ac.uk//">University of Leeds</a>.</li>
 * </ul>
 * 
 * @author <a href="http://www.geog.leeds.ac.uk/people/a.turner/">Andy
 *         Turner</a>
 * @version 1.0.0, 2007-06-13
 * @see CASDataHandler_GA_IPS
 */
public class CASDataRecord_1 extends AbstractCASDataRecord {

	/**
	 * Serializable class version number for swapping
	 * (serialization/deserialization)
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * CAS001DataRecord
	 */
	protected CAS001DataRecord _CAS001DataRecord;

	/**
	 * CAS003DataRecord
	 */
	protected CAS003DataRecord _CAS003DataRecord;

	/**
	 * CAS044DataRecord
	 */
	protected CAS044DataRecord _CAS044DataRecord;

	/**
	 * CASKS006DataRecord
	 */
	protected CASKS006DataRecord _CASKS006DataRecord;

	/**
	 * CASKS008DataRecord
	 */
	protected CASKS008DataRecord _CASKS008DataRecord;

	/** Creates a new CASDataRecord_1 */
	public CASDataRecord_1() {
		_Init();
	}

	/** Creates a new CASDataRecord_1
     * @param _CASDataHandler_1
     * @param _RecordID */
	public CASDataRecord_1(
            CASDataHandler_GA_IPS _CASDataHandler_1,
			long _RecordID) {
		init(
                _CASDataHandler_1,
                _RecordID);
	}

	/**
	 * Initialises all fields.
	 */
	protected void _Init() {
		this._CAS001DataRecord = new CAS001DataRecord();
		this._CAS003DataRecord = new CAS003DataRecord();
		this._CAS044DataRecord = new CAS044DataRecord();
		this._CASKS006DataRecord = new CASKS006DataRecord();
		this._CASKS008DataRecord = new CASKS008DataRecord();
	}

	/**
	 * Initialises all fields.
     * @param _CASDataHandler_1
     * @param _RecordID
	 */
	protected void init(
            CASDataHandler_GA_IPS _CASDataHandler_1,
            long _RecordID) {
		this._CAS001DataRecord = (CAS001DataRecord) _CASDataHandler_1
				.getCAS001DataHandler().getDataRecord(_RecordID);
		this._CAS003DataRecord = (CAS003DataRecord) _CASDataHandler_1
				.getCAS003DataHandler().getDataRecord(_RecordID);
		this._CAS044DataRecord = (CAS044DataRecord) _CASDataHandler_1
				.getCAS044DataHandler().getDataRecord(_RecordID);
		this._CASKS006DataRecord = (CASKS006DataRecord) _CASDataHandler_1
				.getCASKS006DataHandler().getDataRecord(_RecordID);
		this._CASKS008DataRecord = (CASKS008DataRecord) _CASDataHandler_1
				.getCASKS008DataHandler().getDataRecord(_RecordID);
	}

	/**
	 * Returns a string description of this;
     * @return 
	 */
	public String toString() {
		return "CAS001DataRecord " + _CAS001DataRecord.toString() + ", "
				+ "CAS003DataRecord " + _CAS003DataRecord.toString() + ", "
				+ "CAS044DataRecord " + _CAS044DataRecord.toString() + ", "
				+ "CASKS006DataRecord " + _CASKS006DataRecord.toString() + ", "
				+ "CASKS008DataRecord " + _CASKS008DataRecord.toString();
	}

	/**
	 * Returns the size of this CASDataRecord in bytes as a long. This does not
	 * account for serialVersionUID. A boolean is assumed to be the same size as
	 * an int in bytes.
     * @return 
	 */
	public long getSizeInBytes() {
		return _CAS001DataRecord.getSizeInBytes()
				+ _CAS003DataRecord.getSizeInBytes()
				+ _CAS044DataRecord.getSizeInBytes()
				+ _CASKS006DataRecord.getSizeInBytes()
				+ _CASKS008DataRecord.getSizeInBytes();
	}

	/**
	 * Returns this._CAS001DataRecord
     * @return 
	 */
	public CAS001DataRecord getCAS001DataRecord() {
		return this._CAS001DataRecord;
	}

	/**
	 * Returns this._CAS003DataRecord
     * @return 
	 */
	public CAS003DataRecord getCAS003DataRecord() {
		return this._CAS003DataRecord;
	}

	/**
	 * Returns this._CAS044DataRecord
     * @return 
	 */
	public CAS044DataRecord getCAS044DataRecord() {
		return this._CAS044DataRecord;
	}

	/**
	 * Returns this._CASKS006DataRecord
     * @return 
	 */
	public CASKS006DataRecord getCASKS006DataRecord() {
		return this._CASKS006DataRecord;
	}

	/**
	 * Returns this._CASKS008DataRecord
     * @return 
	 */
	public CASKS008DataRecord getCASKS008DataRecord() {
		return this._CASKS008DataRecord;
	}

	/**
	 * Returns a copy of this.Zone_Code
     * @return 
	 */
	public @Override
	char[] getZone_Code() {
		return getCAS003DataRecord().getZone_Code();
	}

	/**
	 * Writes <code>this</code> to <code>_RandomAccessFile</code> at the current
	 * position.
	 * 
	 * @param _RandomAccessFile
	 *            The <code>RandomAccessFile</code> this is written to.
	 */
	@Override
	public void write(RandomAccessFile _RandomAccessFile) {
		super.write(_RandomAccessFile);
		write(
                _RandomAccessFile,
                false);
	}

	/**
	 * Writes <code>this</code> to <code>_RandomAccessFile</code> at the current
	 * position.
	 * 
	 * @param _RandomAccessFile
	 *            The <code>RandomAccessFile</code> this is written to.
	 * @param avoidCallToSuper
	 *            If true super.write() is not called
	 */
	public void write(RandomAccessFile _RandomAccessFile,
			boolean avoidCallToSuper) {
		if (avoidCallToSuper) {
		} else {
			write(_RandomAccessFile);
		}
	}
}
