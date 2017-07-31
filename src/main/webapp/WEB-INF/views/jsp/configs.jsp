<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset='utf-8'/>
    <title>Configurations | Ultimate Functional Testing</title>
    <link rel='icon' type='image/png' href="<c:url value="/resources/img/favicon.svg" />" sizes='any'/>
    <link type="text/css" rel="stylesheet" href="<c:url value="/resources/styles/styles.min.css" />" media="all" />
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
            <div class="section-item title">
               <h1>Select browser</h1>
                <p>Binary file for selected OS will be download and used t run the tests.</p>
             </div>
			<div id="step0" class="clonedInput settings-section">
                <b>Browser</b>
                <div class="section-item step">
                    <label for="os">OS:</label>
                    <select name="os">
                        <option ${step.type == "Given" ? "selected" : ''} value="Given">Windows</option>
                        <option ${step.type == "And" ? "selected" : ''} value="And">Mac OS/X</option>
                        <option ${step.type == "When" ? "selected" : ''} value="When">*nix</option>
                     </select>
                    <select name="broser">
                        <option ${step.type == "Given" ? "selected" : ''} value="Given">Chrome</option>
                        <option ${step.type == "And" ? "selected" : ''} value="And">Firefox/option>
                        <option ${step.type == "When" ? "selected" : ''} value="When">PhantomJs</option>
                    </select>
                    <div class="input-wrap">
                        <input type="text" value="${step.title}" name="scenarios[0].steps[0].title" id="step">
                    </div>
                </div>
                <div class="actions">
                    <button class="btn clone save-project create-project">Add Step</button>
                    <button class="btn remove save-project create-project danger">Remove Step</button>
                </div>
                <br>
            </div>
            </section><!-- .content -->

			</div><!-- .main -->
		</div><!-- .wrap -->
    </body>
</html>
