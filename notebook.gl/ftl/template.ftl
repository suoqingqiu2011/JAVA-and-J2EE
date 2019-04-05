= Note List<br/>
notebook@notebook.fr<br/>
:context: misc<br/>
:project: misc<br/>
<br/>
* All head infos<br/>
	<#assign text>
		${info}
	</#assign>
	<#assign json=text?eval />
	<#list json as title,json1>
		Title:${title}<br/>
		<#list json1 as k,v>
		${k} ${v}<br/>
		</#list>
		<br/>
	</#list>
