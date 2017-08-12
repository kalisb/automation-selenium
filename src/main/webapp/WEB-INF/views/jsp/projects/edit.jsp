<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset='utf-8'/>
    <title>${project.title} | Ultimate Functional Testing</title>
    <link rel='icon' type='image/png' href="<c:url value="/resources/img/favicon.svg" />" sizes='any'/>
    <link type="text/css" rel="stylesheet" href="<c:url value="/resources/styles/styles.min.css" />" media="all" />
    <link rel="stylesheet"
        	href="<c:url value="/resources/font-awesome-4.7.0/css/font-awesome.min.css" />">
</head>
<body class="page-projects">
	<jsp:include page="../../jsp/fragments/header.jsp"></jsp:include>
    <div class="wrap">
        <div class="main">
            <section class="sidebar">
                <a href="<c:url value="/" />">
                <img src="<c:url value="/resources/img/favicon.svg" />" alt="UFT" style="width:60px;height:60px;">
                </a>
                <ul class="nav main-nav">
                    <li><a href="#" class="projects current">Features</a></li>
                    <li><br></li>
                    <li><br></li>
                    <li>
                        <jsp:include page="../../jsp/fragments/tasks.jsp"></jsp:include>
                    </li>
                </ul>
			</section>

			<section class="content">
                <div class="page-title">
                    <h1>${project.title}</h1>
                    <p class="usage">You're having ${features.size()} features.</p>
                    <div class="pull-right">
                        <a href="<c:url value="/projects/edit/${project.id}/features" />" id="new-project-main" class="btn add-btn new-project">New Feature</a>
                    </div>
                </div>

            <c:if test = "${features.size() == 0}">
                <div id="no-projects">
                    <h2>Create your first test feature</h2>
                    <a href="<c:url value="/projects/edit/${project.id}/features" />" class="btn add-btn new-project">New Feature</a>
                </div>
            </c:if>

            <div id="projects" class="ui-sortable">
                <c:forEach var = "feature" items="${features}">
                    <div id="project-255217" class="project private">
                        <a href="#">
                            <div class="project-thumb no-thumbnail">
                                <img src="<c:url value="/resources/img/feature.png" />" alt="" class="transparent">
                            </div>
                        </a>
                        <a href="<c:url value="/projects/edit/${project.id}/features/${feature.id}" />" class="project-title">${feature.title}</a>
                        <ul class="nav sf-menu dropit">
                            <li class="dropit-trigger"><a href="#" class="cog"></a>
                                <ul class="dropit-submenu" style="display: none;">
                                    <li><a href="#rename-project-modal" class="rename fancybox">Rename</a></li>
                                    <li class="sep"><a href="#delete-project-modal" data-id="255217" class="delete-project fancybox">Delete Project</a></li>
                                </ul>
                            </li>
                        </ul>
                    </div>
                </c:forEach>
            </div>
			</section><!-- .content -->

			</div><!-- .main -->
		</div><!-- .wrap -->
		<jsp:include page="../../jsp/fragments/footer.jsp"></jsp:include>
    </body>
</html>
