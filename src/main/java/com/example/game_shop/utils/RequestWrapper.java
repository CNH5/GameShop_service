package com.example.game_shop.utils;

import javax.servlet.ReadListener;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.io.*;

/**
 * @author sheng
 * @date 2021/12/25 9:29
 */
public class RequestWrapper extends HttpServletRequestWrapper {
    private byte[] buffer = {};

    public RequestWrapper(HttpServletRequest request) throws IOException {
        super(request);
        InputStream is = request.getInputStream();
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        byte[] buff = new byte[1024];
        int read;
        while ((read = is.read(buff)) > 0) {
            os.write(buff, 0, read);
            this.buffer = os.toByteArray();
        }
    }

    @Override
    public ServletInputStream getInputStream() {
        final ByteArrayInputStream is = new ByteArrayInputStream(buffer);
        return new ServletInputStream() {

            @Override
            public int read() {
                return is.read();
            }

            @Override
            public boolean isFinished() {
                return is.available() == 0;
            }

            @Override
            public boolean isReady() {
                return true;
            }

            @Override
            public void setReadListener(ReadListener readListener) {
                //do nothing
            }
        };
    }

    @Override
    public BufferedReader getReader() {
        return new BufferedReader(new InputStreamReader(this.getInputStream()));
    }

    public String getBody() throws IOException {
        StringBuilder builder = new StringBuilder();
        BufferedReader bufferedReader = getReader();
        char[] charBuffer = new char[128];
        int bytesRead;
        while ((bytesRead = bufferedReader.read(charBuffer)) > 0) {
            builder.append(charBuffer, 0, bytesRead);
        }
        return builder.toString();
    }
}
