<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset='utf-8'/>
    <title>New Feature | Ultimate Functional Testing</title>
    <link rel='icon' type='image/png' href="<c:url value="/resources/img/favicon.svg" />" sizes='any'/>
    <link type="text/css" rel="stylesheet" href="<c:url value="/resources/styles/styles.min.css" />" media="all" />
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
</head>
<body class="page-projects-new">
    <div class="wrap">
        <div class="main">
            <section class="sidebar">
                <a href="<c:url value="/" />">
                   <img src="<c:url value="/resources/img/favicon.svg" />" alt="UFT" style="width:60px;height:60px;">
                </a>
                <ul class="nav main-nav">
                    <li><a href="#" class="projects current">Features</a></li>
                </ul>
			</section>

			<section class="content">
			    <form accept-charset="UTF-8" onsubmit="return false;" id="featureForm">
			        <input name="_token" type="hidden" value="gnqU7ebXPF3qpDUSH5NePLBGigp96K89HZ3wZMec">

                    <div class="page-title edit-project-title">
                        <div class="pull-right">
                          <c:if test = "${feature != null}">
                            <button id="run-feature"  class="btn test save-project create-project danger">Run Feature</button>
                          </c:if>
                          <button id="run-feature"  class="btn submit save-project create-project">Save Feature</button>
                        </div>

                         <c:if test = "${feature == null}">
                            <h1>New Feature</h1>
                         </c:if>
                         <h1>${feature.title}</h1>

                        <div id="edit-project" class="settings-section">

                            <div class="section-item title">
                                <label for="title">Title</label>
                                <span class="help">e.g. "Login"</span>
                                <div class="input-wrap">
                                    <input name="title" type="text" id="title" value="${feature.title}">
                                </div>
                            </div>

                            <div class="section-item description">
                                <label for="description">Description</label>
                                <span class="help">e.g. "Any register user can log in"</span>
                                <div class="input-wrap">
                                     <textarea rows="4" cols="50" name="description">
                                    </textarea>
                                </div>
                            </div>

                        </div>

                        <div id="edit-project" class="settings-section">
                            <div class="section-item title">
                               <h1>Scenario</h1>
                                <label for="title">Title</label>
                                <span class="help">e.g. "Login with valid credentials"</span>
                                <div class="input-wrap">
                                    <input name="scenarios[0].title" value="${feature.scenarios[0].title}" type="text" id="title">
                                </div>
                             </div>

                            <div id="edit-steps" class="settings-section">
                            <c:if test = "${feature == null}">
                            <div id="step0" class="clonedInput settings-section">
                                <b>Step</b>
                                <div class="section-item step">
                                    <select name="scenarios[0].steps[0].type">
                                        <option ${step.type == "Given" ? "selected" : ''} value="Given">Given</option>
                                        <option ${step.type == "And" ? "selected" : ''} value="And">And</option>
                                        <option ${step.type == "When" ? "selected" : ''} value="When">When</option>
                                        <option ${step.type == "Then" ? "selected" : ''} value="Then">Then</option>
                                    </select>
                                    <span class="help">e.g. "I am a registered user"</span>
                                    <div class="input-wrap">
                                        <input type="text" value="${step.title}" name="scenarios[0].steps[0].title" id="step">
                                    </div>
                                    <div class="input-wrap">
                                         <textarea rows="4" cols="50" name="scenarios[0].steps[0].code">
                                        </textarea>
                                    </div>
                                </div>
                                <div class="actions">
                                    <button class="btn clone save-project create-project">Add Step</button>
                                    <button class="btn save-project create-project danger">Remove Step</button>
                                </div>
                                <br>
                            </div>
                            </c:if>
                            <c:forEach var = "step" items="${feature.scenarios[0].steps}">
                                <div id="step0" class="clonedInput settings-section">
                                    <b>Step</b>
                                    <div class="section-item step">
                                        <select name="scenarios[0].steps[0].type">
                                            <option ${step.type == "Given" ? "selected" : ''} value="Given">Given</option>
                                            <option ${step.type == "And" ? "selected" : ''} value="And">And</option>
                                            <option ${step.type == "When" ? "selected" : ''} value="When">When</option>
                                            <option ${step.type == "Then" ? "selected" : ''} value="Then">Then</option>
                                        </select>
                                        <span class="help">e.g. "I am a registered user"</span>
                                        <div class="input-wrap">
                                            <input type="text" value="${step.title}" name="scenarios[0].steps[0].title" id="step">
                                        </div>
                                        <div class="input-wrap">
                                             <textarea rows="4" cols="50" name="scenarios[0].steps[0].code">
                                                ${step.code}
                                            </textarea>
                                        </div>
                                    </div>
                                    <div class="actions">
                                        <button class="btn clone save-project create-project">Add Step</button>
                                        <button class="btn save-project create-project danger">Remove Step</button>
                                    </div>
                                    <br>
                                </div>
                                </c:forEach>
                            </div>
                        </div>
                    </div>
	            </form>
			</section><!-- .content -->
		</div><!-- .main -->
	</div><!-- .wrap -->
<script>
var regex = /^(.+?)(\d+)$/i;
var cloneIndex = $(".clonedInput").length;

function clone(){
    $(this).parents(".clonedInput").clone()
        .appendTo("#edit-steps")
        .attr("id", "step" +  cloneIndex)
        .find("*")
        .each(function() {
            var id = this.name || "";
            if (id == ('scenarios[0].steps[' + (cloneIndex - 1) + '].title')) {
                this.name = 'scenarios[0].steps[' + (cloneIndex) + '].title';
            }
            if (id == ('scenarios[0].steps[' + (cloneIndex - 1) + '].type')) {
                this.name = 'scenarios[0].steps[' + (cloneIndex) + '].type';
            }
            if (id == ('scenarios[0].steps[' + (cloneIndex - 1) + '].code')) {
                this.name = 'scenarios[0].steps[' + (cloneIndex) + '].code';
            }
        })
        .on('click', 'button.clone', clone)
        .on('click', 'button.remove', remove);
    cloneIndex++;
}
function remove(){
    $(this).parents(".clonedInput").remove();
}
$("button.clone").on("click", clone);

$("button.submit").on("click", function(e) {

    var url = window.location.pathname; // the script where you handle the form input.
    $.ajax({
           type: "POST",
           url: url,
           data: $("#featureForm").serialize(), // serializes the form's elements.
           success: function(data)
           {
               window.location = window.location.pathname.replace("features", "")
           }
     });

});

$("button.test").on("click", function(e) {

    var url = window.location.pathname; // the script where you handle the form input.

    $.ajax({
           type: "GET",
           url: "/test/" + '${feature.id}',
           success: function(data)
           {
               window.location = window.location.pathname.replace("features", "")
           }
     });

});
</script>
</body>
</html>
