<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!doctype html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Bootstrap demo</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-Zenh87qX5JnK2Jl0vWa8Ck2rdkQ2Bzep5IDxbcnCeuOxjzrPF/et3URy9Bv1WTRi" crossorigin="anonymous">
  </head>
  <body>
    
    <main>
		<div class="container">
			<h1 class="py-5">Upload Image Demo</h1>
			
			<form:form action="/upload" method="post" modelattribute="user" enctype="multipart/form-data">
			<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
			
			<div class="col-4">
				<input class="visually-hidden" name="id" id="id" value="${user.id}" readonly="true" />
				<div class="mb-3">
				  <label for="formFile" class="form-label">Upload profile image</label>
				  <input class="form-control" name="file" type="file" id="formFile" />
				</div>
				
				<c:if test="${not empty err_string}">
				<div class="alert alert-danger alert-dismissible fade show" role="alert">
				  ${err_string}
				  <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
				</div>
				</c:if>
			</div>
			
			<div class="col-4"><button type="submit" class="btn btn-primary">Update</button></div>
			</form:form>
		</div>
    </main>
    
    <section>
    	<div class="container py-5" modelattribute="user">
    		<h2 class="text-muted">${user.username} {${user.id}}</h2>
    		<div class="card" style="width: 18rem;">
    			<c:if test="${empty user}">
	    		<svg class="bd-placeholder-img card-img-top" width="18rem" height="20rem" xmlns="http://www.w3.org/2000/svg" role="img" focusable="false">
					<rect width="100%" height="100%" fill="#868e96"></rect>
				  	<text x="20%" y="50%" fill="#dee2e6">Upload your profile image</text>
				</svg>
				</c:if>
				
				<c:if test="${not empty user}">
				<img src="assets/img/${user.imageFile}" class="card-img-top img-fluid" width="18rem" height="20rem">
				</c:if>
    		</div>
    	</div>
    </section>
    
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-OERcA2EqjJCMA+/3y+gxIOqMEjwtxJY7qPCqsdltbNJuaOe923+mo//f6V8Qbsw3" crossorigin="anonymous"></script>
  </body>
</html>