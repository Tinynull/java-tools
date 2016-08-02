package com.zhaoliang.netty.pojo;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * Created by zhaoliang(weston_contribute@163.com) on 2016/8/1.
 */
public class DiscardServer {

    private int port;

    public DiscardServer(int port) {
        this.port = port;
    }

    public void run() throws Exception {

        /**
         * {@link NioEventLoopGroup} 是用来处理I/O操作的多线程事件循环器，Netty 提供了
         * 许多不同的 EventLoopGroup 的实现用来处理不同的传输。在这个例子中我们
         * 实现了一个服务端的应用，因此会有2个 NioEventLoopGroup 会被使用。第一
         * 个经常被叫做‘boss’，用来接收进来的连接。第二个经常被叫做‘worker’，用来
         * 处理已经被接收的连接，一旦‘boss’接收到连接，就会把连接信息注册到‘worker’
         * 上。如何知道多少个线程已经被使用，如何映射到已经创建的 Channel上都需要
         * 依赖于 EventLoopGroup 的实现，并且可以通过构造函数来配置他们的关系。
         */
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        try {
            ServerBootstrap b = new ServerBootstrap();
            b.group(bossGroup, workerGroup)
             .channel(NioServerSocketChannel.class)
             .childHandler(new ChannelInitializer<SocketChannel>() {
                 @Override
                 public void initChannel(SocketChannel ch) throws Exception {
                     ch.pipeline().addLast(new TimeServerHandler());
                 }
             })
             .option(ChannelOption.SO_BACKLOG, 128)
             .childOption(ChannelOption.SO_KEEPALIVE, true);

            // 绑定端口，开始接收进来的连接
            ChannelFuture f = b.bind(port).sync();

            // 等待服务器  socket 关闭 。
            // 在这个例子中，这不会发生，但你可以优雅地关闭你的服务器。
            f.channel().closeFuture().sync();
        } finally {
            workerGroup.shutdownGracefully();
            bossGroup.shutdownGracefully();
        }
    }

    public static void main(String[] args) throws Exception {
        int port;
        if (args.length > 0) {
            port = Integer.parseInt(args[0]);
        } else {
            port = 8088;
        }
        new DiscardServer(port).run();
    }
}
