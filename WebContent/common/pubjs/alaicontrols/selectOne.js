	//ѡ�б���е�һ�м�¼�����������ɣķ���һ����������
	/*tr:��ǰ�е�tr
	  table:��ǰ���
	  id:���������������
	  index:��Ҫ�ŵ������е��ֶ��ڱ���е����,��0��ʼ
	*/
	var trIdTemp = 0;
	function selectOne(tr,table,id,index){
		if(trIdTemp != 0 && trIdTemp != tr.id){
	        
			var listTable = document.getElementById(table);
			for(var i=0;i<listTable.rows.length;i++){
				if (listTable.rows[i].id==trIdTemp){
					var colorName = i % 2 == 0 ? "#DAE4FC" : "#EAEFFD";
					listTable.rows(trIdTemp).style.backgroundColor = colorName; //"#EAEFFD"; //"#ECECEC";
					//var styleName = i % 2 == 0 ? "list-deepblue" : "list-blue";
					//listTable.rows(trIdTemp).className = styleName;
				}
			}
			
			trIdTemp = tr.id;
			tr.style.backgroundColor = "blue"; //"yellow";
			}
		else if(trIdTemp != 0 && trIdTemp == tr.id){
			tr.style.backgroundColor = "blue"; //"yellow";
		}
		else{
			trIdTemp = tr.id;
			tr.style.backgroundColor = "blue"; //"yellow";
		}
		
		var tdId = tr.cells(index).innerText;
		document.getElementsByName(id).value = tdId;
	}
	