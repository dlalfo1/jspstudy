<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.util.Map"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<%
		request.setCharacterEncoding("UTF-8");
	
		String item = request.getParameter("item");
		int itemCount = Integer.parseInt(request.getParameter("item_count"));
		
		// 제품명 + 구매수량을 하나의 Map으로 저장한다.
		/* Map<Key, Value> 
		 
		   맵에는 키와 밸류를 저장할 수 있다. item, itemCount는 String타입으로 Key에 담고, 
		   신라면과 3은 Object타입(String과, int를 모두 저장할 수 있다.)으로 value에 저장한다.
		   
		   item: 신라면		
		   itemCount : 3     => 이렇게 만들고 싶은 거임
		 
		*/
		// 제품명 + 구매수량을 하나의 Map으로 저장한다.
		Map<String, Object> map = new HashMap<>();
		map.put("item", item);
		map.put("itemCount", itemCount);

		/* 
			참고
			자바에선 서로다른 타입의 값을 저장하고 싶을 땐 Map을 사요하거나 클래스를 만든 후 객체에 저장하거나 둘 중 하나의 방식을 사용한다.
			
			class Product {
				String item;
				int itemCount;
				Product(String item, int itemCount){
					this.item = item;
					this.itemCount = itemCount;
				}
			}
			Product product = new Product(item, itemCount);
	
		*/
		
		// session에 저장된 cart 속성(애트리뷰트)이 있는지 확인한 뒤 없다면 새로운 cart를 만들어서 session에 저장한다.
		// 일단은 cart가 없으면 만들고 그 다음에 어레이리스트에 담는거임.
		// cart는 Map이 여러개 들어가는 ArrayList이다. Map은 하나니 Map을 여러개 저장할 수 있는 List에 담는거임.
		
		
		List<Map<String, Object>> cart = (List<Map<String, Object>>)session.getAttribute("cart");
			// 이 cart는 memory에 있는 cart                           여기서 가져온 cart는 session의 cart
		
		if(cart == null){ // 카트가 없다면
			cart = new ArrayList<>(); // 그 때 어레이리스트를 만드는 것이다.
			// 여기서 cart는 새로만든 cart가 아니다.
			
			
			session.setAttribute("cart", cart); // 만든 cart를 세션에 저장한다. 
		}
		
		// session의 cart에 Map 저장하기
		// ArrayList에 데이터 저장할 때 쓰는 메소드 : add()
		cart.add(map);
		//결국 여기서 add하면 memory의 cart에 저장되고 얘는 참조변수니까 이게 session의 cart를 가리키는거임
		// memory에 저장된걸 session에 알려주는거고 이 session은 memory를 참조하니 서로 매칭되는거임.
	
	%>
	
	<script>
		if(confirm('<%=item%>을 장바구니에 추가했습니다.\n장바구니를 확인하려면 "확인", 계속 쇼핑하려면 "취소"버튼을 누르세요')){
			location.href = '03_cart_list.jsp';
		} else {
			location.href = '01_form.jsp';
		}
	</script>

</body>
</html>