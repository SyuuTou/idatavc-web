<!DOCTYPE html>

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html lang="en">

<body>
<c:url value="/i/memberExcelFile" var="uploadFileUrl" />
<form method="post" enctype="multipart/form-data"
	  action="${uploadFileUrl}">
	<input type="file" name="file" accept=".xls,.xlsx" /> <input
		type="submit" value="Upload file" />
</form>
</body>

</html>
