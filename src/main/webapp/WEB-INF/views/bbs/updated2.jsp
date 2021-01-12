<%@ page contentType="text/html; charset=UTF-8"%>
<%
	request.setCharacterEncoding("UTF-8");
	String cp2 = request.getContextPath();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Notice</title>

<link rel="stylesheet" href="/project/resources/css/style.css" type="text/css"/>
<link rel="stylesheet" href="/project/resources/resources/css/created.css" type="text/css"/>

<script type="text/javascript" src="/project/resources/resources/js/util.js"></script>

<script type="text/javascript">

	function sendIt2(){
		
		f2 = document.myForm2;
		
		str = f2.subject2.value;
		str = str.trim();
		if(!str){
			alert("\nInput Subject.");
			f2.subject2.focus();
			return;
		}
		f2.subject2.value = str;
		
		/* str = f.name.value;
		str = str.trim();
		if(!str){
			alert("\nInput Name.");
			f.name.focus();
			return;
		}		

		f.name.value = str; */
		
		str = f2.content2.value;
		str = str.trim();
		if(!str){
			alert("\nInput Content.");
			f2.content2.focus();
			return;
		}
		f2.content2.value = str;
			
		f2.action = "<%=cp2%>/updated2_ok.action";
		f2.submit();
		
	}

</script>

</head>
<body>

<div id="bbs">
	<div id="bbs_title">
	Notice
	</div>
	
	<form action="" name="myForm2" method="post">
	<div id="bbsCreated">
		<div class="bbsCreated_bottomLine">
			<dl>
				<dt>S&nbsp;u&nbsp;b&nbsp;j&nbsp;e&nbsp;c&nbsp;t</dt>
				<dd>
					<input type="text" name="subject2" value="${dto2.subject2 }" size="74" maxlength="100" class="boxTF"/>
				</dd>							
			</dl>		
		</div>
		
		<div id="bbsCreated_content" >
			<dl>
				<dt>C&nbsp;o&nbsp;n&nbsp;t&nbsp;e&nbsp;n&nbsp;t</dt>
				<dd>
					<textarea rows="12" cols="63" name="content2" class="boxTA">${dto2.content2 }</textarea>
				</dd>							
			</dl>		
		</div>
	
	</div>	
	
	<!-- Manager Mode -->
	<div id="bbsCreated_footer">
	
	<input type="hidden" name="num2" value="${dto2.num2 }"/>
	<input type="hidden" name="pageNum2" value="${pageNum2 }"/>
	
	<input type="button" value=" Update " class="btn2" 
	onclick="sendIt2();"/>
	
	<input type="button" value=" Abort " class="btn2" 
	onclick="javascript:location.href='<%=cp2%>/list2.action';"/>	
	</div>
	
	</form>
	
</div>

</body>
</html>




































