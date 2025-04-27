package hello.container;
import hello.spring.HelloConfig;
import org.springframework.web.WebApplicationInitializer;
import
    org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRegistration;
/**
 * http://localhost:8080/hello-spring
 *
 * 스프링 MVC 제공 WebApplicationInitializer 활용
 * spring-web
 * META-INF/services/jakarta.servlet.ServletContainerInitializer
 * org.springframework.web.SpringServletContainerInitializer
 */
public class AppInitV3SpringMvc implements WebApplicationInitializer {
  @Override
  public void onStartup(ServletContext servletContext) throws ServletException
  {
    System.out.println("AppInitV3SpringMvc.onStartup");
    //스프링 컨테이너 생성
    AnnotationConfigWebApplicationContext appContext = new
        AnnotationConfigWebApplicationContext();
    appContext.register(HelloConfig.class);
    //스프링 MVC 디스패처 서블릿 생성, 스프링 컨테이너 연결
    DispatcherServlet dispatcher = new DispatcherServlet(appContext);
    //디스패처 서블릿을 서블릿 컨테이너에 등록 (이름 주의! dispatcherV3)
    ServletRegistration.Dynamic servlet =
        servletContext.addServlet("dispatcherV3", dispatcher);
    //모든 요청이 디스패처 서블릿을 통하도록 설정
    servlet.addMapping("/");
  }
}