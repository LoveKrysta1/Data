package com.weapon.echo;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.ChannelPipeline;
import io.netty.util.CharsetUtil;

public class EchoServerHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {

        //第一种
//        Channel channel = ctx.channel();
//        channel.writeAndFlush(Unpooled.copiedBuffer("Weapon is handsome",CharsetUtil.UTF_8));
        //第二种
//        ChannelPipeline channelPipeline = ctx.pipeline();
//        channelPipeline.write(Unpooled.copiedBuffer("Weapon is handsome",CharsetUtil.UTF_8));
        //第三种
//        ctx.writeAndFlush(Unpooled.copiedBuffer("Weapon is handsome",CharsetUtil.UTF_8));
        //都可以调用此write方法，前两者都会在整个管道里传播，而CHannelHandlerContext就只会在后续的Handler里面传播


        ByteBuf data = (ByteBuf) msg;

        System.out.println("服务器收到数据" + data.toString(CharsetUtil.UTF_8));

//        ctx.fireChannelRead(data);//调用下一个handler

        //回写
        ctx.writeAndFlush(data);

    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {

        System.out.println("EchoServerHandle channelReadComplete");
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        //关闭管道
        ctx.close();
    }


    /**
     * 验证channel的生命周期
     */
    @Override
    public void channelRegistered(ChannelHandlerContext ctx) throws Exception {
        System.out.println("EchoServerHandle channelRegistered");
    }

    @Override
    public void channelUnregistered(ChannelHandlerContext ctx) throws Exception {
        System.out.println("EchoServerHandle channelUnregistered");
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("EchoServerHandle channelActive");
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("EchoServerHandle channelInactive");
    }
}
