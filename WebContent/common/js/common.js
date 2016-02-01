function openwindow(url,name,iWidth,iHeight)
{
var url; //转向网页的地址;
var name; //网页名称，可为空;
var iWidth; //弹出窗口的宽度;
var iHeight; //弹出窗口的高度;
var iTop = (window.screen.availHeight-30-iHeight)/2; //获得窗口的垂直位置;
var iLeft = (window.screen.availWidth-10-iWidth)/2; //获得窗口的水平位置;
var re =  window.showModalDialog(url,name,'height='+iHeight+',,innerHeight='+iHeight+',width='+iWidth+',innerWidth='+iWidth+',top='+iTop+',left='+iLeft+',toolbar=no,menubar=no,scrollbars=auto,resizeable=no,location=no,status=no');
return re ;
}
//所有提交，查询等操作按钮走的JS校验
function checkStaffFuncButton(button_id,obj){
     // 按钮的ID  -- button_id
    //（1） 该处后面改造，从登录的session中获取该员工的域权限，按后与按钮ID核对是否可以操作

    return true ;
}
// 将空值替换为-9
function replaceNullValToNumber9(val){
   if(val == '' || val == undefined || val=='null'||val == null){
       val = '-9' ;
   }
   return val ;
}
// 将-9替换为空
function replaceF9ValToNull(val){
   if(parseInt(val) == -9 ){
       val = '' ;
   }
   return val ;
}
//将-9替换为不详
function replaceF9ValToUnknown(val){
   if(parseInt(val) == -9 ){
       val = '不详' ;
   }
   return val ;
}
function clrThisPage(){
      window.location.reload();
   }

function replaceNullValToNumberPlus1(val){
   if(val == '' || val == undefined || val=='null'||val == null){
       val = '-1' ;
   } 
   return val ;
}
function replaceNullValToOtherVal(val,toVal){
   if(val == '' || val == undefined || val=='null'||val == null){
       val = toVal ;
   }
   return val ;
}
function clrPage(){
   window.location.reload();   
}
// 返回处理结果insert update delete 等
function alertUpOrInOver(ttDom,plusMes){
       var res = ttDom.selectSingleNode('./root/res').text;
       var msg = ttDom.selectSingleNode('./root/msg').text;
       if(plusMes == '' ||plusMes ==undefined||plusMes == null){
          alert(msg);
       }else{
          alert(msg+":"+plusMes);
       }
       
          window.location.reload();
          return ;
}

function inOrUpdateData(url,edom){
	   var rpc = new XmlRPC(url);	
	    rpc.sendXml(edom);
		var dom = rpc.getXml();
		return dom ;
	}
//
function AjaxExchangeBackXMLData(url,edom){
   var rpc = new XmlRPC(url);	
    rpc.sendXml(edom);
	var dom = rpc.getXml();
	return dom ;
}
// 统一入口
function AjaxExchangeBackXMLDataByXML(url,edom){
	   var rpc = new XmlRPC(url);	
	    rpc.sendXml(edom);
		var dom = rpc.getXml();
		return dom ;
	}
// 统一提示
function alertMsgBoxByXmlData(resultXml){
	var res = resultXml.selectSingleNode('./root/res').text;
    var msg = resultXml.selectSingleNode('./root/msg').text;
    if(res == '1'){
    	alertMsg.error(msg);
    }else if(res=='2'){
    	alertMsg.correct(msg);
    }else if(res=='3'){
    	alertMsg.info(msg);    	
    }else{
    	alertMsg.warn(msg);
    }
}
// 提问后，调用的方法
function AjaxExchangeBackXMLDataByJson(url,edom){
	   var rpc = new XmlRPC(url+"?context="+edom);	
	    rpc.sendText();
		var xml= rpc.getXml();
		return xml ;
	}
function AjaxExchangeBackTextData(url,edom){
	   var rpc = new XmlRPC(url+"?context="+edom);	
	    rpc.sendText();
		var text= rpc.getText();
		return text ;
	}
function AjaxExchangeBackTextDataV1(url,edom){
	   var rpc = new XmlRPC(url);	
	    rpc.sendXml(edom);
		var text= rpc.getText();
		return text ;
	}
function AjaxExchangeBackTextDataV2(url,eJson){ 
	var resultData = "";
	$.post(url,"context="+eJson,function(data){
		resultData = data; 
	});	
	   return resultData ;
 }

function AjaxExchangeBackXMLDataAndAlert(url,edom){
	   var rpc = new XmlRPC(url);	
	    rpc.sendXml(edom);
		var dom = rpc.getXml();
		var res = ttDom.selectSingleNode('./root/res').text;
	    var msg = ttDom.selectSingleNode('./root/msg').text;
	    if(res == '1'){
	    	alertMsg.error(msg);
	    }else if(res=='2'){
	    	alertMsg.correct(msg);
	    }else if(res=='3'){
	    	alertMsg.info(msg);
	    }else{
	    	alertMsg.warn(msg);
	    }
	}
function renderBtnsToDiv(obj,action,edom){
	var text = AjaxExchangeBackTextData(action,edom);
	obj.html(text);
}
function alertToUserMsg(json){
	var res = json.res;
    var msg = json.msg;
    if(res == '1'){
    	alertMsg.error(msg);
    }else if(res=='2'){
    	alertMsg.correct(msg);
    }else if(res=='3'){
    	alertMsg.info(msg);
    }else{
    	alertMsg.warn(msg);
    }
}
// 文件上传
function ajaxFileUpload(foregin_id)
{ 
	$("#loading")
	.ajaxStart(function(){
		$(this).show();
	})//开始上传文件时显示一个图片
	.ajaxComplete(function(){
		$(this).hide();
	});//文件上传完成将图片隐藏起来
	
	$.ajaxFileUpload
	(
		{
			url:'fileUploadAction.action',//用于文件上传的服务器端请求地址
			secureuri:false,//一般设置为false
			fileElementId:'file',//文件上传空间的id属性  <input type="file" id="file" name="file" />
			dataType: 'json',//返回值类型 一般设置为json 
			success: function (data, status)  //服务器成功响应处理函数
			{
				if(typeof(data.error) != 'undefined')
				{
					if(data.error != '')
					{
						alertMsg.error(data.error);
					}else
					{						
						 var edom = createParamDom3(foregin_id, data.message ,data.beforeName);  
						 var ttDom = AjaxExchangeBackXMLDataByXML('createForeignAndAttachmentRelation.action',edom);   
						 alertMsg.correct("文件上传成功");
					}
				}
			},
			error: function (data, status, e)//服务器响应失败处理函数
			{
				alertMsg.error(e);
			}
		}
	)		
	return false;

}
function execTurnJson(url,edom){
   var rpc = new XmlRPC(url);	
    rpc.sendXml(edom);
	var dom = rpc.getText();
	return dom ;
}
function execTurnXml(url,edom){
   var rpc = new XmlRPC(url);	
    rpc.sendXml(edom);
	var dom = rpc.getXml();
	return dom ;
}
// 检查时候登录信息session过时
function isSessionValidate(user){
    if(user == ''||user == 'null'||user == null|| user == undefined){
       alert('登录信息已经失效，请重新登录') ;
       return false ;
    }
    return true ;
}
// 请选择选项
function addPleaseCheck(control){
	// 添加---请选择---
	var option = document.createElement("<option value='-9'></option>");
	option.appendChild(document.createTextNode("--请选择--"));
	control.appendChild(option);
	}
// 创建不同个数参数的DOM
function createParamDom1(
                          param1val){
        var reDom = f_createDom(); 
        var rootNode = reDom.createElement("root");
        var param1Node = reDom.createElement("param1");
        
        if (param1val != null)
		{
			param1Node.text = param1val;
		}
		rootNode.appendChild(param1Node);
  
  		reDom.appendChild(rootNode);
  		return reDom ;
} 
function createParamDom2(
                           param1val,
                           param2val){
        var reDom = f_createDom(); 
        var rootNode = reDom.createElement("root");
        var param1Node = reDom.createElement("param1");
        var param2Node = reDom.createElement("param2");
        if (param1val != null)
		{
			param1Node.text = param1val;
		}
		if (param2val != null)
		{
			param2Node.text = param2val;
		}
		rootNode.appendChild(param1Node);
		rootNode.appendChild(param2Node);
  
  		reDom.appendChild(rootNode);
  		return reDom ;
} 

function createParamDom3(
                           param1val,
                           param2val,
                           param3val){
        var reDom = f_createDom(); 
        var rootNode = reDom.createElement("root");
        var param1Node = reDom.createElement("param1");
        var param2Node = reDom.createElement("param2");
        var param3Node = reDom.createElement("param3");
        if (param1val != null)
		{
			param1Node.text = param1val;
		}
		if (param2val != null)
		{
			param2Node.text = param2val;
		}
		if (param3val != null)
		{
			param3Node.text = param3val;
		}
		rootNode.appendChild(param1Node);
		rootNode.appendChild(param2Node);
        rootNode.appendChild(param3Node);
  		reDom.appendChild(rootNode);
  		return reDom ;
} 
function createParamDom4(
                           param1val,
                           param2val,
                           param3val,
                           param4val){
        var reDom = f_createDom(); 
        var rootNode = reDom.createElement("root");
        var param1Node = reDom.createElement("param1");
        var param2Node = reDom.createElement("param2");
        var param3Node = reDom.createElement("param3");
        var param4Node = reDom.createElement("param4");
        if (param1val != null)
		{
			param1Node.text = param1val;
		}
		if (param2val != null)
		{
			param2Node.text = param2val;
		}
		if (param3val != null)
		{
			param3Node.text = param3val;
		}
		if (param4val != null)
		{
			param4Node.text = param4val;
		}
		rootNode.appendChild(param1Node);
		rootNode.appendChild(param2Node);
        rootNode.appendChild(param3Node);
        rootNode.appendChild(param4Node);
  		reDom.appendChild(rootNode);
  		return reDom ;
} 
function createParamDom5(
                           param1val,
                           param2val,
                           param3val,
                           param4val,
                           param5val){
        var reDom = f_createDom(); 
        var rootNode = reDom.createElement("root");
        var param1Node = reDom.createElement("param1");
        var param2Node = reDom.createElement("param2");
        var param3Node = reDom.createElement("param3");
        var param4Node = reDom.createElement("param4");
        var param5Node = reDom.createElement("param5");
        if (param1val != null)
		{
			param1Node.text = param1val;
		}
		if (param2val != null)
		{
			param2Node.text = param2val;
		}
		if (param3val != null)
		{
			param3Node.text = param3val;
		}
		if (param4val != null)
		{
			param4Node.text = param4val;
		}
		if (param5val != null)
		{
			param5Node.text = param5val;
		}
		rootNode.appendChild(param1Node);
		rootNode.appendChild(param2Node);
        rootNode.appendChild(param3Node);
        rootNode.appendChild(param4Node);
        rootNode.appendChild(param5Node);
  		reDom.appendChild(rootNode);
  		return reDom ;
} 
function createParamDom6(
                           param1val,
                           param2val,
                           param3val,
                           param4val,
                           param5val,
                           param6val){
        var reDom = f_createDom(); 
        var rootNode = reDom.createElement("root");
        var param1Node = reDom.createElement("param1");
        var param2Node = reDom.createElement("param2");
        var param3Node = reDom.createElement("param3");
        var param4Node = reDom.createElement("param4");
        var param5Node = reDom.createElement("param5");
        var param6Node = reDom.createElement("param6");
        if (param1val != null)
		{
			param1Node.text = param1val;
		}
		if (param2val != null)
		{
			param2Node.text = param2val;
		}
		if (param3val != null)
		{
			param3Node.text = param3val;
		}
		if (param4val != null)
		{
			param4Node.text = param4val;
		}
		if (param5val != null)
		{
			param5Node.text = param5val;
		}
		if (param6val != null)
		{
			param6Node.text = param6val;
		}
		rootNode.appendChild(param1Node);
		rootNode.appendChild(param2Node);
        rootNode.appendChild(param3Node);
        rootNode.appendChild(param4Node);
        rootNode.appendChild(param5Node);
        rootNode.appendChild(param6Node);
  		reDom.appendChild(rootNode);
  		return reDom ;
} 
function createParamDom7(
                           param1val,
                           param2val,
                           param3val,
                           param4val,
                           param5val,
                           param6val,
                           param7val){
        var reDom = f_createDom(); 
        var rootNode = reDom.createElement("root");
        var param1Node = reDom.createElement("param1");
        var param2Node = reDom.createElement("param2");
        var param3Node = reDom.createElement("param3");
        var param4Node = reDom.createElement("param4");
        var param5Node = reDom.createElement("param5");
        var param6Node = reDom.createElement("param6");
        var param7Node = reDom.createElement("param7");
        if (param1val != null)
		{
			param1Node.text = param1val;
		}
		if (param2val != null)
		{
			param2Node.text = param2val;
		}
		if (param3val != null)
		{
			param3Node.text = param3val;
		}
		if (param4val != null)
		{
			param4Node.text = param4val;
		}
		if (param5val != null)
		{
			param5Node.text = param5val;
		}
		if (param6val != null)
		{
			param6Node.text = param6val;
		}
		if (param7val != null)
		{
			param7Node.text = param7val;
		}
		rootNode.appendChild(param1Node);
		rootNode.appendChild(param2Node);
        rootNode.appendChild(param3Node);
        rootNode.appendChild(param4Node);
        rootNode.appendChild(param5Node);
        rootNode.appendChild(param6Node);
        rootNode.appendChild(param7Node);
  		reDom.appendChild(rootNode);
  		return reDom ;
} 
function createParamDom8(
                           param1val,
                           param2val,
                           param3val,
                           param4val,
                           param5val,
                           param6val,
                           param7val,
                           param8val){
        var reDom = f_createDom(); 
        var rootNode = reDom.createElement("root");
        var param1Node = reDom.createElement("param1");
        var param2Node = reDom.createElement("param2");
        var param3Node = reDom.createElement("param3");
        var param4Node = reDom.createElement("param4");
        var param5Node = reDom.createElement("param5");
        var param6Node = reDom.createElement("param6");
        var param7Node = reDom.createElement("param7");
        var param8Node = reDom.createElement("param8");
        if (param1val != null)
		{
			param1Node.text = param1val;
		}
		if (param2val != null)
		{
			param2Node.text = param2val;
		}
		if (param3val != null)
		{
			param3Node.text = param3val;
		}
		if (param4val != null)
		{
			param4Node.text = param4val;
		}
		if (param5val != null)
		{
			param5Node.text = param5val;
		}
		if (param6val != null)
		{
			param6Node.text = param6val;
		}
		if (param7val != null)
		{
			param7Node.text = param7val;
		}
		if (param8val != null)
		{
			param8Node.text = param8val;
		}
		rootNode.appendChild(param1Node);
		rootNode.appendChild(param2Node);
        rootNode.appendChild(param3Node);
        rootNode.appendChild(param4Node);
        rootNode.appendChild(param5Node);
        rootNode.appendChild(param6Node);
        rootNode.appendChild(param7Node);
        rootNode.appendChild(param8Node);
  		reDom.appendChild(rootNode);
  		return reDom ;
} 

function createParamDom9(
                           param1val,
                           param2val,
                           param3val,
                           param4val,
                           param5val,
                           param6val,
                           param7val,
                           param8val,
                           param9val){
        var reDom = f_createDom(); 
        var rootNode = reDom.createElement("root");
        var param1Node = reDom.createElement("param1");
        var param2Node = reDom.createElement("param2");
        var param3Node = reDom.createElement("param3");
        var param4Node = reDom.createElement("param4");
        var param5Node = reDom.createElement("param5");
        var param6Node = reDom.createElement("param6");
        var param7Node = reDom.createElement("param7");
        var param8Node = reDom.createElement("param8");
        var param9Node = reDom.createElement("param9");
        if (param1val != null)
		{
			param1Node.text = param1val;
		}
		if (param2val != null)
		{
			param2Node.text = param2val;
		}
		if (param3val != null)
		{
			param3Node.text = param3val;
		}
		if (param4val != null)
		{
			param4Node.text = param4val;
		}
		if (param5val != null)
		{
			param5Node.text = param5val;
		}
		if (param6val != null)
		{
			param6Node.text = param6val;
		}
		if (param7val != null)
		{
			param7Node.text = param7val;
		}
		if (param8val != null)
		{
			param8Node.text = param8val;
		}
		if (param9val != null)
		{
			param9Node.text = param9val;
		}
		rootNode.appendChild(param1Node);
		rootNode.appendChild(param2Node);
        rootNode.appendChild(param3Node);
        rootNode.appendChild(param4Node);
        rootNode.appendChild(param5Node);
        rootNode.appendChild(param6Node);
        rootNode.appendChild(param7Node);
        rootNode.appendChild(param8Node);
        rootNode.appendChild(param9Node);
  		reDom.appendChild(rootNode);
  		return reDom ;
} 

function createParamDom10(
                           param1val,
                           param2val,
                           param3val,
                           param4val,
                           param5val,
                           param6val,
                           param7val,
                           param8val,
                           param9val,
                           param10val){
        var reDom = f_createDom(); 
        var rootNode = reDom.createElement("root");
        var param1Node = reDom.createElement("param1");
        var param2Node = reDom.createElement("param2");
        var param3Node = reDom.createElement("param3");
        var param4Node = reDom.createElement("param4");
        var param5Node = reDom.createElement("param5");
        var param6Node = reDom.createElement("param6");
        var param7Node = reDom.createElement("param7");
        var param8Node = reDom.createElement("param8");
        var param9Node = reDom.createElement("param9");
        var param10Node = reDom.createElement("param10");
        if (param1val != null)
		{
			param1Node.text = param1val;
		}
		if (param2val != null)
		{
			param2Node.text = param2val;
		}
		if (param3val != null)
		{
			param3Node.text = param3val;
		}
		if (param4val != null)
		{
			param4Node.text = param4val;
		}
		if (param5val != null)
		{
			param5Node.text = param5val;
		}
		if (param6val != null)
		{
			param6Node.text = param6val;
		}
		if (param7val != null)
		{
			param7Node.text = param7val;
		}
		if (param8val != null)
		{
			param8Node.text = param8val;
		}
		if (param9val != null)
		{
			param9Node.text = param9val;
		}
		if (param10val != null)
		{
			param10Node.text = param10val;
		}
		rootNode.appendChild(param1Node);
		rootNode.appendChild(param2Node);
        rootNode.appendChild(param3Node);
        rootNode.appendChild(param4Node);
        rootNode.appendChild(param5Node);
        rootNode.appendChild(param6Node);
        rootNode.appendChild(param7Node);
        rootNode.appendChild(param8Node);
        rootNode.appendChild(param9Node);
        rootNode.appendChild(param10Node);
  		reDom.appendChild(rootNode);
  		return reDom ;
} 

function createParamDom11(
                           param1val,
                           param2val,
                           param3val,
                           param4val,
                           param5val,
                           param6val,
                           param7val,
                           param8val,
                           param9val,
                           param10val,
                           param11val){
        var reDom = f_createDom(); 
        var rootNode = reDom.createElement("root");
        var param1Node = reDom.createElement("param1");
        var param2Node = reDom.createElement("param2");
        var param3Node = reDom.createElement("param3");
        var param4Node = reDom.createElement("param4");
        var param5Node = reDom.createElement("param5");
        var param6Node = reDom.createElement("param6");
        var param7Node = reDom.createElement("param7");
        var param8Node = reDom.createElement("param8");
        var param9Node = reDom.createElement("param9");
        var param10Node = reDom.createElement("param10");
        var param11Node = reDom.createElement("param11");
        if (param1val != null)
		{
			param1Node.text = param1val;
		}
		if (param2val != null)
		{
			param2Node.text = param2val;
		}
		if (param3val != null)
		{
			param3Node.text = param3val;
		}
		if (param4val != null)
		{
			param4Node.text = param4val;
		}
		if (param5val != null)
		{
			param5Node.text = param5val;
		}
		if (param6val != null)
		{
			param6Node.text = param6val;
		}
		if (param7val != null)
		{
			param7Node.text = param7val;
		}
		if (param8val != null)
		{
			param8Node.text = param8val;
		}
		if (param9val != null)
		{
			param9Node.text = param9val;
		}
		if (param10val != null)
		{
			param10Node.text = param10val;
		}
		if (param11val != null)
		{
			param11Node.text = param11val;
		}
		rootNode.appendChild(param1Node);
		rootNode.appendChild(param2Node);
        rootNode.appendChild(param3Node);
        rootNode.appendChild(param4Node);
        rootNode.appendChild(param5Node);
        rootNode.appendChild(param6Node);
        rootNode.appendChild(param7Node);
        rootNode.appendChild(param8Node);
        rootNode.appendChild(param9Node);
        rootNode.appendChild(param10Node);
        rootNode.appendChild(param11Node);
  		reDom.appendChild(rootNode);
  		return reDom ;
} 
// 12 param
function createParamDom12(
                           param1val,
                           param2val,
                           param3val,
                           param4val,
                           param5val,
                           param6val,
                           param7val,
                           param8val,
                           param9val,
                           param10val,
                           param11val,
                           param12val){
        var reDom = f_createDom(); 
        var rootNode = reDom.createElement("root");
        var param1Node = reDom.createElement("param1");
        var param2Node = reDom.createElement("param2");
        var param3Node = reDom.createElement("param3");
        var param4Node = reDom.createElement("param4");
        var param5Node = reDom.createElement("param5");
        var param6Node = reDom.createElement("param6");
        var param7Node = reDom.createElement("param7");
        var param8Node = reDom.createElement("param8");
        var param9Node = reDom.createElement("param9");
        var param10Node = reDom.createElement("param10");
        var param11Node = reDom.createElement("param11");
        var param12Node = reDom.createElement("param12");
        if (param1val != null)
		{
			param1Node.text = param1val;
		}
		if (param2val != null)
		{
			param2Node.text = param2val;
		}
		if (param3val != null)
		{
			param3Node.text = param3val;
		}
		if (param4val != null)
		{
			param4Node.text = param4val;
		}
		if (param5val != null)
		{
			param5Node.text = param5val;
		}
		if (param6val != null)
		{
			param6Node.text = param6val;
		}
		if (param7val != null)
		{
			param7Node.text = param7val;
		}
		if (param8val != null)
		{
			param8Node.text = param8val;
		}
		if (param9val != null)
		{
			param9Node.text = param9val;
		}
		if (param10val != null)
		{
			param10Node.text = param10val;
		}
		if (param11val != null)
		{
			param11Node.text = param11val;
		}
		if (param12val != null)
		{
			param12Node.text = param12val;
		}
		rootNode.appendChild(param1Node);
		rootNode.appendChild(param2Node);
        rootNode.appendChild(param3Node);
        rootNode.appendChild(param4Node);
        rootNode.appendChild(param5Node);
        rootNode.appendChild(param6Node);
        rootNode.appendChild(param7Node);
        rootNode.appendChild(param8Node);
        rootNode.appendChild(param9Node);
        rootNode.appendChild(param10Node);
        rootNode.appendChild(param11Node);
        rootNode.appendChild(param12Node);
  		reDom.appendChild(rootNode);
  		return reDom ;
} 
// 13 param
function createParamDom13(
                           param1val,
                           param2val,
                           param3val,
                           param4val,
                           param5val,
                           param6val,
                           param7val,
                           param8val,
                           param9val,
                           param10val,
                           param11val,
                           param12val,
                           param13val){
        var reDom = f_createDom(); 
        var rootNode = reDom.createElement("root");
        var param1Node = reDom.createElement("param1");
        var param2Node = reDom.createElement("param2");
        var param3Node = reDom.createElement("param3");
        var param4Node = reDom.createElement("param4");
        var param5Node = reDom.createElement("param5");
        var param6Node = reDom.createElement("param6");
        var param7Node = reDom.createElement("param7");
        var param8Node = reDom.createElement("param8");
        var param9Node = reDom.createElement("param9");
        var param10Node = reDom.createElement("param10");
        var param11Node = reDom.createElement("param11");
        var param12Node = reDom.createElement("param12");
        var param13Node = reDom.createElement("param13");
        if (param1val != null)
		{
			param1Node.text = param1val;
		}
		if (param2val != null)
		{
			param2Node.text = param2val;
		}
		if (param3val != null)
		{
			param3Node.text = param3val;
		}
		if (param4val != null)
		{
			param4Node.text = param4val;
		}
		if (param5val != null)
		{
			param5Node.text = param5val;
		}
		if (param6val != null)
		{
			param6Node.text = param6val;
		}
		if (param7val != null)
		{
			param7Node.text = param7val;
		}
		if (param8val != null)
		{
			param8Node.text = param8val;
		}
		if (param9val != null)
		{
			param9Node.text = param9val;
		}
		if (param10val != null)
		{
			param10Node.text = param10val;
		}
		if (param11val != null)
		{
			param11Node.text = param11val;
		}
		if (param12val != null)
		{
			param12Node.text = param12val;
		}
		if (param13val != null)
		{
			param13Node.text = param13val;
		}
		rootNode.appendChild(param1Node);
		rootNode.appendChild(param2Node);
        rootNode.appendChild(param3Node);
        rootNode.appendChild(param4Node);
        rootNode.appendChild(param5Node);
        rootNode.appendChild(param6Node);
        rootNode.appendChild(param7Node);
        rootNode.appendChild(param8Node);
        rootNode.appendChild(param9Node);
        rootNode.appendChild(param10Node);
        rootNode.appendChild(param11Node);
        rootNode.appendChild(param12Node);
        rootNode.appendChild(param13Node);
  		reDom.appendChild(rootNode);
  		return reDom ;
} 
// 14 param
function createParamDom14(
                           param1val,
                           param2val,
                           param3val,
                           param4val,
                           param5val,
                           param6val,
                           param7val,
                           param8val,
                           param9val,
                           param10val,
                           param11val,
                           param12val,
                           param13val,
                           param14val){
        var reDom = f_createDom(); 
        var rootNode = reDom.createElement("root");
        var param1Node = reDom.createElement("param1");
        var param2Node = reDom.createElement("param2");
        var param3Node = reDom.createElement("param3");
        var param4Node = reDom.createElement("param4");
        var param5Node = reDom.createElement("param5");
        var param6Node = reDom.createElement("param6");
        var param7Node = reDom.createElement("param7");
        var param8Node = reDom.createElement("param8");
        var param9Node = reDom.createElement("param9");
        var param10Node = reDom.createElement("param10");
        var param11Node = reDom.createElement("param11");
        var param12Node = reDom.createElement("param12");
        var param13Node = reDom.createElement("param13");
        var param14Node = reDom.createElement("param14");
        if (param1val != null)
		{
			param1Node.text = param1val;
		}
		if (param2val != null)
		{
			param2Node.text = param2val;
		}
		if (param3val != null)
		{
			param3Node.text = param3val;
		}
		if (param4val != null)
		{
			param4Node.text = param4val;
		}
		if (param5val != null)
		{
			param5Node.text = param5val;
		}
		if (param6val != null)
		{
			param6Node.text = param6val;
		}
		if (param7val != null)
		{
			param7Node.text = param7val;
		}
		if (param8val != null)
		{
			param8Node.text = param8val;
		}
		if (param9val != null)
		{
			param9Node.text = param9val;
		}
		if (param10val != null)
		{
			param10Node.text = param10val;
		}
		if (param11val != null)
		{
			param11Node.text = param11val;
		}
		if (param12val != null)
		{
			param12Node.text = param12val;
		}
		if (param13val != null)
		{
			param13Node.text = param13val;
		}
		if (param14val != null)
		{
			param14Node.text = param14val;
		}
		rootNode.appendChild(param1Node);
		rootNode.appendChild(param2Node);
        rootNode.appendChild(param3Node);
        rootNode.appendChild(param4Node);
        rootNode.appendChild(param5Node);
        rootNode.appendChild(param6Node);
        rootNode.appendChild(param7Node);
        rootNode.appendChild(param8Node);
        rootNode.appendChild(param9Node);
        rootNode.appendChild(param10Node);
        rootNode.appendChild(param11Node);
        rootNode.appendChild(param12Node);
        rootNode.appendChild(param13Node);
        rootNode.appendChild(param14Node);        
  		reDom.appendChild(rootNode);
  		return reDom ;
} 
//over
function createParamDom15(
                           param1val,
                           param2val,
                           param3val,
                           param4val,
                           param5val,
                           param6val,
                           param7val,
                           param8val,
                           param9val,
                           param10val,
                           param11val,
                           param12val,
                           param13val,
                           param14val,
                           param15val){
        var reDom = f_createDom(); 
        var rootNode = reDom.createElement("root");
        var param1Node = reDom.createElement("param1");
        var param2Node = reDom.createElement("param2");
        var param3Node = reDom.createElement("param3");
        var param4Node = reDom.createElement("param4");
        var param5Node = reDom.createElement("param5");
        var param6Node = reDom.createElement("param6");
        var param7Node = reDom.createElement("param7");
        var param8Node = reDom.createElement("param8");
        var param9Node = reDom.createElement("param9");
        var param10Node = reDom.createElement("param10");
        var param11Node = reDom.createElement("param11");
        var param12Node = reDom.createElement("param12");
        var param13Node = reDom.createElement("param13");
        var param14Node = reDom.createElement("param14");
        var param15Node = reDom.createElement("param15");
        if (param1val != null)
		{
			param1Node.text = param1val;
		}
		if (param2val != null)
		{
			param2Node.text = param2val;
		}
		if (param3val != null)
		{
			param3Node.text = param3val;
		}
		if (param4val != null)
		{
			param4Node.text = param4val;
		}
		if (param5val != null)
		{
			param5Node.text = param5val;
		}
		if (param6val != null)
		{
			param6Node.text = param6val;
		}
		if (param7val != null)
		{
			param7Node.text = param7val;
		}
		if (param8val != null)
		{
			param8Node.text = param8val;
		}
		if (param9val != null)
		{
			param9Node.text = param9val;
		}
		if (param10val != null)
		{
			param10Node.text = param10val;
		}
		if (param11val != null)
		{
			param11Node.text = param11val;
		}
		if (param12val != null)
		{
			param12Node.text = param12val;
		}
		if (param13val != null)
		{
			param13Node.text = param13val;
		}
		if (param14val != null)
		{
			param14Node.text = param14val;
		}
		if (param15val != null)
		{
			param15Node.text = param15val;
		}
		rootNode.appendChild(param1Node);
		rootNode.appendChild(param2Node);
        rootNode.appendChild(param3Node);
        rootNode.appendChild(param4Node);
        rootNode.appendChild(param5Node);
        rootNode.appendChild(param6Node);
        rootNode.appendChild(param7Node);
        rootNode.appendChild(param8Node);
        rootNode.appendChild(param9Node);
		rootNode.appendChild(param10Node);
        rootNode.appendChild(param11Node);
        rootNode.appendChild(param12Node);
        rootNode.appendChild(param13Node);
        rootNode.appendChild(param14Node);
        rootNode.appendChild(param15Node);
  		reDom.appendChild(rootNode);
  		return reDom ;
} 


function createParamDom16(
                           param1val,
                           param2val,
                           param3val,
                           param4val,
                           param5val,
                           param6val,
                           param7val,
                           param8val,
                           param9val,
                           param10val,
                           param11val,
                           param12val,
                           param13val,
                           param14val,
                           param15val,
                           param16val){
        var reDom = f_createDom(); 
        var rootNode = reDom.createElement("root");
        var param1Node = reDom.createElement("param1");
        var param2Node = reDom.createElement("param2");
        var param3Node = reDom.createElement("param3");
        var param4Node = reDom.createElement("param4");
        var param5Node = reDom.createElement("param5");
        var param6Node = reDom.createElement("param6");
        var param7Node = reDom.createElement("param7");
        var param8Node = reDom.createElement("param8");
        var param9Node = reDom.createElement("param9");
        var param10Node = reDom.createElement("param10");
        var param11Node = reDom.createElement("param11");
        var param12Node = reDom.createElement("param12");
        var param13Node = reDom.createElement("param13");
        var param14Node = reDom.createElement("param14");
        var param15Node = reDom.createElement("param15");
        var param16Node = reDom.createElement("param16");
        if (param1val != null)
		{
			param1Node.text = param1val;
		}
		if (param2val != null)
		{
			param2Node.text = param2val;
		}
		if (param3val != null)
		{
			param3Node.text = param3val;
		}
		if (param4val != null)
		{
			param4Node.text = param4val;
		}
		if (param5val != null)
		{
			param5Node.text = param5val;
		}
		if (param6val != null)
		{
			param6Node.text = param6val;
		}
		if (param7val != null)
		{
			param7Node.text = param7val;
		}
		if (param8val != null)
		{
			param8Node.text = param8val;
		}
		if (param9val != null)
		{
			param9Node.text = param9val;
		}
		if (param10val != null)
		{
			param10Node.text = param10val;
		}
		if (param11val != null)
		{
			param11Node.text = param11val;
		}
		if (param12val != null)
		{
			param12Node.text = param12val;
		}
		if (param13val != null)
		{
			param13Node.text = param13val;
		}
		if (param14val != null)
		{
			param14Node.text = param14val;
		}
		if (param15val != null)
		{
			param15Node.text = param15val;
		}
		if (param16val != null)
		{
			param16Node.text = param16val;
		}
		rootNode.appendChild(param1Node);
		rootNode.appendChild(param2Node);
        rootNode.appendChild(param3Node);
        rootNode.appendChild(param4Node);
        rootNode.appendChild(param5Node);
        rootNode.appendChild(param6Node);
        rootNode.appendChild(param7Node);
        rootNode.appendChild(param8Node);
        rootNode.appendChild(param9Node);
		rootNode.appendChild(param10Node);
        rootNode.appendChild(param11Node);
        rootNode.appendChild(param12Node);
        rootNode.appendChild(param13Node);
        rootNode.appendChild(param14Node);
        rootNode.appendChild(param15Node);
        rootNode.appendChild(param16Node);
  		reDom.appendChild(rootNode);
  		return reDom ;
} 
function createParamDom17(
                           param1val,
                           param2val,
                           param3val,
                           param4val,
                           param5val,
                           param6val,
                           param7val,
                           param8val,
                           param9val,
                           param10val,
                           param11val,
                           param12val,
                           param13val,
                           param14val,
                           param15val,
                           param16val,
                           param17val){
        var reDom = f_createDom(); 
        var rootNode = reDom.createElement("root");
        var param1Node = reDom.createElement("param1");
        var param2Node = reDom.createElement("param2");
        var param3Node = reDom.createElement("param3");
        var param4Node = reDom.createElement("param4");
        var param5Node = reDom.createElement("param5");
        var param6Node = reDom.createElement("param6");
        var param7Node = reDom.createElement("param7");
        var param8Node = reDom.createElement("param8");
        var param9Node = reDom.createElement("param9");
        var param10Node = reDom.createElement("param10");
        var param11Node = reDom.createElement("param11");
        var param12Node = reDom.createElement("param12");
        var param13Node = reDom.createElement("param13");
        var param14Node = reDom.createElement("param14");
        var param15Node = reDom.createElement("param15");
        var param16Node = reDom.createElement("param16");
        var param17Node = reDom.createElement("param17");
        if (param1val != null)
		{
			param1Node.text = param1val;
		}
		if (param2val != null)
		{
			param2Node.text = param2val;
		}
		if (param3val != null)
		{
			param3Node.text = param3val;
		}
		if (param4val != null)
		{
			param4Node.text = param4val;
		}
		if (param5val != null)
		{
			param5Node.text = param5val;
		}
		if (param6val != null)
		{
			param6Node.text = param6val;
		}
		if (param7val != null)
		{
			param7Node.text = param7val;
		}
		if (param8val != null)
		{
			param8Node.text = param8val;
		}
		if (param9val != null)
		{
			param9Node.text = param9val;
		}
		if (param10val != null)
		{
			param10Node.text = param10val;
		}
		if (param11val != null)
		{
			param11Node.text = param11val;
		}
		if (param12val != null)
		{
			param12Node.text = param12val;
		}
		if (param13val != null)
		{
			param13Node.text = param13val;
		}
		if (param14val != null)
		{
			param14Node.text = param14val;
		}
		if (param15val != null)
		{
			param15Node.text = param15val;
		}
		if (param16val != null)
		{
			param16Node.text = param16val;
		}
		if (param17val != null)
		{
			param17Node.text = param17val;
		}
		rootNode.appendChild(param1Node);
		rootNode.appendChild(param2Node);
        rootNode.appendChild(param3Node);
        rootNode.appendChild(param4Node);
        rootNode.appendChild(param5Node);
        rootNode.appendChild(param6Node);
        rootNode.appendChild(param7Node);
        rootNode.appendChild(param8Node);
        rootNode.appendChild(param9Node);
		rootNode.appendChild(param10Node);
        rootNode.appendChild(param11Node);
        rootNode.appendChild(param12Node);
        rootNode.appendChild(param13Node);
        rootNode.appendChild(param14Node);
        rootNode.appendChild(param15Node);
        rootNode.appendChild(param16Node);
        rootNode.appendChild(param17Node);
  		reDom.appendChild(rootNode);
  		return reDom ;
} 




function createParamDom19(
                           param1val,
                           param2val,
                           param3val,
                           param4val,
                           param5val,
                           param6val,
                           param7val,
                           param8val,
                           param9val,
                           param10val,
                           param11val,
                           param12val,
                           param13val,
                           param14val,
                           param15val,
                           param16val,
                           param17val,
                           param18val,
                           param19val){
        var reDom = f_createDom(); 
        var rootNode = reDom.createElement("root");
        var param1Node = reDom.createElement("param1");
        var param2Node = reDom.createElement("param2");
        var param3Node = reDom.createElement("param3");
        var param4Node = reDom.createElement("param4");
        var param5Node = reDom.createElement("param5");
        var param6Node = reDom.createElement("param6");
        var param7Node = reDom.createElement("param7");
        var param8Node = reDom.createElement("param8");
        var param9Node = reDom.createElement("param9");
        var param10Node = reDom.createElement("param10");
        var param11Node = reDom.createElement("param11");
        var param12Node = reDom.createElement("param12");
        var param13Node = reDom.createElement("param13");
        var param14Node = reDom.createElement("param14");
        var param15Node = reDom.createElement("param15");
        var param16Node = reDom.createElement("param16");
        var param17Node = reDom.createElement("param17");
        var param18Node = reDom.createElement("param18");
        var param19Node = reDom.createElement("param19");
        if (param1val != null)
		{
			param1Node.text = param1val;
		}
		if (param2val != null)
		{
			param2Node.text = param2val;
		}
		if (param3val != null)
		{
			param3Node.text = param3val;
		}
		if (param4val != null)
		{
			param4Node.text = param4val;
		}
		if (param5val != null)
		{
			param5Node.text = param5val;
		}
		if (param6val != null)
		{
			param6Node.text = param6val;
		}
		if (param7val != null)
		{
			param7Node.text = param7val;
		}
		if (param8val != null)
		{
			param8Node.text = param8val;
		}
		if (param9val != null)
		{
			param9Node.text = param9val;
		}
		if (param10val != null)
		{
			param10Node.text = param10val;
		}
		if (param11val != null)
		{
			param11Node.text = param11val;
		}
		if (param12val != null)
		{
			param12Node.text = param12val;
		}
		if (param13val != null)
		{
			param13Node.text = param13val;
		}
		if (param14val != null)
		{
			param14Node.text = param14val;
		}
		if (param15val != null)
		{
			param15Node.text = param15val;
		}
		if (param16val != null)
		{
			param16Node.text = param16val;
		}
		if (param17val != null)
		{
			param17Node.text = param17val;
		}
		if (param18val != null)
		{
			param18Node.text = param18val;
		}
		if (param19val != null)
		{
			param19Node.text = param19val;
		}
		rootNode.appendChild(param1Node);
		rootNode.appendChild(param2Node);
        rootNode.appendChild(param3Node);
        rootNode.appendChild(param4Node);
        rootNode.appendChild(param5Node);
        rootNode.appendChild(param6Node);
        rootNode.appendChild(param7Node);
        rootNode.appendChild(param8Node);
        rootNode.appendChild(param9Node);
		rootNode.appendChild(param10Node);
        rootNode.appendChild(param11Node);
        rootNode.appendChild(param12Node);
        rootNode.appendChild(param13Node);
        rootNode.appendChild(param14Node);
        rootNode.appendChild(param15Node);
        rootNode.appendChild(param16Node);
        rootNode.appendChild(param17Node);
        rootNode.appendChild(param18Node);
        rootNode.appendChild(param19Node);
  		reDom.appendChild(rootNode);
  		return reDom ;
} 

function createParamJson(paramArray ){  
    var obj = new Object(); 
  obj.param1 = paramArray[0]; 
		obj.param2 = paramArray[1]; 
		obj.param3 = paramArray[2]; 
		obj.param4 = paramArray[3]; 
		obj.param5 = paramArray[4]; 
		obj.param6 = paramArray[5]; 
		obj.param7 = paramArray[6]; 
		obj.param8 = paramArray[7]; 
		obj.param9 = paramArray[8]; 
		obj.param10 = paramArray[9];  
		obj.param11 = paramArray[10]; 
		obj.param12 =  paramArray[11];
		obj.param13 =  paramArray[12];
		obj.param14 =  paramArray[13];
		obj.param15 =  paramArray[14];
		obj.param16 =  paramArray[15];
		obj.param17 =  paramArray[16];
		obj.param18 =  paramArray[17];
		obj.param19 =  paramArray[18];
		obj.param20 =  paramArray[19];
		var lst=[];	 var json={};lst.push(obj);json['content']=lst; 
		return json ;
} 
//****************************************************************
// Description: sInputString 为输入字符串，iType为类型，分别为
// 0 - 去除前后空格; 1 - 去左边空格; 2 - 去右边空格
//****************************************************************
function cTrim(sInputString,iType)
{
var sTmpStr = ' '
var i = -1
if(iType == 0 || iType == 1)
{
while(sTmpStr == ' ')
{
++i
sTmpStr = sInputString.substr(i,1)
}
sInputString = sInputString.substring(i)
}
if(iType == 0 || iType == 2)
{
sTmpStr = ' '
i = sInputString.length
while(sTmpStr == ' ')
{
--i
sTmpStr = sInputString.substr(i,1)
}
sInputString = sInputString.substring(0,i+1)
}
return sInputString
}

// 
function initTableData(src){
    //var src = document.getElementById("tabApplyInfos");
	var rowCount = src.rows.length;
	for(var j=1;j<rowCount;j++)
	{
		src.deleteRow();
	}
 }
 // 统一使用检查className为空的
function checkPageItemNotNull(obj){

	for(var i =0;i<obj.length;i++){ 
		if(obj.eq(i).val() == ""  ){  
			return false;
		}
	}
	return true ;
}
 
function getOneSeqId(){
   var edom = createParamDom1( '-9'
    							);										
    var rpc = new XmlRPC("getOneSeqId.action");
    rpc.sendXml(edom);
	var dom = rpc.getXml();
	return dom.selectSingleNode('./root/seq').text ;
}

function takeTypeDataListV2(control,url,isSelect,edom){ 
	var json = AjaxExchangeBackTextData(url,edom);   
	json = JSON.parse(json);
	// 请选择
	if(isSelect == '1'){
		control.append("<option value='-9'>请选择</option>");
	} 
	for(var i=0;i< json.length;i++){
		control.append("<option value='"+json[i].id+"'>"+json[i].name+"</option>");
	}
 } 
function takeTypeDataListV4(control,json,isSelect){
	// 请选择
	if(isSelect == '1'){
		control.append("<option value='-9'>请选择</option>");
	}    
	for(var i=0;i< json.length;i++){
		control.append("<option value='"+json[i].id+"'>"+json[i].name+"</option>");
	}
 }
function takeTypeDataListV3(control,url,isSelect,edom){
	var json = AjaxExchangeBackTextData(url,edom);  
	json = JSON.parse(json);
	// 请选择
	if(isSelect == '1'){
		control.append("<option value='-9'>请选择</option>");
	}    
	for(var i=0;i< json.length;i++){
		control.append("<option value='"+json[i].id+"'>"+json[i].name+"</option>");
	}
 }
// 统一使用
function takeTypeDataList(control,url,isSelect,edom){
    var rpc = new XmlRPC(url);	
    rpc.sendXml(edom);
	var dom = rpc.getXml(); 
	// 请选择
	if(isSelect == '1'){
	   addPleaseCheck(control);
	}
	var nodes = dom.documentElement.childNodes ; 
	for(var i = 0;i <nodes.length;i+=2)	{
		var itemValue = nodes.item(i+1).text ;
		var option = document.createElement("<option value='" + nodes.item(i).text + "'></option>");
		option.appendChild(document.createTextNode(itemValue));
		control.appendChild(option);
	}
 }
function getTotalPageCnt(nodeslength,len){
    if(len >= nodeslength) {
       return 1;
     }  
    var ye = nodeslength%len ;
    if(ye == 0){
       return nodeslength / len ;
    }else{
       return Math.floor(nodeslength/len) +1 ;
    }
}


loadXML = function(xmlFile){
        var xmlDoc=null;
        //判断浏览器的类型
        //支持IE浏览器
        if(!window.DOMParser && window.ActiveXObject){
            var xmlDomVersions = ['MSXML.2.DOMDocument.6.0','MSXML.2.DOMDocument.3.0','Microsoft.XMLDOM'];
            for(var i=0;i<xmlDomVersions.length;i++){
                try{
                    xmlDoc = new ActiveXObject(xmlDomVersions[i]);
                    break;
                }catch(e){
                }
            }
        }
        //支持Mozilla浏览器
        else if(document.implementation && document.implementation.createDocument){
            try{
                /* document.implementation.createDocument('','',null); 方法的三个参数说明
                 * 第一个参数是包含文档所使用的命名空间URI的字符串； 
                 * 第二个参数是包含文档根元素名称的字符串； 
                 * 第三个参数是要创建的文档类型（也称为doctype）
                 */
                xmlDoc = document.implementation.createDocument('','',null);
            }catch(e){
            }
        }
        else{
            return null;
        }

        if(xmlDoc!=null){
            xmlDoc.async = false;
            xmlDoc.load(xmlFile);
        }
        return xmlDoc;
    }
    
    
    function isNullCheck(obj){
    
      if(obj==''||obj==null||obj=='undefined'||obj=='-1'||obj=='-9'){
          return true ;
      }
      return false ;
    }
    
 // Ajax 文件下载
    $.download = function(url, data, method){    // 获得url和data
    if( url && data ){ 
        // data 是 string 或者 array/object
        data = typeof data == 'string' ? data : jQuery.param(data);        // 把参数组装成 form的  input
        var inputs = '';
        jQuery.each(data.split('&'), function(){ 
            var pair = this.split('=');
            inputs+='<input type="hidden" name="'+ pair[0] +'" value="'+ pair[1] +'" />'; 
        });        // request发送请求
        jQuery('<form action="'+ url +'" method="'+ (method||'post') +'">'+inputs+'</form>')
        .appendTo('body').submit().remove();
    };
};


// 删除表头以外的行
function removeTableData(obj,cnt){ 
	obj.find("tr").remove();
}
// 创建一个20长度的数组Json,并做ajax请求
function createJsonAndAjax(action,arr, ff,dataFormat) {
	var eJson = createParamJson(arr);
	eJson = JSON.stringify(eJson);
	var args = {
		"context" : eJson
	};
	baidu.post(action, args, function(data) {
		ff(data)
	},  dataFormat);
}