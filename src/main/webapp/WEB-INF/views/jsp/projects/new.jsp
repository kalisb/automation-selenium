<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset='utf-8'/>
    <title>New Project | Ultimate Functional Testing</title>
    <link rel='icon' type='image/png' href="<c:url value="/resources/img/favicon.svg" />" sizes='any'/>
    <link type="text/css" rel="stylesheet" href="<c:url value="/resources/styles/styles.min.css" />" media="all" />
</head>
<body class="page-projects-new">
    <div class="wrap">
        <div id="saving">Saving...</div>
		<div id="loading">Loading...</div>
        <div class="main">
            <section class="sidebar">
                <a href="#" class="logo">Dunked</a>
                <ul class="nav main-nav">
                    <li><a href="#" class="projects current">Projects</a></li>
                    <li><a href="#" class="customize">Customize</a></li>
                </ul>
			</section>

			<section class="content">
			    <form method="POST" action="#" accept-charset="UTF-8">
			    <input name="_token" type="hidden" value="gnqU7ebXPF3qpDUSH5NePLBGigp96K89HZ3wZMec">

		        <div class="page-title edit-project-title">
                    <div class="pull-right">
                    <input type="submit" id="save-project" value="Save Project" class="btn save-project create-project" />
                </div>

			    <h1>New Project</h1>


		<div id="edit-project" class="settings-section">

			<div class="section-item title">
				<label for="title">Title</label>
				<span class="help">e.g. "Mona Lisa Portrait"</span>
				<div class="input-wrap">
					<input name="title" type="text" id="title">

				</div>
			</div>

			<div class="section-item">
				<label for="url">URL</label>
				<span class="help">e.g. http://www.example.com</span>
				<div class="input-wrap">
					<input placeholder="http://" name="url" type="text" id="url">

				</div>
			</div>

							<input name="thumbnail" type="hidden">
				<input name="thumbnail_full" type="hidden">
					</div>

	</form>

    <div id="edit-slug-modal" class="modal">
        <h2>Edit Project URL</h2>
        <p>Change the URL of this project by entering a new one below. We’ll automatically redirect the old one for you.</p>
        <div class="input-wrap">
            <div class="mask">https://kalisb.dunked.com/</div>
            <div class="slug-input">
                <input type="text" id="slug" />
            </div>
        </div>
        <div class="modal-actions">
            <a href="#" class="btn save">Save Changes</a>
            <a href="#" class="cancel">Cancel</a>
        </div>
    </div>

	<div id="edit-caption-modal" class="modal">
        <div class="modal-thumbnail"></div>
		<h2>Edit Caption</h2>
		<p>Add a caption to this image. It will be displayed alongside your image in your portfolio. Basic HTML is accepted.</p>
		<textarea id="asset-caption"></textarea>
        <div class="modal-actions">
            <a href="http://help.dunked.com/customer/portal/articles/1954893-adding-and-editing-captions" target="_blank" class="help">Need help with this?</a>
            <a href="#" class="btn save">Save Changes</a>
            <a href="#" class="cancel">Cancel</a>
        </div>
	</div>

    <div id="delete-project-modal" class="modal">
        <h2>Delete Project</h2>
        <p>Are you sure? Deleting a project cannot be undone. Your project and all project content will be gone forever.</p>
        <div class="modal-actions">
            <a href="http://help.dunked.com/customer/portal/articles/1951604-delete-projects" target="_blank" class="help">Need help with this?</a>
            <a href="#" class="btn danger delete">Delete Project</a>
            <a href="#" class="cancel">Cancel</a>
        </div>
    </div>

	<script id="template_asset" type="text/html">
	<div id="asset-{{asset.id}}"
		class="asset type-{{asset.type}} {{asset.status}}{{#asset.description}} has-caption{{/asset.description}}{{#asset.is_thumbnail}} asset-is-project-thumbnail{{/asset.is_thumbnail}}"
		data-id="{{asset.id}}" data-project-id="{{asset.project_id}}" data-type="{{asset.type}}">
		{{#asset.is_thumbnail}}
		<div class="is-project-thumbnail dunked-tooltip" title="Project Thumbnail"></div>
		{{/asset.is_thumbnail}}
		<div class="asset-thumb{{^asset.thumb_url}} no-thumbnail{{/asset.thumb_url}}">
			<img src="https://dunked.cdn.speedyrails.net/assets/images/transparent.png" alt=""
				class="transparent"/>
			{{#asset.thumb_url}}
			<div data-picture data-alt="" data-class="img-thumb">
				<div data-src="{{asset.thumb_url}}"></div>
				{{#asset.thumb_url_x2}}
				<div data-src="{{asset.thumb_url_x2}}"
					data-media="(-webkit-min-device-pixel-ratio: 2),(min--moz-device-pixel-ratio: 2),(-o-min-device-pixel-ratio: 2/1),(min-device-pixel-ratio: 2),(min-resolution: 192dpi),(min-resolution: 2dppx)"></div>
				{{/asset.thumb_url_x2}}
				<!--[if (lt IE 9) & (!IEMobile)]>
				<div data-src="{{asset.thumb_url}}"></div>
				<![endif]-->
				<noscript>
					<img src="{{asset.thumb_url}}" alt="" class="img-thumb"/>
				</noscript>
			</div>
			{{/asset.thumb_url}}
		</div>
		<a href="#" class="status">{{asset.status_label}}</a>
		<ul class="nav sf-menu">
			<li><a href="#" class="cog"></a>
				<ul>
					{{#asset.is_embed}}
					<li><a href="#embed-content-modal" class="edit-embed fancybox" data-url="{{asset.file_data}}">Edit
							Embed URL</a></li>
					{{/asset.is_embed}}
					<li><a href="#edit-caption-modal" class="edit-caption fancybox"><span class="edit-caption-text">Edit Caption</span><span
								class="add-caption-text">Add Caption</span></a></li>
											<li><a href="#" class="edit-thumbnail"{{#asset.is_thumbnail}}
							style="display:none"{{/asset.is_thumbnail}}>Use as Thumbnail</a></li>
										<li class="sep"><a href="#" class="delete">Delete {{asset.type}}</a></li>
				</ul>
			</li>
		</ul>
		<a href="#edit-caption-modal" class="edit-caption-link edit-caption fancybox" title="Edit Caption">Caption</a>
	</div>
</script>



				</section><!-- .content -->

			</div><!-- .main -->

            <div id="notice-bar" data-hash="d9f8dd83d266f4a919e59a4ef64ce6e5">
	<div class="notice-bar-inner">
        <div class="pull-right">
                        <span class="days-left-of-trial">10 days free trial remaining</span>
            <a href="https://kalisb.dunked.com/admin/upgrade" class="btn">Upgrade Now</a>
        </div>
        <div class="notice-bar-content">
		    Note: During your free trial, your site is not visible to the public, only to you.
        </div>
	</div>
</div>

		</div><!-- .wrap -->
</div>

</body>
</html>
