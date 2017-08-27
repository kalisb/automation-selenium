<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset='utf-8' />
<title>Configurations | Ultimate Functional Testing</title>
<link rel='icon' type='image/png'
	href="<c:url value="/resources/img/favicon.svg" />" sizes='any' />
<link type="text/css" rel="stylesheet"
	href="<c:url value="/resources/styles/styles.min.css" />" media="all" />
<link rel="stylesheet"
	href="<c:url value="/resources/font-awesome-4.7.0/css/font-awesome.min.css" />">
</head>
<body class="page-projects">
	<jsp:include page="../jsp/fragments/header.jsp"></jsp:include>
	<div class="main">
		<div class="wrap">
			<section class="sidebar">
				<a href="<c:url value="/" />"> <img
					src="<c:url value="/resources/img/favicon.svg" />" alt="UFT"
					style="width: 60px; height: 60px;">
				</a>
				<ul class="nav main-nav">
					<li><a href="<c:url value="/projects/" />"
						class="projects current">Projects</a></li>
					<li><a href="<c:url value="/configs/" />"
						class="projects current">Configurations</a></li>
					<li><br></li>
					<li><br></li>
					<li>
                        <jsp:include page="../jsp/fragments/tasks.jsp"></jsp:include>
					</li>
				</ul>
			</section>

			<section class="content">
				<div class="page-title">
					<h1>Configurations</h1>
					<p class="usage">Basic configurations before start testing.</p>
				</div>
				<div id="step0" class="clonedInput settings-section">
					<div class="section-item step">
						<label for="driver">WebDriver</label><span class="help">Select
							the WebDriver to be used to run the test. Stand alone server
							binaries will be downloaded according current platform.</span> <select
							id="driver">
							<option id="chrome" value="chrome">Chrome</option>
							<option id="firefox" value="firefox">Firefox</option>
							<option id="opera" value="opera">Opera</option>
							<option id="phantomjs" value="phantomjs">PhantomJs</option>
						</select>
					</div>
					<pre id="driver-debug"></pre>
					<div class="actions">
						<button class="btn submit save-project create-project">Download
							binaries</button>
					</div>
					<br>
				</div>
			</section>
			<!-- .content -->

		</div>
		<!-- .main -->
	</div>
	<!-- .wrap -->
	<jsp:include page="../jsp/fragments/footer.jsp"></jsp:include>
</body>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script>
	function getContextPath() {
		var ctx = window.location.pathname,
			path = '/' !== ctx ? ctx.substring(0, ctx.indexOf('/', 1) + 1) : ctx;
		return path + (/\/$/.test(path) ? '' : '/');
	}
	$("button.submit").on("click", function(e) {
	$('#driver-debug').text(" \n--------------------------------------------------------\n DOWNLOADING SELENIUM STAND-ALONE EXECUTABLE BINARIES...\n--------------------------------------------------------\n  \n");
    $.ajax({
           type: "GET",
           url: getContextPath() + '/os/' + $("#driver").val(),
     });
 });
</script>
</html>
