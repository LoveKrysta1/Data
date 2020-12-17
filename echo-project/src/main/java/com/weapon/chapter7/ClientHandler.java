package com.weapon.chapter7;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

public class ClientHandler extends ChannelInboundHandlerAdapter {


    /**
     * 测试半包和LineBasedFrameDecoder解决TCP半包读写
     */
//    @Override
//    public void channelActive(ChannelHandlerContext ctx) throws Exception {
//
//        ByteBuf mes = null;
//        byte [] req = ("xdclass.net"+System.getProperty("line.separator")).getBytes();
//        //连续发送
//        for(int i=0; i< 100; i++){
//            mes = Unpooled.buffer(req.length);
//            mes.writeBytes(req);
//            ctx.writeAndFlush(mes);
//        }
//    }


    /**
     * 测试DelimiterBasedFrameDecoder
     */
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {

        String message = "kakaaasdasd  asdk&_" + "jalksd  asdjasldj l&_" + "asda a asd&&_" + "kajsda kasjdlasj  asdkjals&_";

        ByteBuf mes = null;
        mes = Unpooled.buffer(message.getBytes().length);
        mes.writeBytes(message.getBytes());
        ctx.writeAndFlush(mes);
    }


    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {

        cause.printStackTrace();
        ctx.close();
    }
}
