<html>
<head>
<title><g:layoutTitle default="Grailsx" /></title>
<link rel="stylesheet" href="${resource(dir:'css',file:'main.css')}" />
<link rel="stylesheet" href="${resource(dir:'css',file:'styles.css')}" />
<link rel="shortcut icon"
	href="${resource(dir:'images',file:'deckview.jpg')}"
	type="image/x-icon" />
<g:layoutHead />
<g:javascript library="application" />
</head>
<body>

<div id="header">
<table>
	<tr>
		<td><img src="${resource(dir:'images',file:'deckView.jpg')}"
			alt="Deck Viewx" height="100" width="100" /></td>
		<td>
		<h1>Welcome to Book Library!</h1>
		<h1>We'll help you keep track of the books in your library.</h1>
		</td>
	</tr>
</table>
</div>
<div id="toolBar">
    <span class="menuButton"><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></span>
	<g:if test="${session.userId}">
	    <span class="menuButton"><g:link class="info" controller="user" action="logout">logout</g:link></span>
	</g:if>
	<g:else>
	    <span class="menuButton"><g:link class="info" controller="user" action="login">login</g:link></span>
	</g:else>
	<span class="menuButton"><g:link class="info" controller="author" action="list">authors</g:link></span>
	<span class="menuButton"><g:link class="info" controller="book" action="list">books</g:link></span>
	<g:if test="${session.userId}">
	    <span class="menuButton"><g:link class="info" controller="user" action="mylibrary">my library</g:link></span>
	</g:if>	
</div>
<g:layoutBody />
</body>
</html>