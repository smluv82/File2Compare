<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/include/declare.jspf" %>

<div class="row">
	<ul class="breadcrumb pull-right">
		<li><a href="#"><i class="fa fa-home"></i> Home</a></li>
		<li><a href="#"><i class="fa fa-tachometer"></i> 메인</a></li>
	</ul>

	<div class="panel panel-default">
		<div class="panel-heading">
			<h4><i class="fa fa-tachometer"></i> 메인</h4>
		</div>

		<div class="panel-body">
			<h4>
				<a id="compareCondition" data-toggle="collapse" href="#compareTable" class="btn btn-default btn-primary">
					<i class="fa fa-plus-circle" aria-hidden="true"></i> 생성 조건
				</a>
			</h4>

			<form id="frm" name="frm" method="post" role="form" action="${pageContext.request.contextPath}/jquery/compare/makeFile/run">
				<div id="compareTable" class="collapse">
					<table class="table">
						<tbody>
							<tr>
								<th class="text-center">
									파일 최상위 경로
								</th>
								<td>
									<input type="text" id="filePath" name="filePath" value="D:/test" class="form-control"/>
								</td>
							</tr>
							<tr>
								<th class="text-center">
									암호화 방식
								</th>
								<td>
									<select id="encryptAlgorithm" name="encryptAlgorithm" class="form-control">
										<option value="SHA-256" selected="selected">SHA-256</option>
										<option value="SHA-512">SHA-512</option>
										<option value="MD5">MD5</option>
									</select>
								</td>
							</tr>
							<tr>
								<th class="text-center">
									비교파일 생성경로 및 파일명
								</th>
								<td>
									<input type="text" id="createFilePath" name="createFilePath" value="D:/test.txt" class="form-control"/>
								</td>
							</tr>
							<tr>
								<td colspan="2">
									<a href="javascript:$.make();" id="compareRunBtn" class="btn btn-default pull-right"><i class="fa fa-search"></i> 실행</a>
								</td>
							</tr>
						</tbody>
					</table>
				</div>
			</form>
		</div>
	</div>
</div>

<script type="text/javascript">
	$(document).ready(function() {
		$.make = function() {
			var filePath = $('#filePath').val();
			var createFilePath = $('#createFilePath').val();

			if(filePath == '' || filePath == undefined) {
				bootbox.alert('파일 최상위 경로를 입력하여 주세요.');
				return;
			}

			if(createFilePath == '' || createFilePath == undefined) {
				bootbox.alert('비교파일 생성경로 및 파일명을 입력하여 주세요.');
				return;
			}

			var params = {
					"filePath" : filePath,
					"encryptAlgorithm" : $('#encryptAlgorithm').val(),
					"createFilePath" : createFilePath
			}

// 			$('#frm').submit();

			$.ajax({
				type:'post',
				url: '${pageContext.request.contextPath}/jquery/compare/makeFile/run',
				data:params,
				dataType:'json',
				success:function(json) {
					alert('dsfsdfsf');
				}
			});
		}
	});
</script>