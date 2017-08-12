<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div id="buildHistory"
    class="container-fluid pane-frame track-mouse expanded mouseover">
    <div class="row">
        <div class="col-xs-24 pane-header">Tasks History</div>
    </div>
    <div class="row pane-content">
        <table class="pane stripped">
            <tbody id="tasks">
            </tbody>
        </table>
    </div>
</div>
<script>
    size = 0;
    setInterval(function(){
        $.ajax({ url: "/tasks", success: function(data){
            if (data.length < size) {
                $("#tasks tr").remove();
                size = 0;
            }
            if ( data.length > size) {
                for (d in data) {
                    console.log(d);
                    var task = '<tr class="build-row  single-line overflow-checked"><td class="build-row-cell">' +
                    '<div class="pane build-name" style="height: 19px;"><div class="build-icon">' +
                    '<a href="#" class="build-status-link"> <img src="<c:url value="/resources/img/blue.png" />"' +
                    ' alt="Success > Console Output" style="width: 16px; height: 16px;" class="icon-blue icon-sm"' +
                    ' title="Success > Console Output"></a></div></div><div class="pane build-details" style="height: 19px;">' +
                    '<a class="build-row" href="#" class="tip model-link inside build-link">' + data[d]["browser"] + '</a>' +
                    '</div><div class="pane build-details" style="height: 19px;"></div>' +
                    '<div class="left-bar"></div></td></tr>';
                    $('#tasks').append(task);
                    size++;
                }
            }
        }, });
    }, 1000);
</script>