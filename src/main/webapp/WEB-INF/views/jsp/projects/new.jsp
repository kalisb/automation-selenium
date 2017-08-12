<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset='utf-8' />
<title>New Project | Ultimate Functional Testing</title>
<link rel='icon' type='image/png'
	href="<c:url value="/resources/img/favicon.svg" />" sizes='any' />
<link type="text/css" rel="stylesheet"
	href="<c:url value="/resources/styles/styles.min.css" />" media="all" />
</head>
<body class="page-projects-new">
	<div class="wrap">
		<div class="main">
			<section class="sidebar">
				<a href="<c:url value="/" />"> <img
					src="<c:url value="/resources/img/favicon.svg" />" alt="UFT"
					style="width: 60px; height: 60px;">
				</a>
				<ul class="nav main-nav">
					<li><a href="<c:url value="/projects/" />"
						class="projects current">Projects</a></li>
				</ul>
			</section>

			<section class="content">
				<form:form method="POST" accept-charset="UTF-8" commandName="project">

					<div class="page-title edit-project-title">
						<div class="pull-right">
							<input type="submit" id="save-project" value="Save Project"
								class="btn save-project create-project" />
						</div>

						<h1>New Project</h1>


						<div id="edit-project" class="settings-section">

							<div class="section-item title">
								<label for="title">Title</label> <span class="help">e.g.
									"Mona Lisa Portrait"</span>
								<div class="input-wrap">
									<form:input path="title" name="title" type="text" id="title" />
									<form:errors path="title" cssClass="error"/>
								</div>
							</div>

							<div class="section-item">
								<label for="url">URL</label> <span class="help">e.g.
									http://www.example.com</span>
								<div class="input-wrap">
									<form:input path="url" placeholder="http://" name="url" type="text" id="url" />
									<form:errors path="url" cssClass="error"/>
								</div>
							</div>
						</div>
					</div>
				</form:form>
			</section>
			<!-- .content -->
		</div>
		<!-- .main -->
	</div>
	<!-- .wrap -->
	<jsp:include page="../../jsp/fragments/footer.jsp"></jsp:include>
</body>
</html>
