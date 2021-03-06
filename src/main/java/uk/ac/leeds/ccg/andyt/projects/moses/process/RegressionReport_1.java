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
package uk.ac.leeds.ccg.andyt.projects.moses.process;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import org.jfree.chart.JFreeChart;
import uk.ac.leeds.ccg.andyt.projects.moses.io.AbstractOutputDataHandler;
import uk.ac.leeds.ccg.andyt.projects.moses.io.OutputDataHandler_ControlConstraints;
import uk.ac.leeds.ccg.andyt.projects.moses.io.OutputDataHandler_NonConstraints;
import uk.ac.leeds.ccg.andyt.projects.moses.io.OutputDataHandler_OptimisationConstraints;

/**
 * A class for generating maps comparing CAS data with IndividualCensus outputs
 * in the form of regression plots.
 */
public class RegressionReport_1 extends RegressionReport {

	public RegressionReport_1() {
	}

	String[] _Variables;

	String _AreaLevel;

	String _Type;

	/**
	 * @param args
	 *            the command line arguments
     * @throws java.lang.Exception
	 */
	public static void main(String[] args) throws Exception {
		new RegressionReport_1().run();
	}

	public void run() throws Exception {
		// run( "ControlConstraints", "SSE" );
		// run( "OptimisationConstraints", "SSE" );
		// run( "NonConstraints", "SSE" );
		run("ControlConstraints", "NSSE");
		run("OptimisationConstraints", "NSSE");
		run("NonConstraints", "NSSE");
	}

	public void run(
            String _Type,
            String _ErrorFunction)
            throws Exception {
		this._Type = _Type;
		String _OutputDirectoryName_1 = "C:/temp/"; // Because
																// filename
																// length is a
																// problem!
		String _OutputName = "OA_SWS_HSARHP_ISARCEP_100_10_10_30";
		String _OutputDirectoryName_2 = _OutputDirectoryName_1
                        + _ErrorFunction + "/" + _OutputName + "/";
		File _InputFile = new File(_OutputDirectoryName_2, _OutputName + ".csv");

		String _InputDirectoryName = "C:/Work/Projects/MoSeS/Workspace/";

		AbstractOutputDataHandler _OutputDataHandler = null;
		if (_Type.equalsIgnoreCase("ControlConstraints")) {
			_OutputDataHandler = new OutputDataHandler_ControlConstraints();
		}
		if (_Type.equalsIgnoreCase("OptimisationConstraints")) {
			//_OutputDataHandler = new OutputDataHandler_OptimisationConstraints();
		}
		if (_Type.equalsIgnoreCase("NonConstraints")) {
			_OutputDataHandler = new OutputDataHandler_NonConstraints();
		}

		this._AreaLevel = "OA"; // MSOA,Ward
		String _Area = "Leeds"; // UK
		long _StartRecordID = 0;//56749L;// 0
		long _EndRecordID = _StartRecordID + 2438L;// 175433
		String _OutputFileName;

		// Observed
		_OutputFileName = _InputDirectoryName + "/" +
                        _Area + "/" +
                        _Type + "/" +
                        _AreaLevel + ".csv";
		File _ObservedFile = new File(_OutputFileName);
		// _OutputDataHandler.writeObserved(
		// _InputDirectoryName,
		// _OutputFileName,
		// _StartRecordID,
		// _EndRecordID,
		// _AreaLevel );

		// Estimated
		_OutputFileName = _OutputDirectoryName_2 + "/" +
                        _Area + "/" +
                        _Type + "/" +
                        _AreaLevel + ".csv";
		File _EstimatedFile = new File(_OutputFileName);
		_OutputDataHandler.writeEstimated_HSARHP(_InputFile, _EstimatedFile,
				_AreaLevel);

		// URL _BaseURL_1 = new URL(
		// "http://www.geog.leeds.ac.uk/people/a.turner/projects/MoSeS/documentation/demography/results/2001PopulationInitialisation/"
		// );
		URL _BaseURL_2 = new URL(
				"http://www.geog.leeds.ac.uk/people/a.turner/projects/MoSeS/documentation/demography/results/2001PopulationInitialisation/"
						+ _ErrorFunction
						+ "/"
						+ _OutputName
						+ "/"
						+ _Area
						+ "/" + _Type + "/" + _AreaLevel + ".xhtml2.0.html");
		int _Chart_Width = 500;
		int _Chart_Height = 500;
		outputImages(_ObservedFile, _EstimatedFile, _EstimatedFile
				.getParentFile(), _Chart_Width, _Chart_Height);
		_OutputFileName = _OutputDirectoryName_2 + "/" + _Area + "/"
                        + _Type + "/" + _AreaLevel + ".xhtml2.0.html";
		writeHTML(_BaseURL_2, _OutputName + " " + _Area + " " + _Type + " "
				+ _AreaLevel, new File(_OutputFileName));
	}

	public void outputImages(File _File_Observed, File _File_Expected,
			File _OutputDirectory, int _Chart_Width, int _Chart_Height)
			throws Exception {
		Object[] _Data = loadData(_File_Expected, _File_Observed);
		this._Variables = (String[]) _Data[0];
		double[][] _SARExpectedData = (double[][]) _Data[1];
		double[][] _CASObservedData = (double[][]) _Data[2];
		String xAxisLabel = "CAS Estimation (Observed)";
		String yAxisLabel = "SAR Prediction (Expected)";
		JFreeChart[] _ScatterPlots = createScatterPlots(this._Variables,
				_SARExpectedData, _CASObservedData, xAxisLabel, yAxisLabel);
		Object[] _RegressionParametersAndCreateXYLineCharts = getRegressionParametersAndCreateXYLineCharts(
				this._Variables, _SARExpectedData, _CASObservedData);
		JFreeChart[] _RegressionCharts = createRegressionCharts(_ScatterPlots,
				_RegressionParametersAndCreateXYLineCharts);
		String type = "PNG";
		outputImages(_RegressionCharts, this._Variables, _Chart_Width,
				_Chart_Height, this._AreaLevel, _OutputDirectory, type);
	}

	public void runOA(String baseURL, String directoryCASObserved,
			String directory, String filenamePrefix, String filenameSuffix,
			String aOutputName, int int_MainBodyControlSwitch) throws Exception {
		String filenameMidfix = "_" + aOutputName + "OA";
		Object[] _Data = loadData(new File(directory + filenamePrefix,
				filenamePrefix + filenameMidfix + ".csv"), new File(
				directoryCASObserved, aOutputName + "OA.csv"));
		String[] _Variables = (String[]) _Data[0];
		double[][] _SARExpectedData = (double[][]) _Data[1];
		double[][] _CASObservedData = (double[][]) _Data[2];
		String xAxisLabel = "CAS Estimation (Observed)";
		String yAxisLabel = "SAR Prediction (Expected)";
		JFreeChart[] _ScatterPlots = createScatterPlots(_Variables,
				_SARExpectedData, _CASObservedData, xAxisLabel, yAxisLabel);
		Object[] _RegressionParametersAndCreateXYLineCharts = getRegressionParametersAndCreateXYLineCharts(
				_Variables, _SARExpectedData, _CASObservedData);
		JFreeChart[] _RegressionCharts = createRegressionCharts(_ScatterPlots,
				_RegressionParametersAndCreateXYLineCharts);
		String type = "PNG";
		// outputImages( _ScatterPlots, directory + filenamePrefix +
		// "_ScatterPlot", type );
		// outputImages( _XYLineCharts, directory + filenamePrefix + "/" +
		// filenamePrefix + "_XYLineChart", type );
		outputImages(_RegressionCharts, 500, 500, directory + filenamePrefix
				+ "/", filenamePrefix + filenameMidfix + "_RegressionChart",
				type);
		writeHTML(baseURL, directory + filenamePrefix + "/", filenamePrefix
				+ filenameMidfix, filenameSuffix, int_MainBodyControlSwitch);
	}

	public void writeHTMLBodyMain(byte[] _LineSeparator, URL _BaseURL,
			FileOutputStream a_FileOutputStream) throws IOException {
		writeHTMLBodyMain1(_LineSeparator, _BaseURL, a_FileOutputStream);
	}

	public void writeHTMLBodyMain(byte[] _LineSeparator, String _BaseURL,
			String filenamePrefix, String filenameSuffix,
			FileOutputStream a_FileOutputStream, int int_MainBodyControlSwitch)
			throws IOException {
		if (int_MainBodyControlSwitch == 1) {
			writeHTMLBodyMain1(_LineSeparator, _BaseURL, filenamePrefix,
					filenameSuffix, a_FileOutputStream);
		}
	}

	public void writeHTMLBodyMain1(byte[] _LineSeparator, String _BaseURL,
			String filenamePrefix, String filenameSuffix,
			FileOutputStream a_FileOutputStream) throws IOException {
		a_FileOutputStream.write(_LineSeparator);
		a_FileOutputStream.write("<div>".getBytes());
		a_FileOutputStream.write(_LineSeparator);
		a_FileOutputStream.write("<ul>".getBytes());
		a_FileOutputStream.write(_LineSeparator);
		a_FileOutputStream.write(("<li><h2>" + filenamePrefix
                        + "</h2>").getBytes());
		a_FileOutputStream.write(_LineSeparator);
		a_FileOutputStream.write("<ul>".getBytes());
		a_FileOutputStream.write(_LineSeparator);
		a_FileOutputStream.write("<li><h3>Control constraints</h3>"
				.getBytes());
		a_FileOutputStream.write(_LineSeparator);
		a_FileOutputStream.write("<ul>".getBytes());
		for (int i = 0; i < 38; i++) {
			a_FileOutputStream.write(_LineSeparator);
			a_FileOutputStream.write(("<li><img src=\""
                                + filenamePrefix + "_RegressionChart" + i
                                + ".PNG\" /></li>").getBytes());
			a_FileOutputStream.write(_LineSeparator);
		}
		a_FileOutputStream.write("</ul></li>".getBytes());
		a_FileOutputStream.write(_LineSeparator);
		a_FileOutputStream.write("</ul>".getBytes());
		a_FileOutputStream.write(_LineSeparator);
		a_FileOutputStream.write("</div>".getBytes());
		a_FileOutputStream.write(_LineSeparator);
	}

	public void writeHTMLBodyMain1(byte[] _LineSeparator, URL _BaseURL,
			FileOutputStream a_FileOutputStream) throws IOException {
		a_FileOutputStream.write(_LineSeparator);
		a_FileOutputStream.write("<div>".getBytes());
		a_FileOutputStream.write(_LineSeparator);
		a_FileOutputStream.write("<ul>".getBytes());
		a_FileOutputStream.write(_LineSeparator);
		a_FileOutputStream.write(("<li><h2>" + this._Type + "</h2>")
				.getBytes());
		a_FileOutputStream.write(_LineSeparator);
		a_FileOutputStream.write("<ul>".getBytes());
		for (int i = 0; i < this._Variables.length - 1; i++) {
			a_FileOutputStream.write(_LineSeparator);
			a_FileOutputStream.write(("<li><img src=\""
                                + this._AreaLevel + this._Variables[i + 1]
                                + ".PNG\" /></li>").getBytes());
			a_FileOutputStream.write(_LineSeparator);
		}
		a_FileOutputStream.write("</ul></li>".getBytes());
		a_FileOutputStream.write(_LineSeparator);
		a_FileOutputStream.write("</ul>".getBytes());
		a_FileOutputStream.write(_LineSeparator);
		a_FileOutputStream.write("</div>".getBytes());
		a_FileOutputStream.write(_LineSeparator);
	}
}
