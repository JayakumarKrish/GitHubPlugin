import com.cloudbees.flowpdf.*

/**
* GitHubPlugin
*/
class GitHubPlugin extends FlowPlugin {

    @Override
    Map<String, Object> pluginInfo() {
        return [
                pluginName     : '@PLUGIN_KEY@',
                pluginVersion  : '@PLUGIN_VERSION@',
                configFields   : ['config'],
                configLocations: ['ec_plugin_cfgs'],
                defaultConfigValues: [:]
        ]
    }
// === check connection ends ===
/**
     * Auto-generated method for the procedure Sample REST Procedure/Sample REST Procedure
     * Add your code into this method and it will be called when step runs* Parameter: config* Parameter: username
     */
    def sampleRESTProcedure(StepParameters p, StepResult sr) {
        //End point and request body for creating pull request
        def pullReqBody = '{"head":"jayakumar","base":"main","title":"sample pull"}'
        def pullEndPoint = "https://api.github.com/repos/jaikrishOrg/HeareSightRepo/pulls"
        //End point and request body for creating release
        def releaseReqBody = '{"tag_name":"v1.0.0","name": "Release3","prerelease": true}'
        def releaseEndPoint = "https://api.github.com/repos/jaikrishOrg/HeareSightRepo/releases"
        SampleRESTProcedureParameters sp = SampleRESTProcedureParameters.initParameters(p)
        GitHubPluginRESTClient rest = genGitHubPluginRESTClient()
        Map restParams = [:]
        Map requestParams = p.asMap
        restParams.put('username', requestParams.get('username'))

        Object response = rest.getUser(restParams)
        log.info "Got response from server: $response"
        //TODO step result output parameters
        sr.apply()
        //To create pull request
        triggerGitHubEndPoint(pullReqBody, pullEndPoint)
        //To create release
        triggerGitHubEndPoint(releaseReqBody, releaseEndPoint)
    }

    def triggerGitHubEndPoint(String requestBody, String endPoint) {
        def data  = requestBody;
        def post = new URL(endPoint).openConnection() as HttpURLConnection;
        def credentials = "jayakumarkrish97@gmail.com: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"
        def encodedCredentials = Base64.getEncoder().encodeToString(credentials.getBytes())
        post.setRequestMethod("POST");
        post.setDoOutput(true);
        post.setRequestProperty('Accept', 'application/vnd.github.v3+json');
        post.setRequestProperty("Authorization", "Basic " + encodedCredentials.toString());
        post.setRequestProperty("Content-Type", "application/json");
        post.getOutputStream().write(data.getBytes("UTF-8"));
        def postRC = post.getResponseCode();
        println(postRC);
        println(post.getInputStream().getText());
    }

/**
     * This method returns REST Client object
     */
    GitHubPluginRESTClient genGitHubPluginRESTClient() {
        Context context = getContext()
        GitHubPluginRESTClient rest = GitHubPluginRESTClient.fromConfig(context.getConfigValues(), this)
        return rest
    }
// === step ends ===

}