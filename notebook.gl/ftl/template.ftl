= Note List<br/>
notebook@notebook.fr<br/>
:context: misc<br/>
:project: misc<br/>
<br/>
* All head infos<br/>
	<!--assign指令用于在页面上定义一个变量,这儿定义了一个名称为text的json格式变量，下面普通的json解析是属于后台传递过来的-->
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
