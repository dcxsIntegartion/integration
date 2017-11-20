package team.union.sys_sp.filter.util;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.zip.GZIPOutputStream;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;

/**
 * 一个简单的响应正文压缩，过滤器实现
 * @author 范芳铭
 */
public class ResponseWrapper implements Filter{
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		System.out.println("----过滤器开始----");
	}
	@Override
    public void doFilter(ServletRequest req, ServletResponse resp,
            FilterChain chain) throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;

        BufferResponse myresponse = new BufferResponse(response);
        chain.doFilter(request, myresponse);
        //拿出缓存中的数据，压缩后再打给浏览器
        byte out[] = myresponse.getBuffer();
        ByteArrayOutputStream bout = new ByteArrayOutputStream();
        //压缩输出流中的数据
        GZIPOutputStream gout = new GZIPOutputStream(bout);
        gout.write(out);
        gout.close();
        byte gzip[] = bout.toByteArray();
        response.setHeader("content-encoding", "gzip");
        response.setContentLength(gzip.length);
        response.getOutputStream().write(gzip);
    }
    @Override
    public void destroy() {
       // System.out.println("----过滤器销毁----");
    }
	
}
/*** 过滤器结束  **/

class BufferResponse extends HttpServletResponseWrapper{
    private ByteArrayOutputStream bout = new ByteArrayOutputStream();
    private PrintWriter pw;
    private HttpServletResponse response;
    public BufferResponse(HttpServletResponse response) {
        super(response);
        this.response = response;
    }
    @Override
    public ServletOutputStream getOutputStream() throws IOException {
        return new MyServletOutputStream(bout);
    }
    @Override
    public PrintWriter getWriter() throws IOException {
        pw = new PrintWriter(new OutputStreamWriter(bout,this.response.getCharacterEncoding()));
        return pw;
    }
    public byte[] getBuffer(){
        try{
            if(pw!=null){
                pw.close();
            }
            if(bout!=null){
                bout.flush();
                return bout.toByteArray();
            }
            return null;
        }catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
/*** response封装结束 **/

class MyServletOutputStream extends ServletOutputStream{

    private ByteArrayOutputStream bout;
    public MyServletOutputStream(ByteArrayOutputStream bout){
        this.bout = bout;
    }

    @Override
    public void write(int b) throws IOException {
        this.bout.write(b);
    }
}