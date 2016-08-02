package com.zhaoliang.netty;

import java.util.List;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * Created by zhaoliang(weston_contribute@163.com) on 2016/8/1.
 */
public class TimeServerHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelActive(final ChannelHandlerContext ctx) { // (1)

        /**
         * 为了发送一个新的消息，我们需要分配一个包含这个消息的新的缓冲。因为我们需要
         * 写入一个32位的整数，因此我们需要一个至少有4个字节的 ByteBuf。通过
         * ChannelHandlerContext.alloc() 得到一个当前的ByteBufAllocator，然后
         * 分配一个新的缓冲。
         */
        final ByteBuf time = ctx.alloc().buffer(4); // (2)
        time.writeInt((int) (System.currentTimeMillis() / 1000L + 2208988800L));

        /**
         * 和往常一样我们需要编写一个构建好的消息。但是等一等，flip 在哪？难道我们使
         * 用 NIO 发送消息时不是调用 java.nio.ByteBuffer.flip() 吗？ByteBuf 之所
         * 以没有这个方法因为有两个指针，一个对应读操作一个对应写操作。当你向 ByteBuf
         * 里写入数据的时候写指针的索引就会增加，同时读指针的索引没有变化。读指针索引和
         * 写指针索引分别代表了消息的开始和结束。
         *
         * 比较起来，NIO 缓冲并没有提供一种简洁的方式来计算出消息内容的开始和结尾，除
         * 非你调用 flip 方法。当你忘记调用 flip 方法而引起没有数据或者错误数据被发送
         * 时，你会陷入困境。这样的一个错误不会发生在 Netty 上，因为我们对于不同的操作
         * 类型有不同的指针。你会发现这样的使用方法会让你过程变得更加的容易，因为你已经
         * 习惯一种没有使用 flip 的方式。
         *
         */
        final ChannelFuture f = ctx.writeAndFlush(time); // (3)

        /**
         * 当一个写请求已经完成是如何通知到我们？这个只需要简单地在返回的 ChannelFuture
         * 上增加一个ChannelFutureListener。这里我们构建了一个匿名的 ChannelFutureListener
         * 类用来在操作完成时关闭 Channel。或者，你可以使用简单的预定义监听器代码:
         * f.addListener(ChannelFutureListener.CLOSE);
         *
         */
        f.addListener(new ChannelFutureListener() {
            @Override
            public void operationComplete(ChannelFuture future) {
                assert f == future;
                ctx.close();
            }
        }); // (4)
    }


    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        cause.printStackTrace();
        ctx.close();
    }
}
