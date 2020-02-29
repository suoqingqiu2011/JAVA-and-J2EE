= Note List
notebook@notebook.fr
:context: misc
:project: misc

	<#assign text>
		${info}
	</#assign>
	<#assign json=text?eval />
	<#list json as title,json1>
		* ${title} *Context:* ${json1.Context} *Project:* ${json1.Project} *Author:* ${json1.Author} *Date:* ${json1.Date}
	</#list>
