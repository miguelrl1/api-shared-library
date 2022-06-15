def call(Map config=[:]) {
  
  def rawBody = libraryResource 'com/devopsbox/api/jira/addComment.json'
  def binding = [
    key: "${config.key}",
    body: "${config.body}"
  ]
  def render = renderTemplate(rawBody,binding)
  def issueId = "${config.key}"

  sh('curl -x $URL_PROXY -D- -u $JIRA_CREDENTIALS -X POST --data "'+render+'" -H "Content-Type: application/json" $JIRA_URL/rest/api/2/issue/"'+issueId+'"/comment')
}
