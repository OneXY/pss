var My = {};
function selectHotel(){  
    url = 'product_bill.action';  
    var hotelIdList = window.showModalDialog(url, "hotel", "dialogWidth:860px;dialogHeight:500px;help:no;resizable:no;center:yes;scroll:yes;status:no");  
    if(!has_showModalDialog) return;//chrome 返回 因为showModalDialog是阻塞的 open不一样;    
//    $("#content").append(hotelIdList);  
}  
function selectHotelChrome(json){//为打开的窗口定义方法，让打开的窗口关闭时通过window.opener赋值回来并执行  
//    $("#content").append(option);
	var tr = $(My).closest("tr");
	tr.find("input[code=productId]").val(json.id);
	tr.find("input[code=productName]").val(json.name);
	tr.find("input[code=itemPrice]").val(json.costPrice);
	tr.find("td[code=productColor]").html(json.color);
}

$().ready(function() {
	$("#itemTable img[code=searchProduct]").click(function(){
//		var vReturnValue = window.showModalDialog("product_bill.action","hotel","dialogHeight:550px;dialogWidth:850px;center:1;dialogTop:100px");
//		if(vReturnValue){
//			var tr = $(this).closest("tr");
//			tr.find("input[code=productId]").val(vReturnValue.id);
//			tr.find("input[code=productName]").val(vReturnValue.name);
//			tr.find("input[code=itemPrice]").val(vReturnValue.costPrice);
//			tr.find("td[code=productColor]").html(vReturnValue.color);
//		}
		My = this;
		selectHotel();
	});
	
	$("#addItem").click(function(){
		var tr = $("#itemTable tr:last").clone(true);
		tr.find("input[code=productId]").val("");
		tr.find("input[code=productName]").val("");
		tr.find("input[code=itemPrice]").val("");
		tr.find("input[code=itemNum]").val("");
		tr.find("input[code=itemDescs]").val("");
		
//		tr.find("input").each(function(index, domEle){
//			$(domEle).val("");
//		});//有问题，还未解决
		
		tr.find("td[code=itemAmount]").html("");
		tr.find("td[code=productColor]").html("");
		$("#itemTable").append(tr);
	});
	
	$("#sumbitBtn").click(function(){
		var flag=false;
		$("#itemTable tr:gt(0)").each(function(index, domEle){
			var tr = $(domEle);
			tr.find("input[code=productId]").attr("name","items["+index+"].product.id");
			tr.find("input[code=itemPrice]").attr("name","items["+index+"].price");
			tr.find("input[code=itemNum]").attr("name","items["+index+"].num");
			tr.find("input[code=itemDescs]").attr("name","items["+index+"].descs");
			var productId = tr.find("input[code=productId]").val();
			if(!flag&&productId==""){
				flag=true;
				alert("必须选择产品");
			}
			var itemPrice = tr.find("input[code=itemPrice]").val();
			if(!flag&&itemPrice==""){
				flag=true;
				alert("必须填写采购价格");
			}
			if(!flag&&!/^-?(?:\d+|\d{1,3}(?:,\d{3})+)(?:\.\d+)?$/.test(itemPrice)){
				flag=true;
				alert("采购价格格式不正确");
			}
			var itemNum = tr.find("input[code=itemNum]").val();
			if(!flag&&itemNum==""){
				flag=true;
				alert("必须填写采购数量");
			}
			if(!flag&&!/^-?(?:\d+|\d{1,3}(?:,\d{3})+)(?:\.\d+)?$/.test(itemNum)){
				flag=true;
				alert("采购数量格式不正确");
			}
		});
		if(!flag){
			$("#purchaseBillForm").submit();
		}
	});
	
	$("#itemTable input[code=deleteItem]").click(function(){
		if ($("#itemTable tr").size()>2) {
			var tr = $(this).closest("tr");
			tr.remove();
		}
	});
});