<%@ page contentType="text/html; charset=UTF-8"%>
<%
	request.setCharacterEncoding("UTF-8");
	String cp3 = request.getContextPath();
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

	function sendIt3(){
		
		f3 = document.myForm3;
		
		str = f3.subject3.value;
		str = str.trim();
		if(!str){
			alert("\nInput Subject.");
			f.subject3.focus();
			return;
		}
		f3.subject3.value = str;
		
		str = f3.name3.value;
		str = str.trim();
		if(!str){
			alert("\nInput Name.");
			f3.name3.focus();
			return;
		}		
		
		f3.name3.value = str;
		
		str = f3.content3.value;
		str = str.trim();
		if(!str){
			alert("\nInput Content.");
			f3.content3.focus();
			return;
		}
		f3.content3.value = str;
			
		f3.action = "<%=cp3%>/created3_ok.action";
		f3.submit();
		
	}

</script>

</head>
<body>

<div id="bbs">
	<div id="bbs_title">
	Notice3	
	</div>
	
	<form action="" name="myForm3" method="post">
	<div id="bbsCreated">
		<div class="bbsCreated_bottomLine">
			<dl>
				<dt>S&nbsp;u&nbsp;b&nbsp;j&nbsp;e&nbsp;c&nbsp;t</dt>
				<dd>
					<input type="text" name="subject3" size="74" maxlength="100" class="boxTF"/>
				</dd>							
			</dl>		
		</div>
		
		<div class="bbsCreated_bottomLine">
			<dl>
				<dt>C&nbsp;l&nbsp;a&nbsp;s&nbsp;s</dt>
				<dd>
					<input type="text" name="name3" size="35" maxlength="20" class="boxTF"/>
				</dd>							
			</dl>		
		</div>
		
		<div id="bbsCreated_content" >
			<dl>
				<dt>C&nbsp;o&nbsp;n&nbsp;t&nbsp;e&nbsp;n&nbsp;t</dt>
				<dd>
					<textarea rows="12" cols="63" name="content3" class="boxTA"></textarea>
				</dd>							
			</dl>		
		</div>
		
	</div>	
	
	<!-- Manager Mode -->
	<div id="bbsCreated_footer">
	<input type="button" value=" Post " class="btn2" 
	onclick="sendIt3();"/>
	<input type="reset" value=" Retry " class="btn2" 
	onclick="document.myForm3.subject3.focus();"/>
	<input type="button" value=" Abort " class="btn2" 
	onclick="javascript:location.href='<%=cp3%>/list3.action';"/>	
	</div>
	
	</form>
	
</div>

</body>
</html>




































