
// DO NOT EDIT THIS BLOCK BELOW=== Parameters starts ===
// PLEASE DO NOT EDIT THIS FILE

import com.cloudbees.flowpdf.StepParameters

class SampleRESTProcedureParameters {
    /**
    * Label: Username, type: entry
    */
    String username

    static SampleRESTProcedureParameters initParameters(StepParameters sp) {
        SampleRESTProcedureParameters parameters = new SampleRESTProcedureParameters()

        def username = sp.getRequiredParameter('username').value
        parameters.username = username

        return parameters
    }
}
// DO NOT EDIT THIS BLOCK ABOVE ^^^=== Parameters ends, checksum: e542ed02fe7fec8863b823554d768852 ===
