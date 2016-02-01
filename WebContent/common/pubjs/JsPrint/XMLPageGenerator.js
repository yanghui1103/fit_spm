// ���ڴ��XMLPageGenerator�����ؽű�

//XMLPageGenerator�ࣺ���ڽ��д�ӡ����
function XMLPageGenerator(templateFileName) {
    var privateTemplateFileName = null;
    var privatePrintControl = null;
    var privateXMLDoc = null;
    var debugInfo = "";
    var privatePageCount = null;

    var privatePageFrame = null;
    var privateReportIndex = null;
    var privatePageIndex = null;

//---------- �������� ----------

    //����XML�ļ���ģ��ҳ����Ҫ������,����ֵ
    this.prepareDataWithXML = function (xmlDataFileURL) {
        privatePageCount = null;

        var result = false;
        var objXMLDoc = new ActiveXObject("Microsoft.XMLDOM");

        if (objXMLDoc==null) {
            window.alert("create objXMLDoc fault");
            return true;
        }

        objXMLDoc.async = false;
        objXMLDoc.load(xmlDataFileURL);
        privateXMLDoc = objXMLDoc;

        return true;
    }

    this.generatePage = function(pageFrame,reportIndex,pageIndex) {
        pageFrame.contentWindow.location = privateTemplateFileName;
        privatePrintControl.setTargetWindow(pageFrame.contentWindow);

        privatePageFrame = pageFrame;
        privateReportIndex = reportIndex;
        privatePageIndex = pageIndex;
    }

    this.generatePageInTimer = function() {
        var curXmlMultiReportHead = "";
        var curMultiReportDiv = null;
        var temp;
        var result = false;

        var pageFrame = privatePageFrame;
        var reportIndex = privateReportIndex;
        var pageIndex = privatePageIndex;

        if (pageFrame.readyState != "complete") {
            alert("Assert: state error!");
            return result;
        }

        pageFrame.style.display = "block";
        privatePrintControl.setTargetWindow(pageFrame.contentWindow);
        var curPrintDocument = privatePrintControl.getPrintDocument();
        for (var i=0;i<curPrintDocument.all.length;i++) {
            var curElement = curPrintDocument.all(i);

            //����൥ģʽ
            var xmlMultiReportHead = curElement.getAttribute("xml_multi_report_head");
            if (xmlMultiReportHead!=null) {
                curXmlMultiReportHead = xmlMultiReportHead;
                var nodeList = privateXMLDoc.documentElement.selectNodes(curXmlMultiReportHead);
                privatePageCount = nodeList.length;

                curMultiReportDiv = curElement;
                continue;
            }

            //��������xml��ֵ
            var xmlSigleValuePath = curElement.getAttribute("xml_sigle_value");
            if (xmlSigleValuePath!=null) {
                this.generateSigleValue(curElement,privateXMLDoc,curXmlMultiReportHead);
                continue;
            }

            //��������б�
            if ( curElement.getAttribute("xml_multi_line_list")!=null ) {
                result = this.generateMultiLinetValue(curElement,privateXMLDoc,curXmlMultiReportHead,pageIndex);
                debugInfo = curElement.innerHTML;
            }

            //����xml�б��е�ֵ(!!! todo �� �˴����봦��.all�б�仯������)
            if ( curElement.getAttribute("xml_list")!=null ) {
                result = this.generateListValue(curElement,privateXMLDoc,curXmlMultiReportHead,pageIndex);
                debugInfo = curElement.innerHTML;
            }
        }
        return result;
    }

    this.getPageCount = function () {
        return privatePageCount;
    }

    this.getDebugInfo = function () {
        return debugInfo;
    }

//---------- ˽�з��� ----------
    this.generateSigleValue = function(targetElement,valueXMLDoc,xmlMultiReportHead) {
        var xmlSigleValuePath = targetElement.getAttribute("xml_sigle_value");

        var xmlOnlyFirstPage  = targetElement.getAttribute("xml_only_first_page");
        var xmlOnlyMidPage    = targetElement.getAttribute("xml_only_mid_page");
        var xmlOnlyLastPage   = targetElement.getAttribute("xml_only_last_page");

        if ( (xmlOnlyFirstPage!=null) && (pageIndex!=0) )
            return;
        if ( (xmlOnlyMidPage!=null) && (pageIndex==0) )
            return;
        if ( (xmlOnlyLastPage!=null) && (pageIndex==0) )
            return;

        var totalPath;
        if ( xmlSigleValuePath.substr(0,2)=="//" )
            totalPath = xmlSigleValuePath;
        else
            totalPath = xmlMultiReportHead+"["+privateReportIndex+"]/"+xmlSigleValuePath;

        var nodeList = valueXMLDoc.documentElement.selectNodes(totalPath);
        if (nodeList.length==0)
            var curText = "No data at path: "+totalPath;
        else
            var curText = nodeList(0).text;

        targetElement.innerText = curText;
    }

    this.generateMultiLinetValue = function (targetTr,valueXMLDoc,xmlMultiReportHead,pageIndex) {
        var result = false;

        var xmlListPath = xmlMultiReportHead+"["+privateReportIndex+"]/"+targetTr.getAttribute("xml_multi_line_list");

        var xmlListMaxCount = targetTr.getAttribute("xml_max_count");
        if (xmlListMaxCount==null)
            xmlListMaxCount = 100000;

        var curListIndex = pageIndex*xmlListMaxCount;
        var firstXmlListValuePath = null;

        var i;
        var bHaveData;
        do {
        	bHaveData = false;
	        for (i=0;i<targetTr.all.length;i++) {
	            var curTd = targetTr.all(i);
	
	            var xmlListValuePath = curTd.getAttribute("xml_list_value");
	            if (xmlListValuePath==null)
	                continue;
	
	            //�õ�һ��Ԫ�����б�ֵ��ŵĸ���
	            if (firstXmlListValuePath==null)
	                firstXmlListValuePath = xmlListValuePath;
	            else if (xmlListValuePath==firstXmlListValuePath)
	                curListIndex++;
	
	            var totalPath = xmlListPath+"/"+xmlListValuePath;
	            var nodeList = valueXMLDoc.documentElement.selectNodes(totalPath);
	
	                //����б����꣬��ֱ�ӽ���������
	            if (nodeList.length<curListIndex+1) {
	                return true;
	            } else if ( xmlListMaxCount<(curListIndex+1-pageIndex*xmlListMaxCount) ) {
	                return false;
	            } else {
	            	bHaveData = true;
	                curText = nodeList(curListIndex).text;
	                if (curTd.innerText=="")
	                    curTd.innerText = curText;
	                else
	                    curTd.innerText = curTd.innerText+"\n"+curText;
	            }
	        }
		}while(bHaveData);
		
		return result;
    }

    this.generateListValue = function (targetTable,valueXMLDoc,xmlMultiReportHead,pageIndex) {
        var result = false;

        var xmlListPath = xmlMultiReportHead+"["+privateReportIndex+"]/"+targetTable.getAttribute("xml_list");
        var xmlListMaxCount = targetTable.getAttribute("xml_max_count");
        if (xmlListMaxCount==null)
            xmlListMaxCount = 100000;

            //ȡ��һ����Ϊģ����(table.tbody.tr)
        var templateTr = targetTable.firstChild.firstChild;
        var curListIndex = pageIndex*xmlListMaxCount;
        var firstXmlListValuePath = null;

        var bIsLastLine=false;
        var curText="";
        var i = 0;
        var bHavaData = false;
        do {
            var newTr = templateTr.cloneNode();

            bHaveData = false;
            for (i=0;i<templateTr.children.length;i++) {
                var curTd = templateTr.children(i);

                newTd = curTd.cloneNode();
                newTr.appendChild(newTd);
                newTd.innerText = curTd.innerText;

                var xmlListValuePath = curTd.getAttribute("xml_list_value");
                if (xmlListValuePath==null)
                    continue;

                //�õ�һ��Ԫ�����б�ֵ��ŵĸ���
                if (firstXmlListValuePath==null)
                    firstXmlListValuePath = xmlListValuePath;
                else if (xmlListValuePath==firstXmlListValuePath)
                    curListIndex++;

                var totalPath = xmlListPath+"/"+xmlListValuePath;
                var nodeList = valueXMLDoc.documentElement.selectNodes(totalPath);

                    //����б����꣬��ֱ�ӽ���������
                if (nodeList.length<curListIndex+1) {
                    result = true;
                    bIsLastLine = true;
                    curText = "��";
                } else if ( xmlListMaxCount<(curListIndex+1-pageIndex*xmlListMaxCount) ) {
                    firstXmlListValuePath = null;
                    result = false;
                    bIsLastList = true;
                    curText = "��";
                } else {
                    curText = nodeList(curListIndex).text;
                    bHaveData = true;
                    //alert( curText );
                }

                newTd.innerText = curText;
            }

            if (bHaveData==true)
                templateTr.parentNode.appendChild(newTr);
            if (firstXmlListValuePath==null)
                break;
        } while(!bIsLastLine);

        var lastTr = templateTr.parentNode.lastChild;
        for (var i=0;i<lastTr.children.length;i++) {
            var curTd = lastTr.children(i);
            var xmlLastLineClass = curTd.getAttribute("xml_last_line_class");
            if (xmlLastLineClass!=null) {
                curTd.className = xmlLastLineClass;
            }
        }

        templateTr.parentNode.removeChild(templateTr);

        return result;
    }

    this.getPrintControl = function() {
        return privatePrintControl;
    }

    this.addDebugMsg = function (errMsg) {
        debugInfo += '\n'+errMsg;
    }

//---------- ���¹��췽�� ----------
    privateTemplateFileName = templateFileName;
    privatePrintControl = new PrintControl(null);
}
