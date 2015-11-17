<%@taglib prefix="tt"  tagdir="/WEB-INF/tags"%>
<tt:template>
	<jsp:attribute name="title">
		Segundo passo
	</jsp:attribute>
	
	<jsp:attribute name="style">
		<link href="${pageContext.request.contextPath}/jquery.fileapi-master/statics/main.css" rel="stylesheet" type="text/css"/>
		<link href="${pageContext.request.contextPath}/jquery.fileapi-master/jcrop/jquery.Jcrop.min.css" rel="stylesheet" type="text/css"/>	
	</jsp:attribute>
	<jsp:attribute name="script">
		<script>
  	  window.FileAPI = {
          debug: false // debug mode
        , staticPath: '/jquery.fileapi-master/js/jquery.fileapi/FileAPI/' // path to *.swf
  	  };
	</script>
	<script src="${pageContext.request.contextPath}/jquery.fileapi-master/FileAPI/FileAPI.min.js"></script>
	<script src="${pageContext.request.contextPath}/jquery.fileapi-master/FileAPI/FileAPI.exif.js"></script>
	<script src="${pageContext.request.contextPath}/jquery.fileapi-master/jquery.fileapi.js"></script>
	<script src="${pageContext.request.contextPath}/jquery.fileapi-master/jcrop/jquery.Jcrop.min.js"></script>
	<script src="${pageContext.request.contextPath}/jquery.fileapi-master/statics/jquery.modal.js"></script>	
	<script>
	$('#userpic').fileapi({
		   url: 'upload/',
		   accept: 'image/*',
		   imageSize: { minWidth: 200, minHeight: 200 },
		   elements: {
		      active: { show: '.js-upload', hide: '.js-browse' },
		      preview: {
		         el: '.js-preview',
		         width: 200,
		         height: 200
		      },
		      progress: '.js-progress',
		     
		   },
		   onSelect: function (evt, ui){
		      var file = ui.files[0];
		      if( !FileAPI.support.transform ) {
		         alert('Your browser does not support Flash :(');
		      }
		      else if( file ){
		         $('#popup').modal({
		            closeOnEsc: true,
		            closeOnOverlayClick: false,
		            onOpen: function (overlay){
		               $(overlay).on('click', '.js-upload', function (){
		                  $.modal().close();
		                  $('#userpic').fileapi('upload');
		               });
		               $('.js-img', overlay).cropper({
		                  file: file,
		                  bgColor: '#fff',
		                  maxSize: [$(window).width()-100, $(window).height()-100],
		                  minSize: [200, 200],
		                  selection: '90%',
		                  onSelect: function (coords){
		                     $('#userpic').fileapi('crop', file, coords);
		                  }
		               });
		            }
		         }).open();
		      }
		   }
		});
</script>
	</jsp:attribute>
	<jsp:body>
		<div id="userpic" class="userpic">
   <div class="js-preview userpic__preview"></div>
   <div class="btn btn-success js-fileapi-wrapper">
      <div class="js-browse">
         <span class="btn-txt">Choose</span>
         <input type="file" name="filedata">
         <input type="hidden" name="id" value="iddofuncionario">
      </div>
      <div class="js-upload" style="display: none;">
         <div class="progress progress-success"><div class="js-progress bar"></div></div>
         <span class="btn-txt">Uploading</span>
      </div>
   </div>
</div>
<div id="popup" class="popup" style="display: none;">
		<div class="popup__body"><div class="js-img"></div></div>
		<div style="margin: 0 0 5px; text-align: center;">
			<div class="js-upload btn btn_browse btn_browse_small">Upload</div>
		</div>
	</div>
	</jsp:body>
</tt:template>