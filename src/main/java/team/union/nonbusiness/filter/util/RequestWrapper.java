package team.union.nonbusiness.filter.util;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;

/**
 * Created with antnest-platform
 * User: chenyuan
 * Date: 12/31/14
 * Time: 8:49 PM
 */
public class RequestWrapper extends HttpServletRequestWrapper {

	private final byte[] body;

    public RequestWrapper(HttpServletRequest request,String bodyData) throws IOException {
        super(request);
        body = bodyData.getBytes(Charset.forName("UTF-8"));
    }

    @Override
    public BufferedReader getReader() throws IOException {
        return new BufferedReader(new InputStreamReader(getInputStream()));
    }

    @Override
    public ServletInputStream getInputStream() throws IOException {

        final ByteArrayInputStream bais = new ByteArrayInputStream(body);

        return new ServletInputStream() {

            @Override
            public int read() throws IOException {
                return bais.read();
            }
        };
    }
}