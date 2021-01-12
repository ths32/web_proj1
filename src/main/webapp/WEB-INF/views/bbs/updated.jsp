<%@ page contentType="text/html; charset=UTF-8"%>
<%
	request.setCharacterEncoding("UTF-8");
	String cp = request.getContextPath();
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

	function sendIt(){
		
		f = document.myForm;
		
		str = f.subject.value;
		str = str.trim();
		if(!str){
			alert("\nInput Subject.");
			f.subject.focus();
			return;
		}
		f.subject.value = str;
		
		str = f.name.value;
		str = str.trim();
		if(!str){
			alert("\nInput Name.");
			f.name.focus();
			return;
		}		

		f.name.value = str;
		
		str = f.content.value;
		str = str.trim();
		if(!str){
			alert("\nInput Content.");
			f.content.focus();
			return;
		}
		f.content.value = str;
			
		f.action = "<%=cp%>/updated_ok.action";
		f.submit();
		
	}

</script>

</head>
<body>

<div id="bbs">
	<div id="bbs_title">
	Notice
	</div>
	
	<form action="" name="myForm" method="post">
	<div id="bbsCreated">
		<div class="bbsCreated_bottomLine">
			<dl>
				<dt>S&nbsp;u&nbsp;b&nbsp;j&nbsp;e&nbsp;c&nbsp;t</dt>
				<dd>
					<input type="text" name="subject" value="${dto.subject }" size="74" maxlength="100" class="boxTF"/>
				</dd>							
			</dl>		
		</div>
		
		<div class="bbsCreated_bottomLine">
			<dl>
				<dt>C&nbsp;l&nbsp;a&nbsp;s&nbsp;s</dt>
				<dd>
					<input type="text" name="name" value="${dto.name }" size="35" maxlength="20" class="boxTF"/>
				</dd>							
			</dl>		
		</div>
		
		<div id="bbsCreated_content" >
			<dl>
				<dt>C&nbsp;o&nbsp;n&nbsp;t&nbsp;e&nbsp;n&nbsp;t</dt>
				<dd>
					<textarea rows="12" cols="63" name="content" class="boxTA">${dto.content }</textarea>
				</dd>							
			</dl>		
		</div>
	
	</div>	
	
	<!-- Manager Mode -->
	<div id="bbsCreated_footer">
	
	<input type="hidden" name="num" value="${dto.num }"/>
	<input type="hidden" name="pageNum" value="${pageNum }"/>
	
	<input type="button" value=" Update " class="btn2" 
	onclick="sendIt();"/>
	
	<input type="button" value=" Abort " class="btn2" 
	onclick="javascript:location.href='<%=cp%>/list.action';"/>	
	</div>
	
	</form>
	
</div>

</body>
</html>




































