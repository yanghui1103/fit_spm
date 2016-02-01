// ControlPage.js
/*ҳ�����*/
var componentInfoArr = new Array();
var g_orderType = 0;
var framesTd = new Array();
framesTd[0] = 'baseInfoTD';
framesTd[1] = 'feeInfoTD';
framesTd[2] = 'nwkInfoTD';
framesTd[3] = 'acctInfoTD';
initFrameTD = 'baseInfoTD';
	
function ControlPageManager(){   
    this.controlAll = f_controlAll;				    //����ҳ��Ԫ��
    this.controlAppointEle = f_controlAppointEle;	//����ָ����Ԫ��
}

function f_controlAll(eventPageId,orderType,controlDomObject,isClick) {
	var controlAllElement = new ControlAllElement();
	return controlAllElement.controlInfo(eventPageId,orderType,controlDomObject,isClick);
}

function f_controlAppointEle(controlElement,isVisible,isDisable) {
	var controlAppointElement = new ControlAppointElement();
	return controlAppointElement.controlOneElement(controlElement,isVisible,isDisable);
}

//��������
function PublicContol() {
}
PublicContol.prototype.findRec = function f_findRec(id,orderType){
	for (var i = 1;i < componentInfoArr.length+1;i++) {		
		if ((componentInfoArr[i-1].id == id) && (componentInfoArr[i-1].isEnable=='true') && (componentInfoArr[i-1].orderType != orderType))  {
			return i;
		}
	}	
	return 0;
}

PublicContol.prototype.findPageTd = function f_findPageTd(id,orderType){
	for (var i = 1;i < componentInfoArr.length+1;i++) {		
		if ((componentInfoArr[i-1].id == id) && (componentInfoArr[i-1].isEnable=='true'))  {
			return i;
		}
	}	
	return 0;
}

PublicContol.prototype.setComponentEnable = function f_setComponentEnable(id,orderType) {
	for (var i = 0;i < componentInfoArr.length;i++) {
		if ((componentInfoArr[i].id == id) && (componentInfoArr[i].orderType == orderType))  {
			componentInfoArr[i].isEnable = 'false';
		}
	}
}

PublicContol.prototype.componentType = function f_componentType() {
	var id = 0;
	var orderType = 0;
	var isEnable = 0;
}

PublicContol.prototype.setDocumentDisable = function f_setDocumentDisable() {
	var documentElements = document.all;
	for (var i = 0;i < documentElements.length;i++) {
		if ((documentElements[i].comptype =='Panel') || (documentElements[i].comptype =='Component')) {
			documentElements[i].disabled = true;
		}
	}
}

//��ʾ��ȷ��ҳ��
PublicContol.prototype.setPageDisplay = function f_setPageDisplay(orderType){
	var i = 0,returnNum = 0,iframesTd = '',iframeInfo = '';
	var frames = document.getElementsByTagName('Iframe');
	var oldtd = document.getElementById(initFrameTD);
	
	for (i = 0; i < frames.length;i++) {
		returnNum = this.findPageTd(frames[i].id+'TD',orderType);
		if (returnNum != 0) {
			iframesTd = frames[i].id+'TD';
			break;
		}
	}

	if (iframesTd == '') {
		oldtd.className = "tag2";
		initFrame(frames,'baseInfo');
   		rollFrame(frames,'baseInfo');
		return false;
	}

	if (iframesTd == 'baseInfoTD') {
		iframeInfo = 'baseInfo';
	}
	
	if (iframesTd == 'feeInfoTD') {
		iframeInfo = 'feeInfo';
	}
	
	if (iframesTd == 'nwkInfoTD') {
		iframeInfo = 'nwkInfo';
	}
	
	if (iframesTd == 'acctInfoTD') {
		iframeInfo = 'acctInfo';
	}
  	
   	frames(iframeInfo).style.display == 'block';
   	initFrame(frames,iframeInfo);
   	rollFrame(frames,iframeInfo);   	  
			
	var newtd = document.getElementById(iframesTd);
	oldtd.className = "tag2";
	newtd.className = "tag1";
	initFrameTD = newtd.id;
}

//���ƿؼ�
function ControlControl() {
}
ControlControl.prototype = new PublicContol();
ControlControl.prototype.constructor = PublicContol;
ControlControl.prototype.supperclass = PublicContol.prototype;

ControlControl.prototype.controlComponent = function f_controlComponent(eventPageId,componentUnit,documentEle,isClick,orderType){
	var recordNum = 0;

	var componentReadOnly = componentUnit.getAttribute('readOnly');
	var componentIsVisible= componentUnit.getAttribute('isVisible');
	var componentElement  = documentEle.getElementById(componentUnit.getAttribute('controlId'));

	if (componentElement == null) {
			return  false;
	}
		
	if (eventPageId != '') {							//��IFRAME�������
		if (componentReadOnly == '1') {					//�ɶ�д
			componentElement.disabled = false;
			
			componentInfoArr[componentInfoArr.length] = new this.componentType();
			componentInfoArr[componentInfoArr.length-1].id = componentElement.getAttribute('id');
			componentInfoArr[componentInfoArr.length-1].orderType = orderType;
			componentInfoArr[componentInfoArr.length-1].isEnable = 'true';
			
		}
		if (componentIsVisible  == '0') {
			componentElement.style.display = 'none';
		}
		
		return true;
	}
	
	if (isClick != 'click') {				//�ǵ���¼�
		if (componentReadOnly == '1') {				//�ɶ�д�ؼ�
			componentElement.disabled = false;
			
			componentInfoArr[componentInfoArr.length] = new this.componentType();
			componentInfoArr[componentInfoArr.length-1].id = componentElement.getAttribute('id');
			componentInfoArr[componentInfoArr.length-1].orderType = orderType;
			componentInfoArr[componentInfoArr.length-1].isEnable = 'true';
		}
		if (componentIsVisible  == '0') {
			componentElement.style.display = 'none';
		}
		return true;
	}
	
	if (event.srcElement.checked) {					//ѡ��ĳ��ҵ��ʱ
		if (componentReadOnly == '1') {				//�ɶ�д�ؼ�
			componentElement.disabled = false;
		
			componentInfoArr[componentInfoArr.length] = new this.componentType();
			componentInfoArr[componentInfoArr.length-1].id = componentElement.getAttribute('id');
			componentInfoArr[componentInfoArr.length-1].orderType = orderType;
			componentInfoArr[componentInfoArr.length-1].isEnable = 'true';
		}
		if (componentIsVisible  == '0') {
			componentElement.style.display = 'none';
		}
		if (componentIsVisible  == '1') {
			componentElement.style.display = '';
		}
	} else {										//ȡ��ѡ�е�ҵ��
		this.setComponentEnable(componentElement.getAttribute('id'),orderType);
		recordNum = this.findRec(componentElement.getAttribute('id'),orderType);
		
		if (recordNum == 0) {						//ֻ���ҵ���й�
			componentElement.disabled = true;
		}
	}
}

//������� 
function PanelControl() {
}
PanelControl.prototype = new ControlControl();
PanelControl.prototype.constructor = ControlControl;
PanelControl.prototype.supperclass = ControlControl.prototype;

PanelControl.prototype.controlPanel = function f_controlPanel(eventPageId,panelUnit,documentEle,isClick,orderType){
	var panelVisible = panelUnit.getAttribute('isVisible');	
	var panelReadOnly = panelUnit.getAttribute('readOnly');	
	var panelElement = documentEle.getElementById(panelUnit.getAttribute('controlId'));
		
	if (panelElement == null) {   //�Ҳ���Ԫ��
		return false;
	}	
	if (panelReadOnly == '1') {
		documentEle.getElementById(panelUnit.getAttribute('controlId')).disabled = false;
		
		var componentNodes = panelUnit.selectNodes('Component');
		
		for (var i = 0;i < componentNodes.length;i++) {
			this.controlComponent(eventPageId,componentNodes[i],documentEle,isClick,orderType);
		}
	}
	/*
	if (panelVisible == '0') {				//����岻�ɼ�		
		panelElement.style.display = 'none';
	} else {							    //���ɼ�,��������Ŀؼ�	
		var componentNodes = panelUnit.selectNodes('Component');
		
		for (var i = 0;i < componentNodes.length;i++) {
			this.controlComponent(eventPageId,componentNodes[i],documentEle,isClick,orderType);
		}
	}*/
}

//����ҳ��
function PageControl() {
}
PageControl.prototype = new PanelControl();
PageControl.prototype.constructor = PanelControl;
PageControl.prototype.supperclass = PanelControl.prototype;

PageControl.prototype.controlPage = function f_controlPage(eventPageId,pageId,pageUnit,isClick,orderType){
	var i = 0;
	var pageChildNodes = pageUnit.childNodes;
	var pageReadOnly = pageUnit.getAttribute("readOnly");
	var frames = '',documentEle = '';
	if (eventPageId == '') {
		
		if (pageId == 'orderInfo') {			//��ǰҳ��
			documentEle = document;
		} else if (isClick=='click') {								//����IFRAME
			frames = document.getElementsByTagName('Iframe');
			documentEle = document.frames(pageId).document;
		} else if ((pageId != 'orderInfo') && (isClick=='onload')){
			return false;
		}
	} else {
		documentEle = document;
	}
	
	for (i = 0;i < pageChildNodes.length;i++) {			//����ҳ���е�Ԫ��
		if(pageChildNodes[i].getAttribute("controlType") == "Component") {		//���ƿؼ�
			this.controlComponent(eventPageId,pageChildNodes[i],documentEle,isClick,orderType);
		}		
		if(pageChildNodes[i].getAttribute("controlType") == "Panel") {			//�������
			this.controlPanel(eventPageId,pageChildNodes[i],documentEle,isClick,orderType);
		}
	}
	if ((i == pageChildNodes.length) &&(eventPageId == '')) {
		this.setPageDisplay(orderType);
	}
}

//�ܿ�����Ϣ
function ControlAllElement() {
}
ControlAllElement.prototype = new PageControl();
ControlAllElement.prototype.constructor = PageControl;
ControlAllElement.prototype.supperclass = PageControl.prototype;

ControlAllElement.prototype.controlInfo = function f_controlInfo(eventPageId,orderType,controlDomObject,isClick){     	
 	if (orderType == ORDER_TYPE_NEW) {
 		return true;
 	}
 	
 	if (controlDomObject.controlDom == null) {
 		/*if (eventPageId =='') {
 			alert("��ҵ�����Ͷ�Ӧ�Ŀ�����Ϣû������,����ϵͳ����Ա��ϵ!");
 		}*/
 		return false;
 	}
 	
 	if (componentInfoArr.length == 0) {
 		this.setDocumentDisable();
 	}
  	g_orderType = orderType;
  	
 	var pageId = '',pageNodes = '';

    pageNodes   = controlDomObject.controlDom.selectNodes('Page[@orderType="'+orderType+'"]');

    for (var i = 0;i < pageNodes.length;i++) {    	
    	if (!pageNodes[i].hasChildNodes()) {
    		continue;
    	}
    	if (eventPageId =='') {
    		pageId = pageNodes[i].getAttribute('controlId');	    	
	    	this.controlPage(eventPageId,pageId,pageNodes[i],isClick,orderType);
	    } else { 	
	    	pageId = eventPageId;
	    	
	    	if (pageNodes[i].getAttribute('controlId') ==eventPageId) {
	    		this.controlPage(eventPageId,pageId,pageNodes[i],isClick,orderType);
	    	}
	    }
    } 
}

function ControlAppointElement() {
}
ControlAppointElement.prototype.controlOneElement = function f_controlOneElement(controlElement,isVisible,isDisabled){
	//isVisible:none,block
	//isDisabled:true,false
	var documentElement = document.getElementById(controlElement);
	if (isVisible =='') {
		documentElement.style.display = isVisible;
	}

	if (isDisabled =='') {
		documentElement.disabled = isDisabled;
	}
}

//��������ܱ�ǩ���ƶ�
function controlOrder() {
	var bodyInfo = document.getElementsByTagName('body');
	var orderTd = '',win = '',id = '';
	if (bodyInfo[0].getAttribute('id') == 'custInfo')   {
		orderTd = document.getElementById('order');
		//win = parent.parent;
		id = 'frameOrder';
	} else if (bodyInfo[0].getAttribute('id') == 'orderInfo') {
		orderTd = parent.document.getElementById('car');
		win = parent;
		id = 'frameCar';
		win.switchFrame(orderTd,id,true);
	}
	orderTd.disabled = false;
}