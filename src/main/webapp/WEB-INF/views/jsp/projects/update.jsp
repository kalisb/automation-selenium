<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset='utf-8' />
<title>New Feature | Ultimate Functional Testing</title>
<link rel='icon' type='image/png'
	href="<c:url value="/resources/img/favicon.svg" />" sizes='any' />
<link type="text/css" rel="stylesheet"
	href="<c:url value="/resources/styles/styles.min.css" />" media="all" />
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<link rel="stylesheet"
	href="<c:url value="/resources/font-awesome-4.7.0/css/font-awesome.min.css" />">
</head>
<body class="page-projects-new">
	<jsp:include page="../../jsp/fragments/header.jsp"></jsp:include>
	<div class="wrap">
		<div class="main">
			<section class="sidebar">
				<a href="<c:url value="/" />"> <img
					src="<c:url value="/resources/img/favicon.svg" />" alt="UFT"
					style="width: 60px; height: 60px;">
				</a>
				<ul class="nav main-nav">
					<li><a href="#" class="projects current">Features</a></li>
					<li><br></li>
					<li><br></li>
					<li><jsp:include page="../../jsp/fragments/tasks.jsp"></jsp:include>
					</li>
				</ul>
			</section>

			<section class="content">
				<div class="input-wrap">
					<textarea rows="10" id="result">
					</textarea>
				</div>
				<form accept-charset="UTF-8" onsubmit="return false;"
					id="featureForm">

					<div class="page-title edit-project-title">
						<div class="pull-right">
							<button id="run-feature"
								class="btn test save-project create-project danger">Run
								Feature</button>
							<button id="run-feature"
								class="btn submit save-project create-project">Save
								Feature</button>
						</div>

						<h1>${feature.title}</h1>

						<div id="edit-project" class="settings-section">

							<div class="section-item title">
								<label for="title">Title</label> <span class="help">e.g.
									"Login"</span>
								<div class="input-wrap">
									<input name="title" type="text" id="title"
										value="${feature.title}"> <input style="display: none"
										name="id" type="text" id="id" value="${feature.id}"> <input
										style="display: none" name="projectId" type="text"
										id="projectId" value="${feature.projectId}">
								</div>
							</div>

							<div class="section-item description">
								<label for="description">Content</label>
								<div class="input-wrap">
									<textarea rows="10" cols="50" name="dataStr">
                                     ${feature.readData(feature.data)}
                                    </textarea>
								</div>
							</div>

						</div>

						<div id="edit-project" class="settings-section">
							<div class="section-item title">
								<h1>Implementation</h1>
							</div>

							<div class="section-item description">
								<label for="description">Content</label>
								<div class="input-wrap">
									<textarea class="code" name="dataImplStr">
                                     ${feature.readData(feature.dataImpl)}
                                    </textarea>
								</div>
							</div>
						</div>
					</div>
				</form>
			</section>
			<!-- .content -->
		</div>
		<!-- .main -->
	</div>
	<!-- .wrap -->
	<jsp:include page="../../jsp/fragments/footer.jsp"></jsp:include>
	<script>
var regex = /^(.+?)(\d+)$/i;
var cloneIndex = $(".clonedInput").length;

$("button.submit").on("click", function(e) {
	var starturl = window.location.pathname
    var url = String(window.location.href).replace(/projects\/edit\/\d+\/features\/\d+/g, "features/update"); // the script where you handle the form input.
    console.log(url);
    $.ajax({
           type: "POST",
           url: url,
           data: $("#featureForm").serialize(), // serializes the form's elements.
           success: function(data)
           {
               window.location = starturl.replace(/features\/\d+/g, "")
           }
     });

});

$("button.test").on("click", function(e) {

    var url  =String(window.location.href).replace(/projects\/edit\/\d+\/features\/\d+/g, "/test/" + '${feature.id}'); // the script where you handle the form input.
    $.ajax({
           type: "GET",
           url: url,
           complete: function(data)
           {
                $("#result").text("")
                $("#result").text(data.responseText.replace(/(\w|\W)*\/jsp\//g, '').replace(/.jsp(\w|\W)+/g, '').trim());
           },
     });

});
</script>
</body>
</html>
