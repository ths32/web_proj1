<%@ page contentType="text/html; charset=UTF-8"%>
<%
	request.setCharacterEncoding("UTF-8");
	String cp4 = request.getContextPath();
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

	function sendIt4(){
		
		f4 = document.myForm4;
		
		str = f4.subject4.value;
		str = str.trim();
		if(!str){
			alert("\nInput Subject.");
			f4.subject4.focus();
			return;
		}
		f4.subject4.value = str;
		
		str = f4.name4.value;
		str = str.trim();
		if(!str){
			alert("\nInput name.");
			f4.name4.focus();
			return;
		}		
		
		f4.name4.value = str;
		
		str = f4.content4.value;
		str = str.trim();
		if(!str){
			alert("\nInput Content.");
			f4.content4.focus();
			return;
		}
		f4.content4.value = str;
			
		f4.action = "<%=cp4%>/created4_ok.action";
		f4.submit();
		
	}

</script>

</head>
<body>

<div id="bbs">
	<div id="bbs_title">
	공지사항4	
	</div>
	
	<form action="" name="myForm4" method="post">
	<div id="bbsCreated">
		<div class="bbsCreated_bottomLine">
			<dl>
				<dt>S&nbsp;u&nbsp;b&nbsp;j&nbsp;e&nbsp;c&nbsp;t</dt>
				<dd>
					<input type="text" name="subject4" size="74" maxlength="100" class="boxTF"/>
				</dd>							
			</dl>		
		</div>
		
		<div class="bbsCreated_bottomLine">
			<dl>
				<dt>C&nbsp;l&nbsp;a&nbsp;s&nbsp;s</dt>
				<dd>
					<input type="text" name="name4" size="35" maxlength="20" class="boxTF"/>
				</dd>							
			</dl>		
		</div>
		
		<div id="bbsCreated_content" >
			<dl>
				<dt>C&nbsp;o&nbsp;n&nbsp;t&nbsp;e&nbsp;n&nbsp;t</dt>
				<dd>
					<textarea rows="12" cols="63" name="content4" class="boxTA"></textarea>
				</dd>							
			</dl>		
		</div>
		
	</div>	
	
	<!-- Manager Mode -->
	<div id="bbsCreated_footer">
	<input type="button" value=" Post " class="btn2" 
	onclick="sendIt4();"/>
	<input type="reset" value=" Retry " class="btn2" 
	onclick="document.myForm4.subject4.focus();"/>
	<input type="button" value=" Abort " class="btn2" 
	onclick="javascript:location.href='<%=cp4%>/list4.action';"/>	
	</div>
	
	</form>
	
</div>

</body>
</html>




































