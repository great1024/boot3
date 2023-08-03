package com.demo.boot3.netty.test;

import com.demo.boot3.netty.demo.Response;
import com.google.gson.Gson;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.concurrent.Promise;
import lombok.Getter;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;

@Getter
@Setter
public class ClientHandler extends ChannelInboundHandlerAdapter {
    private Promise<Response<String>> promise;

    @Override
    public void channelRead(@NotNull ChannelHandlerContext ctx, @NotNull Object msg) throws Exception {
        Gson gson = new Gson();
        System.out.println("服务端信息解析, msg = " + msg);
        Response<String> response = gson.fromJson(msg.toString(), Response.class);
        promise.setSuccess(response);
    }
}
