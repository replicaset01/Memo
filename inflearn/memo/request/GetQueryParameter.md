//i 1. 단일 파라미터 조회
String a = request.getParameter("Keyname");
String b = request.getParameter("ValueName");

//i 2. 전체 파라미터 조회
request.getParameterNames().asIterator().forEachRemaining(
paramName-> System.out.println(paramName + "=" + request.getParameter(paramName)));

//i 3. 이름이 같은 복수 파라미터 조회
String[] usernames = request.getParameterValues("username");
for (String name : usernames)
System.out.println("username = " + name);