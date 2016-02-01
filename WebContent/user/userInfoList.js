function initPageofUserList(){
	var edom = createParamDom1('TYPE');
   var ttDom = inOrUpdateData('getSysItems.action',edom);    
   
   var src = document.getElementById("tabInfos");
	var rowCount = src.rows.length;
	for(var j=1;j<rowCount;j++)
	{
		src.deleteRow();
	}
	var nodes = ttDom.selectNodes('/root');	
	
	for(var i=0;i<1;i++)
	{
		var val1 = nodes.item(i).childNodes.item(0).text ;
		var val2 = nodes.item(i).childNodes.item(1).text ; 
		var val3 = nodes.item(i).childNodes.item(2).text ;
		var val4 = nodes.item(i).childNodes.item(3).text ; 
		var val5 = nodes.item(i).childNodes.item(4).text ;
		var val6 = nodes.item(i).childNodes.item(5).text ; 
		var val7 = nodes.item(i).childNodes.item(6).text ;
		var val8 = nodes.item(i).childNodes.item(7).text ; 
		var newrow = src.insertRow(src.rows.length);
		newrow.className="list-blue";
		var n = 8;
		while(n--)
		{
			var newcell = newrow.insertCell();
			newcell.className = "list-blue";
			switch(n){				
			case 7: newcell.innerHTML = replaceF9ValToNull(val1);break;
			case 6: newcell.innerHTML = replaceF9ValToNull(val2);break;
			case 5: newcell.innerHTML = replaceF9ValToNull(val3);break;
			case 4: newcell.innerHTML = replaceF9ValToNull(val4);break;
			case 3: newcell.innerHTML = replaceF9ValToNull(val5);break;
			case 2: newcell.innerHTML = replaceF9ValToNull(val6);break;
				case 1: newcell.innerHTML = replaceF9ValToNull(val7);break;
				case 0: newcell.innerHTML = replaceF9ValToNull(val8);break;
			}
		}
    }	

   
}