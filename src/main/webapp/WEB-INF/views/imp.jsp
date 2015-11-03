<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<link href="/js/validation/css/screen.css" rel="stylesheet" type="text/css" media="screen"/>
<script type="text/javascript" src="/js/jquery.js"></script>
<script type="text/javascript" src="/js/validation/jquery.validate.js"></script>
<script type="text/javascript" src="/js/validation/messages_cn.js"></script>
<script type="text/javascript">
$().ready(function() {
	$("#impForm").validate({
		rules : {
			upload : {
				required : true,
				accept : "xls"
			}
		},
		messages : {
			upload : {
				required : "请选择xls文件",
				accept : "文件后缀限制为xls"
			}
		}
	});
});
</script>
<s:form id="impForm" action="imp_upload" enctype="multipart?form-data">
请选择上传的文件对应的表
	<s:select list="#{1:'员工表',2:'部门表'}" name="type"/>
	请选择表对应的文件<s:file name="upload"/>
	<s:submit value="上传"/>
	<s:if test="count>0">
		成功导入${count}条	
	</s:if>
</s:form>