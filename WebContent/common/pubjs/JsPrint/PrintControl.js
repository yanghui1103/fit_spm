// ���ڴ��PrintControl�����ؽű�

//PrintControl�ࣺ���ڽ��д�ӡ����
function PrintControl(templateFileName) {
	var privateCurPrinterWindow=null;
	var privateLocationTable=null;
	var privateCurTr=null;
	var privateLastTd = null;
	
//---------- �������� ----------
	this.getPrintDocument=function () {
		return privateCurPrinterWindow.document;
	}

    //����ģ���ļ�
    this.loadTemplateFile=function (templateFileName) {
        privateCurPrinterWindow = window.open(templateFileName);
        
        //����IE��ҳ���ȡ�첽���ط�ʽ������ѭ�����ڱ�֤������ɣ�
        do {
        }while(privateCurPrinterWindow.document.readyState!="complete");
    }

	this.setTargetWindow = function (targetWindow) {
		privateCurPrinterWindow = targetWindow;
	}
	
    //��׼���õ�ҳ���ʹ�ӡ��
    this.print=function () {
		privateCurPrinterWindow.document.execCommand("Print");
    }

	//�͹̶�������ֵ
    this.sendValue=function(id,value) {
		var element = privateCurPrinterWindow.document.getElementById(id);
		element.innerText = value;
	}
	
	//�ͻ������ֵ
	this.sendGridValue=function(value,width) {
		var newTd = privateCurPrinterWindow.document.createElement(
				"<td width=\"" + width +
				">" +
				value + 
				"</td>");
		newTd.innerText = value;

		privateCurTr.appendChild(newTd);
		if (newTd.cellIndex==0)
			newTd.className = "xl24b";
		else			
			newTd.className = "xl25b";
		
		privateLastTd = newTd;
	}
	
	//�ڻ����һ�����У�����д�������
	this.newLine=function() {
		//���ǰһ�����һ�еĿ�ȣ��Ա����ܿ�ȵ�һ����
		this.clearWidthOfLastTd();
		
		var newTable = privateCurPrinterWindow.document.createElement(	
			"<table border=0 cellspacing=0 cellpadding=0 id='1'></table>");
		newTable.width = privateLocationTable.width;

		var newTBody = privateCurPrinterWindow.document.createElement("<tbody></tbody>");
		newTable.appendChild(newTBody);
		var newTr = privateCurPrinterWindow.document.createElement("<tr></tr>");
		newTBody.appendChild(newTr);

		privateCurTr = newTr;
		
		privateCurPrinterWindow.document.body.insertBefore(newTable,privateLocationTable);
	}

	//��ʼһ����
	this.beginSection=function(tableId) {
		privateLocationTable = privateCurPrinterWindow.document.getElementById(tableId);
		if (privateLocationTable==null) return ;
		
		privateLastTd = null;
		
		this.newLine();
	}

	//����һ����
	this.endSection=function() {
		this.clearWidthOfLastTd();
		if (privateLastTd!=null)
			privateLastTd.removeAttribute("width");
		
	}
			
    //�ر����ڴ�ӡ��Ԥ��ҳ��
    this.close=function () {
        privateCurPrinterWindow.close();
    }

	//�����ɺõ�ҳ���ʹ�ӡ��
    this.execute=function () {
        privateCurPrinterWindow.print();
    }

	//ȡ���ɵ�table�����innerHTML���Ա��顣�������ڵ��Եķ�������
	this.debugGetText=function(tableId) {
		var element = privateCurPrinterWindow.document.getElementById("1");

		return element.outerHTML;
	}
	
//---------- ˽�з��� ---------------
	//���ǰһ�����һ�еĿ�ȣ��Ա����ܿ�ȵ�һ����
	this.clearWidthOfLastTd=function() {
		if (privateLastTd!=null) {
			privateLastTd.removeAttribute("width");
			privateLastTd = null;
		}
	}
	
	
//---------- ���¹��췽�� ----------
	if (templateFileName!=null)
    	this.loadTemplateFile(templateFileName);
}


//-------------- ֧�ֺ��� ---------------

