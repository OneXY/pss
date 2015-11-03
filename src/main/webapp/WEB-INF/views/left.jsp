<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<link href="/js/zTree/css/zTreeStyle/zTreeStyle.css" rel="stylesheet" type="text/css"/>
<script type="text/javascript" src="/js/jquery.js"></script>
<script type="text/javascript" src="/js/zTree/jquery.ztree.core-3.4.js"></script>
<script type="text/javascript">
<!--
	var setting = {
		async : {
			enable : true,
			url : "left.action",
			autoParam : ["id"],
// 			autoParam : ["id","name=n","level=lv"],
// 			otherParam : {"otherParam":"zTreeAsyncTest"},
			dataFilter : filter
		}
	};

	function filter(treeId, parentNode, childNodes) {
		if(!childNodes) return null;
		for (var i=0; i<childNodes.length; i++){
			childNodes[i].name = childNodes[i].name.replace(/\.n/g,'.');
		}
		return childNodes;
	}
	
	$(document).ready(function(){
		$.fn.zTree.init($("#treeDemo"),setting);
	});
</script>
<div class="left">
<div style="margin-left:2px;"><img src="images/left-top.gif" width="162" height="25"></div>
<div class="left-bottom">
<!-- <div class="left-text"> -->
<div class="zTreeDemoBackground left">
	<ul id="treeDemo" class="ztree"></ul>
</div>
<!-- </div> -->
</div><!--"left-bottom"end-->
</div><!--"left"end-->