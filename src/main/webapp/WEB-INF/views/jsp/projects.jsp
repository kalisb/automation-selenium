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
						<div id="buildHistory"
							class="container-fluid pane-frame track-mouse expanded mouseover">
							<div class="row">
								<div class="col-xs-24 pane-header">Tasks History</div>
							</div>
							<div class="row pane-content">
								<table class="pane stripped">
									<tbody>
										<tr class="build-row  single-line overflow-checked">
											<td class="build-row-cell">
												<div class="pane build-name" style="height: 19px;">
													<div class="build-icon">
														<a href="#" class="build-status-link"> <img
															src="<c:url value="/resources/img/blue.png" />"
															alt="Success > Console Output"
															style="width: 16px; height: 16px;"
															class="icon-blue icon-sm"
															title="Success > Console Output"></a>
													</div>
												</div>
												<div class="pane build-details" style="height: 19px;">
													<a class="build-row" href="#"
														class="tip model-link inside build-link">Task 1 </a>
												</div>
												<div class="pane build-details" style="height: 19px;">
													<a class="build-row" href="#"
														class="tip model-link inside build-link">Aug 9, 2017
														12:45 PM</a>
												</div>
												<div class="left-bar"></div>
											</td>
										</tr>
										<tr class="build-row  single-line overflow-checked">
											<td class="build-row-cell">
												<div class="pane build-name" style="height: 19px;">
													<div class="build-icon">
														<a href="#" class="build-status-link"> <img
															src="<c:url value="/resources/img/blue.png" />"
															alt="Success > Console Output"
															style="width: 16px; height: 16px;"
															class="icon-blue icon-sm"
															title="Success > Console Output"></a>
													</div>
												</div>
												<div class="pane build-details" style="height: 19px;">
													<a class="build-row" href="#"
														class="tip model-link inside build-link">Task 2 </a>
												</div>
												<div class="pane build-details" style="height: 19px;">
													<a class="build-row" href="#"
														class="tip model-link inside build-link">Aug 9, 2017
														12:45 PM</a>
												</div>
												<div class="left-bar"></div>
											</td>
										</tr>
										<tr class="build-row  single-line overflow-checked">
											<td class="build-row-cell">
												<div class="pane build-name" style="height: 19px;">
													<div class="build-icon">
														<a href="#" class="build-status-link"> <img
															src="<c:url value="/resources/img/blue.png" />"
															alt="Success > Console Output"
															style="width: 16px; height: 16px;"
															class="icon-blue icon-sm"
															title="Success > Console Output"></a>
													</div>
												</div>
												<div class="pane build-details" style="height: 19px;">
													<a class="build-row" href="#"
														class="tip model-link inside build-link">Task 3 </a>
												</div>
												<div class="pane build-details" style="height: 19px;">
													<a class="build-row" href="#"
														class="tip model-link inside build-link">Aug 9, 2017
														12:45 PM</a>
												</div>
												<div class="left-bar"></div>
											</td>
										</tr>
										<tr class="build-row  single-line overflow-checked">
											<td class="build-row-cell">
												<div class="pane build-name" style="height: 19px;">
													<div class="build-icon">
														<a href="#" class="build-status-link"> <img
															src="<c:url value="/resources/img/red.png" />"
															alt="Success > Console Output"
															style="width: 16px; height: 16px;"
															class="icon-blue icon-sm"
															title="Success > Console Output"></a>
													</div>
												</div>
												<div class="pane build-details" style="height: 19px;">
													<a class="build-row" href="#"
														class="tip model-link inside build-link">Task 4 </a>
												</div>
												<div class="pane build-details" style="height: 19px;">
													<a class="build-row" href="#"
														class="tip model-link inside build-link">Aug 9, 2017
														12:45 PM</a>
												</div>
												<div class="left-bar"></div>
											</td>
										</tr>
										<tr class="build-row  single-line overflow-checked">
											<td class="build-row-cell">
												<div class="pane build-name" style="height: 19px;">
													<div class="build-icon">
														<a href="#" class="build-status-link"> <img
															src="<c:url value="/resources/img/red.png" />"
															alt="Success > Console Output"
															style="width: 16px; height: 16px;"
															class="icon-blue icon-sm"
															title="Success > Console Output"></a>
													</div>
												</div>
												<div class="pane build-details" style="height: 19px;">
													<a class="build-row" href="#"
														class="tip model-link inside build-link">Task 5 </a>
												</div>
												<div class="pane build-details" style="height: 19px;">
													<a class="build-row" href="#"
														class="tip model-link inside build-link">Aug 9, 2017
														12:45 PM</a>
												</div>
												<div class="left-bar"></div>
											</td>
										</tr>
										<tr class="build-row  single-line overflow-checked">
											<td class="build-row-cell">
												<div class="pane build-name" style="height: 19px;">
													<div class="build-icon">
														<a href="#" class="build-status-link"> <img
															src="<c:url value="/resources/img/red.png" />"
															alt="Success > Console Output"
															style="width: 16px; height: 16px;"
															class="icon-blue icon-sm"
															title="Success > Console Output"></a>
													</div>
												</div>
												<div class="pane build-details" style="height: 19px;">
													<a class="build-row" href="#"
														class="tip model-link inside build-link">Task 6 </a>
												</div>
												<div class="pane build-details" style="height: 19px;">
													<a class="build-row" href="#"
														class="tip model-link inside build-link">Aug 9, 2017
														12:45 PM</a>
												</div>
												<div class="left-bar"></div>
											</td>
										</tr>
										<tr class="build-row  single-line overflow-checked">
											<td class="build-row-cell">
												<div class="pane build-name" style="height: 19px;">
													<div class="build-icon">
														<a href="#" class="build-status-link"> <img
															src="<c:url value="/resources/img/red.png" />"
															alt="Success > Console Output"
															style="width: 16px; height: 16px;"
															class="icon-blue icon-sm"
															title="Success > Console Output"></a>
													</div>
												</div>
												<div class="pane build-details" style="height: 19px;">
													<a class="build-row" href="#"
														class="tip model-link inside build-link">Task 7 </a>
												</div>
												<div class="pane build-details" style="height: 19px;">
													<a class="build-row" href="#"
														class="tip model-link inside build-link">Aug 9, 2017
														12:45 PM</a>
												</div>
												<div class="left-bar"></div>
											</td>
										</tr>
										
										<tr class="build-row  single-line overflow-checked">
											<td class="build-row-cell">
												<div class="pane build-name" style="height: 19px;">
													<div class="build-icon">
														<a href="#" class="build-status-link"> <img
															src="<c:url value="/resources/img/blue.png" />"
															alt="Success > Console Output"
															style="width: 16px; height: 16px;"
															class="icon-blue icon-sm"
															title="Success > Console Output"></a>
													</div>
												</div>
												<div class="pane build-details" style="height: 19px;">
													<a class="build-row" href="#"
														class="tip model-link inside build-link">Task 8 </a>
												</div>
												<div class="pane build-details" style="height: 19px;">
													<a class="build-row" href="#"
														class="tip model-link inside build-link">Aug 9, 2017
														12:45 PM</a>
												</div>
												<div class="left-bar"></div>
											</td>
										</tr>
										
										<tr class="build-row  single-line overflow-checked">
											<td class="build-row-cell">
												<div class="pane build-name" style="height: 19px;">
													<div class="build-icon">
														<a href="#" class="build-status-link"> <img
															src="<c:url value="/resources/img/blue.png" />"
															alt="Success > Console Output"
															style="width: 16px; height: 16px;"
															class="icon-blue icon-sm"
															title="Success > Console Output"></a>
													</div>
												</div>
												<div class="pane build-details" style="height: 19px;">
													<a class="build-row" href="#"
														class="tip model-link inside build-link">Task 9 </a>
												</div>
												<div class="pane build-details" style="height: 19px;">
													<a class="build-row" href="#"
														class="tip model-link inside build-link">Aug 9, 2017
														12:45 PM</a>
												</div>
												<div class="left-bar"></div>
											</td>
										</tr>
										
										<tr class="build-row  single-line overflow-checked">
											<td class="build-row-cell">
												<div class="pane build-name" style="height: 19px;">
													<div class="build-icon">
														<a href="#" class="build-status-link"> <img
															src="<c:url value="/resources/img/blue.png" />"
															alt="Success > Console Output"
															style="width: 16px; height: 16px;"
															class="icon-blue icon-sm"
															title="Success > Console Output"></a>
													</div>
												</div>
												<div class="pane build-details" style="height: 19px;">
													<a class="build-row" href="#"
														class="tip model-link inside build-link">Task 10 </a>
												</div>
												<div class="pane build-details" style="height: 19px;">
													<a class="build-row" href="#"
														class="tip model-link inside build-link">Aug 9, 2017
														12:45 PM</a>
												</div>
												<div class="left-bar"></div>
											</td>
										</tr>
									</tbody>
								</table>
							</div>
						</div>
					</li>
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
	<jsp:include page="../jsp/fragments/footer.jsp"></jsp:include>
</body>
</html>
