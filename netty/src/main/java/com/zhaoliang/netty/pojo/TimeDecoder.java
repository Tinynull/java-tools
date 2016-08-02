package com.zhaoliang.netty.pojo;

import java.util.List;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

/**
 * Created by zhaoliang(weston_contribute@163.com) on 2016/8/2.
 */
public class TimeDecoder extends ByteToMessageDecoder {

    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
        if (in.readableBytes() < 4) {
            return; // (3)
        }

        out.add(new UnixTime(in.readUnsignedInt())); // (4)
    }
}
