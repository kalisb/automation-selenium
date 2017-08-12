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
                        <div>
                            <a style="display:inline-block;" href="<c:url value="/projects/edit/${project.id}/features/${feature.id}" />">${feature.title}</a>
                            <a class='confirm' id=${feature.id} style="display:inline-block; float: right;" href="#"><i class="fa fa-trash" aria-hidden="true"></i></a>
                        </div>
                    </div>
                </c:forEach>
            </div>
			</section><!-- .content -->

			</div><!-- .main -->
		</div><!-- .wrap -->
		<jsp:include page="../../jsp/fragments/footer.jsp"></jsp:include>
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
        <script>
            $(function() {
                $('.confirm').click(function(e) {
                    e.preventDefault();
                    if (window.confirm("Are you sure?")) {
                       $.ajax({
                               type: "DELETE",
                               url: window.location.pathname + '/features/' + $(this).attr('id'),
                               success: function(data)
                               {
                                  document.location.reload(true)
                               },
                               error: function(data)
                              {
                                 document.location.reload(true)
                              }
                         });
                    }
                });
            });
        </script>
    </body>
</html>
