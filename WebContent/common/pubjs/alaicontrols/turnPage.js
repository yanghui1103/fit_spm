// turnPage.js


var theRows = new Array();//�洢table��������
var pageIndex;            //��ǰҳ��
var pageSize = null;      //ÿҳ����
var pageCount;            //��ҳ��
var tempTableId;
var counts;               //������

//��ʼ��table����
function initTable(id){
    //alert('ok');
    tempTb = document.getElementById(id);

	if(theRows.length == 0){
		for(i=0;i<tempTb.rows.length-1;i++){
		    theRows[i] = new Array(tempTb.rows[i+1].cells[0].innerText.toLowerCase(),tempTb.rows[i+1].outerHTML);
		}
	}
	//alert(theRows.length);
	tempTableId = id;
	pageIndex = 0;
	if(pageSize == null) pageSize = 5;
	pageCount = parseInt(theRows.length/pageSize) + 1;
	counts = theRows.length;
}

function initTable_nopageSize(id,size){
    tempTb = document.getElementById(id);

	if(theRows.length == 0){
		for(i=0;i<tempTb.rows.length;i++){//�޸�
		    theRows[i] = new Array(tempTb.rows[i].cells[0].innerText.toLowerCase(),tempTb.rows[i].outerHTML);
		}
	}

	tempTableId = id;
	pageIndex = 0;

	if(size != null)
		pageSize = size;

	if(theRows.length%pageSize == 0)
		pageCount = parseInt(theRows.length/pageSize);
	else pageCount = parseInt(theRows.length/pageSize) + 1;
	counts = theRows.length;
}

//��ʾ����
function bindPage(){
    var tempTb = document.getElementById(tempTableId);;
    var rows = theRows.slice((pageIndex-1)*pageSize,pageIndex*pageSize)
    //alert((pageIndex-1)*pageSize+1);
    var str = '';
       for(i=0;i<rows.length;i++){
          str += rows[i][1];
       }

     var tempHead = tempTb.outerHTML ;
     var tempInner = tempTb.innerHTML;
     var head = tempHead.replace(tempInner,'');

     var head = head.replace('</TABLE>','');
     var firstrow = tempTb.rows[0].outerHTML;
     tempTb.outerHTML = head  + str+'</TABLE>';//�޸�ԭ����head+ firstrow + str+'</TABLE>'
}

//��ҳ����
function turnPage(command){
     switch(command){
        case 'prev':
           turnPrev();
           break;
        case 'next':
           turnNext()
           break;
        case 'first':
           turnFirst();
           break;
        case 'end':
           pageIndex = pageCount;
           turnEnd();
           break;
     }
     //pageInfo.innerText = pageIndex + '/' + pageCount;
 }

 //��һҳ
 function turnNext(){
    if(pageIndex == pageCount) return;
    pageIndex++;
    bindPage();
 }

 //��һҳ
 function turnPrev(){
    if(pageIndex == 1) return;
    pageIndex--;
    bindPage();
 }

 //��һҳ
 function turnFirst(){
    pageIndex = 1;
    bindPage();
 }

 //ĩҳ
 function turnEnd(){
    pageIndex = pageCount;
    bindPage();
 }
