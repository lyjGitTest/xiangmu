<%@page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title></title>
<link rel="stylesheet" type="text/css" href="easyui/themes/default/easyui.css"/>
<link rel="stylesheet" type="text/css" href="easyui/themes/icon.css"/>
<script type="text/javascript" src="easyui/jquery-1.9.1.js"></script>
<script type="text/javascript" src="easyui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="easyui/locale/easyui-lang-zh_CN.js"></script>
<script type="text/javascript" >
$(function(){
	$('#win').window('close');
		$.getJSON('doinit.do',function(map){
			var lsdep=map.lsdep;
			var lswf=map.lswf;
			for(var i=0;i<lswf.length;i++){
				var wf=lswf[i];
				$("#fl").append("<input type='checkbox' id='wids' name='wids' value='"+wf.wid+"'/>"+wf.wname);
			}
			$('#depid').combobox({    
			    data:lsdep,    
			    valueField:'depid',    
			    textField:'depname',
			    value:1,
				panelHeight:70
			});
	});
});


$(function(){
	$('#ta').datagrid({    
	    url:'findpageAll.do', 
	    singleSelect:true,
	    width:'900',
		pagination:true,
		pageList:[5,10,15,20],
		pageSize:5,
	    columns:[[    
	  	        {field:'eid',title:'编号',width:100,align:'center'},
	  	        {field:'ename',title:'姓名',width:100,align:'center'},
	  	        {field:'sex',title:'性别',width:100,align:'center'},
	  	        {field:'address',title:'地址',width:100,align:'center'},
	  	        {field:'newdate',title:'生日',width:100,align:'center'},
	  	        {field:'photo',title:'照片',width:100,align:'center',
	  	        	formatter:function(value,row,index){
	  					return '<img src=uppic/'+row.photo+' width="100"/>';
	  				}		
	  	        },
	  	        {field:'depname',title:'部门',width:100,align:'center'},
	  	        {field:'opt',title:'操作',width:100,align:'center',
	  	        	formatter:function(value,row,index){
	  					var bt1='<input type="button" value="删除" onclick="dodel('+row.eid+')">';
	  					var bt2='<input type="button" value="编辑" onclick="findById('+row.eid+')">';
	  					var bt3='<input type="button" value="详细" onclick="findDetail('+row.eid+')">';
	  					return bt1+'&nbsp;'+bt2+'&nbsp;'+bt3;
	  				}
	  	        }     
	  	    ]]  
	});  

});
/*
function dodel(eid){
	$.messager.confirm('确认','您确认想要删除记录吗？',function(r){    
	    if (r){
	    	$.get('delete.do?eid='+eid,function(code){
	    		if(code=='1'){
					$.messager.alert('提示','删除成功！');
					$("#ta").datagrid('reload');
				}else{
					$.messager.alert('提示','删除失败！');
				}	
	    	})
	        alert('确认删除');    
	    }    
	});  
}*/



function findById(eid){
	$.getJSON('findById.do?eid='+eid,function(emp){

		$(":checkbox[name='wids']").each(function(){
			//清除选中状态

			$(this).prop("checked",false);
		});
		$("#ffl").form('load',{
			'eid':emp.eid,
			'ename':emp.ename,
			'sex':emp.sex,
			'address':emp.address,
			'newdate':emp.newdate,
			'depid':emp.depid,
			'emoney':emp.emoney
		});
       $("#myphoto").attr('src','uppic/'+emp.photo);
       var wids=emp.wids;
       $(":checkbox[name='wids']").each(function(){
	alert(emp.wids);
    	   for(var i=0;i<wids.length;i++){
    		  if($(this).val()== wids[i]){
    			  $(this).prop("checked",true);
    		  }

    	   }
       })

	});
}


/*
function findDetail(eid){
	$.getJSON('findDetail.do?eid='+eid,function(emp){
		$("#enametxt").html(emp.ename);
		$("#sextxt").html(emp.sex);
		$("#addresstext").html(emp.address);
		$("#sdatetext").html(emp.newdate);
		$("#phototxt").html(emp.photo);
		$("#depnametext").html(emp.depname);
		$("#emoneytext").html(emp.emoney);
		var lswf=emp.lswf;
		var wnames=[];
		for(var i=0;i<lswf.length;i++){
			var wf=lswf[i];
			wnames.push(wf.wname);//福利放入数组
		}
		var newstr=wnames.join(",");
		$("#wftxt").html(newstr);
		$("#phototxt").attr('src','/uppic'+emp.photo);
		$("#win").window('open');
	});
}



*/

$(function(){
	$("#btsave").click(function(){
		$.messager.progress(); 
		$("#ffl").form('submit', {
			url: "save.do",
			onSubmit: function(){
				var isValid = $(this).form('validate');
				if (!isValid){
					$.messager.progress('close');	// 如果表单是无效的则隐藏进度条
				}
				return isValid;	// 返回false终止表单提交
			},
			success: function(ts){
				if(ts=='1'){
					$.messager.alert('提示','保存成功！');
				}else{
					$.messager.alert('提示','保存失败！');
				}
				$.messager.progress('close');	// 如果提交成功则隐藏进度条
			}
		});

	});
/*	$("#btupdate").click(function(){
		$.messager.progress(); 
		$("#ffl").form('submit', {
			url: "update.do",
			onSubmit: function(){
				var isValid = $(this).form('validate');
				if (!isValid){
					$.messager.progress('close');	// 如果表单是无效的则隐藏进度条
				}
				return isValid;	// 返回false终止表单提交
			},
			success: function(ts){
				if(ts=='1'){
					$.messager.alert('提示','修改成功！');
					$('#ta').datagrid('reload');
				}else{
					$.messager.alert('提示','修改失败！');
				}
				$.messager.progress('close');	// 如果提交成功则隐藏进度条
			}
		});

	});*/
});
</script>
</head>
<body>
<table id="ta"></table>
<form action="" method="post" enctype="multipart/form-data" id="ffl" name="ffl">
<table width="500px" height="500px" border="2px" align="center">
<tr><td colspan="3" align="center">员工信息</td></tr>
<tr>
<td>姓名</td>
<td><input type="text" id="ename" name="ename" class="easyui-validatebox" data-options="required:true"/>
<input type="hidden" id="eid" name="eid"/>
<td colspan="6" rowspan="6" ><img src="uppic/1.jpg" id="myphoto" width="150px" ></td>
</tr>
<tr>
<td>性别</td>
<td>
<input type="radio" id="radio" name="sex" value="男" checked="checked"/>男
<input type="radio" id="radio2" name="sex" value="女"/>女
</td>
</tr>
<tr>
<td>地址</td><td><input type="text" id="address" name="address"  class="easyui-validatebox" data-options="required:true"/></td>
</tr>
<tr>
<td>生日</td><td><input type="date" id="newdate" name="newdate" /></td>
</tr>
<tr>
<td>照片</td><td><input type="file" id="pic" name="pic"/></td></tr>
<tr>
<td>部门</td><td><input type="text" name="depid" id="depid" /></td>
</tr>
<tr>
<td>薪资</td><td><input type="text" id="emoney" name="emoney" value="3000"/></td>
</tr>
<tr><td>福利</td><td colspan="2"><span id="fl"></span></td>
</tr>
 <tr>
	    <td colspan="3" align="center" bgcolor="#99FFCC">
	    <input type="button" name="btsave" id="btsave" value="保存" />
        <input type="button" name="btupdate" id="btupdate" value="修改" />
        <input type="button" name="btreset" id="btreset" value="取消" /></td>
        </tr>
</table>
</form>
<!-- 提示 -->
<%--<div id="win" class="easyui-window" title="详细页面" style="width:600px;height:400px"
        data-options="iconCls:'icon-save',modal:true">   
     <table width="550" border="1" align="center" cellpadding="1" cellspacing="0">
		    <tr>
		      <td colspan="3" align="center" bgcolor="#99FFCC">员工详细信息</td>
	        </tr>
		    <tr>
		      <td width="104">姓名</td>
		      <td width="303"><span id="enametxt"></span></td>
		      <td width="129" rowspan="7"><img id="phototext" src="uppic/1.jpg" width="129" height="150" /></td>
	        </tr>
		    <tr>
		      <td>性别</td>
		      <td><span id="sextxt"></span></td>
	        </tr>
		    <tr>
		      <td>地址</td>
		      <td><span id="addresstext"></span></td>
	        </tr>
		    <tr>
		      <td>生日</td>
		      <td><span id="sdatetext"></span></td>
	        </tr>
		    <tr>
		      <td>照片</td>
		      <td><span id="phototxt"></span></td>
	        </tr>
		    <tr>
		      <td>部门</td>
		      <td><span id="depnametext"></span></td>
	        </tr>
		    <tr>
		      <td>薪资</td>
		      <td><span id="emoneytext"></span></td>
	        </tr>
		    <tr>
		      <td>福利</td>
		      <td colspan="2"><span id="wftxt"></span></td>
	        </tr> 
	      </table> 
</div> --%>
</body>
</html>