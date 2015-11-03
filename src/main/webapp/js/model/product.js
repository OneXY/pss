$(function(){
	jQuery.validator.addMethod("equalNotTo", function(value, element, param) {
		return value != $(param).val();
		}, $.validator.format("两次输入不能相同!"));
});

var My={};
My.items={};
$().ready(function() {
	$("#productForm").validate({
		rules : {
			name : {
				required : true,
				minlength : 0,
				maxlength : 8,
				remote : "product_checkName.action?id="+$("#id").val()
			},
			upload : {
				accept : "png|jpe?g|gif"
			},
			"types.id":{
				equalNotTo:"#parent"				
			}
		},
		messages : {
			name : {
				required : "请输入用户名",
				minlength : "最小长度为{0}",
				maxlength : "最大的长度为{0}",
				remote:"产品名已存在"
			},
			upload:{
				accept:"必须上传图片"
			},
			"types.id":{
				equalNotTo:"必须选择产品类型"				
			}
		}
	});
	
	$("#parent").bind("change",function(){
		var children = document.getElementById("children");
		children.options.length=0;
		var pid = $(this).val();
		if(pid==-1){
			children.options.add(new Option("--请选择--",-1));
		}else{
			var cacheData = My.items[pid];//[]这里是取json(My.items)对象里面的自定义属性
			if(cacheData){
				for(var i=0;i<cacheData.length;i++){
					children.options.add(new Option(cacheData[i].name,cacheData[i].id));
				}
			}else{
				$.get("product_findChildren.action",{id : pid},function(data){
					My.items[pid]=data;//[]这里是定义json(My.items)对象里面的自定义属性
					console.debug(My.items[pid]);
					for(var i=0;i<data.length;i++){
						children.options.add(new Option(data[i].name,data[i].id));//[i]这里是取json数组(data)里面索引为i的json对象
					}
				});
			}
		}
	});
});