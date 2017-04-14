<#assign  security=JspTaglibs["http://www.springframework.org/security/tags"] />

<!DOCTYPE html>
<head>
<meta content="text/html; charset=utf-8" http-equiv="Content-Type">
<meta content="IE=10;chrome=1" http-equiv="X-UA-Compatible">

<@security.authorize access="isAuthenticated()">
    <meta http-equiv="refresh" content="0;url='/'}" />
</@security.authorize>


<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
<!-- Latest compiled and minified CSS -->
<link rel="stylesheet" href="//maxcdn.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap.min.css">
<!-- Optional theme -->
<link rel="stylesheet" href="//maxcdn.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap-theme.min.css">
<!-- Latest compiled and minified JavaScript -->
<script src="//maxcdn.bootstrapcdn.com/bootstrap/3.2.0/js/bootstrap.min.js"></script>
<script src="http://cdnjs.cloudflare.com/ajax/libs/jstimezonedetect/1.0.4/jstz.min.js"></script>

<script>

    var needsElementsBlocking = true;

    $(function()
    {
        $('input').prop("readonly", false);
        $('#submit').prop("disabled", false);
        $('#auth').on('submit', function (event) {
            if (needsElementsBlocking) {
                presubmit();
            }
            return true;// mark to make submit
        });
    });

	function enterProcessing(e)
	{
	    if (e && e.keyCode == 13)
	    {
            presubmit();
	    }
        return true;
	}

    function presubmit()
    {
        $('input').prop("readonly", true);
        $('#submit').addClass('btn-disabled disabled active');
        needsElementsBlocking = false;
        $('#auth').submit();
    }

	function focusOnInput()
	{
        $('#username').fadeIn();
	}

	function setTimeZoneInCookie() 
	{
        document.cookie = "TIMEZONE_COOKIE=" + jstz.determine().name();
    }
</script>
</head>

<body scroll="auto" onload="setTimeZoneInCookie(); focusOnInput();">

	<form id="auth" class="form-horizontal" role="form" method="post" action="login/j_spring_security_check">
		<div id="login" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">      
		  <div class="modal-dialog">
			<div class="modal-content">
		  	  <div class="modal-header text-center">
		    	<h4 class="modal-title"><@spring.messageText "message.authentication" ""/></h4>
		  	  </div>
			  <div class="modal-body text-center">
		          <div class="form-group">
		            <label class="col-sm-1 col-sm-offset-3 control-label"><span class="glyphicon glyphicon-user"></span></label>
		            <div class="col-sm-5">
		              <input id="username" name="j_username" placeholder='<@spring.messageText "message.username" ""/>' class="form-control" type="text"
		              		 autocomplete="off" keyev="true" clickev="true" tabindex="1" class="form-control" onKeyPress="return enterProcessing(event)"/>
		            </div>
		          </div>
		          <div class="form-group">
		            <label class="col-sm-1 col-sm-offset-3 control-label"><span class="glyphicon glyphicon-lock"></span></label>
		            <div class="col-sm-5">
					  <input type="password" name="j_password" class="form-control" placeholder='<@spring.messageText "message.password" ""/>' 
						     autocomplete="off" keyev="true" clickev="true" tabindex="2" onKeyPress="return enterProcessing(event)"/>
		            </div>
		          </div>
		          <br>
		          <div class="form-group">
					  <label class="col-sm-1 col-sm-offset-3 control-label"></label>
					  <input id="submit" type="submit" tabindex="3" style="margin-right:-10px" value='<@spring.messageText "message.enter" ""/>'
					  		 class="btn btn-default" />
				  </div>
		      </div>
			<#if isError?? && isError?string == "true">
	  			<div class="modal-footer">
        			  <div class="error" style="background-color:red;text-align:center"><@spring.messageText "message.wrong.credentials" ""/></div>
      			</div>
      			</#if>
		    </div>
		  </div>
		</div>
	</form>
</body>
</html>