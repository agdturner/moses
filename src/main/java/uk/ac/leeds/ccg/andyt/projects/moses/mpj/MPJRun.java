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
package uk.ac.leeds.ccg.andyt.projects.moses.mpj;

import uk.ac.leeds.ccg.andyt.generic.core.Generic_ErrorAndExceptionHandler;
import mpi.MPIException;
import mpi.MPI;

//import mpi.Request;
//import mpi.Status;
/**
 * A class for MPJ Express initialisation, and finalising.
 */
public class MPJRun {

    /**
     * For storing the number of processes
     */
    private int _Size;
    /**
     * For storing the rank of process
     */
    private int _Rank;

    /**
     * Default Constructor
     */
    public MPJRun() {
    }

    /**
     * Initialises MPI Environment.
     * @param args
     */
    public void initMPI(String[] args) {
        try {
            MPI.Init(args);
        } catch (MPIException _MPIException) {
            System.err.println(_MPIException.getLocalizedMessage());
            System.exit(Generic_ErrorAndExceptionHandler.MPIException);
        }
        try {
            _Size = MPI.COMM_WORLD.Size();
            _Rank = MPI.COMM_WORLD.Rank();
        } catch (MPIException _MPIException) {
            System.err.println(_MPIException.getLocalizedMessage());
            System.exit(Generic_ErrorAndExceptionHandler.MPIException);
        }
    }

    /**
     * Finalises MPI Environment..
     */
    public void finalizeMPI() {
        try {
            MPI.Finalize();
        } catch (MPIException _MPIException) {
            System.err.println(_MPIException.getLocalizedMessage());
            System.exit(Generic_ErrorAndExceptionHandler.MPIException);
        }
    }

    /**
     * @return this._Rank
     */
    public int get_Rank() {
        return _Rank;
    }

    /**
     * @return this._Size
     */
    public int get_Size() {
        return _Size;
    }
}
