<!DOCTYPE html>
<html>
<head>
	<title>Sample Page</title>
	<link rel="stylesheet" type="text/css" href="css/stylesheet.css">
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">

	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>

</head>

<body>
	<div class="wrapper">
		
		<div class="header">
			<div class="links">
				<ul class="top-links">
					<li><a>Gmail</a></li>
					<li><a>Images</a></li>
					<li><a><span><i class="fa fa-th" style="font-size:19px; color: gray;" title="Google Apps"></i></span></a></li>
					<li><a><span class="btn btn-sm btn-primary">Sign In</span></a></li>
				</ul>
			</div>
		</div>

		<div class="banner">
			<img src="https://www.google.co.in/images/branding/googlelogo/2x/googlelogo_color_272x92dp.png">
		</div>

		<div class="content">
			<div class="form">
				<input type="text" class="form-control" id="mysearch">
			</div>

			<div class="submit-buttons">
				<input value="Google Search" aria-label="Google Search" name="btnK" type="submit" onclick="check()">
				<input value="I'm Feeling Lucky" aria-label="I'm Feeling Lucky" name="btnI" type="submit">
			</div>
		</div>
	</div>
</body>

<script src="scripts/script.js"></script>
</html>