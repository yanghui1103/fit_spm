//��麯��

//ȥ�����ߵĿո�author wang
function trim(string){
	return string.replace(/(^\s*)|(\s*$)/g, "");
}
//�ж������뵱ǰ���ڵ�ǰ��author wang
// dateTemp [�����ڿؼ�������ֵ][��ʽ xxxx-xx-xx]
// ������ڵ�ǰ���ڣ�retrun true  
// ���С�ڵ�ǰ����, retrun false
function checkIsAfterNow(dateTemp){
    dateTemp = trim(dateTemp);
    nowDate = new Date();
    var array = dateTemp.split('-');
    var month = nowDate.getMonth()+1;
	var date = nowDate.getDate();
	var year = nowDate.getYear();
	//alert("date="+date+string);
    if (parseInt(eval(array[0])) > year ){
    	return true;
    }else if (parseInt(eval(array[0])) == year){
    	if(parseInt(eval(array[1]))>month){
    		return true;
    	}else if (parseInt(eval(array[1]))==month){
    			if(parseInt(eval(array[2]))>=date){
    				return true;
    			}else{
    				return false;
    			}    		
    	}
    }
}

//�ж������Ƿ��ǽ��� wang  (ʹ�ô���ͬ��)
function checkIsToday(dateTemp){
    dateTemp = trim(dateTemp);
    nowDate = new Date();
    var array = dateTemp.split('-');
    var month = nowDate.getMonth()+1;
	var date = nowDate.getDate();
	var year = nowDate.getYear();
	//alert("date="+date+string);
    if ( parseInt(eval(array[0])) != year ){
    	return false;
    }else if( parseInt(eval(array[1])) != month ){
    		return false;
    }else if( parseInt(eval(array[2])) !=date ){
    		return false;
    }else{
    		return true;
    }    		
}

//�Ƚ��������ڵ��Ⱥ� author wang
function checkTwoDate(first,second){//���first<second���򷵻�true;���򷵻�false
    first = trim(first).split("-");
    second = trim(second).split("-");
	if( parseInt(eval(first[0])) < parseInt(eval(second[0])) )  {
		return true;
	}else if ( parseInt(eval(first[0])) == parseInt(eval(second[0])) ){
		if( parseInt(eval(first[1])) < parseInt(eval(second[1])) ){			
			return true;
		}else if ( parseInt(eval(first[1])) == parseInt(eval(second[1])) ){
			if ( parseInt(eval(first[2])) < parseInt(eval(second[2])) ){
				return true;
			}else{
				return false;
			}
		}else{
			return false;
		}
	}else{
		return false;
	}
}

//�������
//������������
function passwordType() {
	var value ='0';
	var passwordElement = '0';
}

//�������
function checkPassword() {
	var passwordArray = new Array();
	var i=0;j = 0;
	var inputElements = document.getElementsByTagName('INPUT');
	for (var i=0;i<inputElements.length;i++) {
		if (inputElements[i].getAttribute('type') =='password') {
			passwordArray[j] = new passwordType();
			passwordArray[j].value = inputElements[i].value;
			passwordArray[j].passwordElement = inputElements[i];
			j++;
		}
	}
	
	for (var k=1;k<passwordArray.length;k++) {
		if ((k %2) ==1) {
			if (passwordArray[k].value != passwordArray[k-1].value) {		//���벻���
				alert('������ĳ�ʼ��������֤���벻һ��,�������룡');
				passwordArray[k-1].passwordElement.focus();
				return false;
			}
		}
	}
	return true;
} 

//����������

//��������ʽ
function checkMail(chkElementValue,chkElement) {
	var intIndex = -1,intPointIndex =-1;
	
	intIndex = chkElementValue.indexOf('@');

	if (intIndex ==-1) 	{	//������'@'
		chkElement.select();
		chkElement.focus();
		alert("������ĵ��������ʽ���ԣ������䣡");
		return false;
	}
	
	intPointIndex = chkElementValue.indexOf('.');

	if (intPointIndex == -1) { //������'.'
		chkElement.select();
		chkElement.focus();
		alert("������ĵ��������ʽ���ԣ������䣡");
		return false;
	}
	
	if (intIndex>intPointIndex) { //��ʽ����
		chkElement.select();
		chkElement.focus();
		alert("������ĵ��������ʽ���ԣ������䣡");
		return false;
	}
	
	return true;
}

//���绰����
function checkPhone(chkElement) {
	var intIndex = -1;
	intIndex = chkElement.value.indexOf('-');
	
	if (intIndex == -1) {					//������'-'
		if (isNaN(chkElement.value)) {
			alert("������ĵ绰�����ʽ���ԣ������䣡");
			chkElement.focus();
			chkElement.select();
			return false;
		} 
		
		return true;
	}
	
	if ((intIndex !=-1) || (intIndex !=4) || (intIndex !=5)) {	//����'-'�����ڵ���λ�����λ
		chkElement.select();
		chkElement.focus();
		alert("������ĵ绰�����ʽ���ԣ������䣡");
		return false;
	}
	
	phoneArray = chkElement.value.split('-');
	
	if (phoneArray.length>2) {
		chkElement.select();
		chkElement.focus();
		alert("������ĵ绰�����ʽ���ԣ������䣡");
		return false;
	}
	
	if (isNaN(phoneArray[0]) || isNaN(phoneArray[1])) {
		alert("������ĵ绰�����ʽ���ԣ������䣡");
		chkElement.focus();
		chkElement.select();
		return false;
	}
		
	return true;
}

//�������
function checkInt(chkElementValue,chkElement) {
	var intIndex = -1;
	
	intIndex = chkElementValue.indexOf('.');
	
	if (intIndex != -1) {
		alert("������Ĳ��������������䣡");
		chkElement.focus();
		chkElement.select();
		return false;
	}
	
	if (isNaN(chkElementValue)) {
		alert("������Ĳ��������������䣡");
		chkElement.focus();
		chkElement.select();
		return false;
	}
		
	return true;
}

//��鸡����
function checkFloat(chkElementValue,chkElement) {
	var intIndex = -1,intIndexBear = -1;
	
	intIndexBear = chkElementValue.indexOf('-');
	
	intIndex = chkElementValue.indexOf('.');
	
	if (intIndexBear != -1) {				//����'-'
		floatArray = chkElementValue.split('-');
		chkElementValue = floatArray[1];
				
		if (floatArray[0])	{				//�ǿ�
			alert("����������ָ�ʽ���ԣ������䣡");
			chkElement.focus();
			chkElement.select();
			return false;
		}
	}
		
	if (intIndex == -1) {					//������'.'
		if (isNaN(chkElementValue)) {
			alert("����������ָ�ʽ���ԣ������䣡");
			chkElement.focus();
			chkElement.select();
			return false;
		} 
		
		return true;
	}
	
	floatArray = chkElementValue.split('.');
	
	if (floatArray.length>2) {
		alert("����������ָ�ʽ���ԣ������䣡");
		chkElement.focus();
		chkElement.select();
		return false;
	}
	
	if (isNaN(floatArray[0]) || isNaN(floatArray[1])) {
		alert("����������ָ�ʽ���ԣ������䣡");
		chkElement.focus();
		chkElement.select();
		return false;
	}
		
	return true;
}

//�������
function checkDate(chkElement){
//������
   	var dateFormat= /^((\d{2}(([02468][048])|([13579][26]))[\-\/\s]?((((0?[13578])|(1[02]))[\-\/\s]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\-\/\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\-\/\s]?((0?[1-9])|([1-2][0-9])))))|(\d{2}(([02468][1235679])|([13579][01345789]))[\-\/\s]?((((0?[13578])|(1[02]))[\-\/\s]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\-\/\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\-\/\s]?((0?[1-9])|(1[0-9])|(2[0-8]))))))(\s(((0?[1-9])|(1[0-2]))\:([0-5][0-9])((\s)|(\:([0-5][0-9])\s))([AM|PM|am|pm]{2,2})))?$/;

	var checkValue=chkElement.value.match(dateFormat);
	
	if(checkValue!=null)
		return true;
	else
		alert("���ڸ�ʽ����ѭYYYY-MM-DD�������䣡");
		chkElement.focus();
		chkElement.select();
	 	return false;
}

//���ʱ��
//����ʱ������
function dateType() {
	var value ='0';
	var dateElement = '0';
}

//���ʱ��
function compDate() {
	var dateArray = new Array();
	var i=0;j = 0;
	var inputElements = document.getElementsByTagName('INPUT');
	for (var i=0;i<inputElements.length;i++) {
		if (inputElements[i].getAttribute('type') =='Date') {
			dateArray[j] = new dateType();
			dateArray[j].value = inputElements[i].value;
			dateArray[j].dateElement = inputElements[i];
			j++;
		}
	}
	
	for (var k=0;k<dateArray.length;k++) {
		if (!checkSysteDate(dateArray[k].dateElement)) {
			return false;
		}
		
		if ((k %2) ==1) {
			if (dateArray[k].value < dateArray[k-1].value) {		
				alert('����ʱ�䲻��С�ڿ�ʼʱ��,�������룡');
				dateArray[k-1].dateElement.focus();
				return false;
			}
		}
	}
	return true;
} 

//��ϵͳʱ��Ƚ�
//��ϵͳʱ��Ƚ�
function checkSysteDate(dateElement){
    var sysDate = new Date();
    
    var dateValue = dateElement.value.split('-');
    
    if (parseInt(dateValue[0]) < parseInt(sysDate.getYear())) {
    	alert('ʱ�䲻�����ڵ�ǰʱ��,�������룡');
		dateElement.focus();
		return false;
    } else if (parseInt(dateValue[0]) > parseInt(sysDate.getYear())) {
    	return true;
    }
    
    if (parseInt(dateValue[1]) < (parseInt(sysDate.getMonth())+1)) {
    	alert('ʱ�䲻�����ڵ�ǰʱ��,�������룡');
		dateElement.focus();
		return false;
    } else if (parseInt(dateValue[1]) > (parseInt(sysDate.getMonth())+1)) {
    	return true;
    }
    
    if (parseInt(dateValue[2]) < parseInt(sysDate.getDate())) {
    	alert('ʱ�䲻�����ڵ�ǰʱ��,�������룡');
		dateElement.focus();
		return false;
    }

    return true;
}
//���ʱ�����
