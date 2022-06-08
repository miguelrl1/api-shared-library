def call(Map config=[:]) {
  def rawBody = libraryResource 'com/devopsbox/api/jira/transitionIssue.json'
  def binding = [
    key: "${config.key}",
    idTransition: "${config.id}"
  ]
  def render = renderTemplate(rawBody,binding)
  def issueId = binding[0]
  sh('curl -x $URL_PROXY -D- -u $JIRA_CREDENTIALS -X POST --data "'+render+'" -H "Content-Type: application/json" $JIRA_URL/rest/api/2/issue/"'+issueId+'"/transitions')
}
