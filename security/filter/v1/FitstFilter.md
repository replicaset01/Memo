```java
import javax.servlet.*;
import java.io.IOException;

public class FirstFilter implements Filter 
{
    
    @Override
    public void init(FilterConfig filterConfig) throws ServletException
    {
        Filter.super.init(filterConfig);
        System.out.println("===First Filter 생성됨===");
    }

    @Override
    public void doFilter(ServletRequest request,
                         ServletResponse response,
                         FilterChain chain)
            throws IOException, ServletException
    {
        //i (2-1) 이 곳에서 request(ServletRequest)를 이용해 다음 Filter로 넘어가기 전처리 작업을 수행한다
        System.out.println("========First Filter 시작========");
        chain.doFilter(request,response);
        System.out.println("========First Filter 종료========");
    }

    @Override
    public void destroy()
    {
        //i (5) Filter가 사용한 자원을 반납하는 처리
        System.out.println("===FirstFilter가 사라집니다===");
        Filter.super.destroy();

    }
}

```