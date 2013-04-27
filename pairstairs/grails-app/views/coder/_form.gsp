<%@ page import="uk.co.mb.pairstairs.Coder" %>



<div class="fieldcontain ${hasErrors(bean: coderInstance, field: 'name', 'error')} required">
	<label for="name">
		<g:message code="coder.name.label" default="Name" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="name" required="" value="${coderInstance?.name}"/>
</div>

