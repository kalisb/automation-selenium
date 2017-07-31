<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset='utf-8' />
<title>Projects Dashboard | Ultimate Functional Testing</title>
<link rel='icon' type='image/png'
	href="<c:url value="/resources/img/favicon.svg" />" sizes='any' />
<link type="text/css" rel="stylesheet"
	href="<c:url value="/resources/styles/styles.min.css" />" media="all" />
</head>
<body class="page-projects">
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
					<li><a href="<c:url value="/configs/" />"
                    						class="projects current">Configuration</a></li>
				</ul>
			</section>
			<section class="content">
				<div class="page-title">
					<h1>Projects</h1>
					<p class="usage">You're having ${projects.size()} projects.</p>
					<div class="pull-right">
						<a href="<c:url value="/projects/new" />" id="new-project-main"
							class="btn add-btn new-project">New Project</a>
					</div>
				</div>

				<div id="projects" class="ui-sortable">
					<c:forEach var="project" items="${projects}">
						<div id="project-255217" class="project private">
							<a href="#">
								<div class="project-thumb no-thumbnail">
									<img src="<c:url value="/resources/img/project.jpg" />" alt=""
										class="transparent">
								</div>
							</a> <a href="<c:url value="/projects/edit/${project.id}" />"
								class="project-title">${project.title}</a>
							<ul class="nav sf-menu dropit">
								<li class="dropit-trigger"><a href="#" class="cog"></a>
									<ul class="dropit-submenu" style="display: none;">
										<li><a href="#rename-project-modal"
											class="rename fancybox">Rename</a></li>
										<li class="sep"><a href="#delete-project-modal"
											data-id="255217" class="delete-project fancybox">Delete
												Project</a></li>
									</ul></li>
							</ul>
						</div>
					</c:forEach>
				</div>

				<c:if test="${projects.size() == 0}">
					<div id="no-projects">
						<h2>Create your first project</h2>
						<a href="<c:url value="/projects/new" />"
							class="btn add-btn new-project">New Project</a>
					</div>
				</c:if>
			</section>
			<!-- .content -->
		</div>
		<!-- .main -->
	</div>
</body>
</html>
