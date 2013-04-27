<%@ page import="uk.co.mb.pairstairs.Team" %>



<div class="fieldcontain ${hasErrors(bean: teamInstance, field: 'name', 'error')} required">
	<label for="name">
		<g:message code="team.name.label" default="Name" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="name" required="" value="${teamInstance?.name}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: teamInstance, field: 'coders', 'error')} ">
	<label for="coders">
		<g:message code="team.coders.label" default="Coders" />
		
	</label>
	<g:select name="coders" from="${uk.co.mb.pairstairs.Coder.list()}" multiple="multiple" optionKey="id" size="5" value="${teamInstance?.coders*.id}" class="many-to-many"/>
</div>

