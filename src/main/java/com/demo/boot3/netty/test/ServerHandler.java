package com.demo.boot3.netty.test;

import com.demo.boot3.netty.demo.RequestFuture;
import com.demo.boot3.netty.demo.Response;
import com.google.gson.Gson;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandler;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.concurrent.EventExecutorGroup;
import org.jetbrains.annotations.NotNull;

import java.nio.charset.Charset;

/**
 * 业务逻辑处理
 * 这个Handler需要读取客户端数据，并对请求进行业务逻辑处理，最终把响应结果返回给客户端。
 * ServerHandler需要继承ChannelInboundHandlerAdapter，它是ChannelInboundHandler的子类，这跟Netty的处理数据流向有关。
 * 当NioEventLoop线程从Channel读取数据时，执行绑定在Channel的ChannelInboundHandler对象上，并执行其channelRead()方法。
 */
@ChannelHandler.Sharable
public class ServerHandler extends ChannelInboundHandlerAdapter {
    @Override
    public void channelRead(@NotNull ChannelHandlerContext ctx, @NotNull Object msg) throws Exception {
//        if (msg instanceof ByteBuf){
//            System.out.println(((ByteBuf) msg).toString(Charset.defaultCharset()));
//        }
//        ctx.channel().writeAndFlush("msg has recived!");
//        JsonP
        System.out.println("请求信息为：" + msg.toString());
        Gson gson = new Gson();
        RequestFuture<String> requestFuture = gson.fromJson(msg.toString(), RequestFuture.class);
        long id = requestFuture.getId();

        Response<String> response = new Response<>();
        response.setId(id);
        response.setResult("200");
        ctx.channel().writeAndFlush(gson.toJson(response));
//        ctx.channel().writeAndFlush("服务器响应成功");

    }
}
