/*************************************************************************************
	����Ŀ¼���ؼ�prettyģ����� �������������2003��7��16�գ���������Ȩ����
**************************************************************************************/
function getScriptPath(js)
{
	js=js.toLowerCase()
	var script=document.getElementsByTagName("SCRIPT");
	for(var i=0;i<script.length;i++)
	{
		var s=script[i].src.toLowerCase()
		if(s.indexOf(js)!=-1)return s.replace(js,"")
	}
	return null
}

function alai_tree_pretty(toObject)
{
	var path=getScriptPath("alai_tree.js")
	if(path==null){alert("run alai_tree_pretty() fail, please load alai_tree.js first!");return;}
	var icons=new alai_imagelist()
	icons.path="../images/alai_images/"
	icons.add("folderclose","folder")
	icons.add("hfile","file")
	icons.add("face");
	icons.add("angry");
	icons.add("plus_m","expand");
	icons.add("plus","expand_top");
	icons.add("plus_end","expand_end");
	icons.add("minus_m","collapse");
	icons.add("minus","collapse_top");
	icons.add("minus_end","collapse_end");
	icons.add("branch","leaf");
	icons.add("branch_end","twig");
	icons.add("vline","line");
	icons.add("blank");

	icons.add("note","default")
	icons.add("folderclose")
	icons.add("folderopen")
		
	var tree=new alai_tree(icons,0,toObject)
	tree.afteradd=function(srcNode)
	{
		if(srcNode.parent!=tree.root)srcNode.parent.icon.src=icons.item["folderopen"].src
	}
	tree.onexpand=function(srcNode)
	{
		srcNode.icon.src=icons.item["folderopen"].src
	}
	tree.oncollapse=function(srcNode)
	{
		srcNode.icon.src=icons.item["folderclose"].src
	}
	return tree
}
