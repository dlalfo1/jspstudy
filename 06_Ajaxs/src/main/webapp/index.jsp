<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<%
	pageContext.setAttribute("contextPath", request.getContextPath());
%>

</head>

<body>

	<div class="wrap">
	
		<h1>회원관리</h1>
		<form id="frm_member">
			<div>
				<label for="id">아이디</label>
				<input type="text" id="id" name="id">
				<span id="span"></span>
			</div>	
			<div>
				<label for="name">이름</label>
				<input type="text" id="name" name="name">
			</div>
			<div>
				<input type="radio" id="male" name="gender" value="M"> <%-- value 값을 줘야만 DB로 데이터가 넘어간다. --%>
				<label for="male">남자</label>
			</div>
			<div>
				<input type="radio" id="female" name="gender" value="F">
				<label for="male">여자</label>
			</div>		
			<div>
				<label for="address">주소</label>
				<input type="text" id="address" name="address">
			</div>	
			<div>
				<input type="hidden" id="memberNo" name="memberNo"> <%-- 조회버튼을 눌렀을 때(submit) 여기에 value값이 들어오면 된다. --%>
				<input type="button" value="초기화" onclick="fnInit()">
				<input type="button" value="신규등록" onclick="fnAddMember()">
				<input type="button" value="변경저장" onclick="fnModifyMember()">
				<input type="button" value="삭제" onclick="fnRemoveMember()">
			</div>
		</form>
		
		<hr>
		<table border="1">
			<caption>전체 회원 수 : <span id="member_count"></span></caption>
			<thead>
				<tr>
					<td>회원번호</td>
					<td>아이디</td>
					<td>이름</td>
					<td>성별</td>
					<td>주소</td>
					<td>버튼</td>
				</tr>
			</thead>
			<tbody id="member_list"></tbody>
		</table>
	
	</div>
		<script src="${contextPath}/resources/js/lib/jquery-3.6.4.min.js"></script>
		<script>
		
			/* 함수 호출 */
			fnGetAllMember();
			fnInit();
			
			/* 함수 정의 */
			function fnInit() {
				$('#id').val('').prop('readonly', false);
				$('#name').val('');
				$(':radio[name=gender]').prop('checked', false); // radio 가져오는 법 : :radio
				$('#address').val('');
			}
			
			function fnGetAllMember(){
				$.ajax({
					// 요청
					type: 'get',
					url: '${contextPath}/list.do',
					// 응답
					dataType: 'json',
					success: function(resData){  // 응답 데이터는 resData로 전달된다.
						
						/*
							resData <--- out.println(obj.toString())
							resData = {
								"memberCount": 2,
								"memberList": [
									{ },
									{ },
								]
							}
						*/
					
					$('#member_count').text(resData.memberCount);
					let memberList = $('#member_list');
					memberList.empty(); // 기존의 회원 목록을 지운다.
					
					if(resData.memberCount === 0){
						memberList.append('<tr><td colspan="6">회원이 없습니다.</td></tr>');
					} else {
						/* $.each(배열, (인덱스, 요소)->{}) */
						/* $.each(배열, function(인덱스, 요소)->{}) */
						$.each(resData.memberList, function(i, element){ // element는 하나의 회원 객체를 의미한다.
							let str = '<tr>';
							str += '<td>' + element.memberNo + '</td>';
							str += '<td>' + element.id + '</td>';
							str += '<td>' + element.name + '</td>';
							str += '<td>' + (element.gender === 'M' ? '남자' : '여자') + '</td>';
							str += '<td>' + element.address + '</td>';
							str += '<td><input type="button" value="조회" class="btn_detail" onclick="fnGetMember('+ element.memberNo + ')"></td>';
							// 조회 버튼 누르면 목록나오게 할거면 여기에 인라인 이벤트 줘도된다.
							// onclick="fnGetMember()" 이렇게 넣으면 클릭이벤트 발생시 함수가 호출되고 그 안에 요소의 memberNo속성을 넣어줬으니
							// 펑션이 실행되고 element.memberNo가 경고창에 뜬다.
							// 테이블의 목록을 만들때 버튼 클릭시엔 이미 회원번호를 알고있으므로 여기서 값을 넘겨주는게 가능한 것이다.
							memberList.append(str);
						})
					} 	
					}
				})
			}
			
			
			function fnAddMember() {
				$.ajax({
					// 요청
					type: 'post',
					url: '${contextPath}/add.do',
					data: $('#frm_member').serialize(), // 폼의 모든 입력 요소를 파라미터로 전송한다.(입력 요소에는 name 속성이 필요하다.)
														// data속성의 값은 파라미터를 의미한다.
					// 응답
					dataType: 'json',
					success: function(resData){ // try문의 응답이 resData에 저장된다. resData = {"insertRresult": 1}
						if(resData.insertResult === 1){
							alert('신규 회원이 등록되었습니다.');
							fnInit();        // 입력란 초기화
							fnGetAllMember(); // 새로운 회원 목록으로 갱신하기.
						} else {
							alert('신규 회원등록이 실패했습니다.');
						}
					},
					error: function(jqXHR){ // 예외 사유, 예외 응답코드, 예외 메시지가 다 들어있는 객체가 넘어온다.
											// jqXHR 객체에는 예외코드(응답코드: 404, 500 등 )와  예외메시지 등이 저장된다.
											// catch문의 응답은 jqXHR 객체의 responseText 속성에 저장된다.
											// catch문의 응답 메시지넌 jqXHR 객체의 responseText 속성에 저장된다.
						alert(jqXHR.responseText + '(' + jqXHR.status + ')');					
											
					}	
				}) 
			} 
			
			
			// onclick="fnGetMember(element.memberNo)"
			// fnGetMember() 함수를 호출할 때 회원번호(element.membarNo)를 인수로 전달하면 .매개변수 memberNo가 받는다.
			
			function fnGetMember(memberNo) { // 매개변수로 memberNo(변수명)을 넣으면 경고창에 값을 띄워주는 함수.
					$.ajax({
						// 요청
						type: 'get',
						url: '${contextPath}/detail.do',
						data: 'memberNo=' + memberNo,
						// 응답
						dataType: 'json',
						success: function(resData) { // resData = {"member":{"memberNo":회원번호, "gender": "M", ...}}	
							alert('회원 정보가 조회되었습니다.');
							$('#id').val(resData.member.id).prop('readonly', true);
							$('#name').val(resData.member.name);
							$(':radio[name=gender][value='+ resData.member.gender +']').prop('checked', true);
							// DB에서 가져온 value값을 가지고 있는 하나를 찾기 위해서 
							$('#address').val(resData.member.address);
							$('#memberNo').val(resData.member.memberNo); // <input type="hidden">에 저장하는 값
											
						}
						
					})
			}
			
			function fnModifyMember(){
				$.ajax({
					// 요청
					type: 'post',
					url: '${contextPath}/modify.do',
					data: $('#frm_member').serialize(),
					// 응답
					dataType: 'json',
					success: function(resData) { // resData = {"updateResult":1}
						if(resData.updateResult === 1){
							alert('회원 정보가 수정되었습니다.');
							fnGetAllMember(); // 새로운 회원 목록으로 갱신
						} else {
							alert('회원 정보가 수정이 실패했습니다.');
						}
					},
					error: function(jqXHR){
						
						alert(jqXHR.responseText + '(' + jqXHR.status + ')');					

					}	
				})
			}
		
			function fnRemoveMember(memberNo){
				$.ajax({
					type: 'post',
					url: '${contextPath}/remove.do',
					data: $('#frm_member').serialize(),
					dataType: 'json',
					success: function(resData){
						if(resData.deleteResult === 1){
							alert('회원 정보가 삭제되었습니다.');
							fnInit();        // 입력란 초기화
							fnGetAllMember();
							fnGetAllMember(); // 새로운 회원 목록으로 갱신
						} else {
							alert('회원 정보가 수정이 실패했습니다.');
						}
					}			
				})
			}
		
		$(function(){
			$('#id').on('keyup', function(){
				if($('#id').val().length > 2 ){
					$('#span').text('글자수지켜');
				} else {
					$('#span').text('');
				}
			})
		})
		
		</script>
</body>
</html>