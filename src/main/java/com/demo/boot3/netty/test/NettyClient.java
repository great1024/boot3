package com.demo.boot3.netty.test;

import com.demo.boot3.netty.demo.RequestFuture;
import com.demo.boot3.netty.demo.Response;
import com.google.gson.Gson;
import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.PooledByteBufAllocator;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.sctp.nio.NioSctpChannel;
import io.netty.channel.socket.ServerSocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.codec.LengthFieldPrepender;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.util.concurrent.DefaultPromise;
import org.jetbrains.annotations.NotNull;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.concurrent.ExecutionException;

public class NettyClient {
    public static EventLoopGroup group = null;
    public static Bootstrap bootstrap = null;
    static {
        bootstrap = new Bootstrap();
        group = new NioEventLoopGroup();
        bootstrap.channel(NioSocketChannel.class);
        bootstrap.group(group);
        bootstrap.option(ChannelOption.ALLOCATOR, PooledByteBufAllocator.DEFAULT);

    }

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        DefaultPromise<Response<String>> objectDefaultPromise = new DefaultPromise<>(group.next());
        ClientHandler clientHandler = new ClientHandler();
        clientHandler.setPromise(objectDefaultPromise);
        bootstrap.handler(new ChannelInitializer<NioSocketChannel>() {
            @Override
            protected void initChannel(@NotNull NioSocketChannel ch) throws Exception {
                ch.pipeline().addLast(new LengthFieldBasedFrameDecoder(Integer.MAX_VALUE,0,4,0,4));
                ch.pipeline().addLast(new StringDecoder());
                ch.pipeline().addLast(clientHandler);
                ch.pipeline().addLast(new LengthFieldPrepender(4,false));
                ch.pipeline().addLast(new StringEncoder(StandardCharsets.UTF_8));
            }
        });
        ChannelFuture sync = bootstrap.connect("localhost", 12306).sync();
        RequestFuture<String> requestFuture = new RequestFuture<>();
        requestFuture.setId(1);
        requestFuture.setRequest("hello world");
        Gson gson = new Gson();
        String json = gson.toJson(requestFuture);
        sync.channel().writeAndFlush(json);
        Response<String> stringResponse = objectDefaultPromise.get();
        System.out.println("服务端响应 = " + gson.toJson(stringResponse));
    }
}
